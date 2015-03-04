<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'new-detail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
 	<!-- Site Theme Styling -->
    <link rel="stylesheet" href="static/style/layout/reset.css" />
    <link rel="stylesheet" href="static/style/layout/index.css" />
    <link rel="stylesheet" href="static/style/layout/global.css" />

    <!-- Styles --> 
    <link type="text/css" href="static/style/layout/custom-theme/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
    <link href="static/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="static/style/layout/demo.css" rel="stylesheet">
    <link href="static/style/layout/new_content.css" rel="stylesheet" type="text/css"/>
    <link href="static/style/layout/rolling_notice.css" rel="stylesheet" type="text/css"/>
	

  </head>
  
  <body>
	<div id="header">
	            <div class="fix">
	            </div>
	            <div class="navigation">
	                <div class="navicontainer">
	                    <!--学校logo-->
	                    <div id="schoollogo">
	                        <a href="#"><img border="0" src="static/style/images/schoollogo.png" /></a>
	                    </div>
	
	                    <ul id="nav-1"> 
	                        <li></li>
	                        <li><a href="#">平台首页</a></li> 
	                        <li><a href="#">活动大厅</a></li> 
	                        <li><a href="#">风采走廊</a></li> 
	                        <li><a href="#">精彩走廊</a></li> 
	                        <li><a href="#">关于我们</a></li> 
	                    </ul> 
	                    <div id="login">
	                        <div id="top">
	                            <a href="#">学号登录</a>
	                        </div>
	                        <div id="middle"></div>
	                        <div id="button"></div>
	                    </div>
	                </div>
	            </div>
	        </div>
	      
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
	                    <div id="location">
	                        <span><a href="#">站内新闻</a><b>当前所在位置</b></span>
	                    </div>
	                    <div id="new_content_head">
	                        <div id="new_content_title">辽宁省累计投入200多万人抗旱减灾</div>
	                        <div id="new_content_info">发布时间：<span id="time">2014-08-19</span>&nbsp;&nbsp;&nbsp;&nbsp;发布人：<span id="autor">admin</span>&nbsp;&nbsp;&nbsp;&nbsp;点击人数：<span id="count">145</span></div>
	                        <div id="share">分享到：
	                        <a href="#"><img src="static/style/img/renren.gif"></a>
	                        <a href="#"><img src="static/style/img/kongjian.gif"></a>
	                        <a href="#"><img src="static/style/img/tengxun.gif"></a>
	                        <a href="#"><img src="static/style/img/xinlang.gif"></a>
	                        </div>
	                        <div id="collect"><a href="#">收藏</a></div>
	                    </div>
	                    <div id="new_content_body"><img src="static/style/img/new_photo.gif">
	                    <br /><br /><br /><br />
	                        <p>&nbsp;&nbsp;&nbsp;&nbsp;8月18日，从辽宁省防汛抗旱指挥部获悉，截至8月17日，全省累计投入抗旱人数201.92万人，开动机电井21.3万眼，泵站1554座，投入抗旱机动设备28.5万台套，机动运水车12万辆，累计投入资金3.99亿元，完成抗旱一次浇灌面积846.96万亩，临时解决了前期出现的32.3万人、7.5万头大牲畜饮水困难。</p>
	                        <p>
	                            &nbsp;&nbsp;&nbsp;&nbsp;据统计，7月1日8时至8月17日8时，辽宁省降水持续偏少，全省平均降水量为96.4毫米，比多年同期285.3毫米少66.2%。由于降水偏少，造成辽宁省水利工程蓄水不足、全省土壤失墒严重。8月17日8时，全省27座大型水库蓄水量为40.84亿立方米，比2013年同期57.22亿立方米少16.38亿立方米。目前，全省作物受旱面积达2778.82万亩，其中干枯549.44万亩，重旱108点27万亩，轻旱1141.11万亩；全省有27.49万人、7.45万头大牲畜因旱临时饮水困难。
	                        </p>
	                    </div>
	                    <div id="new_content_next">
	                        <ul>
	                            <li>上一篇：<a href="#">江西省举行座谈会纪念</a></li>
	                            <li>下一篇：<a href="#">发展改革委颁布抽水蓄</a></li>
	                        </ul>
	                    </div>
	                </div>
	            </div>
	            <div class="clean"></div>
	        </div>
	        <div id="footer">
	            <div class="container">
	                </br>
	                <p>长沙理工大学义工平台版权所有&nbsp;&nbsp;技术支持：梦之站</p>
	                <p>主校区地址：长沙市（雨花区万家丽南路2段960号&nbsp;邮箱：410114）</p>
	                <p>ICP证号：湘ICP备0500000</p>
	            </div>
	        </div>
	
	        <!--scripts-->
	        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
	
	        <!--init for this page-->
	        <script type="text/javascript" src="js/demo.js"></script>
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
