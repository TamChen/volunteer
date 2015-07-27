var newsNumber;
$(document).ready(function(){
	$(function(){
	   	$.ajax({
		   	type:"GET",
		   	dataType:"json",  
	    	url:'../../infoAction!getLastSevenInfo.action?infoPage=1&isnew=true',
	    	success:function(result){
	    		$.each(result.InfoList, function(i, item) {
	                $("#tbody").append(
	                		" <tr id=tr"+item.uuid+">"+
	                         "<td>"+eval(i+1)+"</td>"+
	                        " <td><a style='color:#333333' href=LookNews.html?infoid="+item.uuid+">"+item.title+"</a></td>"   +         
	                         "<td>"+item.author+"</td>"+
	                         "<td>"+item.recordTime+"</td>"+
	                         "<td>"+ 
	                         "<span class='glyphicon glyphicon-edit'></span>" +
	                          "<span onclick=lookNew("+item.uuid+") id='lookActivity' style='cursor:pointer'>查看</span>"+
	                          " <span class='glyphicon glyphicon-edit'></span>" +
	                          "<span onclick=editorNews("+item.uuid+") id='editorNews' style='cursor:pointer'>编辑</span>"+
	                         "  <span class='glyphicon glyphicon-trash'></span>" +
	                         "<span onclick=deleteNews("+item.uuid+") id='deleteNews' style='cursor:pointer'>删除</span>"+
	                         "</td>"+
	                       "</tr> "
	                );
	            });
	            $.ajax({
                     type: "POST",
                     url: "../../infoAction!getInfoNumber.action?isnew=true",
                     success: function(newsNumber){
//                    	 globalNewsNumber=newsNumber;
                    	 $("#newsTotal").text(newsNumber);
                    	 createPage(7, (newsNumber/7+1), newsNumber);
                     }
	            });
	    		   
	 		}
	    });
	    console.log( "success" );
	 });
}); 


function editorNews(uuid){
	window.location = "AddNews.html?uuid="+uuid;
}
function lookNew(uuid){
	window.location = "LookNews.html?infoid="+uuid;
}
function deleteNews(uuid){
	if(window.confirm('确认删除该新闻？')){
	    $.ajax({
	        type: "POST",
	        url: "../../infoAction!deleteInfo.action?isnew=true&infoid="+uuid,
	        success: function(result){
	        	if(result=="true"){
//	        		alert("删除新闻成功")
	        		 $("#newsTotal").text(eval($("#newsTotal").html())-1);
	        		$("#tr"+uuid).remove();
	        	}else{
	        		alert("删除新闻失败")
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
//    	   		alert("hahhahha ");
    		   	type:"GET",
    		   	dataType:"json",  
    	    	url:'../../infoAction!getLastSevenInfo.action?infoPage='+eval(pageIndex+1)+'&isnew=true',
    	    	success:function(result){
    	    		console.log(eval(pageIndex+1));
    	    		 $("#tbody").empty();
    	    		$.each(result.InfoList, function(i, item) {
    	              $("#tbody").append(
    	            		  " <tr id=tr"+item.uuid+">"+
 	                         "<td>"+eval(i+1)+"</td>"+
 	                        " <td><a style='color:#333333' href=LookNews.html?infoid="+item.uuid+">"+item.title+"</a></td>"   +         
 	                         "<td>"+item.author+"</td>"+
 	                         "<td>"+item.recordTime+"</td>"+
 	                         "<td>"+ 
 	                         "<span class='glyphicon glyphicon-edit'></span>" +
 	                         "<span onclick=lookNew("+item.uuid+") id='lookActivity' style='cursor:pointer'>查看</span>"+
 	                         " <span class='glyphicon glyphicon-edit'></span>" +
 	                         "<span onclick=editorNews("+item.uuid+") id='editorNews'>编辑</span>"+
 	                         "  <span class='glyphicon glyphicon-trash'></span>" +
 	                         "<span onclick=deleteNews("+item.uuid+") id='deleteNews'>删除</span>"+
 	                         "</td>"+
    	                       "</tr> "
    	                );
    	            })
    	 		}
    	    })
        }
    });
}

