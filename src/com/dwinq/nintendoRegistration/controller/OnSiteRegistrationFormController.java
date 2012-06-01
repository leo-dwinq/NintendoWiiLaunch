package com.dwinq.nintendoRegistration.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dwinq.nintendoRegistration.entity.AgeRange;
import com.dwinq.nintendoRegistration.service.RegistrationService;


@Controller
//@RequestMapping("/onsite-registration")
public class OnSiteRegistrationFormController { 

	@Autowired
	private RegistrationService registrationService;
	
//	@RequestMapping(value="/registration/")
//    public ModelAndView edit(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		System.out.println(id);
//
//		ModelAndView m =  new ModelAndView("/registration/");
//		return m;
//    }
//	@RequestMapping(method = RequestMethod.GET)
//	public String registrationForm(ModelMap model) {
//		model.addAttribute("today", new Date());
//		model.addAttribute("message", "Welcome to Nintendo Event On Site Registration");
//		return "onSiteRegistrationForm";
//	}
	
	@RequestMapping(value="/onsite-registration", method = RequestMethod.GET)
	public String registrationForm(ModelMap model) {
		List<AgeRange> ageRanges = registrationService.getAllAgeRanges();
		model.addAttribute("ageRanges", ageRanges);
		model.addAttribute("today", new Date());
		model.addAttribute("message", "Welcome to Nintendo Event Onsite Registration");
		model.addAttribute("guestBean", new GuestBean());
		return "onSiteRegistrationForm";
	}
}