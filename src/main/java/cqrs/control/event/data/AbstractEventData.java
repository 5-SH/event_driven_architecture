package cqrs.control.event.data;

import cqrs.core.event.EventData;

public abstract class AbstractEventData<ID> implements EventData<ID> {
  protected String date;
  protected int seq;
  protected String identifier;
  protected String type;
  protected Long version;
  protected String payload;

  public AbstractEventData() {
  }

  @Override
  public abstract ID getIdentifier();

  @Override
  public abstract String getType();

  @Override
  public abstract Long getVersion();

  @Override
  public abstract String getPayload();
}