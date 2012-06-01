package com.dwinq.nintendoRegistration.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDateBean {
	public Date eventDate;
	
	public List<EventTimeBean> eventTimeBeans;
	
	public EventDateBean(Date eventDate){
		this.eventDate = eventDate;
		eventTimeBeans = new ArrayList<EventTimeBean>();
	}


	public List<EventTimeBean> getEventTimeBeans() {
		return eventTimeBeans;
	}

	public void setEventTimeBeans(List<EventTimeBean> eventTimeBeans) {
		this.eventTimeBeans = eventTimeBeans;
	}


	public Date getEventDate() {
		return eventDate;
	}


	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
}
