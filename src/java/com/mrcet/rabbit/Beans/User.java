
package com.mrcet.rabbit.Beans;

import java.io.Serializable;

public class User implements Serializable{
    private String email;
    private String password;
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword(boolean isPermitted) {
        return isPermitted ? password : null;
    }
    
    @Override
    public String toString() {
        return "User{" + "email=" + email + "}";
    }
    
}