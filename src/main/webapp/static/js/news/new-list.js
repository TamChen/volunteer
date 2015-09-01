function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}  
$(function(){
	var param=GetQueryString("param");
	//加载滚动新闻
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
	 	asyn:false,
    	url:'infoAction!getBroadcastAndNewsInfo.action?num=100',
    	success:function(result){
    		if(param==1){
    			$.each(result.newsInfo, function(i, item) {
	    			$("#new"+eval(i+1)).html("<a href='news/new-detail.jsp?infoid="+item.uuid+"'>"+item.title+"</a>");
	    		});
    		}
    		else{
    			 $.each(result.broadcastInfo, function(i, item) {
    				$("#new"+eval(i+1)).html("<a href='news/new-detail.jsp?infoid="+item.uuid+"'>"+item.title+"</a>");
    			 });
    		}
    	
 		}
    });
	
	document.getElementById("infonew").style.background="#62C5E4";
 	document.getElementById("infonew").style.color="white";
	 $.jqPaginator('#pagination1', {
	        totalPages:1,
	        visiblePages: 10,
	        currentPage: 1,
	        prev: '<li class="prev"><a href="javascript:;">前一页</a></li>',
	        next: '<li class="next"><a href="javascript:;">后一页</a></li>',
	        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	        onPageChange: function (num, type) {
	            $.ajax({
	    		   	type:"GET",
	    		   	dataType:"json",
	    	    	url:'infoAction!getNewsInfoList.action?currentpage='+num+'&&param='+param+'',
	    	    	success:function(result){
	    	    		$("#yesterday_new").html(""); 
	    	    		$.each(result.infoMap, function(key,val ) {
//	    	    			 alert(key.split(",")[0]);
//	    	                alert(key+"++"+val[0].author);
	    	    			var content="";
	    	    			for(var a=0;a<val.length;a++){
	    	    				content=content+"<li><i class='content_right_body_point'></i><a href='news/new-detail.jsp?infoid="+val[a].id+"'>"+val[a].title+"</a></li>"
	    	    			}
	    	                $("#yesterday_new").append(
	    	                	"<div class='date'>"+
	 		    					"<span class='year'>"+key.split(",")[0]+"</span>"+
	 		    					"<span class='day'>"+key.split(",")[1]+"月</span>"+
	 		    				"</div>"+
	 		    				"<div class='content_right_body'>"+
	 		    				"	<ul>"+content+
	 		    				"	</ul>"+
	 		    				"	<div class='new_icon'><img src='static/style/img/new_icon.gif'></div>"+
	 		    				"</div>"+
	 		    				"<br/><br/><br/><br/>"
	    	                );
	    	               
	    	            })
	    	    	}
	    	    });
	        }
	    });
	 
 });
