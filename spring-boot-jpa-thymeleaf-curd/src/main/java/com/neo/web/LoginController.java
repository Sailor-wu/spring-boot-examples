package com.neo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neo.model.User;

@Controller
public class LoginController {
	@RequestMapping("/")
    public String index() {
        return "login";
    }
    
    @RequestMapping("/login")
    public String login(User user) {
    	System.out.println(user);
    	if(user != null && user.getUserName().equals("admin") && user.getPassword().equals("admin")) {
    		return "redirect:/user/list";
    	}
    	return "login";
    }
    
}
