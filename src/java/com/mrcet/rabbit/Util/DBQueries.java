/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.Util;

/**
 *
 * @author hp
 */
public class DBQueries {
   public static String VALIDATE_USER = "select count(*) as c from rabbituser where email=? and password=?";
   public static String INSERT_NEW_USER = "INSERT INTO rabbituser(EMAIL,PASSWORD) VALUES(?,?)";
   public static String ALL_TWEETS = "select * from rabbittweet order by id desc limit 50";
   public static String USER_TWEETS = "select * from rabbittweet where username=? order by id desc limit 50 ";
   public static String TWEETS_OF_FOLLOWING="";
   public static String INSERT_NEW_TWEET = "INSERT INTO rabbittweet(tweet,username) VALUES(?,?)";
}
