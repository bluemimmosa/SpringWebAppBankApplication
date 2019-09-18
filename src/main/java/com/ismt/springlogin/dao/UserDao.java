/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.dao;

import com.ismt.springlogin.model.Login;
import com.ismt.springlogin.model.User;

/**
 *
 * @author Primax
 */
public interface UserDao extends DataBaseVariable{
    boolean register(User user);
    //Returns valid user if user and password matches else return null.
    User verfiyUser(Login login);
}
