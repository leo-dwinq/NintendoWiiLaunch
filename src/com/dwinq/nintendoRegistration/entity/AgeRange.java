package com.dwinq.nintendoRegistration.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name="age_range")
public class AgeRange extends BaseEntity {
	private Integer ageMin;
	private Integer ageMax;
	private Integer isRegisterAllowed;
	private Integer isChaperonRequired;
	private String range;
	
	
    private List<Guest> guests;


	@Column (name = "age_min")
	public Integer getAgeMin() {
		return ageMin;
	}

	public void setAgeMin(Integer ageMin) {
		this.ageMin = ageMin;
	}

	@Column (name = "age_max")
	public Integer getAgeMax() {
		return ageMax;
	}

	public void setAgeMax(Integer ageMax) {
		this.ageMax = ageMax;
	}

	@Column (name = "is_register_allowed")
	public Integer getIsRegisterAllowed() {
		return isRegisterAllowed;
	}

	public void setIsRegisterAllowed(Integer isRegisterAllowed) {
		this.isRegisterAllowed = isRegisterAllowed;
	}

	@Column (name = "is_chaperon_required")
	public Integer getIsChaperonRequired() {
		return isChaperonRequired;
	}

	public void setIsChaperonRequired(Integer isChaperonRequired) {
		this.isChaperonRequired = isChaperonRequired;
	}

	@Column (name = "range")
	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

	@OneToMany(targetEntity=Guest.class, mappedBy="ageRange", fetch=FetchType.LAZY)
	public List<Guest> getGuests() {
		return guests;
	}
}
