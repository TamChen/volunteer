package edu.csust.volunteer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.persister.entity.Loadable;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.NoticeDao;
import edu.csust.volunteer.model.Notice;
@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao{

	@Override
	public List<Notice> getLastNoticeList(String hql, Object[] params) {
		List<Notice> noticelist=new ArrayList<Notice>();
		List<Object[]> object=MutiLoad(hql,params);
		for (Object[] objects : object) {
			Notice notice=getNoticeById((Integer)objects[0]);
			System.out.println("这里是找到的最新记录的id"+(Integer)objects[0]);
			noticelist.add(notice);
		}
		System.out.println("前台的条数"+noticelist.size());
		return noticelist;
	}
	public Notice getNoticeById(int id){
		String hql="from Notice where id=?";
		Object params[]={id};
		Notice notice=load(hql, params);
		return  notice;
	}
	@Override
	public int getLeftNoticeNum(String hql, Object[] params) {
		Long count1=(Long)uniqueQuery(hql, params);
		int count2=new Long(count1).intValue();
		System.out.println(count2);
		return count2;
	}
	@Override
	public List<Notice> getAllNotice(String hql, Object[] params) {
		List<Notice> notices=loadInfo(hql, params);
		return notices;
	}
	@Override
	public void setUnreadToRead(String hql, Object[] params) {
		executeByHql(hql, params);
	}

}
