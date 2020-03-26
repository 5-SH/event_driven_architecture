package cqrs.control.event.repository.snapshot;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import cqrs.control.domain.ParkingControl;
import cqrs.core.snapshot.Snapshot;
import cqrs.core.snapshot.repository.SnapshotRepository;

@Repository
public class ParkingControlSnapshotRepository implements SnapshotRepository<ParkingControl, String> {
  private List<Snapshot<ParkingControl, String>> snapshots = new CopyOnWriteArrayList<>();

  @Override
  public Optional<Snapshot<ParkingControl, String>> findLatest(String identifier) {
    return snapshots.stream().filter(snapshot -> snapshot.getIdentifier().equals(identifier))
        .reduce((s1, s2) -> s1.getVersion() > s2.getVersion() ? s1 : s2);
  }

  @Override
  public void save(Snapshot<ParkingControl, String> snapshot) {
    snapshots.add(snapshot);
  }
}