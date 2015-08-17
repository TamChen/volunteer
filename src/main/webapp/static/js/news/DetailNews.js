function editorNews(uuid){
	window.location = "AddNews.html?uuid="+uuid;
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
	    			$("#new-top").html("");
	    			$("#new_content_body").html("");
	    			$("#new_button").html("");
	    			var title = document.getElementById('new-top');
	    			title.innerHTML= "标题:"+item.title+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	    			"作者:"+item.author+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间:"+item.recordTime;
	    			//内容
	    			var content = document.getElementById('new_content_body');
	    			content.innerHTML= item.content;
	    			//button
	    			 $("#new_button").append("<a onclick=editorNews("+item.id+")><button class='btn btn-primary save1' style='margin-left:60%;' type='button'>编辑</button></a>" +
 					"<a href='NewsManage.html'><button class='btn btn-primary save1' style='margin-left:2%;' type='button'>返回</button></a>&nbsp;");
	    		});
	 		}
	    });
});