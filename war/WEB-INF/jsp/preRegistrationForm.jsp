<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <script type="text/javascript" src="resources/js/jquery-1.7.2.js"></script>
  <title>Pre Registration</title>
<script type="text/javascript">
var ageRanges = new Array();
<c:forEach var="ageRange" items="${ageRanges}" >
var ageRange = new Object();
ageRange.isRegisterAllowed = <c:out value="${ageRange.isRegisterAllowed}"></c:out>;
ageRange.isChaperonRequired = <c:out value="${ageRange.isChaperonRequired}"></c:out>;
ageRange.range = '<c:out value="${ageRange.range}"></c:out>';
ageRanges.push(ageRange);
</c:forEach>
</script>
<script> 
function fillGuestInfo2() {
	var accessC = $('#accessCode').val();
	alert('test 10, ' + accessC);
  $.getJSON("getGuest", { accessCode: accessC }, function(guest) {
	  alert('test 20');
     document.guestInfo.firstName.value = guest.firstName;
     document.guestInfo.lastName.value = guest.lastName;
  });
}
</script>

<script>
function convertMilitaryTimeToStandardTime(militaryTime){
	var mTime = parseInt(militaryTime);
	var hour = mTime / 100;
	var minutes = mTime % 100;
	var minuteStr = minutes + '';
	if(minutes < 10){
		minuteStr = '0' + minuteStr;
	}
	var amPm = 'am';
	if(hour >= 12){
		amPm = 'pm';
		hour -= 12;
	}
	if(hour == 0){
		hour = 12;
	}
	
	return hour + ':' + minuteStr + amPm;
}
</script>
<script>
var currentGuest;

function selectEventDate(){
    var eventDateSelect = document.getElementById("eventDate");
    var eventTimeSelect = document.getElementById("eventTime");
	
    eventTimeSelect.options.length = 0;
    eventTimeSelect.add(new Option('Please select', ''), null);
    
    for(var i = 0;i < currentGuest.venueBean.eventDateBeans.length;i++){
        var eventDateBean = currentGuest.venueBean.eventDateBeans[i];
        //alert(eventDateBean.eventDate + ', ' + parseInt(eventDateSelect.options[eventDateSelect.selectedIndex].value))
        if(eventDateBean.eventDate == parseInt(eventDateSelect.options[eventDateSelect.selectedIndex].value)){
		    //alert('test 10'); 
        	for(var i = 0;i < eventDateBean.eventTimeBeans.length;i++){
                 var eventTimeBean = eventDateBean.eventTimeBeans[i];
                 //alert('test 20, ' + eventTimeBean.startTime);
		         eventTimeSelect.add(new Option(convertMilitaryTimeToStandardTime(eventTimeBean.startTime) + ' - ' + convertMilitaryTimeToStandardTime(eventTimeBean.endTime),eventTimeBean.blockId + ''), null);
		    }
		    break;
        }
    }
}

