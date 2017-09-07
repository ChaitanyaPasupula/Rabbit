/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.dao.imp;

import com.mrcet.rabbit.Beans.User;
import com.mrcet.rabbit.Util.DBQueries;
import com.mrcet.rabbit.Util.DBUtil;
import com.mrcet.rabbit.dao.UserDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class UserDaoImpl implements UserDAO{

    @Override
    public boolean validateUser(User user) throws SQLException, ClassNotFoundException {
        Connection con=null;
        boolean status = false;
        try {
            con=DBUtil.getConnection();
            if(con != null){
                PreparedStatement pst=con.prepareStatement(DBQueries.VALIDATE_USER);
                pst.setString(1,user.getEmail());
                pst.setString(2,user.getPassword(true));// validate permissions to get password
                ResultSet resultSet = pst.executeQuery();
                
                while(resultSet.next()){
                    System.out.println(resultSet.getString("c"));
                    status = (Integer)resultSet.getInt(1) == 1 ? true : false;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtil.closeConnection(con);
        }
        return status;
    }

    @Override
    public boolean registerUser(User user) throws SQLException, ClassNotFoundException {
        Connection con=null;
        boolean status = false;
        try {
            con=DBUtil.getConnection();
            if(con != null){
                String insertSQL = DBQueries.INSERT_NEW_USER;
                PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
                preparedStatement.setString(1,user.getEmail());
                preparedStatement.setString(2,user.getPassword(true));
                int returnVal = preparedStatement.executeUpdate();
                if(returnVal == 1){
                    status = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtil.closeConnection(con);
        }
        return status;
    }
}