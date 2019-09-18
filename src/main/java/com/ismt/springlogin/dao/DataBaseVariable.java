/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.dao;

import com.ismt.springlogin.controller.DbConnection;

/**
 *
 * @author Primax
 */
public interface DataBaseVariable {
     public static final DbConnection dbConn = new DbConnection();
}
