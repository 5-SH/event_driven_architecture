package cqrs.control.command;

import cqrs.valueobject.ValueObject;
import cqrs.valueobject.ValueObjectAssembler;

public class VisitControlCommand {
	private String partnerid;
	private String partnerHouseholdcd;
	private String indexno;
	private String location;
	
	public VisitControlCommand(ValueObjectAssembler cmdVoa) {
		ValueObject cmdVo = cmdVoa.get("information_visitor");
		
		this.partnerid = cmdVo.getString("partnerid");
		this.partnerHouseholdcd = cmdVo.getString("partnerhouseholdcd");
		this.indexno = cmdVo.getString("index_no");
		this.location = cmdVo.getString("location");
	}
	
	private boolean parameterValidation(ValueObjectAssembler pVoa, String pStr) {
		return pVoa.containsKey(pStr);
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

	@Override
	public String toString() {
		return "VisitControlCommand [partnerid=" + partnerid + ", partnerHouseholdcd=" + partnerHouseholdcd
				+ ", indexno=" + indexno + ", location=" + location + "]";
	}
}
