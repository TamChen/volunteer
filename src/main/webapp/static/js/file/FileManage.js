var infonumber;//总记录数
var size=7;//表示每页展示多少条

$(function(){
    initInfo();
});


/*function look(id){
	window.location.href= "backend/info/infodetail.jsp?id="+id;
}
*/
function edit(id){
	window.location = "editFile.html?id="+id;
}
//删除文件
function deleteInfo(id){
	if(window.confirm('确认删除该文件？')){
	    $.ajax({
	        type: "GET",
	        dataType:"json",
	        url: "../../downloadAction!delete.action?id="+id,
	        success: function(result){
	        	if(result.success==true){
	        		$("#tr"+id).remove();
	        		/*$("#total").text(globalNewsNumber-1);*/
	        	}else{
	        		alert("删除文件失败")
	        	}
	        }
	    });
	}else{
	}
}

function initInfo(){
	$.ajax({
	   	type:"GET",
	   	dataType:"json",  
    	url:'../../downloadAction!getInfo.action?current=1&&size='+size,
    	success:function(result){
    		loadInfo(result);
            $.ajax({
                 type: "GET",
             	 dataType:"json",  
             	 url: "../../downloadAction!getNumber.action",
                 success: function(result){
                	 var total1=result.number;
                	 globalNewsNumber=total1;
                	 $("#fileTotal").text(total1);
             	 createPage(size,(total1/size+1), total1);
              }
            });
            console.log( "消息一成功" );
 		}
    })
}
function loadInfo(result){
	 $("#tbody").empty();
	$.each(result.infoList, function(i, item) {
		/*这里可以点击名字进行下载，跟前台一样*/
        $("#tbody").append(
        		" <tr id=tr"+item.id+">"+
                 "<td>"+eval(i+1)+"</td>"+
                " <td style='max-width:700px;'><a style='color:#333333' href='../../downloadAction!download.action?fileno="+item.id+"'>"+item.name+"</a></td>"   +         
                "<td>"+item.time+"</td>"+//时间
                 "<td>"+" <span class='glyphicon glyphicon-edit'></span>" +
                  "<span onclick=edit("+item.id+") id='edit' style='cursor:pointer'>编辑</span>"+
                 "  <span class='glyphicon glyphicon-trash'></span>" +
                 "<span onclick=deleteInfo("+item.id+") id='delete' style='cursor:pointer'>删除</span>"+
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
    		   	url:'../../downloadAction!getInfo.action?current='+eval(pageIndex+1)+'&&size='+size,
    	    	success:function(result){
    	    		loadInfo(result);
    	 		}
    	    })
        }
    });
}

