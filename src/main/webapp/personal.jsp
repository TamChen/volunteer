<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'personal.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<!-- Site Theme Styling -->
    <link rel="stylesheet" href="static/style/layout/reset.css" />
    <link rel="stylesheet" href="static/style/layout/index.css" />

    <!-- Styles --> 
    <link type="text/css" href="static/style/layout/custom-theme/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
    <link href="static/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="static/style/layout/demo.css" rel="stylesheet">
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
                        <li><a href="index.jsp">平台首页</a></li> 
                        <li><a href="activity/activity-list.jsp">活动大厅</a></li> 
                        <li><a href="glimpse/glimpse-list.jsp">风采走廊</a></li> 
                        <li><a href="#">精彩走廊</a></li> 
                        <li><a href="#">关于我们</a></li> 
                    </ul> 
                    <!-- 这里私信数的多少用jQuery实现 -->
                    <%--=session.getAttribute("userno")--%>
                    <div id="login">
                        <div id="login-left">
                        	<img alt="" src="static/style/images/photo.png">
                        </div>
                        <div id="login-right">
                        	<a id="name" href="#">裴云庆|消息</a>
                            <a id="logout" href="userAction!logout.action">退出登入</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<div style="width:1000px;height:400px;">
		
		
		</div>        
        <div id="footer">
            <div class="container">
                <br>
                <p>长沙理工大学义工平台版权所有&nbsp;&nbsp;技术支持：梦之站</p>
                <p>主校区地址：长沙市（雨花区万家丽南路2段960号&nbsp;邮箱：410114）</p>
                <p>ICP证号：湘ICP备0500000</p>
            </div>
        </div>
      </body>
</html>
