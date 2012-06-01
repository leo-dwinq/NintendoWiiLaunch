package com.dwinq.nintendoRegistration.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name="schedule_block")
public class ScheduleBlock extends BaseEntity {
	private Integer dayOfWeek;
	private Date blockDate;
	private Integer startTime;
	private Integer endTime;
	
    private List<GuestLight> guests;
    private Venue venue;
    private List<ScheduleBlockCategory> scheduleBlockCategories;

	@Column(name="day_of_week")
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Column(name="block_date")
	public Date getBlockDate() {
		return blockDate;
	}

	public void setBlockDate(Date blockDate) {
		this.blockDate = blockDate;
	}

	@Column(name="start_time")
	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	@Column(name="end_time")
	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public void setGuests(List<GuestLight> guests) {
		this.guests = guests;
	}

	@OneToMany(targetEntity=GuestLight.class, mappedBy="scheduleBlock", fetch=FetchType.EAGER)
	public List<GuestLight> getGuests() {
		return guests;
	}

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="venue_id")
	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public void setScheduleBlockCategories(List<ScheduleBlockCategory> scheduleBlockCategories) {
		this.scheduleBlockCategories = scheduleBlockCategories;
	}

	@OneToMany(targetEntity=ScheduleBlockCategory.class, mappedBy="scheduleBlock")
	public List<ScheduleBlockCategory> getScheduleBlockCategories() {
		return scheduleBlockCategories;
	}
	
	
}
