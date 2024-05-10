package com.example.secHandShop.constants;

import java.lang.String;

public enum RtnMsg {

	SUCCESSFUL(200, "Successful!"), //
	PARAM_ERROR(400, "Param error!"), //
	// 註冊
	EMAIL_IS_EXISTED(400, "Email is existed!"), //
	EMAIL_FORMAT_ERROR(400, "Email format error!"), //
	PASSWORD_FORMAT_ERROR(400, "Password format error!"), //
	PHONE_FORMAT_ERROR(400, "Phone format error!"), //
	REGISTER_ERROR(500, "Register error!"), //
	// 登入
	ACCOUNT_NOT_FOUND(400, "Account not found!"), //
	PASSWORD_ERROR(400, "Password error!"), //
	LOGIN_ERROR(500, "Login error!"), //
	;

	private int stateCode;

	private String message;

	private RtnMsg(int stateCode, String message) {
		this.stateCode = stateCode;
		this.message = message;
	}

	public int getStateCode() {
		return stateCode;
	}

	public String getMessage() {
		return message;
	}

}
