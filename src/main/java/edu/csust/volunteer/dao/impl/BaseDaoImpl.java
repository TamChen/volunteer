package edu.csust.volunteer.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.BaseDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.Info;
@Transactional 
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

/*String sql="update Table set field = 'test'"
Session session = HibernateSessionFactory.getSession();
session.createSQLQuery(sql).executeUpdate();
ts.commit();*/
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
	
	public Session getSession2() {
		return sessionFactory.openSession();
	}
	
	@Override
	public Serializable save(T t) {
//		return getSession2().save(t);
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(t);
		tx.commit();
		session.close();
		return session;
	}

	@Override
	public void update(T t) {
		getSession2().update(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}
	
	@Override
	public void executeByHql(String hql, Object[] params) {
		Session session=getSession2();
		Query query =session.createQuery(hql);
		setObjectParams(query, params);
		query.executeUpdate(); 
		session.close();
	}
	
	@Override
	public T load(String hql) {
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getTheFirstElementOfList(list);
	}
	
	@Override
	public List<T> loadList(String hql) {
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
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
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getTheFirstElementOfList(list);
	}
	public List<T> getNews_F(int newsPage,String hql,int number) {
		Session session = getSession();
		Query query = session.createQuery(hql).setFirstResult((newsPage-1)*7).setMaxResults(number);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		session.close();
		return getNotEmptyList(list);
	}
	
	public int getNewsNumber_F(String hql) {
		Session session = getSession2();
		Long  newsNumber =  (Long) session.createQuery(hql).setCacheable(true).uniqueResult();
		session.close();
		return newsNumber.intValue();
	}
	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}
	public List<T> findBrodcastInfo(String hql){
		Query query = getSession().createQuery(hql);
		query.setMaxResults(4);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getNotEmptyList(list);
	}
	//查询实体
	public List<T> find(String hql) {
		Query query = getSession2().createQuery(hql);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getNotEmptyList(list);
	}
	//查询单个字段
	public List<String> findSingelCol(String hql) {
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<String> list = query.list();
		return list;
	}
	//分页查询活动
	@Override
	public List<T> findListWhitoutParam(String hql,int currentpage,int perPage){
		int start=(currentpage-1)*perPage;
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(perPage);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	public List<T> findListByParamAndPage(String hql,int currentpage,int perPage,Object[] params){
		int start=(currentpage-1)*perPage;
		Query query = getSession().createQuery(hql);
		setObjectParams(query, params);
		query.setFirstResult(start);
		query.setMaxResults(perPage);//每页数据为4个
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	//查询多个字段,这里是前台活动
	public List<Object[]> findMutiCol(String hql){
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		return list;
	}
	//分页查询多个字段
	public List<Object[]> getObjectPageList(String hql,int current,int pageSize){
		int start=(current-1)*pageSize;
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		return list;
	}
	//分页查询活动
	@Override
	public List<T> splitpageList(String hql,int currentpage,int perPage){
		int start=(currentpage-1)*perPage;
		Query query = getSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(perPage);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	public List<Object[]> MutiLoad(String hql,Object[] params){
		Query query = getSession().createQuery(hql);
		setObjectParams(query, params);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		return list;
	}
	public List<T> findActivityList(String hql,int currentpage,Object[] params){
		int start=(currentpage-1)*4;
		Query query = getSession().createQuery(hql);
		setObjectParams(query, params);
		query.setFirstResult(start);
		query.setMaxResults(4);//每页数据为4个
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	public List<T> loadInfo(String hql,Object[] params) {
		Query query = getSession().createQuery(hql);
		setObjectParams(query, params);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	public List<Integer> findSingelCol(String hql,Object[] params) {
		Query query = getSession().createQuery(hql);
		setObjectParams(query, params);
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();
		return list;
	}
	public List<T> find(String hql, Map<String, ?> params) {
		Query query = getSession().createQuery(hql);
		setMapParams(query,params);
		query.setCacheable(true);
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
		query.setCacheable(true);
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return getNotEmptyList(list);
	}

	@Override
	public Long getCount(String hql) {
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long getCount(String hql, Map<String, ?> params) {
		Query query = getSession().createQuery(hql);
		setMapParams(query,params);
		query.setCacheable(true);
		return (Long) query.uniqueResult();
	}
	
	public Object uniqueQuery(String hql, Object[] parameters) {
		Query query=this.sessionFactory.getCurrentSession().createQuery(hql);
		setObjectParams(query, parameters);
		query.setCacheable(true);
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
