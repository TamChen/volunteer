<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<META   HTTP-EQUIV="pragma"   CONTENT="no-cache">   
    <meta http-equiv="Pragma" content="no-cache">   
    <meta http-equiv="Expires" content="Mon,12 May 2001 00:20:00 GMT">
	<link rel="stylesheet" type="text/css" href="static/style/layout/login.css">
	<style type="text/css">
	body{
		height:100%;
		width:100%;
		margin:0;
	 } 
	 .cover
        {
            width: 100%;
            height: 100%;
            position: fixed;
            z-index: -10;
            _position: absolute;
            _top: expression(eval(document.body.scrollTop));
            _left: expression(eval(document.body.scrollLeft));
        }
	.cover img
         {
          width:100%;
          height:100%;
          border:0;
          }
	</style>
  </head>
  
  <body>
  <div class="cover">
  <img alt="" src="static/style/images/bg.jpg"/>
  </div>

  <form id="login" action="userAction!login.action" method="post">
    <h1>义工平台登录</h1>
    <fieldset id="inputs">

        <input id="name" name="userno" type="text" placeholder="账号" autofocus required>   

        <input id="password" name="password" type="password" placeholder="密码" required>

    </fieldset>

    <fieldset id="actions">

        <input type="submit" id="submit"  value="登  录"/>

        <a href="">忘记密码?</a>

    </fieldset>

</form>
<!-- BSA AdPacks code -->

	<script src="js/jquery-1.6.2.min.js"></script>
  </body>
</html>
