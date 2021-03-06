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
				活动详情《当前位置
			</div>
			<div class="middle-up">
				<div class="activity">
					<div class="activity-logo">
						<img style="width:110px;height:165px;" class="act_logo" src="" class="act-logo">
					</div>
					<div class="information">
						<div class="activity-name"><a id="act_title" style="font-size:16px;"></a></div>
						<ul class="detailed-information">
							<li id="adminname"></li>
							<li id="act_time">时间：12月13日 8:00-12:00</li>
							<li id="act_address"></li>
							<li id="act_number"></li>
							<li class="check" id="act_detail"></li>
						</ul>
					</div>
					
					<!-- 分享 -->
					<div class="share">
						<div class="bdsharebuttonbox">
		                       <a href="#" class="bds_more" data-cmd="more"></a>
		                       <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
		                       <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
		                       <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
		                       <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
		                       <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
	                       	</div>
	                 </div>
	                 
				</div>
				<div id="attend">
	            </div><%--
	            <div style="width:160px;height:32px;background-color:#F54D80;font-family:'微软雅黑';margin-left:300px;font-size:12px;color:white">取消报名</div>
	            --%><div class="detail-activity-img">
	            </div>
	            <div class="detail-activity-content">
		             <pre id="show_content_1"> </pre>
		             <div id="more"><a href='javascript:void(0)' onclick="getMore()">展开</a></div>
		             <div id="short"><a href='javascript:void(0)' onclick="getShort()">收起</a></div>
	            </div>
	            <div class="mod">
				    <div class="pic_title">活动照片
				        <span class="pl"><a href=""></a> </span>
				    </div>
				    <ul class="detail-photo">
				   
				    </ul>
				</div>
	            <div class="comment">
	            <!-- 多说评论框 start js如何更改属性-->
				<!-- 	<div id="comment" class="ds-thread" data-thread-key="1" data-title="hellword" data-url="http://localhost:8080/volunteer/activity/activity-detail.jsp"></div>
				 -->
				<!-- 多说评论框 end -->
	            </div>
	           
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
	            <input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>
			</div>
		</div>
	</div>
	  <jsp:include  page="../foot.jsp"/>

	<!--scripts-->
	<script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="static/js/activity/activity-detail.js"></script>
	<script type="text/javascript" src="static/js/head.js"></script>
	<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>
