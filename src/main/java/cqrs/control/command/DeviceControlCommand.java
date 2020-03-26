package cqrs.control.command;

import cqrs.valueobject.ValueObject;
import cqrs.valueobject.ValueObjectAssembler;

public class DeviceControlCommand {
  private ValueObject service;
  private ValueObject device;
  private ValueObject operation;
  private ValueObject auth;

  // TODO: cmdVoa parameter value validation 추가
  public DeviceControlCommand(ValueObjectAssembler cmdVoa) {
    this.service = cmdVoa.get("service");
    this.device = cmdVoa.get("device");
    this.operation = cmdVoa.get("operation");
    this.auth = cmdVoa.get("auth");
  }

  // TODO: cmdVoa parameter value validation 추가
  private boolean parameterValidation(ValueObjectAssembler pVoa, String pStr) {
    return pVoa.containsKey(pStr);
  }

  public ValueObject getService() {
    return service;
  }

  public void setService(ValueObject service) {
    this.service = service;
  }

  public ValueObject getDevice() {
    return device;
  }

  public void setDevice(ValueObject device) {
    this.device = device;
  }

  public ValueObject getOperation() {
    return operation;
  }

  public void setOperation(ValueObject operation) {
    this.operation = operation;
  }

  public ValueObject getAuth() {
    return auth;
  }

  public void setAuth(ValueObject auth) {
    this.auth = auth;
  }

  @Override
  public String toString() {
    return "DeviceControlCommand [service=" + service + ", device=" + device + ", operation=" + operation + ", auth="
        + auth + "]";
  }

}
