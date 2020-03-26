package cqrs.control.command;

import cqrs.valueobject.ValueObject;
import cqrs.valueobject.ValueObjectAssembler;

public class ElevatorControlCommand {
	private ValueObject service;
	private ValueObject condition;
	private ValueObject auth;
	
	public ElevatorControlCommand(ValueObjectAssembler cmdVoa) {
		this.service = cmdVoa.get("service");
		this.condition = cmdVoa.get("condition");
		this.auth = cmdVoa.get("auth");
	}
	
	private boolean parameterValidation(ValueObjectAssembler pVoa, String pStr) {
		return pVoa.containsKey(pStr);
	}

	public ValueObject getService() {
		return service;
	}

	public void setService(ValueObject service) {
		this.service = service;
	}

	public ValueObject getCondition() {
		return condition;
	}

	public void setCondition(ValueObject condition) {
		this.condition = condition;
	}

	public ValueObject getAuth() {
		return auth;
	}

	public void setAuth(ValueObject auth) {
		this.auth = auth;
	}
	
	@Override
	public String toString() {
		return "elevatorControlCommand [service=" + service + ", condition=" + condition + ", auth=" + auth + "]";
	}
}
