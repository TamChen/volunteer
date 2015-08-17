package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.UserDiary;

public interface LogDao {

	List<UserDiary> getLogList(String hql, int current, int size);

	int getLogTotal(String hql);

	UserDiary getLogByID(String hql);

	void updateLog(UserDiary log);

	void deleteLog(int logID);

	void saveDiary(UserDiary userDiary);

	UserDiary getUnPostRecord(String hql, Object[] params);

	void deleteLogByIdAndUserNo(String hql, Object[] params);

}
