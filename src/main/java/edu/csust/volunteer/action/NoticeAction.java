package edu.csust.volunteer.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.Notice;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.NoticeService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.NoticeVo;

@Action("noticeAction")
public class NoticeAction extends BaseAction<Notice>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;
	public void getNoticeListFistPage() {
		JSONObject jsonData=new JSONObject();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userno=(String)session.getAttribute("userno");
		List<NoticeVo>  noticeList=noticeService.getLastNoticeList(userno);
		int leftNoticeNum=noticeService.getLeftNotice(userno);
		jsonData.put("infonum", leftNoticeNum);
		if (noticeList==null) 
			jsonData.put("size", 0);
		else 
			jsonData.put("size", noticeList.size());
		jsonData.put("noticeList", noticeList);
		writeJson(jsonData);
	}
	public void getUserInfoNum() {
		JSONObject jsonData=new JSONObject();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userno=(String)session.getAttribute("userno");
		jsonData.put("infonum", noticeService.getLeftNotice(userno));
		writeJson(jsonData);
	}
	public void saveUserNotice() throws IOException {
		JSONObject jsonData=new JSONObject();
		StringBuilder mailContent = new StringBuilder();  
		HttpServletRequest request = ServletActionContext.getRequest();
		String friend=request.getParameter("friend");//对方的学号
		HttpSession session = request.getSession();
		String userno=(String)session.getAttribute("userno");//本地
		//接收私信内容
		char[]buff = new char[1024];  
	    int len;  
	    while((len = request.getReader().read(buff)) != -1) {  
	        mailContent.append(buff,0, len);  
	    } 
	    //false表示不是系统消息，null表示没有标题，true表示未读；
	    Notice notice1=new Notice(friend,userno,userno,friend,false,null,mailContent.toString(),new Date(),true);
		Notice notice2=new Notice(userno,friend,userno,friend,false,null,mailContent.toString(),new Date(),false);
		noticeService.saveNotice(notice1);
		noticeService.saveNotice(notice2);//保存成功后私信数加1
		User user =userService.findUserById(friend);
		user.setInfoNum(user.getInfoNum()+1);
		userService.updateUser(user);
		jsonData.put("success", true);
		writeJson(jsonData);
	}
	public void getUserNoticeDetail() {
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request = ServletActionContext.getRequest();
		String friend=request.getParameter("friend");//对方的学号
		HttpSession session = request.getSession();
		String userno=(String)session.getAttribute("userno");
		//将用户与发信人的所有信息都查出来，在前台做判断进行显示
		List<NoticeVo>  noticeList=noticeService.getLastUnreadNotice(userno,friend);
		int num=getUnReadNum(noticeList);//这里是未读的条数
		User user=userService.findUserById(userno);
		User friendUser=userService.findUserById(friend);
		jsonData.put("friendname", friendUser.getUsername());
		jsonData.put("friendhead", friendUser.getUserHead());
		user.setInfoNum(user.getInfoNum()-num);
		userService.updateUser(user);//将未读数
		jsonData.put("infonum", user.getInfoNum()-num);//剩余的消息，私信条数
		jsonData.put("noticeList", noticeList);
		jsonData.put("noticeSize", noticeList.size());
		writeJson(jsonData);
	}
	private int getUnReadNum(List<NoticeVo> noticeList) {
		int num=0;
		for (NoticeVo noticeVo : noticeList) {
			if (noticeVo.isStatu()==true) {
				num++;
			}
		}
		return num;
	}
	public void deleteUserNotice() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int noticeId=Integer.parseInt(request.getParameter("id"));//要删的id；
		noticeService.deleteNotice(noticeId);
	}
	public void deleteAllNotice(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String userno=(String)request.getSession().getAttribute("userno");
		String  mailuser=request.getParameter("mailuser");//要删的id；
		noticeService.deleteRelationshipNotice(userno,mailuser);
	}
	
}
