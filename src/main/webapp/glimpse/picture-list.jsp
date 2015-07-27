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
			<div class="volunteer-search" style="height:200px;">
				<div class="hot-search"></div>
				<div class="search-key">
					<a href="glimpse/picture-list.jsp?param=0">活动风采</a>
				</div>
				<hr>
				<div class="search-key">
					<a href="glimpse/picture-list.jsp?param=1">个人风采</a>
				</div>
				<div class="in-search">
					<div class="input">
						<p>输入关键字：</p>
					</div>
					<div class="input-group">
						<input type="text" class="form-control" id="input">
					</div>
					<div class="search">
						 <a href="javascript:void(0)" onclick="searchPicture()"><img src="static/style/images/search.jpg"></a>
					</div>
				</div>
			</div>
			
				<div class="excellent-host">
                            <div class="right-title">
                                <div class="title"><p>活跃举办方</p></div>
                                <div class="pre-photo">
                                    <a href="javascript:void(0)" onclick="loadHostPre()"><img src="static/style/images/pre.jpg"></a>
                                </div>
                                <div class="next-photo">
                                    <a id="exhost" href="javascript:void(0)" onclick="loadHostNext()"><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <!-- jquery分页 -->
                            <ul style="height:auto;" id="excellent-photo">
                            </ul>
                  </div>
             <div class="excellent-host">
                            <div class="right-title">
                                <div class="title"><p>活跃个人</p></div>
                                <div class="pre-photo">
                                    <a href="javascript:void(0)" onclick="loadUserPre()"><img src="static/style/images/pre.jpg"></a>
                                </div>
                                <div class="next-photo">
                                    <a id="exUser" href="javascript:void(0)" onclick="loadUserNext()"><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <!-- jquery分页 -->
                            <ul style="height:auto;"  id="excellent-personal">
                            </ul>
             </div>   
		</div>

		<div class="content-minddle">
			<div class="location">
				活动照片《当前位置
			</div>
			<div class="middle-top" style="text-align:left;">
				<div style="margin-left:30px;font-family:'微软雅黑';font-size:17px;"><span class="pic_list_title"></span></div>
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
	<script type="text/javascript" src="static/js/glimpse/picture_list.js"></script>
	<script type="text/javascript" src="static/js/head.js"></script>
	<script type="text/javascript" src="static/jBootstrapPage/jBootstrapPage.js"></script>
</body>
</html>
