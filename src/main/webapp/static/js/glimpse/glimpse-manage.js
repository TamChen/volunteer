var size=12;//表示每页展示多少条
$(document).ready(function(){
	$(function(){
		fistload(0);
		fistload(1);
	    console.log( "success" );
	});


	
}); 

function fistload(param){
		$.ajax({
		   	type:"GET",
		   	dataType:"json",  
	    	url:'../../pictureAction!getPicList.action?param='+param+'&&current=1&&size='+size,
	    	success:function(result){
	    		loadInfo(result,param);
	            $.ajax({
                     type: "POST",
                     url: "../../pictureAction!getPicListTotalNum.action?param="+param,
                     success: function(Number){
                    	 $("#total"+param).text(Number);
                 	 createPage(size, (Number/size)+1, Number,param);
                  }
	            });
	    		 
	 		}
	    });
}

function deletePic(id){
	if(window.confirm('你确定要删除图片吗？')){
	    $.ajax({
	        type: "POST",
	        dataType:"json",
	        url: "../../pictureAction!deletPicture.action?pic="+id,
	        success: function(result){
	        	if(result.deleteResult==true){
	        		alert("删除照片成功")
	        		window.location = "glimpseManage.html";
	        	}else{
	        		alert("删除照片失败")
	        	}
		     }
		});
	}
}

function createPage(pageSize, buttons, total,param) {
    $("#pic_list"+param).jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
    	   	$.ajax({
    		   	type:"GET",
    		   	dataType:"json",  
    	    	url:'../../pictureAction!getPicList.action?param='+param+'&&current='+eval(pageIndex+1)+'&&size='+size,
    	    	success:function(result){
    	    		/*$("#tbody"+param).html("");*/
    	    		loadInfo(result,param);
    	 		}
    	    })
        }
    });
}
function loadInfo(result,param){
	
	if (param==0) {
		$("#upload_pic_show2").html("");
		$.each(result.piclist, function(i, item) {
			
			$("#upload_pic_show2").append(
	    		"<div class='each_pic' id=pic_"+item.id+">"+
	                "<a class='img_pic'>"+
	                  "<img style='width：170px;height:170px;' class='pic' src='"+item.thumb+"'>"+
	                "</a>"+
	                "<div class='upload_pic_intro'>"+
	                "<p style='width:170px;height:22px;overflow:hidden;'>"+item.picIntro+"</p>"+
	                "</div>"+
	                "<div class='upload_pic_operate'>"+
	                	"<span style='float:right;'><a href='javascript:void(0)' onclick='deletePic("+item.id+")'>删除</a></span>"+
	                "</div>"+
	            "</div>	");
		});
		
		
		
		/*	$.each(result.piclist, function(i, item) {
	                $("#tbody"+param).append(
	                		" <tr id=tr"+item.id+">"+
	                         "<td>"+eval(i+1)+"</td>"+
	                        " <td><a >"+item.activityId+"</td>"   +  
	                        " <td><a >"+item.picIntro+"</a></td>"   + 
	                        " <td>"+item.date+"</td>"   +   
	                         "<td><a href='../../activity/act_pic_detail.jsp?pic="+item.id+"&&activityid="+item.activityId+"' target='_blank'>详细</a></td>"+
	                         "<td>"+" <span class='glyphicon glyphicon-edit'></span>" +
	                         "  <span class='glyphicon glyphicon-trash'></span>" +
	                         "<span onclick=deletePic("+item.id+")  style='cursor:pointer'>删除</span>"+
	                         "</td>"+
	                       "</tr> "
	                );
	 		})*/
	}else{
		$("#upload_pic_show1").html("");
		$.each(result.piclist, function(i, item) {
			$("#upload_pic_show1").append(
	    		"<div class='each_pic' id=pic_"+item.id+">"+
	                "<a class='img_pic'>"+
	                  "<img style='width：170px;height:170px;' class='pic' src='"+item.thumb+"'>"+
	                "</a>"+
	                "<div class='upload_pic_intro'>"+
	                	"<p style='width:170px;height:22px;overflow:hidden;'>"+item.picIntro+"</p>"+
	                "</div>"+
	                "<div class='upload_pic_operate'>"+
	                	"<span style='float:right;'><a href='javascript:void(0)' onclick='deletePic("+item.id+")'>删除</a></span>"+
	                "</div>"+
	            "</div>	");
		});
		/*$.each(result.piclist, function(i, item) {
	                $("#tbody"+param).append(
	                		" <tr id=tr"+item.id+">"+
	                         "<td>"+eval(i+1)+"</td>"+
	                        " <td><a >"+item.userno+"</td>"   +  
	                        " <td><a >"+item.picIntro+"</a></td>"   + 
	                        " <td>"+item.date+"</td>"   +   
	                         "<td><a href='../../personal/user-pic-detail.jsp?pic="+item.id+"&&userno="+item.userno+"&&album="+item.album+"' target='_blank'>详细</a></td>"+
	                         "<td>"+" <span class='glyphicon glyphicon-edit'></span>" +
	                         "  <span class='glyphicon glyphicon-trash'></span>" +
	                         "<span onclick=deletePic("+item.id+")  style='cursor:pointer'>删除</span>"+
	                         "</td>"+
	                       "</tr> "
	                );
	 		})*/
	}


}
