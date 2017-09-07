/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.services.impl;

import com.mrcet.rabbit.Beans.User;
import com.mrcet.rabbit.dao.UserDAO;
import com.mrcet.rabbit.dao.imp.UserDaoImpl;
import com.mrcet.rabbit.services.LoginService;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService{

    @Override
    public boolean validateUser(User user) throws SQLException,ClassNotFoundException{
        UserDAO userDAO= new UserDaoImpl();
        return userDAO.validateUser(user);
    }

    @Override
    public boolean registerUser(User user) throws SQLException, ClassNotFoundException {
        UserDAO userDAO= new UserDaoImpl();
        return userDAO.registerUser(user);
    }
    
}
