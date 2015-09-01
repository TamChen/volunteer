$(function(){
	//加载滚动新闻
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
	 	asyn:false,
    	url:'infoAction!getBroadcastAndNewsInfo.action?num=100',
    	success:function(result){
    		 $.each(result.broadcastInfo, function(i, item) {
 				$("#new"+eval(i+1)).html("<a href='news/new-detail.jsp?infoid="+item.uuid+"'>"+item.title+"</a>");
 			 });
 		}
    });
	//获取用户消息数目
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:'noticeAction!getUserInfoNum.action',
    	success:function(result){
    		var info = document.getElementById("info");
    		if(result.infonum==0){
    			info.style.backgroundColor="#706E6E";
    			info.html=result.infonum;
    		}else{
    			info.style.backgroundColor="red";
    			info.html=result.infonum;
    		}
 		}
    });
	//获取文章的详细信息
	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"infoAction!getInfoDetail.action?infoid="+GetQueryString("infoid"),
	    	success:function(result){
	    		$.each(result.detailinfoList, function(i, item) {
	    			//标题
	    			$("#new_content_title").html("");
	    			$("#time").html("");
	    			$("#autor").html("");
	    			$("#new_content_body").html("");
	    			var title = document.getElementById('new_content_title');
	    			title.innerHTML= item.title;
	    			//时间
	    			var time = document.getElementById('time');
	    			time.innerHTML= item.recordTime;
	    			//作者
	    			var author = document.getElementById('author');
	    			author.innerHTML= item.author;
	    			//内容
	    			var content = document.getElementById('new_content_body');
	    			content.innerHTML= item.content;
	    		});
	 		}
	    });
	 //获取上一篇文章和下一篇文章
/*	   $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:"infoAction!getInfoDetail.action?infoid="+GetQueryString("infoid"),
	    	success:function(result){
	    		$.each(result.detailinfoList, function(i, item) {
	    			//标题
	    			$("#new_content_title").html("");
	    			$("#time").html("");
	    			$("#autor").html("");
	    			$("#new_content_body").html("");
	    			var title = document.getElementById('new_content_title');
	    			title.innerHTML= item.title;
	    			//时间
	    			var time = document.getElementById('time');
	    			time.innerHTML= item.recordTime;
	    			//作者
	    			var author = document.getElementById('author');
	    			author.innerHTML= item.author;
	    			//内容
	    			var content = document.getElementById('new_content_body');
	    			content.innerHTML= item.content;
	    		});
	 		}
	    });*/
});
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}