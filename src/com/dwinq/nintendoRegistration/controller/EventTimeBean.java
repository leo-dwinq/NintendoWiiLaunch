package com.dwinq.nintendoRegistration.controller;

public class EventTimeBean {
	private Long blockId;
	private Integer startTime;
	private Integer endTime;
	
	public EventTimeBean(Long blockId, Integer startTime, Integer endTime){
		this.blockId = blockId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Long getBlockId() {
		return blockId;
	}
	

}
