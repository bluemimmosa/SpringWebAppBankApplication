/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.controller;

import com.ismt.springlogin.dao.AccountDao;
import com.ismt.springlogin.model.Account;
import com.ismt.springlogin.model.User;
import java.util.ArrayList;
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
public class AccountController {
    @Autowired
    AccountDao acc;
    public static String user;
    
    @RequestMapping(value = "/createnewaccount", method = RequestMethod.GET)
    public ModelAndView showCreateNewAccount() {
        ModelAndView mav = new ModelAndView("createnewaccount");
        mav.addObject("account", new Account());
        mav.addObject("name",user);
        mav.addObject("title", "BANKING SYSTEM : OPEN NEW ACCOUNT");
        return mav;
    }
    
    @RequestMapping(value = "/processNewAccount", method = RequestMethod.POST)
    public ModelAndView processNewAccount(@ModelAttribute("account") Account account) {
        System.out.println("ProcessNewAccount bhitra");
        if(acc.createAccount(account.getAccountNumber(), account.getName(), account.getMobileNo(), account.getBalance(), account.getAccountType())){
            //mav.addObject("login", new Login());
            ModelAndView mav = new ModelAndView("createnewaccount");
            mav.addObject("message", "Account Created successfully");
            mav.addObject("name",user);
            return mav;
        }
        else{
            ModelAndView mav = new ModelAndView("processnewAccount");
            mav.addObject("message", "Account already Exist!!");
            mav.addObject("name",user);
            return mav;
        }
    }
    
    @RequestMapping(value = "/deleteaccount", method = RequestMethod.GET)
    public ModelAndView showDeleteAccount() {
        ModelAndView mav = new ModelAndView("deleteaccount");
        mav.addObject("name",user);
        mav.addObject("account", new Account());
        mav.addObject("title", "BANKING SYSTEM : CLOSE ACCOUNT");
        return mav;
    }
    
    @RequestMapping(value = "/processdeleteaccount", method = RequestMethod.POST)
    public ModelAndView deleteAccount(@ModelAttribute("account") Account account) {
        Account tmpAcc = acc.search(account.getAccountNumber());
        if(tmpAcc == null){
            ModelAndView mav = new ModelAndView("deleteaccount");
            mav.addObject("message", "Account doesnt exist!!");
            mav.addObject("name",user);
            return mav;
        }
        else{
            if(acc.deleteAccount(tmpAcc)){
                ModelAndView mav = new ModelAndView("deleteaccount");
                mav.addObject("message", "Account deleted successfully!!");
                mav.addObject("name",user);
                return mav;
            }
            else{
                ModelAndView mav = new ModelAndView("deleteaccount");
                mav.addObject("message", "Account cannot be deleted!!");
                mav.addObject("name",user);
                return mav;
            }
        }
    }
    
    
    @RequestMapping(value = "/listallaccounts", method = RequestMethod.GET)
    public ModelAndView showListAllAccounts() {
        ArrayList<Account> aList = acc.listAll();
        ModelAndView mav = new ModelAndView("listallaccounts");
        mav.addObject("aList",aList);
        mav.addObject("name",user);
        mav.addObject("title", "BANKING SYSTEM : LIST ALL ACCOUNTS");
        return mav;
    }
    
    @RequestMapping(value = "/searchbyname", method = RequestMethod.GET)
    public ModelAndView showSearchByName() {
        ArrayList<Account> aList = acc.listAll();
        ModelAndView mav = new ModelAndView("searchbyname");
        mav.addObject("account", new Account());
        mav.addObject("name",user);
        mav.addObject("title", "BANKING SYSTEM : SEARCH BY NAME");
        return mav;
    }
    
    
    @RequestMapping(value = "/processsearchaccountbyname", method = RequestMethod.POST)
    public ModelAndView processSearchByName(@ModelAttribute("account") Account account) {
        ArrayList<Account> aList = acc.searchName(account.getName());
        ModelAndView mav = new ModelAndView("listofsearchbyname");
        mav.addObject("aList",aList);
        mav.addObject("name",user);
        mav.addObject("title", "BANKING SYSTEM : SEARCH BY NAME LISTS");
        return mav;
    }
    
    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public ModelAndView showWithdrawForm() {
        ModelAndView mav = new ModelAndView("withdraw");
        mav.addObject("name",user);
        mav.addObject("account", new Account());
        mav.addObject("title", "BANKING SYSTEM : WITHDRAW AMOUNT");
        return mav;
    }
    
    @RequestMapping(value="/processwithdraw", method=RequestMethod.POST)
    public ModelAndView processWithdraw(@ModelAttribute ("account") Account account){
        ModelAndView mav = new ModelAndView("withdraw");
        mav.addObject("name", user);
        Account ac = acc.search(account.getAccountNumber());
        double status = acc.withdraw(ac, account.getBalance());
        if(status == -2){
           mav.addObject("message","Insufficient Balance");
        }else if(status == -1){
           mav.addObject("message", "Some errors occured, cannot withdraw");
        }else{
           mav.addObject("message","Withdraw successful, Now balance is "+status); 
        }
                
        return mav;
}
}
