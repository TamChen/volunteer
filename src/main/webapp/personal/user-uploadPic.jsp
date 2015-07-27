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
      <link href="static/cropper/cropper.css" rel="stylesheet">
  <link href="static/cropper/main.css" rel="stylesheet">

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
                        
                                <div class="col-md-9" style="margin-top:40px;">
                            <span class="sub-title"><span class="user_name"></span>的设置>更改头像</span>
                        </div>
                           <div class="row">
                           		<div class="col-md-9">
								      <div class="col-md-9">
								        <h3 class="page-header" style="font-family:'微软雅黑'">更改头像：</h3>
								      	<div class="img-container" id="activityimg">
								     
								        </div> 
								        
								      </div>
								       <div class="col-md-3">
								       	<div style="width:100%;margin-top:150px;margin-left:-20px;font-family:'微软雅黑';height:400px;">
								       	<div><p>从电脑中选择你喜欢的照片:
										你可以上传JPG、JPEG、GIF、PNG或BMP文件。<br>图片像素大小最好大于200X200</br></p></div>
										<input type="file" id="fileUpload"  onchange="ajaxFileUpload()"/>
								       </div>
								       </div>
								       <div class="col-md-11" style="width:100%;height:40px;">
								       	<div style="float:right;width:200px;height:40px;">
								        		<button class="btn btn-primary" style="width:60px;height:30px;" type="button" onclick="uploadCropperPic()">保存</button>
												<button class="btn btn-primary" style="margin-left:20px;width:60px;height:30px;" type="button" onclick="cancel()" >取消</button>
								        </div>
								       </div>
							      </div>
							      
							      <div class="col-md-3">
							        <h3 class="page-header" style="font-family:'微软雅黑'">预览:</h3>
							        <div class="docs-preview clearfix">
							          <div class="img-preview preview-lg"></div>
							          <div class="img-preview preview-md"></div>
							        </div>
									
							        <h3 class="page-header" style="font-family:'微软雅黑'">数据:</h3>
							        <div class="docs-data">
							          <div class="input-group">
							            <label class="input-group-addon" for="dataX">X</label>
							            <input class="form-control" id="dataX" type="text" placeholder="x">
							            <span class="input-group-addon">px</span>
							          </div>
							          <div class="input-group">
							            <label class="input-group-addon" for="dataY">Y</label>
							            <input class="form-control" id="dataY" type="text" placeholder="y">
							            <span class="input-group-addon">px</span>
							          </div>
							          <div class="input-group">
							            <label class="input-group-addon" for="dataWidth">宽度</label>
							            <input class="form-control" id="dataWidth" type="text" placeholder="width">
							            <span class="input-group-addon">px</span>
							          </div>
							          <div class="input-group">
							            <label class="input-group-addon" for="dataHeight">高度</label>
							            <input class="form-control" id="dataHeight" type="text" placeholder="height">
							            <span class="input-group-addon">px</span>
							          </div>
							        </div>
							      </div>
							     
							    </div><%-- 
                      
                  		<form >
                           	<input id="submit" style="float:right;margin-top:10px;margin-right:10px;width:60px;height:30px;" class="submit" onclick='cancel()' type="button" value="取消">
                           	<input id="cancel" style="float:right;margin-top:10px;margin-right:10px;width:60px;height:30px;" class="submit" onclick='updateUserInfo()' type="button" value="修改">
                         </form>
         
               	--%><input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>
                <!-- 多说公共JS代码 start (一个网页只需插入一次)-->
         
                        
                  
                </div>
            </div>
         </div>
            <jsp:include  page="../foot.jsp"/>
            	<!--scripts-->
	<script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="static/js/personal/user.js"></script>
	<script type="text/javascript" src="static/cropper/cropper-userhead.js"></script>
    <script type="text/javascript" src="static/cropper/main-userhead.js"></script>
	<script type="text/javascript" src="static/js/personal/user-edit-pic.js"></script>
	<script type="text/javascript" src="static/js/head.js"></script>

  </body>
</html>
