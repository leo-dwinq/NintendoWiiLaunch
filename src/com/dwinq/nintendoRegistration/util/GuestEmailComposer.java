package com.dwinq.nintendoRegistration.util;

import com.dwinq.nintendoRegistration.entity.Guest;

public class GuestEmailComposer {
	public static void sendInfluencerConfirmationEmail(Guest guest){
		String[] accessCodes = guest.getGuestAccessCodes();
		String subject = "Thank you for registering for the Wii U Experience!";

		StringBuffer message = new StringBuffer();
		message.append("Thank you for registering for the Wii U Experience.  Your confirmation is bellow.");
		message.append("<br><br>");
		message.append("Location: " + guest.getVenue().getCity() + "/" + guest.getVenue().getState());
		message.append("<br>Event date: " + guest.getScheduleBlock().getBlockDate());
		message.append("<br>Event Time: " + guest.getScheduleBlock().getStartTime() + " - " + guest.getScheduleBlock().getEndTime());

		if(accessCodes != null && accessCodes.length > 0){
			message.append("<br><br>");
			message.append(accessCodes.length + " of additional spots have been reserved for your friends and family.  Share the access codes and registration site below with your guests to reserve their spot at the event by June 22.");
			message.append("<br>");
			int i = 1;
			for(String accessCode : accessCodes){
				message.append("<br>Access Code #" + i + ": " + accessCode);
			}
		}

		message.append("<br><br>");
		message.append("We look forward to sharing everything the all-new Wii U has to offer at this exciting event you'll never forget");

		message.append("<br><br>");


	}
	public static void sendGuestConfirmationEmail(Guest guest){

	}
	public static void sendInvitationDeclineConfirmationEmail(Guest guest){

	}
}
