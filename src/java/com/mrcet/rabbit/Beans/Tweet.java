/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.Beans;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class Tweet implements Serializable{
   private String tweet;
   private User user;
   private boolean isAnynomous;
   public Tweet(String tweet, User user){
       this.tweet = tweet;
       this.user = user;
   }
   public Tweet(String tweet, User user, boolean isAnynomous){
       this.tweet = tweet;
       this.user = user;
       this.isAnynomous = isAnynomous;
   }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public User getUser() {
        return user;
    }
    
    public boolean isIsAnynomous() {
        return isAnynomous;
    }

    public void setIsAnynomous(boolean isAnynomous) {
        this.isAnynomous = isAnynomous;
    }

    @Override
    public String toString() {
        return "Tweet{" + "tweet=" + tweet + ", user=" + user + ", isAnynomous=" + isAnynomous + '}';
    }
    
}
