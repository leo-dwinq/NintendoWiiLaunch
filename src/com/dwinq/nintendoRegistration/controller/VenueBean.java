package com.dwinq.nintendoRegistration.controller;

import java.util.List;

import com.dwinq.nintendoRegistration.entity.Venue;

public class VenueBean {
	private Long id;
	private String city;
	private String state;
	
	private List<EventDateBean> eventDateBeans;
	
	public VenueBean(Venue venue, List<EventDateBean> eventDateBeans){
		this.id = venue.getId();
		this.city = venue.getCity();
		this.setState(venue.getState());
		this.eventDateBeans = eventDateBeans;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<EventDateBean> getEventDateBeans() {
		return eventDateBeans;
	}

	public void setEventDateBeans(List<EventDateBean> eventDateBeans) {
		this.eventDateBeans = eventDateBeans;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
