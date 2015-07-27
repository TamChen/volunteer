package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.Notice;

public interface NoticeDao {

	List<Notice> getLastNoticeList(String hql, Object[] params);

	int getLeftNoticeNum(String hql, Object[] params);

	
	void setUnreadToRead(String hql, Object[] params);

	List<Notice> getAllNotice(String hql, Object[] params);

}
