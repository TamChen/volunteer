var activityid=GetQueryString("activityID");
$(function(){
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
	    url:"../../pictureAction!getPicByActivityId.action?current=1&&size=100&&activityid="+activityid,
    	success:function(result){
    		if(result.size!=0)
    		loadUnPostPic(result);
    	}
	});
});

function loadUnPostPic(result){
	$("#upload_pic_show").css("display","inline-block");
	$.each(result.pic, function(i, item) {
		$("#upload_pic_show").append(
    		"<div class='each_pic' id=pic_"+item.id+">"+
                "<a class='img_pic'>"+
                  "<img style='width：170px;height:170px;' class='pic' src='"+item.thumb+"'>"+
                "</a>"+
                "<div class='upload_pic_intro'>"+
                	"<textarea id=intro_"+item.id+" placeholder='增加描述...'>"+item.picIntro+"</textarea>"+
                "</div>"+
                "<div class='upload_pic_operate'>"+
                	"<span style='float:right;'><a href='javascript:void(0)' onclick='deleteUnPost("+item.id+")'>删除</a></span>"+
                "</div>"+
            "</div>	");
	});
}

function uploadPicActivity(){
	//获得未保存的
	$.ajax({ 
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
	    url:"../../pictureAction!getPicByActivityId.action?current=1&&size=100&&activityid="+activityid,
    	success:function(result){
    		saveActivityPic(result);
    	}
	});
}
function saveActivityPic(result){
	$.each(result.pic, function(i, item) {
		var intro =$("#intro_"+item.id).val();
		$.ajax({
			type : "POST",
			contentType: "application/json; charset=utf-8",
		    dataType: "json",//param,封面信息
	    	url:'../../pictureAction!savePictureIntro.action?album=0&&pic='+item.id+'&&param=0&&intro='+intro,
	    	success:function(result){
	    		window.location.href="LookActivity.html?activityID="+activityid;
	    	}
		});
	});
}
//获得url中的参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]);
     return null;
}
function cancel(){
	window.location.href="ActivityManage.html";
}