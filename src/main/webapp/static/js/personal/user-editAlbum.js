//页面加载时执行
var userno=GetQueryString("userno");//这个应该是登录用户的userno
var album=GetQueryString("album");
var user=$("#userno").val();//这个是登陆用户的userno
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	$("#upload_pic_show").css("display","inline-block");
	//加载相册信息
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'pictureAction!getAlbumInfo.action?album='+album,
    	success:function(result){
    		$(".alubmName").html(result.album.name);
    		$(".album_title").val(result.album.name);
    		$(".album_intro").html(result.album.intro);
    	}
	});
	if(userno==user){
		$(".album_op").css("display","inline");
	}
	$("#turn_back").css("display","inline");
 });

function updateAlbum(){
	var name=$(".album_title").val();
	var intro=$(".album_intro").val();
	if(name!=""){
		$.ajax({
			type : "POST",
			contentType: "application/json; charset=utf-8",
		    dataType: "json",//
		    asyn:false,
	    	url:'pictureAction!updateAlbumInfo.action?name='+name+'&&intro='+intro+'&&album='+album,
	    	success:function(albumresult){
	    		top.window.location="personal/user-picture.jsp?userno="+userno;
	    	}
		});
	}else{
		alert("相册名称不能为空");
	}	
	
}

function cancelAlbum(){
	top.window.location="personal/user-picture.jsp?userno="+userno;
}
function turnBack(){
	top.window.location="personal/user-picture.jsp?userno="+userno;
}
function editAlbum(){
	top.window.location="personal/user-editAlbum-attribute.jsp?userno="+userno+"&&album="+album;
}
function addPicture(){
	top.window.location="personal/user-addPicture-Album.jsp?userno="+userno+"&&album="+album;
}
function editBatch(){
	top.window.location="personal/user-editAlbum-batch.jsp?userno="+userno+"&&album="+album;
}