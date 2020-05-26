package com.arosseto.g2glite.entities.enums;

public enum ClientType {

	Personal(1, "Personal"),
	Business(2, "Business");
	
	private int code;
	private String description;
	
	private ClientType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ClientType valueOf (Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (ClientType value : ClientType.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid client type code: " + code);
	}
}
