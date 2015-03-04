<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    <link href="static/style/layout/demo.css" rel="stylesheet">
    <link href="static/style/layout/activity-list.css" rel="stylesheet">
    
	<style type="text/css">
		a { color:#213f71; font-size:9pt; text-decoration:none;}
		a:hover {color:#0066cc; }
	</style>
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
            <div id="content">
                <div class="content-left">
                    <div class="volunteer-logo"></div>
                    <div class="volunteer-search" style="height:200px;">
                        <div class="hot-search"></div>
                        <div class="search-key">
                            <a href="">通知公告</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a href="">站内新闻</a>
                        </div>
                        <div class="in-search">
                            <div class="input">
                            <p>输入关键字：</p>
                            </div>

                            <div class="input-group">
                              <input type="text" class="form-control">
                            </div>
                            <div class="search">
                            <a href=""><img src="static/style/images/search.jpg"></a>
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
                    <div class="excellent-organizers">

                    <h4 class="org">优秀主办方</h4>
                    <div class="excellent">
                        <div class="images">
                            <img src="static/style/images/organizer1.png" >
                        </div>
                        <div class="content">
                            <a>***学院</a>
                            <p>有*个活动正在进行</p>
                        </div>
                    </div>
                    <div class="excellent">
                        <div class="images">
                            <img src="static/style/images/organizer1.png" >
                        </div>
                        <div class="content">
                            <a>***学院</a>
                            <p>有*个活动正在进行</p>
                        </div>
                    </div>
                    <div class="excellent">
                        <div class="images">
                            <img src="static/style/images/organizer1.png" >
                        </div>
                        <div class="content">
                            <a>***学院</a>
                            <p>有*个活动正在进行</p>
                        </div>
                    </div>
                    <div class="excellent">
                        <div class="images">
                            <img src="static/style/images/organizer1.png" >
                        </div>
                        <div class="content">
                            <a>***学院</a>
                            <p>有*个活动正在进行</p>
                        </div>
                    </div>
                    
                </div>
                </div>
                <div class="content-minddle">
                	<div class="middle-top">
                <br><br>
                <h2>义工活动招募集中营</h2><br>
                <ul class="classify">
                        <li><b>分类：</b></li>
                        <li><a href="#">综合排名&nbsp;</a></li>
                        <li><a href="#">最新发布&nbsp;</a></li>
                        <li><a href="#">学院发布&nbsp;</a></li>
                        <li><a href="#">人气排名</a></li>
                </ul>
                <ul class="classify" style="margin-top:5px;">
                        <li>时间：</li>
                        <li><a href="#">全部&nbsp;</a></li>
                        <li><a href="#">今天&nbsp;</a></li>
                        <li><a href="#">明天&nbsp;</a></li>
                        <li><a href="#">周末&nbsp;</a></li>
                        <li><a href="#">最近一周&nbsp;</a></li>
                        <li><a href="#">选择日期</a></li>
                </ul>
                <hr class="middle">
                </div>
            <div class="middle-up">
              	<div class="activity">
                	<div class="activity-logo"> 
                		<img src="static/style/images/activity.png" class="act-logo">
                	</div>
                    <div class="information">
	                    <h2 class="activity-name">2014年大学生广告大赛义工招募</h2>
	                    <ul class="detailed-information" >
	                        <li>发起: </li>
	                        <li>时间： </li>
	                        <li>地点： </li>
	                        <li>招募人数：</li>
	                        <li class="check"> 已报名人数： | 感兴趣人数：</li>
	                     </ul>
                	 </div>
	                 <div class="share">
	                     <b>分享到 </b>
	                     <a href="#"><img src="static/style/images/renren.png "></a>
	                     <a href="#"><img src="static/style/images/qq.png "></a>
	                     <a href="#"><img src="static/style/images/qqweibo.png "></a>
	                     <a href="#"> <img src="static/style/images/xinlangweibo.png"></a>
	                 </div>
           		 </div>
             </div>
		</div>
       	</div>
            <div class="clean"></div>
       
        <div id="footer">
            <div class="container">
                <br>
                <p>长沙理工大学义工平台版权所有&nbsp;&nbsp;技术支持：梦之站</p>
                <p>主校区地址：长沙市（雨花区万家丽南路2段960号&nbsp;邮箱：410114）</p>
                <p>ICP证号：湘ICP备0500000</p>
            </div>
        </div>

        <!--scripts-->
        <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="static/js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="static/lib/jquery.raty.min.js"></script>
        <!-- load index info -->
        <script type="text/javascript" src="static/js/index.js"></script>


</body>
</html>
