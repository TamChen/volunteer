package edu.csust.volunteer.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.AlbumDao;
import edu.csust.volunteer.dao.PictureDao;
import edu.csust.volunteer.model.Album;
import edu.csust.volunteer.model.Picture;
import edu.csust.volunteer.model.User;

@Repository("albumDao")
public class AlbumDaoImpl extends BaseDaoImpl<Album>implements AlbumDao{

			/* List<Picture> return splitpageList(hql,current,pageSize);

			 Picture return load(hql);*/
	
//===================================================================
		@Override
		public List<Album> getAlbumList(String hql){
			return find(hql);
		}

		@Override
		public Album getAlbum(String hql) {
			return load(hql);
		}

		@Override
		public void saveAlbum(Album album) {
			Session session = getSession2();
			session.saveOrUpdate(album);
			session.close();
		}

		@Override
		public void updateAlbum(Album album) {
			Session session = getSession2();
			Transaction tx = session.beginTransaction();
			session.update(album);
			tx.commit();
			session.close();
		}

		@Override
		public Object excuteHql(String hql) {
			Session session = getSession2();
			Query query= session.createQuery(hql);
			query.executeUpdate();
			session.close();
			return true;
		}
		
}
