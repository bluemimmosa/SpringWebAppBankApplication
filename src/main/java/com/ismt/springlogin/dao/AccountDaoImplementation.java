/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.dao;

import com.ismt.springlogin.model.Account;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Primax
 */
@Repository
public class AccountDaoImplementation implements AccountDao{

    @Override
    public boolean createAccount(long accountNumber, String name, long mobileNo, double balance, char accountType) {
        Account acco = new Account(accountNumber, name, mobileNo, balance, accountType);
        //this.acc.add(acco);
        String sql ="INSERT INTO `account` (`accountNumber`, `name`, `mobileNumber`, `balance`, `accountType`) VALUES ('"+acco.getAccountNumber()+"', '"+acco.getName()+"', '"+acco.getMobileNo()+"', '"+acco.getBalance()+"', '"+acco.getAccountType()+"');";
      //  System.out.println(sql);
        return dbConn.iud(sql);
    }

    @Override
    public boolean deleteAccount(Account ac) {
        String sql = "DELETE FROM `account` WHERE (`accountNumber` = '"+ac.getAccountNumber()+"');";
        return dbConn.iud(sql);
    }

    @Override
    public double deposit(Account ac, double balance) {
        double tempAmount = ac.getBalance();
        tempAmount += balance;
        ac.setBalance(tempAmount);
        String sql = "UPDATE `account` SET `balance` = '"+tempAmount+"' WHERE (`accountNumber` = '"+ac.getAccountNumber()+"');";
        if(dbConn.iud(sql)){
            return ac.getBalance();
        }
        else{
            return -1;
        }     
    }

    @Override
    public double withdraw(Account ac, double balance) {
        double tempAmount = ac.getBalance();
                if(tempAmount < balance){
                    return -2;
                }
                tempAmount -= balance;
                ac.setBalance(tempAmount);
                String sql = "UPDATE `account` SET `balance` = '"+tempAmount+"' WHERE (`accountNumber` = '"+ac.getAccountNumber()+"');";
                if(dbConn.iud(sql)){
                    return ac.getBalance();
                }
                else{
                    return -1;
                }
    }

    @Override
    public ArrayList<Account> searchName(String name) {
        String sql ="SELECT * FROM account WHERE name like \"%"+name+"%\";";
        ResultSet rs = dbConn.select(sql);
        ArrayList<Account> data = new ArrayList<>();
        try {
            while (rs.next()) {
                Account tmp = new Account(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDouble(4), rs.getString(5).charAt(0));
                data.add(tmp);
            }
        } catch (SQLException sQLException) {
           return null;
        }
        return data;
    }

    @Override
    public Account search(long accountNumber) {
        String sql ="SELECT * FROM account WHERE accountnumber ="+accountNumber+";";
        ResultSet rs = dbConn.select(sql);
        try{
            while(rs.next()){
                Account tmp = new Account(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDouble(4), rs.getString(5).charAt(0));
                return tmp;
            }
        } catch (SQLException sQLException) {
           return null;
        }
        return null;
    }

    @Override
    public boolean FundTransfer(Account srcAccount, Account trgtAccount, double amount) {
        double fundA, fundB;
        fundA = srcAccount.getBalance();
        fundB = trgtAccount.getBalance();
        if(fundA < amount){
            return false;
        }
        fundB += amount;
        fundA -= amount;
        String sql1 = "UPDATE `account` SET `balance` = '"+fundA+"' WHERE (`accountNumber` = '"+srcAccount.getAccountNumber()+"');";
        String sql2 = "UPDATE `account` SET `balance` = '"+fundB+"' WHERE (`accountNumber` = '"+trgtAccount.getAccountNumber()+"');";
        return (dbConn.iud(sql1) && dbConn.iud(sql2));
        
    }

    @Override
    public ArrayList<Account> listAll() {
        String sql ="SELECT * FROM account;";
        ResultSet rs = dbConn.select(sql);
        ArrayList<Account> data = new ArrayList<>();
        try {
            while (rs.next()) {
                Account tmp = new Account(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDouble(4), rs.getString(5).charAt(0));
                data.add(tmp);
            }
        } catch (SQLException sQLException) {
           return null;
        }
        return data;
    }
    
}
