package cqrs.control.domain;

import cqrs.control.command.DeviceControlCommand;
import cqrs.control.event.DeviceControlledEvent;
import cqrs.control.event.DeviceUpdatedEvent;
import cqrs.core.aggregate.Aggregate;
import cqrs.valueobject.ValueObjectAssembler;

public class DeviceControl extends Aggregate<String> {
  private String devicecd;
  private String resource;
  private String attribute;
  private String value;
  private String nodecd;

  public DeviceControl() {
    super("");
  }

  // find 에서 reflection을 사용해 Domain 객체 생성할 때 사용
  public DeviceControl(String devicecd) {
    super(devicecd);
    this.devicecd = devicecd;
  }

  public void create(DeviceControlCommand command) {
    System.out.println("DeviceControl.create() in");

    /**
     * DeviceControl deviceContol = new
     * DeviceControl(deviceControlCreated.getDeviceCd(),
     * deviceControlCreated.getResource(), deviceControlCreated.getAttribute(),
     * deviceControlCreated.getValue(), deviceControlCreated.getNodeCd(),
     * deviceControlCreated.getDeviceModelCd(),
     * deviceControlCreated.getDeviceTypeCd())
     **/

    // DeviceControlCommand 에서 Event 생성
    super.setIdentifier(command.getDevice().getString("devicecd"));

    ValueObjectAssembler pVoa = new ValueObjectAssembler();
    pVoa.put("device", command.getDevice());
    pVoa.put("operation", command.getOperation());
    pVoa.put("auth", command.getAuth());

    DeviceControlledEvent event = new DeviceControlledEvent(pVoa);

    applyChange(event);
  }

  public void update(DeviceControlCommand command) {
    // DeviceControlCommand 에서 Event 생성
    ValueObjectAssembler pVoa = new ValueObjectAssembler();
    pVoa.put("device", command.getDevice());
    pVoa.put("operation", command.getOperation());
    pVoa.put("auth", command.getAuth());

    DeviceControlledEvent event = new DeviceControlledEvent(pVoa);

    applyChange(event);
  }

  public void apply(DeviceControlledEvent event) {
    this.devicecd = event.getDevicecd();
    this.resource = event.getResource();
    this.attribute = event.getAttribute();
    this.value = event.getValue();
    this.nodecd = event.getNodecd();
  }

  public void apply(DeviceUpdatedEvent event) {
    this.devicecd = event.getDevicecd();
    this.resource = event.getResource();
    this.attribute = event.getAttribute();
    this.value = event.getValue();
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

  public String getNodecd() {
    return nodecd;
  }

  public void setNodecd(String nodecd) {
    this.nodecd = nodecd;
  }

  @Override
  public String toString() {
    return "DeviceControl [devicecd=" + devicecd + ", resource=" + resource + ", attribute=" + attribute + ", value="
        + value + ", nodecd=" + nodecd + "]";
  }

}
