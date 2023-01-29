package com.avensys.employeesys.dto;


public class AuthResponseDto {
	private String accessToken;
	private String tokenType = "Bearer ";
	

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
	public AuthResponseDto(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
	
}
