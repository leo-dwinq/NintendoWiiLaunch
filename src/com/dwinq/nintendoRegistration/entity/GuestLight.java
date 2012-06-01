package com.dwinq.nintendoRegistration.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="guest")
public class GuestLight extends BaseEntity{

	private ScheduleBlock scheduleBlock;
	
	public void setScheduleBlock(ScheduleBlock scheduleBlock) {
		this.scheduleBlock = scheduleBlock;
	}
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="schedule_block_id")
	public ScheduleBlock getScheduleBlock() {
		return scheduleBlock;
	}
}
