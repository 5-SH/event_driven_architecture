package cqrs.control.command;

import cqrs.valueobject.ValueObject;
import cqrs.valueobject.ValueObjectAssembler;

public class ParkingControlCommand {
	private String partnerid;
	private String partnerHouseholdcd;
	private String carno;
	private String location;
	
	public ParkingControlCommand(ValueObjectAssembler cmdVoa) {
		ValueObject cmdVo = cmdVoa.get("information_parking");
		
		this.partnerid = cmdVo.getString("partnerid");
		this.partnerHouseholdcd = cmdVo.getString("partnerhouseholdcd");
		this.location = cmdVo.getString("location");
		this.carno = cmdVo.getString("car_no");
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

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ParkingControlCommand [partnerid=" + partnerid + ", partnerHouseholdcd=" + partnerHouseholdcd
				+ ", carno=" + carno + ", location=" + location + "]";
	}
}
