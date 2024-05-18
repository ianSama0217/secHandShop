package com.example.secHandShop.vo;

import java.util.List;

import com.example.secHandShop.constants.RtnMsg;
import com.example.secHandShop.entity.Cart;

public class CartRes extends BasicRes {

	private List<Cart> cartList;

	public CartRes(RtnMsg rtnMsg) {
		super(rtnMsg);
	}

	public CartRes(RtnMsg rtnMsg, List<Cart> cartList) {
		super(rtnMsg);
		this.cartList = cartList;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

}
