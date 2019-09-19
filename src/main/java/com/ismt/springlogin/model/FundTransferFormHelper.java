/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.model;

import java.util.ArrayList;

/**
 *
 * @author Primax
 */
public class FundTransferFormHelper {
    private long srcAccountNumber;
    private long trgtAccountNumber;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    

    public long getSrcAccountNumber() {
        return srcAccountNumber;
    }

    public void setSrcAccountNumber(long srcAccountNumber) {
        this.srcAccountNumber = srcAccountNumber;
    }

    public long getTrgtAccountNumber() {
        return trgtAccountNumber;
    }

    public void setTrgtAccountNumber(long trgtAccountNumber) {
        this.trgtAccountNumber = trgtAccountNumber;
    }
    
    

   
    
    
}
