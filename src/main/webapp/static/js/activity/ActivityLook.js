var ActivityNumber;//总记录数
var ApplySize=7;//表示每页展示多少条
$(document).ready(function() {
	var activityID = GetQueryString("activityID");
	if (activityID != null) {
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../activityAction!getActivityDetail.action?activityid=" + activityID,
			success : function(result) {
				$("#ActivityTitle").html(result.activity.title);
				$("#ActivityIntro").html(result.activity.intro);
				$("#ActivityNeedPeople").html(result.activity.number);
				$("#ActivityAttendPeople").html(result.activity.attend);
				$("#ActivityRegistPeople").html(result.activity.registration);
				$("#ActivityBeginTime").html(result.activity.beginTime);
				$("#ActivityEndTime").html(result.activity.endTime);
				$("#ActivityAddress").html(result.activity.address);
				$("#imghead").attr("src",result.activity.pic);
				$("#ActivityStatus").html(judgeStatus(result.activity.state));
				$("#ActivityDetail").html(result.activity.detail);
//				globeNumber=result.activity.attend;
			}
		});
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : '../../userActivityAction!getJoinActivityUser.action?current=1&&applySize='+ApplySize+'&&activityid=' + activityID,
			success : function(result) {
				for(var i=0;i<result.listUserActivity.length;i++){
					$("#JoinActivityUserTable").append(
							"<tr>" +
							"<td>"+result.listUserActivity[i].userNo+"</td>"+
							"<td>"+result.listUserActivity[i].userName+"</td>"+
							"<td>"+result.listUserActivity[i].college+"</td>"+
							"<td>"+result.listUserActivity[i].grade+"</td>"+
							"<td><span id='workTime"+result.listUserActivity[i].id+"'>"+result.listUserActivity[i].worktime+"</span>小时</td>"+
							"<td><span id='totalHour"+result.listUserActivity[i].id+"'>"+result.listUserActivity[i].totaltime+"</span>小时</td>"+
							"<td id='userStatus"+result.listUserActivity[i].id+"'>"+judgeUserJoinStatus(result.listUserActivity[i].id,result.listUserActivity[i].statu)+"</td>"+
							"<td><span  id='workHour"+result.listUserActivity[i].id+"'>"+editUserWorkHourActivity(result.listUserActivity[i].id,result.listUserActivity[i].statu,result.listUserActivity[i].worktime)+"</span>小时</td>"+
							"</td>" +
							"<td>"+judgeOperation(result.listUserActivity[i].id,result.listUserActivity[i].statu)+"</td>" +
							"</tr>")
				}
			}
		});
		$.ajax({
            type: "POST",
            url: "../../userActivityAction!getApplyTotal.action?activityid=" + activityID,
            success: function(ApplyNumber){
//              	 globalNewsNumber=ApplyNumber;
              	 $("#ApplyTotal").text(ApplyNumber);
           	createPage(ApplySize, 5, ApplyNumber);
            }
        });
		//加载活动图片
		$.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"../../pictureAction!getPicByActivityId.action?current=1&&size=16&&activityid="+activityID,
	    	success:function(result){
	    		$.each(result.pic, function(key, pic) {
	    			$(".act_pic_list").append("" +
	    		"<li>"+
	    			"<img width=150px; height=150px;  src='"+pic.thumb+"'>"+
	    			"<div class='pic_intro'>"+pic.picIntro+"</div>"+
	    		"</li>");
	    		});
	    	}
	    });
	}
});
function updateActivityPic(){
	var activityID = GetQueryString("activityID");
	window.location.href="EditActivityPic.html?activityID="+activityID;
}
function judgeStatus(status){
	if(status==135){
		$("#endActivityDiv").append("<button style='margin-left:10%;font-family:微软雅黑;' class='btn btn-primary' type='button'>活动已结束</button>"); 
		return "已结束";
	}else if(status==136){
		$("#endActivityDiv").append("<button style='margin-left:10%;font-family:微软雅黑;' class='btn btn-primary' type='button' onclick='endActivity()'>结束活动</button>"); 
		return "正在进行";
	}else if(status==4){
		return "无活动海报，未发布";
	}
}
function judgeUserJoinStatus(id,flag){
	//1报名2通过
	if(flag==2){
		return "已通过申请";
	}else if(flag==1){
		return "正在申请";
	}else{
		return "已忽略";	
	}
}
function judgeOperation(id,flag){
	if(flag==2){
		return "<span class='glyphicon glyphicon-edit'></span>" +
        "<span  style='cursor:pointer'>通过</span>"+
        " <span class='glyphicon glyphicon-edit'></span>" +
        "<span onclick=ignoreUser("+id+") style='cursor:pointer'>忽略</span>"
	}else if(flag==1){
		return "<span class='glyphicon glyphicon-edit'></span>" +
        "<span onclick=setUserJoinedActivity("+id+") style='cursor:pointer'>通过</span>"+
        " <span class='glyphicon glyphicon-edit'></span>" +
        "<span onclick=ignoreUser("+id+") style='cursor:pointer'>忽略</span>"
	}else {
		return "<span class='glyphicon glyphicon-edit'></span>" +
	    "<span onclick=setUserJoinedActivity("+id+") style='cursor:pointer'>通过</span>"+
        " <span class='glyphicon glyphicon-edit'></span>" +
        "<span   style='cursor:pointer'>忽略</span>"
	}

}
function setUserJoinedActivity(id){
	if(window.confirm('你确定要审核通过？')){
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../userActivityAction!setUserJoinedActivity.action?userActivityID=" + id,
			success : function(result) {
				if (result.setUserJoinedResult == true) {
					alert("操作成功");
					$("#userStatus"+id).html("已通过申请");
					$("#workHour"+id).html("<span onclick=setUserWorkHourActivity("+id+") id='setUserWorkHourActivity' style='cursor:pointer;color:red;font-size:12px'>编辑</span>");
//					alert($("#ActivityNeedPeople").val());
//					var info = document.getElementById("ActivityNeedPeople");
//					alert(info);这里需要刷新才能看到参加人数增加了1；
				} else {
					alert("操作失败");
				}
			}
		});
    }
	else{
    }
}
function ignoreUser(id){
	if(window.confirm('你确定要忽略该用户？')){
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../userActivityAction!ignoreUser.action?userActivityID=" + id,
			success : function(result) {
				if (result.setUserJoinedResult == true) {
					alert("操作成功");
					$("#userStatus"+id).html("已忽略");
//					$("#workHour"+id).html("<span onclick=setUserWorkHourActivity("+id+") id='setUserWorkHourActivity' style='cursor:pointer;color:red;font-size:12px'>编辑</span>");
				} else {
					alert("操作失败");
				}
			}
		});
    }
	else{
    }
}
function editUserWorkHourActivity(id,flag,workHour){
	if(flag==2){
		return " <span onclick=setUserWorkHourActivity("+id+","+workHour+") id='setUserWorkHourActivity' style='cursor:pointer;color:red;font-size:12px'>编辑</span>";
	}else {
		return "0";
	}
}
function setUserWorkHourActivity(id,workHour){
	$("#setUserWorkHourActivity").html("");
	$("#workHour"+id).html("<input type='text' name='UserActivityWorkHour' id='UserActivityWorkHour' />"
			+"<input value='确定'  onclick='setUserActivityWorkHourActivity("+id+","+workHour+")' type='button' />"
			+"<input value='取消'  onclick='cancelSetUserActivityWorkHourActivity("+id+","+workHour+")' type='button' />")
}
function setUserActivityWorkHourActivity(id,workHour){
	alert($("#UserActivityWorkHour").val());
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		url : "../../userActivityAction!setUserActivityWorkHourActivity.action?userActivityID=" + id
				+"&&userActivityWorkHour="+$("#UserActivityWorkHour").val(),
		success : function(result) {
			if (result.setUserActivityWorkHourActivityResult == true) {
				alert("操作成功");
				location.reload([true]);
			} else {
				alert("操作失败");
			}
		}
	});
}
function cancelSetUserActivityWorkHourActivity(id,workHour){
//	$("#workTime"+id).html(workHour);
	$("#workHour"+id).html("<span onclick=setUserWorkHourActivity("+id+","+workHour+") id='setUserWorkHourActivity' style='cursor:pointer;color:red;font-size:12px'>编辑</span>");
}
function endActivity(){
	if(window.confirm('你确定要结束活动吗？')){
		var activityID = GetQueryString("activityID");
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../activityAction!endActivity.action?activityID=" + activityID,
			success : function(result) {
				if (result.endActivityResult == true) {
					alert("操作成功");
					location.reload([true])  ;
				} else {
					alert("操作失败");
				}
			}
		});
    }
	else{
    }
}
function editActivity(){
	var activityID = GetQueryString("activityID");
	window.location = "AddActivity.html?activityID="+activityID;
}
function cancelActivity() {
	window.location = "ActivityManage.html";
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return (r[2]);
	return null;
}
//

