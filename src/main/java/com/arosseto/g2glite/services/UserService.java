package com.arosseto.g2glite.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.arosseto.g2glite.security.UserAuth;

public class UserService {
	
	public static UserAuth authenticated() {
		try {
			return (UserAuth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
