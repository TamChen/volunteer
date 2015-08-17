function editorBroadcast(uuid){
	window.location = "AddBroadcast.html?infoid="+uuid;
}
$(function(){
	function GetQueryString(name)
	   {
	        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	        var r = window.location.search.substr(1).match(reg);
	        if(r!=null)return  unescape(r[2]); return null;
	   }
	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"../../infoAction!getInfoDetail.action?infoid="+GetQueryString("infoid"),
	    	success:function(result){
	    		$.each(result.detailinfoList, function(i, item) {
	    			//标题
	    			$("#broadcast-top").html("");
	    			$("#broadcast_content_body").html("");
	    			$("#broadcast_button").html("");
	    			var title = document.getElementById('broadcast-top');
	    			title.innerHTML= "标题:"+item.title+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	    			"作者:"+item.author+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间:"+item.recordTime;
	    			//内容
	    			var content = document.getElementById('broadcast_content_body');
	    			content.innerHTML= item.content;
	    			//button
	    			 $("#broadcast_button").append("<a onclick=editorBroadcast("+item.id+")><button class='btn btn-primary save1' style='margin-left:60%;' type='button'>编辑</button></a>" +
 					"<a href='BroadcastManage.html'><button class='btn btn-primary save1' style='margin-left:2%;' type='button'>返回</button></a>&nbsp;");
	    		});
	 		}
	    });
});