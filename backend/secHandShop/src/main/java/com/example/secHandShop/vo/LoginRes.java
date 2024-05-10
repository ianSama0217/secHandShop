package com.example.secHandShop.vo;

import com.example.secHandShop.constants.RtnMsg;
import com.example.secHandShop.entity.User;

public class LoginRes extends BasicRes {

	private User user;

	public LoginRes(RtnMsg rtnMsg) {
		super(rtnMsg);
	}

	public LoginRes(RtnMsg rtnMsg, User user) {
		super(rtnMsg);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
