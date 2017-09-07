
package com.mrcet.rabbit.services;

import com.mrcet.rabbit.Beans.User;
import java.sql.SQLException;

public interface LoginService {

    public abstract boolean validateUser(User user) throws SQLException,ClassNotFoundException;
    public abstract boolean registerUser(User user) throws SQLException,ClassNotFoundException;
    
}
