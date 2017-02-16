package com.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.config.HibernateConfig;
import com.dao.UserInfo;
import com.model.Account;
import com.model.User;

@Repository
public class UserInfoImpl implements UserInfo {
	
	public static boolean b=false;

	@Override
	public void save(User user) {
		Session session = HibernateConfig.getSessionFactory();
		try {
			session.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public  void checkUser(User user) {
		Session session = HibernateConfig.getSessionFactory();
		user.getUsername();
		user.getPassword();
		try {
			session.beginTransaction();
			@SuppressWarnings({ "deprecation" })
			List<?> userObjs = session.createNamedQuery("CheckUser").setProperties(user).list();
			for (int i = 0; i < userObjs.size(); i++) {
				System.out.println(userObjs.get(i));
			}
			if (userObjs.size() != 0) {
				
				System.out.println("login success");			
				b=true;
			} else {
				b=false;
				System.out.println("invalid credentials");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.clear();
		}
	}

	@Override
	public List<User> findUserbyname(String username) {
		Session session=HibernateConfig.getSessionFactory();
		List<User> userdata=null;
		try {
			Criteria ct= session.createCriteria(User.class);
			ct.add(Restrictions.eq("username", username));
			userdata=ct.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return userdata;
	}

	@Override
	public List<User> getUserData() {
		Session session = HibernateConfig.getSessionFactory();
		List<User> data = null;
		try{
			Criteria cr = session.createCriteria(User.class);
			data = cr.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return data;
	}

	@Override
	public void depositByAccount(Account account ) {
		Session session=HibernateConfig.getSessionFactory();	
		try{			
			session.update(account);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
}
   