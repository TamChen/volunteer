package edu.csust.volunteer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.AttentionDao;
import edu.csust.volunteer.model.Activity;
import edu.csust.volunteer.model.Attention;
@Repository("attentionDao")
public class AtentionDaoImpl extends BaseDaoImpl<Attention>implements AttentionDao {
	@Override
	public List<Object[]> getMyFriend(String hql, int current, int maxSize) {
		List<Object[]> objects=getObjectPageList(hql, current,maxSize);
		return objects;
	}
	@Override
	public int getAttentionMeNum(String hql, Object[] params) {
		Long AttentionMeNumber=(Long)uniqueQuery(hql, params);
		return AttentionMeNumber.intValue();
	}
	@Override
	public List<Attention> getAttentionList(String hql, int current,
			int pagesize) {
		List<Attention> list=splitpageList(hql,current,pagesize);
		return list;
	}
	@Override
	public List<Object[]> getMyAllFriend(String hql) {
		return findMutiCol(hql);
	}
	@Override
	public List<Object[]> getMyAllAttentionMe(String hql) {
		return findMutiCol(hql);
	}
	@Override
	public void saveAttentionRelationship(Attention attention) {
		save(attention);
	}
	@Override
	public void deleteAttentionRecord(String hql, Object[] params) {
		executeByHql(hql, params);
	}

}
