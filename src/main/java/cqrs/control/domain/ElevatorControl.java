package cqrs.control.domain;

import cqrs.control.command.ElevatorControlCommand;
import cqrs.control.event.ElevatorCalledEvent;
import cqrs.core.aggregate.Aggregate;
import cqrs.valueobject.ValueObjectAssembler;

public class ElevatorControl extends Aggregate<String> {
  private String complexcd;
  private String buildingno;
  private String householdcd;
  private String partnerHouseholdcd;

  public ElevatorControl() {
    super("");
  }

  public ElevatorControl(String partnerHouseholdcd) {
    super(partnerHouseholdcd);
    this.partnerHouseholdcd = partnerHouseholdcd;
  }

  public void create(ElevatorControlCommand command) {
    super.setIdentifier(command.getCondition().getString("partnerhouseholdcd"));

    ValueObjectAssembler pVoa = new ValueObjectAssembler();
    pVoa.put("condition", command.getCondition());
    // complexcd, buildingno, householdcd 값 DB 검색해서 가져오는 로직 추가할 부분, pVoa 에 set
    ElevatorCalledEvent event = new ElevatorCalledEvent(pVoa);

    applyChange(event);
  }

  public void update(ElevatorControlCommand command) {
    System.out.println("ElevatorControl.update() in");

    ValueObjectAssembler pVoa = new ValueObjectAssembler();
    pVoa.put("condition", command.getCondition());
    // complexcd, buildingno, householdcd 값 DB검색해서 가져오는 로직 추가할 부분, pVoa 에 set
    ElevatorCalledEvent event = new ElevatorCalledEvent(pVoa);

    applyChange(event);
  }

  public void apply(ElevatorCalledEvent event) {
    // VisitControl 도메인 객체 field 값 set
    this.partnerHouseholdcd = event.getPartnerHouseholdcd();
  }
}
