package com.dwinq.nintendoRegistration.dal;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dwinq.nintendoRegistration.entity.AgeRange;
import com.dwinq.nintendoRegistration.entity.Guest;
import com.dwinq.nintendoRegistration.entity.GuestCategory;
import com.dwinq.nintendoRegistration.entity.ScheduleBlock;
import com.dwinq.nintendoRegistration.entity.ScheduleBlockCategory;
import com.dwinq.nintendoRegistration.entity.Venue;

//@Repository("registrationDao")
public class RegistrationDaoImpl implements RegistrationDao {
	private static org.apache.log4j.Logger log = Logger.getLogger(RegistrationDaoImpl.class); 

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public HibernateTemplate getHibernateTemplate(){
		return hibernateTemplate;
	}

	@Override
	public List<Guest> findGuestRegistrationForBlock(Long scheduleBlockId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Guest.class).add( Property.forName("scheduleBlockId").eq(scheduleBlockId) );

		List<Guest> guestList = getHibernateTemplate().findByCriteria(criteria);
		return guestList;
	}

	@Override
	public List<ScheduleBlock> findScheduleBlocksForVenue(Long venueId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ScheduleBlock.class).add( Property.forName("venue.id").eq(venueId) );//.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);

		List<ScheduleBlock> scheduleBlocks = getHibernateTemplate().findByCriteria(criteria);

		for(ScheduleBlock scheduleBlock : scheduleBlocks){
			criteria = DetachedCriteria.forClass(ScheduleBlockCategory.class).add( Property.forName("scheduleBlock.id").eq(scheduleBlock.getId()) );
			List<ScheduleBlockCategory> blockCategories = getHibernateTemplate().findByCriteria(criteria);
			scheduleBlock.setScheduleBlockCategories(blockCategories);
		}

		return scheduleBlocks;

		//		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(ScheduleBlock.class);
		//		//criteria.add(Expression.eq("venue.id", venueId));
		//		criteria.addOrder(Order.asc("blockDate"));
		//		
		//		return criteria.list();
	}

	@Override
	public Guest findGuestForAccessCode(String accessCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Guest.class).add( Property.forName("accessCode").eq(accessCode) );

		List<Guest> guestList = getHibernateTemplate().findByCriteria(criteria);

		return guestList.get(0);
	}

	@Override
	public Guest findGuestForId(Long guestId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Guest.class).add( Property.forName("id").eq(guestId) );

		List<Guest> guestList = getHibernateTemplate().findByCriteria(criteria);

		return guestList.get(0);
	}

	@Override
	public Guest findGuestWithMappingsForAccessCode(String accessCode) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Guest.class).add( Property.forName("accessCode").eq(accessCode) );
		criteria.setFetchMode ("Venue", FetchMode.EAGER);
		criteria.setFetchMode ("ScheduleBlock", FetchMode.EAGER);

		List<Guest> guestList = getHibernateTemplate().findByCriteria(criteria);
		if(guestList.size() == 1){
			return guestList.get(0);
		}
		return null;
	}

	@Override
	public List<Guest> findGuestsForInfluencerId(Long influencerId){
		DetachedCriteria criteria = DetachedCriteria.forClass(Guest.class).add( Property.forName("influencerId").eq(influencerId) );

		List<Guest> guestList = getHibernateTemplate().findByCriteria(criteria);

		return guestList;
	}

	@Override
	public void save(Guest guest){
		Transaction tx = null;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		try{
			tx = session.beginTransaction();
			session.saveOrUpdate(guest);

			List<Guest> influencerGuestList = findGuestsForInfluencerId(guest.getId());
			List<String> accessCodes = new ArrayList<String>();
			if(guest.getInfluencerId() == null || guest.getInfluencerId() == 0){
				if(influencerGuestList.size() == 0){
					for(int i = 0;i < (guest.getGuestCategory().getMaxInfluencerGuests() - guest.getChaperoningCountUnder13());i++){
						Guest influencerGuest = new Guest();
						influencerGuest.setAccessCode(guest.getAccessCode() + "0" + i);
						influencerGuest.setInfluencerId(guest.getId());
						influencerGuest.setScheduleBlock(guest.getScheduleBlock());
						influencerGuest.setGuestCategory(guest.getGuestCategory());
						influencerGuest.setVenue(guest.getVenue());
						session.saveOrUpdate(influencerGuest);
						accessCodes.add(influencerGuest.getAccessCode());
					}
				}
				else{
					for(Guest influencerGuest : influencerGuestList){
						accessCodes.add(influencerGuest.getAccessCode());
					}
				}
			}
			guest.setGuestAccessCodes(accessCodes.toArray(new String[]{}));
			tx.commit();
		}catch (RuntimeException e) {
			if (tx != null && tx.isActive()) {
				try {
					// Second try catch as the rollback could fail as well
					tx.rollback();
				} catch (HibernateException e1) {
					//logger.debug(ÒError rolling back transactionÓ);
				}
				// throw again the first exception
				throw e;
			}
		}
	}



	@Override
	public List<ScheduleBlock> findAllScheduleBlocks() {
		DetachedCriteria criteria = DetachedCriteria.forClass(ScheduleBlock.class);

		List<ScheduleBlock> scheduleBlocks = getHibernateTemplate().findByCriteria(criteria);

		return scheduleBlocks;
	}

	@Override
	public List<AgeRange> findAllAgeRanges() {
		DetachedCriteria criteria = DetachedCriteria.forClass(AgeRange.class);

		List<AgeRange> ageRanges = getHibernateTemplate().findByCriteria(criteria);

		return ageRanges;
	}

	@Override
	public GuestCategory findGuestCategoryForId(Long categoryId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GuestCategory.class).add( Property.forName("id").eq(categoryId) );
		List<GuestCategory> guestCategoryList = getHibernateTemplate().findByCriteria(criteria);

		return guestCategoryList.get(0);
	}

	@Override
	public ScheduleBlock findScheduleBlockForId(Long scheduleBlockId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ScheduleBlock.class).add( Property.forName("id").eq(scheduleBlockId) );
		List<ScheduleBlock> scheduleBlockList = getHibernateTemplate().findByCriteria(criteria);

		for(ScheduleBlock scheduleBlock : scheduleBlockList){
			criteria = DetachedCriteria.forClass(ScheduleBlockCategory.class).add( Property.forName("scheduleBlock.id").eq(scheduleBlock.getId()) );
			List<ScheduleBlockCategory> blockCategories = getHibernateTemplate().findByCriteria(criteria);
			scheduleBlock.setScheduleBlockCategories(blockCategories);
		}

		return scheduleBlockList.get(0);
	}
	@Override
	public AgeRange findAgeRangeForId(Long ageRangeId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(AgeRange.class).add( Property.forName("id").eq(ageRangeId) );
		List<AgeRange> ageRangeList = getHibernateTemplate().findByCriteria(criteria);

		return ageRangeList.get(0);
	}
	@Override
	public Venue findVenueForId(Long venueId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Venue.class).add( Property.forName("id").eq(venueId) );
		List<Venue> venueList = getHibernateTemplate().findByCriteria(criteria);

		return venueList.get(0);
	}
}
