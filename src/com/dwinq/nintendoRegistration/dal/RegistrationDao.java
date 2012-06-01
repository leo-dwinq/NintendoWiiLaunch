package com.dwinq.nintendoRegistration.dal;

import java.util.List;

import com.dwinq.nintendoRegistration.entity.AgeRange;
import com.dwinq.nintendoRegistration.entity.Guest;
import com.dwinq.nintendoRegistration.entity.GuestCategory;
import com.dwinq.nintendoRegistration.entity.ScheduleBlock;
import com.dwinq.nintendoRegistration.entity.Venue;

public interface RegistrationDao {

	public List<Guest> findGuestRegistrationForBlock(Long scheduleBlockId);

	public Guest findGuestForAccessCode(String accessCode);

	public void save(Guest guest);

	public List<ScheduleBlock> findAllScheduleBlocks();

	public List<AgeRange> findAllAgeRanges();

	public Guest findGuestWithMappingsForAccessCode(String accessCode);

	public List<ScheduleBlock> findScheduleBlocksForVenue(Long venueId);

	public GuestCategory findGuestCategoryForId(Long categoryId);

	public ScheduleBlock findScheduleBlockForId(Long scheduleBlockId);

	public AgeRange findAgeRangeForId(Long ageRangeId);

	public Venue findVenueForId(Long venueId);

	public Guest findGuestForId(Long guestId);

	public List<Guest> findGuestsForInfluencerId(Long influencerId);

}
