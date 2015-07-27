var LogNumber;//总记录数
var LogSize=7;//表示每页展示多少条
$(document).ready(function(){
	$(function(){
	   	$.ajax({
		   	type:"GET",
		   	dataType:"json",  
	    	url:'../../logAction!getLogList.action?current=1&&LogSize='+LogSize,
	    	success:function(result){
	    		$.each(result.LogList, function(i, item) {
	                $("#tbody").append(
	                		" <tr id=tr"+item.id+">"+
	                         "<td>"+eval(i+1)+"</td>"+
	                        " <td><a style='color:#333333' href=LookLog.html?id="+item.id+">"+item.title+"</td>"   +  
	                        " <td><a style='color:#333333' href=UserDetail.html?id="+item.userNo+">"+item.userName+"</a></td>"   + 
	                        " <td>"+item.publishTime+"</td>"   +   
	                         "<td>"+judgeStatus(item.status)+"</td>"+
	                         "<td>"+" <span class='glyphicon glyphicon-edit'></span>" +
	                          "<span onclick=lookLog("+item.id+") id='lookLog' style='cursor:pointer'>查看</span>"+
	                         "  <span class='glyphicon glyphicon-trash'></span>" +
	                         "<span onclick=deleteLog("+item.id+") id='deleteLog' style='cursor:pointer'>删除</span>"+
	                         "</td>"+
	                       "</tr> "
	                );
	            })
	            
	            $.ajax({
                     type: "POST",
                     url: "../../logAction!getLogTotal.action",
                     success: function(LogNumber){
                    	 globalNewsNumber=LogNumber;
                    	 $("#LogTotal").text(LogNumber);
                 	 createPage(LogSize, (LogNumber/LogSize)+1, LogNumber);
                  }
	            });
	    		   
	 		}
	    })
	    console.log( "success" );
	 });

}); 
function judgeStatus(status){
	if(status==1){
		return "已通过审核";
	}else {
		return "待审核";
	}
}

function lookLog(logID){
	window.location = "LookLog.html?LogID="+logID;
}

function editLog(LogID){
	window.location = "EditLog.html?LogID="+LogID;
}

function deleteLog(LogID){
	if(window.confirm('你确定要删除日志吗？')){
	    $.ajax({
	        type: "POST",
	        dataType:"json",
	        url: "../../logAction!deleteLog.action?LogID="+LogID,
	        success: function(result){
	        	if(result.deleteLogResult==true){
	        		alert("删除日志成功")
	        		window.location = "LogManage.html";
	        	}else{
	        		alert("删除日志失败")
	        	}
		     }
		});
	}
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
    	    	url:'../../logAction!getLogList.action?current='+eval(pageIndex+1)+'&&LogSize='+LogSize,
    	    	success:function(result){
    	    		 $("#tbody").empty();
    	    		$.each(result.LogList, function(i, item) {
    	              $("#tbody").append(
    	            		  " <tr id=tr"+item.id+">"+
 	                         "<td>"+eval(i+1)+"</td>"+
 	                        " <td>"+item.title+"</td>"   +   
 	                        " <td>"+item.publishTime+"</td>"   +   
 	                         "<td>"+judgeStatus(item.status)+"</td>"+
 	                         "<td>"+" <span class='glyphicon glyphicon-edit'></span>" +
 	                          "<span onclick=lookLog("+item.id+") id='lookLog' style='cursor:pointer'>查看</span>"+
 	                         "  <span class='glyphicon glyphicon-trash'></span>" +
 	                         "<span onclick=deleteLog("+item.id+") id='deleteLog' style='cursor:pointer'>删除</span>"+
 	                         "</td>"+
 	                       "</tr> "
    	                );
    	            })
    	 		}
    	    })
        }
    });
}

