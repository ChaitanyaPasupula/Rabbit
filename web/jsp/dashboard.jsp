<%-- 
    Document   : dashboard
    Created on : 23 Apr, 2016, 3:59:50 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%
            String contextPath = request.getContextPath();
            HttpSession httpSession = request.getSession(false);
            String username = (String)httpSession.getAttribute("username");
            if(httpSession == null || username == null) response.sendRedirect("../index.html");
            out.println(username);
            out.println(httpSession.getAttribute("allTweets"));
            out.println(httpSession.getAttribute("userTweets"));
        %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%out.print(username);%>'s Dashboard</title>
        <link href="<%out.print(contextPath);%>/static/css/style.css" rel="stylesheet" type="text/css" media="all"/>
        <!-- Custom Theme files -->
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
        <link href='//fonts.googleapis.com/css?family=Source+Sans+Pro:400,200,300,600,700,900' rel='stylesheet' type='text/css'>
        <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    </head>
    <body>
        <div class="news-blockx">
            <div class="news-left">
			<div class="news-1">
				<div class="news-text1">
					<h2>News</h2>
				</div>
				<div class="reaches">
					<h6>Archive</h6>
				</div>
			   <div class="clear"> </div>
			</div>
			<h3><a href="#">At vero eos et accusamus</a></h3>
			<p>There anyone who loves or pursues or desires to obtain pain of itself, because it is pain.</p>
			<div class="news-btn-1"><a class="popup-with-zoom-anim" href="#small-dialog">More</a></div>
		</div>
        </div>
        
        <div class="news-blocky">
        <div class="news-block-bottom">
		<div class="buzz-news">
			<div class="news-1">
				<div class="news-text1">
					<h2>News</h2>
				</div>
				<div class="reaches">
					<h6>Archive</h6>
				</div>
			   <div class="clear"> </div>
			</div>
			<h3><a href="#">At vero eos et accusamus</a></h3>
			<p>There anyone who loves or pursues or desires to obtain pain of itself, because it is pain.</p>
<!--			<div class="news-btn-3"><a class="popup-with-zoom-anim" href="#small-dialog">More</a></div>-->
		</div>
<!--		<div class="buzz-img">
			<a href="#"><img src="images/mobile.jpg" class="img-responsive" alt=""></a>
		</div>-->
            <div class="clear"> </div>
            </div>
    </body>
</html>