function fillGuestInfo(){
	var accessC = $('#accessCode').val();
	
	$.ajax({
		type: "GET",
		url: "getGuest",
		data: "accessCode=" + accessC,
		success: function(guest){
			 currentGuest = guest;
			 
		     var locationDiv = document.getElementById("location");
		     locationDiv.innerHTML = '';

		     var eventDateSelect = document.getElementById("eventDate");
		     var eventTimeSelect = document.getElementById("eventTime");
		     eventDateSelect.options.length = 0;
		     eventTimeSelect.options.length = 0;
		     
			 if(guest.guestId != null && guest.guestId > 0){
			     document.guestInfo.guestId.value = guest.guestId;
			     document.guestInfo.categoryId.value = guest.categoryId;
			     document.guestInfo.firstName.value = guest.firstName;
			     document.guestInfo.lastName.value = guest.lastName;
			     document.guestInfo.email.value = guest.email;
			     document.guestInfo.venueId.value = guest.venueBean.id;
			     document.guestInfo.influencerId.value = guest.influencerId;

			     var row = document.getElementById("row7");
			     if(guest.categoryCode == 'DCCF' || guest.categoryCode == 'FSF'){
				    row.style.display = '';
			     }
			     else{
			     	row.style.display = 'none';
			     }
			     
			     locationDiv.innerHTML = guest.venueBean.city + '/' + guest.venueBean.state;
			     
			     if(guest.venueBean.eventDateBeans.length > 1){
			     	eventDateSelect.add(new Option('Please select', ''), null);
		    	    eventTimeSelect.add(new Option('Please select', ''), null);
			 	 }
			     else{
			    		
			    	if(guest.venueBean.eventDateBeans[0].eventTimeBeans.length > 1){
			    	    eventTimeSelect.add(new Option('Please select', ''), null);
			    	}
			       	for(var i = 0;i < guest.venueBean.eventDateBeans[0].eventTimeBeans.length;i++){
	                    var eventTimeBean = guest.venueBean.eventDateBeans[0].eventTimeBeans[i];
	                     //alert('test 20, ' + eventTimeBean.startTime);
	    	            eventTimeSelect.add(new Option(convertMilitaryTimeToStandardTime(eventTimeBean.startTime) + ' - ' + convertMilitaryTimeToStandardTime(eventTimeBean.endTime),eventTimeBean.blockId + ''), null);
				    }
			     }
			     for(var i = 0;i < guest.venueBean.eventDateBeans.length;i++){
	                 var eventDateBean = guest.venueBean.eventDateBeans[i];
	                 //alert(eventDateBean.eventDate + '');
	                 var eventDate = new Date(eventDateBean.eventDate);
			         eventDateSelect.add(new Option(eventDate.toString('dddd, MMMM ,yyyy').substring(0,15),eventDateBean.eventDate), null);
			     }
			 }
			 else{
			     document.guestInfo.guestId.value = '';
			     document.guestInfo.categoryId.value = '';
			     document.guestInfo.firstName.value = '';
			     document.guestInfo.lastName.value = '';
			     document.guestInfo.email.value = '';
			     document.guestInfo.venueId.value = '';
			     document.guestInfo.influencerId.value = '';
			     eventDateSelect.add(new Option('Please select', ''), null);
		    	 eventTimeSelect.add(new Option('Please select', ''), null);
				 alert('Access code ' + accessC + ' is not recognized');
			 }
		},
	    error: function (request, status, error) {
	        //alert(request.responseText);
	        var errorDiv = document.getElementById("error");
	        errorDiv.innerHTML= request.responseText;
	    }
     });

}
</script>
<script>
var eventDetailsHtml;
function declineInvitationHandler(){
	//var row5 = document.getElementById("row5");
	var declineCheckBox = document.getElementById("declineCheckBox");
	//alert('test 20, ' + declineCheckBox.checked + ', ' + eventDetailsDiv.innerHTML);
	if(declineCheckBox.checked){
        for(var i = 7;i <= 8;i++){
        	var row = document.getElementById("row" + i);
		    row.style.display = 'none';
        }
		//$('tbody').find('tr:row5').hide();
	}
	else{
		//eventDetailsDiv.innerHTML = eventDetailsHtml;
        for(var i = 7;i <= 8;i++){
        	var row = document.getElementById("row" + i);
        	if(i != 7 || (currentGuest.categoryCode == 'DCCF' || currentGuest.categoryCode == 'FSF')){
		    	row.style.display = '';
        	}
        }
		//row5.style.display = '';

		//$('tbody').find('tr:row5').show();
	}

}
</script>
<script>
function submitForm(){
	var agreeToTermsCheckBox = document.getElementById("agreeToTerms");
    var eventTimeSelect = document.getElementById("eventTime");
    var ageRangeSelect = document.getElementById("ageRangeId");
    var isChaperoningUnder13CheckBox = document.getElementById("isChaperoningUnder13");
    var chaperoningCountUnder13Select = document.getElementById("chaperoningCountUnder13");
	var declineCheckBox = document.getElementById("declineCheckBox");    
   
    
	if(!agreeToTermsCheckBox.checked){
		alert('You need to agree to terms to proceed');
		return;
	}
	if(!declineCheckBox.checked){
		if(eventTimeSelect.selectedIndex == 0){
			alert('Please select date and time when you\'ll be attending');
			return;
		}
		if(ageRangeSelect.selectedIndex == 0){
			alert('Please select age range');
			return;
		}
		else{
			var ageRange = ageRanges[ageRangeSelect.selectedIndex - 1];
			if(ageRange.isRegisterAllowed != 1){
				alert('Guests must be over 13 to register.');
				return;
			}
			else if(ageRange.isChaperonRequired == 1){
				alert('Guests under 18 are allowed to register but must be chaperoned by an adult.');
			}
		}
		if(isChaperoningUnder13CheckBox.checked && chaperoningCountUnder13Select.selectedIndex == 0){
			alert('Please select the number of children under 13 you will be chaperoning.')
			return;
		}
	}

	document.guestInfo.submit();
}
</script>

