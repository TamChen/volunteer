package edu.csust.volunteer.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.ActivityDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.Info;
import edu.csust.volunteer.model.UserActivity;
@Repository("activityDao")
public class ActivityDaoImpl extends BaseDaoImpl<Activity> implements ActivityDao {

	@Override
	public List<Object[]> getIndexPageActivityInfo(String hql, Object[] params) {
		//获得两条最新的活动信息
		List<Object[]> list=getObjectPageList(hql,1,1);
		return list;
	}

	@Override
	public int getActivityTotalPageNum(String hql, Object[] params) {
		Long count1=(Long)uniqueQuery(hql, params);
		return (count1.intValue()/4)+1;
	}

	@Override
	public List<Activity> getSearchTotalActivityNum(String hql) {
		List<Activity> list=loadList(hql);
		return list;
	}
	//修改数据的时候可能不成功，需要测试
	@Override
	public void updateActivity(Activity activity) {
		Session session = getSession2();
		Transaction tx = session.beginTransaction();
		session.update(activity);
		tx.commit();
		session.close();
	}

	@Override
	public void addActivity(Activity activity) {
		Session session = getSession();
		session.saveOrUpdate(activity);
		session.close();
	}

	@Override
	public List<Activity> getActivityList(String hql, int current, int size) {
		List<Activity> list=splitpageList(hql,current,size);
		return list;
	}

	@Override
	public int getActivityTotal(String hql, Object[] params) {
		Long ActivityTotal=(Long)uniqueQuery(hql, params);
		return ActivityTotal.intValue();
	}

	@Override
	public void deleteActivityByID(int activityID) {
		Session session = getSession2();
		Query query= session.createQuery("delete from Activity acti where acti.id="+activityID);
		query.executeUpdate();
		session.close();
	}

	@Override
	public Activity getActivityById(String hql, Object[] params) {
		Activity activity=load(hql, params);
		return activity;
	}

	@Override
	public void saveActivityPic(String hql, Object[] params) {
		executeByHql(hql, params);
	}

	@Override
	public List<Object[]> getAdminActivity(String hql) {
		List<Object[]> objects=getObjectPageList(hql,0,8);
		return objects;
	}

	@Override
	public Activity getSpecialActivity(String hql) {
		Activity activity=load(hql);
		return activity;
	}

	@Override
	public List<Activity> getOffActivity(String hql) {
		return loadList(hql);
	}

	@Override
	public List<Activity> getLastSevenActivityList(String hql, Object[] param,
			int currentpage, int perPage) {
		int start=(currentpage-1)*perPage;
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(perPage);
		query.setParameter(0, param);
		@SuppressWarnings("unchecked")
		List<Activity> list = query.list();
		return list;
	}

	

	
}
