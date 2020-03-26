package cqrs.core.snapshot.repository;

import java.util.Optional;

import cqrs.core.aggregate.Aggregate;
import cqrs.core.snapshot.Snapshot;

public interface SnapshotRepository<A extends Aggregate, ID> {
  Optional<Snapshot<A, ID>> findLatest(ID id);

  void save(Snapshot<A, ID> snapshot);
}