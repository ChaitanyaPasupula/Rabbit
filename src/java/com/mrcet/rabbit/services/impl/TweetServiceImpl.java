/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.services.impl;

import com.mrcet.rabbit.Beans.Tweet;
import com.mrcet.rabbit.Beans.User;
import com.mrcet.rabbit.dao.TweetDAO;
import com.mrcet.rabbit.dao.imp.TweetDaoImpl;
import com.mrcet.rabbit.services.TweetService;
import java.util.List;

/**
 *
 * @author hp
 */
public class TweetServiceImpl implements TweetService{

    @Override
    public List<Tweet> getAlltweets() {
        TweetDAO tweetDAO = new TweetDaoImpl();
        return tweetDAO.getAlltweets();
    }

    @Override
    public List<Tweet> getUsertweets(User user) {
        TweetDAO tweetDAO = new TweetDaoImpl();
        return tweetDAO.getUsertweets(user);
    }

    @Override
    public List<Tweet> getTweetsfromFollowing(User user) {
        TweetDAO tweetDAO = new TweetDaoImpl();
        return tweetDAO.getTweetsfromFollowing(user);
    }

    @Override
    public boolean addTweet(Tweet tweet) {
        TweetDAO tweetDAO = new TweetDaoImpl();
        return tweetDAO.addTweet(tweet);
    }
}