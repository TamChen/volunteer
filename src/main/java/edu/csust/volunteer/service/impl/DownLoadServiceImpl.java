package edu.csust.volunteer.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.csust.volunteer.dao.BaseDao;
import edu.csust.volunteer.dao.IdownLoadDao;
import edu.csust.volunteer.model.Download;
import edu.csust.volunteer.service.IdownLoadService;


@Service("IdownLoadService")
public class DownLoadServiceImpl implements IdownLoadService {

	@Autowired
	private BaseDao<Download> baseDao;
	@Autowired
	private IdownLoadDao downloadDao;
	@Override
	public List<Download> getLastSevenInfo(int current,int size) {
		String hql="from Download order by id desc";
		int number=size;
		Object params[]={};
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
	public Integer getNumber() {
		String hql="select count(*) from Download";
		Long number=baseDao.getCount(hql);
		return number.intValue();
	}
	/*上传的路径*/
	@Override
	public boolean updateDownloadInfo(int id, String name, int type) {
		DateTime now = new DateTime();// 取得当前时间  
    	String time = now.toString("yyyy/MM/dd");
		String hql="update Download w set w.name =? , w.type=? , w.typename=? ,w.time=? where w.id = ?";
		Object params[]={name,time,id};
		baseDao.executeByHql(hql, params);
		return true;
	}
	@Override
	public void savaDownloadInfo(int type, String path, String fileName,String time) {
		Download download=new Download();
		download.setName(fileName);
		download.setPath(path);//主要是查看path的路径变化，有没有带文件名
		download.setTime(time);
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
