/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.controllers;

import com.mrcet.rabbit.Beans.Tweet;
import com.mrcet.rabbit.Beans.User;
import com.mrcet.rabbit.services.TweetService;
import com.mrcet.rabbit.services.impl.TweetServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;

/**
 *
 * @author hp
 */
public class GenerateDashboardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        TweetService tweetService = new TweetServiceImpl();
        List<Tweet> allTweets = null;
        List<Tweet> usertweets = null;
        
        if(request.getMethod().equals("POST")){
        HttpSession httpSession = request.getSession(false);
        User user = new User();
        String username = (String)httpSession.getAttribute("username");
        user.setEmail(username);
        // Get all tweets
        // Get user Tweets
        // Get tweets from following
        allTweets = tweetService.getAlltweets();
        usertweets = tweetService.getUsertweets(user);
        
        httpSession.setAttribute("allTweets", allTweets);
        httpSession.setAttribute("userTweets", usertweets);
        response.sendRedirect("./jsp/dashboard-old.jsp");
        }
        else{
            response.setContentType("application/json;charset=UTF-8");
            String username = request.getParameter("username");
            System.out.println(username);
            if(username!=null) 
                usertweets = tweetService.getUsertweets(new User(username,""));
            
            allTweets = tweetService.getAlltweets();
            PrintWriter printWriter = response.getWriter();
            
            JSONObject obj = new JSONObject();
            
            
            printWriter.print("{\"all\" : " + allTweets + ","); 
            printWriter.print("\"user\" :" + usertweets + "}");
            printWriter.flush();
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
