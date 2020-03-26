package cqrs.controller;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cqrs.control.command.DeviceControlCommand;
import cqrs.control.command.ElevatorControlCommand;
import cqrs.control.command.ParkingControlCommand;
import cqrs.control.command.VisitControlCommand;
import cqrs.control.domain.DeviceControl;
import cqrs.control.domain.ElevatorControl;
import cqrs.control.domain.ParkingControl;
import cqrs.control.domain.VisitControl;
import cqrs.control.event.handler.DeviceControlEventHandler;
import cqrs.control.event.handler.ElevatorControlEventHandler;
import cqrs.control.event.handler.ParkingControlEventHandler;
import cqrs.control.event.handler.VisitControlEventHandler;
import cqrs.valueobject.ValueObjectAssembler;

/**
 * Handles requests for the application home page.
 */
@RestController
public class ControlController {

  private static final Logger logger = LoggerFactory.getLogger(ControlController.class);

  @Autowired
  private DeviceControlEventHandler deviceEventHandler;

  @Autowired
  private ElevatorControlEventHandler elevatorEventHandler;

  @Autowired
  private VisitControlEventHandler visitEventHandler;

  @Autowired
  private ParkingControlEventHandler parkingEventHandler;

  @RequestMapping(value = "/device/control", method = RequestMethod.PUT)
  public void exeDeviceControl(@RequestBody ValueObjectAssembler pVoa) throws Exception {
    String devicecd = pVoa.get("device").getString("devicecd");
    DeviceControl deviceControl = (DeviceControl) deviceEventHandler.find(devicecd);
    DeviceControlCommand command = new DeviceControlCommand(pVoa);

    if (deviceControl == null) {
      deviceControl = new DeviceControl();
      deviceControl.create(command);
    } else {
      deviceControl.update(command);
    }

    deviceEventHandler.save(deviceControl);
  }

  @RequestMapping(value = "/device/control/{deviceCd}", method = RequestMethod.GET)
  public ResponseEntity<DeviceControl> getDeviceControl(@PathVariable String deviceCd) throws Exception {
    DeviceControl deviceControl = (DeviceControl) deviceEventHandler.find(deviceCd);
    return new ResponseEntity<>(deviceControl, HttpStatus.OK);
  }

  @RequestMapping(value = "/elevator/control", method = RequestMethod.PUT)
  public void exeElevatorControl(@RequestBody ValueObjectAssembler pVoa) throws Exception {
    String partnerHouseholdcd = pVoa.get("condition").getString("partnerhouseholdcd");
    ElevatorControl elevatorControl = (ElevatorControl) elevatorEventHandler.find(partnerHouseholdcd);

    ElevatorControlCommand command = new ElevatorControlCommand(pVoa);

    if (elevatorControl == null) {
      elevatorControl = new ElevatorControl();
      elevatorControl.create(command);
    } else {
      elevatorControl.update(command);
    }

    elevatorEventHandler.save(elevatorControl);
  }

  @RequestMapping(value = "/elevator/control/{partnerhouseholdcd}", method = RequestMethod.GET)
  public ResponseEntity<ElevatorControl> getElevatorControl(@PathVariable String partnerhouseholdcd) throws Exception {
    ElevatorControl elevatorControl = (ElevatorControl) elevatorEventHandler.find(partnerhouseholdcd);
    return new ResponseEntity<>(elevatorControl, HttpStatus.OK);
  }

  @RequestMapping(value = "/homenet/control", method = RequestMethod.PUT)
  public void exeHomenetControl(@RequestBody ValueObjectAssembler pVoa) throws Exception {
    if (pVoa.containsKey("information_visitor")) {
      visitProcess(pVoa);
    } else if (pVoa.containsKey("information_parking")) {
      parkingProcess(pVoa);
    } else {
      // Exception
    }
  }

  @RequestMapping(value = "/homenet/control/information_visitor/{partnerhouseholdcd}", method = RequestMethod.GET)
  public ResponseEntity<VisitControl> getVisitControl(@PathVariable String partnerhouseholdcd) throws Exception {
    VisitControl visitControl = (VisitControl) visitEventHandler.find(partnerhouseholdcd);
    return new ResponseEntity<>(visitControl, HttpStatus.OK);
  }

  @RequestMapping(value = "/homenet/control/information_parking/{carno}", method = RequestMethod.GET)
  public ResponseEntity<ParkingControl> getParkingControl(@PathVariable String carno) throws Exception {
    ParkingControl parkingControl = (ParkingControl) parkingEventHandler.find(carno);
    return new ResponseEntity<>(parkingControl, HttpStatus.OK);
  }

  private void visitProcess(ValueObjectAssembler pVoa) throws Exception {
    String partnerHouseholdcd = pVoa.get("information_visitor").getString("partnerhouseholdcd");
    VisitControl visitControl = (VisitControl) visitEventHandler.find(partnerHouseholdcd);

    VisitControlCommand command = new VisitControlCommand(pVoa);

    if (visitControl == null) {
      visitControl = new VisitControl();
      visitControl.create(command);
    } else {
      visitControl.update(command);

    }

    visitEventHandler.save(visitControl);
  }

  private void parkingProcess(ValueObjectAssembler pVoa) throws Exception {
    String partnerHouseholdcd = pVoa.get("information_parking").getString("partnerhouseholdcd");
    ParkingControl parkingControl = (ParkingControl) parkingEventHandler.find(partnerHouseholdcd);

    ParkingControlCommand command = new ParkingControlCommand(pVoa);

    if (parkingControl == null) {
      parkingControl = new ParkingControl();
      parkingControl.create(command);
    } else {
      parkingControl.update(command);
    }

    parkingEventHandler.save(parkingControl);

  }
}
