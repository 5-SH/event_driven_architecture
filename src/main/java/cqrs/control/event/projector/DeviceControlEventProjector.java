package cqrs.control.event.projector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cqrs.control.domain.DeviceStatus;
import cqrs.control.event.DeviceControlledEvent;
import cqrs.control.event.DeviceUpdatedEvent;
import cqrs.control.event.repository.domain.DeviceControlDomainRepository;
import cqrs.core.projector.AbstractEventProjector;

/**
 * 도메인 로직 수행 -> Query 전용 모델에서 필요한 정보를 DB에 저장 디바이스 상태 변경 쿼리 수행 디바이스 최신 상태를 저장 필요에
 * 따라 전등, 월소켓 등 디바이스 모델 별로 상태를 저장할 수도 있고 의미 있는 정보를 뽑아 낼 수 있도록 DB 모델링해서 저장
 **/

@Component
public class DeviceControlEventProjector extends AbstractEventProjector {
  @Autowired
  private DeviceControlDomainRepository deviceControlDomainRepo;

  public void execute(DeviceControlledEvent event) {
    System.out.println("DeviceControlEventProjector.execute() DeviceCOntrolCreated");
    // DeviceControlCreated 객체에서 DeviceStatus 객체로 변경
    DeviceStatus deviceStatus = new DeviceStatus(event);
    // DeviceStatus 객체 저장
    deviceControlDomainRepo.save("savedevicecontrolstatus", deviceStatus);
  }

  public void execute(DeviceUpdatedEvent event) {
    System.out.println("DeviceControlEventProjector.execute() DeviceStatusUpdate");
    // DeviceControlCreated 객체에서 DeviceStatus 객체로 변경
    DeviceStatus deviceStatus = new DeviceStatus(event);
    // DeviceStatus 객체 저장
    deviceControlDomainRepo.save("savedevicecontrolstatus", deviceStatus);
  }
}