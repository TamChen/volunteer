package edu.csust.volunteer.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.Attention;
import edu.csust.volunteer.service.AttentionService;
import edu.csust.volunteer.vo.PictureVO;

@Action("attentionAction")
public class AttentionAction extends BaseAction<Attention>{
	@Autowired
	private AttentionService attentionService;
	//获得用户的朋友列表
	public void getUserFriend(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		String userno = request.getParameter("userno");
		int maxSize=16;
		int param = Integer.parseInt(request.getParameter("param"));//0-16条  1--所有的关注的图，2-所有关注我的人；
		List<PictureVO> friendPicList=new ArrayList<PictureVO>();
		int attentionMeNum=attentionService.getAttentionMeNum(userno);
		if (param==0) {
			friendPicList=attentionService.getMyFriend(userno,maxSize);
			//返回的是一组图片，我朋友的，图片列表friendPicList,应该包含pic和userno两项
			int friendNumber=friendPicList.size();
			jsonData.put("friendPicList", friendPicList);
			jsonData.put("friendNumber", friendNumber==0?0:friendNumber);
			jsonData.put("attentionMeNum", attentionMeNum==0?0:attentionMeNum);
		}else if (param==1) {
			//返回的是我所有的朋友的信息
			friendPicList=attentionService.getMyAllFriend(userno);
			jsonData.put("friendPicList", friendPicList);
		}else if (param==2){
			//返回的是关注我的人的信息
			friendPicList=attentionService.getMyAllAttentionMe(userno);
			jsonData.put("friendPicList", friendPicList);
		}
		writeJson(jsonData);
	}
	public void getRelationUsers(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		String userno = request.getParameter("userno");
		int param = Integer.parseInt(request.getParameter("param"));//说明是我关注的1还是关注我的2
		int current = Integer.parseInt(request.getParameter("current"));//当期页数
		int pagesize = Integer.parseInt(request.getParameter("size"));//页面里内容多少
		List<Attention> attentionsList=attentionService.getRelationUsers(userno,param,current,pagesize);
		jsonData.put("attentionsList", attentionsList);
		writeJson(jsonData);
	}
	public void attentionUser(){
		JSONObject jsonData=new JSONObject();
		HttpServletRequest request=ServletActionContext.getRequest();
		String userno = request.getParameter("userno");
		String currentUser=(String)request.getSession().getAttribute("userno");
		boolean isAttention =attentionService.attentionUser(userno,currentUser);
		jsonData.put("success", isAttention);
		writeJson(jsonData);
	}
	public void isAttention() {
		JSONObject jsonData=new JSONObject();
		String userno = request.getParameter("userno");
		String currentUser=(String)request.getSession().getAttribute("userno");
		boolean isAttention=attentionService.isAttention(userno,currentUser);
		jsonData.put("isAttention", isAttention);
		writeJson(jsonData);
	}
	public void cancelAttention(){
		JSONObject jsonData=new JSONObject();
		String userno = request.getParameter("userno");
		String currentUser=(String)request.getSession().getAttribute("userno");
		boolean isAttention=attentionService.cancelAttention(userno,currentUser);
		jsonData.put("success", isAttention);
		writeJson(jsonData);
	}
}
