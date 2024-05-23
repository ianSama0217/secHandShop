package com.example.secHandShop.vo;

import java.util.List;

import com.example.secHandShop.constants.RtnMsg;

public class CartRes extends BasicRes {

	private List<CartProductVo> cartList;

	public CartRes(RtnMsg rtnMsg) {
		super(rtnMsg);
	}

	public CartRes(RtnMsg rtnMsg, List<CartProductVo> cartList) {
		super(rtnMsg);
		this.cartList = cartList;
	}

	public List<CartProductVo> getCartList() {
		return cartList;
	}

}
