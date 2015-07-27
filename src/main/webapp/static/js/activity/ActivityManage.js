var ActivityNumber;//总记录数
var ActivitySize=7;//表示每页展示多少条
$(document).ready(function(){
	$(function(){
	   	$.ajax({
		   	type:"GET",
		   	dataType:"json",  
	    	url:'../../activityAction!getActivityList.action?current=1&&ActivitySize='+ActivitySize,
	    	success:function(result){
	    		loadInfo(result);
	            $.ajax({
                     type: "POST",
                     url: "../../activityAction!getActivityTotal.action",
                     success: function(ActivityNumber){
                    	 globalNewsNumber=ActivityNumber;
                    	 $("#ActivityTotal").text(ActivityNumber);
                 	 createPage(ActivitySize,(ActivityNumber/ActivitySize+1), ActivityNumber);
                  }
	            });
	    		   
	 		}
	    })
	    console.log( "success" );
	 });

}); 
function judgeStatus(status){
	if(status==135){
		$("#endActivityDiv").append("<button style='margin-left:10%;font-family:微软雅黑;' class='btn btn-primary' type='button'>活动已结束</button>"); 
		return "已结束";
	}else if(status==136){
		$("#endActivityDiv").append("<button style='margin-left:10%;font-family:微软雅黑;' class='btn btn-primary' type='button' onclick='endActivity()'>结束活动</button>"); 
		return "正在进行";
	}else if(status==4){
		return "<span style='color:red'>未发布</span>";
	}
}
function uploadActivityPic(activityID){
	window.location.href = "UploadActivityPic.html?activityID="+activityID;
}
function lookActivity(activityID){
	window.location.href= "LookActivity.html?activityID="+activityID;
}

function editActivity(activityID){
	window.location.href = "AddActivity.html?activityID="+activityID;
}

function deleteActivity(activityID){
	if(window.confirm('确认删除该活动？(建议不要删除活动)')){
	    $.ajax({
	        type: "POST",
	        dataType:"json",
	        url: "../../activityAction!deleteActivity.action?activityID="+activityID,
	        success: function(result){
	        	if(result.deleteActivityResult==true){
	        		$("#tr"+activityID).remove();
	        		$("#ActivityTotal").text(globalNewsNumber-1);
	        	}else{
	        		alert("删除活动失败")
	        	}
	        }
	    });
	}else{
		
	}
	
}
function loadInfo(result){
	 $("#tbody").empty();
	$.each(result.ActivityList, function(i, item) {
        $("#tbody").append(
        		" <tr id=tr"+item.id+">"+
                 "<td>"+eval(i+1)+"</td>"+
                " <td><a style='color:#333333' href=LookActivity.html?activityID="+item.id+">"+item.title+"</a></td>"   +         
                "<td>"+item.adminName+"</td>"+//活动举办方
                "<td>"+item.address+"</td>"+//活动地点
                "<td>"+item.beginTime+"</td>"+//活动时间都应该按统一格式在后台先处理，或者在前台处理
                "<td>"+item.number+"</td>"+//需要的人数
                "<td>"+item.attend+"</td>"+//参与人数
                "<td>"+item.registration+"</td>"+//报名人数
                "<td>"+item.interest+"</td>"+//感兴趣的人数
                "<td><span sytle='color:red'>"+item.unVrify+"</span></td>"+//待审核人数
                "<td>"+judgeStatus(item.state)+"</td>"+
                 "<td>"+" <span class='glyphicon glyphicon-edit'></span>" +
                  "<span onclick=lookActivity("+item.id+") id='lookActivity' style='cursor:pointer'>查看</span>"+
                  " <span class='glyphicon glyphicon-edit'></span>" +
                  "<span onclick=editActivity("+item.id+") id='editActivity' style='cursor:pointer'>编辑</span>"+
                 "  <span class='glyphicon glyphicon-trash'></span>" +
                 "<span onclick=deleteActivity("+item.id+") id='deleteActivity' style='cursor:pointer'>删除</span>"+
                 "  <span class='glyphicon glyphicon-trash'></span>" +
                 "<span onclick=uploadActivityPic("+item.id+") id='uploadActivity' style='cursor:pointer'>上传图片</span>"+
                 "</td>"+
               "</tr> "
        );
    });
    
}
function createPage(pageSize, buttons, total) {
    $(".pagination").jBootstrapPage({
        pageSize : pageSize,
        total : total,
        maxPageButton:buttons,
        onPageClicked: function(obj, pageIndex) {
    	   	$.ajax({
    		   	type:"GET",
    		   	dataType:"json",  
    	    	url:'../../activityAction!getActivityList.action?current='+eval(pageIndex+1)+'&&ActivitySize='+ActivitySize,
    	    	success:function(result){
    	    		loadInfo(result);
    	 		}
    	    })
        }
    });
}

