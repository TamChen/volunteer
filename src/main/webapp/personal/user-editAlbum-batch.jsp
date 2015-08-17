<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user-index.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" href="static/style/layout/reset.css" />
    <link rel="stylesheet" href="static/style/layout/index.css" />
    <link rel="stylesheet" href="static/bootstrap/bootstrap.min.css" />
        <!-- Styles --> 
    <link type="text/css" href="static/style/layout/custom-theme/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
    <link href="static/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="static/style/layout/person.css" rel="stylesheet">
    <link rel="stylesheet" href="static/jBootstrapPage/jBootsrapPage.css">

  </head>
  
  <body>
   <jsp:include  page="../head.jsp"/>
          <div id="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="location-user">
                                用户页面《当前位置
                    </div>
                </div>
            </div>
            <div class="row" >
                <div class="col-md-3">
                    <div style="width:100%;height:200px;">
                        <a  href='javascript:void(0)' onclick='index()' style="margin-left:10px";><img id="user-head" style="width:200px;height:200px" src="" /> </a>
                    </div>
                    <div id="friend">                  
                        <span id="attention_user"></span>
                        <span class="cancel_attention"></span>
                        <span class="mail_user"><a href='javascript:void(0)' onclick='mailUser()'>发私信</a></span>
                        
                    </div>
                     <div id="intro" style="">
                        <pre id="user-intro"></pre>
                        <span id="edit-intro"></span>
                    </div>
                    <!-- 修改自我简介 -->
                        <form id="input-intro" style="" name="edit_intro" mothod="post">
                            <textarea id='textarea' name="textarea" onpropertychange='setTareaAutoHeight("textarea")' oninput=setTareaAutoHeight1("textarea")></textarea><br/>
                            <input id="intro_submit" class="submit" onclick='saveUserIntro()' type="button" value="保存" />
                            <input id="intro_cancel" class="cancel" onclick='cancelIntroEdit()' type="button" value="取消" />
                        </form>
                       <div class="middle-attention" style="margin-left:10px;">
                            <div class="right-title" sytle="width:auto">
                                <div class="title" style="width:auto;"><a href='javascript:void(0)' onclick='friend()'><p><span class=user_name></span>的关注&nbsp;&nbsp;&nbsp;(<span id="friend_num"></span>人)</p></a></div>
                            </div>
                        
                            <ul id="attention">
                                
                            </ul>
                        </div>
                        <div class="middle-attention" style="margin-left:10px;">
                            <div class="right-title" sytle="width:auto;">
                                <div class="title" style="width:auto;height:80px;"><a href='javascript:void(0)' onclick='likeme()'><p><span class=user_name></span>被关注&nbsp;&nbsp;&nbsp;(<span id="attentionMe_num"></span>人)</p></a></div>
                            </div>
                        
                        </div>
                    <!-- <div style="width:100%;height:600px;background-color:green">

                    </div>-->                    
                </div>
                <div class="col-md-9">
                        <div class="col-md-10" >
                            <span id="usertitile"></span>
                            <span id="sign"></span>
                            <span id="edit_sign"></span>
                            <form id=input_sign>
                                <input id="sign_value" type="text" size="30" style="font-family: '微软雅黑'"maxlength="30" value="">
                                <input id="sign_submit" class="submit" onclick='saveUserSign()' type="button" value="修改">
                                <input id="sign_cancel" class="submit" onclick='cancelSignEdit()' type="button" value="取消">
                            </form>
                        </div>
                          <div class="col-md-11">
                            <div id="userlist">
                                <span class="index_nav"><a href='javascript:void(0)' onclick='index()'style="margin-left:0px;">主页</a></span>
                                <span class="info_nav"><a href='javascript:void(0)' onclick='info()'>个人资料</a></span>
                                <span class="attend_nav"><a id="attend" href='javascript:void(0)' onclick='attend()'>已参加活动</a></span>
                                <span class="register_nav"><a  href='javascript:void(0)' onclick='register()'>已报名活动</a></span>
                                <span class="interest_nav"><a href='javascript:void(0)' onclick='interest()'>感兴趣的活动</a></span>
                                <span class="album_nav"><a href='javascript:void(0)' onclick='picture()'>相册</a></span>
                                <span class="record"><a href='javascript:void(0)' onclick='record()'>日志</a></span>
                                <span class="mail_nav"><a href='javascript:void(0)' onclick='mail()'>私信</a></span>
                                <span class="setting_nav"><a id="setting" href='javascript:void(0)' onclick='setting()'>设置</a></span>
                            </div>
                        </div>
                        
                        <div class="col-md-11" id="attend_title" style="margin-top:40px;">
                            <a  class="sub-title" href="javascript:void(0)" onclick="turnBack()"><span class="user_name"></span>的相册><span class="alubmName"></span><span>>批量修改</span></a>
                            <a class="album_op" href="javascript:void(0)" onclick="editAlbum()">修改相册属性</a>
                            <a class="album_op" href="javascript:void(0)" onclick="addPicture()">添加照片>></a>
                            <a class="album_op" href="javascript:void(0)" onclick="editBatch()">批量修改>></a>
                        </div>
                        
                         <div class="col-md-12" id="upload_pic_show">
                       	</div>
                       	
                       	 <div class="col-md-12">
                       	  	<input id="album_submit" class="submit" onclick="cancelAlbum()" type="button" value="取消">
                        	<input id="album_cancel" class="submit" onclick='savePhoto()' type="button" value="保存">
                       	</div>
                       	
                       	 <ul style="margin-left:50%;font-family:'微软雅黑'" class="pagination" ></ul>
                        <input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>
                        <input id="size" type="hidden" value="">
                </div>
            </div>
         </div>
            <jsp:include  page="../foot.jsp"/>
                <!--scripts-->
    <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="static/js/personal/user.js"></script>
    <script type="text/javascript" src="static/js/personal/user-edit-batch.js"></script>
    <script type="text/javascript" src="static/js/head.js"></script>
    <script type="text/javascript" src="static/jBootstrapPage/jBootstrapPage.js"></script>
  </body>
</html>
