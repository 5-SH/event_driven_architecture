package cqrs.core.snapshot;

import java.io.Serializable;

import cqrs.core.aggregate.Aggregate;

/**
 * 이벤트 저장 시 생성 이벤트 발생 개수를 기준으로 생성 EventStore 와 분리된 저장소에 저장
 **/
public class Snapshot<A extends Aggregate, ID> implements Serializable {
  private ID identifier;
  private Long version;
  private A aggregate;

  public Snapshot(ID identifier, Long version, A aggregate) {
    this.identifier = identifier;
    this.version = version;
    this.aggregate = aggregate;
  }

  public ID getIdentifier() {
    return identifier;
  }

  public void setIdentifier(ID identifier) {
    this.identifier = identifier;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public A getAggregate() {
    return aggregate;
  }

  public void setAggregate(A aggregate) {
    this.aggregate = aggregate;
  }

  @Override
  public String toString() {
    return "Snapshot [identifier=" + identifier + ", version=" + version + ", aggregate=" + aggregate + "]";
  }
}