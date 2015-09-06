var UserTotalNumber;//总记录数
var UserListSize=10;//表示每页展示多少条
$(document).ready(function(){
	$(function(){
	   	$.ajax({
		   	type:"GET",
		   	dataType:"json",  
	    	url:'../../userAction!getAllUserList.action?current=1&&userListSize='+UserListSize,
	    	success:function(result){
	    		$.each(result.userList, function(i, item) {
	                $("#tbody").append(
	                		" <tr id=tr"+item.id+">"+
	                        "<td>"+eval(i+1)+"</td>"+
	                        "<td><a style='color:#333333' href=UserDetail.html?id="+item.id+">"+item.userno+"</a></td>"+//学号
	                        " <td><a style='color:#333333' href=UserDetail.html?id="+item.id+">"+item.username+"</a></td>"   +         
	                        "<td>"+item.userCollege+"</td>"+//学院
	                        "<td>"+item.userGrade+"</td>"+//专业
	                        "<td>"+item.userTel+"</td>"+//电话
	                        "<td>"+item.workTime+"</td>"+//工作时长
	                        "<td>"+item.userRate+"</td>"+//排名
	                        "<td>"+" <span class='glyphicon glyphicon-edit'></span>" +
	                        "<span onclick=LookUserDetail("+item.userno+") id='lookUserDetail' style='cursor:pointer'>查看</span>"+
	                        "<span class='glyphicon glyphicon-edit'></span>" +
	                        "<span onclick=editUserDetail("+item.userno+") id='editUserDetail' style='cursor:pointer'>修改</span>"+
	                        "<span class='glyphicon glyphicon-trash'></span>" +
	                        "<span onclick=lockUser("+item.userno+") id='lockUser' style='cursor:pointer'>锁定</span>"+
	                        "</td>"+
	                        "</tr> "
	                );
	            })
	            
	            $.ajax({
                     type: "POST",
                     url: "../../userAction!getUserTotalNumber.action",
                     success: function(UserTotalNumber){
                    	 globalNewsNumber=UserTotalNumber;
                    	 $("#UserTotal").text(UserTotalNumber);
                 	 createPage(UserListSize, (UserTotalNumber/UserListSize+1), UserTotalNumber);
                  }
	            });
	    		   
	 		}
	    })
	    console.log( "success" );
	 });

}); 

function LookUserDetail(id){
	window.location = "UserDetail.html?userno="+id;
}

function editUserDetail(id){
	window.location = "EditUser.html?userno="+id;
}

function lockUser(id){
	alert("该功能正在完善！");
	/*if(window.confirm('确认锁定该用户，该用户将不能登陆！')){
	    $.ajax({
	        type: "POST",
	        dataType:"json",
	        url: "../../userAction!lockUser.action?userno="+id,
	        success: function(result){
	        	if(result.result==true){
	        		alert("成功锁定")
	        	}else{
	        		alert("锁定失败")
	        	}
	        }
	    });
	}else{
		
	}*/
	
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
    	    	url:'../../userAction!getAllUserList.action?current='+eval(pageIndex+1)+'&&userListSize='+UserListSize,
    	    	success:function(result){
    	    		 $("#tbody").empty();
    	    		 $.each(result.userList, function(i, item) {
    		                $("#tbody").append(
    		                		" <tr id=tr"+item.id+">"+
    		                         "<td>"+eval(i+1)+"</td>"+
    		                         "<td>"+item.userno+"</td>"+//学号
    		                        " <td><a style='color:#333333' href=UserDetail.html?id="+item.id+">"+item.username+"</a></td>"   +         
    		                        "<td>"+item.userCollege+"</td>"+//学院
    		                        "<td>"+item.userGrade+"</td>"+//专业
    		                        "<td>"+item.userTel+"</td>"+//电话
    		                        "<td>"+item.workTime+"</td>"+//工作时长
    		                        "<td>"+item.userRate+"</td>"+//排名
    		                         "<td>"+" <span class='glyphicon glyphicon-edit'></span>" +
    		                          "<span onclick=LookUserDetail("+item.id+") id='lookUserDetail' style='cursor:pointer'>查看</span>"+
    		                          " <span class='glyphicon glyphicon-edit'></span>" +
    		                          "<span onclick=editUserDetail("+item.id+") id='editUserDetail' style='cursor:pointer'>修改</span>"+
    		                         "  <span class='glyphicon glyphicon-trash'></span>" +
    		                         "<span onclick=lockUser("+item.id+") id='lockUser' style='cursor:pointer'>锁定</span>"+
    		                         "</td>"+
    		                       "</tr> "
    		                );
    		            })
    	 		}
    	    })
        }
    });
}

