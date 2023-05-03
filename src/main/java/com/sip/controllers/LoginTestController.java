package com.sip.controllers;

import com.sip.entities.User;
import com.sip.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginTestController {

    UserService userService;
    @Autowired
    public LoginTestController(UserService userService)
    {
        this.userService = userService;
    }
    @RequestMapping("/add")
    @ResponseBody
    public String addUser()
    {
        User user = new User();
        user.setEmail("amine.mezghich@gmail.com");
        user.setPassword("123456");
        user.setName("Mohamed Amine");
        user.setLastName("MEZGHICH");
        this.userService.saveUser(user);
        return "Added";
    }
}
