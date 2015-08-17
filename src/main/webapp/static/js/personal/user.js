 //用户首页
//获得参数//屏幕的分辨率问题如何解决，现在的方法是，当遇到过大的分辨率的时候，底部自动变为abosulte；
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}  
function updateUserInfo(){
	var userno=GetQueryString("userno");//这个是通过url传过来的
	var radio=$("input[name='sex_set']:checked").val();
	var bir=$(".bir_set").val();
	var home=$(".home_set").val();
	var mail=$(".mail_set").val();
	var resume=$(".resume_set").val();
	$.ajax({
	 	type : "POST",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
    	url:'userAction!saveUserInfo.action?sex='+radio+'&&bir='+bir+'&&home='+home+'&&mail='+mail+'&&resume='+resume,
    	success:function(result){
    		top.window.location = "personal/user-index.jsp?userno="+userno;
 		}
	});
}
function cancel(){
	var userno=GetQueryString("userno");//这个是通过url传过来的
	windtop.window.location = "personal/user-index.jsp?userno="+userno;
}
function changeHead(){
	var userno=GetQueryString("userno");//这个是通过url传过来的
	var path=$(".head_set").attr("src");
	top.window.location = "personal/user-uploadPic.jsp?userno="+userno+"&&path="+path;
}
//页面加载时执行
var friend_num;
var attentionMe_num;
function initialize(){
	var userno=GetQueryString("userno");//这个是通过url传过来的
	var param=GetQueryString("param");//这个说明是什么类型，参加，报名，感兴趣
	$(".index_nav").css("display","inline-block");
	$(".info_nav").css("display","inline-block");
	$(".attend_nav").css("display","inline-block");
	$(".interest_nav").css("display","inline-block");
	$(".album_nav").css("display","inline-block");
	 //加载个人信息
	 $.ajax({
		 	type : "POST",
			dataType : "json",
			asyn:false,
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
	    	url:'userAction!getUserDetail.action?userno='+userno,
	    	success:function(result){
	    		$("#user-head").attr("src",result.user.userHead);
		    	$("#user-intro").html(result.user.userIntro);
		    	$("#usertitile").html(result.user.username);
		    	$(".user_name").html(result.user.username);
		    	//===================================================================
		    	//这里是加载个人中心的信息；
		    	$("#userno_info").html(result.user.userno);
		    	$("#college_info").html(result.user.userCollege);
		    	$("#major_info").html(result.user.userMajor);
		    	$("#sex_info").html(getSex(result.user.userSex));
		    	$(".time_info").html(result.user.workTime);
		    	$("#userBir_info").html(result.user.userBir);
		    	$("#userAddress_info").html(result.user.userAddress);
		    	$("#userMail_info").html(result.user.userMail);
		    	$("#short_userinfo").html(result.user.resume);
		    	$("#sign").html(result.user.sign);
		    	//设置中心加载
		    	$(".userno_set").html(result.user.userno);
		    	$(".college_set").html(result.user.userCollege);
		    	$(".major_set").html(result.user.userMajor);
		    	$(".sex_set").val(getSex(result.user.userSex));
		    	$(".time_info").val(result.user.workTime);
		    	$("#bir_set").val(result.user.userBir);
		    	$(".home_set").val(result.user.userAddress);
		    	$(".mail_set").val(result.user.userMail);
		    	$(".resume_set").html(result.user.resume);
		    	$(".head_set").attr("src",result.user.userHead);
		    	
		    	setStatu(result.user.sign,result.user.userIntro);
	    		//这里是用户关注页面的内容--标题
	    		if(GetQueryString("param")==1)
	    		$("#user_attention_titile").html(result.user.username+"关注的人");
	    		else $("#user_attention_titile").html("关注"+result.user.username+"的人");
	    		//这里是首页的内容--标题
	    		if(userno.length<12){
	    			$("#act_type_attend").html(result.user.username+"发布的活动");
	    			$("#act_type_interest").html(result.user.username+"感兴趣的活动");
	    		}else{
	    			$("#act_type_attend").html(result.user.username+"要参加的活动");
	    			$("#act_type_interest").html(result.user.username+"感兴趣的活动");
	    			$("#act_type_register").html(result.user.username+"报名的活动");
	    		}
	    		//这里是活动页面的内容---标题
	    		if (userno.length<12) {
	    			if (param==2) {
	    				$("#act_user_title").html(result.user.username+"发布的活动");
	    			}else{
	    				$("#act_user_title").html(result.user.username+"感兴趣的活动");
	    			}
	    		}else{
	    			if(param==2){
	    			$("#act_user_title").html(result.user.username+"要参加的活动");
		    		}else if(param==0){
		    			$("#act_user_title").html(result.user.username+"感兴趣的活动");
		    		}else{
		    			$("#act_user_title").html(result.user.username+"报名的活动");
		    		}
	    		}
	 		}
	    });
	 //加载我关注的人
	 $("#attention").html("");
	 $.ajax({
		 	type : "POST",
			dataType : "json",
			asyn:false,
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
	    	url:'attentionAction!getUserFriend.action?param=0&&userno='+userno,
	    	success:function(result){
	    		$("#friend_num").html(result.friendNumber);friend_num=result.friendNumber;
	    		$("#attentionMe_num").html(result.attentionMeNum);attentionMe_num=result.attentionMeNum;
	    		$.each(result.friendPicList, function(i, item) {
	    			$("#attention").append("<li><a href='personal/user-index.jsp?userno="+item.url+"'>"+
	    				"<img style='width:50px;height:50px'src='"+
	    				item.picPath+"'></a></li>");
	    		});
	 		}
	    });
 }
