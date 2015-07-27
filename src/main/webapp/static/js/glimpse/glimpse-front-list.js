$(function(){
	document.getElementById("glimpse").style.background="#62C5E4";
 	document.getElementById("glimpse").style.color="white";
});				
var obj,j;
				var page=0;
				var nowPage=0;//当前页
				var listNum=9;//每页显示<ul>数
				var PagesLen;//总页数
				var PageNum=4;//分页链接接数(5个)
				var json;
				onload=function(){
				//obj=document.getElementById("www_zzjs_net").getElementsByTagName("tr");
				//j=obj.length;
				//PagesLen=Math.ceil(j/listNum);
				var xmlhttp;
				if (window.XMLHttpRequest)
				  {// code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				  }
				else
				  {// code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				  }
				xmlhttp.open("POST","pictureAction!listActPicture.action",true);
				xmlhttp.send();
				xmlhttp.onreadystatechange=function()
				  {
					  if (xmlhttp.readyState==4 && xmlhttp.status==200)
					    {
					   			 var result = xmlhttp.responseText;
				            	  json = eval("(" + result + ")");
				            	 j=json.Acts.length;
								 PagesLen=Math.ceil(j/listNum);
				            	 listActPicture(json,0);
								 upPage(0);
					    }
	  				};
	  				
	  					var xmlhttp_1;
				if (window.XMLHttpRequest)
				  {// code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp_1=new XMLHttpRequest();
				  }
				else
				  {// code for IE6, IE5
				  xmlhttp_1=new ActiveXObject("Microsoft.XMLHTTP");
				  }
				xmlhttp_1.open("POST","pictureAction!listPerPicture.action",true);
				xmlhttp_1.send();
				xmlhttp_1.onreadystatechange=function()
				  {
					  if (xmlhttp_1.readyState==4 && xmlhttp_1.status==200)
					    {
					   			 var result_1 = xmlhttp_1.responseText;
				            	  json_1= eval("(" + result_1 + ")");
				            	 j_1=json_1.Pers.length;
								 PagesLen_1=Math.ceil(j_1/listNum_1);
				            	 listPerPicture(json_1,0);
								 upPage_1(0);
					    }
	  				};
				
				};
				
				function listActPicture(json,page){
								 var i;
				            	 var div =  document.getElementById("picture_top");
				            	 div.innerHTML='';
								 for(i=page*9;(i-page*9)<listNum&&(i-page*9)<(json.Acts.length-page*9);i++){
								 	
								 		 	var img=document.createElement("div");
								 		 	img.innerHTML="<a href='activityAction!listAction?id="+json.Acts[i].activityId+"'><div id ='imgtop_"+i+"'></div><strong>"+json.Acts[i].name+"</strong></a>";
										 	 img.id="img_div";
										    div.appendChild(img); 
										    var picture=document.getElementById("imgtop_"+i);
										  	picture.style.backgroundImage="url('uploadPicture/"+json.Acts[i].path+".jpg')";
										  	picture.style.width="210px";
										  	picture.style.height="130px";
										  	picture.style.backgroundPosition="center";
										  	/* 图片上传要压缩图片210 130 */
										  	
								 }
				}
				
				function upPage(p){
				nowPage=p;
				strS='<a id="herf" onclick="upPage(0);listActPicture(json,0)">首页</a>  ';
				var PageNum_2=PageNum%2==0?Math.ceil(PageNum/2)+1:Math.ceil(PageNum/2);
				var PageNum_3=PageNum%2==0?Math.ceil(PageNum/2):Math.ceil(PageNum/2)+1;
				var strC="",startPage,endPage;
				if (PageNum>=PagesLen) 
				{
					startPage=0;
					endPage=PagesLen-1;
					}
					else if (nowPage<PageNum_2)
						{
							startPage=0;
							endPage=PagesLen-1>PageNum?PageNum:PagesLen-1;
						}//首页
							else {
								startPage=nowPage+PageNum_3>=PagesLen?PagesLen-PageNum-1: nowPage-PageNum_2+1;
								var t=startPage+PageNum;
								endPage=t>PagesLen?PagesLen-1:t;
								}
					for (var i=startPage;i<=endPage;i++){
				 if (i==nowPage)
				 		strC+='<a  id="herf" style="color:red;font-weight:700;" onclick="upPage('+i+');listActPicture(json,'+i+')">'+(i+1)+'</a> ';
				 else 
				 		strC+='<a  id="herf" onclick="upPage('+i+');listActPicture(json,'+i+')">'+(i+1)+'</a> ';
				}
				strE=' <a id="herf"  onclick="upPage('+(PagesLen-1)+');listActPicture(json,'+(PagesLen-1)+')">尾页</a>  ';
				strE2=nowPage+1+"/"+PagesLen+"页"+"  共"+j+"条";
				document.getElementById("changpage_1").innerHTML=strS+strC+strE+strE2;
				}
				
				
				var obj_1,j_1;
				var page_1=0;
				var nowPage_1=0;//当前页
				var listNum_1=3;//每页显示<ul>数
				var PagesLen_1;//总页数
				var PageNum_1=4;//分页链接接数(5个)
				var json_1;
			
				function listPerPicture(json,page){
								 var i;
				            	 var div =  document.getElementById("picture_buttom");
				            	 div.innerHTML='';
								 for(i=page*3;i-page*3<listNum_1&&(i-page*3)<(json.Pers.length-page*3);i++){
								 	
								 		 	var img=document.createElement("div");
								 		 	img.innerHTML="<a href='userAction!showUserAction?id="+json.Pers[i].id+"'><div id ='imgbuttom_"+i+"'></div><strong>"+json.Pers[i].username+"</strong></a>";
										   img.id="img_div";
										    div.appendChild(img); 
										     var picture=document.getElementById("imgbuttom_"+i);
										  	picture.style.backgroundImage="url('uploadPicture/"+json.Pers[i].outstandingPicture+".jpg')";
										  	picture.style.width="210px";
										  	picture.style.height="130px";
										  	picture.style.backgroundPosition="center";
										  	/* 图片上传要压缩图片210 130 */
								 }
				}
				
				function upPage_1(p){
				nowPage_1=p;
				strS_1='<a id="herf" onclick="upPage_1(0);listPerPicture(json_1,0)">首页</a>  ';
				var PageNum_2=PageNum%2==0?Math.ceil(PageNum/2)+1:Math.ceil(PageNum/2);
				var PageNum_3=PageNum%2==0?Math.ceil(PageNum/2):Math.ceil(PageNum/2)+1;
				var strC_1="",startPage_1,endPage_1;
				if (PageNum_1>=PagesLen_1) 
				{
					startPage_1=0;
					endPage_1=PagesLen_1-1;
					}
					else if (nowPage_1<PageNum_2)
						{
							startPage_1=0;
							endPage_1=PagesLen_1-1>PageNum_1?PageNum_1:PagesLen_1-1;
						}//首页
							else {
								startPage_1=nowPage_1+PageNum_3>=PagesLen_1?PagesLen_1-PageNum_1-1: nowPage_1-PageNum_2+1;
								var t=startPage_1+PageNum_1;
								endPage_1=t>PagesLen_1?PagesLen_1-1:t;
								}
					for (var i=startPage_1;i<=endPage_1;i++){
				 if (i==nowPage_1)
				 		strC_1+='<a id="herf"  style="color:red;font-weight:700;" onclick="upPage_1('+i+');listPerPicture(json_1,'+i+')">'+(i+1)+'</a> ';
				 else 
				 		strC_1+='<a  id="herf" onclick="upPage_1('+i+');listPerPicture(json_1,'+i+')">'+(i+1)+'</a> ';
				}
				strE_1=' <a id="herf" onclick="upPage_1('+(PagesLen_1-1)+');listPerPicture(json_1,'+(PagesLen_1-1)+')">尾页</a>  ';
				strE2_1=nowPage_1+1+"/"+PagesLen_1+"页"+"  共"+j_1+"条";
				document.getElementById("changpage_2").innerHTML=strS_1+strC_1+strE_1+strE2_1;
				}
				
				/*搜索功能代码  */
				var actOrPer="";
				var searchResult;
				
				var obj_search,j_search;
				var page_search=0;
				var nowPage_search=0;//当前页
				var listNum_search=12;//每页显示<ul>数
				var PagesLen_search;//总页数
				var PageNum_search=4;//分页链接接数(5个)
				
				function listSearchPicture(searchResult,page,actOrPer){
								 var i;
				            	 var div =  document.getElementById("searchResult");
				            	 div.innerHTML='';
				            	 var path;
								 for(i=page*12;i-page*12<listNum_search&&(i-page*12)<(searchResult.pic.length-page*12);i++){
								 			var searchDiv=document.createElement("div");
								 		 	var img=document.createElement("div");
								 		 	
								 		 	if(actOrPer=="act"){
								 		 	path=searchResult.pic[i].path;
								 		 		img.innerHTML="<a href='activityAction!showActivityAction?id="+searchResult.pic[i].activityId+"'><div id ='imgsearch_"+i+"'></div><strong>"+searchResult.pic[i].name+"</strong></a>";
										   }
										   	else{
										   	path=searchResult.pic[i].outstandingPicture;
										   		img.innerHTML="<a href='userAction!showUserAction?id="+searchResult.pic[i].id+"'><div id ='imgsearch_"+i+"'></div><strong>"+searchResult.pic[i].username+"</strong></a>";
										   	}
										   	img.id="img_div";
										   	img.style.left="50px";
										  	searchDiv.appendChild(img); 
										  	div.appendChild(searchDiv);
										  	  var picture=document.getElementById("imgsearch_"+i);
										  	picture.style.backgroundImage="url('uploadPicture/"+path+".jpg')";
										  	picture.style.width="210px";
										  	picture.style.height="130px";
										  	picture.style.backgroundPosition="center";
								 }
								 var page = document.createElement("div");
								var center = document.getElementById("content-center")
								 page.id="changpage_3";
								 page.style.float="right";
								 center.appendChild(page); 
				
								
				}
				
				function upPage_search(p,actOrPer){
				nowPage_search=p;
				strS_search='<a id="herf" onclick="upPage_search(0,actOrPer);listSearchPicture(searchResult,0,actOrPer)">首页</a>  ';
				var PageNum_2=PageNum%2==0?Math.ceil(PageNum/2)+1:Math.ceil(PageNum/2);
				var PageNum_3=PageNum%2==0?Math.ceil(PageNum/2):Math.ceil(PageNum/2)+1;
				var strC_search="",startPage_search,endPage_search;
				if (PageNum_search>=PagesLen_search) 
				{
					startPage_search=0;
					endPage_search=PagesLen_search-1;
					}
					else if (nowPage_search<PageNum_2)
						{
							startPage_search=0;
							endPage_search=PagesLen_search-1>PageNum_search?PageNum_search:PagesLen_search-1;
						}//首页
							else {
								startPage_search=nowPage_search+PageNum_3>=PagesLen_search?PagesLen_search-PageNum_search-1: nowPage_search-PageNum_2+1;
								var t=startPage_search+PageNum_search;
								endPage_search=t>PagesLen_search?PagesLen_search-1:t;
								}
					for (var i=startPage_search;i<=endPage_search;i++){
				 if (i==nowPage_search)
				 		strC_search+='<a id="herf"  style="color:red;font-weight:700;" onclick="upPage_search('+i+',actOrPer);listSearchPicture(searchResult,'+i+',actOrPer)">'+(i+1)+'</a> ';
				 else 
				 		strC_search+='<a  id="herf" onclick="upPage_search('+i+',actOrPer);listSearchPicture(searchResult,'+i+',actOrPer)">'+(i+1)+'</a> ';
				}
				strE_search=' <a id="herf" onclick="upPage_search('+(PagesLen_search-1)+',actOrPer);listSearchPicture(searchResult,'+(PagesLen_search-1)+',actOrPer)">尾页</a>  ';
				strE2_search=nowPage_search+1+"/"+PagesLen_search+"页"+"  共"+j_search+"条";
				document.getElementById("changpage_3").innerHTML=strS_search+strC_search+strE_search+strE2_search;
				}
				function clickAct(){
					actOrPer="act";
					document.getElementById("Per").style.color="#989A99";
					document.getElementById("Act").style.color="red";
				};
				function clickPer(){
					actOrPer="per";
					document.getElementById("Act").style.color="#989A99";
					document.getElementById("Per").style.color="red";
				};
				function clickButton(){
				searchResult=null;
					 var text = document.getElementById("form").value;
					 if(text==""){
					 	alert("请输入要搜索的活动名称！");
					 }else if(actOrPer==""){
					 	alert("请选择你要搜索的对象！");
					 }
					 else{
						var xmlhttp;
						if (window.XMLHttpRequest)
						  {// code for IE7+, Firefox, Chrome, Opera, Safari
						  xmlhttp=new XMLHttpRequest();
						  }
						else
						  {// code for IE6, IE5
						  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
						  }
						xmlhttp.open("POST","pictureAction!findPicture.action?name="+text+"&actOrPer="+actOrPer,true);
						xmlhttp.send();
						xmlhttp.onreadystatechange=function()
						  {
							  if (xmlhttp.readyState==4 && xmlhttp.status==200)
							    {
							   			 var result = xmlhttp.responseText;
						            	  searchResult = eval("(" + result + ")");
						            	  if($.isEmptyObject(searchResult)){
							  				var center = document.getElementById("searchResult");
							  				center.innerHTML="<br><br><br><br><br><br><br><br><br><h1>没有你想要查询的结果！　^_^|||</h1>";
							  			}
							  				 
							  			else{
							  				j_search=searchResult.pic.length;
								 			PagesLen_search=Math.ceil(j_search/listNum_search);
							  				if(actOrPer=="act"){
							  					listSearchPicture(searchResult,0,"act");
							  					upPage_search(0,"act");	
							  				}
							  				else{
							  					listSearchPicture(searchResult,0,"per");
							  					upPage_search(0,"per");	
							  				}
							    }
			  				};
			  				
			  			
			  			};
							 }
				}