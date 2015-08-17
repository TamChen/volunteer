//页面加载时执行
var userno=GetQueryString("userno");//这个是通过url传过来的
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	//加载我参加的活动
	//加载我感兴趣的活动
	//加载我报名的活动
	//初始化多说的参数
	$("#comment").attr("data-thread-key",window.location.pathname+GetQueryString("userno"));
	$("#comment").attr("data-title",document.title);
	$("#comment").attr("data-url",window.location.pathname);
	if(userno.length<12){
		//将管理员报名和要参加的两个隔离
		$("#act-attend").html();
		$("#act-interest").html();
		$("#act-register").html();
		$("#register_title").css("display","none");
		$("#register_content").css("display","none");
		$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'activityAction!getAdminActivity.action?userno='+userno,
		    	success:function(result){
		    		//管理员发布的活动与感兴趣的活动
		    		$.each(result.adminActivity, function(i, item) {
		    			$("#act-attend").append("<li><a href='activity/activity-detail.jsp?activityid="+item.url+"'><img style='width:110px;height:165px'src='"+item.picPath+"'></a></li>");
		    		});
		    		$.each(result.userActivityList, function(i, item) {
		    			if(item.statu==0){//感兴趣
		    				$("#act-interest").append("<li><a href='activity/activity-detail.jsp?activityid="+item.url+"'><img style='width:110px;height:165px'src='"+item.picPath+"'></a></li>");
		    			}
		    		});
		    		
		 		}
		});
	}else{
		$("#act-attend").html();
		$("#act-interest").html();
		$("#act-register").html();
		$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'userActivityAction!getUserActivityAll.action?userno='+userno,
		    	success:function(result){
		    		var interest=0,attend=0,register=0;
		    		$.each(result.userActivityList, function(i, item) {
		    			if(item.statu==0){//感兴趣
		    				$("#act-interest").append("<li><a href='activity/activity-detail.jsp?activityid="+item.url+"'><img style='width:110px;height:165px'src='"+item.picPath+"'></a></li>");
		    				interest=interest+1;
		    			}else if(item.statu==2){//参加了
		    				$("#act-attend").append("<li><a href='activity/activity-detail.jsp?activityid="+item.url+"'><img style='width:110px;height:165px'src='"+item.picPath+"'></a></li>");
		    				attend=attend+1;
		    			}else if(item.statu==1){//报名了
		    				$("#act-register").append("<li><a href='activity/activity-detail.jsp?activityid="+item.url+"'><img style='width:110px;height:165px'src='"+item.picPath+"'></a></li>");
		    				register=register+1;
		    			}
		    		});
		    		$("#act_type_attend").append("&nbsp;&nbsp;&nbsp;(参加<span id=attend_num>"+attend+"</span>)");
		    		$("#act_type_interest").append("&nbsp;&nbsp;&nbsp;(感兴趣<span id=interest_num>"+interest+"</span>)");
		    		$("#act_type_register").append("&nbsp;&nbsp;&nbsp;(报名<span id=register_num>"+register+"</span>)");
		 		}
		});
	}

 });