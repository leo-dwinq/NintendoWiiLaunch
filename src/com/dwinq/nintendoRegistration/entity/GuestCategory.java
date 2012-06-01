package com.dwinq.nintendoRegistration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="guest_category")
public class GuestCategory extends BaseEntity{
	private String categoryCode;
	private String description;
	private Integer maxInfluencerGuests;
	
	@Column (name="category_code")
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	@Column (name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column (name="max_influencer_guests")
	public Integer getMaxInfluencerGuests() {
		return maxInfluencerGuests;
	}
	public void setMaxInfluencerGuests(Integer maxInfluencerGuests) {
		this.maxInfluencerGuests = maxInfluencerGuests;
	}
	
	
}