function getSex(sex){
	if(sex=true)return "男";
	else return "女";
}
//设置签名和介绍的状态
function setStatu(sign,userIntro){
	var userno=$("#userno").val();//这个是登陆用户的userno
	var user=GetQueryString("userno");//根据URL中的userno看是否是自己
	//管理员应该没有日志，可以有相册；？？？？？？？？
	if(user.length<12){
		 $(".register_nav").css("display","none");
		 $("#attend").html("已发布活动");
	}else{
		$(".register_nav").css("display","inline-block");
	}
	 if(userno==user){
		 //个人简介
		 $(".mail_nav").css("display","inline-block");
		 $(".setting_nav").css("display","inline-block");
		 //个人设置这个可以后面再写
		/* $(".setting_nav").css("display","static");*/
		 var textval = userIntro;
		 if(textval==""){
			 $("#edit-intro").html("<a  href='javascript:void(0)' onclick='editIntro()'>(添加个人简介)</a>");
		 }else{
			 $("#edit-intro").html("<a  href='javascript:void(0)' onclick='editIntro()'>(编辑)</a>");
		 }
		//个人签名
		 var user_sign = sign;
		 if(user_sign==""){
			 $("#edit_sign").html("<a  href='javascript:void(0)' onclick='editSign()'>(添加签名)</a>");
		 }else{
			 $("#edit_sign").html("<a  href='javascript:void(0)' onclick='editSign()'>(编辑)</a>");
		 }
	 }else{
		 $("#friend").css("display","block");
		 $(".mail_nav").css("display","none");
		 $(".setting_nav").css("display","none");
		//获得该用户与当前用户的关系，是否已经关注，返回true和false
		 $.ajax({
			 	type : "POST",
				dataType : "json",
				asyn:false,
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'attentionAction!isAttention.action?userno='+user,
		    	success:function(result){
		    		if(result.isAttention){//已关注
		    			$("#attention_user").html("已关注");
		    			$(".cancel_attention").css("display","inline");
		    			$(".cancel_attention").html("<a href='javascript:void(0)' onclick='cancel_Attendtion("+user+")'>取消关注</a>");
		    		}else{
		    			$(".cancel_attention").css("display","none");
		    			$("#attention_user").html("<a href='javascript:void(0)' onclick='attentionUser("+user+")'>关注此人</a>");
		    		}
		 		}
		    });
	 }
}
function editSign(){
	$("#edit_sign").css("display","none")
	$("#input_sign").css("display","block");
	$("#sign").css("display","none");
	var sign = $("#sign").html();
	$("#sign_value").attr("value",sign);
}
function editIntro(){
	//没有任何简介也要留个空格
	var height=0;
	 var textval = $("#user-intro").html();
	 if(textval=="")height=42;
	 height =height+ $("#user-intro").height();
	 $("#intro").css("display","none");
	 $("#input-intro").css("display","block");
	 $("#textarea").attr("value",textval);//填充内容 
	 $("#textarea").height(height);	 
}
function saveUserSign(){
	var textval = $("#sign_value").val();
	 $.ajax({
		 asyn:false,
		   	type:"POST",
		   	dataType:"json",
	    	url:'userAction!saveUserSign.action?userSign='+textval,
	    	success:function(result){}
	    });
	 $("#sign").html(textval);
	 if(textval==""){
		 $("#edit_sign").html("<a  href='javascript:void(0)' onclick='editSign()'>(添加签名)</a>");
	 }else{
		 $("#edit_sign").html("<a  href='javascript:void(0)' onclick='editSign()'>(编辑)</a>");
	 }
	 cancelSignEdit();
}
function saveUserIntro(){
	 var textval = $("#textarea").val();
	 $.ajax({
		 asyn:false,
		   	type:"POST",
		   	dataType:"json",
	    	url:'userAction!saveUserIntro.action?textarea='+textval,
	    	success:function(result){}
	    });
	 $("#user-intro").html(textval);
	 if(textval==""){
		 $("#edit-intro").html("<a  href='javascript:void(0)' onclick='editIntro()'>(添加个人简介)</a>");
	 }else{
		 $("#edit-intro").html("<a  href='javascript:void(0)' onclick='editIntro()'>(编辑)</a>");
	 }
	 cancelIntroEdit();
}
function cancelIntroEdit(){
	$("#input-intro").css("display","none");
	 $("#intro").css("display","block");
}
function cancelSignEdit(){
	$("#edit_sign").css("display","block")
	$("#input_sign").css("display","none");
	$("#sign").css("display","block");
}
function cancel_Attendtion(user){
	if(window.confirm('确认取消关注吗？')){
		$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'attentionAction!cancelAttention.action?userno='+user,
		    	success:function(result){
		    		if(result.success){//取消成功,取关
		    			$("#attention_user").html("<a href='javascript:void(0)' onclick='attentionUser("+user+")'>关注此人</a>");
		    			$(".cancel_attention").css("display","none");
		    			$("#friend"+user).css("display","none");
		    		}else{
		    		}
		 		}
		});
	}
}
function attentionUser(user){
	$.ajax({
	 	type : "POST",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
    	url:'attentionAction!attentionUser.action?userno='+user,
    	success:function(result){
    		if(result.success){
    			$("#attention_user").html("已关注");
    			$(".cancel_attention").css("display","inline");
    			$(".cancel_attention").html("<a href='javascript:void(0)' onclick='cancel_Attendtion("+user+")'>取消关注</a>");
    			$("#op"+user).html("<a href='javascript:void(0)' onclick='cancel_Attendtion("+user+")'>相互关注</a></li>");
    		}else{
    			$(".cancel_attention").css("display","none");
    			$("#attention_user").html("<a href='javascript:void(0)' onclick='attentionUser("+user+")'>关注此人</a>");
    		}
 		}
	});
}
function mailUser(){
	//这里要带自己的参数，否则在写邮件页面不能识别;到自己的主页中写
	var userno=$("#userno").val();//这个是登陆用户的userno
	top.window.location="personal/user-mail-w.jsp?userno="+userno+"&&mailuserno="+GetQueryString("userno");
}
function index(){
	top.window.location="personal/user-index.jsp?userno="+GetQueryString("userno");
}
function info(){
	top.window.location="personal/user-info.jsp?userno="+GetQueryString("userno");
}
function attend(){
	top.window.location="personal/user-activity.jsp?param=2&&userno="+GetQueryString("userno");
}
function register(){
	top.window.location="personal/user-activity.jsp?param=1&&userno="+GetQueryString("userno");
}
function interest(){
	top.window.location="personal/user-activity.jsp?param=0&&userno="+GetQueryString("userno");
}
function record(){
	top.window.location="personal/user-record.jsp?userno="+GetQueryString("userno");
}
function picture(){
	top.window.location="personal/user-picture.jsp?userno="+GetQueryString("userno");
}

