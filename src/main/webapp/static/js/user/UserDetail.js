$(document).ready(function() {
	var userno = GetQueryString("userno");
	var pageSize=7;//表示每页展示多少条
	if (userno != null) {
		//获得用户详细信息
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../userAction!getUserDetail.action?userno="+ userno,
			success : function(result) {
				$("#username").html(result.user.username);
				$("#userno").html(result.user.userno);
				$("#password").html(result.user.password);
				$("#userHead").attr("src",result.user.userHead);
				$("#userSex").html(judgeSex(result.user.userSex));
				$("#userMajor").html(result.user.userMajor);
				$("#userCollege").html(result.user.userCollege);
				$("#userBir").html(result.user.userBir);
				$("#userIntro").html(result.user.userIntro);
				$("#userTele").html(result.user.userTele);
				$("#userMail").html(result.user.userMail);
				$("#userRate").html(result.user.userRate);
				$("#workTime").html(result.user.workTime);
			}
		});
		//获得用户与活动的列表
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : '../../userActivityAction!getUserActivity.action?current=1&&userno='+userno+"&&pageSize="+pageSize,
			success : function(result) {
				$.each(result.listUserActivity, function(i, item) {
					$("#userActivityTable").append(
							"<tr>" +
							" <tr id=tr"+item.activityId+">"+
	                        "<td>"+eval(i+1)+"</td>"+
							"<td><a style='color:#333333' href=../activity/LookActivity.html?activityID="+item.activityId+">"+item.title+"</td>"+
							"<td>"+item.adminName+"</td>"+
							"<td>"+item.address+"</td>"+
							"<td>"+item.activityTime+"</td>"+
							"<td>"+item.userWorkTime+"</td>"+
							"<td>"+getUserStatu(item.userStatu,item.activityStatu)+"</td>"+
							"<td>"+getActivityStatu(item.userStatu,item.activityStatu)+"</td>"+
							"</tr>"
							);
				});
			}
		});
		//获得用户与日志的列表
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : '../../logAction!getUserLog.action?current=1&&userno='+userno+"&&pageSize="+pageSize,
			success : function(result) {
				$.each(result.listUserLog, function(i, item) {
					$("#userRecordTable").append(
							"<tr>" +
							" <tr id=tr"+item.id+">"+
	                        "<td>"+eval(i+1)+"</td>"+
							"<td><a style='color:#333333' href=../log/LookLog.html?LogID="+item.id+">"+item.title+"</td>"+
							"<td>"+item.publishTime+"</td>"+
							"<td>"+isNice(item.nice)+"</td>"+
							"<td>"+judgeStatus(item.status)+"</td>"+
							"</tr>"
							);
				});
			}
		});
		$.ajax({
            type: "POST",
            url: "../../userActivityAction!getUserActivityTotal.action?userno=" + userno,
            success: function(ActivityNumber){
              	 $("#ActivityTotal").text(ActivityNumber);
           	createActivityPage(pageSize, (ActivityNumber/pageSize+1), ActivityNumber);
            }
        });
		$.ajax({
            type: "POST",
            url: "../../logAction!getUserLogTotal.action?userno=" + userno,
            success: function(LogNumber){
              	 $("#LogTotal").text(LogNumber);
             createLogPage(pageSize,(LogNumber/pageSize)+1, LogNumber);
            }
        });
		//加载相册列表
		$.ajax({
			type : "POST",
			contentType: "application/json; charset=utf-8",
		    dataType: "json",//
	    	url:'pictureAction!getAlbumPicList.action?current=1&&size='+size+'&&album='+album,
	    	success:function(result){//pic
	    		/*$.each(result.pic, function(key, pic) {
	    			$(".act_pic_list").append("" +
	    		"<li>"+
	    			"<img width=150px; height=150px;  src='"+pic.thumb+"'>"+
	    			"<div class='pic_intro'>"+pic.picIntro+"</div>"+
	    		"</li>");
	    		});*/
	    	}
		});
	}
});
function isNice(nice){
	if(nice==true){
		return "是";
	}else{
		return "否";
	}
}
function judgeStatus(status){
	if(status==1){
		return "已通过审核";
	}else {
		return "待审核";
	}
}
function getUserStatu(userStatu,activityStatu){
	//userStatu是指对活动是参加了还是，报名了，感兴趣，//1:报名2、通过0：是感兴趣
	if(userStatu==1){
		return "已报名";
	}else if(userStatu==2){
		return "通过审核";
	}else if(userStatu==3){
		return "未通过审核";
	}else{
		return "感兴趣";
	}
}
function getActivityStatu(userStatu,activityStatu){
	if(activityStatu==135){
		return "已结束";
	}else if(activityStatu==136){
		return "正在进行";
	}
}
function judgeSex(id){
	if(id=1){
		return "男";
	}else{
		return "女";
	}
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return (r[2]);
	return null;
}
function createActivityPage(pageSize, buttons, total) {
	var userno = GetQueryString("userno");
	$("#pageActivity").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
    		$.ajax({
    			type : "POST",
    			dataType : "json",
    			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
    			url : '../../userActivityAction!getUserActivity.action?current='+eval(pageIndex+1)+'&&userno='+userno+'&&pageSize='+pageSize,
    			success : function(result) {
    				$("#userActivityTable").html("");
    				$.each(result.listUserActivity, function(i, item) {
    					$("#userActivityTable").append(
    							"<tr>" +
    							" <tr id=tr"+item.activityId+">"+
    	                        "<td>"+eval(i+1)+"</td>"+
    							"<td><a style='color:#333333' href=../activity/LookActivity.html?activityID="+item.activityId+">"+item.title+"</td>"+
    							"<td>"+item.adminName+"</td>"+
    							"<td>"+item.address+"</td>"+
    							"<td>"+item.activityTime+"</td>"+
    							"<td>"+item.userWorkTime+"</td>"+
    							"<td>"+getUserStatu(item.userStatu,item.activityStatu)+"</td>"+
    							"<td>"+getActivityStatu(item.userStatu,item.activityStatu)+"</td>"+
    							"</tr>"
    					);
    				});
    			}
    		});
        }
    });
}
function editUser(){
	alert("感觉没多大必要去修改用户的信息");
}
function createLogPage(pageSize, buttons, total) {
	var userno = GetQueryString("userno");
	$("#pageLog").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
    		$.ajax({
    			type : "POST",
    			dataType : "json",
    			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
    			url : '../../logAction!getUserLog.action?current='+eval(pageIndex+1)+'&&userno='+userno+'&&pageSize='+pageSize,
    			success : function(result) {
    				$("#userRecordTable").html("");
    				$.each(result.listUserLog, function(i, item) {
    					$("#userRecordTable").append(
    							"<tr>" +
    							" <tr id=tr"+item.id+">"+
    	                        "<td>"+eval(i+1)+"</td>"+
    							"<td>"+item.title+"</td>"+
    							"<td>"+item.publishTime+"</td>"+
    							"<td>"+item.nice+"</td>"+
    							"<td>"+item.status+"</td>"+
    							"</tr>"
    							);
    				});
    			}
    		});
        }
    });
}