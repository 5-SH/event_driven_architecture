package cqrs.core.event.store;

import java.util.List;

import cqrs.core.event.Event;

public interface EventStore<ID> {
  void saveEvents(String type, ID identifier, Long expectedVersion, List<Event> baseEvents) throws Exception;

  List<Event<ID>> getEvents(String type, ID identifier);

  List<Event<ID>> getAllEvents();

  List<Event<ID>> getEventsByAfterVersion(String type, ID identifier, Long version);
}