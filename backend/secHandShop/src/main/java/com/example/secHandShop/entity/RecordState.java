package com.example.secHandShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "record_state")
public class RecordState {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_state_id")
	private int recordStateId;

	@Column(name = "state")
	private int state;

	@Column(name = "state_time")
	private LocalDateTime stateTime;

	@Column(name = "record_id")
	private String recordId;

	public RecordState() {
		super();
	}

	public RecordState(int recordStateId, int state, LocalDateTime stateTime, String recordId) {
		super();
		this.recordStateId = recordStateId;
		this.state = state;
		this.stateTime = stateTime;
		this.recordId = recordId;
	}

	public int getRecordStateId() {
		return recordStateId;
	}

	public void setRecordStateId(int recordStateId) {
		this.recordStateId = recordStateId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public LocalDateTime getStateTime() {
		return stateTime;
	}

	public void setStateTime(LocalDateTime stateTime) {
		this.stateTime = stateTime;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

}
