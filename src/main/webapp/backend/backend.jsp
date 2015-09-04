<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>    
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Pragma" content="no-cache">
    <title>volunteer</title>
    <link href="static/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="static/style/layout/dashboard.css" rel="stylesheet">
  </head>
	  <body>
	    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	            <span class="sr-only"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" style="color:white" href="backend/backend.jsp">义工平台后台管理</a>
	        </div>
	        <div class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="backend/setting.html" target="show">设置</a></li>
	            <li><a href="backend/adminhelp.html" target="show">帮助</a></li>
	            <li><a href="userAction!logout.action">退出</a></li>
	          </ul>
	          <form class="navbar-form navbar-right" action="searchPersonDataAction" target="show" method="post"  >
	            <input type="text" name="username" id="username" class="form-control" placeholder="搜索个人活动">
	          </form>
	        </div>
	      </div>
	    </div>
	
	    <div class="container-fluid">
	      <div class="row">
	        <div class="col-sm-3 col-md-2 sidebar">
	          <ul class="nav nav-sidebar">
	            <li class="active"><a>新闻公告</a></li>
	            <li><a href="backend/new/NewsManage.html" target="show">新闻管理</a></li>
	            <li><a href="backend/broadcast/BroadcastManage.html" target="show">公告管理</a></li>
	          </ul>
	       
	           <ul class="nav nav-sidebar">
	            <li class="active"><a>活动管理</a></li>
	            <li><a href="backend/activity/ActivityManage.html" target="show">活动管理</a></li>
	            <li><a href="backend/glimpse/glimpseManage.html" target="show">活动风采</a></li>
	          </ul>
	           <ul class="nav nav-sidebar">
	            <li class="active"><a>文档管理</a></li>
	            <li><a href="file/fileManage.html" target="show">文档管理</a></li>
	          </ul>
	          <ul class="nav nav-sidebar">
	            <li class="active"><a>用户管理</a></li>
	            <li><a href="backend/user/UserManage.html" target="show">用户管理</a></li>
	            <li><a href="backend/log/LogManage.html" target="show">用户日志</a></li>
	          </ul>
	           <ul class="nav nav-sidebar">
	            <li class="active"><a>管理员信息</a></li>
	            <li><a href="backend/admin.html" target="show">信息修改</a></li>
	            <li><a href="index.jsp" >进入前台</a></li>
	          </ul>
	        </div>
	        
	       <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	     
	
				<div id="myModal" role="dialog" tabIndex="-1">  
					
			 		 <div class="modal-body">    
					    <iframe name="show" style="zoom: 1;" height="1200px;" src="" frameBorder="0" width="100%">
					    
					    </iframe> 
			     	 </div> 
			    
			    </div>
			
	       </div>
	       
	      </div>
	    </div>
		<script type="text/javascript" src="static/js/common/jquery.js"></script>
		<script type="text/javascript" src="static/js/common/bootstrap.min.js"></script>
  </body>
</html>