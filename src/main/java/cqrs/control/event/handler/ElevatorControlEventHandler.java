package cqrs.control.event.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cqrs.control.domain.ElevatorControl;
import cqrs.control.event.repository.snapshot.ElevatorControlSnapshotRepository;
import cqrs.control.event.store.ControlEventStore;
import cqrs.core.handler.AbstractEventHandler;

@Component
public class ElevatorControlEventHandler extends AbstractEventHandler<ElevatorControl, String> {
  @Autowired
  public ElevatorControlEventHandler(ControlEventStore eventStore,
      ElevatorControlSnapshotRepository snapshotRepository) {
    super(eventStore, snapshotRepository);
  }
}