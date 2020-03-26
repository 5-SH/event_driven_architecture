package cqrs.core.handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import cqrs.core.aggregate.Aggregate;
import cqrs.core.event.Event;
import cqrs.core.event.EventData;
import cqrs.core.event.store.EventStore;
import cqrs.core.snapshot.Snapshot;
import cqrs.core.snapshot.repository.SnapshotRepository;

public abstract class AbstractEventHandler<A extends Aggregate, ID> implements EventHandler<A, ID> {
  private static final int SNAPSHOT_COUNT = 10;
  private final Class<A> aggregateType;
  private Map<ID, AtomicInteger> snapshotCountMap = new ConcurrentHashMap<ID, AtomicInteger>();

  private EventStore<ID> eventStore;
  private SnapshotRepository<A, ID> SnapshotRepository;

  public AbstractEventHandler(EventStore eventStore, SnapshotRepository snapshotRepository) {
    this.eventStore = eventStore;
    this.SnapshotRepository = snapshotRepository;
    this.aggregateType = aggregateType();
  }

  private Class<A> aggregateType() {
    Type genType = this.getClass().getGenericSuperclass();
    if (genType instanceof ParameterizedType) {
      Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

      if ((params != null) && (params.length >= 1)) {
        return (Class) params[0];
      }
    }
    return null;
  }

  @Override
  public void save(Aggregate aggregate) throws Exception {
    final ID identifier = (ID) aggregate.getIdentifier();
    eventStore.saveEvents(aggregateType.toString(), identifier, aggregate.getExpectedVersion(),
        aggregate.getUncommittedChanges());

    aggregate.markChangesAsCommitted();
    AtomicInteger snapshotCount = snapshotCountMap.get(identifier);

    if (snapshotCount == null) {
      snapshotCount = new AtomicInteger(0);
      snapshotCountMap.put(identifier, snapshotCount);
    }

    if (snapshotCount.get() == SNAPSHOT_COUNT) {
      Snapshot<A, ID> snapshot = new Snapshot(identifier, aggregate.getExpectedVersion(), aggregate);
      SnapshotRepository.save(snapshot);
      snapshotCount.set(0);
      return;
    }
    final int increaseCount = snapshotCount.incrementAndGet();
  }

  @Override
  public A find(ID identifier) {
    A aggregate = createAggregateRootViaReflection(identifier);
    Optional<Snapshot<A, ID>> retrieveSnapshot = retrieveSnapshot(identifier);

    List<Event<ID>> baseEvents;
    if (retrieveSnapshot.isPresent()) {
      Snapshot<A, ID> snapshot = retrieveSnapshot.get();
      baseEvents = eventStore.getEventsByAfterVersion(aggregateType.toString(), snapshot.getIdentifier(),
          snapshot.getVersion());
      aggregate = snapshot.getAggregate();
    } else {
      baseEvents = eventStore.getEvents(aggregateType.toString(), identifier);
    }

    if (baseEvents == null || baseEvents.size() == 0) {
      return null;
    }

    aggregate.replay(baseEvents);

    return aggregate;
  }

  @Override
  public List<A> findAll() {
    List<A> result = new ArrayList<>();

    // TODO: getAllEvents 구현 필요
    List<Event<ID>> allEventsOpt = eventStore.getAllEvents();
    if (allEventsOpt == null) {
      return result;
    }

    /**
     * Event interface 의 getIdentifier method 의 리턴 값 identifier 로 전체 Event 를 그룹핑 한다.
     * 모든 Event 상속체 는 identifier, type, version, payload 를 멤버로 가지고 있어야한다. ex.
     * DeviceControlledEvent, DeviceUpdatedEvent Event 상속체는 Domain 객체가 비즈니스 로직을 수행하며
     * 생성한다. ex. DeviceControl - update, create method
     **/
    Map<ID, List<Event<ID>>> eventsByIdentifier = allEventsOpt.stream()
        .collect(Collectors.groupingBy(Event::getIdentifier));
    for (Map.Entry<ID, List<Event<ID>>> entry : eventsByIdentifier.entrySet()) {
      A aggregateRoot = createAggregateRootViaReflection(entry.getKey());
      aggregateRoot.replay(entry.getValue());

      result.add(aggregateRoot);
    }
    return result;
  }

  private A createAggregateRootViaReflection(ID identifier) {
    try {
      Constructor[] constructors = aggregateType.getDeclaredConstructors();
      for (Constructor constructor : constructors) {
        if (constructor.getParameterCount() == 1) {
          constructor.setAccessible(true);
          // 도메인 객체마다 identifier 로 instance 생성하는 생성자가 있어야 함.
          return (A) constructor.newInstance(identifier);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    throw new IllegalArgumentException("Aggregate에 identifier 를 argument 로 받는 생성자가 없음");
  }

  private Optional<Snapshot<A, ID>> retrieveSnapshot(ID identifier) {
    if (SnapshotRepository == null) {
      return Optional.empty();
    }
    return SnapshotRepository.findLatest(identifier);
  }
}