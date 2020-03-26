package cqrs.control.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import cqrs.valueobject.ValueObject;

public class VisitedEvent extends AbstractControlEvent {
  // TODO: 세대 정보
  // private String complexcd;
  // private String buildingno;
  // private String householdcd;
  private String partnerid;
  private String partnerHouseholdcd;
  private String indexno;
  private String location;
  private String date;

  public VisitedEvent() {
    this.partnerid = "";
    this.partnerHouseholdcd = "";
    this.indexno = "";
    this.location = "";
    this.date = "";
  }

  public VisitedEvent(ValueObject evtVo) {
    this.partnerid = evtVo.getString("partnerid");
    this.partnerHouseholdcd = evtVo.getString("partnerHouseholdcd");
    this.indexno = evtVo.getString("indexno");
    this.location = evtVo.getString("location");

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat();
    this.date = dateFormat.format(date).toString();
  }

  public String getPartnerid() {
    return partnerid;
  }

  public void setPartnerid(String partnerid) {
    this.partnerid = partnerid;
  }

  public String getPartnerHouseholdcd() {
    return partnerHouseholdcd;
  }

  public void setPartnerHouseholdcd(String partnerHouseholdcd) {
    this.partnerHouseholdcd = partnerHouseholdcd;
  }

  public String getIndexno() {
    return indexno;
  }

  public void setIndexno(String indexno) {
    this.indexno = indexno;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "VisitedEvent [partnerid=" + partnerid + ", partnerHouseholdcd=" + partnerHouseholdcd + ", indexno="
        + indexno + ", location=" + location + ", date=" + date + "]";
  }

  @Override
  public String getIdentifier() {
    return this.partnerHouseholdcd;
  }

}