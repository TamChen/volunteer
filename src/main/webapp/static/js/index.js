function searchActivity(){
	var input=$("#input").val();
	var url=encodeURI("activity/activity-list.jsp?parms="+encodeURIComponent(input));
	top.window.location = url;
}

 //获得URL中的参数
 function request(paras) { 
	 var url = location.href; 
	 var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");

 }
 
 //加载新闻和公告内容
 $(function(){
	 // Tabs
	/* alert(document.getElementsByTagName('*').length);*/
	 	document.getElementById("index").style.background="#62C5E4";
	 	document.getElementById("index").style.color="white";
	 	var admin=$("#admin").val();
	 	if(admin==1){
	 		$("#volunteer-nav-1").html("");
	 			 var content = document.getElementById('volunteer-nav-1');
	  			content.innerHTML= "<a href='backend/ActivityManage.html'><img border='0' src='static/style/images/go.jpg' /> </a>"
	 		
	 	}
	 	
	   $('#tabs2, #tabs').tabs();
	   var $tab_title_input = $("#tab_title"),
	       $tab_content_input = $("#tab_content");
	   var tab_counter = 2;
	   
	   $("#news-list").html("");//清空内容
	   $("#brodcast-list").html("");
	   	$.ajax({
		   	type:"GET",
		   	dataType:"json",
		   	asyn:false,
	    	url:'infoAction!getBroadcastAndNewsInfo.action?num=4',
	    	success:function(result){
	    		$.each(result.newsInfo, function(i, item) {
	                $("#news-list").append(
	                		"<li> " +
	    	                "<div class='news-title'>" +
	    	                "<a href=news/new-detail.jsp?infoid="+item.uuid+">"+item.title+"</a>"+
	                        "</div>"+
	                        "<div class='isnew'></div>"+
	                        "<div class='news-time'>"+
	                        "   <p>"+item.recordTime+"</p>"+
	                        "</div>" +
	                        "</li>");
	            });
	            $.each(result.broadcastInfo, function(i, item) {
	                $("#brodcast-list").append(
	                		"<li> " +
	    	                "<div class='news-title'>" +
	    	                "<a href=news/new-detail.jsp?infoid="+item.uuid+">"+item.title+"</a>"+
	                        "</div>"+
	                        "<div class='isnew'></div>"+
	                        "<div class='news-time'>"+
	                        "   <p>"+item.recordTime+"</p>"+
	                        "</div>" +
	                        "</li>");
	            });
	 		}
	    });
	    $("#activity-top-3").html("");
	    	$.ajax({
		   	type:"GET",
		   	dataType:"json",
		   	asyn:false,
	    	url:'activityAction!getIndexPageActivityInfo.action',
	    	success:function(result){
	    		$.each(result.indexpageactivityinfo, function(i, item) {
	                $("#activity-top-3").append(
	                		"<div class='activity-detail'>"+
                                "<div class='activity-photo'>"+
                                   "<a href='personal/user-index.jsp?userno="+item.adminID+"'><img style='border-radius:50%;width:50px;height:50px;'src='"+item.adminphoto+"'></a>"+
                                "</div>"+
                                "<div class='source'>" +
                                		"<div class='admin-name'>" +item.adminName+"</div>"+
                                		"<div id='activity-time-front'>"+item.publishDate+"</div>"+
                                "</div>"+
                                "<div class='picture'>"+
                                "<a class='act-name' href='activity/activity-detail.jsp?activityid="+item.activityId+"'>哈"+item.title+"</a>"+
	                             "<a href='activity/activity-detail.jsp?activityid="+item.activityId+"'><img class='act-pic' style='width:320px;height:180px;' src='"+item.pic+"'></a>"+
	                             " </div>"+
                            "<div class='clear'></div>"+
                              "<ul>"+
                                "<li>招募人数&nbsp;&nbsp;"+item.number+"&nbsp;<span class='divider'>|</span></li>"+
                                "<li>报名人数&nbsp;&nbsp;"+item.attend+"&nbsp;<span class='divider'>|</span></li>"+
                                "<li>感兴趣人数&nbsp;&nbsp;"+item.interest+"&nbsp;<span class='divider'>|</span></li>"+
                                "<li><a href='javascript:void(0)' onclick='vote("+item.activityId+")'><img src='static/style/images/vote.jpg'></a>&nbsp;&nbsp;"+
                                "<span class='praiseNum'>"+item.praise+"</span>&nbsp;</li>"+
                            "</ul>"+
                        "</div>"
                            );
	            })
	 		}
	    });
	    //加载前台的专题活动的页面
	    $.ajax({
			   	type:"GET",
			   	dataType:"json",
		    	url:'activityAction!getSpecialActivity.action',
		    	success:function(result){
		                $(".special_act").attr("src",result.activity.pic2);
		                $("#link_special").attr("href","activity/activity-detail.jsp?activityid="+result.activity.id)
		        }
		 });
	    //加载前台的活动照片
	    loadPic(1);
	    //加载日志
	    loadRecord(1);
	    //加载风云榜
	    loadRate(1);
	    //加载访问量信息
	    $.ajax({
			   	type:"GET",
			   	dataType:"json",
		    	url:'statisticsAction!getTotalAccessNum.action',
		    	success:function(result){
		                $("#totalNum").html(result.num[0]+"人");
		                $("#dayNum").html(result.info.accessNumDay+"人");
		                $("#act_attend").html(result.num[1]+"人");
		        }
		 });
	  //加载前台图片..滑动的
	    $.ajax({
			   	type:"GET",
			   	dataType:"json",
		    	url:'activityAction!getOffActivity.action',
		    	success:function(result){
		    		$.each(result.activityList, function(i, item) {
		    			size=result.num;
		    			if(size<8){
		    				var a=1;
		    				for(a ;a<size;a++){
		    					$("#activity"+a).css("display","block");
		    					$(".link"+a).attr("href","activity/activity-detail.jsp?activityid="+item.id); 
		    					$(".img"+a).attr("src",item.pic);
		    					$(".act_title"+a).html(item.title);
		    				}
		    				for(size;size<9;size++){
		    					/*alert(size);
		    					$("#activity7").css("display","none");*/
		    					$(".act_style"+size).css("display","none");
		    				}
		    			}else{
	    					$("#activity"+(i+1)).css("display","block");
	    					$(".link"+(i+1)).attr("href","activity/activity-detail.jsp?activityid="+item.id); 
	    					$(".img"+(i+1)).attr("src",item.pic);
	    					$("#act_title"+(i+1)).html(item.title);
	    				}
		    		});
		    	}
		 });
	    //排名人数
		 $.ajax({
			   	type:"GET",
			   	dataType:"json",
		    	url:'userAction!getUserRateListNum.action',
		    	success:function(result){
		    		 $("#rate_num").attr("onclick","loadRateNext("+result.num+")");
		 		}
		 });
		 //图片墙总数
		 $.ajax({
			   	type:"GET",
			   	dataType:"json",
		    	url:'pictureAction!getPicListNum.action?input=&&param=0&&size=4',
		    	success:function(result){
		    		 $("#glimpse_photo").attr("onclick","loadNext("+result.size+")");
		 		}
		 });
		 //日志数
		 $.ajax({
			   	type:"GET",
			   	dataType:"json",
		    	url:'diaryAction!getNiceRecordListNum.action',
		    	success:function(result){
		    		 $("#next_record").attr("onclick","loadRecordNext("+result.num+")");
		 		}
		 });
	 });
 function vote(activityId){
	 var num= $(".praiseNum").html();
	 $(".praiseNum").html(eval(num)+1);
	 $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:'activityAction!voteActivity.action?activityId='+activityId,
	    	success:function(result){
	 		}
	 });
 }
 var current=1;
 function loadPic(current){
	 $("#glimpse_list").html("");
	 $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:'pictureAction!getGlimpseList.action?input=&&param=0&&current='+current+"&&size=4",
	    	success:function(result){
	    		$.each(result.pic, function(i, item) {
	               $("#glimpse_list").append("" +
	               		"<li ><img style='width:100px;height:100px;' src="+item.thumb+"></li>" +
	               		"");
	    		});
	 		}
	 });

 }

 function loadPre(){
	 if(current!=1){
		 current=current-1; 
		 loadPic(current);
	 }
 }
 function loadNext(page){
		if(current!=page){
			current=current+1;
			loadPic(current);
		}
 }
 var recordnum=1;
 function loadRecord(current){
	 $("#record_list").html("");
	 $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:'diaryAction!getFrontRecordList.action?current='+current,
	    	success:function(result){
	    		$.each(result.recordList, function(i, item) {
	               $("#record_list").append("" +
	               		"<li>"+
                           "<div class='rate_icon'>"+eval(i+1+(current-1)*6)+"</div>"+
                           "<a href=personal/record-detail.jsp?recordId="+item.id+"&&userno="+item.userNo+">"+
                           "<span>"+item.title+"</span>"+
                           "</a>"+
                         "</li>");
	              
	    		 });
	 		}
	 });

 }
 function loadRecordPre(){
	 if(recordnum!=1){
		 recordnum=recordnum-1; 
		 loadRecord(recordnum);
	 }
}
function loadRecordNext(page){
	if(recordnum!=page){
		recordnum=recordnum+1;
		loadRecord(recordnum);
	}
}
var page=1;
function loadRate(current){
	 $("#rate_list").html("");
	 $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:'userAction!getUserRateList.action?current='+current,
	    	success:function(result){
	    		$.each(result.userList, function(i, item) {
	               $('#rate_list').append(
	            		   "<li>"+
                  " <div class='photo-rate'>"+
                  "     <a href='personal/user-index.jsp?userno="+item.userno+"'><img style='width:70px;height:70px;' src='"+item.userHead+"'></a>"+
                  " </div>"+
                  " <div class='rate'>"+
                  " </div>"+
                  " <div class='text'>"+
                  item.userRate+
	              "</div>"+
                  " <div class='info'>"+
                  "     <a href='personal/user-index.jsp?userno="+item.userno+"'>"+item.username+"</a>"+
                  "     <p>"+item.userCollege+"</p>"+
                  "     <div class='text'><p>活跃度</p></div>"+
                  "     <div class='star' id='star"+item.userno+"'></div>"+
                  " </div>"+
               "</li>");
	               setStar(item.userno,item.star);
	    		 });
	 		}
	 });

}
function loadRatePre(){
	 if(page!=1){
		 page=page-1; 
		 loadRate(page);
	 }
}
function loadRateNext(page){
	if(page!=page){
		page=page+1;
		loadRate(page);
	}
}

function setStar(userno,star){
	$('#star'+userno).raty({
	    readOnly: true, 
	    score: eval(star),
	    size      : 24,
	    starOff:  'static/lib/img/star-off.png',
	    starOn :  'static/lib/img/star-on.png',
	    starHalf: 'static/lib/img/star-half.png'
	});
}
