package edu.csust.volunteer.service;

import java.util.Date;

import edu.csust.volunteer.model.Statistics;

public interface StatisticService {

	int[] getTotalAccessNum();

	Statistics getTodayNum(Date date);

	void saveStatistic(Statistics statistics);

	void increaseLoginInfo();

	void increaseAttendActivityNum();

}
