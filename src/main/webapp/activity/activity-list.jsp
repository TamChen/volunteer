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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
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
	<link href="static/style/layout/bootstrap.min.css" rel="stylesheet">
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
					<a href="javascript:void(0)" onclick="searchMuti(0)">综合排名</a>
				</div>
				<hr>
				<div class="search-key">
					<a href="javascript:void(0)" onclick="searchMuti(1)">最新发布</a>
				</div>
				<hr>
				<div class="search-key">
					<a href="javascript:void(0)" onclick="searchMuti(2)">人气排名</a>
				</div>
				<div class="in-search">
					<div class="input">
						<p>输入关键字：</p>
					</div>

					<div class="input-group">
						<input id="input" type="text" class="form-control">
					</div>
					<div class="search">
						<a href="javascript:void(0)" onclick="searchActivity()"><img src="static/style/images/search.jpg">
						</a>
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
			活动列表《当前位置
		</div>
			<div class="middle-top" style="text-align:left;">
				
				<div style="margin-left:30px;font-family:'微软雅黑';font-size:17px;">义工活动招募大厅</div>
				<ul class="classify" style="margin-top:24px;">
					<li>
					<li><b>分类：</b>
					</li>
					<li><a href="javascript:void(0)" onclick='searchMuti(0)'>综合排名&nbsp;&nbsp;</a>
					</li>
					<li><a href="javascript:void(0)" onclick='searchMuti(1)'>最新发布&nbsp;&nbsp;</a>
					</li>
					<%--<li><a href="#">学院发布&nbsp;&nbsp;</a>
					</li>
					--%><li><a href="javascript:void(0)" onclick='searchMuti(2)'>人气排名</a>
					</li>
				</ul>
				<ul class="classify">
					<li>
					<li>时间：</li>
					<li><a href="javascript:void(0)" onclick='searchMuti(0)'>全部&nbsp;&nbsp;</a>
					</li>
					<li><a href="javascript:void(0)" onclick='searchMuti(3)'>今天&nbsp;&nbsp;</a>
					</li>
					<li><a href="javascript:void(0)" onclick='searchMuti(4)'>明天&nbsp;&nbsp;</a>
					</li>
					<li><a href="javascript:void(0)" onclick='searchMuti(5)'>周末&nbsp;&nbsp;</a>
					</li>
					<li><a href="javascript:void(0)" onclick='searchMuti(6)'>最近一周&nbsp;&nbsp;</a>
					</li>
					<%--<li><a href="#">选择日期</a>
					</li>
				--%></ul>
				<hr class="middle">
			</div>
			<div class="middle-up" id="middle-activity">
			</div>
			<ul class="pagination" id="pagination1" 
			style="font-family:'\5FAE\8F6F\96C5\9ED1';margin-left:450px;margin-top:20px;";></ul>
		</div>
	<input id="total" type="hidden" value=<%=session.getAttribute("totalpage")%>>
	 </div>
<input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>
	 <jsp:include  page="../foot.jsp"/>
	<!--scripts-->
	<script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="static/js/activity/activity.js"></script>
	<script type="text/javascript" src="static/js/jqPaginator.js"></script>
	<script type="text/javascript" src="static/js/head.js"></script>
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1" charset="utf-8"></script>
</body>
</html>
