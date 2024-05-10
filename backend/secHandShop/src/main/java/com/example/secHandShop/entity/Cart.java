package com.example.secHandShop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private int price;

	@Column(name = "add_time")
	private LocalDateTime addTime;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "product_id")
	private int productId;

	public Cart() {
		super();
	}

	public Cart(int cartId, int quantity, int price, LocalDateTime addTime, int userId, int productId) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		this.price = price;
		this.addTime = addTime;
		this.userId = userId;
		this.productId = productId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDateTime getAddTime() {
		return addTime;
	}

	public void setAddTime(LocalDateTime addTime) {
		this.addTime = addTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
