package cqrs.control.domain;

import cqrs.control.command.VisitControlCommand;
import cqrs.control.event.VisitedEvent;
import cqrs.core.aggregate.Aggregate;
import cqrs.valueobject.ValueObject;

public class VisitControl extends Aggregate<String> {
  private String complexcd;
  private String buildingno;
  private String householdcd;
  private String partnerid;
  private String partnerHouseholdcd;
  private String indexno;
  private String location;

  public VisitControl() {
    super("");
  }

  public VisitControl(String partnerHouseholdcd) {
    super(partnerHouseholdcd);
    this.partnerHouseholdcd = partnerHouseholdcd;
  }

  public void create(VisitControlCommand command) {
    super.setIdentifier(command.getPartnerHouseholdcd());

    ValueObject pVo = new ValueObject();
    pVo.set("partnerid", command.getPartnerid());
    pVo.set("partnerHouseholdcd", command.getPartnerHouseholdcd());
    pVo.set("indexno", command.getIndexno());
    pVo.set("location", command.getLocation());

    VisitedEvent event = new VisitedEvent(pVo);

    applyChange(event);
  }

  public void update(VisitControlCommand command) {
    ValueObject pVo = new ValueObject();
    pVo.set("partnerid", command.getPartnerid());
    pVo.set("partnerHouseholdcd", command.getPartnerHouseholdcd());
    pVo.set("indexno", command.getIndexno());
    pVo.set("location", command.getLocation());
    VisitedEvent event = new VisitedEvent();

    applyChange(event);
  }

  public void apply(VisitedEvent event) {
    this.partnerid = event.getPartnerid();
    this.partnerHouseholdcd = event.getPartnerHouseholdcd();
    this.indexno = event.getIndexno();
    this.location = event.getLocation();
  }
}
