package cqrs.control.event.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cqrs.control.domain.VisitControl;
import cqrs.control.event.repository.snapshot.VisitControlSnapshotRepository;
import cqrs.control.event.store.ControlEventStore;
import cqrs.core.handler.AbstractEventHandler;

@Component
public class VisitControlEventHandler extends AbstractEventHandler<VisitControl, String> {
  @Autowired
  public VisitControlEventHandler(ControlEventStore eventStore, VisitControlSnapshotRepository snapshotRepository) {
    super(eventStore, snapshotRepository);
  }
}