package cqrs.control.event.store;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cqrs.control.event.data.ControlEventData;
import cqrs.control.event.projector.DeviceControlEventProjector;
import cqrs.control.event.publisher.ControlEventPublisher;
import cqrs.control.event.repository.event.ControlEventGateway;
import cqrs.core.event.Event;
import cqrs.core.event.store.EventStore;

@Component
public class ControlEventStore implements EventStore<String> {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private DeviceControlEventProjector deviceControlEventProjector;
  @Autowired
  private ControlEventGateway controlEventGateway;
  @Autowired
  private ControlEventPublisher eventPublisher;

  @Override
  public void saveEvents(final String type, final String identifier, Long expectedVersion, final List<Event> events)
      throws Exception {
    if (expectedVersion > 0) {
      final String table = getTable(type);
      final List<ControlEventData> controlEventDatas = controlEventGateway.findWithIdentifier(table, identifier);
      final Long actualVersion = controlEventDatas.stream()
          .sorted(Comparator.comparing(ControlEventData::getVersion).reversed()).findFirst()
          .map(ControlEventData::getVersion).orElse(-1L);

      if (expectedVersion != actualVersion) {
        final String message = String.format("Unmatched Version : expected: {}, actual: {}", expectedVersion,
            actualVersion);
        throw new IllegalStateException(message);
      }
    }

    for (final Event event : events) {
      expectedVersion++;
      final String payload = objectMapper.writeValueAsString(event);
      final String eventType = event.getClass().getName();

      final ControlEventData controlEventData = new ControlEventData(identifier, eventType, expectedVersion, payload);

      final String table = getTable(type);
      controlEventData.setTable(table);

      controlEventGateway.save("savecontrolevent", controlEventData);

      // eventPublisher.publish(deviceControlRawEvent);

      deviceControlEventProjector.handle(event);
    }
  }

  @Override
  public List<Event<String>> getEventsByAfterVersion(final String type, final String identifier, final Long version) {
    // type을 가지고 target table 을 찾아와야 한다. sql 이름은 필요 없음
    final String table = getTable(type);
    final List<ControlEventData> deviceControlRawEvents = controlEventGateway.findWithIdentifierAfterVersion(table,
        identifier, version);
    return convertEvent(deviceControlRawEvents);
  }

  private List<Event<String>> convertEvent(final List<ControlEventData> controlRawEvents) {
    return controlRawEvents.stream().map(controlRawEvent -> {
      Event<String> event = null;
      try {
        event = (Event) objectMapper.readValue(controlRawEvent.getPayload(), Class.forName(controlRawEvent.getType()));
      } catch (IOException | ClassNotFoundException e) {
        String exceptionMessage = String.format("Event Object Convert Error : {} {}", controlRawEvent.getSeq(),
            controlRawEvent.getType(), controlRawEvent.getPayload());
      }
      return event;
    }).collect(Collectors.toList());
  }

  @Override
  public List<Event<String>> getEvents(String type, String identifier) {
    String table = getTable(type);
    final List<ControlEventData> controlEventDatas = controlEventGateway.findWithIdentifier(table, identifier);
    System.out.println("ControlEventStore.getEvents() controlEventDatas : " + controlEventDatas.toString());
    return convertEvent(controlEventDatas);
  }

  // aggregate type 을 가지고 target table 찾아오는 method
  private String getTable(String target) {
    String table = new String();
    switch (target) {
      case "class cqrs.control.domain.DeviceControl":
        table = "tb_device_control_event";
        break;
      case "class cqrs.control.domain.ElevatorControl":
        table = "tb_elevator_control_event";
        break;
      case "class cqrs.control.domain.ParkingControl":
        table = "tb_parking_control_event";
        break;
      case "class cqrs.control.domain.VisitControl":
        table = "tb_visit_control_event";
        break;
      default:
    }
    return table;
  }

  @Override
  public List<Event<String>> getAllEvents() {
    // TODO Auto-generated method stub
    return null;
  }
}