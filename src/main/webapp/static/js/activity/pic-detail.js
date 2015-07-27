$(function(){
	//加载图片
	var picId=GetQueryString("pic");
	var activityid=GetQueryString("activityid");
	//初始化评论框
	$("#comment").attr("data-thread-key",window.location.pathname+GetQueryString("activityid"));
	$("#comment").attr("data-title",document.title);
	$("#comment").attr("data-url",window.location.pathname);
	loadAttendUser(1);
	//加载图片详细
	  $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"pictureAction!getPicDetail.action?pic="+picId+"&&activityid="+activityid,
	    	success:function(result){
	    			$(".current").html(result.current);
	    			$(".pre_pic").attr("href","activity/act_pic_detail.jsp?activityid="+activityid+"&&pic="+result.pre);
	    			$(".next_pic").attr("href","activity/act_pic_detail.jsp?activityid="+activityid+"&&pic="+result.next);
	    			$(".pic_img").attr("src",result.pic.path);
	    			$(".pic_intro_detail").html(result.pic.picIntro);
	    			$(".total").html(result.num);
	 		}
	    });
	//加载是什么活动
	  $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"activityAction!getActivityDetail.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    			$(".activityName").html(result.activity.title);
	    			loadExcelentHost(result.activity.adminID);
	 		}
	    });

});
//获得url中的参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]);
     return null;
}
//加载主办方
function loadExcelentHost(userno){
	//load优秀举办方getExcelentHost hostadmin
	$("#excellent-host").html("");
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"userAction!getUserDetail.action?userno="+userno,
    	success:function(result){
    			$("#excellent-host").append("" +
    					"<li class='host'><a href='personal/user-index.jsp?userno="+result.user.userno+"'><img style='width:50px;height:50px' src='"+result.user.userHead+"'></a></li>"
    					);
 		}
    });
}
function searchActivity(){
	var input=$("#input").val();
	var url=encodeURI("activity/activity-list.jsp?parms="+encodeURIComponent(input));
	window.location = url;
}
//加载参与的用户
function loadAttendUser(current){
	$("#excellent-user").html("");
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"userActivityAction!getJoinActivityUser.action?activityid="+GetQueryString("activityid")+"&&current="+current+"&&applySize=16",
    	success:function(result){
    		$.each(result.listUserActivity, function(i, item) {
    			$("#excellent-user").append("" +
    					"<li class='host'><a href='personal/user-index.jsp?userno="+item.userNo+"'><img style='width:50px;height:50px' src='"+item.userHead+"'></a></li>"
    					);
    		});
 		}
    });
}
var current=1;
function loadAttendUserNext(current){
	if(current!=current){
		current=current+1;
		loadAttendUser(current);
	}
}
function loadAttendUserPre(){
	 if(current!=1){
		 current=current-1; 
		 loadAttendUser(current);
	 }
}
function getAllPic(){
	window.location = "activity/activity_picture.jsp?num="+GetQueryString("num")+"&&activityid="+GetQueryString("activityid");
}