function mail(){
	top.window.location="personal/user-mail.jsp?userno="+GetQueryString("userno");
}
function setting(){
	top.window.location="personal/user-setting.jsp?userno="+GetQueryString("userno");
}
function friend(){
	top.window.location="personal/user-friend.jsp?num="+friend_num+"&&param=1&&userno="+GetQueryString("userno");
}
function likeme(){
	top.window.location="personal/user-friend.jsp?num="+attentionMe_num+"&&param=2&&userno="+GetQueryString("userno");
}
function init() {
    // 设置textarea自适应高度
    var handler = function () {
        setTareaAutoHeight('textarea'); 
    };
    // 添加监听
    if (window.addEventListener) {//FF
        window.addEventListener('DOMContentLoaded', handler, false);
        window.addEventListener('load', handler, false);
    } else if (window.attachEvent)//IE
        window.attachEvent('onload', handler);
    else
        window.onload = handler;
} // end init

init();

function setTareaAutoHeight(id) {
    document.getElementById(id).style.height='auto';
    document.getElementById(id).style.height = document.getElementById(id).scrollHeight + "px";
   /* console.log(document.getElementById(id).scrollHeight);*/
}
function setTareaAutoHeight1(id) {
    document.getElementById(id).style.height='auto';
    document.getElementById(id).style.height = document.getElementById(id).scrollHeight + "px";
/*    console.log(document.getElementById(id).scrollHeight);*/
}
