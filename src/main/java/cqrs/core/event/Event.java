package cqrs.core.event;

import java.io.Serializable;

enum EventType {
  DEVICE_CONTROL
}

// Domain 로직을 수행하며 발생하는 Event
public interface Event<ID> extends Serializable {
  public ID getIdentifier();
}