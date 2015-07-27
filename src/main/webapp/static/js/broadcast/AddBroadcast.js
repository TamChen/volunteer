	function addBroadcast(){
		var title  = $("#broadcastTitle").val();
		var content = UM.getEditor('myEditor').getContent();
		console.log($("#broadcastContent").html());
	    $.ajax({
	    	 type: "POST",
		     contentType: "application/json; charset=utf-8",
		     dataType: "json",//这里利用json接收数据不成功
	         data: content,
	         url: "../../infoAction!addInfo.action?title="+title+"&&isnew=false&&infoid="+GetQueryString("infoid"),
		     success: function(result){
		        	if(result==true){
		        		alert("编辑成功")
		        		window.location = "BroadcastManage.html";
		        	}else{
		        		alert("编辑公告失败")
		        	}
	     }
	});
	}
	function cancelBroadcast(){
	        		$("#broadcastTitle").val('')
	        		$("#broadcastContent").html('')
	        		window.location = "BroadcastManage.html";
	        	}
	$(document).ready(function(){
		var uuid = GetQueryString("infoid"); 
		console.log(uuid);
		if(uuid!=null) 
		{
		    $.ajax({
		        type: "POST",
		        dataType:"json",
		        contentType:'application/x-www-form-urlencoded;charset=UTF-8',
		        url:"../../infoAction!getInfoDetail.action?infoid="+GetQueryString("infoid"),
		        success: function(result){
		        	$.each(result.detailinfoList, function(i, item) {
			        	console.log(item.title);
			        	$("#broadcastTitle").val(item.title)
		        		$("#broadcastContent").html(item.content)
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
