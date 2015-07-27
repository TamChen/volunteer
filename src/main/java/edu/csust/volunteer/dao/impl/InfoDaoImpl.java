package edu.csust.volunteer.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.InfoDao;
import edu.csust.volunteer.model.Info;
@Repository("infoDao")
public class InfoDaoImpl extends BaseDaoImpl<Info>implements InfoDao {

	@Override
	public List<Info> getBrodcastInfoList(String hql, Object[] params) {
		List<Info> list=find(hql);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		return list;
	}

}
