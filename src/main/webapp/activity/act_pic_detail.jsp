<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
				<div class="detail_picture" style="text-align:center;">
					<div class="pic_nav">
						<span style="float:left;">第<span class="current"></span>张/共<span class="total"></span>张</span>
						<span ><a class="pre_pic" href="">上一张</a> / <a class="next_pic" href="">下一张</a></span>
						<span style="float:right;"><a href='javascript:void(0)' onclick='getAllPic()'>浏览所有照片</a></span>
					</div>
					<img class="pic_img" src="">'
					<div class="pic_intro_detail"></div>
				</div>
			</div>
			 <div class="comment" style="width:90%;margin-left:30px;">
	            <!-- 多说评论框 start js如何更改属性-->
					<div id="comment" class="ds-thread" data-thread-key="" data-title="" data-url=""></div>
				<!-- 多说评论框 end -->
	            </div>
	           <input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>
				<!-- 多说公共JS代码 start (一个网页只需插入一次)-->
				<script type="text/javascript">
				var duoshuoQuery = {short_name:"tamchen"};
					(function() {
						var ds = document.createElement('script');
						ds.type = 'text/javascript';ds.async = true;
						ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
						ds.charset = 'UTF-8';
						(document.getElementsByTagName('head')[0] 
						 || document.getElementsByTagName('body')[0]).appendChild(ds);
					})();
					</script>
				<!-- 多说公共JS代码 end -->
		</div>
	</div>
    <jsp:include  page="../foot.jsp"/>

	<!--scripts-->
	<script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="static/js/activity/pic-detail.js"></script>
	<script type="text/javascript" src="static/js/head.js"></script>
</body>
</html>
