package edu.csust.volunteer.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csust.remote.dao.BaseDao;
import edu.csust.remote.dao.IdownLoadDao;
import edu.csust.remote.entity.Download;
import edu.csust.remote.entity.Info;
import edu.csust.remote.service.IdownLoadService;
import edu.csust.remote.service.ItypeService;
@Service("IdownLoadService")
public class DownLoadServiceImpl implements IdownLoadService {

	@Autowired
	private BaseDao<Download> baseDao;
	@Autowired
	private IdownLoadDao downloadDao;
	@Autowired
	private ItypeService typeSercice;
	@Override
	public List<Download> getLastSevenInfo(int typeno,int current,int size) {
		String hql="";
		if (typeno==0) hql="from Download order by id desc";
		else hql="from Download where type=?";
		int number=size;
		Object params[]={typeno};
		List<Download> infos=downloadDao.getDownloadInfos(hql,current,number,params);
		return infos;
	}
	@Override
	//删除一条下载记录，并将源文件也删除
	public boolean deleteInfoById(int id) {
		String hql="delete Download i where i.id=?";
		Object params[]={id};
		baseDao.executeByHql(hql, params);
		return true;
	}
	
	@Override
	//获得下载内容的条数
	public Integer getNumber(int i) {
		String hql="select count(*) from Download where type= "+i+" ";
		Long number=baseDao.getCount(hql);
		return number.intValue();
	}
	/*上传的路径*/
	@Override
	public boolean updateDownloadInfo(int id, String name, int type) {
		String typename=typeSercice.getTypeNameByTypeNo(type);	
		DateTime now = new DateTime();// 取得当前时间  
    	String time = now.toString("yyyy/MM/dd");
		String hql="update Download w set w.name =? , w.type=? , w.typename=? ,w.time=? where w.id = ?";
		Object params[]={name,type,typename,time,id};
		baseDao.executeByHql(hql, params);
		return true;
	}
	@Override
	public void savaDownloadInfo(int type, String path, String fileName,String time) {
		Download download=new Download();
		download.setName(fileName);
		download.setPath(path);//主要是查看path的路径变化，有没有带文件名
		download.setTime(time);
		download.setType(type);
		download.setTypename(typeSercice.getTypeNameByTypeNo(type));
		baseDao.save(download);
	}
	@Override
	public String getDownLoadInfoById(int no) {
		String hql="from Download i where i.id=?";
		Object params[]={no};
		Download download=baseDao.load(hql, params);
		return download.getPath();
	}
}
