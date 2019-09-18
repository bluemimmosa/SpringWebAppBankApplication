/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismt.springlogin.dao;

import com.ismt.springlogin.model.Login;
import com.ismt.springlogin.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Primax
 */
@Repository
public class UserDaoImplementation implements UserDao{
    @Override
    public boolean register(User user) {
        String sql = "SELECT * FROM `userdb`.`users` WHERE uName='"+user.getuName()+"'";
        ResultSet rs = dbConn.select(sql);
        try{
            while(rs.next()){
                
                System.out.println("Username: "+rs.getString(1));
                return false;
            }
        }catch(SQLException sqlE){
            return false;
        }
        
        sql="INSERT INTO `userdb`.`users` (`uName`, `passWord`, `name`, `address`) VALUES ('"+user.getuName()+"', '"+user.getPassWord()+"', '"+user.getName()+"', '"+user.getAddress()+"');";
        
        
        return dbConn.iud(sql);
       
        
    }

    @Override
    public User verfiyUser(Login login) {
        String sql="SELECT * FROM `userdb`.`users` WHERE uName='"+login.getUserName()+"' AND passWord='"+login.getPassword()+"'";
        ResultSet rs = dbConn.select(sql);
        try{
            while(rs.next()){
                User tmp = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                if(!(login.getUserName()).equals(tmp.getuName())){
                    return null;
                }
                if(!(login.getPassword()).equals(tmp.getPassWord())){
                    return null;
                }
                return tmp;
            }
        } catch (SQLException sQLException) {
           return null;
        }
        return null;
    } 
}
