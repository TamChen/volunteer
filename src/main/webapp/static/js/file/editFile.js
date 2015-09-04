function updateInfo(){
	var id=GetQueryString("id");
	var title=$("#title").val();
	$.ajax({
		type : "GET",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		url:'../../downloadAction!update.action?id='+id+"&&name="+title,
		success : function(result) {
			alert("修改成功");
			windows.location="fileManage.html";
		}
	});
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return (r[2]);
	return "";
}