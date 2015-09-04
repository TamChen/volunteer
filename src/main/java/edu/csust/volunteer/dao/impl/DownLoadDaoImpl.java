package edu.csust.volunteer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csust.volunteer.dao.IdownLoadDao;
import edu.csust.volunteer.dao.LogDao;
import edu.csust.volunteer.model.Download;
import edu.csust.volunteer.model.UserDiary;
@Repository("IdownLoadDao")
@Transactional
public class DownLoadDaoImpl extends BaseDaoImpl<Download> implements IdownLoadDao {
	@Override
	public List<Download> getDownloadInfos(String hql, int current, int number,
			Object[] params) {
		List<Download> infos=new ArrayList<Download>();
		infos=findListWhitoutParam(hql,current,number);
		return infos;
	}

}
