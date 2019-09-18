/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.dao;

import com.ismt.springlogin.model.Account;
import java.util.ArrayList;

/**
 *
 * @author Primax
 */
public interface AccountDao extends DataBaseVariable{
    public boolean createAccount(long accountNumber, String name, long mobileNo, double balance, char accountType);
    public boolean deleteAccount(Account ac);
    public double deposit(Account ac, double balance);
    public double withdraw(Account ac, double balance);
    public ArrayList<Account> searchName(String name);
    public Account search(long accountNumber);
    public boolean FundTransfer(Account srcAccount, Account trgtAccount, double amount);
    public ArrayList<Account> listAll();
}
