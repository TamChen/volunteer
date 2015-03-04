package edu.csust.volunteer.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.BaseDao;
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T t) {
		return getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public T load(String hql) {
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getTheFirstElementOfList(list);
	}

	@Override
	public T load(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setObjectParams(query, params);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getTheFirstElementOfList(list);
	}

	@Override
	public T load(String hql, Map<String, ?> params) {
		Query query = getSession().createQuery(hql);
		setMapParams(query,params);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getTheFirstElementOfList(list);
	}

	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	@Override
	public List<T> find(String hql) {
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getNotEmptyList(list);
	}

	@Override
	public List<T> find(String hql, Map<String, ?> params) {
		Query query = getSession().createQuery(hql);
		setMapParams(query,params);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getNotEmptyList(list);
	}

	@Override
	public List<T> find(String hql, Map<String, ?> params, int pageIndex,
			int pageSize) {
		Query query = getSession().createQuery(hql);
		setMapParams(query,params);
		int firstResult = (pageIndex - 1) * pageSize;
		query.setFirstResult(firstResult).setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getNotEmptyList(list);
	}

	@Override
	public List<T> find(String hql, int pageIndex, int pageSize) {
		Query query = getSession().createQuery(hql);
		int firstResult = (pageIndex - 1) * pageSize;
		query.setFirstResult(firstResult).setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getNotEmptyList(list);
	}

	@Override
	public Long getCount(String hql) {
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long getCount(String hql, Map<String, ?> params) {
		Query query = getSession().createQuery(hql);
		setMapParams(query,params);
		return (Long) query.uniqueResult();
	}
	
	public Object uniqueQuery(String hql, Object[] parameters) {
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		setObjectParams(query, parameters);
		return query.uniqueResult();
	}
	
	//设置参数的方法
	public void setObjectParams(Query query, Object[] params) {
		System.out.println(params.toString());
	    if (params != null&&params.length>0) {
	        for (int i = 0; i < params.length; i++) {
	            query.setParameter(i, params[i]);
	        }
	    }
	}
	public void setMapParams(Query query, Map<String, ?> params) {
		if (MapUtils.isNotEmpty(params)) {
			Set<String> keys = params.keySet();
			for (String key : keys) {
				query.setParameter(key, params.get(key));
			}
		}
	}
	//getNotEmptyList
	public List<T> getNotEmptyList(List<T> list){
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}
	//getTheFirstElementOfList
	public T getTheFirstElementOfList(List<T> list){
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
