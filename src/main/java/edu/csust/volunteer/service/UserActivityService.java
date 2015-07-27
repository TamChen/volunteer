package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.UserActivity;
import edu.csust.volunteer.vo.ActivityUser;
import edu.csust.volunteer.vo.ApplyUser;
import edu.csust.volunteer.vo.PictureVO;

public interface UserActivityService {

	int getFlagByActIdAndUserno(int parseInt, String attribute);

	void saveUserActivity(UserActivity userActivity);

	void setUserJoinedActivity(UserActivity ua);

	UserActivity getJoinActivityUserByUAID(int parseInt);

	List<ApplyUser> getJoinActivityUserByID(int parseInt, int current, int applySize);

	int getActivityByActIdAndUserno(int activityId, String userno);

	int getApplyTotal(int activityId);

	void deletRecordById(int activityID);

	List<ActivityUser> getActivityByUserNo(String userno, int current, int pageSize);

	int getUserActivityNum(String userno);

	List<PictureVO> getUserActivityAll(String userno);

	int getSizeOfUserActivity(String userno, int param);

	int getAllAdminActivitySize(String userno, int param);

	boolean deleteUserActivity(String userno, int activityId);

	List<ActivityUser> getAdminActivityInfo(String userno, int current, int pageSize);
}
