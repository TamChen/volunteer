package edu.csust.volunteer.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edu.csust.volunteer.model.Album;

public interface BaseDao<T> {
	public Serializable save(T t);

	public void update(T t);

	public void delete(T t);

	public void saveOrUpdate(T t);

	public T load(String hql);

	public T load(String hql, Object[] params);

	public T load(String hql, Map<String, ?> params);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, ?> params);

	public List<T> find(String hql, Map<String, ?> params, int pageIndex,
			int pageSize);

	public List<T> find(String hql, int pageIndex, int pageSize);

	public Long getCount(String hql);

	public Long getCount(String hql, Map<String, ?> params);
	
	public Object uniqueQuery(String hql, Object[] parameters);

	void executeByHql(String hql, Object[] params);

	List<T> loadList(String hql);

	List<T> splitpageList(String hql, int currentpage, int perPage);

	List<T> findListWhitoutParam(String hql, int currentpage, int perPage);
}
