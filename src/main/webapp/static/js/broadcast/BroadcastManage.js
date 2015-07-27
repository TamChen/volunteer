//var newsNumber=7;
$(document).ready(function(){
	$(function(){
	   	$.ajax({
		   	type:"GET",
		   	dataType:"json",  
	    	url:'../../infoAction!getLastSevenInfo.action?newsPage=1&isnew=false',
	    	success:function(result){
	    		$.each(result.InfoList, function(i, item) {
	                $("#tbody").append(
	                		" <tr id=tr"+item.uuid+">"+
	                         "<td>"+eval(i+1)+"</td>"+
	                        " <td><a style='color:#333333' href=LookBroadcast.html?infoid="+item.uuid+">"+item.title+"</a></td>"   +         
	                         "<td>"+item.author+"</td>"+
	                         "<td>"+item.recordTime+"</td>"+
	                         "<td>"+ 
	                         "<span class='glyphicon glyphicon-edit'></span>" +
	                          "<span onclick=lookBroadcast("+item.uuid+") style='cursor:pointer'>查看</span>"+
	                          " <span class='glyphicon glyphicon-edit'></span>" +
	                          "<span onclick=editorBroadcast("+item.uuid+") style='cursor:pointer'>编辑</span>"+
	                         "  <span class='glyphicon glyphicon-trash'></span>" +
	                         "<span onclick=deleteBroadcast("+item.uuid+") style='cursor:pointer'>删除</span>"+
	                         "</td>"+
	                       "</tr> "
	                );
	            })
	            
	            $.ajax({
                     type: "POST",
                     url: "../../infoAction!getInfoNumber.action?isnew=false",
                     success: function(newsNumber){
                    	 globalNewsNumber=newsNumber;
                    	 $("#broadcastTotal").text(newsNumber);
                 	 createPage(7, (newsNumber/7+1), newsNumber);
                  }
});
	    		   
	 		}
	    })
	    console.log( "success" );
	 });

}); 


function editorBroadcast(uuid){
	window.location = "AddBroadcast.html?infoid="+uuid;
}
function lookBroadcast(uuid){
	window.location = "LookBroadcast.html?infoid="+uuid;
}
function deleteBroadcast(uuid){
	if(window.confirm('确认删除该公告？')){
	    $.ajax({
	        type: "POST",
	        url: "../../infoAction!deleteInfo.action?infoid="+uuid,
	        success: function(result){
	        	if(result=="true"){
//	        		alert("删除公告成功")
	        		$("#tr"+uuid).remove();
	        		 $("#broadcastTotal").text(globalNewsNumber-1);
	        	}else{
	        		alert("删除公告失败")
	        	}
	        }
	    });
	}else{
		
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
    	    	url:'../../infoAction!getLastSevenInfo.action?infoPage='+eval(pageIndex+1),
    	    	success:function(result){
    	    		console.log(eval(pageIndex+1));
    	    		 $("#tbody").empty();
    	    		$.each(result.InfoList, function(i, item) {
    	    			
    	              $("#tbody").append(
    	            		  " <tr id=tr"+item.uuid+">"+
 	                         "<td>"+eval(i+1)+"</td>"+
 	                        " <td><a style='color:#333333' href=LookBroadcast.html?infoid="+item.uuid+">"+item.title+"</a></td>"   +         
 	                         "<td>"+item.author+"</td>"+
 	                         "<td>"+item.recordTime+"</td>"+
 	                         "<td>"+ 
 	                         "<span class='glyphicon glyphicon-edit'></span>" +
 	                          "<span onclick=lookBroadcast("+item.uuid+") id='lookActivity' style='cursor:pointer'>查看</span>"+
 	                          " <span class='glyphicon glyphicon-edit'></span>" +
 	                          "<span onclick=editorBroadcast("+item.uuid+") id='editorBroadcast'>编辑</span>"+
 	                         "  <span class='glyphicon glyphicon-trash'></span>" +
 	                         "<span onclick=deleteBroadcast("+item.uuid+") id='deleteBroadcast'>删除</span>"+
 	                         "</td>"+
    	                       "</tr> "
    	                );
    	            })
    	            
//    	            $.ajax({
//                         type: "POST",
//                         url: "../infoAction!getNewsNumber.action",
//                         success: function(newsNumber){
//                     	 createPage(7, 5, newsNumber);
//                      }
//    });
    	    		   
    	 		}
    	    })
        }
    });
}

