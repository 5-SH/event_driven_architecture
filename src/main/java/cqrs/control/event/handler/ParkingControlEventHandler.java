package cqrs.control.event.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cqrs.control.domain.ParkingControl;
import cqrs.control.event.repository.snapshot.ParkingControlSnapshotRepository;
import cqrs.control.event.store.ControlEventStore;
import cqrs.core.handler.AbstractEventHandler;

@Component
public class ParkingControlEventHandler extends AbstractEventHandler<ParkingControl, String> {
  @Autowired
  public ParkingControlEventHandler(ControlEventStore eventStore, ParkingControlSnapshotRepository snapshotRepository) {
    super(eventStore, snapshotRepository);
  }
}