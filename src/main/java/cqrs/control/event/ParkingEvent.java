package cqrs.control.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import cqrs.valueobject.ValueObject;

public class ParkingEvent extends AbstractControlEvent {
  private String partnerid;
  private String partnerHouseholdcd;
  private String location;
  private String carno;
  private String date;

  public ParkingEvent(ValueObject evtVo) {
    this.partnerid = evtVo.getString("partnerid");
    this.partnerHouseholdcd = evtVo.getString("partnerHouseholdcd");
    this.location = evtVo.getString("location");
    this.carno = evtVo.getString("carno");

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
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

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getCarno() {
    return carno;
  }

  public void setCarno(String carno) {
    this.carno = carno;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "ParkingEvent [partnerid=" + partnerid + ", partnerHouseholdcd=" + partnerHouseholdcd + ", location="
        + location + ", carno=" + carno + ", date=" + date + "]";
  }

  @Override
  public String getIdentifier() {
    return this.partnerHouseholdcd;
  }

}