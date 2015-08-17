//页面加载时执行
var size=5;
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	var userno=GetQueryString("userno");//这个是通过url传过来的
	var user=$("#userno").val();//这个是登陆用户的userno
	var param=GetQueryString("param");//这个说明是什么类型，参加，报名，感兴趣
		//加载我参加的活动我感兴趣的我报名了名的，把所有的活动都搜出来，只是在前台筛选
		//如果是我的活动，则可以对活动进行操作，例如删除活动；，如果是管理员则不能在前台删除活动；谨慎操作
	if (userno.length<12) {
		$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'userActivityAction!getAllAdminActivityInfo.action?param='+param+'&&userno='+userno+'&&current=1&&size='+size,
		    	success:function(result){
		    		replaceInfo(result);
		 		}
		});
		$.ajax({
		//获得管理员所有的活动数目，为分页用
		 	type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
	    	url:'userActivityAction!getAdminActivityInfoSize.action?param='+param+'&&userno='+userno,
	    	success:function(result){
	    		createPage1(size,(result/size)+1, result,param);
	 		}
		});
	}else{//这里是普通用户
		//根据参数和用户学号加载用户的活动信息，并显示在前台，分页
		$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'userActivityAction!getAllUserActivityInfo.action?param='+param+'&&userno='+userno+'&&current=1&&size='+size,
		    	success:function(result){
		    		replaceInfo(result);
		 		}
		});
		$.ajax({
		 	type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
	    	url:'userActivityAction!getSizeOfUserActivity.action?param='+param+'&&userno='+userno,
	    	success:function(result){
	    		createPage(size,(result/size)+1, result,param);
	 		}
		});
	}
		
 });
var myactivitynum=0;
function judge(user,userno){
	if(user==userno){
		return "删除";
	}else return "";
}
function deleteActivity(activityid,act_size){
	 $.ajax({
		 	type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url:'userActivityAction!deleteUserActivity.action?activityid='+activityid,
	    	success:function(result){
	    	/*	$("#list"+activityid).css("display","none");*/
	    		$("#list"+activityid).fadeOut(600);
	 		}
	    });
	 myactivitynum=myactivitynum+1;
	if(myactivitynum==act_size){
		top.window.location="personal/user-index.jsp?userno="+GetQueryString("userno");
	}
}
function createPage(pageSize, buttons, total,param) {
	var userno=GetQueryString("userno");//这个是通过url传过来的
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
        	$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'userActivityAction!getAllUserActivityInfo.action?param='+param+'&&userno='+userno+'&&current='+eval(pageIndex+1)+'&&size='+size,
		    	success:function(result){
		    		replaceInfo(result);
		 		}
		});
        }
    });
}
function createPage1(pageSize, buttons, total,param) {
	var userno=GetQueryString("userno");//这个是通过url传过来的
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
        	$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'userActivityAction!getAllAdminActivityInfo.action?param='+param+'&&userno='+userno+'&&current='+eval(pageIndex+1)+'&&size='+size,
		    	success:function(result){
		    		replaceInfo(result);
		 		}
		});
        }
    });
}
function replaceInfo(result){
	var userno=GetQueryString("userno");//这个是通过url传过来的
	var user=$("#userno").val();//这个是登陆用户的userno
	$("#Activity_list").html("");
	 var act_size=result.size;
/*	 alert(act_size);*/
		$.each(result.ActivityList, function(i, item) {
		    	$("#Activity_list").append(
		    				"<li class='user-activity-list' id='list"+item.activityId+"'>"+
		    				"    <div class='col-md-2'>"+
		    				"            <a href='activity/activity-detail.jsp?activityid="+item.activityId+"'><img style='width: 110px;height:162px;'src='"+item.pic+"'></a>"+
		    				"    </div>"+
		    				"    <ul class='col-md-7'>"+
		    				"        <li class='title'><a href='activity/activity-detail.jsp?activityid="+item.activityId+"'>"+item.title+"</a></li>"+
		    				"        <li class='activity'>时间："+item.activityTime+"</li>"+
		    				"        <li class='activity'>地点："+item.address+"</li>"+
		    				"        <li class='activity'>招募人数："+item.number+"人</li>"+
		    				"        <li class='activity'>发起:"+item.adminName+"</li>"+
		    				"        <li class='detail'>"+item.attend+"人参加 | "+item.interest+"人感兴趣</li>"+
		    				"        <li class='act_operate'><a href='javascript:void(0)' onclick='deleteActivity("+item.activityId+","+act_size+")' >"+judge(user,userno)+"</a></li>"+
		    				"    </ul>"+
		    				"</li>"
		    	);
	});
}