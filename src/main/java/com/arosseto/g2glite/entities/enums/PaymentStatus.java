package com.arosseto.g2glite.entities.enums;

public enum PaymentStatus {
	
	PENDING(1, "Pending"),
	SETTLED(2, "Settled"),
	CANCELED(3, "Canceled");
	
	
	private int code;
	private String description;
	
	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus valueOf (Integer code) {
		
		if (code == null) {
			return null;
		}
		
		for (PaymentStatus value : PaymentStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		
		throw new IllegalArgumentException("Invalid client type code: " + code);
	}
}
