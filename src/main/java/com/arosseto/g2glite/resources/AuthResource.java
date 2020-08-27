package com.arosseto.g2glite.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arosseto.g2glite.dto.EmailDTO;
import com.arosseto.g2glite.entities.Category;
import com.arosseto.g2glite.security.JWTUtil;
import com.arosseto.g2glite.security.UserAuth;
import com.arosseto.g2glite.services.AuthService;
import com.arosseto.g2glite.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/auth")
@Api(value="/auth",  tags="auth")
public class AuthResource {
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;

	@ApiOperation(value = "Generate a new token for the current user", response = UserAuth.class)
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserAuth user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Password Reset", response = Category.class)
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO) {
		authService.sendNewPassword(objDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
}
