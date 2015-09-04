<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>activity-detail</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- Site Theme Styling -->
<link rel="stylesheet" href="static/style/layout/reset.css" />
<link rel="stylesheet" href="static/style/layout/index.css" />
<!-- Styles -->
<link type="text/css"
	href="static/style/layout/custom-theme/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" />
<link href="static/bootstrap/bootstrap.css" rel="stylesheet">

<link href="static/style/layout/demo.css" rel="stylesheet">
<link href="static/style/layout/activity-list.css" rel="stylesheet"
	type="text/css" />
	<link href="static/style/layout/bootstrap.min.css" rel="stylesheet">
</head>
    <link href="static/style/layout/new_content.css" rel="stylesheet" type="text/css"/>
    <link href="static/style/layout/rolling_notice.css" rel="stylesheet" type="text/css"/>
    
       <link href="static/style/layout/new_list.css" rel="stylesheet" type="text/css"/>
       	<link href="static/style/layout/bootstrap.min.css" rel="stylesheet">
       	    <link href="static/style/layout/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="static/jBootstrapPage/jBootsrapPage.css"></script>
    <style>
    	#filelist li{
    		margin-top:10px;
    		margin-left:30px;
    		font-family:'微软雅黑';
    		list-style-type:decimal;
    	}
    	#filelist li a{
    	}
    </style>
  </head>
  
  <body>
     <body>
<jsp:include  page="../head.jsp"/>
      
        <div id="content">
            <div class="content-left">
                <div class="volunteer-logo"></div>
                <div class="volunteer-search" style="height:180px;">
                    <div class="hot-search"></div>
                    <div class="search-key">
                        <a href="">活动文档</a>
                    </div>
                    
                    <div class="in-search">
                        <div class="input">
                        <p>输入关键字：</p>
                        </div>

                        <div class="input-group">
                            <input type="text" class="form-control">
                        </div>
                        <div class="search">
                        <a href=""><img src="static/style/images/search.jpg"></a>
                        </div>
                    </div>

                </div></div>

            <div class="content-minddle">
                    <!--这里是大框-->
                    <!--这里是上面部分-->
            <div class="location">
				新闻公告《当前位置
			</div>
                <div id="content_right_head">
                <div style="margin-left:30px;text-align:left;font-family:'微软雅黑';font-size:17px;">文档中心</div><%--
				
                    <h2 id="content_right_head_title">文档中心</h2>
	                   <div id="time_week">
	                        <span id="Time"></span>
	                        <span id="week">2014-2015学年第一学期&nbsp;第十六周</span>
	                    </div>
                	--%></div>
                <!--这里是列表那个div-->
                <ol id="filelist" style="text-align:left;list-style-type:decimal;margin-left:100px;margin-top:100px;">
                
                	
                </ol>
                <ul class="pagination" id="pagination1" 
			style="font-family:'\5FAE\8F6F\96C5\9ED1';margin-left:450px;margin-top:20px;";></ul>
			<%--<div id="yesterday_new">
			</div>
            </div>
            <br/>
            <div id="pagination-new">
            	<ul class="pagination" id="pagination1" style="font-family:'\5FAE\8F6F\96C5\9ED1';margin-left:550px;margin-top:20px;";></ul>
            </div>
            --%><input id="total" type="hidden" value=10>
        </div>
        </div>
  		<jsp:include  page="../foot.jsp"/>
<input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>
        <!--scripts-->
        <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="static/js/filecenter.js"></script>
        <script type="text/javascript" src="static/js/head.js"></script>
		  <script type="text/javascript" src="static/jBootstrapPage/jBootstrapPage.js"></script>
		<script type="text/javascript">
		  	var height=window.screen.height;
	    	var trueheight=height-420;
	    	$("#content").css("height",trueheight);
	    	var size=15;
	    	var current=1;
	    	$.ajax({
	    	   	type:"GET",
	    	   	dataType:"json",  
	        	url:'downloadAction!getInfo.action?current=1&&size='+size,
	        	success:function(result){
	        		loadInfo(result);
	                $.ajax({
	                     type: "GET",
	                 	 dataType:"json",  
	                     url: "downloadAction!getNumber.action",
	                     success: function(result){
	                    	 var total1=result.number;
	                    	 globalNewsNumber=total1;
	                    	
	                 	 createPage(size,(total1/size+1), total1);
	                  }
	                });
	                console.log( "消息一成功" );
	     		}
	        })
	        function loadInfo(result){
				$("#filelist").html("");
				var array = [];
				$.each(result.infoList, function(i, item) {
					array[i]=
						"<li>"+
                			"<a href=''>"+item.name+"("+item.time+")</a>"+
                		"</li>";		
				});
				$("#filelist").html(array.join(''));
			}
	    	function createPage(pageSize, buttons, total) {
	    	    $("#pagination1").jBootstrapPage({
	    	        pageSize : pageSize,
	    	        total : total,
	    	        maxPageButton:buttons,
	    	        onPageClicked: function(obj, pageIndex) {
	    	    	   	$.ajax({
	    	    		   	type:"GET",
	    	    		   	dataType:"json",  
	    	    	    	url:'downloadAction!getInfo.action?current='+eval(pageIndex+1)+'&&size='+size,
	    	    	    	success:function(result){
	    	    	    		loadInfo(result);
	    	    	 		}
	    	    	    })
	    	        }
	    	    });
	    	}
		</script>
  </body>
</html>
