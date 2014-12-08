package edu.csust.volunteer.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.csust.volunteer.dao.BaseDao;

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
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public T load(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		if (ArrayUtils.isNotEmpty(params)) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public T load(String hql, Map<String, ?> params) {
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(params)) {
			Set<String> keys = params.keySet();
			for (String key : keys) {
				query.setParameter(key, params.get(key));
			}
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<T> find(String hql, Map<String, ?> params) {
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(params)) {
			Set<String> keys = params.keySet();
			for (String key : keys) {
				query.setParameter(key, params.get(key));
			}
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}

	@Override
	public List<T> find(String hql, Map<String, ?> params, int pageIndex,
			int pageSize) {
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(params)) {
			Set<String> keys = params.keySet();
			for (String key : keys) {
				query.setParameter(key, params.get(key));
			}
		}
		int firstResult = (pageIndex - 1) * pageSize;
		query.setFirstResult(firstResult).setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}

	@Override
	public List<T> find(String hql, int pageIndex, int pageSize) {
		Query query = getSession().createQuery(hql);
		int firstResult = (pageIndex - 1) * pageSize;
		query.setFirstResult(firstResult).setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		}
		return null;
	}

	@Override
	public Long getCount(String hql) {
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long getCount(String hql, Map<String, ?> params) {
		Query query = getSession().createQuery(hql);
		if (MapUtils.isNotEmpty(params)) {
			Set<String> keys = params.keySet();
			for (String key : keys) {
				query.setParameter(key, params.get(key));
			}
		}
		return (Long) query.uniqueResult();
	}

}
