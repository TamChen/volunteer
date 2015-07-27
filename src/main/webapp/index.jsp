<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 1.0 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>volunteer</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Site Theme Styling -->
    <link rel="stylesheet" href="static/style/layout/reset.css" />
    <link rel="stylesheet" href="static/style/layout/index.css" />
    
	<link type="text/css" href="static/style/layout/lrtk.css" rel="stylesheet" />

	
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

      <jsp:include  page="head.jsp"/>
            <div id="content">
                <div class="content-left">
                    <div class="volunteer-logo"></div>
                    <div class="volunteer-nav">
                            <div id="volunteer-nav-1">
                            <a href="personal/user-index.jsp?userno=<%=session.getAttribute("userno")%>"><img border="0" src="static/style/images/go.jpg" /> </a>
                            </div>
                            <input id="admin" type="hidden" value=<%=session.getAttribute("admin")%>>
                             <hr>
                            <div class="volunteer-nav-2">
                            <a href="activity/activity-list.jsp"><img border="0" src="static/style/images/go.jpg" /> </a>
                            </div>
                             <hr>
                              <div class="volunteer-nav-3">
                            <a href="glimpse/picture-list.jsp"><img border="0" src="static/style/images/go.jpg" /> </a>
                            </div>
                    </div>
                    <div class="volunteer-subject">
                        <a id="link_special" href="javascript:void(0)" onclick="turn()"><img class="special_act" style="width: 228px;height:128.25px;" border="0" src="" /> </a>
                        <!--这里的go就不放了-->
                    </div>
                    <div class="volunteer-search" style="height:230px;">
                        <div class="hot-search"></div>
                        <div class="search-key">
                            <a href="activity/activity-list.jsp?parms=0">综合排名</a>
                        </div>
                        <hr>
                        <div class="search-key">
                            <a href="activity/activity-list.jsp?parms=1">最新发布</a>
                        </div><%--
                        <hr>
                        <div class="search-key">
                            <a href="javascript:void(0)' onclick='searchActivity(3)">学院发布</a>
                        </div>
                        --%><hr>
                        <div class="search-key">
                            <a href="activity/activity-list.jsp?parms=2">人气排名</a>
                        </div>
                        <div class="in-search">
                            <div class="input">
                            <p>输入关键字：</p>
                            </div>

                            <div class="input-group">
                              <input type="text" class="form-control" id="input">
                            </div>
                            <div class="search">
                            <a href="javascript:void(0)" onclick="searchActivity()"><img src="static/style/images/search.jpg"></a>
                            </div>
                        </div>

                    </div>
                    <div class="volunteer-count">
                        <div class="title">
                            <p>总访问人次：</p>
                        </div>
                        <div class="count">
                            <p id="totalNum">0人</p>
                        </div>
                    </div>
                    <div class="volunteer-count">
                        <div class="title">
                            <p>单日访问人次:</p>
                        </div>
                        <div class="count">
                            <p id="dayNum">0人</p>
                        </div>
                    </div>
                    <div class="volunteer-count">
                        <div class="title">
                            <p>活动参与人次:</p>
                        </div>
                        <div class="count">
                            <p id="act_attend">0人</p>
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
                                <ul style="font-family:'微软雅黑'">
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
                                    <p>义工招募</p>
                                </div>
                                <div class="activity-more">
                                    <a href="activity/activity-list.jsp"><img src="static/style/images/news-more.jpg"></a>
                                </div>
                            </div>
                            
                          
                         <div id="activity-top-3"></div>
                         </div>
                        <!--风采-->
                        <div class="minddle-glimpse">
                           
							<div class="box">
								<div class="control">
									<div class="og_prev"></div>
									<div class="og_next"></div>
								</div>
								<div class="picbox">
									<ul id="pic_list" class="piclist mainlist">
										<li class="act_style1" ><div>
							<a class="link1" href="" ><img class="img1" src="" width="110" height="165" /></a>
											</div>
											<div class="act-title-scoll" id="act_title1">
											</div>
										</li>
										<li class="act_style2" ><div>
							<a class="link2" href="" ><img class="img2" src="" width="110" height="165" /></a>
											</div>
											<div class="act-title-scoll" id="act_title2">
											</div>
										</li>
										<li class="act_style3" ><div>
							<a class="link3" href="" ><img class="img3" src="" width="110" height="165" /></a>
											</div>
											<div class="act-title-scoll" id="act_title3">
											</div>
										</li>
										<li class="act_style4" ><div>
							<a class="link4" href="" ><img class="img4" src="" width="110" height="165" /></a>
											</div>
											<div class="act-title-scoll" id="act_title4">
											</div>
										</li>
										<li class="act_style5" ><div>
							<a class="link5" href="" ><img class="img5" src="" width="110" height="165" /></a>
											</div>
											<div class="act-title-scoll" id="act_title5">
											</div>
										</li>
										<li class="act_style6" ><div>
							<a class="link6" href="" ><img class="img6" src="" width="110" height="165" /></a>
											</div>
											<div class="act-title-scoll" id="act_title6">
											</div>
										</li>
										<li class="act_style7" ><div>
							<a class="link7" href="" ><img class="img7" src="" width="110" height="165" /></a>
											</div>
											<div class="act-title-scoll" id="act_title7">
											</div>
										</li>
										<li class="act_style8" ><div>
							<a class="link8" href="" ><img class="img8" src="" width="110" height="165" /></a>
											</div>
											<div class="act-title-scoll" id="act_title8">
											</div>
										</li>
									</ul>
							        <ul class="piclist swaplist"></ul>
								</div>
								
							</div>
                                
                        </div>
                        <div class="minddle-link"></div>
                    </div>
                    <div class="minddle-right">
                        <div class="middle-photo">
                            <div class="right-title">
                                <div class="title"><p>义工风采</p></div>
                                <div class="pre-photo">
                                    <a href='javascript:void(0)' onclick='loadPre()'><img src="static/style/images/pre.jpg"></a>
                                </div>
                                <div class="next-photo">
                                    <a id="glimpse_photo" href='javascript:void(0)' onclick='loadNext()'><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <!-- jquery分页 -->
                            <ul id="glimpse_list">
                            </ul>
                        </div>
                        <div class="middle-record">
                             <div class="right-title">
                                <div class="title"><p>义工日记</p></div>
                                <div class="pre-photo">
                                    <a href='javascript:void(0)' onclick='loadRecordPre()'><img src="static/style/images/pre.jpg"></a>
                                 </div>
                                <div class="next-photo">
                                    <a id="next_record" href='javascript:void(0)' onclick='loadRecordNext()'><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <ul id="record_list">
                           </ul>
                        </div>
                        <div class="middle-rate">
                             <div class="right-title">
                                <div class="title"><p>义工风云榜</p></div>
                                <div class="pre-photo">
                                    <a href='javascript:void(0)' onclick='loadRatePre()'><img src="static/style/images/pre.jpg"></a>
                                </div>
                                <div class="next-photo">
                                    <a id="rate_num" href='javascript:void(0)' onclick='loadRateNext()'><img src="static/style/images/next.jpg"></a>
                                </div>
                            </div>
                            <hr size="1" />
                            <ul id="rate_list">
         
                               
                               
                            </ul>
                        </div>
                    </div>
                </div>
           
            </div>
            
        <jsp:include  page="foot.jsp"/>
		<input id="userno" type="hidden" value=<%=session.getAttribute("userno")%>>

        <!--scripts-->
        <script type="text/javascript" src="static/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="static/js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="static/lib/jquery.raty.min.js"></script>
        <!-- load index info -->
        <script type="text/javascript" src="static/js/index.js"></script>
        <script type="text/javascript" src="static/js/head.js"></script>
    

    	<script type="text/javascript" src="static/js/lrtk.js"></script>
     

</body>
</html>
