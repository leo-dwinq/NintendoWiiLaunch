package com.dwinq.nintendoRegistration.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.dwinq.nintendoRegistration.entity.Guest;

public class GuestBean {
	private Long guestId;
	private Long categoryId;
	private Long scheduleBlockId;
	private Long venueId;
	private Long influencerId;
	private String accessCode;
	private String categoryCode;
	private String firstName;
	private String lastName;
	private String email;
	private Long ageRangeId;
	private String gender;
	private Integer isDeclined;
	private Long locationId;
	private Integer chaperoningCount;
	private Integer isChaperoningUnder13;
	private Integer chaperoningCountUnder13;
	private String dateStr;
	private Integer startTime;
	private Integer isFutureCommunication;

	private String guestAccessCodes;
	
	private VenueBean venueBean;

	public GuestBean(){

	}
	public GuestBean(Guest guest, VenueBean venueBean){
		this.guestId = guest.getId();
		this.influencerId = guest.getInfluencerId();
		this.accessCode = guest.getAccessCode();
		this.firstName = guest.getFirstName();
		this.lastName = guest.getLastName();
		this.email = guest.getEmail();
		this.venueBean = venueBean;
		this.categoryId = guest.getGuestCategory().getId();
		this.categoryCode = guest.getGuestCategory().getCategoryCode();
		this.chaperoningCountUnder13 = guest.getChaperoningCountUnder13();
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getIsDeclined() {
		return isDeclined;
	}
	public void setIsDeclined(Integer isDecline) {
		this.isDeclined = isDecline;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getAgeRangeId() {
		return ageRangeId;
	}
	public void setAgeRangeId(Long ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Integer getChaperoningCount() {
		return chaperoningCount;
	}
	public void setChaperoningCount(Integer chaperoningCount) {
		this.chaperoningCount = chaperoningCount;
	}
	public Integer getIsChaperoningUnder13() {
		return isChaperoningUnder13;
	}
	public void setIsChaperoningUnder13(Integer isChaperoningUnder13) {
		this.isChaperoningUnder13 = isChaperoningUnder13;
	}

	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public Integer getStartTime() {
		return startTime;
	}
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}
	public Integer getIsFutureCommunication() {
		return isFutureCommunication;
	}
	public void setIsFutureCommunication(Integer isFutureCommunication) {
		this.isFutureCommunication = isFutureCommunication;
	}
	
	
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public VenueBean getVenueBean() {
		return venueBean;
	}

	public void setVenueBean(VenueBean venueBean) {
		this.venueBean = venueBean;
	}

	public Long getGuestId() {
		return guestId;
	}
	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}
	
	
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public Long getVenueId() {
		return venueId;
	}
	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}
	public Long getScheduleBlockId() {
		return scheduleBlockId;
	}
	public void setScheduleBlockId(Long scheduleBlockId) {
		this.scheduleBlockId = scheduleBlockId;
	}
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(super.toString() + " : ");

		
		for(Method method : this.getClass().getDeclaredMethods()){
			try {
				String getterMethodName = method.getName();
				if(getterMethodName.startsWith("get")){
					stringBuffer.append(getterMethodName + "() = " + method.invoke(this, null) + "\n");
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return stringBuffer.toString();
	}
	public void setGuestAccessCodes(String guestAccessCodes) {
		this.guestAccessCodes = guestAccessCodes;
	}
	public String getGuestAccessCodes() {
		return guestAccessCodes;
	}
	public Long getInfluencerId() {
		return influencerId;
	}
	public void setInfluencerId(Long influencerId) {
		this.influencerId = influencerId;
	}

	public void setChaperoningCountUnder13(Integer chaperoningCountUnder13) {
		this.chaperoningCountUnder13 = chaperoningCountUnder13;
	}
	public Integer getChaperoningCountUnder13() {
		return chaperoningCountUnder13;
	}
	
	
	
}
