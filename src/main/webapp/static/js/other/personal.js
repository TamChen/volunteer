$(function(){
	$.ajax({
	   	type:"GET",
	   	dataType:"json",
    	url:'noticeAction!getUserInfoNum.action',
    	success:function(result){
    		var info = document.getElementById("info");
    		if(result.infonum==0){
    			info.style.backgroundColor="#706E6E";
    			info.innerHTML=result.infonum;
    		}else{
    			info.style.backgroundColor="red";
    			info.innerHTML=result.infonum;
    		}
 		}
    });
});