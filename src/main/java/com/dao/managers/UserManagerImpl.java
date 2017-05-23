package com.dao.managers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.entity.UserInformationEntity;
import com.dao.entityManagerFactory.SessionFactoryInstance;
@Component
public class UserManagerImpl implements UserManager {

	@Autowired
	SessionFactoryInstance sessionFactoryInstance;
	
	@Override
	public void addUser(UserInformationEntity userInformationEntity){
		
		SessionFactory factory = sessionFactoryInstance.createSession();
		Session session = factory.openSession();
		Transaction transaction = null ;
		try{
			transaction = session.beginTransaction();
			session.save(userInformationEntity);
			transaction.commit();		
		}catch(HibernateException e){
	        if (transaction!=null) transaction.rollback();
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
	}
	
}
