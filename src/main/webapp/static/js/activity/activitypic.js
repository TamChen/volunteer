var uploadPicture;
function ajaxFileUpload(){
	$.ajaxFileUpload({  
        url:"../../ajaxUploderAction!uploadPic.action",             //需要链接到服务器地址  
        secureuri:false,
        type: 'post',
        async: true,
        fileElementId:"fileUpload",                         //文件选择框的id属性  
        dataType: "json",                                     //服务器返回的格式，可以是json
        success: function (data)  //服务器成功响应处理函数
        {
        	var jsonObj = JSON.parse(data.responseText);
        	if(jsonObj.uploadResult==true){
        		uploadPicture=jsonObj.pictureSavePath[0];
        		window.location = "ActivityCroPic.html?time=1&path="+uploadPicture+"&activityId="+GetQueryString("activityID");
        	}else{
        		alert("图片上传失败");
        	}
        },
        error: function (data, e)//服务器响应失败处理函数
        {
        	var jsonObj = JSON.parse(data.responseText);
        	if(jsonObj.uploadResult==true){
        		uploadPicture=jsonObj.pictureSavePath[0];
        		window.location = "ActivityCroPic.html?time=1&path="+uploadPicture+"&activityId="+GetQueryString("activityID");
        	}else{
        		alert("上传失败");
        	}
        }
    });  
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return (r[2]);
	return null;
}