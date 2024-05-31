package com.example.secHandShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "record_item")
public class RecordItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_item_id")
	private int recordItemId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private int price;

	@Column(name = "record_id")
	private int recordId;

	@Column(name = "product_id")
	private int productId;

	public RecordItem() {
		super();
	}

	public RecordItem(int recordItemId, int quantity, int price, int recordId, int productId) {
		super();
		this.recordItemId = recordItemId;
		this.quantity = quantity;
		this.price = price;
		this.recordId = recordId;
		this.productId = productId;
	}

	public int getRecordItemId() {
		return recordItemId;
	}

	public void setRecordItemId(int recordItemId) {
		this.recordItemId = recordItemId;
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

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
