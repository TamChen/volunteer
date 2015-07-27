package edu.csust.volunteer.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import edu.csust.volunteer.dao.PictureDao;
import edu.csust.volunteer.model.Picture;
import edu.csust.volunteer.model.User;
/**
 * 
 * @date 2015-3-7
 */
@Repository("pictureDao")
public class PictureDaoImpl extends BaseDaoImpl<Picture>implements PictureDao{
		public List<Picture> listActPicture(String hql){
			List<Picture> acts=find(hql);
			return acts;
		}
		
		public List<Picture> listPerPicture(String hql){
			return find(hql);
		}

		@Override
		public List<Picture> findActPicture(String hql) {
			// TODO Auto-generated method stub
			return find(hql);
		}

		@Override
		public List<Picture> findPictureList(String hql, int current,
				int pageSize) {
			return splitpageList(hql,current,pageSize);
		}

		@Override
		public int getPicNumInfo(String hql) {
			Long count1=(Long)getCount(hql);
			return count1.intValue();
		}
		@Override
		public Picture getPicture(String hql){
			return load(hql);
		}

		@Override
		public List<Picture> findPictureList(String hql) {
			return find(hql);
		}

		@Override
		public void savePic(Picture picture) {
			Session session = getSession();
			session.saveOrUpdate(picture);
			session.close();
		}

		@Override
		public boolean excuteHql(String hql) {
			Session session = getSession2();
			Query query= session.createQuery(hql);
			query.executeUpdate();
			session.close();
			return true;
		}

		@Override
		public void updatePicture(Picture picture) {
			Session session = getSession2();
			Transaction tx = session.beginTransaction();
			session.update(picture);
			tx.commit();
			session.close();
		}
		
}
