package com.dwinq.nintendoRegistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwinq.nintendoRegistration.controller.GuestBean;
import com.dwinq.nintendoRegistration.dal.RegistrationDao;
import com.dwinq.nintendoRegistration.entity.AgeRange;
import com.dwinq.nintendoRegistration.entity.Guest;
import com.dwinq.nintendoRegistration.entity.GuestCategory;
import com.dwinq.nintendoRegistration.entity.ScheduleBlock;
import com.dwinq.nintendoRegistration.entity.Venue;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private RegistrationDao registrationDao;

	@Override
	public List<AgeRange> getAllAgeRanges(){
		return registrationDao.findAllAgeRanges();
	}
	@Override
	public Guest getGuestForAccessCode(String accessCode){
		return registrationDao.findGuestWithMappingsForAccessCode(accessCode);
	}

	@Override
	public Guest getGuestForId(Long guestId){
		return registrationDao.findGuestForId(guestId);
	}

	public RegistrationDao getRegistrationDao() {
		return registrationDao;
	}

	public void setRegistrationDao(RegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}

	@Override
	public List<ScheduleBlock> getScheduleBlocksForVenue(Long venueId){
		return registrationDao.findScheduleBlocksForVenue(venueId);
		//return registrationDao.findAllScheduleBlocks();
	} 

	@Override
	public ScheduleBlock getScheduleBlocksForId(Long scheduleBlockId){
		return registrationDao.findScheduleBlockForId(scheduleBlockId);
	} 

	@Override
	public Guest saveGuestBean(GuestBean guestBean){
		Guest guest = new Guest(guestBean);
		GuestCategory guestCategory = registrationDao.findGuestCategoryForId(guestBean.getCategoryId());
		guest.setGuestCategory(guestCategory);
		if(guestBean.getScheduleBlockId() != null && guestBean.getScheduleBlockId() > 0){
			ScheduleBlock scheduleBlock = registrationDao.findScheduleBlockForId(guestBean.getScheduleBlockId());
			guest.setScheduleBlock(scheduleBlock);
		}
		if(guestBean.getAgeRangeId() != null && guestBean.getAgeRangeId() > 0){
			AgeRange ageRange = registrationDao.findAgeRangeForId(guestBean.getAgeRangeId());
			guest.setAgeRange(ageRange);
		}
		Venue venue = registrationDao.findVenueForId(guestBean.getVenueId());
		guest.setVenue(venue);

		registrationDao.save(guest);

		StringBuffer guestAccessCodeStr = new StringBuffer();
		if(guest.getGuestAccessCodes() != null && guest.getGuestAccessCodes().length > 0){
			for(String guestAccessCode : guest.getGuestAccessCodes()){
				if(guestAccessCodeStr.length() > 0){
					guestAccessCodeStr.append(", ");
				}
				guestAccessCodeStr.append(guestAccessCode);
			}
			guestBean.setGuestAccessCodes(guestAccessCodeStr.toString());
		}

		return guest;
	}
}
