package com.dwinq.nintendoRegistration.service;

import java.util.List;

import com.dwinq.nintendoRegistration.controller.GuestBean;
import com.dwinq.nintendoRegistration.entity.AgeRange;
import com.dwinq.nintendoRegistration.entity.Guest;
import com.dwinq.nintendoRegistration.entity.ScheduleBlock;

public interface RegistrationService {

	public List<AgeRange> getAllAgeRanges();

	public Guest getGuestForAccessCode(String accessCode);

	public List<ScheduleBlock> getScheduleBlocksForVenue(Long venueId);

	public Guest saveGuestBean(GuestBean guestBean);

	public ScheduleBlock getScheduleBlocksForId(Long scheduleBlockId);

	public Guest getGuestForId(Long guestId);

}
