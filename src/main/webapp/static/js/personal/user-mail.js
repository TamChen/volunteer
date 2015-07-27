//页面加载时执行
var userno=GetQueryString("userno");//这个是通过url传过来的,这个是自己的号子
var mailuser=GetQueryString("mailuserno");//这个是对方的号子
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	//加载我与对方的邮件来往；如果有邮件则显示，没有则是一个框
//	$("#mail-detail").html("");
	if(mailuser!=null){
		$.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:'noticeAction!getUserNoticeDetail.action?friend='+mailuser,
	    	success:function(result){//if(result.infonum==0)friendname friendhead
	    		$("#friend_name").html(result.friendname);
	    		var size=result.noticeSize;
	    		if(size==0){
	    			$("#text_area").css("display","block");
	    			$("#reply_submit").css("display","block");
	    			$("#reply_form").css("display","none");
	    		}else{
	    			if(mailuser.length<12)
	    			$("#reply_form").css("display","none");
	    			$.each(result.noticeList, function(key, item) {
	     				$("#mail-detail").append(
	     						"<li class='email-chat' id='"+item.id+"'>"+
	                    	    "<div class='split-line'><i>"+splitTime(item.send_time,0)+"</i></div>"+
	                    	    "<div class='chat'>"+
								"	<div class='time'>"+splitTime(item.send_time,1)+"</div>"+
								"    <div class='pic'>"+
								"        <a href='personal/user-index.jsp?userno="+item.userno+"'>"+
								"          <img style='width:50px;height:50px' src='"+item.userpic+"'>"+
								"        </a>"+
								"    </div>"+
								"    <div class='content'>"+
								"      <div class='sender'>"+
								"       <a href='personal/user-index.jsp?userno="+item.userno+"'>"+item.username+"</a>"+
								"      </div>"+
								"      <p>"+item.noticeContent+"+</p>"+
								"    </div>"+
								"  </div>"+
								"  <div style='width:10%;height:1px;clear:both;'></div>"+
								"  <div class='operations'>"+
								"        <a href='javascript:void(0)' onclick='deleteNotice("+item.id+","+size+")' >删除</a>"+
								"    </div>"+
							"</li>	"
	     			 	);
	     			});
	    		}
	    	}
	    });
	}else{
		$.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:'noticeAction!getNoticeListFistPage.action',
	    	success:function(result){
	    		if(result.size!=0){
	    			$.each(result.noticeList, function(key, item) {
	     				$("#noticeList").append(
	     						"<li class='email-chat' id='"+item.id+"'>"+
	                    	    "<div class='chat'>"+
								"	<div class='time'>"+splitTime(item.send_time,0)+"</div>"+
								"    <div class='pic'>"+
								"        <a href='javascript:void(0)' onclick='noticeDetail("+item.userno+")'>"+
								"          <img style='width:50px;height:50px' src='"+item.userpic+"'>"+
								"        </a>"+
								"    </div>"+
								"    <div class='content'>"+
								"      <div class='sender'>"+
								"       <a href='javascript:void(0)' onclick='noticeDetail("+item.userno+")'>"+item.username+"</a>"+
								"      </div>"+
								"      <p>"+item.noticeContent+"+</p>"+
								"    </div>"+
								"  </div>"+
								"  <div style='width:10%;height:1px;clear:both;'></div>"+
								"  <div class='operations'>"+
								"        <a href='javascript:void(0)' onclick='deleteAllNotice("+item.userno+","+item.id+")' >删除</a>"+
								"    </div>"+
							"</li>	"
	     			 	);
	     			});
	    		}
	    		
	    	}
	    });
	}

 });
function deleteAllNotice(mailuser,id){
	 $.ajax({
		 	type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url:'noticeAction!deleteAllNotice.action?mailuser='+mailuser,
	    	success:function(result){
	    		/*$("#"+id).css("display","none");*/
	    		$("#"+id).fadeOut(600);
	 		}
	    });
}
function noticeDetail(mailuser){
	//这里要带自己的参数，否则在写邮件页面不能识别;到自己的主页中写
	top.window.location="personal/user-mail-w.jsp?userno="+GetQueryString("userno")+"&&mailuserno="+mailuser;
}
function splitTime(time,param){
	var string=time.split(" ");
	if(param==0)return string[0];
	else return string[1];
	}
function saveUserEmail(){
	var mailContent = $("#mail_content").val();
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
		data: mailContent,
    	url:'noticeAction!saveUserNotice.action?friend='+mailuser,
    	success:function(result){
    		top.window.location = "personal/user-mail.jsp?userno="+userno;
    	}
    });
}
function reply(){
	var mailContent=$("#reply_content").val();
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
		data: mailContent,
    	url:'noticeAction!saveUserNotice.action?friend='+mailuser,
    	success:function(result){
    		location.reload();
    	}
    });
}
var number=0;
function deleteNotice(id,size){
	number=number+1;
	if(number==size){
		top.window.location = "personal/user-mail.jsp?userno="+userno;
	}
	 $.ajax({
		 	type : "POST",
			dataType : "json",
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
			url:'noticeAction!deleteUserNotice.action?id='+id,
	    	success:function(result){
	    		/*$("#"+id).css("display","none");*/
	    		$("#"+id).fadeOut(600);
	 		}
	    });
	size=size-1;
}
function cancelEmailEdit(){
	top.window.location = "personal/user-mail.jsp?userno="+userno;
}