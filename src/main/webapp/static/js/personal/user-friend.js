//页面加载时执行
var size=10;
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	var userno=GetQueryString("userno");//这个是通过url传过来的
	var user=$("#userno").val();//这个是登陆用户的userno
	var param=GetQueryString("param");//1说明是我关注的，2是关注我的
	var num=GetQueryString("num");//
	if(user==userno){
		//加载关注我的和我关注的人
		$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'attentionAction!getRelationUsers.action?param='+param+'&&userno='+userno+'&&current=1&&size='+size,
		    	success:function(result){
		    		$("#all_friend").html("");
		    		$.each(result.attentionsList, function(i, item) {
		    			$("#all_friend").append("" +
		    					"<li class='user-friend' id=friend"+getuserno(item,param)+">"+
	                            "   <div class='col-md-1'>"+
	                            "      <a href='personal/user-index.jsp?userno="+getuserno(item,param)+"'><img style='width:50px;height:50px' src="+head(item,param)+"></a>"+
	                            "       </div>"+
	                            "       <ul class='col-md-9'>"+
	                            "    <li class='name'><a href='personal/user-index.jsp?userno="+getuserno(item,param)+"'>"+name(item,param)+"</a></li>"+
	                            "           <li class='sign'>"+sign(item,param)+"</li>"+
	                            "           <li class='college'>"+college(item,param)+"/"+major(item,param)+"</li>"+
	                            "           <li id='op"+getuserno(item,param)+"' class='user_operate'>"+
	                            "       </ul>"+
	                            "</li>");
		    				judge(item,param);
		    		});
		    		//这里当在别人关注我的页面时，有相互关注和关注的两个状态；
		    		//当屏幕内容少于屏幕尺寸时的处理
//		    		if(440+85*(result.attentionsList.length)<$(window).height()){
//		    			$("#footer").css({
//							position: "absolute",
//							bottom:0
//						});
//		    		}
		 		}
		});
		createPage(size,(num/size)+1, num,param);
	}else{
		 $.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'attentionAction!getUserFriend.action?param='+param+'&&userno='+userno,
		    	success:function(result){
		    		$.each(result.friendPicList, function(i, item) {
		    			$("#all_friend").append("" +
		    				"<li class='all_pic'>" +
		    					"<a class='user_img' href='personal/user-index.jsp?userno="+item.url+"'>"+
		    				"<img style='width:100px;height:100px'src='"+
		    				item.picPath+"'>" +
		    					"</a>" +
		    					"<div class='pic_user_name'>"+item.name+"</div>"+
		    				"</li>");
		    		});
		 		}
		    });
		}
 });
function judge(item,param){
	if(param==1){
		$("#op"+item.friend).html("<a href='javascript:void(0)' onclick='cancel_Attendtion("+item.friend+")'>取消关注</a></li>");
	}else{
		$.ajax({
		 	type : "POST",
			dataType : "json",
			asyn:true,
			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
	    	url:'attentionAction!isAttention.action?userno='+item.active,
	    	success:function(result){
	    		if(result.isAttention)
	    			$("#op"+item.active).html("<a href='javascript:void(0)' onclick='cancel_Attendtion("+item.active+")'>相互关注</a></li>");
	    		else
	    			$("#op"+item.active).html("<a href='javascript:void(0)' onclick='attentionUser("+item.active+")'>关注</a></li>");
	    	}
	    });
	}
}
//1说明是我关注的，2是关注我的
function getuserno(item,param){
	if(param==1){return item.friend;}
	else{return item.active;}
} 
function sign(item,param){
	if(param==1){return item.friend_sign;}
	else{return item.active_sign;}
}
function college(item,param){
	if(param==1){return item.friend_college;}
	else{return item.active_college;}
} 
function major(item,param){
	if(param==1){return item.friend_major;}
	else{return item.active_major;}
} 
function head(item,param){
	if(param==1){return item.friend_head;}
	else{return item.active_head;}
} 
function name(item,param){
	if(param==1){return item.friend_name;}
	else{return item.active_name;}
} 
function createPage(pageSize, buttons, total,param) {
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
        	$.ajax({
        		 	type : "POST",
        			dataType : "json",
        			contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
        	    	url:'attentionAction!getRelationUsers.action?param='+param+'&&userno='+GetQueryString("userno")+'&&current='+eval(pageIndex+1)+'&&size='+size,
        	    	success:function(result){
        	    		$("#all_friend").html("");
        	    		$.each(result.attentionsList, function(i, item) {
    		    			$("#all_friend").append("" +
    		    					"<li class='user-friend' id=friend"+getuserno(item,param)+">"+
    	                            "   <div class='col-md-1'>"+
    	                            "      <a href='personal/user-index.jsp?userno="+getuserno(item,param)+"'><img style='width:50px;height:50px' src="+head(item,param)+"></a>"+
    	                            "       </div>"+
    	                            "       <ul class='col-md-9'>"+
    	                            "    <li class='name'><a href='personal/user-index.jsp?userno="+getuserno(item,param)+"'>"+name(item,param)+"</a></li>"+
    	                            "           <li class='sign'>"+sign(item,param)+"</li>"+
    	                            "           <li class='college'>"+college(item,param)+"/"+major(item,param)+"</li>"+
    	                            "           <li id='op"+getuserno(item,param)+"' class='user_operate'>"+
    	                            "       </ul>"+
    	                            "</li>");
    		    				judge(item,param);
    		    		});
        	 		}
        	});
        }
    });
}