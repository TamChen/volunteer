package edu.csust.volunteer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.StatisticDao;
import edu.csust.volunteer.model.Statistics;
@Repository("statisticDao")
public class StatisticDaoImpl extends BaseDaoImpl<Statistics> implements StatisticDao{

	@Override
	public List<Statistics> getActivityTotalPageNum(String hql, Object[] params) {
		return loadList(hql);
	}

	@Override
	public Statistics getInfo(String hql, Object[] params) {
		return load(hql, params);
	}

	@Override
	public void saveStatistic(Statistics statistics) {
		save(statistics);
	}

	@Override
	public void updateStatistic(Statistics statistics) {
		Session session = getSession2();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(statistics);
		tx.commit();
		session.close();
	}

}
