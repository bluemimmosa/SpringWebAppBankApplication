/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.controller;

import com.ismt.springlogin.dao.UserDao;
import com.ismt.springlogin.model.Login;
import com.ismt.springlogin.model.User;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Primax
 */
@Controller
public class LoginController {
    @Autowired
    UserDao userService;
    ModelAndView mav;
    
    User user;
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView showDashboard() {
        mav = new ModelAndView("dashboard");
        mav.addObject("dashboard", new Login());
        return mav;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin() {
        mav = new ModelAndView("login");
        mav.addObject("login", new Login());
        return mav;
    }
    
    
    
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute Login login, HttpSession session) {
        user = userService.verfiyUser(login);
        if (user != null) {
            mav = new ModelAndView("dashboard");
            mav.addObject("name", user.getName());
            AccountController.user = user.getName();
            mav.addObject("title", "BANKING SYSTEM : DASHBOARD");
            session.setAttribute("uname", user.getName());
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
        }
        return mav;
    }
    
    
}
