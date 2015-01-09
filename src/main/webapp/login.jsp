<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<META   HTTP-EQUIV="pragma"   CONTENT="no-cache">   
    <meta http-equiv="Pragma" content="no-cache"">   
    <meta http-equiv="Expires" content="Mon,12 May 2001 00:20:00 GMT">
	<link rel="stylesheet" type="text/css" href="style/layout/login.css">
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
  <img alt="" src="style/images/bg.jpg"/>
  </div>
  
   <!-- 
    <form method="post" action="login" >
         UserName<input type="text" name="name" id="name"/><br/>
         Password<input type="password" name="password" id="password"/><br/>
     <input type="submit" value="login" /></form>
     -->
      <!-- 
     <form id="login" method="post" action="login">
  -->
      <form id="login" action="manageLogin" method="post">
    <h1>管理员登录</h1>

    <fieldset id="inputs" >

        <input id="name" name="name" type="text" placeholder="用户名" autofocus required>   

        <input id="password" name="password" type="password" placeholder="密码" required>

    </fieldset>

    <fieldset id="actions">

        <input type="submit" id="submit"  value="登  录"/>

        <a href="">忘记密码?</a><a href="">注册</a>

    </fieldset>
<!--  
    <a href="http://www.sharejs.com/subject/1599" id="back">Back to article...</a>
-->
</form>
<!-- BSA AdPacks code -->

	<script src="javascript/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	function login(){
		var name=$("#name").attr("value");
		var password=$("#password").attr("value");
		alert(name);
		alert(password);
		$.ajax({
			url:'${pageContext.request.contextPath}/login.action'
		});
	}
	

	</script>
  </body>
</html>
