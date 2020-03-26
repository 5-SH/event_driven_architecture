package cqrs.core.handler;

import java.util.List;

import cqrs.core.aggregate.Aggregate;

public interface EventHandler<A extends Aggregate, ID> {
  void save(A aggregate) throws Exception;

  A find(ID identifier);

  List<A> findAll();
}