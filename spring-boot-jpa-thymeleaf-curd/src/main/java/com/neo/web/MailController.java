package com.neo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.model.Mail;
import com.neo.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	MailService mailService;
	
	
	@RequestMapping("/sendMail")
	@ResponseBody
	public String sendMail(Mail mail) {
		
		if(mail != null) {
			mailService.sendSimpleMail(mail);
		}
		return "";
	}
}
