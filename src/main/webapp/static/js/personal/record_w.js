//页面加载时执行
var userno=GetQueryString("userno");//这个应该是登录用户的userno
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	$("#write_diary").css("display","block");
	$("#preview_diary").css("display","none");
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'diaryAction!getUnPostRecord.action',
    	success:function(result){
    		if(result.hasDiary){
    			$("#record_content").html(result.userDiary.content);
        		$("#record_title").html(result.userDiary.title);
    		}
    	}
	});
 });
//未保存的下次打开的时候可以继续编写；,,应该是在退出的时候保存
function preview_record(){
	$("#write_diary").css("display","none");
	$("#preview_diary").css("display","block");
	var recordContent=$("#record_content").val();
	var record_title=$("#record_title").val();
	$("#log_content").html(recordContent);
	$("#log_title").html(record_title);
}
function publish_record(){
	var recordContent=$("#record_content").val();
	var record_title=$("#record_title").val();
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
		data: recordContent,
    	url:'diaryAction!postRecord.action?title='+record_title+"&&param=2",
    	success:function(result){
    		window.onbeforeunload=null;
    		top.window.location="personal/user-record.jsp?userno="+GetQueryString("userno");
    	}
	});
}
function cancel_record(){
	if(window.confirm('确认离开此页吗？还有日记没保存')){
		var recordContent=$("#record_content").val();
		var record_title=$("#record_title").val();
		$.ajax({
			type : "POST",
			contentType: "application/json; charset=utf-8",
		    dataType: "json",//
			data: recordContent,
	    	url:'diaryAction!postRecord.action?title='+record_title+"&&param=1",
	    	success:function(result){
	    		window.onbeforeunload=null;
	    		top.window.location="personal/user-record.jsp?userno="+GetQueryString("userno");
	    	}
		});
	}
}

window.onbeforeunload = function(){
	return '确认离开此页吗？'; 
};

function insert_link(){
	
}
function insert_photo(){
	
}
function continue_edit(){
	$("#write_diary").css("display","block");
	$("#preview_diary").css("display","none");
}