</head>
<body>
								
							
<%-- ${message} ${today} --%>
<div id="error"></div>
<table width="900" align="center">
 <tr>
  <td>
  <img src="resources/img/header.jpg" width="900" height="215" border="0">
  </td>
 </tr>
</table>

<br><br>
    <c:choose>
        <c:when test="${isSaved}">
<table width="900" align="center">
 <tr>
  <td colspan="2">
Thank you for registering for the Wii U Experience!
<br><br>
We are excited to offer guests of the Wii U Experience an opt-in feature 
which will allow you to instantly share personal photos and reactions 
from the event with your friends on Facebook.
<br><br>
If you would like to instantly post photos to your Facebook page from the event, 
click "CONNECT TO FACEBOOK" bellow and follow the steps to authorize the application.

<br><br>
<c:out value="${guestAccessCodes}"></c:out>
  </td>
 </tr>
 <tr>
  <td align="right">
  <a href="www.nintendo.com">NO THANKS</a>&nbsp;&nbsp;&nbsp;
  </td>
  <td>&nbsp;&nbsp;&nbsp;
  <a href="http://dwinq-example.appspot.com/resource/user/register?epc=5&redirectUrl=www.nintendo.com">CONNECT TO FACEBOOK</a>
  </td>
 </tr>
</table>	
        </c:when>
        <c:when test="${isDeclined}">
<table width="900" align="center">
 <tr>
  <td>
Your invitation has been declined.<br>
Thank you for taking your time to respond to the invitation.
  </td>
 </tr>
</table>	
        </c:when>
        <c:otherwise>
<table align="center" width="900">
 <tr>
  <td align="center">
  This holiday season, Nintendo is launching the next-generation entertainment console. Wii U is a new system that will introduce remarkable ways for friends and families to play together.
  <br><br>
To celebrate, we are taking Wii U on the road and hosting an all-access preview event to give
fans a sneak peek. Be one of the first to experience everything Wii U has to offer, including a revolutionary new touch-screen controller, precision motion controls and full HD graphics. This is an exclusive event you don't want to miss.
<br><br>
Enter your access code in the box below to reserve your spot. Please note that children age 17 and under must be accompanied by a parent or legal guardian to the event.
We hope you can join us for this exciting preview event!
  </td>
 </tr>
</table>     
<br>   
<form:form name="guestInfo" action="saveGuestInfo" commandName="guestBean" >
	<form:input type="hidden" path="guestId" />
	<form:input type="hidden" path="categoryId" />
	<form:input type="hidden" path="venueId" />
	<form:input type="hidden" path="influencerId" />
<center>
<table width="900">
<tr>
	<td align="right" width="325">
	*Access Code:
	</td>
	<td colspan="3" width="125">
	<!-- <input type="text" name="accessCode" id="accessCode" value="" size="25" maxlength="20" onBlur="fillGuestInfo()"><br> -->
	<form:input id="accessCode" onBlur="fillGuestInfo()" path="accessCode" />
	</td>
	<td>
	</td>
	<td>
	</td>		
</tr>

<tr>
	<td align="right" width="325">
	*First Name:
	</td>
	<td valign="bottom" width="125">
	<!-- <input type="text" name="firstName" value="" size="25" maxlength="20"><br> -->
	<form:input id="firstName" path="firstName" />
	</td>
	
	<td align="right" width="325">
	*Last Name:
	</td>
	<td valign="bottom" width="125">
	<!-- <input type="text" name="lastName" value="" size="25" maxlength="20"><br> -->
	<form:input id="lastName" path="lastName" />
	</td>	
</tr>

