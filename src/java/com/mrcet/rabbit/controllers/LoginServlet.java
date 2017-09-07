/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrcet.rabbit.controllers;

import com.mrcet.rabbit.Beans.User;
import com.mrcet.rabbit.services.LoginService;
import com.mrcet.rabbit.services.impl.LoginServiceImpl;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class LoginServlet extends HttpServlet {

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
            
            
            RequestDispatcher rd = null;
            
            String userName=request.getParameter("username");
            String password=request.getParameter("password");
            
            User user=new User(userName,password);
            LoginService loginService= new LoginServiceImpl();
            boolean status = false;
            try {
                status = loginService.validateUser(user);
            } catch (SQLException ex) {
                //Log
            } catch (ClassNotFoundException ex) {
                //Log
            }
            catch(Exception e){
                //Log
            }
            
            System.out.println(status);
            if(status == true){
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("username",user.getEmail());
                rd = request.getRequestDispatcher("./generatedash");
                rd.forward(request, response);
            }
            else{
//                response.sendError(400, "Sorry, wrong username or password");
                response.sendRedirect("./jsp/error.jsp");
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