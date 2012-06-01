package com.dwinq.nintendoRegistration.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="schedule_block_category")
public class ScheduleBlockCategory extends BaseEntity{
	private Integer isAccess;
	private Integer maxReservations;
	
	private ScheduleBlock scheduleBlock;
	private GuestCategory guestCategory;
	
	@Column (name="is_access")
	public Integer getIsAccess() {
		return isAccess;
	}
	public void setIsAccess(Integer isAccess) {
		this.isAccess = isAccess;
	}
	@Column (name="max_reservations")
	public Integer getMaxReservations() {
		return maxReservations;
	}
	public void setMaxReservations(Integer maxReservations) {
		this.maxReservations = maxReservations;
	}
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="schedule_block_id")
	public ScheduleBlock getScheduleBlock() {
		return scheduleBlock;
	}
	public void setScheduleBlock(ScheduleBlock scheduleBlock) {
		this.scheduleBlock = scheduleBlock;
	}
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER )
    @JoinColumn(name="category_id")
	public GuestCategory getGuestCategory() {
		return guestCategory;
	}
	public void setGuestCategory(GuestCategory guestCategory) {
		this.guestCategory = guestCategory;
	}
	
	
}
