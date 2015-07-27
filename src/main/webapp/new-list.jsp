<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'new-list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Site Theme Styling -->
    <link rel="stylesheet" href="style/layout/reset.css" />
    <link rel="stylesheet" href="style/layout/index.css" />
    <link rel="stylesheet" href="style/layout/global.css" />

    <!-- Styles --> 
    <link type="text/css" href="style/layout/custom-theme/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
    <link href="bootstrap/bootstrap.css" rel="stylesheet">
    <link href="style/layout/demo.css" rel="stylesheet">
    <link href="style/layout/new_list.css" rel="stylesheet" type="text/css"/>
    <link href="style/layout/rolling_notice.css" rel="stylesheet" type="text/css"/>

  </head>
  
  <body>
   <div id="header">
            <div class="fix">
            </div>
            <div class="navigation">
                <div class="navicontainer">
                    <!--学校logo-->
                    <div id="schoollogo">
                        <a href="#"><img border="0" src="style/images/schoollogo.png" /></a>
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
                        <a href=""><img src="style/images/search.jpg"></a>
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
                    <!--这里是大框-->
                    <!--这里是上面部分-->
                <div id="content_right_head">
                    <span id="content_right_head_title">公告新闻</span>
                    <div id="time_week">
                        <span id="Time"></span>
                        <span id="week">2014-2015学年第一学期&nbsp;第十六周</span>
                    </div>
                </div>
                <!--这里是列表那个div-->
                <div id="today_new">
                    <div class="date">
                        <span class="year">2014</span>
                        <span class="day">19</span>
                        <span class="month">8月</span>
                    </div>
                    <div class="content_right_body">
                        <ul>
                            <li><i class="content_right_body_point"></i><a href="#">李克强给新疆维吾尔族学生回信：放飞梦想 健康成长</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">李克强总理对东北等地抗旱工作作出重要批示</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">外交部就习近平对蒙古国进行访问举行媒体吹风会</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">两部委共推国产医疗设备产业转型升级</a></li>
                        </ul>
                        <div class="new_icon"><img src="style/img/new_icon.gif"></div>
                    </div>
                </div>

                <div id="yesterday_new">
                    <div class="date">
                        <span class="year">2014</span>
                        <span class="day">19</span>
                        <span class="month">8月</span>
                    </div>
                    <div class="content_right_body">
                        <ul>
                            <li><i class="content_right_body_point"></i><a href="#">李克强给新疆维吾尔族学生回信：放飞梦想 健康成长</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">李克强总理对东北等地抗旱工作作出重要批示</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">外交部就习近平对蒙古国进行访问举行媒体吹风会</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">两部委共推国产医疗设备产业转型升级</a></li>
                        </ul>
                        <div class="new_icon"><img src="style/img/new_icon.gif"></div>
                    </div>
                    <br /><br /><br /><br /><br /><br /><br /><br /><br />
                    <div class="date">
                        <span class="year">2014</span>
                        <span class="day">19</span>
                        <span class="month">8月</span>
                    </div>
                    <div class="content_right_body">
                        <ul>
                            <li><i class="content_right_body_point"></i><a href="#">李克强给新疆维吾尔族学生回信：放飞梦想 健康成长</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">李克强总理对东北等地抗旱工作作出重要批示</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">外交部就习近平对蒙古国进行访问举行媒体吹风会</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">两部委共推国产医疗设备产业转型升级</a></li>
                        </ul>
                        <div class="new_icon"><img src="style/img/new_icon.gif"></div>
                    </div>
                    <br /><br /><br /><br /><br /><br /><br /><br /><br />
                    <div class="date">
                        <span class="year">2014</span>
                        <span class="day">19</span>
                        <span class="month">8月</span>
                    </div>
                    <div class="content_right_body">
                        <ul>
                            <li><i class="content_right_body_point"></i><a href="#">李克强给新疆维吾尔族学生回信：放飞梦想 健康成长</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">李克强总理对东北等地抗旱工作作出重要批示</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">外交部就习近平对蒙古国进行访问举行媒体吹风会</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">两部委共推国产医疗设备产业转型升级</a></li>
                        </ul>
                        <div class="new_icon"><img src="style/img/new_icon.gif"></div>
                    </div>
                    <br /><br /><br /><br /><br /><br /><br /><br /><br />
                    <div class="date">
                        <span class="year">2014</span>
                        <span class="day">19</span>
                        <span class="month">8月</span>
                    </div>
                    <div class="content_right_body">
                        <ul>
                            <li><i class="content_right_body_point"></i><a href="#">李克强给新疆维吾尔族学生回信：放飞梦想 健康成长</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">李克强总理对东北等地抗旱工作作出重要批示</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">外交部就习近平对蒙古国进行访问举行媒体吹风会</a></li>
                            <li><i class="content_right_body_point"></i><a href="#">两部委共推国产医疗设备产业转型升级</a></li>
                        </ul>
                        <div class="new_icon"><img src="style/img/new_icon.gif"></div>
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
