package edu.csust.volunteer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csust.volunteer.dao.LogDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.UserDiary;
import edu.csust.volunteer.service.LogService;
@Service("logService")
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;

	@Override
	public List<UserDiary> getLogList(int current, int size) {
		String hql = "from UserDiary order by id desc";
		List<UserDiary> logList = logDao.getLogList(hql, current,size);
		return logList;
	}

	@Override
	public int getLogTotal(String userno) {
		String hql ="";
		if ("".equals(userno)) {
			hql = "select count(*) from UserDiary";
		}else {
			hql = "select count(*) from UserDiary where userNo='"+userno+"'";
		}
		return logDao.getLogTotal(hql);
	}

	@Override
	public UserDiary getLogByID(int logID) {
		String hql="from UserDiary where id= "+logID;
		UserDiary log=logDao.getLogByID(hql);
		return log;
	}

	@Override
	public void updateLog(UserDiary log) {
		logDao.updateLog(log);
	}

	@Override
	public void deleteLog(int logID) {
		logDao.deleteLog(logID);
	}

	@Override
	public List<UserDiary> getUserLog(String userno, int current, int pageSize) {
		String hql = "from UserDiary where userNo='"+userno+"' and param=2 and status=true order by id desc";
		List<UserDiary> logList = logDao.getLogList(hql, current,pageSize);
		return logList;
	}

	@Override
	public int saveDiary(String username,String userno,String title, String content, int param) {
		UserDiary userDiary=new UserDiary();
		userDiary.setActivityId(0);
		userDiary.setContent(content);
		userDiary.setLikeNum(0);
		userDiary.setNice(false);
		userDiary.setPublishTime(new Date());
		userDiary.setParam(param);
		userDiary.setStatus(true);
		userDiary.setTitle(title);
		userDiary.setUserNo(userno);
		userDiary.setUserName(username);
		logDao.saveDiary(userDiary);
		return userDiary.getId();
	}

	@Override
	public UserDiary getUnPostRecord(String userno) {
		String hql = "from UserDiary where userNo='"+userno+"' and param=1 and status=true order by id desc";
		Object params[]={userno};
		UserDiary userDiary = logDao.getUnPostRecord(hql, params);
		return userDiary;
	}

	@Override
	public void deleteLogByIdAndUserNo(int recordId, String userno) {
		String hql="delete from UserDiary where id=? and userNo=? ";
		Object params[]={recordId,userno};
		logDao.deleteLogByIdAndUserNo(hql,params);
	}

	@Override
	public List<UserDiary> getFrontRecordList(int current, int recordSize) {
		String hql = "from UserDiary where nice=true order by likeNum desc";
		List<UserDiary> logList = logDao.getLogList(hql, current,recordSize);
		return logList;
	}

	@Override
	public int getNiceRecordListNum() {
		String hql = "select count(*) from UserDiary where nice=true";
		return logDao.getLogTotal(hql);
	}

}
