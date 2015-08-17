
$(function(){
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:'noticeAction!getUserInfoNum.action',
    	success:function(result){
    		var info = document.getElementById("info");
    		if(result.infonum==0){
    			info.style.backgroundColor="#706E6E";
    			info.innerHTML=result.infonum;
    		}else{
    			info.style.backgroundColor="red";
    			info.innerHTML=result.infonum;
    		}
 		}
    });
//	var totalpage=$("#total").val();
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
	    	    	url:'infoAction!getNewsInfoList.action?currentpage='+num,
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
