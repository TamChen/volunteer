<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>activity-detail</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- Site Theme Styling -->
<link rel="stylesheet" href="static/style/layout/reset.css" />
<link rel="stylesheet" href="static/style/layout/index.css" />
<!-- Styles -->
<link type="text/css"
	href="static/style/layout/custom-theme/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" />
<link href="static/bootstrap/bootstrap.css" rel="stylesheet">
<link href="static/style/layout/demo.css" rel="stylesheet">
<link href="static/style/layout/activity-list.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="static/jBootstrapPage/jBootsrapPage.css">
</head>
<body>
  <body>
  <jsp:include  page="../head.jsp"/>
	<div id="content">
		<div class="content-left">
			<div class="volunteer-logo"></div>
			<div class="volunteer-search" style="height:230px;">
				<div class="hot-search"></div>
				<div class="search-key">
					<a href="activity/activity-list.jsp?parms=0">综合排名</a>
				</div>
				<hr>
				<div class="search-key">
					<a href="activity/activity-list.jsp?parms=1">最新发布</a>
				</div>
				<hr>
				<div class="search-key">
					<a href="activity/activity-list.jsp?parms=2">人气排名</a>
				</div>
				<div class="in-search">
					<div class="input">
						<p>输入关键字：</p>
					</div>

					<div class="input-group">
						<input type="text" class="form-control" id="input">
					</div>
					<div class="search">
						 <a href="javascript:void(0)" onclick="searchActivity()"><img src="static/style/images/search.jpg"></a>
					</div>
				</div>
			</div>
			
				<div class="excellent-host">
                            <div class="right-title">
                                <div class="title"><p>活动组织者</p></div>
                                <div class="pre-photo">
                                    <a href=""><img src="static/style/images/pre.jpg"></a>
                                </div>
                                <div class="next-photo">
                                    <a href=""><img src="static/style/images/next.jpg"></a>
                                </div>
                                
                            </div>
                            <hr size="1" />
                            <!-- jquery分页 -->
                            <ul id="excellent-host"  style="height:auto;"><%--
                                <li class="hostadmin"><a href="#"><img src="static/style/img/head.jpg"></a></li>
                            --%></ul>
                  </div>
                  <div class="excellent-host">
                            <div class="right-title">
                                <div class="title"><p>活动成员</p></div>
                                <div class="pre-photo">
                                    <a href=""><img src="static/style/images/pre.jpg"></a>
                                </div>
                                <div class="next-photo">
                                    <a id="nextUser"href='javascript:void(0)' onclick=''><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <!-- jquery分页 -->
                            <ul id="excellent-user"><%--
                                <li class="host"><a href="#"><img src="static/style/img/head.jpg"></a></li>
                             
                            	
                            --%></ul>
                  </div>
		</div>

		<div class="content-minddle">
			<div class="location">
				活动照片《当前位置
			</div>
			<div class="middle-top" style="text-align:left;">
				<div style="margin-left:30px;font-family:'微软雅黑';font-size:17px;"><span class="activityName"></span>的活动照片</div>
				<div class="activity_pic_list"><%--
					<div class="item_pic">
						<a href="#"><img class="imgstyle" src="image/activity/test.jpg"></a>
						<div class="pic_intro">哈哈哈哈哈哈</div>
					</div>
					<div class="item_pic">
						<img class="imgstyle" src="image/activity/test1.jpg">
						<div class="pic_intro">哈哈哈哈哈哈</div>
					</div>
					<div class="item_pic">
						<img class="imgstyle" src="image/activity/picture.jpg">
						<div class="pic_intro">哈哈哈哈哈哈</div>
					</div>
					<div class="item_pic">
						<img class="imgstyle" src="image/activity/test.jpg">
						<div class="pic_intro">哈哈哈哈哈哈</div>
					</div>
					<div class="item_pic">
						<img class="imgstyle" src="image/activity/test1.jpg">
						<div class="pic_intro">哈哈哈哈哈哈</div>
					</div>
					<div class="item_pic">
						<img class="imgstyle" src="image/activity/picture.jpg">
						<div class="pic_intro">哈哈哈哈哈哈</div>
					</div>
				--%></div>
	            <ul style="margin-left:50%;font-family:'微软雅黑'" class="pagination" ></ul>
			</div>
		</div>
	</div>
	<input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>
    <jsp:include  page="../foot.jsp"/>

	<!--scripts-->
	<script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="static/js/activity/activity-pic.js"></script>
	<script type="text/javascript" src="static/js/head.js"></script>
	<script type="text/javascript" src="static/jBootstrapPage/jBootstrapPage.js"></script>
</body>
</html>