<tr>
	<td align="right" width="325">
	*Email Address:
	</td>
	<td width="125">
	<form:input id="email" path="email" size="30"/>
	</td>
	
	<td>
	</td>
	<td>
	</td>	
</tr>

<tr>
	<td align="right" width="325">
	*Age Range:
	</td>
	<td valign="bottom" width="125">
<form:select path="ageRangeId">
<form:option value="" label="Please select" />
<form:options items="${ageRanges}" itemValue="id" itemLabel="range" />
</form:select>
	</td>
	
	<td align="right" width="325">
	Gender:
	</td>
	<td valign="bottom" width="125">
<form:select path="gender">
<form:option value="" label="Please select" />
<form:option value="M" label="Male" />
<form:option value="F" label="Female" />
<form:option value="" label="(Decline)" />
</form:select>		
	</td>	
</tr>

<tr id="row5">
	<td align="right" width="325">
	Event Location:
	</td>
	<td width="125">
		 <div id="location"></div>
	</td>
	
	<td>
	</td>
	<td>
	</td>	
</tr>

<tr id="row6">
	<td align="right" width="325">
	*Select Date:
	</td>
	<td valign="bottom" width="125">
		<!-- <input type="date" name="Date"><br> -->

<form:select path="dateStr" id="eventDate" onChange="selectEventDate()">
<form:option value="" label="Please select" />
</form:select>		
	</td>
	
	<td align="right" width="325">
	*Select Time:
	</td>
	<td valign="bottom" width="125">
		<!-- <input type="time" name="Time"><br> -->
<form:select path="scheduleBlockId" id="eventTime">
<form:option value="" label="Please select" />
</form:select>	
	</td>	
</tr>

<tr>
	<td align="right" width="325">
	Decline Invitation:
	</td>
	<td valign="bottom" width="125">
		<!-- <input type="checkbox" name="decline" id="declineCheckBox" value="1" onClick="declineInvitationHandler()"> -->
		<form:checkbox id="declineCheckBox" path="isDeclined" value="1" onClick="declineInvitationHandler()" />
	</td>
	
	<td>
	</td>
	<td>
	</td>	
</tr>

<tr id="row7">
	<td align="right" width="325">
		Please check this box if you will be chaperoning any children under the age of 13 to the event?
	</td>
	<td valign="bottom" width="125">
		<input type="checkbox" name="isChaperoningUnder13" id="isChaperoningUnder13" value="1" >
	</td>
	
	<td align="right" width="325">
		Select number of additional RSVPs for children under 13:
	</td>
	<td valign="bottom" width="125">
<form:select path="chaperoningCountUnder13" id="chaperoningCountUnder13">
<form:option value="0" label="0" />
<form:option value="1" label="1" />
<form:option value="2" label="2" />
<form:option value="3" label="3" />
</form:select>	
	</td>	
</tr>
<tr>
	<td align="right" width="325">
		Please check this box to receive future communication from Nintendo:
	</td>
	<td valign="bottom" width="125">
		<input type="checkbox" name="isFutureCommunication" value="1" ><br>
	</td>
	
	<td>
	</td>
	<td>
	</td>	
</tr>
<tr>
	<td align="right" width="325">
		Please check this box to agree to Terms and Conditions:
		<br />
		<a href="http://www.google.com">Hyperlink</a>
	</td>
	<td valign="bottom" width="125">
		<input type="checkbox" name="agreeToTerms" id="agreeToTerms" value="Accept" ><br>
	</td>
	
	<td>
	</td>
	<td>
		<input type="button" width="250" height="75" name="nextPage" value="Next Step" onClick="submitForm()">
	</td>	
</tr>


</table>
</center>
</form:form>

<script>
var row = document.getElementById("row7");
row.style.display = 'none';
</script>

        </c:otherwise>
    </c:choose>
<br><br><br>    
<center>
<span style="font-size:10pt">Wii U is a tradermark of Nintendo. &copy; 2012 Nintendo.</span>
<br>
<a href="">Help</a>&nbsp;&nbsp;&nbsp;<a href="">Privacy Policy</a>&nbsp;&nbsp;&nbsp;<a href="faq.html" target="_BLANK">FAQ</a>
</center>
</body>
</html>