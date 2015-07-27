//活动组织者可能有多名
function searchActivity(){
	var input=$("#input").val();
	var url=encodeURI("activity/activity-list.jsp?parms="+encodeURIComponent(input));
	window.location = url;
}

function attendClick(){
	//参加活动应该让他填写邮箱，并填写详细信息，个人简介等等
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"userActivityAction!applyAttendActivity.action?activityid="+GetQueryString("activityid"),
    	success:function(result){
    	 $.each(result, function(key, val) {
    		 var num=eval(val[0])-1;
    		 $("#act_detail").html("");
    		 $("#attend").html("");
    		 $("#act_detail").html("已报名人数："+num+" | 感兴趣人数："+val[1]);
  			$("#attend").html("<div id='interst-act'><img border='0' src='static/style/img/after-register.png' /></div>"+
            "<div id='attend-act'><a href='javascript:void(0)' onclick='cancelAttend()'><img border='0' src='static/style/img/cancel.png' /></a></div>");
    		});
 		}
    });
}
function cancelAttend(){
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"userActivityAction!cancelAttendActivity.action?activityid="+GetQueryString("activityid"),
    	success:function(result){
    	 $.each(result, function(key, val) {
    		 $("#act_detail").html("");
    		 $("#attend").html("");
    		 $("#act_detail").html("已报名人数："+val[0]+" | 感兴趣人数："+val[1]);
 			$("#attend").html("<div id='interst-act'><a href='javascript:void(0)' onclick='attendClick()'><img border='0' src='static/style/img/attend.png' /></a></div>"+
            "<div id='attend-act'><a href='javascript:void(0)' onclick='interestClick()'><img border='0' src='static/style/img/interest.png' /></a></div>");
    		});
 		}
    });
}
function interestClick(){
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"activityAction!interestToActivity.action?activityid="+GetQueryString("activityid"),
    	success:function(result){
    	 $.each(result, function(key, val) {
    		 $("#act_detail").html("");
    		 $("#attend").html("");
    		 $("#act_detail").html("已报名人数："+val[0]+" | 感兴趣人数："+val[1]);
 			$("#attend").html("<div id='interst-act'><a href='javascript:void(0)' onclick='attendClick()'><img border='0' src='static/style/img/attend.png' /></a></div>"+
            "<div id='attend-act'><a><img border='0' src='static/style/img/after-interest.png' /></a></div>");
    		});
 		}
    });
}
//获得url中的参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]);
     return null;
}
$(function(){
		//加载活动详细信息
	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"activityAction!getActivityDetail.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    			$("#act_title").html("");
	    			$("#adminname").html("");
	    			$("#act_address").html("");
	    			$("#act_number").html("");
	    			$("#act_detail").html("");
	    			loadExcelentHost(result.activity.adminID);
	    			$(".act_logo").attr("src",result.activity.pic);
	    			$("#act_title").html( "地点："+result.activity.title);
	    			//发起人
	    			$("#adminname").html( "发起："+result.activity.adminName);
	    			//地点
	    			$("#act_address").html( "地点："+result.activity.address);
	    			//招募人数
	    			$("#act_number").html("招募人数："+result.activity.number+"人");
	    			//详情
	    			$("#act_detail").html("已报名人数："+result.activity.registration+" | 感兴趣人数："+result.activity.interest);
	    			$("#show_content_1").html(result.activity.detail);
	    			$("#comment").attr("data-thread-key","activity"+result.activity.id);
	    			$("#comment").attr("data-title",result.activity.title);
	    			$("#comment").attr("data-url",window.location.pathname);
	    	}
	    });
		//加载活动图片数目
	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"pictureAction!getPicNumByActivityId.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    		if(result.num!=0){
	    			$(".pl").html("( <a href='activity/activity_picture.jsp?num="+result.num+"&&activityid="+GetQueryString("activityid")+"'>全部"+result.num+"张</a> )");
	    			$(".pic_title").css("display","inline-block");
	    			loadThumbnail();
	    		}
	    	}
	    });
	  
	   //取得活动状态
	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"userActivityAction!getActivityStatu.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    		$.each(result, function(key, val) {
	    			var content = document.getElementById('attend');
	    			//感兴趣
	    			if(val==0){
	    				$("#attend").html("");
	    		 		content.innerHTML= "<div id='interst-act'><a href='javascript:void(0)' onclick='attendClick()'><img border='0' src='static/style/img/attend.png' /></a></div>"+
	    		 		"<div id='attend-act'><a><img border='0' src='static/style/img/after-interest.png' /></a></div>";
	    			//已报名，
	    			}else if(val==1){
	    	  			$("#attend").html("<div id='interst-act'><img border='0' src='static/style/img/after-register.png' /></div>"+
	    	            "<div id='attend-act'><a href='javascript:void(0)' onclick='cancelAttend()'><img border='0' src='static/style/img/cancel.png' /></a></div>");
	    			}//已通过审核
	    			else if(val==2){
	    				$("#attend").html("");
	    				content.innerHTML= "<div id='after-attend'><img border='0' src='static/style/img/after-attend1.png' /></div>";
	    			}//活动已截止
	    			else if(val==3){
	    				$("#attend").html("");
	    				content.innerHTML= "<div id='after-attend'><img border='0' src='static/style/img/notaccept.png' /></div>";
	    			}//活动已截止
	    			else if(val==4){
	    				$("#attend").html("");
	    				content.innerHTML= "<div id='after-attend'><img border='0' src='static/style/img/close.png' /></div>";
	    			}else {
	    				$("#attend").html("");
		    	 		content.innerHTML= "<div id='interst-act'><a href='javascript:void(0)' onclick='attendClick()'><img border='0' src='static/style/img/attend.png' /></a></div>"+
		                 "<div id='attend-act'><a href='javascript:void(0)' onclick='interestClick()'><img border='0' src='static/style/img/interest.png' /></a></div>";
	    			}
	    		});
	 		}
	    });
/*	   loadExcelentHost(1);*/
	   loadAttendUser(1);
/*	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"userActivityAction!getExcelentHostNum.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    		
	 		}
	    });*/
	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"userActivityAction!getApplyTotal.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    		$("#nextUser").attr("onclick","loadAttendUserNext("+result+")");
	 		}
	    });
});
function loadThumbnail(){
	 //加载缩略图片
	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"pictureAction!getThumbPicByActivityId.action?activityid="+GetQueryString("activityid"),
	    	success:function(result){
	    		$.each(result.pic, function(key, pic) {
	    				$(".detail-photo").append("" +
	    				" <li>"+
				            "<a href='activity/act_pic_detail.jsp?pic="+pic.id+"' title=''><img src='"+pic.thumb+"'></a>"+
				        "</li>");
	    			});
	    		}
	    });
}
function getMore(){
	$("#show_content_1").css("overflow","auto");
	$("#show_content_1").height("auto");
	$("#more").css("display","none");
	$("#short").css("display","inline");
}
function getShort(){
	$("#show_content_1").css("overflow","hidden");
	$("#show_content_1").height("170px");
	$("#more").css("display","inline");
	$("#short").css("display","none");
}
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
var page=1;
function loadExcelentHostNext(page){
	if(page!=page){
		page=page+1;
		loadExcelentHost(page);
	}
}
function loadExcelentHostPre(){
	 if(page!=1){
		 page=page-1; 
		 loadExcelentHost(page);
	 }
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