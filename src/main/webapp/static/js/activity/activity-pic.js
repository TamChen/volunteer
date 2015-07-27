var size=12;
$(function(){
	//加载图片
	var num=GetQueryString("num");
	var activityid=GetQueryString("activityid");
	//加载是什么活动,加载活动
	  $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"activityAction!getActivityDetail.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    			$(".activityName").html(result.activity.title);
	    			loadExcelentHost(result.activity.adminID);
	 		}
	    });
	//加载图片
	$.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"pictureAction!getPicByActivityId.action?current=1&&size="+size+"&&activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    		replaceInfo(result);
	    	}
	    });
	createPage(size,(num/size+1), num);
	loadAttendUser(1);
	  $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"userActivityAction!getApplyTotal.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    		$("#nextUser").attr("onclick","loadAttendUserNext("+result+")");
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
function searchActivity(){
	var input=$("#input").val();
	var url=encodeURI("activity/activity-list.jsp?parms="+encodeURIComponent(input));
	window.location = url;
}
function replaceInfo(result){
	$.each(result.pic, function(key, pic) {
		$(".activity_pic_list").append("" +
	"<div class='item_pic'>"+
		"<a href='activity/act_pic_detail.jsp?activityid="+GetQueryString("activityid")+"&&pic="+pic.id+"'><img class='imgstyle' src='"+pic.path+"'></a>"+
		"<div class='pic_intro'>"+pic.picIntro+"</div>"+
	"</div>");
	});
}
function getpicIntro(text){
	if(text==null) return " ";
	else return text;
}
function createPage(pageSize, buttons, total) {
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
        	$(".activity_pic_list").html("");
        	$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'pictureAction!getPicByActivityId.action?activityid='+GetQueryString("activityid")+'&&current='+eval(pageIndex+1)+'&&size='+size,
		    	success:function(result){
		    		replaceInfo(result);
		 		}
		});
        }
    });
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