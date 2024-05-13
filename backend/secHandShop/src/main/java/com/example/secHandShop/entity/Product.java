package com.example.secHandShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private int type;

	@Column(name = "photo")
	private byte[] photo;

	@Column(name = "price")
	private int price;

	@Column(name = "inventory")
	private int inventory;

	@Column(name = "sell_count")
	private int sellCount;

	@Column(name = "update_time")
	private LocalDateTime updateTime;

	@Column(name = "state")
	private int state;

	@Column(name = "seller_id")
	private int sellerId;

	public Product() {
		super();
	}

	public Product(int productId, String name, int type, byte[] photo, int price, int inventory, int sellCount,
			LocalDateTime updateTime, int state, int sellerId) {
		super();
		this.productId = productId;
		this.name = name;
		this.type = type;
		this.photo = photo;
		this.price = price;
		this.inventory = inventory;
		this.sellCount = sellCount;
		this.updateTime = updateTime;
		this.state = state;
		this.sellerId = sellerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

}
