 $(function(){
	var LogID = GetQueryString("LogID");
	if (LogID != null) {
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../logAction!getLogByID.action?LogID=" + LogID,
			success : function(result) {
				console.log(result)
				$("#LogTitle").html(result.log.title);
				$("#LogAuthor").html(result.log.userName);
				$("#LogPublishTime").html(result.log.publishTime);
				$("#LogContent").html(result.log.content);
				$("#LogStatus").html(judgeStatus(result.log.status));
				$("#LogIsNice").html(judgeIsNice(result.log.status,result.log.nice));
			}
		});
	}
});
function judgeStatus(status){
	if(status==1){
		return "已通过审核";
	}else {
		$("#endLogDiv").append("<button class='btn btn-primary save1'  onclick='setLogPass()' type='button'>通过审核</button>")
		return "待审核";
	}
}
function judgeIsNice(status,nice){
	if(nice==1){
		return "是";
	}else {
		if(status==1){
			$("#endLogDiv").append("<button class='btn btn-primary save1'  onclick='setLogIsNice()' type='button'>设为精品</button>")
		}
		return "否";
	}
}
function setLogIsNice(){
	if(window.confirm('你确定要设为精品日志吗？')){
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../logAction!setLogIsNice.action?LogID=" + GetQueryString("LogID"),
			success : function(result) {
				if (result.setLogIsNiceResult == true) {
					alert("操作成功");
					location.reload(true);
				} else {
					alert("操作失败");
				}
			}
		});
    }
}
function setLogPass(){
	if(window.confirm('你确定要审核通过吗？')){
		$.ajax({
			type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url : "../../logAction!setLogStatusPass.action?LogID=" + GetQueryString("LogID"),
			success : function(result) {
				if (result.setLogStatusPassResult == true) {
					alert("操作成功");
					location.reload(true)  ;
				} else {
					alert("操作失败");
				}
			}
		});
    }
	else{
    }
}
function back() {
	window.location = "LogManage.html";
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return (r[2]);
	return null;
}
