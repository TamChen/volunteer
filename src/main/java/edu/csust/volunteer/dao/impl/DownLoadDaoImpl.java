package edu.csust.volunteer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.csust.remote.dao.IdownLoadDao;
import edu.csust.remote.entity.Download;
import edu.csust.remote.entity.Info;
@Repository("IdownLoadDao")
@Transactional
public class DownLoadDaoImpl extends BaseDaoImpl<Download> implements IdownLoadDao {

	@Override
	public List<Download> getDownloadInfos(String hql, int current, int number,
			Object[] params) {
		List<Download> infos=new ArrayList<Download>();
		if(params[0].equals(0)){
			infos=findListWhitoutParam(hql,current,number);
		}else {
			infos=findListByParamAndPage(hql,current,number,params);
		}
		return infos;
	}

}
