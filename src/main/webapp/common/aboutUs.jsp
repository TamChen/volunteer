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
    <style>
    	.filelist li{
    		margin-top:10px;
    		margin-left:30px;
    		font-family:'微软雅黑';
    	}
    </style>
  </head>
  
  <body>
     <body>
<jsp:include  page="../head.jsp"/>
      
        <div id="content">
            <div class="content-left">
                <div class="volunteer-logo"></div>
                <div class="volunteer-search" style="height:150px;">
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

                </div>
                <div id="rolling_notice">
                    <div id="rolling_notice_head">
                        <i id="rolling_notice_head_icon"></i>
                        <span id="rolling_notice_head_title">滚动文档</span>
                    </div>
                    <div style="clear:both"></div>
                     <div id="rolling_notice_body">
                        <ul>
                            <li><a href="#">2014年第三十七批在职名单公示</a></li>
                            <li><a href="#">2014年第三十七批在职名单公示</a></li>
                            <li><a href="#">2014年第三十七批在职名单公示</a></li>
                            <li><a href="#">2014年第三十七批在职名单公示</a></li>
                            <li><a href="#">2014年第三十七批在职名单公示</a></li>
                            <li><a href="#">2014年第三十七批在职名单公示</a></li>
                            <li><a href="#">2014年第三十七批在职名单公示</a></li>
                            <li><a href="#">2014年第三十七批在职名单公示</a></li>
                        </ul>
                    </div>
                </div> 
            </div>

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
                <ol class="filelist" style="text-align:left;margin-left:100px;margin-top:100px;">
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	<li><a href="">关于举办第三节长沙理工义工活动周的相关文件</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>2015.3.12</span></li>
                	
                	
                	
                </ol>
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
        <script type="text/javascript" src="static/js/jqPaginator.js"></script>
        <script type="text/javascript" src="static/js/head.js"></script>
<script type="text/javascript">
window.onload=function()
{
    var oDiv=document.getElementById('rolling_notice_body');
    var oUl=oDiv.getElementsByTagName('ul')[0];
    var aLi=oUl.getElementsByTagName('li');
    var speed=4;
    oUl.innerHTML=oUl.innerHTML+oUl.innerHTML;
    oUl.style.height=aLi[0].offsetHeight*aLi.length+'px';
    function move(){
        if (oUl.offsetTop<-oUl.offsetHeight/2) {
            oUl.style.top='0';
        }
        oUl.style.top=oUl.offsetTop-speed+'px';
    }

    var timer=setInterval(move,35);

    oDiv.onmouseover=function ()
    {
        clearInterval(timer);
    };

    oDiv.onmouseout=function()
    {
        timer=setInterval(move,35);
    };
};
</script>

<script type="text/javascript">
function showLocale(objD)
{
    var str,colorhead,colorfoot;
    var yy = objD.getYear();
    if(yy<1900) yy = yy+1900;
    var MM = objD.getMonth()+1;
    if(MM<10) MM = '0' + MM;
    var dd = objD.getDate();
    if(dd<10) dd = '0' + dd;
    var ww = objD.getDay();
    if  ( ww==0 )  colorhead="<font color=\"#b5b5b5\">";
    if  ( ww > 0 && ww < 6 )  colorhead="<font color=\"#b5b5b5\">";
    if  ( ww==6 )  colorhead="<font color=\"#b5b5b5\">";
    if  (ww==0)  ww="星期日";
    if  (ww==1)  ww="星期一";
    if  (ww==2)  ww="星期二";
    if  (ww==3)  ww="星期三";
    if  (ww==4)  ww="星期四";
    if  (ww==5)  ww="星期五";
    if  (ww==6)  ww="星期六";
    colorfoot="</font>"
    str = colorhead + yy + "年" + MM + "月" + dd +"日"+ "  " + ww + colorfoot;
    return(str);
}
function tick()
{
    var today;
    today = new Date();
    document.getElementById("Time").innerHTML = showLocale(today);
    window.setTimeout("tick()", 1000);
}

</script>   
  </body>
</html>
