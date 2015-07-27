//页面加载时执行
var userno=GetQueryString("userno");//这个应该是登录用户的userno
var user=$("#userno").val();//这个是登陆用户的userno
var album=GetQueryString("album");
var pic=GetQueryString("pic");
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	$("#comment").attr("data-thread-key",window.location.pathname+GetQueryString("pic"));
	$("#comment").attr("data-title",document.title);
	$("#comment").attr("data-url",window.location.pathname);
	//加载图片详细
	$.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"pictureAction!getUserPicDetail.action?pic="+pic+"&&album="+album,
	    	success:function(result){
	    			$(".current").html(eval(result.current+1));
	    			$(".pre_pic").attr("href","personal/user-pic-detail.jsp?album="+result.pic.album+"&&userno="+result.pic.userno+"&&pic="+result.pre);
	    			$(".next_pic").attr("href","personal/user-pic-detail.jsp?album="+result.pic.album+"&&userno="+result.pic.userno+"&&pic="+result.next);
	    			$(".pic_img").attr("src",result.pic.path);
	    			$(".pic_intro_detail").html(result.pic.picIntro);
	    			$(".total").html(result.num);
	 		}
	});
	//加载相册信息
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'pictureAction!getAlbumInfo.action?album='+album,
    	success:function(result){
    		$(".alubmName").html(result.album.name);
    		$(".total").html(result.album.num);//这里替换总数
    	}
	});
 });
function getAllPic(){
	top.window.location="personal/user-picture.jsp?userno="+userno;
}
