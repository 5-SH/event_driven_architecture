package cqrs.control.domain;

import cqrs.control.event.DeviceControlledEvent;
import cqrs.control.event.DeviceUpdatedEvent;

public class DeviceStatus {
  private String deviceCd;
  private String resource;
  private String attribute;
  private String value;
  private String deviceTypeCd;
  private String deviceModelCd;
  private String nodeCd;

  public DeviceStatus(DeviceControlledEvent event) {
    this.deviceCd = event.getDevicecd();
    this.resource = event.getResource();
    this.attribute = event.getAttribute();
    this.value = event.getValue();
    this.deviceTypeCd = event.getDevicetypecd();
    this.deviceModelCd = event.getDevicemodelcd();
    this.nodeCd = event.getNodecd();
  }

  public DeviceStatus(DeviceUpdatedEvent event) {
    this.deviceCd = event.getDevicecd();
    this.resource = event.getResource();
    this.attribute = event.getAttribute();
    this.value = event.getValue();
  }

  @Override
  public String toString() {
    return "DeviceStatus [deviceCd=" + deviceCd + ", resource=" + resource + ", attribute=" + attribute + ", value="
        + value + ", deviceTypeCd=" + deviceTypeCd + ", deviceModelCd=" + deviceModelCd + ", nodeCd=" + nodeCd + "]";
  }
}
