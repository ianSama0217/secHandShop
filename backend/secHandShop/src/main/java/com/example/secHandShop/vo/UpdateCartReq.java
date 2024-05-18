package com.example.secHandShop.vo;

public class UpdateCartReq {

	private int cartId;

	private int quantity;

	public UpdateCartReq() {
		super();
	}

	public UpdateCartReq(int cartId, int quantity) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public int getQuantity() {
		return quantity;
	}

}
