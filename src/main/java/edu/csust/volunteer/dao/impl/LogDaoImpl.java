package edu.csust.volunteer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.LogDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.UserDiary;
import edu.csust.volunteer.model.UserActivity;
@Repository("logDao")
public class LogDaoImpl extends BaseDaoImpl<UserDiary> implements LogDao{

	@Override
	public List<UserDiary> getLogList(String hql, int current, int size) {
		Session session = getSession2();
		Query query = session.createQuery(hql);
		query.setFirstResult((current - 1) * size);
		query.setMaxResults(size);
		List<UserDiary> list = query.list();
		session.close();
		return list;
	}

	@Override
	public int getLogTotal(String hql) {
		Session session = getSession2();
		Long LogTotal = (Long) session.createQuery(hql).uniqueResult();
		session.close();
		return LogTotal.intValue();
	}

	@Override
	public UserDiary getLogByID(String hql) {
		return load(hql);
	}
	@Override
	public void updateLog(UserDiary log) {
		Session session = getSession2();
		Transaction tx = session.beginTransaction();
		session.update(log);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteLog(int logID) {
		Session session = getSession2();
		Query query= session.createQuery("delete from UserDiary log where log.id="+logID);
		query.executeUpdate();
		session.close();
	}

	@Override
	public void saveDiary(UserDiary userDiary) {
		Session session = getSession2();
		Transaction tx = session.beginTransaction();
		session.save(userDiary);
		tx.commit();
		session.close();
	}

	@Override
	public UserDiary getUnPostRecord(String hql, Object[] params) {
		
		return load(hql);
	}

	@Override
	public void deleteLogByIdAndUserNo(String hql, Object[] params) {
		executeByHql(hql, params);
	}

}
