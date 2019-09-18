/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.controller;

import com.ismt.springlogin.dao.UserDao;
import com.ismt.springlogin.model.Login;
import com.ismt.springlogin.dao.UserDaoImplementation;
import com.ismt.springlogin.model.User;
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
public class RegistrationController {
    @Autowired
    UserDao userService ;
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }
    
    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView("login");
        if(userService.register(user)){
            mav.addObject("login", new Login());
            //mav.addObject("name", user.getName());
            return mav;
        }
        else{
            mav = new ModelAndView("register");
            mav.addObject("message", "Username Already Exists!!");
            return mav;
        }
  }
}
