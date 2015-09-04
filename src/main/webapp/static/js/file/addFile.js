var uploadPicture;

function cancel() {
	window.location = "addFile.html";
}


//上传文件，依赖ajax
function addInfo(){
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
	var title=$("#title").val();
	// 实例化一个AJAX对象
	var request = new XMLHttpRequest();
	// 发送表单数据
	request.open("POST", "../../downloadAction!upload.action?title="+title, true);
	// 请求完成时建立一个处理程序。
	request.onload = function () {
		/*console.log(request.responseText);*/
		/*var jsonObj = JSON.parse(request.responseText);*/
	/*	loadUploadPic(jsonObj);*/
		if (request.status == 200) {
			alert("文件上传成功！");
			window.location = "fileManage.html";
			/*console.log(request.responseText);*/
		    // File(s) uploaded.
		} else {
		    alert('上传错误!');
		}
	}
	request.send(formData);
}