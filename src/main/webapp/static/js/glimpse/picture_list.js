//获得url中的参数
var size=12;
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]);
     return null;
}
function searchPicture(){
	$(".activity_pic_list").html("");
	var input=$("#input").val();
	getPicList(input);
}
$(function(){
	document.getElementById("glimpse").style.background="#62C5E4";
 	document.getElementById("glimpse").style.color="white";
	var param=GetQueryString("param");
	if(param==0){
		$(".pic_list_title").html("活动风采");
	}else{
		$(".pic_list_title").html("个人风采");
	}
	//参数为0说明是活动照片，，参数为1说明是个人最新照片
	//加载图片
	getPicList("");
	 
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
//获得所有图片
function getPicList(input){
	var param=GetQueryString("param");
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:"pictureAction!getGlimpseList.action?input="+input+"&&param="+param+"&&current=1&&size="+size,
    	success:function(result){
    		replaceInfo(result);
    	}
    });
	 //照片总数
	 $.ajax({
		   	type:"GET",
		   	dataType:"json",
	    	url:'pictureAction!getPicListNum.action?input='+input+'&&size='+size+'&&param='+param,
	    	success:function(result){
	    		createPage(size,(result.size/size+1), result.size,input);
	 		}
	 });
}
function searchActivity(){
	var input=$("#input").val();
	var url=encodeURI("activity/activity-list.jsp?parms="+encodeURIComponent(input));
	window.location = url;
}
function replaceInfo(result){
	$.each(result.pic, function(key, pic) {
		$(".activity_pic_list").append("" +
	"<div class='item_pic'>"+
		"<a "+gethref(pic)+"><img class='imgstyle' src='"+pic.path+"'></a>"+
		"<div class='pic_intro'>"+pic.picIntro+"</div>"+
	"</div>");
	});
}
function gethref(pic){
	var param=GetQueryString("param");
	if(param==0)
	return "href='activity/act_pic_detail.jsp?num="+GetQueryString("num")+"&&activityid="+pic.activityId+"&&pic="+pic.id+"'";
	else
	return "href='personal/user-pic-detail.jsp?userno="+pic.userno+"&&pic="+pic.id+"&&album="+pic.album+"'";	
}
function createPage(pageSize, buttons, total,input) {
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
        	$(".activity_pic_list").html("");
        	$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'pictureAction!getGlimpseList.action?input='+input+'&&param="+param+"&&current='+eval(pageIndex+1)+'&&size='+size,
		    	success:function(result){
		    		replaceInfo(result);
		 		}
		});
        }
    });
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