/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.dao.imp;

import com.mrcet.rabbit.Beans.Tweet;
import com.mrcet.rabbit.Beans.User;
import com.mrcet.rabbit.Util.DBQueries;
import com.mrcet.rabbit.Util.DBUtil;
import com.mrcet.rabbit.dao.TweetDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class TweetDaoImpl implements TweetDAO{
    
    @Override
    public List<Tweet> getAlltweets() {
        Connection con=null;
        List<Tweet> allTweets = new ArrayList<>();
        try{
            con=DBUtil.getConnection();
            PreparedStatement pst=con.prepareStatement(DBQueries.ALL_TWEETS);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()){
                String tweet = resultSet.getString("tweet");
                User user = new User(resultSet.getString("username"),null);
                Tweet temp = new Tweet(tweet,user);
                allTweets.add(temp);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
                    
        }
        finally{
            DBUtil.closeConnection(con);
        }
        return allTweets;
    }

    @Override
    public List<Tweet> getUsertweets(User user) {
        Connection con=null;
        List<Tweet> userTweets = new ArrayList<>();
        try{
            con=DBUtil.getConnection();
            PreparedStatement preparedStatement=con.prepareStatement(DBQueries.USER_TWEETS);
            preparedStatement.setString(1,user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String tweet = resultSet.getString("tweet");
                Tweet temp = new Tweet(tweet,user);
                userTweets.add(temp);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(TweetDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBUtil.closeConnection(con);
        }
        return userTweets;
    }

    @Override
    public List<Tweet> getTweetsfromFollowing(User user) {
        return null;
    }

    @Override
    public boolean addTweet(Tweet tweet) {
        Connection con = null;
        boolean status = false;
        try {
            con = DBUtil.getConnection();
            if(con != null){
                String insertSQL = DBQueries.INSERT_NEW_TWEET;
                PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
                preparedStatement.setString(1,tweet.getTweet());
                preparedStatement.setString(2,tweet.getUser().getEmail());
                int returnVal = preparedStatement.executeUpdate();
                if(returnVal == 1){
                    status = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            DBUtil.closeConnection(con);
        }
        return status;
    }
    
}
