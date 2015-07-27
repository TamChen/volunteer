package edu.csust.volunteer.dao;

import java.util.List;

import edu.csust.volunteer.model.Attention;

public interface AttentionDao {

	int getAttentionMeNum(String hql, Object[] params);

	List<Object[]> getMyFriend(String hql, int current, int maxSize);

	List<Attention> getAttentionList(String hql, int current, int pagesize);

	List<Object[]> getMyAllFriend(String hql);

	List<Object[]> getMyAllAttentionMe(String hql);

	void saveAttentionRelationship(Attention attention);

	void deleteAttentionRecord(String hql, Object[] params);

}
