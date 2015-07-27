package edu.csust.volunteer.action;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import edu.csust.volunteer.model.Statistics;
import edu.csust.volunteer.service.StatisticService;

@Action("statisticsAction")
public class StatisticsAction extends BaseAction<Statistics>{
	private static final long serialVersionUID = 1L;
	@Autowired 
	private StatisticService statisticService;
	public void getTotalAccessNum() {
		JSONObject jsonObject=new JSONObject();
		int num[]=new int[2];
		num=statisticService.getTotalAccessNum();
		Statistics statistics=statisticService.getTodayNum(new Date());
		jsonObject.put("num", num);
		if (statistics==null) {
			statistics=new Statistics();
			statistics.setAccessNumDay(1);
			statistics.setDate(new Date());
			statistics.setAttendNum(num[1]);
			statisticService.saveStatistic(statistics);
		}
		jsonObject.put("info", statistics);
		writeJson(jsonObject);
	}
}

