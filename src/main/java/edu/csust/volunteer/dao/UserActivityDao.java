package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.UserActivity;

public interface UserActivityDao {
	int getFlagByActIdAndUserno(String hql, Object[] params);

	void saveUserActivity(UserActivity userActivity);

	void setUserJoinedActivity(UserActivity ua);

	UserActivity getJoinActivityUserByUAID(String hql);

	List<UserActivity> getJoinActivityUserByID(String hql, int current, int applySize);

	int getApplyTotal(String hql, Object[] params);

	List<Object[]> getUserActivityAll(String hql);

	void deleteUserActivity(String hql, Object[] params);
}
