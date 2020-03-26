package cqrs.control.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import cqrs.valueobject.ValueObject;
import cqrs.valueobject.ValueObjectAssembler;

public class DeviceUpdatedEvent extends AbstractControlEvent {
  private String devicecd;
  private String resource;
  private String attribute;
  private String value;
  private String createdDate;

  public DeviceUpdatedEvent() {
    super();
    this.devicecd = "";
    this.resource = "";
    this.attribute = "";
    this.value = "";

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String createdDate = dateFormat.format(date).toString();

    this.createdDate = createdDate;
  }

  public DeviceUpdatedEvent(ValueObjectAssembler evtVoa) {
    ValueObject dvcVo = evtVoa.get("device");
    this.devicecd = dvcVo.getString("devicecd");

    ValueObject oprtVo = evtVoa.get("operation");
    this.resource = oprtVo.getString("resource");
    this.attribute = oprtVo.getString("attribute");
    this.value = oprtVo.getString("value");

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    String createdDate = dateFormat.format(date).toString();

    this.createdDate = createdDate;
  }

  public String getDevicecd() {
    return devicecd;
  }

  public void setDevicecd(String devicecd) {
    this.devicecd = devicecd;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String toString() {
    return "DeviceUpdatedEvent [deviceCd=" + deviceCd + ", resource=" + resource + ", attribute=" + attribute
        + ", value=" + value + ", createdDate=" + createdDate + "]";
  }

  @Override
  public String getIdentifier() {
    return this.devicecd;
  }
}