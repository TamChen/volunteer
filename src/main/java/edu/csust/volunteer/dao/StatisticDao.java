package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.Statistics;

public interface StatisticDao {

	List<Statistics> getActivityTotalPageNum(String hql, Object[] params);

	Statistics getInfo(String hql, Object[] params);

	void saveStatistic(Statistics statistics);

	void updateStatistic(Statistics statistics);

}