function createPage(pageSize, buttons, total) {
	$(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
    		$.ajax({
    			type : "POST",
    			dataType : "json",
    			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
    			url : '../../userActivityAction!getJoinActivityUser.action?current='+eval(pageIndex+1)+'&&applySize='+ApplySize+'&&activityid='+GetQueryString("activityID"),
    			success : function(result) {
    				$("#JoinActivityUserTable").html("");
    				for(var i=0;i<result.listUserActivity.length;i++){
    					$("#JoinActivityUserTable").append(
    							"<tr>" +
    							"<td>"+result.listUserActivity[i].userNo+"</td>"+
    							"<td>"+result.listUserActivity[i].userName+"</td>"+
    							"<td>"+result.listUserActivity[i].college+"</td>"+
    							"<td>"+result.listUserActivity[i].grade+"</td>"+
    							"<td><span id='workTime"+result.listUserActivity[i].id+"'>"+result.listUserActivity[i].worktime+"</span>小时</td>"+
    							"<td><span id='totalHour"+result.listUserActivity[i].id+"'>"+result.listUserActivity[i].totaltime+"</span>小时</td>"+
    							"<td id='userStatus"+result.listUserActivity[i].id+"'>"+judgeUserJoinStatus(result.listUserActivity[i].id,result.listUserActivity[i].statu)+"</td>"+
    							"<td><span  id='workHour"+result.listUserActivity[i].id+"'>"+editUserWorkHourActivity(result.listUserActivity[i].id,result.listUserActivity[i].statu,result.listUserActivity[i].worktime)+"</span>小时</td>"+
    							"</td></tr>")
    				}
    			}
    		});
        }
    });
}