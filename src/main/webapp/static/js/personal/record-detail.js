//在日记页面有展开全文的功能
//页面加载时执行//在日志详情页面有喜欢功能，有分页
//列表页面没有删除和喜欢功能
var userno=GetQueryString("userno");//这个是通过url传过来的recordId
var user=$("#userno").val();//这个是登陆用户的userno
var recordId=GetQueryString("recordId");
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		url:'diaryAction!getUserDiarydetail.action?recordId='+recordId,
		success:function(result){
			//likeNum
			$("#record-title").html(result.userDiary.title);
			$(".record-time").html(result.userDiary.publishTime);
			$(".all-record-detail").html(result.userDiary.content);
			if (userno==user) {
				$(".delete_record").html("<a href='javascript:void(0)' onclick='deleteRecord("+result.userDiary.id+")'>删除</a>");
			}else{
				$(".delete_record").html("<a href='javascript:void(0)' onclick=''></a>");
			}
			//初始化多说的参数
			$("#comment").attr("data-thread-key",result.userDiary.id);
			$("#comment").attr("data-title",result.userDiary.title);
			$("#comment").attr("data-url",window.location.pathname);
		}
	});
	
 });
/*function getDiaryDetail(id){
	window.location="personal/record-detail.jsp?recordId="+id+"&&userno="+GetQueryString("userno");
}*/

function deleteRecord(recordId){
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		url:'diaryAction!deleteUserDiarydetail.action?recordId='+recordId,
		success:function(result){
			top.window.location="personal/user-record.jsp?userno="+GetQueryString("userno");
		}
	});
}
function likeThisText(){
	
}

