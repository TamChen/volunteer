package edu.csust.volunteer.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.UserDiary;
import edu.csust.volunteer.service.LogService;
import edu.csust.volunteer.vo.ActivityUser;

@Action("logAction")
public class LogAction extends BaseAction<UserDiary>{
	@Autowired
	private LogService logService;
	
	private int LogID;
	
	private int  LogSize;//每页显示记录数
	private int current;  //当前页
	
	public void getLogList(){
		JSONObject jsonObj = new JSONObject();
		List<UserDiary> LogList = logService.getLogList(current, LogSize);
//		for(ActivityLog al : LogList){
//			System.out.println(al.toString());
//		}
		jsonObj.put("LogList", LogList);
		writeJson(jsonObj);
	}
	
	public void getLogTotal(){
		int LogTotal = logService.getLogTotal("");
		writeJson(LogTotal);
	}
	
	public void getLogByID(){
		JSONObject jsonObj = new JSONObject();
		UserDiary log = logService.getLogByID(LogID);
		jsonObj.put("log", log);
		writeJson(jsonObj);
	}
	
	public void deleteLog(){
		JSONObject jsonObj = new JSONObject();
		boolean deleteLogResult =false;
		try {
			logService.deleteLog(LogID);
			deleteLogResult = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObj.put("deleteLogResult", deleteLogResult);
		writeJson(jsonObj);
	}
	
	public void setLogStatusPass(){
		JSONObject jsonObj = new JSONObject();
		boolean setLogStatusPassResult = false;
		UserDiary log = logService.getLogByID(LogID);
		log.setStatus(true);
		try {
			logService.updateLog(log);
			setLogStatusPassResult=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObj.put("setLogStatusPassResult", setLogStatusPassResult);
		writeJson(jsonObj);
	}
	
	public void setLogIsNice(){
		boolean setLogIsNiceResult = false;
		JSONObject jsonObj = new JSONObject();
		UserDiary log =logService.getLogByID(LogID);
		log.setNice(true);
		try {
			logService.updateLog(log);
			setLogIsNiceResult=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObj.put("setLogIsNiceResult", setLogIsNiceResult);
		writeJson(jsonObj);
	}

	public void getUserLog() {
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		int current=Integer.parseInt(request.getParameter("current"));
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		List<UserDiary> listUserLog = logService.getUserLog(userno,current,pageSize);
		jsonObj.put("listUserLog", listUserLog);
		writeJson(jsonObj);
	}
	public void getUserLogTotal(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		int LogNumber = logService.getLogTotal(userno);
		writeJson(LogNumber);
	}
	
	
	
	
	public int getLogID() {
		return LogID;
	}

	public void setLogID(int logID) {
		LogID = logID;
	}

	public int getLogSize() {
		return LogSize;
	}

	public void setLogSize(int logSize) {
		LogSize = logSize;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}
	
	
}
