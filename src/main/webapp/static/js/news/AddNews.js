	function addNews(){
		var newsTitle = $("#newsTitle").val();
		var newsContent = UM.getEditor('myEditor').getContent();
		var infoid=GetQueryString("uuid");
//		console.log($("#newsContent").html());
	    $.ajax({
	        type: "POST",
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
            data: newsContent,
            url: "../../infoAction!addInfo.action?&&isnew=true&&infoid="+infoid+"&&title="+newsTitle,
	        success: function(result){
//	        	if(result==true){
//	        		alert("编辑成功")
//	        		window.location = "NewsManage.html";
//	        	}else{
//	        		alert("编辑新闻失败")
//	        	}
	     }
	});
	}
	function cancelNews(){
	        		$("#newsTitle").val('')
	        		$("#newsContent").html('')
	        		window.location = "NewsManage.html";
	        	}
	$(document).ready(function(){
		var uuid = GetQueryString("uuid"); 
		console.log(uuid);
		if(uuid!=null) 
		{
		    $.ajax({
		        type: "POST",
		        dataType:"json",
		        contentType:'application/x-www-form-urlencoded;charset=UTF-8',
		        url:"../../infoAction!getInfoDetail.action?infoid="+GetQueryString("uuid"),
		        success: function(result){
		        	$.each(result.detailinfoList, function(i, item) {
		        	console.log(item.title);
		        	$("#newsTitle").val(item.title)
		        	//设置值出现问题
	        		$("#newsContent").html(item.content)
	        		});
		     }
		});	
		}
	})
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null) return (r[2]); return null; 
		} 
