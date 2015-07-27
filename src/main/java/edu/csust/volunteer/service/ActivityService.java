package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.UserActivity;
import edu.csust.volunteer.vo.ActivityVo;
import edu.csust.volunteer.vo.PictureVO;

public interface ActivityService {

	List<ActivityVo> getIndexPageActivityInfo();

	List<Activity> getActivityListFront(int parseInt, String params, int perPageNumber);
	
	int getSearchTotalActivityNum(String string);

	void updateActivity(Activity activity);

	List<Activity> getActivityListBackend(int current, int activitySize);

	int getActivityTotal();

	void deleteActivityByID(int activityID);

	void saveActivityPic(int time, int activityId2, String path);

	Activity getActivityByID(int activityID);

	void addOrUpdateActivity(Activity activity);

	List<PictureVO> getAdminActivity(String adminid);

	Activity getSpecialActivity();

	List<Activity> getOffActivity();

}
