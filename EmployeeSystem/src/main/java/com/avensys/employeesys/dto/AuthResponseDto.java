package com.avensys.employeesys.dto;

import com.avensys.employeesys.model.Employee;

public class AuthResponseDto {
	private String accessToken;
	private String tokenType = "Bearer ";
	private String message;
    private Employee employee;


	public AuthResponseDto(String message, Employee employee) {
		super();
		this.message = message;
		this.employee = employee;
	}

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public AuthResponseDto(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public AuthResponseDto(String accessToken, String tokenType, String message, Employee employee) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.message = message;
		this.employee = employee;
	}
	
	
}
