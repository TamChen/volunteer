var activityid=GetQueryString("activityID");
$(function(){
	//获得活动id

	//获得未保存的活动图片。
	
	//如果没有未保存的图片则执行以下
//	$(".after_upload").css("display","block");
//	$(".after_upload_tip").css("display","block");
	
	//加载活动图片详情（主要是未上传的）
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'../../pictureAction!getUnPostActivityPicture.action?activityid='+activityid,
    	success:function(result){
    		if(result.size!=0)
    		loadUnPostPic(result);
    	}
	});


});
function ajaxFileUpload(){
	
	// 获得上传文件DOM对象
	var fileSelect = document.getElementById('fileUpload1');
	var files = fileSelect.files;
	
	$("#upload_pic_show").css("display","inline-block");
	//主上传隐藏
	$(".upload_muti_pic").css("display","none");
	//副上传
	$(".after_upload_tip").css("display","inline-block");
	$(".after_upload").css("display","inline-block");
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
function deleteUnPost(id){
	$.ajax({
		type : "POST",
		contentType: "application/json; charset=utf-8",
	    dataType: "json",//
    	url:'../../pictureAction!deletPicture.action?pic='+id,
    	success:function(result){
    		$("#pic_"+id).fadeOut(600);
    		/*$("#pic_"+id).css("display","none");*/
    	}
	});
}
function getAjaxData(files,i){
	// 实例化一个表单数据对象  
	var formData = new FormData();
	formData.append('uploadFile',files[i], files[i].name);
	formData.append('filename',files[i].name);
	// 实例化一个AJAX对象
	var request = new XMLHttpRequest();
	// 发送表单数据
	request.open("POST", "../../ajaxUploderAction!uploadActivityPic.action?activityid="+activityid, true);
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
function loadUnPostPic(result){
	$("#upload_pic_show").css("display","inline-block");
	//主上传隐藏
	$(".upload_muti_pic").css("display","none");
	//副上传
	$(".after_upload_tip").css("display","inline-block");
	$(".after_upload").css("display","inline-block");
	$.each(result.photo, function(i, item) {
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
		url:'../../pictureAction!getUnPostActivityPicture.action?activityid='+activityid,
    	success:function(result){
    		saveActivityPic(result);
    	}
	});
}
function saveActivityPic(result){
	$.each(result.photo, function(i, item) {
		var intro =$("#intro_"+item.id).val();
		$.ajax({
			type : "POST",
			contentType: "application/json; charset=utf-8",
		    dataType: "json",
	    	url:'../../pictureAction!savePictureIntro.action?album=0&&pic='+item.id+'&&param=0&&intro='+intro,
	    	success:function(result){
	    		
	    	}
		});
	});
	window.location.href="ActivityManage.html";
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
