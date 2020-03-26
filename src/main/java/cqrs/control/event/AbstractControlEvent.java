package cqrs.control.event;

import cqrs.core.event.Event;

public abstract class AbstractControlEvent implements Event<String> {
  protected String deviceCd;

  @Override
  public abstract String getIdentifier();
}