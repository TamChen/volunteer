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
    <title>CheckIn</title>
    <!-- Bootstrap core CSS -->
    <link href="style/layout/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="style/layout/dashboard.css" rel="stylesheet">
 
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script>-->
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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
	          <a class="navbar-brand"  href="#">志愿者信息平台后管理中心</a>
	        </div>
	        <div class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="setting.jsp" target="show">设置</a></li>
	            <li><a href="help.jsp" target="show">帮助</a></li>
	            <li><a href="exit">退出</a></li>
	          </ul>
	          <form class="navbar-form navbar-right" action="searchPersonDataAction" target="show" method="post"  >
	            <input type="text" name="username" id="username" class="form-control" placeholder="搜索个人">
	          </form>
	        </div>
	      </div>
	    </div>
	
	    <div class="container-fluid">
	      <div class="row">
	        <div class="col-sm-3 col-md-2 sidebar">
	         <ul class="nav nav-sidebar">
	            <li class="active"><a>文件管理</a></li>
	            <li><a href="#" target="show" onclick="setSession()">上传管理</a></li>
	            <li><a href="#" target="show">下载管理</a></li>
	          </ul>
	          <ul class="nav nav-sidebar">
	            <li class="active"><a>实验室统计信息</a></li>
	            <li><a href="#" target="show">详细信息</a></li>
	            <li><a href="#" target="show">基本情况</a></li>
	          </ul>
	       
	           <ul class="nav nav-sidebar">
	            <li class="active"><a>文件管理</a></li>
	            <li><a href="#" target="show">代码管理</a></li>
	            <li><a href="#" target="show">文本管理</a></li>
	            <li><a href="#" target="show">视频管理</a></li>
	          </ul>
	          <ul class="nav nav-sidebar">
	            <li class="active"><a>用户管理</a></li>
	            <li><a href="#" target="show">用户信息管理</a></li>
	            <li><a href="#" target="show">用户文件管理</a></li>
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
	
	    <!-- Bootstrap core JavaScript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
		<script type="text/javascript" src="javascript/jquery.js"></script>
	
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="javascript/bootstrap.min.js"></script>
	<script>
     $(document).ready(function () {
    	 var username="<%=session.getAttribute("username")%>";
    	 if(username=="null"){
	    	 $.ajax({
	    		//	url:'${pageContext.request.contextPath}/getRandomAction.action'
	    	    });
    	 }
   });
   function setSession(){
	   var username="<%=session.getAttribute("username")%>";
  	 if(username=="null"){
	    	 $.ajax({
	    	//		url:'${pageContext.request.contextPath}/getRandomAction.action'
	    	    });
  	 }
   }
     </script>

  </body>
</html>