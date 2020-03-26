package cqrs.control.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import cqrs.valueobject.ValueObjectAssembler;

public class ElevatorCalledEvent extends AbstractControlEvent {
  // TODO: DB 검색해서 추가하도록 수정
  private String complexcd;
  private String buildingno;
  private String householdcd;
  private String partnerHouseholdcd;
  private String date;

  public ElevatorCalledEvent() {
    this.complexcd = "";
    this.buildingno = "";
    this.householdcd = "";
    this.date = "";
  }

  public ElevatorCalledEvent(ValueObjectAssembler evtVoa) {
    // this.complexcd = evtVoa.getString("complexcd");
    // this.buildingno = evtVoa.getString("buildingno");
    // thishouseholdcd = evtVoa.getString("householdcd");

    this.partnerHouseholdcd = evtVoa.get("condition").getString("partnerhouseholdcd");

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    this.date = dateFormat.format(date).toString();
  }

  public String getComplexcd() {
    return complexcd;
  }

  public void setComplexcd(String complexcd) {
    this.complexcd = complexcd;
  }

  public String getBuildingno() {
    return buildingno;
  }

  public void setBuildingno(String buildingno) {
    this.buildingno = buildingno;
  }

  public String getHouseholdcd() {
    return householdcd;
  }

  public void setHouseholdcd(String householdcd) {
    this.householdcd = householdcd;
  }

  public String getPartnerHouseholdcd() {
    return partnerHouseholdcd;
  }

  public void setPartnerHouseholdcd(String partnerHouseholdcd) {
    this.partnerHouseholdcd = partnerHouseholdcd;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "ElevatorCalledEvent [complexcd=" + complexcd + ", buildingno=" + buildingno + ", householdcd=" + householdcd
        + ", partnerHouseholdcd=" + partnerHouseholdcd + ", date=" + date + "]";
  }

  @Override
  public String getIdentifier() {
    return this.partnerHouseholdcd;
  }

}