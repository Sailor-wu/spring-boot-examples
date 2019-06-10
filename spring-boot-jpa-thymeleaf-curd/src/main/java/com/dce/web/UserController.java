package com.dce.web;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dce.model.Location;
import com.dce.model.User;
import com.dce.service.LocationService;
import com.dce.service.UserService;
import com.dce.util.JsonUtil;
import com.dce.util.UtilTool;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    LocationService locationService;

    
    @RequestMapping("/list")
    public String list(Model model) throws JSONException, IOException {
    	JSONObject addrJson = UtilTool.getAddrName();
    	if(addrJson.get("status") == null) {    		
    		Location location = JsonUtil.jsonToObj(addrJson.toJSONString(), Location.class);
    		locationService.save(location);
    		model.addAttribute("location", location);
    	}
        List<User> users=userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user) {
        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }
    
    @RequestMapping("/sendEmail")
    public String sendEmail() {
    	
    	return "email/emailTemplate";
    }
    
    
    
}
