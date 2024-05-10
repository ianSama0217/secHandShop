package com.example.secHandShop.vo;

import com.example.secHandShop.constants.RtnMsg;

public class BasicRes {

	public RtnMsg rtnMsg;

	public BasicRes(RtnMsg rtnMsg) {
		super();
		this.rtnMsg = rtnMsg;
	}

	public RtnMsg getRtnMsg() {
		return rtnMsg;
	}

}
