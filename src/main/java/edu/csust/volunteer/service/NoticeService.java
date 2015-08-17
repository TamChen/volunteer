package edu.csust.volunteer.service;

import java.util.List;

import edu.csust.volunteer.model.Notice;
import edu.csust.volunteer.vo.NoticeVo;

public interface NoticeService {

	List<NoticeVo>  getLastNoticeList(String userno);

	int getLeftNotice(String userno);

	List<NoticeVo> getLastUnreadNotice(String userno, String friend);

	void saveNotice(Notice notice1);

	void deleteNotice(int noticeId);

	void deleteRelationshipNotice(String userno, String mailuser);

}
