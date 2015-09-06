$(function(){
	var userno=$("#userno").val();//这个是登陆用户的userno
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:'noticeAction!getUserInfoNum.action',
    	success:function(result){
    		var info = document.getElementById("info");
    		if(result.infonum==0){
    			info.style.backgroundColor="#706E6E";
    			info.innerHTML=result.infonum;
    		}else{
    			info.style.backgroundColor="red";
    			info.innerHTML=result.infonum;
    		}
 		}
    });
	//加载用户信息
	$.ajax({
		type : "POST",
		dataType : "json",
		asyn:false,
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
    	url:'userAction!getUserDetail.action?userno='+userno,
    	success:function(result){
    		$(".user_head_img").attr("src",result.user.userHead);
 		}
    });
 });