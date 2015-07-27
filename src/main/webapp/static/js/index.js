//评分的jquery插件
 $('.star').raty({
     readOnly: true, 
     score: 5,
     size      : 24,
     starOff:  'static/lib/img/star-off.png',
     starOn :  'static/lib/img/star-on.png',
});
 //获得URL中的参数
 function request(paras) { 
	 var url = location.href; 
	 var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");
	 alert(url);
	 alert(paraString);
 }
 
 //加载新闻和公告内容
 $(function(){
	 // Tabs
	   $('#tabs2, #tabs').tabs();
	   var $tab_title_input = $("#tab_title"),
	       $tab_content_input = $("#tab_content");
	   var tab_counter = 2;
	   
	   
	   $("#news-list").html("");//清空内容
	   $("#brodcast-list").html("");
	   	$.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:'infoAction!getBrodcastAndNewsInfo.action',
	    	success:function(result){
	    		$.each(result.newsInfo, function(i, item) {
	                $("#news-list").append(
	                		"<li> " +
	    	                "<div class='news-title'>" +
	    	                "<a href="+item.uuid+">"+item.title+"</a>"+
	                        "</div>"+
	                        "<div class='isnew'><img src=''></div>"+
	                        "<div class='news-time'>"+
	                        "   <p>"+item.recordTime+"</p>"+
	                        "</div>" +
	                        "</li>");
	            })
	            $.each(result.brodcastInfo, function(i, item) {
	                $("#brodcast-list").append(
	                		"<li> " +
	    	                "<div class='news-title'>" +
	    	                "<a href="+item.uuid+">"+item.title+"</a>"+
	                        "</div>"+
	                        "<div class='isnew'><img src=''></div>"+
	                        "<div class='news-time'>"+
	                        "   <p>"+item.recordTime+"</p>"+
	                        "</div>" +
	                        "</li>");
	            })
	 		}
	    })
	    console.log( "success" );
	 });