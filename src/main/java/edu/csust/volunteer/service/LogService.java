package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.UserDiary;



public interface LogService {

	List<UserDiary> getLogList(int current, int logSize);

	int getLogTotal(String userno);

	UserDiary getLogByID(int logID);

	void updateLog(UserDiary log);

	void deleteLog(int logID);

	List<UserDiary> getUserLog(String userno, int current, int pageSize);

	int saveDiary(String username,String userno,String title, String string, int param);

	UserDiary getUnPostRecord(String userno);

	void deleteLogByIdAndUserNo(int recordId, String userno);

	List<UserDiary> getFrontRecordList(int current, int recordSize);

	int getNiceRecordListNum();

}
