package com.example.secHandShop.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginReq {

	private String email;

	@JsonProperty("password")
	private String pwd;

	public LoginReq() {
		super();
	}

	public LoginReq(String email, String pwd) {
		super();
		this.email = email;
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public String getPwd() {
		return pwd;
	}

}
