//页面加载时执行
var userno=GetQueryString("userno");//这个应该是登录用户的userno

$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	//查看用户是否有未上传完的图片
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'pictureAction!getUnPostPicture.action?album=0',
    	success:function(result){
    		if(result.size!=0)
    		loadUnPostPic(result);
    		else
    		$("#album_list").css("display","inline-block");
    	}
	});
	
 });
function loadUnPostPic(result){
	$("#upload_pic_show").css("display","inline-block");
	$(".upload_muti_pic").css("display","none");
	$(".after_upload_tip").css("display","inline-block");
	$("#after_upload_input").css("display","inline-block");
	$.each(result.photo, function(i, item) {
		$(".show_upload_pic").append(
    		"<div class='each_pic' id=pic_"+item.id+">"+
                "<a class='img_pic'>"+
                  "<img style='width：170px;height:170px;' class='pic' src='"+item.thumb+"'>"+
                "</a>"+
                "<div class='upload_pic_intro'>"+
                	"<textarea id=intro_"+item.id+" placeholder='增加描述...'></textarea>"+
                "</div>"+
                "<div class='upload_pic_operate'>"+
                	"<input type='radio' value="+item.id+" name='iscover'><span>设为封面</span>"+
                	"<span style='float:right;'><a href='javascript:void(0)' onclick='deleteUnPost("+item.id+")'>删除</a></span>"+
                "</div>"+
            "</div>	");
	});
}
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
//$("input[name='approve']:checked").val();
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
	request.open("POST", "ajaxUploderAction!uploadMutiPic.action?album=0", true);
	// 请求完成时建立一个处理程序。
	request.onload = function () {
		/*console.log(request.responseText);*/
		var jsonObj = JSON.parse(request.responseText);
		$(".show_upload_pic").append(
	    		"'<div class='each_pic' id='pic_"+jsonObj.picid+"'>"+
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


function saveAlbumAndPic(){
	//获得未保存的
	$.ajax({ 
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'pictureAction!getUnPostPicture.action?album=0',
    	success:function(result){
    		saveAlbum(result);
    	}
	});

}
function saveAlbum(result){
	var radio=$("input[name='iscover']:checked").val();
	var name=$(".album_title").val();
	var intro=$(".album_intro").val();
	if(radio!=undefined){
		if(name!=""){
			$.ajax({
				type : "POST",
				contentType: "application/json; charset=utf-8",
			    dataType: "json",//
			    asyn:false,
		    	url:'pictureAction!saveAlbum.action?num='+eval(result.size)+'&&pic='+radio+'&&name='+name+'&&intro='+intro,
		    	success:function(albumresult){
		    		var albumId=albumresult.id;
		    		saveAlbumPic(albumId,result,radio);
		    		top.window.location="personal/user-picture.jsp?userno="+userno;
		    	}
			});
		}else{
			alert("相册名称必填");
		}
	}else{
		alert("请选择封面");
	}
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
