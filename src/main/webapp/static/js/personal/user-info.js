//页面加载时执行
var pageSize=7;
var userno=GetQueryString("userno");//这个应该是登录用户的userno
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	//加载用户 参与活动信息
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		url : 'userActivityAction!getUserActivity.action?current=1&&userno='+userno+"&&pageSize="+pageSize,
		success : function(result) {
			$.each(result.listUserActivity, function(i, item) {
				$("#userActivityTable").append(
						"<tr id=tr"+item.activityId+">"+
                        "<td>"+eval(i+1)+"</td>"+
						"<td><a style='color:#333333' href=../activity/LookActivity.html?activityID="+item.activityId+">"+item.title+"</td>"+
						"<td>"+item.adminName+"</td>"+
						"<td>"+item.address+"</td>"+
						"<td>"+item.activityTime+"</td>"+
						"<td>"+item.userWorkTime+"</td>"+
						"</tr>"
				);
			});
		}
	});
 });