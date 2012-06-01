package com.dwinq.nintendoRegistration.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dwinq.nintendoRegistration.controller.GuestBean;
@Entity
@Table (name="guest")
public class Guest extends BaseEntity{
    private Long influencerId;
	private String accessCode;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	//private Long categoryId;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	private Integer chaperoningCountUnder13;
	private Integer isDeclined;
	private Integer isFutureCommunication;
	
	private String[] guestAccessCodes;
	
	public Guest(){
		
	}
	public Guest(GuestBean guestBean){
		this.id = guestBean.getGuestId();
		this.influencerId = guestBean.getInfluencerId();
		this.accessCode = guestBean.getAccessCode();
		this.firstName = guestBean.getFirstName();
		this.lastName = guestBean.getLastName();
		this.gender = guestBean.getGender();
		this.isDeclined = guestBean.getIsDeclined();
		this.isFutureCommunication = guestBean.getIsFutureCommunication();
		this.email = guestBean.getEmail();
		this.chaperoningCountUnder13 = guestBean.getChaperoningCountUnder13();
		
//		GuestCategory guestCategory = new GuestCategory();
//		guestCategory.setId(guestBean.getCategoryId());
//		this.setGuestCategory(guestCategory);
	}

	private ScheduleBlock scheduleBlock;
	private AgeRange ageRange;
	private Venue venue;
	private GuestCategory guestCategory;
    private List<Guest> guests = new ArrayList<Guest>();
	
	@Column(name="access_code")
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	@Column(name="category_id")
//	public Long getCategoryId() {
//		return categoryId;
//	}
//	public void setCategoryId(Long categoryId) {
//		this.categoryId = categoryId;
//	}
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
	@Column
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="postal_code")
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setScheduleBlock(ScheduleBlock scheduleBlock) {
		this.scheduleBlock = scheduleBlock;
	}
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER )
    @JoinColumn(name="schedule_block_id")
	public ScheduleBlock getScheduleBlock() {
		return scheduleBlock;
	}

	public void setAgeRange(AgeRange ageRange) {
		this.ageRange = ageRange;
	}
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="age_range_id")
	public AgeRange getAgeRange() {
		return ageRange;
	}
    
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER )
    @JoinColumn(name="venue_id")
	public Venue getVenue() {
		return venue;
	}
	
//    public void setInfluencer(Guest influencer) {
//		this.influencer = influencer;
//	}
//	@ManyToOne
//    @JoinColumn(name="influencer_id")
//	public Guest getInfluencer() {
//		return influencer;
//	}
//
//    @OneToMany(mappedBy="influencer")
//    public List<Guest> getGuests(){
//    	return guests;
//    }
	public void setInfluencerId(Long influencerId) {
		this.influencerId = influencerId;
	}
	@Column(name="influencer_id")
	public Long getInfluencerId() {
		return influencerId;
	}
	public void setGuestCategory(GuestCategory guestCategory) {
		this.guestCategory = guestCategory;
	}
    @ManyToOne(fetch=FetchType.EAGER )
    @JoinColumn(name="category_id")
	public GuestCategory getGuestCategory() {
		return guestCategory;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setIsDeclined(Integer isDeclined) {
		this.isDeclined = isDeclined;
	}
	@Column(name="is_declined")
	public Integer getIsDeclined() {
		return isDeclined;
	}
	public void setIsFutureCommunication(Integer isFutureCommunication) {
		this.isFutureCommunication = isFutureCommunication;
	}
	@Column(name="is_future_communication")
	public Integer getIsFutureCommunication() {
		return isFutureCommunication;
	}
	
	public void setGuestAccessCodes(String[] guestAccessCodes) {
		this.guestAccessCodes = guestAccessCodes;
	}
	@Transient
	public String[] getGuestAccessCodes() {
		return guestAccessCodes;
	}
	public void setChaperoningCountUnder13(Integer chaperoningCountUnder13) {
		this.chaperoningCountUnder13 = chaperoningCountUnder13;
	}
	@Column(name="chaperoning_count_under_13")
	public Integer getChaperoningCountUnder13() {
		return chaperoningCountUnder13;
	}
}
