//页面加载时执行
var userno=GetQueryString("userno");//这个应该是登录用户的userno
var user=$("#userno").val();//这个是登陆用户的userno
var album=GetQueryString("album");
var size=12;
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	//加载相册信息
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'pictureAction!getAlbumInfo.action?album='+album,
    	success:function(result){
    		$(".alubmName").html(result.album.name);
    		createPage(size,(result.album.num/size)+1, result.album.num);
    	}
	});
	//加载相册列表
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'pictureAction!getAlbumPicList.action?current=1&&size='+size+'&&album='+album,
    	success:function(result){//pic
    		loadInfo(result);
    	}
	});
	if(userno==user){
		$(".album_op").css("display","inline");
	}
	$("#turn_back").css("display","inline");
 });
function createPage(pageSize, buttons, total) {
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
        	$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'pictureAction!getAlbumPicList.action?current='+eval(pageIndex+1)+'&&size='+size+'&&album='+album,
		    	success:function(result){
		    		loadInfo(result);
		 		}
		});
        }
    });
}

//====================================================================================
//===================修改相册信息=====================================
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
//====================================================================================
//===================修改相册信息=====================================
function loadInfo(result){
	$("#user_pic_list").html("");
	$.each(result.pic, function(i, item) {
    	$("#user_pic_list").append(
    		"<div class='user_pic_item'>"+
	            "<a class='img_user' href='personal/user-pic-detail.jsp?album="+item.album+"&&userno="+item.userno+"&&pic="+item.id+"'>"+
	            "<img class='user_pic' src='"+item.path+"'>"+
	            "</a>"+
	        "<div class='pic_intro'>"+item.picIntro+"</div>"+
            "</div>");
    });
}