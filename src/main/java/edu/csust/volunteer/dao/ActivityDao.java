package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.UserActivity;

public interface ActivityDao {

	List<Object[]> getIndexPageActivityInfo(String hql, Object[] params);

	int getActivityTotalPageNum(String hql, Object[] params);

	List<Activity> getSearchTotalActivityNum(String hql);
	//=================================================================
	void updateActivity(Activity activity);

	void addActivity(Activity activity);

	int getActivityTotal(String hql, Object[] params);

	void deleteActivityByID(int activityID);

	Activity getActivityById(String hql, Object[] params);

	List<Activity> getActivityList(String hql, int current, int size);

	void saveActivityPic(String hql, Object[] params);

	List<Object[]> getAdminActivity(String hql);

	Activity getSpecialActivity(String hql);

	List<Activity> getOffActivity(String hql);

	List<Activity> getLastSevenActivityList(String hql, Object[] param,
			int currentpage, int perPage);

	
	
}
