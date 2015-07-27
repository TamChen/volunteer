package edu.csust.volunteer.action;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.ActivityService;
import edu.csust.volunteer.service.UserActivityService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.ActivityVo;
import edu.csust.volunteer.vo.PictureVO;

@Action("activityAction")
public class ActivityAction extends BaseAction<Activity>{
	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserActivityService userActivityService;
	private int activityID ;
	private String ActivityTitle;
	private String ActivityIntro;
	private String ActivityPic;
	private String ActivityAddress;
	private Date ActivityBeginTime;
	private Date ActivityEndTime;
	private int ActivityNeedPeople;
	private int ActivitySize;
	private int userActivityID;
	private long userActivityWorkHour;
	private int current;//当前页
	DateFormat df = new SimpleDateFormat("yyyyMMdd-hh-mm-ss-");
	String questionTime = df.format(new Date());
	HttpServletRequest request = ServletActionContext.getRequest();
	//返回首页活动信息
	public void getIndexPageActivityInfo() throws IOException {
		JSONObject jsonData=new JSONObject();
		HttpSession session = ServletActionContext.getRequest().getSession();
		List<ActivityVo> indexpageactivityinfo=activityService.getIndexPageActivityInfo();
		jsonData.put("indexpageactivityinfo", indexpageactivityinfo);
		session.setAttribute("totalpage", (Integer)activityService.getSearchTotalActivityNum(""));
		writeJson(jsonData);
	}
	//获得前台活动列表，分页
	public void getActivityListFront() throws UnsupportedEncodingException{
		int perPageNumber=5;
		HttpServletRequest request = ServletActionContext.getRequest();
		String currentpage=request.getParameter("currentpage");
		String params = new String(request.getParameter("params").getBytes("iso8859-1"),"utf-8");
		System.out.println(currentpage+"+"+params);
		JSONObject jsonData=new JSONObject();
		List<Activity> activityList=activityService.getActivityListFront(Integer.parseInt(currentpage),params,perPageNumber);
		jsonData.put("activityList", activityList);
		writeJson(jsonData);
	}
	//获得活动的详细信息
	public void getActivityDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String activityId=request.getParameter("activityid");
		JSONObject jsonData=new JSONObject();
		Activity activity=activityService.getActivityByID(Integer.parseInt(activityId));
		jsonData.put("activity", activity);
		writeJson(jsonData);
	}
	//获得搜索的活动总数
	public void getSearchTotalActivityNum() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String params = new String(request.getParameter("params").getBytes("iso8859-1"),"utf-8");
		System.out.println("Total+"+params);
		JSONObject jsonData=new JSONObject();
		int activityNum=activityService.getSearchTotalActivityNum(params);
		jsonData.put("number", activityNum);
		writeJson(jsonData);
	}
	//对活动感心情
	public void interestToActivity(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String activityId=request.getParameter("activityid");
		Activity activity=activityService.getActivityByID(Integer.parseInt(activityId));
		int interestNumber=activity.getInterest()+1;
		activity.setInterest(interestNumber);
		activityService.updateActivity(activity);
		//将报名人数传回前台
		int number[] =new int[2];
		number[0]=activity.getRegistration();
		number[1]=interestNumber;
		System.out.println(interestNumber+"+++++"+number[0]);
		JSONObject jsonData=new JSONObject();
		jsonData.put("number",number);
		writeJson(jsonData);
	}
	//==========================================================================================
	//===================================================================================
	//添加活动
	public void getSpecialActivity() {
		JSONObject jsonData=new JSONObject();
		Activity activity=activityService.getSpecialActivity();
		jsonData.put("activity", activity);
		writeJson(jsonData);
	}
	public void getOffActivity() {
		JSONObject jsonData=new JSONObject();
		List<Activity> activityList=activityService.getOffActivity();
		jsonData.put("activityList", activityList);
		if (activityList!=null) {
			jsonData.put("num", activityList.size());
		}
		writeJson(jsonData);
	}
	public void addActivity() throws IOException {
		boolean addActivityResult = false;
		int activityJustSavedID = -1;
		StringBuilder sb = new StringBuilder();  
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userno=(String)session.getAttribute("userno");
		User user=userService.findUserById(userno);
        char[]buff = new char[1024];  
        int len;  
        while((len = request.getReader().read(buff)) != -1) {  
             sb.append(buff,0, len);  
        } 
        Activity activity = new Activity();
        if(!("null".equals(request.getParameter("ActivityId")))){
        	activity=activityService.getActivityByID(Integer.parseInt(request.getParameter("ActivityId")));
        }else {
		}
        System.out.println("后台获得从前台传来的活动id"+activity.getId()+"++++++++"+request.getParameter("ActivityId"));
		JSONObject jsonData=new JSONObject();
		activity.setTitle(ActivityTitle);
		activity.setIntro(ActivityIntro);
		activity.setDetail(sb.toString());
		activity.setBeginTime(ActivityBeginTime);
		activity.setEndTime(ActivityEndTime);
		activity.setNumber(ActivityNeedPeople);
		activity.setPic("");
		activity.setAdminID(userno);
		activity.setAttend(0);//参加的人数
		activity.setState(4);//4表示活动还未编辑完成
		activity.setAdminName(user.getUsername());
		activity.setAdminID(user.getUserno());
		activity.setAddress(ActivityAddress);
		activity.setInterest(0);
		activity.setRegistration(0);
		activityService.addOrUpdateActivity(activity);
		//this.getHibernateTemplate().save(ab); 
		//插入后,直接ab.getId()就可以获得id值,hibernate会自动赋值回来.
		activityJustSavedID = activity.getId();
		jsonData.put("activityJustSavedID", activityJustSavedID);
		addActivityResult=true;
		jsonData.put("addActivityResult", addActivityResult);
		writeJson(jsonData);
	}
	//获得后台的活动列表
	public void  getActivityList(){
		JSONObject jsonData=new JSONObject();
		List<Activity> ActivityList = activityService.getActivityListBackend(current, ActivitySize);
		jsonData.put("ActivityList", ActivityList);
		writeJson(jsonData);
	}
	//获得后台所有活动的数目
	public void getActivityTotal() {
		int ActivityTotal = activityService.getActivityTotal();
		writeJson(ActivityTotal);
	}
	//结束活动
	public void endActivity(){
		boolean endActivityResult = false;
		JSONObject jsonData=new JSONObject();
		Activity activity = activityService.getActivityByID(activityID);
		activity.setState(135);//设置活动结束
		try {
			activityService.updateActivity(activity);
			jsonData.put("activityJustEndID", activityID);
			endActivityResult=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonData.put("endActivityResult", endActivityResult);
		writeJson(jsonData);
	}
	//删除活动
	public void deleteActivity(){
		JSONObject jsonData=new JSONObject();
		boolean deleteActivityResult = false;
		try {
			activityService.deleteActivityByID(activityID);
			userActivityService.deletRecordById(activityID);
			deleteActivityResult=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonData.put("deleteActivityResult", deleteActivityResult);
    	writeJson(jsonData);
	}
	public void getAdminActivity(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String adminid=request.getParameter("userno");
		List<PictureVO> adminActivity=activityService.getAdminActivity(adminid);
		List<PictureVO> userActivityList=userActivityService.getUserActivityAll(adminid);
		jsonData.put("adminActivity", adminActivity);
		jsonData.put("userActivityList", userActivityList);
		writeJson(jsonData);
	}
	public void voteActivity() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		int activityId=Integer.parseInt(request.getParameter("activityId"));
		Activity activity=activityService.getActivityByID(activityId);
		activity.setPraise(activity.getPraise()+1);
		activityService.updateActivity(activity);
	}
	//get/set 方法
	public int getActivityID() {
		return activityID;
	}
	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}
	public String getActivityTitle() {
		return ActivityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		try {
			this.ActivityTitle =new String(activityTitle.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getActivityIntro() {
		return ActivityIntro;
	}
	public void setActivityIntro(String activityIntro) {
		try {
			this.ActivityIntro =new String(activityIntro.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getActivityPic() {
		return ActivityPic;
	}
	public void setActivityPic(String activityPic) {
		try {
			this.ActivityPic =new String(activityPic.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public Date getActivityBeginTime() {
		return ActivityBeginTime;
	}
	public void setActivityBeginTime(Date activityBeginTime) {
		ActivityBeginTime = activityBeginTime;
	}
	public Date getActivityEndTime() {
		return ActivityEndTime;
	}
	public void setActivityEndTime(Date activityEndTime) {
		ActivityEndTime = activityEndTime;
	}
	public int getActivityNeedPeople() {
		return ActivityNeedPeople;
	}
	public void setActivityNeedPeople(int activityNeedPeople) {
		ActivityNeedPeople = activityNeedPeople;
	}
	public int getActivitySize() {
		return ActivitySize;
	}
	public void setActivitySize(int activitySize) {
		ActivitySize = activitySize;
	}
	public int getUserActivityID() {
		return userActivityID;
	}
	public void setUserActivityID(int userActivityID) {
		this.userActivityID = userActivityID;
	}
	public long getUserActivityWorkHour() {
		return userActivityWorkHour;
	}
	public void setUserActivityWorkHour(long userActivityWorkHour) {
		this.userActivityWorkHour = userActivityWorkHour;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		if(current==0){
			current=1;
		}
		this.current = current;
	}
	public String getActivityAddress() {
		return ActivityAddress;
	}
	public void setActivityAddress(String activityAddress) {
		try {
			this.ActivityAddress =new String(activityAddress.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
