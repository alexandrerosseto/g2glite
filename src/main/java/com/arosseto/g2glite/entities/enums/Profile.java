package com.arosseto.g2glite.entities.enums;

public enum Profile {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private int code;
	private String description;
	
	private Profile(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Profile valueOf (Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (Profile value : Profile.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid client type code: " + code);
	}
}
