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

  </head>
  
  <body>
	  <body>
		<jsp:include  page="../head.jsp"/>
	      
	        <div id="content">
	            <div class="content-left">
	                <div class="volunteer-logo"></div>
	                <div class="volunteer-search" style="height:200px;">
	                    <div class="hot-search"></div>
	                    <div class="search-key">
	                        <a href="">通知公告</a>
	                    </div>
	                    <hr>
	                    <div class="search-key">
	                        <a href="">站内新闻</a>
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
	                        <span id="rolling_notice_head_title">滚动通告</span>
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
	               <div id="new_content">
	                    <div class="location">
							活动详情《当前位置
						</div>
	                    <div id="new_content_head">
	                        <div id="new_content_title"></div>
	                        <div id="new_content_info">发布时间：<span id="time"></span>&nbsp;&nbsp;&nbsp;&nbsp;发布人：<span id="author"></span>&nbsp;&nbsp;&nbsp;&nbsp;点击人数：<span id="count">145</span></div>
	                       	<div class="bdsharebuttonbox">
		                       <a href="#" class="bds_more" data-cmd="more"></a>
		                       <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
		                       <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
		                       <a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a>
		                       <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a>
		                       <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
	                       	</div>
							</div>
	                    </div>
	                    <div id="new_content_body">
	                    </div>
	                    
	                   
	                </div>
	            </div>
	             <div id="new_content_next">
	                        <ul>
	                            <li>上一篇：<a href="#">江西省举行座谈会纪念</a></li>
	                            <li>下一篇：<a href="#">发展改革委颁布抽水蓄</a></li>
	                        </ul>
	             </div>
	        </div>
	      <jsp:include  page="../foot.jsp"/>
	<input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>
	        <!--scripts-->
	<script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>

	<script type="text/javascript" src="static/js/news/new-detail.js"></script>
	<script type="text/javascript" src="static/js/head.js"></script>       
	<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
	
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
	tick();
	</script>
  </body>
</html>
