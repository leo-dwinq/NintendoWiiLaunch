package com.dwinq.nintendoRegistration.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table (name="venue")
public class Venue extends BaseEntity {
	private String address;
	private String city;
	private String state;
	private String postalCode;
	
	private List<ScheduleBlock> scheduleBlocks;

	@Column
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

	@Column (name="state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column (name="postal_code")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setScheduleBlocks(List<ScheduleBlock> scheduleBlocks) {
		this.scheduleBlocks = scheduleBlocks;
	}
  
	@OneToMany(targetEntity=ScheduleBlock.class, mappedBy="venue", fetch=FetchType.LAZY)
	//@Fetch(value = FetchMode.SUBSELECT)
	public List<ScheduleBlock> getScheduleBlocks() {
		return scheduleBlocks;
	}
	
	
}
