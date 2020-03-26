package cqrs.control.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import cqrs.valueobject.ValueObject;
import cqrs.valueobject.ValueObjectAssembler;

public class DeviceControlledEvent extends AbstractControlEvent {
  // 디바이스 정보
  private String devicecd;
  private String devicetypecd;
  private String devicemodelcd;
  private String nodecd;

  // 세대 정보
  private String complexcd;
  private String buildingno;
  private String householdcd;

  private String spacesq;
  private String spacetypecd;

  // 제어 정보
  private String resource;
  private String attribute;
  private String value;

  private String date;

  /**
   * event = (Event) objectMapper.readValue(deviceControlRawEvent.getPayload(),
   * Class.forName(deviceControlRawEvent.getType())) 의
   * Class.forName(deviceControlRawEvent.getType()), deviceControlRawEvent.getType
   * = DeviceControlCreated 부분에서 DeviceControlCreated 클래스를 기본 생성자를 통해 인스턴스 화 한다.
   * Default 생성자가 정의 안되어 있을 경우 생성자를 찾지 못해 에러를 리턴한다. 인자가 있는 생성자를 만들 경우 디폴트 생성자는
   * 만들어지지 않는다.
   **/
  public DeviceControlledEvent() {
    this.devicecd = "";
    this.devicetypecd = "";
    this.nodecd = "";

    this.complexcd = "";
    this.buildingno = "";
    this.householdcd = "";

    this.spacesq = "";
    this.spacetypecd = "";

    this.resource = "";
    this.attribute = "";
    this.value = "";

    this.date = "";
  }

  public DeviceControlledEvent(ValueObjectAssembler evtVoa) {
    ValueObject dvcVo = evtVoa.get("device");

    this.devicecd = dvcVo.getString("devicecd");
    this.nodecd = dvcVo.getString("nodecd");
    // TODO: 나중에 구현하기
    // this.devicetypecd = dvcVo.getString("devicetypecd");

    ValueObject oprtVo = evtVoa.get("operation");

    this.resource = oprtVo.getString("resource");
    this.attribute = oprtVo.getString("attribute");
    this.value = oprtVo.getString("value");

    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    this.date = dateFormat.format(date).toString();

    // TODO: 나중에 구현하기
    // this.complexcd = evtVo.getString("complexcd");
    // this.buildingno = evtVo.getString("buildingno");
    // this.householdcd = evtVo.getString("householdcd");

    // TODO: 나중에 구현하기
    // this.spacesq = evtVo.getString("spacesq");
    // this.spacetypecd = evtVo.getString("spacetypecd");
  }

  public String getDevicecd() {
    return devicecd;
  }

  public void setDevicecd(String devicecd) {
    this.devicecd = devicecd;
  }

  public String getDevicetypecd() {
    return devicetypecd;
  }

  public void setDevicetypecd(String devicetypecd) {
    this.devicetypecd = devicetypecd;
  }

  public String getDevicemodelcd() {
    return devicemodelcd;
  }

  public void setDevicemodelcd(String devicemodelcd) {
    this.devicemodelcd = devicemodelcd;
  }

  public String getNodecd() {
    return nodecd;
  }

  public void setNodecd(String nodecd) {
    this.nodecd = nodecd;
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

  public String getSpacesq() {
    return spacesq;
  }

  public void setSpacesq(String spacesq) {
    this.spacesq = spacesq;
  }

  public String getSpacetypecd() {
    return spacetypecd;
  }

  public void setSpacetypecd(String spacetypecd) {
    this.spacetypecd = spacetypecd;
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "DeviceControlledEvent [attribute=" + attribute + ", buildingno=" + buildingno + ", complexcd=" + complexcd
        + ", date=" + date + ", devicecd=" + devicecd + ", devicemodelcd=" + devicemodelcd + ", devicetypecd="
        + devicetypecd + ", householdcd=" + householdcd + ", nodecd=" + nodecd + ", resource=" + resource + ", spacesq="
        + spacesq + ", spacetypecd=" + spacetypecd + ", value=" + value + "]";
  }

  @Override
  public String getIdentifier() {
    return this.devicecd;
  }
}