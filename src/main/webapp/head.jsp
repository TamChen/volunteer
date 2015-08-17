<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'head.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div id="header">
            <div class="fix">
            </div>
            <div class="navigation">
                <div class="navicontainer">
                    <!--学校logo-->
                    <div id="schoollogo">
                        <a><img border="0" src="static/style/images/schoollogo.png" /></a>
                    </div>

                    <ul id="nav-1"> 
                        <li></li>
                        <li><a id="index" href="index.jsp">平台首页</a></li> 
                        <li><a id="activity" href="activity/activity-list.jsp">活动大厅</a></li> 
                        <li><a id="glimpse" href="glimpse/picture-list.jsp?param=0">风采走廊</a></li> 
                        <li><a id="infonew" href="news/new-list.jsp?param=0">新闻公告</a></li> 
                        <li><a id="about" href="common/aboutUs.jsp">文档中心</a></li> 
                    </ul> 
                    <div id="login">
                        <div id="login-left">
                        	<img class="user_head_img" style="heigh=27px;width:27px;border-radius: 14px;" src="">
                        </div>
                        <div id="login-right">
                        	<a id="name" href="personal/user-index.jsp?userno=<%=session.getAttribute("userno")%>"><%=session.getAttribute("username")%>|</a>
                        	<a href="personal/user-mail.jsp?userno=<%=session.getAttribute("userno")%>"><span id="info"></span></a>
                            <a id="logout" href="userAction!logout.action">退出</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
  </body>
</html>
