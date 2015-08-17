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
    		$("#size").val(result.album.num);
    		getAlbumUnPostPic();
    	}
	});
	if(userno==user){
		$(".album_op").css("display","inline");
	}
	$("#turn_back").css("display","inline");
	
 });
function getAlbumUnPostPic(){
	//加载相册列表
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
		url:'pictureAction!getUnPostPicture.action?album='+album,
    	success:function(result){//pic
    		if(result.size!=0)
        	loadUnPostPic(result);
        	else
        	$("#album_list").css("display","inline-block");
    	}
	});
}
function loadUnPostPic(result){
	$("#upload_pic_show").css("display","inline-block");
	$(".upload_muti_pic").css("display","none");
	$(".after_upload_tip").css("display","inline-block");
	$("#after_upload_input").css("display","inline-block");
	$.each(result.photo, function(i, item) {
		$("#upload_pic_show").append(
    		"<div class='each_pic' id=pic_"+item.id+">"+
                "<a class='img_pic'>"+
                  "<img style='width：170px;height:170px;' class='pic' src='"+item.thumb+"'>"+
                "</a>"+
                "<div class='upload_pic_intro'>"+
                	"<textarea id=intro_"+item.id+" placeholder='增加描述...' >"+item.picIntro+"</textarea>"+
                "</div>"+
                "<div class='upload_pic_operate'>"+
                	"<input type='radio' value="+item.id+" name='iscover'><span>设为封面</span>"+
                	"<span style='float:right;'><a href='javascript:void(0)' onclick='deleteUnPost("+item.id+")'>删除</a></span>"+
                "</div>"+
            "</div>	");
	});
};
function deleteUnPost(id){
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'pictureAction!deletPicture.action?pic='+id,
    	success:function(result){
    		$("#pic_"+id).fadeOut(600);
    	}
	});
}
function savePhoto(){
	var size=$("#size").val();
	//获得未保存的
	$.ajax({ 
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
	    url:'pictureAction!getUnPostPicture.action?album='+album,
    	success:function(result){
    		saveSelectAlbum(result,size);
    	}
	});
}
function saveSelectAlbum(result,oldsize){
	var size=result.size//实际长度，因为可能有删除
	var num=size;
	var cover=$("input[name='iscover']:checked").val();
	if(cover==undefined)cover=0;
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
	    asyn:false,//这里的num指的是本页未保存的数目
    	url:'pictureAction!updateAlbum.action?num='+num+'&&album='+album+'&&pic='+cover,
    	success:function(albumresult){
    		saveAlbumPic(album,result,cover);
    		top.window.location="personal/user-picture.jsp?userno="+userno;
    	}
	});
}
function ajaxFileUpload(){
	// 获得上传文件DOM对象
	var fileSelect = document.getElementById('fileUpload1');
	var files = fileSelect.files;
	$("#upload_pic_show").css("display","inline-block");
	$(".upload_muti_pic").css("display","none");
	$(".after_upload_tip").css("display","inline-block");
	$("#after_upload_input").css("display","inline-block");
	// 遍历图片文件列表，插入到表单数据中  file.name
	 for (var i = 0; i < files.length; i++) {
		 getAjaxData(files,i);
	 }
}
function ajaxFileUpload2(){
	// 获得上传文件DOM对象
	var fileSelect = document.getElementById('fileUpload2');
	var files = fileSelect.files;
	// 遍历图片文件列表，插入到表单数据中  file.name
	 for (var i = 0; i < files.length; i++) {
		 getAjaxData(files,i);
	 }

}
function getAjaxData(files,i){
	// 实例化一个表单数据对象  
	var formData = new FormData();
	formData.append('uploadFile',files[i], files[i].name);
	formData.append('filename',files[i].name);
	// 实例化一个AJAX对象
	var request = new XMLHttpRequest();
	// 发送表单数据
	request.open("POST", "ajaxUploderAction!uploadMutiPic.action?album="+album, true);
	// 请求完成时建立一个处理程序。
	request.onload = function () {
		/*console.log(request.responseText);*/
		var jsonObj = JSON.parse(request.responseText);
		$("#upload_pic_show").append(
	    		"<div class='each_pic' id='pic_"+jsonObj.picid+"'>"+
	                "<a class='img_pic'>"+
	                  "<img style='width：170px;height:170px;' class='pic' src='"+jsonObj.pictureCropperPath+"'>"+
	                "</a>"+
	                "<div class='upload_pic_intro'>"+
	                	"<textarea id=intro_"+jsonObj.picid+" placeholder='增加描述...'></textarea>"+
	                "</div>"+
	                "<div class='upload_pic_operate'>"+
	                	"<input type='radio' value="+jsonObj.picid+" name='iscover'><span>设为封面</span>"+
	                	"<span style='float:right;'><a href='javascript:void(0)' onclick='deleteUnPost("+jsonObj.picid+")'>删除</a></span>"+
	                "</div>"+
	            "</div>	");
	/*	loadUploadPic(jsonObj);*/
		if (request.status == 200) {
			/*console.log(request.responseText);*/
		    // File(s) uploaded.
		} else {
		    alert('An error occurred!');
		}
	}
	request.send(formData);
}
function saveAlbumPic(id,result,param){
	//先得到id然后再插入数据库
	if(result.size!=0)//得到未保存的pic信息
		$.each(result.photo, function(i, item) {
			var intro =$("#intro_"+item.id).val();
			$.ajax({
				type : "POST",
				contentType: "application/json; charset=utf-8",
			    dataType: "json",//param,封面信息
		    	url:'pictureAction!savePictureIntro.action?album='+id+'&&pic='+item.id+'&&param='+param+'&&intro='+intro,
		    	success:function(result){}
			});
		});
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