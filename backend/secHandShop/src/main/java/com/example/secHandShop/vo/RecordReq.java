package com.example.secHandShop.vo;

import java.util.List;

import com.example.secHandShop.entity.Record;
import com.example.secHandShop.entity.RecordItem;
import com.example.secHandShop.entity.RecordState;

public class RecordReq {

	private Record record;

	private List<RecordItem> itemList;

	private RecordState state;

	public RecordReq() {
		super();
	}

	public RecordReq(Record record, List<RecordItem> itemList, RecordState state) {
		super();
		this.record = record;
		this.itemList = itemList;
		this.state = state;
	}

	public Record getRecord() {
		return record;
	}

	public List<RecordItem> getItemList() {
		return itemList;
	}

	public RecordState getState() {
		return state;
	}

}
