package cqrs.core.aggregate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cqrs.core.event.Event;

/**
 * 연관된 Entity 와 Value Object 묶음 데이터 변경 시 한 단위로 처리 됨 이벤트를 도메인 객체에 반영하는 handler
 * method 구현
 **/
public abstract class Aggregate<ID> implements Serializable {
  private ID identifier;
  private List<Event> changeEvents = new ArrayList<Event>();
  private Long expectedVersion = 0L;

  public Aggregate(ID identifier) {
    this.identifier = identifier;
  }

  public ID getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(ID identifier) {
    this.identifier = identifier;
  }

  public void replay(List<Event> changes) {
    for (Event event : changes) {
      applyChange(event, false);
      this.expectedVersion++;
    }
  }

  public Long getExpectedVersion() {
    return this.expectedVersion;
  }

  public List<Event> getUncommittedChanges() {
    return this.changeEvents;
  }

  public void markChangesAsCommitted() {
    this.changeEvents.clear();
  }

  // 도메인 논리가 복잡해 지면 Aggregate 의 applyChange 에서 이벤트 처리를 하는 대신
  // ProcessManager 핸들러 클래스를 만들어서 그 쪽에서 아래 작업을 하는 것이 낫다. -> Domain 의 state 를 집중해서
  // 관리 가능
  protected void applyChange(Event change) {
    System.out.println("Aggregate.applyChange() in_1");

    applyChange(change, true);
  }

  private void applyChange(Event event, boolean isNew) {
    Method method;
    try {
      method = this.getClass().getDeclaredMethod("apply", event.getClass());
      if (method != null) {
        method.setAccessible(true);
        method.invoke(this, event);
      } else {
      }

      if (isNew) {
        changeEvents.add(event);
      }
    } catch (Exception e) {
    }
  }
}