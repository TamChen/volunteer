package edu.csust.volunteer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import edu.csust.volunteer.dao.NoticeDao;
import edu.csust.volunteer.dao.impl.BaseDaoImpl;
import edu.csust.volunteer.model.Notice;
import edu.csust.volunteer.model.User;
import edu.csust.volunteer.service.NoticeService;
import edu.csust.volunteer.service.UserService;
import edu.csust.volunteer.vo.NoticeVo;
@Service("noticeService")
public class NoticeServiceImpl extends BaseDaoImpl<Notice> implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private UserService userService;
	@Override
	public List<NoticeVo> getLastNoticeList(String userno) {
		//找到对方的所有死刑的第一条
		String hql="SELECT MAX(id) AS id, COUNT(id) AS msgCount FROM Notice WHERE user = ? GROUP BY friend ORDER BY id DESC";
		Object params[]={userno};
		List<Notice> notices=noticeDao.getLastNoticeList(hql,params);
		if (notices==null) {
			return null;
		}
		List<NoticeVo> notice= transFormNoticeToNoticeVo(notices,userno);
		for (NoticeVo noticeVo : notice) 
			if (noticeVo.getUserno().equals(userno)) {//说明这条记录是本人先发起的
				System.out.println("看看这里有没有执行");
				noticeVo.setUserno(noticeVo.getFriendno());
				noticeVo.setUsername(noticeVo.getFriendname());
				noticeVo.setUserpic(noticeVo.getFriendpic());
			}	
		return notice;
	}
	@Override
	public int getLeftNotice(String userno) {
		//得到未读的消息数
		String hql="SELECT COUNT(id) FROM Notice WHERE user = ? AND status=true";
		Object params[]={userno};
		int num=noticeDao.getLeftNoticeNum(hql,params);
		return num;
	}
	@Override
	public List<NoticeVo> getLastUnreadNotice(String userno, String friend) {
		//将所有信息都查出来，并将对方与自己方的信都置为已读；
		String hql="FROM Notice WHERE user = ? AND friend=?";
		String hql2="update Notice t set t.status =false , t.read_time=CURRENT_DATE() where user = ? AND friend=?"; 
		Object params[]={userno,friend};
		List<Notice> notices=noticeDao.getAllNotice(hql,params);
		noticeDao.setUnreadToRead(hql2,params);
		if (notices==null) {
			return null;
		}
		return transFormNoticeToNoticeVo(notices,userno);
	}
	public List<NoticeVo> transFormNoticeToNoticeVo(List<Notice> notices,String userno) {
		List<NoticeVo> NoticeVoList=Lists.newArrayList(Collections2.transform(notices, new Function<Notice, NoticeVo>() {
            @Override
            public NoticeVo apply(Notice notice) {
            	NoticeVo noticeVo=new NoticeVo();
            	noticeVo.setNoticeContent(notice.getContent());
            	noticeVo.setNoticetitle(notice.getTitle());
            	noticeVo.setUserno(notice.getSender());
            	noticeVo.setFriendno(notice.getReceiver());
            	noticeVo.setStatu(notice.isStatus());
            	noticeVo.setSend_time(notice.getSend_time());
            	noticeVo.setType(notice.isType());
            	noticeVo.setId(notice.getId());
            	User user=userService.findUserById(notice.getSender());
            	User user2=userService.findUserById(notice.getReceiver());
				noticeVo.setUsername(user.getUsername());
	            noticeVo.setUserpic(user.getUserHead());
	            noticeVo.setFriendname(user2.getUsername());
	            noticeVo.setFriendpic(user2.getUserHead());
                return noticeVo;
            }
        }));
		return NoticeVoList;
	}
	@Override
	public void saveNotice(Notice notice1) {
		save(notice1);		
	}
	@Override
	public void deleteNotice(int noticeId) {
		String hql="delete from Notice notice where notice.id=? ";
		Object params[]={noticeId};
		executeByHql(hql, params);
	}
	@Override
	public void deleteRelationshipNotice(String userno, String mailuser) {
		String hql="DELETE FROM Notice WHERE user = ? AND friend=?";
		Object params[]={userno,mailuser};
		executeByHql(hql, params);
	}
}
