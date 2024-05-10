package com.example.secHandShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "record")
public class Record {

	@Id
	@Column(name = "record_id")
	private String recordId;

	@Column(name = "buyer_id")
	private int buyerId;

	public Record() {
		super();
	}

	public Record(String recordId, int buyerId) {
		super();
		this.recordId = recordId;
		this.buyerId = buyerId;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
}
