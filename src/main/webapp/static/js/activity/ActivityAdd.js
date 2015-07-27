var uploadPicture;
function addActivity() {
//	var ActivityDetails = um.getContent();
	if($("#ActivityTitle").val()==""||$("#ActivityIntro").val()==""||$("#ActivityAddress").val()==""||$("#ActivityNeedPeople").val()==""){
		alert("请填写完整的活动信息");
	}else if($("#ActivityNeedPeople").val()<0){
		alert("输入正确人数");
	}else{
		var ActivityDetails = UM.getEditor('myEditor').getContent();
//		alert(ActivityDetails);
		$.ajax({
			type : "POST",
			contentType: "application/json; charset=utf-8",
		    dataType: "json",//
			data: ActivityDetails,
			url : "../../activityAction!addActivity.action?"
					+ "ActivityTitle="+ $("#ActivityTitle").val() 
					+ "&&ActivityIntro=" + $("#ActivityIntro").val() 
					+ "&&ActivityBeginTime="+$("#ActivityBeginTime").val()
					+ "&&ActivityEndTime="+$("#ActivityEndTime").val()
					+ "&&ActivityAddress="+$("#ActivityAddress").val()
					+ "&&ActivityId="+GetQueryString("activityID")
					+ "&&ActivityNeedPeople="+$("#ActivityNeedPeople").val(),
			success : function(result) {
				if (result.addActivityResult == true) {
					window.location = "ActivityUpPic.html?activityID="+result.activityJustSavedID;
				} else {
					alert("保存活动信息失败")
				}
			}
		});
	}
}
function cancelActivity() {
	window.location = "ActivityManage.html";
}
//修改活动
$(document).ready(function() {
	var activityID = GetQueryString("activityID");
	if (activityID != null) {
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../activityAction!getActivityDetail.action?activityid=" + activityID,
			success : function(result) {
				$("#ActivityTitle").val(result.activity.title);
				$("#ActivityIntro").val(result.activity.intro);
				$("#ActivityNeedPeople").val(result.activity.number);
				$("#ActivityBeginTime").val(result.activity.beginTime);
				$("#ActivityEndTime").val(result.activity.endTime);
				$("#ActivityAddress").val(result.activity.address);
//				$("#imghead").attr("src",result.activity.pic);
//				$("#ActivityStatus").html(judgeStatus(result.activity.state));
				$("#ActivityDetails").html(result.activity.detail);
			}
		});
	}else{
		$("#ActivityDetails").html("");
	}
})

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return (r[2]);
	return null;
}

