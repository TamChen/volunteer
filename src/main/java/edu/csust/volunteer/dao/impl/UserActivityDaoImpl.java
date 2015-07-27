package edu.csust.volunteer.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.UserActivityDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.UserActivity;
@Repository("userActivityDao")
public class UserActivityDaoImpl extends BaseDaoImpl<UserActivity> implements UserActivityDao {

	@Override
	public int getFlagByActIdAndUserno(String hql, Object[] params) {
		int flag;
		List<Integer> flagList=findSingelCol(hql,params);
		//flag==136表示在关系表中没有找到信息，表示该用户与该活动没有相应的操作
		if(flagList==null||flagList.size()==0){
			flag=136;
		}else {
			flag=(int)flagList.get(0);
		}
		return flag;
	}

	//保存用户与活动的关系
	@Override
	public void saveUserActivity(UserActivity userActivity) {
		getSession().save(userActivity);
	}


	@Override
	public void setUserJoinedActivity(UserActivity ua) {
		Session session = getSession2();
		Transaction tx = session.beginTransaction();
		session.update(ua);
		tx.commit();
		session.close();
	}

	@Override
	public UserActivity getJoinActivityUserByUAID(String hql) {
		List<UserActivity> list=loadList(hql);
		return list.get(0);
	}

	@Override
	public List<UserActivity> getJoinActivityUserByID(String hql, int current, int applySize) {
		List<UserActivity> list=splitpageList(hql,current,applySize);
		return list;
	}

	@Override
	public int getApplyTotal(String hql, Object[] params) {
		Long ApplyTotal=(Long)uniqueQuery(hql, params);
		return ApplyTotal.intValue();
	}


	@Override
	public List<Object[]> getUserActivityAll(String hql) {
		List<Object[]> objects=getObjectPageList(hql, 0, 8);
		return objects;
	}

	@Override
	public void deleteUserActivity(String hql, Object[] params) {
		executeByHql(hql, params);
	}
}
