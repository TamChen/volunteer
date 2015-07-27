<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>volunteer</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Site Theme Styling -->
    <link rel="stylesheet" href="static/style/layout/reset.css" />
    <link rel="stylesheet" href="static/style/layout/index.css" />

    <!-- Styles --> 
    <link type="text/css" href="static/style/layout/custom-theme/jquery-ui-1.8.16.custom.css" rel="stylesheet" />
    <link href="static/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="static/style/layout/demo.css" rel="stylesheet">
    
	<style type="text/css">
		a { color:#213f71; font-size:9pt; text-decoration:none;}
		a:hover {color:#0066cc; }
	</style>
  </head>
  
  <body>
    <div id="header">
            <div class="fix">
            </div>
            <div class="navigation">
                <div class="navicontainer">
                    <!--学校logo-->
                    <div id="schoollogo">
                        <a><img border="0" src="static/style/images/schoollogo.png" /></a>
                    </div>

                    <ul id="nav-1"> 
                        <li></li>
                        <li><a href="index.jsp">平台首页</a></li> 
                        <li><a href="activity/activity-list.jsp">活动大厅</a></li> 
                        <li><a href="glimpse/glimpse-list.jsp">风采走廊</a></li> 
                        <li><a href="#">精彩走廊</a></li> 
                        <li><a href="#">关于我们</a></li> 
                    </ul> 
                    <div id="login">
                        <div id="login-left">
                        	<img alt="" src="static/style/images/photo.png">
                        </div>
                        <div id="login-right">
                        	<a id="name" href="#">裴云庆|消息</a>
                            <a id="logout" href="userAction!logout.action">退出登入</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      
            <div id="content">
                <div class="content-left">
                    <div class="volunteer-logo"></div>
                    <div class="volunteer-nav">
                            <div class="volunteer-nav-1">
                            <a href="personal.jsp"><img border="0" src="static/style/images/go.jpg" /> </a>
                            </div>
                             <hr>
                            <div class="volunteer-nav-2">
                            <a href="activity/activity-list.jsp"><img border="0" src="static/style/images/go.jpg" /> </a>
                            </div>
                             <hr>
                              <div class="volunteer-nav-3">
                            <a href="glimpse/glimpse-list.jsp"><img border="0" src="static/style/images/go.jpg" /> </a>
                            </div>
                    </div>
                    <div class="volunteer-subject">
                        <a href="#"><img border="0" src="static/style/images/subject.jpg" /> </a>
                        <!--这里的go就不放了-->
                    </div>
                    <div class="volunteer-search">
                        <div class="hot-search"></div>
                        <div class="search-key">
                            <a href="">综合排名</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a href="">最新发布</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a href="">学院发布</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a href="">人气排名</a>
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
                    <div class="volunteer-count">
                        <div class="title">
                            <p>总访问人次：</p>
                        </div>
                        <div class="count">
                            <p>0人</p>
                        </div>
                    </div>
                    <div class="volunteer-count">
                        <div class="title">
                            <p>单日访问人次:</p>
                        </div>
                        <div class="count">
                            <p>0人</p>
                        </div>
                    </div>
                    <div class="volunteer-count">
                        <div class="title">
                            <p>活动参与人次:</p>
                        </div>
                        <div class="count">
                            <p>0人</p>
                        </div>
                    </div>
                </div>

                <div class="content-minddle">
                    <div class="minddle-top">
                    </div>
                    <div class="minddle-left">

                        <div class="minddle-news">
                            <div class="news-more">
                                <a href="news/new-list.jsp"><img src="static/style/images/news-more.jpg"></a>
                            </div>
                            <div class="columnbox">
                              <div id="tabs2">
                                <ul>
                                  <li><a href="#tabs2-1">通知公告</a></li>
                                  <li><a href="#tabs2-2">站内新闻</a></li>
                                </ul>
                                <div id="tabs2-1">
                                    <div class="news">
                                      <div class="left-pic">
                                         <a href=""><img src="static/style/images/news.jpg"></a>
                                      </div>
                                       <div class="list">
                                            <ul id="brodcast-list">
                                            </ul>
                                       </div>
                                   </div>
                                </div>
                                <div id="tabs2-2">
                                     <div class="news">
                                      <div class="left-pic">
                                         <a href=""><img src="static/style/images/news.jpg"></a>
                                      </div>
                                       <div class="list">
                                            <ul id="news-list">
                                            </ul>
                                       </div>
                                   </div>
                                </div>
                              </div>
                            </div>
                        </div>
                        <hr>
                        <ul>
                            <li></li>

                        </ul>
                        <div class="minddle-activity">
                            <div class="title-more">
                                <div class="title">
                                    <p>活动招募</p>
                                </div>
                                <div class="activity-more">
                                    <a href=""><img src="static/style/images/news-more.jpg"></a>
                                </div>
                            </div>
                            <div class="activity-detail">
                                <div class="activity-content">
                                    <div class="activity-photo">
                                       <a href=""><img src="static/style/images/user.jpg"></a>
                                    </div>
                                    <div class="source">设计艺术学院</div>
                                    <div class="content">
                                     全国大学生广告大赛义工招募正式展开，小伙伴们快来参加吧！
                                    </div>
                                </div>
                                <div class="picture">
                                    <a href=""><img src="static/style/images/activity-pic.jpg"></a>
                                </div>
                                <div class="time">2014-12-11</div>
                                <div class="clear"></div>
                                <ul>
                                    <li>招募人数&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>已报名人数&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>留言&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>分享&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li><a href=""><img src="static/style/images/vote.jpg"></a>&nbsp;&nbsp;10&nbsp;</li>
                                </ul>
                            </div>
                        </div>
                         <div class="minddle-activity">
                            <div class="activity-detail">
                                <div class="activity-content">
                                    <div class="activity-photo">
                                       <a href=""><img src="static/style/images/user.jpg"></a>
                                    </div>
                                    <div class="source">设计艺术学院</div>
                                    <div class="content">
                                     全国大学生广告大赛义工招募正式展开，小伙伴们快来参加吧！
                                    </div>
                                </div>
                                <div class="picture">
                                    <a href=""><img src="static/style/images/activity-pic.jpg"></a>
                                </div>
                                <div class="time">2014-12-11</div>
                                <div class="clear"></div>
                                <ul>
                                    <li>招募人数&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>已报名人数&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>留言&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>分享&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li><a href=""><img src="static/style/images/vote.jpg"></a>&nbsp;&nbsp;10&nbsp;</li>
                                </ul>
                            </div>
                        </div>
                         <div class="minddle-activity">
                            <div class="activity-detail">
                                <div class="activity-content">
                                    <div class="activity-photo">
                                       <a href=""><img src="static/style/images/user.jpg"></a>
                                    </div>
                                    <div class="source">设计艺术学院</div>
                                    <div class="content">
                                     全国大学生广告大赛义工招募正式展开，小伙伴们快来参加吧！
                                    </div>
                                </div>
                                <div class="picture">
                                    <a href=""><img src="static/style/images/activity-pic.jpg"></a>
                                </div>
                                <div class="time">2014-12-11</div>
                                <div class="clear"></div>
                                <ul>
                                    <li>招募人数&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>已报名人数&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>留言&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li>分享&nbsp;&nbsp;10&nbsp;<span class="divider">|</span></li>
                                    <li><a href=""><img src="static/style/images/vote.jpg"></a>&nbsp;&nbsp;10&nbsp;</li>
                                </ul>
                            </div>
                        </div>
                        <!--风采-->
                        <div class="minddle-glimpse">
                            <div class="blk_18"> <a class="turn-left" onmousedown="ISL_GoUp_1()" onmouseup="ISL_StopUp_1()" onmouseout="ISL_StopUp_1()" href="javascript:void(0);" target="_self"></a>
                                    <div class="pcont" id="ISL_Cont_1">
                                        <div class="ScrCont">
                                          <div id="List1_1">
                                            <!-- piclist begin -->
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <a class="pl" href="#" ><img src="static/style/images/volunteer.jpg" alt="测试" width="92" height="58"/>测试测试</a>
                                            <!-- piclist end -->
                                          </div>
                                          <div id="List2_1"></div>
                                        </div>
                                    </div>
                                    <a class="turn-right" onmousedown="ISL_GoDown_1()" onmouseup="ISL_StopDown_1()" onmouseout="ISL_StopDown_1()" href="javascript:void(0);" target="_self"></a> 
                                    </div>
                                <div class="c"></div>
                                
                        </div>
                        <div class="minddle-link"></div>
                    </div>
                    <div class="minddle-right">
                        <div class="middle-photo">
                            <div class="right-title">
                                <div class="title"><p>照片墙</p></div>
                                <div class="pre-photo">
                                    <a href=""><img src="static/style/images/pre.jpg"></a>
                                </div>
                                <div class="next-photo">
                                    <a href=""><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <!-- jquery分页 -->
                            <ul>
                                <li><a href="#"><img src="static/style/images/photo-1.jpg"></a></li>
                                <li><a href="#"><img src="static/style/images/photo-1.jpg"></a></li>
                                <li><a href="#"><img src="static/style/images/photo-1.jpg"></a></li>
                                <li><a href="#"><img src="static/style/images/photo-1.jpg"></a></li>
                            </ul>
                        </div>
                        <div class="middle-record">
                             <div class="right-title">
                                <div class="title"><p>精品日志</p></div>
                                <div class="pre-photo">
                                    <a href=""><img src="static/style/images/pre.jpg"></a>
                                 </div>
                                <div class="next-photo">
                                    <a href=""><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <ul>
                            	<li>
                                        <img src="static/style/images/record.jpg">
                                        <a href="#">
                                        <span>当一天志愿者，我被你们感动</span>
                                        </a>
                                </li>
                                
                                <li>
                                        <img src="static/style/images/record.jpg">
                                        <a href="#">
                                        <span>当一天志愿者，我被你们感动</span>
                                        </a>
                                </li>
                                <li>
                                        <img src="static/style/images/record.jpg">
                                        <a href="#">
                                        <span>当一天志愿者，我被你们感动</span>
                                        </a>
                                </li>
                                <li>
                                        <img src="static/style/images/record.jpg">
                                        <a href="#">
                                        <span>当一天志愿者，我被你们感动</span>
                                        </a>
                                </li>
                                <li >
                                        <img src="static/style/images/record.jpg">
                                        <a href="#">
                                        <span>当一天志愿者，我被你们感动</span>
                                        </a>
                                </li>
                           </ul>
                        </div>
                        <div class="middle-rate">
                             <div class="right-title">
                                <div class="title"><p>活跃度排行</p></div>
                                <div class="pre-photo">
                                    <a href=""><img src="static/style/images/pre.jpg"></a>
                                </div>
                                <div class="next-photo">
                                    <a href=""><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <ul>
                                <li>
                                    <div class="photo-rate">
                                        <a href="#"><img src="static/style/images/rate-photo.jpg"></a>
                                    </div>
                                    <div class="rate">
                                    </div>
                                    <div class="text">
                                        1
                                    </div>
                                    <div class="info">
                                        <a href="">毕书尽</a>
                                        <p>计算机与通信工程学院</p>
                                        <div class="text"><p>活跃度</p></div>
                                        <div class="star"></div>
                                    </div>

                                </li>
                                <li>
                                    <div class="photo-rate">
                                        <a href="#"><img src="static/style/images/rate-photo.jpg"></a>
                                    </div>
                                    <div class="rate">
                                    </div>
                                    <div class="text">
                                        1
                                    </div>
                                    <div class="info">
                                        <a href="">毕书尽</a>
                                        <p>计算机与通信工程学院</p>
                                        <div class="text"><p>活跃度</p></div>
                                        <div class="star"></div>
                                    </div>

                                </li>
                                <li>
                                    <div class="photo-rate">
                                        <a href="#"><img src="static/style/images/rate-photo.jpg"></a>
                                    </div>
                                    <div class="rate">
                                    </div>
                                    <div class="text">
                                        1
                                    </div>
                                    <div class="info">
                                        <a href="">毕书尽</a>
                                        <p>计算机与通信工程学院</p>
                                        <div class="text"><p>活跃度</p></div>
                                        <div class="star"></div>
                                    </div>

                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
           
            </div>
            <div class="clean"></div>
       
        <div id="footer">
            <div class="container">
                </br>
                <p>长沙理工大学义工平台版权所有&nbsp;&nbsp;技术支持：梦之站</p>
                <p>主校区地址：长沙市（雨花区万家丽南路2段960号&nbsp;邮箱：410114）</p>
                <p>ICP证号：湘ICP备0500000</p>
            </div>
        </div>

        <!--scripts-->
        <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="static/js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="static/lib/jquery.raty.min.js"></script>
        <!-- load index info -->
        <script type="text/javascript" src="static/js/index.js"></script>
        <script type="text/javascript">
          
        </script>
  
<script type="text/javascript">

var Speed_1 = 10; //ËÙ¶È(ºÁÃë)
var Space_1 = 20; //Ã¿´ÎÒÆ¶¯(px)
var PageWidth_1 = 107 * 6; //·­Ò³¿í¶È
var interval_1 = 5000; //·­Ò³¼ä¸ôÊ±¼ä
var fill_1 = 0; //ÕûÌåÒÆÎ»
var MoveLock_1 = false;
var MoveTimeObj_1;
var MoveWay_1="right";
var Comp_1 = 0;
var AutoPlayObj_1=null;
function GetObj(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}}
function AutoPlay_1(){clearInterval(AutoPlayObj_1);AutoPlayObj_1=setInterval('ISL_GoDown_1();ISL_StopDown_1();',interval_1)}
function ISL_GoUp_1(){if(MoveLock_1)return;clearInterval(AutoPlayObj_1);MoveLock_1=true;MoveWay_1="left";MoveTimeObj_1=setInterval('ISL_ScrUp_1();',Speed_1);}
function ISL_StopUp_1(){if(MoveWay_1 == "right"){return};clearInterval(MoveTimeObj_1);if((GetObj('ISL_Cont_1').scrollLeft-fill_1)%PageWidth_1!=0){Comp_1=fill_1-(GetObj('ISL_Cont_1').scrollLeft%PageWidth_1);CompScr_1()}else{MoveLock_1=false}
AutoPlay_1()}
function ISL_ScrUp_1(){if(GetObj('ISL_Cont_1').scrollLeft<=0){GetObj('ISL_Cont_1').scrollLeft=GetObj('ISL_Cont_1').scrollLeft+GetObj('List1_1').offsetWidth}
GetObj('ISL_Cont_1').scrollLeft-=Space_1}
function ISL_GoDown_1(){clearInterval(MoveTimeObj_1);if(MoveLock_1)return;clearInterval(AutoPlayObj_1);MoveLock_1=true;MoveWay_1="right";ISL_ScrDown_1();MoveTimeObj_1=setInterval('ISL_ScrDown_1()',Speed_1)}
function ISL_StopDown_1(){if(MoveWay_1 == "left"){return};clearInterval(MoveTimeObj_1);if(GetObj('ISL_Cont_1').scrollLeft%PageWidth_1-(fill_1>=0?fill_1:fill_1+1)!=0){Comp_1=PageWidth_1-GetObj('ISL_Cont_1').scrollLeft%PageWidth_1+fill_1;CompScr_1()}else{MoveLock_1=false}
AutoPlay_1()}
function ISL_ScrDown_1(){if(GetObj('ISL_Cont_1').scrollLeft>=GetObj('List1_1').scrollWidth){GetObj('ISL_Cont_1').scrollLeft=GetObj('ISL_Cont_1').scrollLeft-GetObj('List1_1').scrollWidth}
GetObj('ISL_Cont_1').scrollLeft+=Space_1}
function CompScr_1(){if(Comp_1==0){MoveLock_1=false;return}
var num,TempSpeed=Speed_1,TempSpace=Space_1;if(Math.abs(Comp_1)<PageWidth_1/2){TempSpace=Math.round(Math.abs(Comp_1/Space_1));if(TempSpace<1){TempSpace=1}}
if(Comp_1<0){if(Comp_1<-TempSpace){Comp_1+=TempSpace;num=TempSpace}else{num=-Comp_1;Comp_1=0}
GetObj('ISL_Cont_1').scrollLeft-=num;setTimeout('CompScr_1()',TempSpeed)}else{if(Comp_1>TempSpace){Comp_1-=TempSpace;num=TempSpace}else{num=Comp_1;Comp_1=0}
GetObj('ISL_Cont_1').scrollLeft+=num;setTimeout('CompScr_1()',TempSpeed)}}
function picrun_ini(){
GetObj("List2_1").innerHTML=GetObj("List1_1").innerHTML;
GetObj('ISL_Cont_1').scrollLeft=fill_1>=0?fill_1:GetObj('List1_1').scrollWidth-Math.abs(fill_1);
GetObj("ISL_Cont_1").onmouseover=function(){clearInterval(AutoPlayObj_1)}
GetObj("ISL_Cont_1").onmouseout=function(){AutoPlay_1()}
AutoPlay_1();
}
</script>
</body>
</html>
