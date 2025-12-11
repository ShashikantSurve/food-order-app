package com.surve_Food.response;

import com.surve_Food.Model.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {

	private String jwt;

	private String message;

	private USER_ROLE roles;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public USER_ROLE getRoles() {
		return roles;
	}

	public void setRoles(USER_ROLE roles) {
		this.roles = roles;
	}

}
