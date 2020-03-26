package cqrs.core.projector;

import cqrs.core.event.Event;

public interface EventProjector {
  public void handle(Event event);
}