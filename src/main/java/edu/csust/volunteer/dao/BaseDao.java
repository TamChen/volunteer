package edu.csust.volunteer.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
}
