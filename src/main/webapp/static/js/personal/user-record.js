//在日记页面有展开全文的功能
//页面加载时执行//在日志详情页面有喜欢功能，有分页
//列表页面没有删除和喜欢功能
var userno=GetQueryString("userno");//这个是通过url传过来的
var user=$("#userno").val();//这个是登陆用户的userno
var size=7;//日志页面的一页包含多少日志
$(function(){
	initialize();//初始化页面（不包括正文内容的其他部分）
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		url:'diaryAction!getUserDiary.action?userno='+userno+'&&current=1&&size='+size,
		success:function(result){
			replaceInfo(result);
		}
	});
	$.ajax({
		type : "POST",
		dataType : "json",
		contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		url:'diaryAction!getUserDiaryNum.action?userno='+userno,
		success:function(result){
			createPage(size,(result/size+1), result);
		}
	});
	if(userno==user){
		$(".w-record").css("display","inline-block");
	}else{
		$(".w-record").css("display","none");
	}
 });
function getDiaryDetail(id){
	top.window.location="personal/record-detail.jsp?recordId="+id+"&&userno="+GetQueryString("userno");
}
function w_record(){
	top.window.location="personal/user-record-w.jsp?userno="+GetQueryString("userno");
}
function createPage(pageSize, buttons, total) {
	var userno=GetQueryString("userno");//这个是通过url传过来的
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
        	$("#record-list_all").html("");
        	$.ajax({
			 	type : "POST",
				dataType : "json",
				contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
		    	url:'diaryAction!getUserDiary.action?userno='+userno+'&&current='+eval(pageIndex+1)+'&&size='+size,
		    	success:function(result){
		    		replaceInfo(result);
		 		}
		});
        }
    });
}
function replaceInfo(result){
	$.each(result.userDiaryList, function(i, item) {
		$("#record-list_all").append(
		    	 "<li class='user-record-list'>"+
                 "  <div class='col-md-9'>"+
                 "      <span class='record-title'><a href='javascript:void(0)' onclick='getDiaryDetail("+item.id+")'>"+item.title+"</a></span>"+
                 "       <span class='open' id='open"+item.id+"'><a href='javascript:void(0)' onclick='openDetail("+item.id+")'>展开</a></span>"+
                 "       <div class='record-time'>"+item.publishTime+"</div>"+
                 "       <p class='record-detail' id=content"+item.id+"><span id='record"+item.id+"'>"+item.content+"</span></p>"+
                 "  </div>"+
                 "</li>"
                 );
		});	
}
function openDetail(id){
	$("#content"+id).css("overflow","auto");
	$("#content"+id).height("auto");
	$("#content"+id).css("max-height","10000px");
	$("#record"+id).replaceWith(function(){
	    return $("<pre id=record"+id+"/>").append($(this).contents());
	});
	$("#open"+id).html("<a href='javascript:void(0)' onclick='closeDetail("+id+")'>收起</a>");
}
function closeDetail(id){
	$("#content"+id).css("overflow","hidden");
	$("#content"+id).height("auto");
	$("#content"+id).css("max-height","54px");
	$("#record"+id).replaceWith(function(){
	    return $("<p id=record"+id+"/>").append($(this).contents());
	});
	$("#open"+id).html("<a href='javascript:void(0)' onclick='openDetail("+id+")'>展开</a>");
}