/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.dao;

import com.mrcet.rabbit.Beans.User;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public interface UserDAO {
    public abstract boolean validateUser(User user) throws SQLException,ClassNotFoundException;    

    public abstract boolean registerUser(User user) throws SQLException, ClassNotFoundException ;
}
