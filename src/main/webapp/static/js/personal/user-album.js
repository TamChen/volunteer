//页面加载时执行
//如果是管理员登陆可以，显示的是活动的图片//管理员应该没有相册
var userno=GetQueryString("userno");//这个应该是登录用户的userno
var user=$("#userno").val();//这个是登陆用户的userno
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	loadUserAlbum();
	if(userno==user){
		$(".album_op").css("display","inline");
		$(".deleteAlbum").css("display","inline-block");
	}
 });
function loadUserAlbum(){
	//加载用户相册
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
	    async:false, 
    	url:'pictureAction!getUserAlbum.action?userno='+userno,
    	success:function(result){
    		if(result.size==0){
    			addDefaultAlbum();
    			loadUserAlbum();
    		}else{
    			$.each(result.albums, function(i, item) {
        			$("#album_list_index").append(
        				"<div class='each_album' id='album_"+item.id+"'>"+
    	                    "<a class='img_album' href='personal/user-picture-list.jsp?userno="+userno+"&&album="+item.id+"'>"+
    	                    "<img class='album' style='width:170px;height:170px;' src='"+item.thumb+"'>"+
    	                    "</a>"+
    	                    "<div class='albumname'><a href='personal/user-picture-list.jsp?userno="+userno+"&&album="+item.id+"'>"+item.name+"</a></div>"+
    	                    "<div class='albumIntro'>"+getIntro(item)+"</div>"+
    	                    "<div class='deleteAlbum'><a href='javascript:void(0)' onclick='deleteAlbum("+item.id+")'>删除</a></div>"+
                        "</div>");
        		});
    		}
    	
    	}
	});
}
function addDefaultAlbum(){
	alert(hello);
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
	    asyn:false,
    	url:'pictureAction!saveAlbum.action?num=0&&pic=0&&name=默认相册&&intro='+getDate(),
    	success:function(albumresult){
    	}
	});
}
function getDate(){
	var myDate = new Date();
	return myDate.toLocaleString();
}
function deleteAlbum(id){
if(window.confirm('确认删除该相册？')){
	$("#album_"+id).fadeOut(600);
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'pictureAction!deleteUserAlbum.action?album='+id,
    	success:function(result){
    	}
	});
	    
	}
}
function getIntro(item){
	if (item.intro==null) return item.num+"张照片&nbsp;&nbsp;"+item.date+"更新";
	else return item.intro;
}
function addAlbum(){
	top.window.location="personal/user-addAlbum.jsp?userno="+userno;
}
function addPhoto(){
	top.window.location="personal/user-addPicture.jsp?userno="+userno;
}