package com.dwinq.nintendoRegistration.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dwinq.nintendoRegistration.entity.AgeRange;
import com.dwinq.nintendoRegistration.entity.Guest;
import com.dwinq.nintendoRegistration.entity.ScheduleBlock;
import com.dwinq.nintendoRegistration.entity.ScheduleBlockCategory;
import com.dwinq.nintendoRegistration.service.RegistrationService;


@Controller
//@RequestMapping("/pre-registration")
public class PreRegistrationFormController { 

	@Autowired
	private RegistrationService registrationService;

	//	@RequestMapping(value="/registration/")
	//    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
	//		System.out.println(id);
	//
	//		ModelAndView m =  new ModelAndView("/registration/");
	//		return m;
	//    }
	@RequestMapping(value="/pre-registration", method = RequestMethod.GET)
	public String registrationForm(ModelMap model) {
		List<AgeRange> ageRanges = registrationService.getAllAgeRanges();
		model.addAttribute("ageRanges", ageRanges);
		model.addAttribute("today", new Date());
		model.addAttribute("message", "Welcome to Nintendo Event Pre Registration");
		model.addAttribute("guestBean", new GuestBean());
		return "preRegistrationForm";
	}

	@RequestMapping(value="/getGuest", method=RequestMethod.GET)
	public @ResponseBody GuestBean getGuest(@RequestParam("accessCode") String accessCode) {
		//System.out.println("******* Inside getGuest(), accessCode = " + accessCode);
		GuestBean guestBean = null;
		Guest guest = registrationService.getGuestForAccessCode(accessCode);
		if(guest != null){
			List<ScheduleBlock> scheduleBlocks = new ArrayList<ScheduleBlock>();

			if(guest.getInfluencerId() != null && guest.getInfluencerId() > 0){
				Guest influencer = registrationService.getGuestForId(guest.getInfluencerId());
				scheduleBlocks.add(registrationService.getScheduleBlocksForId(influencer.getScheduleBlock().getId()));
			}
			else{
				scheduleBlocks.addAll(registrationService.getScheduleBlocksForVenue(guest.getVenue().getId()));
			}
			//System.out.println("test 5, " + scheduleBlocks);

			VenueBean venueBean = new VenueBean(guest.getVenue(), new ArrayList<EventDateBean>());
			EventDateBean eventDateBean = null; 
			for(ScheduleBlock scheduleBlock : scheduleBlocks){
				//System.out.println("test 10, " + scheduleBlock.getId() + ", scheduleBlockCategories = " + scheduleBlock.getScheduleBlockCategories());
				boolean isGuestCategoryAccess = false;
				if(guest.getInfluencerId() != null && guest.getInfluencerId() > 0){
					isGuestCategoryAccess = true;
				}
				else{
					for(ScheduleBlockCategory scheduleBlockCategory : scheduleBlock.getScheduleBlockCategories()){
						if(scheduleBlockCategory.getGuestCategory().getId() == guest.getGuestCategory().getId()){
							if(scheduleBlock.getGuests().size() < scheduleBlockCategory.getMaxReservations()){
								isGuestCategoryAccess = true;
							}
							break;
						}
					}
				}

				if(isGuestCategoryAccess){
					if(eventDateBean == null){//System.out.println("test 20");
						eventDateBean = new EventDateBean(scheduleBlock.getBlockDate());
					}
					else if(eventDateBean.getEventDate().compareTo(scheduleBlock.getBlockDate()) != 0){//System.out.println("test 30");
						venueBean.getEventDateBeans().add(eventDateBean);
						eventDateBean = new EventDateBean(scheduleBlock.getBlockDate());
					}
					eventDateBean.getEventTimeBeans().add(new EventTimeBean(scheduleBlock.getId(), scheduleBlock.getStartTime(), scheduleBlock.getEndTime()));
				}
			}
			venueBean.getEventDateBeans().add(eventDateBean);
			guestBean = new GuestBean(guest, venueBean);
		}
		else{
			guestBean = new GuestBean();
		}
		return guestBean;
	}

	@RequestMapping(value="/saveGuestInfo", method=RequestMethod.POST)
	public String saveGuestInfo(@ModelAttribute GuestBean guestBean, Model model) {
		//System.out.println("******* Inside saveGuestInfo(), guestBean = " + guestBean.toString());
		//Guest guest = registrationService.getGuestForAccessCode(guestBean.getAccessCode());

		registrationService.saveGuestBean(guestBean);
		if(guestBean.getIsDeclined() == null || guestBean.getIsDeclined() == 0){
			//return "redirect:http://dwinq-example.appspot.com/resource/user/register?epc=" + guestBean.getGuestId() + "&redirectUrl=www.dwinq.com";
			model.addAttribute("isSaved", true);
			if(guestBean.getGuestAccessCodes() != null && guestBean.getGuestAccessCodes().length() > 0){
				model.addAttribute("guestAccessCodes", guestBean.getGuestAccessCodes());
			}
		}
		else{
			model.addAttribute("isDeclined", true);
		}
		return "preRegistrationForm";
	}

	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}
}
