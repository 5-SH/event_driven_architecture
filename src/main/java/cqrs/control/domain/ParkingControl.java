package cqrs.control.domain;

import cqrs.control.command.ParkingControlCommand;
import cqrs.control.event.ParkingEvent;
import cqrs.core.aggregate.Aggregate;
import cqrs.valueobject.ValueObject;

public class ParkingControl extends Aggregate<String> {
  private String partnerid;
  private String partnerHouseholdcd;
  private String location;
  private String carno;

  public ParkingControl() {
    super("");
  }

  public ParkingControl(String partnerHouseholdcd) {
    super(partnerHouseholdcd);
    this.partnerHouseholdcd = partnerHouseholdcd;
  }

  public void create(ParkingControlCommand command) {
    super.setIdentifier(command.getPartnerHouseholdcd());

    ValueObject pVo = new ValueObject();
    pVo.set("partnerid", command.getPartnerid());
    pVo.set("partnerHouseholdcd", command.getPartnerHouseholdcd());
    pVo.set("location", command.getLocation());
    pVo.set("carno", command.getCarno());

    ParkingEvent event = new ParkingEvent(pVo);

    applyChange(event);
  }

  public void update(ParkingControlCommand command) {
    ValueObject pVo = new ValueObject();
    pVo.set("partnerid", command.getPartnerid());
    pVo.set("partnerHouseholdcd", command.getPartnerHouseholdcd());
    pVo.set("carno", command.getCarno());
    pVo.set("location", command.getLocation());

    ParkingEvent event = new ParkingEvent(pVo);

    applyChange(event);
  }

  public void apply(ParkingEvent event) {
    this.partnerid = event.getPartnerid();
    this.partnerHouseholdcd = event.getPartnerHouseholdcd();
    this.carno = event.getCarno();
    this.location = event.getLocation();
  }
}
