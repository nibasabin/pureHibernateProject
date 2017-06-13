package com.dao.managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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
	public void addUser(UserInformationEntity userInformationEntity) {

		SessionFactory factory = sessionFactoryInstance.createSession();
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(userInformationEntity);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public String getUserFirstLastName(String emailId) {
		SessionFactory factory = sessionFactoryInstance.createSession();
		Session session = factory.openSession();
		String hql = "SELECT U.firstName, U.lastName FROM UserInformationEntity U where U.emailId=:emailId";
		Query query = session.createQuery(hql);
		query.setParameter("emailId", emailId);
		List<Object[]> results  = query.list();
		String firstLastName ="";
		for(Object[] result: results){
			String firstName = (String) result[0];
			String lastName = (String)result[1];
			firstLastName = firstName + " "+lastName;
		}
		
		return firstLastName;

	}

}
