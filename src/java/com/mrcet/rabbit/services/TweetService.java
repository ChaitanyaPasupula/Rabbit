/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.services;

import com.mrcet.rabbit.Beans.Tweet;
import com.mrcet.rabbit.Beans.User;
import java.util.List;

/**
 *
 * @author hp
 */
public interface TweetService {
    public abstract List<Tweet> getAlltweets();
    public abstract List<Tweet> getUsertweets(User user);
    public abstract List<Tweet> getTweetsfromFollowing(User user);
    public boolean addTweet(Tweet tweet);
}
