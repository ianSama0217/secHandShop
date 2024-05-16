package com.example.secHandShop.vo;

import com.example.secHandShop.constants.RtnMsg;
import com.example.secHandShop.entity.User;

public class UserRes extends BasicRes {

	private User user;

	public UserRes(RtnMsg rtnMsg) {
		super(rtnMsg);
	}

	public UserRes(RtnMsg rtnMsg, User user) {
		super(rtnMsg);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
