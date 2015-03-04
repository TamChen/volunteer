package edu.csust.volunteer.action;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.MapMaker;

import edu.csust.volunteer.service.InfoService;
import edu.csust.volunteer.vo.InfoModel;
/**
 * @author tam7
 *?如何实现部分加载（只刷新部分内容而其他的不刷新）
 *?在链接放置参数,js获得URL参数
 *?如何实现加载信息的时候不要等待，同时加载信息，
 *？不要等加载完jsp文件再加载ajax
 */
@Action("infoAction")
public class InfoAction extends BaseAction<InfoModel> {
	private static final long serialVersionUID = 1L;
	private static final String PICWALL="picwall";
	private static final String HOTDIARY="hotDiary";
	private static final String USERRATE="userRate";
	@Autowired
	private InfoService infoService;
	private List<InfoModel> infoList;
	private static ConcurrentMap<String, ConcurrentMap<String, Integer>> storePageInfo;
	//返回首页的公告和新闻内容
	public void getBrodcastAndNewsInfo() {
		JSONObject jsonData=new JSONObject();
		List<InfoModel> brodcastInfoList=infoService.getBrodcastInfoList(4);
		List<InfoModel> newsInfoList=infoService.getNewsInfoList(4);
		jsonData.put("brodcastInfo", brodcastInfoList);
		jsonData.put("newsInfo", newsInfoList);
		writeJson(jsonData);
	}
	
	public void getHotUserDiary() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userno=(String) session.getAttribute("userno");
		int count=0;
		HttpServletRequest request = ServletActionContext.getRequest();
		ConcurrentMap<String, ConcurrentMap<String, Integer>> storePageInfo=getStorePageInfo(userno);
		ConcurrentMap<String, Integer> pageCountMap=storePageInfo.get(userno);
		if (request.getParameter("param").equals("up")&&pageCountMap.get(HOTDIARY)>0) {
			//如果点击上一页，且当前页面不是第一页则往上翻一页
		}else if(request.getParameter("param").equals("up")&&pageCountMap.get(HOTDIARY)==0) {
			//如果点击上一页，且当前页面是第一页则依旧返回当前页面
		}else if (request.getParameter("param").equals("down")&&pageCountMap.get(HOTDIARY)>0) {
			
		}else if (request.getParameter("param").equals("down")&&pageCountMap.get(HOTDIARY)==0) {
			
		}
		JSONObject jsonData=new JSONObject();
		List<InfoModel> hotUserDiaryList=infoService.getHotUserDiary();
		jsonData.put("diayInfoList", hotUserDiaryList);
		writeJson(jsonData);
	}
	
	private ConcurrentMap<String, ConcurrentMap<String, Integer>> getStorePageInfo(String attribute) {
		if (storePageInfo==null) {
			storePageInfo=new MapMaker().makeMap();
		}
		if (storePageInfo.get(attribute) == null) {
			ConcurrentMap<String, Integer> pageCountMap =new MapMaker().makeMap();
			pageCountMap.put(PICWALL, 0);
			pageCountMap.put(HOTDIARY, 0);
			pageCountMap.put(USERRATE, 0);
			storePageInfo.put(attribute, pageCountMap);
		}
		return storePageInfo;
	}

	
	public List<InfoModel> getInfoList() {
		return infoList;
	}


	public void setInfoList(List<InfoModel> infoList) {
		this.infoList = infoList;
	}

}
