package edu.csust.volunteer.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.Info;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.InfoService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.InfoModel;
/**
 * @author tam7

 */
@Action(value = "infoAction") 
public class InfoAction extends BaseAction<InfoModel> {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(InfoAction.class);
	private List<InfoModel> infoList;
	private int infoPage;
	private String content;
	private String title;
	private boolean isnew;
	private Info info;
	@Autowired
	private InfoService infoService;
	@Autowired
	private UserService userService;
	//返回首页的公告和新闻内容
	public void getBroadcastAndNewsInfo() {
	    HttpServletRequest request=ServletActionContext.getRequest();
	    int num=Integer.parseInt(request.getParameter("num"));
		JSONObject jsonData=new JSONObject();
		List<InfoModel> broadcastInfoList=infoService.getBrodcastInfoList(num);
		List<InfoModel> newsInfoList=infoService.getNewsInfoList(num);
		jsonData.put("broadcastInfo", broadcastInfoList);
		jsonData.put("newsInfo", newsInfoList);
		writeJson(jsonData);
	}
	//获得详细信息不应该使用list，而是直接返回一个对象。
	public void getInfoDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String infoId=request.getParameter("infoid");
		JSONObject jsonData=new JSONObject();
		List<Info> detailinfoList=infoService.getDetailInfoById(Integer.parseInt(infoId));
		jsonData.put("detailinfoList", detailinfoList);
		LOGGER.info("取得新闻或公告的详细信息");
		writeJson(jsonData);
	}
	public void getNewsInfoList() {
		HttpServletRequest request=ServletActionContext.getRequest();
		int param=Integer.parseInt(request.getParameter("param"));
		Calendar c = Calendar.getInstance();//当前时间
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		List<Info> yearInfoList=infoService.getInfoByYear(year,param);
		System.out.println("月份"+month+"年份"+year+"yearInfoList"+yearInfoList.size());
//		HashMultimap<String, Info> infoMap=HashMultimap.create();
		@SuppressWarnings("unchecked")
		Map<String, List<Info>> infoMap=new HashedMap();
		infoMap=getInfoMap(month,year,infoMap,yearInfoList);
		
		//月份少于11说明到上一年了
		if(month<11){
			infoMap=getInfoMap(12,year-1,infoMap,yearInfoList);
		}// 这里是一个月份的，总数先不传给前台，前台数设为1
		System.out.println("MAP的大小+"+infoMap.size());
		JSONObject jsonData=new JSONObject();
		jsonData.put("infoMap", infoMap);
		writeJson(jsonData);
	}
	public Map<String, List<Info>>  getInfoMap(int month,int year,Map<String, List<Info>> map,List<Info> yearInfoList) {
		Calendar c = Calendar.getInstance();//当前时间
		while (month>=0) {
			List<Info> infoList=new ArrayList<Info>();
			for (Info info : yearInfoList) {
				c.setTime(info.getRecordTime());
				if (c.get(Calendar.MONTH)==month&&c.get(Calendar.YEAR)==year) {
					System.out.println("获得的月份"+c.get(Calendar.MONTH)+"获得的年份"+c.get(Calendar.YEAR));
					infoList.add(info);
					map.put(getYearAndMonthString(year, month+1), infoList);
				}
			}
			month--;
		}
		return map;
	}
	public String getYearAndMonthString(int year,int month){
		String yearString=String.valueOf(year);
		String monthString=String.valueOf(month);
		return yearString+","+monthString;
	} 
	//===============================分割线==========================================
/*	public void getNewsByID() {
		JSONObject jsonData=new JSONObject();
		Info info =infoService.getNewsByID(infoid);
		jsonData.put("info", info);
		writeJson(jsonData);
	}*/
	//获取新闻
	public void getLastSevenInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("userno"));
		JSONObject jsonData=new JSONObject();
		System.out.println("取到前台穿来的值"+isnew+"++"+infoPage);
		List<InfoModel> InfoList = infoService.getInfo_F(isnew,infoPage,(String)session.getAttribute("userno"), 7);
		jsonData.put("InfoList", InfoList);
		writeJson(jsonData);
	}

	public void addInfo() throws IOException {
		boolean addNewsResult = false;
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuilder sb = new StringBuilder();  
		HttpSession session = ServletActionContext.getRequest().getSession();
        char[]buff = new char[1024];  
        int len;  
        while((len = request.getReader().read(buff)) != -1) {  
             sb.append(buff,0, len);  
        } 
        String title = new String(request.getParameter("title").getBytes("iso8859-1"),"utf-8");
        if (!request.getParameter("infoid").equals("null")) {
        	int infoid=Integer.parseInt(request.getParameter("infoid"));
        	infoService.updateInfoTitleAndContent(infoid,title,sb.toString());
        	addNewsResult = true;
    		writeJson(addNewsResult);
		}else {
			info=new Info();
			String adminid=(String) session.getAttribute("userno");
	        User user=userService.findUserById(adminid);
			info.setTitle(title);
			info.setContent(sb.toString());
			info.setIsnew(isnew);
			info.setAuthor(user.getUsername());
			info.setAuthorNumber(adminid);
			info.setRecordTime(new Date());
			System.out.println(info.toString());
			infoService.addOrUpdateInfo(info);
			addNewsResult = true;
			writeJson(addNewsResult);
		}
	}
	public void getInfoNumber() {
		HttpServletRequest request = ServletActionContext.getRequest();
		JSONObject jsonData=new JSONObject();
		HttpSession session=request.getSession();
		int newsNumber = infoService.getInfoNumber_F(isnew,(String)session.getAttribute("userno"));
		jsonData.put("newsNumber", newsNumber);
		writeJson(newsNumber);
	}
	public void deleteInfo() {
		int infoid=Integer.parseInt(request.getParameter("infoid"));
		boolean result=false;
		System.out.println("删除的ID号为"+infoid);
		infoService.deleteInfo(infoid);
		result=true;
		writeJson(result);
	}

		
		
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<InfoModel> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<InfoModel> infoList) {
		this.infoList = infoList;
	}
	public int getInfoPage() {
		return infoPage;
	}
	public void setInfoPage(int infoPage) {
		if(infoPage==0){
			infoPage=1;
		}
		this.infoPage = infoPage;
	}
	public boolean isIsnew() {
		return isnew;
	}
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
	}
