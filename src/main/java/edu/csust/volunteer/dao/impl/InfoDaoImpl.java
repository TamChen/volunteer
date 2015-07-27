package edu.csust.volunteer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.InfoDao;
import edu.csust.volunteer.model.Info;
@Repository("infoDao")
public class InfoDaoImpl extends BaseDaoImpl<Info>implements InfoDao {

	@Override
	public List<Info> getBrodcastInfoList(String hql, Object[] params) {
		List<Info> list=findBrodcastInfo(hql);
		if(list==null)
		return null;
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		return list;
	}

	@Override
	public List<Info> getDetailInfo(String hql, Object[] params) {
		List<Info> list=new ArrayList<Info>();
		Info info=load(hql, params);
		list.add(info);
		return list;
	}

	@Override
	public List<Info> getNews_F(int newsPage,String hql, Object[] params,int number) {
		List<Info> list=getNews_F(newsPage,hql,number);
		return list;
	}

	@Override
	public int getNewsNumber_F(String hql, Object[] params) {
		int newsNumber =getNewsNumber_F(hql);
		return newsNumber;
	}

	@Override
	public Info getNewsByID(int newsID) {
		Session session = getSession2();
		Info in = (Info) session.get(Info.class, newsID);
		session.close();
		return in;
	}

	@Override
	public List<Info> getInfoByYear(String hql, Object[] params) {
		List<Info> list=loadInfo(hql, params);
		return list;
	}

	@Override
	public void addInfo(Info info) {
		Session session = getSession2();
		session.save(info);
		session.close();
	}

	@Override
	public void updateByInfoId(String hql, Object[] params) {
		executeByHql(hql, params);
		
	}


	@Override
	public void deleteInfoById(String hql, Object[] params) {
		executeByHql(hql, params);
	}

}
