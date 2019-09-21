/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.controller;

import com.ismt.springlogin.dao.AccountDao;
import com.ismt.springlogin.model.Account;
import com.ismt.springlogin.model.FundTransferFormHelper;
import com.ismt.springlogin.model.Login;
import java.util.ArrayList;
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
public class AccountController {
    @Autowired
    AccountDao acc;
    public static String user;
    //private static FundTransferFormHelper fundTransferFormHelper = new FundTransferFormHelper();
    
    @RequestMapping(value = "/createnewaccount", method = RequestMethod.GET)
    public ModelAndView showCreateNewAccount(HttpSession session) {
        if(session.getAttribute("uname")== null){
            ModelAndView mav = new ModelAndView("dashboard");
            mav.addObject("title", "BANKING SYSTEM : OPEN NEW ACCOUNT");
            mav.addObject("message", "Login is required to do that.");
            return mav;
        }
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
    public ModelAndView showDeleteAccount(HttpSession session) {
        if(session.getAttribute("uname")== null){
            ModelAndView mav = new ModelAndView("dashboard");
            mav.addObject("title", "BANKING SYSTEM : CLOSE ACCOUNT");
            mav.addObject("message", "Login is required to do that.");
            return mav;
        }
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
    public ModelAndView showListAllAccounts(HttpSession session) {
        if(session.getAttribute("uname")== null){
            ModelAndView mav = new ModelAndView("dashboard");
            mav.addObject("title", "BANKING SYSTEM : LIST ALL ACCOUNTS");
            mav.addObject("message", "Login is required to do that.");
            return mav;
        }
        ArrayList<Account> aList = acc.listAll();
        ModelAndView mav = new ModelAndView("listallaccounts");
        mav.addObject("aList",aList);
        mav.addObject("name",user);
        mav.addObject("title", "BANKING SYSTEM : LIST ALL ACCOUNTS");
        return mav;
    }
    
    @RequestMapping(value = "/searchbyname", method = RequestMethod.GET)
    public ModelAndView showSearchByName(HttpSession session) {
        if(session.getAttribute("uname")== null){
            ModelAndView mav = new ModelAndView("dashboard");
            mav.addObject("title", "BANKING SYSTEM : SEARCH ACCOUNT BY NAME");
            mav.addObject("message", "Login is required to do that.");
            return mav;
        }
        ArrayList<Account> aList = acc.listAll();
        ModelAndView mav = new ModelAndView("searchbyname");
        mav.addObject("account", new Account());
        mav.addObject("name",user);
        mav.addObject("title", "BANKING SYSTEM : SEARCH ACCOUNT BY NAME");
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
    public ModelAndView showWithdrawForm(HttpSession session) {
        if(session.getAttribute("uname")== null){
            ModelAndView mav = new ModelAndView("dashboard");
            mav.addObject("title", "BANKING SYSTEM : WITHDRAW AMOUNT");
            mav.addObject("message", "Login is required to do that.");
            return mav;
        }
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
        if(ac == null){
            mav.addObject("message", "Account doesnt exist.");
            return mav;
        }
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
    
    @RequestMapping(value = "/fundtransfer", method = RequestMethod.GET)
    public ModelAndView showFundTransferForm(HttpSession session) {
        if(session.getAttribute("uname")== null){
            ModelAndView mav = new ModelAndView("dashboard");
            mav.addObject("title", "BANKING SYSTEM : FUND TRANSFER");
            mav.addObject("message", "Login is required to do that.");
            return mav;
        }
        ModelAndView mav = new ModelAndView("fundtransfer");
        mav.addObject("name",user);
        mav.addObject("fundtransferhelper", new FundTransferFormHelper());
        //mav.addObject("trgtaccount", new Account());
        mav.addObject("title", "BANKING SYSTEM : FUND TRANSFER");
        return mav;
    }
    
    @RequestMapping(value="/processfundtransfer", method=RequestMethod.POST)
    public ModelAndView processWithdraw(@ModelAttribute ("fundtransferhelper") FundTransferFormHelper fundTransferFormHelper){
        ModelAndView mav = new ModelAndView("fundtransfer");
        mav.addObject("name", user);
        Account srcAc = acc.search(fundTransferFormHelper.getSrcAccountNumber());
        Account trgtAc = acc.search(fundTransferFormHelper.getTrgtAccountNumber());
        if(srcAc == null){
            mav.addObject("message","Source Account doesnt exist.");
            return mav;
        }
        if(trgtAc == null){
            mav.addObject("message","Target Account doesnt exist.");
            return mav;
        }
        if(!acc.FundTransfer(srcAc, trgtAc, fundTransferFormHelper.getBalance())){
            mav.addObject("message", "Fund Transfer failed due to insufficeint balance in source account.");
        }else{
            mav.addObject("message", "Fund transferres Successfully.");
        }
        return mav;
    }
    
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        if(session.getAttribute("uname")== null){
            ModelAndView mav = new ModelAndView("dashboard");
            mav.addObject("title", "BANKING SYSTEM : LOGOUT");
            mav.addObject("message", "You are not logged into system before logging out.");
            return mav;
        }
        session.invalidate();
//        session.removeAttribute("uname");
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());
        return mav;
    }
}
