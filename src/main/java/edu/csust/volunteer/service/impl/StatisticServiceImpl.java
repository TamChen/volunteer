package edu.csust.volunteer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import edu.csust.volunteer.dao.StatisticDao;
import edu.csust.volunteer.model.Statistics;
import edu.csust.volunteer.service.StatisticService;
@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {
	@Autowired
	private StatisticDao statisticDao;
	@Override
	public int[] getTotalAccessNum() {
		String hql="from Statistics";
		Object[] params={};
		int num[]=new int[2];
		List<Statistics> statistics=statisticDao.getActivityTotalPageNum(hql,params);
		int sum=0,sum2=0;
		for (Statistics statistics2 : statistics) {
			sum=sum+statistics2.getAccessNumDay();
		}
		num[0]=sum;
		for (Statistics statistics2 : statistics) {
			sum2=sum2+statistics2.getAttendNum();
		}
		num[1]=sum2;
		return num;
	}
	@Override
	public Statistics getTodayNum(Date date) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
		String hql="from Statistics where DAY(date)=?";
		Object[] params={day};
		Statistics statistics=statisticDao.getInfo(hql,params);
		return statistics;
	}
	@Override
	public void saveStatistic(Statistics statistics) {
		statisticDao.saveStatistic(statistics);
	}
	@Override
	public void increaseLoginInfo() {
		Calendar calendar=Calendar.getInstance();
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		String hql="from Statistics where DAY(date)=?";
		Object[] params={day};
		Statistics statistics=statisticDao.getInfo(hql,params);
		if (statistics==null) {
		statistics=new Statistics();
		statistics.setAccessNumDay(0);
		statistics.setAttendNum(0);
		statistics.setDate(new Date());
		}
		statistics.setAccessNumDay(statistics.getAccessNumDay()+1);
		statisticDao.updateStatistic(statistics);
	}
	@Override
	public void increaseAttendActivityNum() {
		Calendar calendar=Calendar.getInstance();
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		String hql="from Statistics where DAY(date)=?";
		Object[] params={day};
		Statistics statistics=statisticDao.getInfo(hql,params);
		statistics.setAccessNumDay(statistics.getAttendNum()+1);
		statisticDao.updateStatistic(statistics);
	}

}
