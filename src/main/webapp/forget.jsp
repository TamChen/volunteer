<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>volunteer</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Site Theme Styling -->
    <link rel="stylesheet" href="static/style/layout/reset.css" />
    <link rel="stylesheet" href="static/style/layout/index.css" />
    
	<link type="text/css" href="static/style/layout/lrtk.css" rel="stylesheet" />

	
    <!-- Styles --> 
    <link type="text/css" href="static/style/layout/custom-theme/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
    <link href="static/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="static/style/layout/demo.css" rel="stylesheet">
    <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
	<style type="text/css">
		a { color:#213f71; font-size:9pt; text-decoration:none;}
		a:hover {color:#0066cc; }
	</style>
  </head>
  
  <body>

      <jsp:include  page="head.jsp"/>
            <div id="content" style="height:1000px;">
           	<div style="margin:0 auto;width:400px;height:400px;margin-top:100px;">
           	<p style="font-size:24px;font-family:微软雅黑;text-align:left;">找回密码</p>
			<span style="font-size:16px;font-family:微软雅黑;">学号：</span><input style="margin-top:30px;" id="userno_1" name="userno" type="text" placeholder="请输入学号" autofocus required> 
			<br>
			<a href='javascript:void(0)' onclick='submit()' style="text-align:left;margin-top:30px;"><button  class="btn btn-primary" style="margin-left:35%;margin-top:30px;font-family:微软雅黑" type="button">提交</button></a>
           	</div>
            
            </div>
            </div>
        <jsp:include  page="foot.jsp"/>
        <script>
        	var height=window.screen.height;
        	var trueheight=height-420;
        	$("#content").css("height",trueheight);
			function submit(){
				var userno=$("#userno_1").val();
				$.ajax({
					type : "POST",
					dataType : "json",
					asyn:false,
					contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			    	url:'userAction!forgetPass.action?userno='+userno,
			    	success:function(result){
			    		alert(result.msg);
			    		window.location = "login.jsp";
			 		}
			    });
			}        	
        </script>
		<input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>

        <!--scripts-->
        
        <script type="text/javascript" src="static/js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="static/lib/jquery.raty.min.js"></script>
        <!-- load index info --><%--
        <script type="text/javascript" src="static/js/index.js"></script>
        --%><script type="text/javascript" src="static/js/head.js"></script>
    

    	<script type="text/javascript" src="static/js/lrtk.js"></script>
     

</body>
</html>
