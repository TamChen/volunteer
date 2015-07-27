package edu.csust.volunteer.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.UserDiary;
import edu.csust.volunteer.service.LogService;

@Action("diaryAction") 
public class DiaryAction extends BaseAction<UserDiary>{
	@Autowired 
	private LogService logService;
	//得到用户的所有日记内容
	public void getUserDiary(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		int current=Integer.parseInt(request.getParameter("current"));
		int size=Integer.parseInt(request.getParameter("size"));
		List<UserDiary> userDiaryList=logService.getUserLog(userno,current,size);
		jsonData.put("userDiaryList", userDiaryList);
		jsonData.put("size", userDiaryList.size());
		writeJson(jsonData);
	}
	//得到用户日记的数目
	public void getUserDiaryNum(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		int  num=logService.getLogTotal(userno);
		writeJson(num);
	}
	//得到用户的的详细日记内容，详细信息
	public void getUserDiarydetail(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		int diaryId=Integer.parseInt(request.getParameter("recordId"));
		UserDiary userDiary=logService.getLogByID(diaryId);
		jsonData.put("userDiary", userDiary);
		writeJson(jsonData);
	}
	//保存日记信息
	public void postRecord () throws IOException {
		//param说明的是0是未发表，1是预览信息的，2是确认发表的
		//预览后，没有保存的为1，编辑后离开的也是1；2是最终确认发表
		JSONObject jsonData=new JSONObject();
		StringBuilder recordContent = new StringBuilder();
		HttpServletRequest request = ServletActionContext.getRequest();
		int param=Integer.parseInt(request.getParameter("param"));
		String userno=(String)request.getSession().getAttribute("userno");
		String username=(String)request.getSession().getAttribute("username");
		String title = new String(request.getParameter("title").getBytes("iso8859-1"),"utf-8");
		//接收私信内容
		char[]buff = new char[1024];  
		int len;  
		while((len = request.getReader().read(buff)) != -1) {  
			recordContent.append(buff,0, len);  
		} 
		int diaryId=logService.saveDiary(username,userno,title,recordContent.toString(),param);
		jsonData.put("diaryId", diaryId);
		jsonData.put("success", 1);
		writeJson(jsonData);
	}
	//获得用户草稿
	public void getUnPostRecord() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=request.getParameter("userno");
		UserDiary userDiary=logService.getUnPostRecord(userno);
		if (userDiary!=null) {
			jsonData.put("hasDiary", true);
		}else {
			jsonData.put("hasDiary", false);
		}
		jsonData.put("userDiary", userDiary);
		writeJson(jsonData);
	}
	//删除用户日记
	public void deleteUserDiarydetail(){
		//必须是本人登陆才能删除
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		int recordId=Integer.parseInt(request.getParameter("recordId"));
		String userno=(String)request.getSession().getAttribute("userno");
		logService.deleteLogByIdAndUserNo(recordId,userno);
		jsonData.put("issuccess", true);
		writeJson(jsonData);
	}
	public void getFrontRecordList() {
		JSONObject jsonObj = new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		int current=Integer.parseInt(request.getParameter("current"));
		int recordSize=6;
		List<UserDiary> recordList = logService.getFrontRecordList(current, recordSize);
		jsonObj.put("recordList", recordList);
		writeJson(jsonObj);
	}
	public void getNiceRecordListNum() {
		JSONObject jsonObj = new JSONObject();
		int num = logService.getNiceRecordListNum();
		jsonObj.put("num", (num/6)+1);
		writeJson(jsonObj);
	}
}