<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>volunteer</title>
    
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
    <link href="static/style/layout/activity-list.css" rel="stylesheet">
    <link href="static/style/layout/glimpse-front-list.css" rel="stylesheet">
  </head>
  
  <body>
<jsp:include  page="../head.jsp"/>
            <div id="content">
                <div class="content-left">
                    <div class="volunteer-logo"></div>
                    <div class="volunteer-search" style="height:200px;">
                        <div class="hot-search"></div>
                        <div class="search-key">
                            <a id="Act" onclick="clickAct()">活动风采</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a id="Per" onclick="clickPer()">个人风采</a>
                        </div>
                        <div class="in-search">
                            <div class="input">
                            <p>输入关键字：</p>
                            </div>

                            <div class="input-group">
                              <input id="form" type="text" class="form-control">
                            </div>
                            <div class="search">
                            <a id="button" onclick="clickButton()"><img src="static/style/images/search.jpg"></a>
                            </div>
                        </div>
                    </div>
                    <!-- 活动推荐 -->
                    <div class="volunteer-search" style="height:170px;">
                        <div class="hot-search"></div>
                        <div class="search-key">
                            <a href="">2014年大学生广告大赛义工招募</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a href="">2014年大学生广告大赛义工招募</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a href="">2014年大学生广告大赛义工招募</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a href="">2014年大学生广告大赛义工招募</a>
                        </div>
                    </div>

                </div>
              
		<div class="content-center" id="content-center">
		<div id="searchResult">
		<div class="center_top"></div>
		<div id="picture_top">
		空空如也！ ‘(*>﹏<*)′
		
		</div>
		<div class = "changepage"  id="changpage_1"></div>
		<div class="center_buttom"></div>
		<div id= "picture_buttom">
		空空如也！ ‘(*>﹏<*)′
		</div>
		<div class = "changepage" id="changpage_2"></div>
		</div>
		</div>
       	</div>
      <jsp:include  page="../foot.jsp"/>

        <!--scripts-->
        <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="static/js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="static/lib/jquery.raty.min.js"></script>
        <!-- load index info -->
		<script type="text/javascript" src="static/js/glimpse/glimpse-front-list.js"></script>
		<script type="text/javascript" src="static/js/head.js"></script>
</body>
</html>

