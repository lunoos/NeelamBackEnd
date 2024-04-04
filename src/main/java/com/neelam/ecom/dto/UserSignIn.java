package com.neelam.ecom.dto;

public class UserSignIn {
	private String userId;
	private String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserSignIn [userId=" + userId + ", password=" + password + "]";
	}
	
}
