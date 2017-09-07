<%-- 
    Document   : user_wall
    Created on : Jun 27, 2014, 7:12:32 PM
    Author     : Prasenjit Roy
--%>

<%@page import="java.util.List"%>
<%@page import="com.mrcet.rabbit.Beans.Tweet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            String contextPath = request.getContextPath();
            HttpSession httpSession = request.getSession(false);
            String username = (String)httpSession.getAttribute("username");
            if(httpSession == null || username == null) response.sendRedirect("../index.html");
            List<Tweet> all = (List)httpSession.getAttribute("allTweets");
            List<Tweet> his = (List)httpSession.getAttribute("userTweets");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="60">
        <link rel="stylesheet" href="<% out.println(contextPath);%>/static/css/dashboard-old.css"/>
        <title><%out.println(username);%>'s|Wall</title>
    </head>
    <body background="${pageContext.request.contextPath}/static/images/second.jpg">
        <div id="left-coloumn" style="">
            <form action="<% out.println(contextPath);%>/tweet" method="post">
                <div id="f-submit">
                    <textarea name="tweet" id="styleid" maxlength="150" placeholder="Start typing here ... "></textarea>
                </div>

                <div>
                    <button id="submit" type="submit">Submit</button>
                    <!--<a href="dashboard-old.jsp"> <button id="f-refresh" type="submit">Refresh</button></a>-->
                </div>    
            </form>     
            <hr width="75%">
            <table>
                <tr><th align="center">Your tweets ...</th></tr>
                
                <% for(Tweet tweet: his){%>
                <tr>
                    <td style="font-family:Verdana"  align="center">
                        <%  
                            out.print(tweet.getTweet());
                        %>
                    </td>
                </tr>
                <%}%>
                
            </table>
            
        </div>
        
    <div id="load_me" >
        <table WIDTH="550" CELLPADDING=9 CELLSPACING=6 BORDER=0 >
        <tr><th style="font-family:Verdana"  align="center">Tweets in your Locality</th></tr>
            <%for(Tweet tweet: all){%>
                <tr>
                    <td style="font-family:Verdana"  align="center">
                     <%  out.print(tweet.getTweet() + " by <i> " + tweet.getUser().getEmail() + " </i>!! <br/> <hr style=\"width:80&#37;; \">");%>
                    </td>
                </tr>
            <%}%>
        </table>        
    </div>
            <div id="footer">
                <form action="<%out.print(contextPath);%>/logout" method="post">
                    <div>
                        <button id="submit" type="submit">
                            Log Out
                        </button>
                    </div>
                </form>
            </div>
    </body>
</html>
