package cqrs.control.event.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cqrs.control.domain.DeviceControl;
import cqrs.control.event.repository.snapshot.DeviceControlSnapshotRepository;
import cqrs.control.event.store.ControlEventStore;
import cqrs.core.handler.AbstractEventHandler;

@Component
public class DeviceControlEventHandler extends AbstractEventHandler<DeviceControl, String> {
  @Autowired
  public DeviceControlEventHandler(ControlEventStore eventStore, DeviceControlSnapshotRepository snapshotRepository) {
    super(eventStore, snapshotRepository);
  }
}