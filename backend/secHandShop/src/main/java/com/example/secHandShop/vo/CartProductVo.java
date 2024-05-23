package com.example.secHandShop.vo;

public class CartProductVo {

	private int cartId;

	private String name;

	private String mimeType;

	private byte[] photo;

	private int quantity;

	private int price;

	private int totalPrice;

	private int productId;

	public CartProductVo() {
		super();
	}

	public CartProductVo(int cartId, String name, String mimeType, byte[] photo, int quantity, int price, int totalPrice,
			int productId) {
		super();
		this.cartId = cartId;
		this.name = name;
		this.mimeType = mimeType;
		this.photo = photo;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.productId = productId;
	}

	public int getCartId() {
		return cartId;
	}

	public String getName() {
		return name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public int getProductId() {
		return productId;
	}

}
