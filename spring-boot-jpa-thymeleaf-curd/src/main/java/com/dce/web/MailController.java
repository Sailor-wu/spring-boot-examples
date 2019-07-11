package com.dce.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dce.model.Mail;
import com.dce.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	MailService mailService;
	
	
	@RequestMapping("/sendMail")
	@ResponseBody
	public String sendMail(Mail mail) {
		
		if(mail != null) {
//			mailService.sendSimpleMail(mail);
			mailService.sendEmailSSL(mail);
			return "redirect:/user/list";
		}
		return "";
	}
	
	@RequestMapping("/getmaillist")
	@ResponseBody
	public JSONArray getMailList(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<Mail> lists = mailService.getMailList();
		if(lists == null) {			
			return null;
		}
		return JSON.parseArray(JSON.toJSONString(lists));
	}
}
