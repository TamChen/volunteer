//获得url中的参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
function searchActivity(){
	var input=$("#input").val();
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"activityAction!getSearchTotalActivityNum.action?params="+input,
    	success:function(result){
    		creatPage(result.number,input);
 		}
    });
}
function searchMuti(param){
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"activityAction!getSearchTotalActivityNum.action?params="+eval(param),
    	success:function(result){
    		creatPage(result.number,eval(param));
 		}
    });
}
function creatPage(number,input){
	$.jqPaginator('#pagination1', {
		totalPages:number,
		 prev: '<li class="prev"><a href="javascript:;">前一页</a></li>',
	     next: '<li class="next"><a href="javascript:;">后一页</a></li>',
	     page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
	     onPageChange: function (num, type) {
	    	 $("#middle-activity").html("");//清空内容
	            $.ajax({
	    		   	type:"GET",
	    		   	async: false, 
	    		   	dataType:"json",
	    	    	url:"activityAction!getActivityListFront.action?currentpage="+num+"&&params="+input,
	    	    	success:function(result){
	    	    		$.each(result.activityList, function(i, item) {
	    	                $("#middle-activity").append(
	    	                		"<div class='activity'>"+
	    	    					"<div class='activity-logo'>"+
	    	    					"	<img style='width:110px;height:165px;'src='"+item.pic+"' class='act-logo'>"+
	    	    					"</div>"+
	    	    					"<div class='information'>"+
	    	    					"	<div class='activity-name'><a href='activity/activity-detail.jsp?activityid="+item.id+"'>"+item.title+"</a></div>"+
	    	    					"	<ul class='detailed-information'>"+
	    	    					"		<li>发起:"+item.adminName+"</li>"+
	    	    					"		<li>时间：12月13日 8:00-12:00</li>"+
	    	    					"		<li>地点："+item.address+"</li>"+
	    	    					"		<li>招募人数："+item.number+"人</li>"+
	    	    					"		<li class='check'>已报名人数："+item.registration+" | 感兴趣人数："+item.interest+"</li>"+
	    	    					"	</ul>"+
	    	    					"</div>"+
	    	    					//分享要用www地址开头的
	    	    					"<div class='share'>"+
                                    "<div class='shareto'>分享到</span> " +
                                    "<a href='http://v.t.sina.com.cn/share/share.php?url="+getUrl()+"&title="+item.title+"' target='_blank'><img src='static/style/img/xinlang.gif '></a>"+
                                    "<a href='http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url="+getUrl()+"' target='_blank'><img src='static/style/img/kongjian.gif'></a>"+
                                    "<a href='http://www.douban.com/recommend/?url=www.127.0.0.1:8080"+getUrl()+".com&&title="+item.title+"' target='_blank'><img style='width:20px;height:20px;' src='static/style/img/douban.png'></a>"+
                                    "<a href='javascript:window.open('http://share.renren.com/share/buttonshare.do?link='+encodeURIComponent("+getUrl()+")+'&title='+encodeURIComponent("+item.title+"));void(0)'> <img style='width:18px;height:18px;margin-left:-3px;'src='static/style/img/renren.png'></a>"+
                                    "</div>"+
	    	    				"</div>");
	    	            })
	    	    	}
	    	    });
	     }
	})
}

$(function(){
	document.getElementById("activity").style.background="#62C5E4";
 	document.getElementById("activity").style.color="white";
	if(GetQueryString("parms")!=null){
		var input=GetQueryString("parms");
	}
	else if($("#input").val()!=""){
		var input=$("#input").val();
	}else{
		var input="";
	}
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"activityAction!getSearchTotalActivityNum.action?params="+input,
    	success:function(result){
    		creatPage(result.number,input);
 		}
    });
	loadExcelentHost(1);
	loadExcelentPersonal(1);
	//优秀主办方
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"userAction!getExcelentHostNum.action",
    	success:function(result){
    		$("#exhost").attr("onclick","loadHostNext("+result.num+")");
 		}
    });
	//优秀个人
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"userAction!loadExcelentPersonalNum.action",
    	success:function(result){
    		$("#exUser").attr("onclick","loadUserNext("+result.num+")");
 		}
    });
 });
function getUrl(){
	return window.location.pathname;
}
function loadExcelentHost(current){
	//load优秀举办方getExcelentHost
	$("#excellent-photo").html("");
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"userAction!getExcelentHost.action?current="+current,
    	success:function(result){
    		$.each(result.hostList, function(i, item) {
    			$("#excellent-photo").append("" +
    					"<li class='host'><a href='personal/user-index.jsp?userno="+item.userno+"'><img style='width:50px;height:50px' src='"+item.userHead+"'></a></li>"
    					);
    		});
 		}
    });
}
var page=1;
function loadHostNext(page){
	if(page!=page){
		page=page+1;
		loadExcelentHost(page);
	}
}
function loadHostPre(){
	 if(page!=1){
		 page=page-1; 
		 loadExcelentHost(page);
	 }
}
//加载活跃分子；
function loadExcelentPersonal(current){
	//load优秀举办方getExcelentHost
	$("#excellent-photo").html("");
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"userAction!loadExcelentPersonal.action?current="+current,
    	success:function(result){
    		$.each(result.userList, function(i, item) {
    			$("#excellent-personal").append("" +
    					"<li class='host'><a href='personal/user-index.jsp?userno="+item.userno+"'><img style='width:50px;height:50px' src='"+item.userHead+"'></a></li>"
    					);
    		});
 		}
    });
}
var current=1;
function loadUserNext(current){
	if(current!=current){
		current=current+1;
		loadExcelentPersonal(current);
	}
}
function loadUserPre(){
	 if(current!=1){
		 current=current-1; 
		 loadExcelentPersonal(current);
	 }
}