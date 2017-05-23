package com.dao.entityManagerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.dao.entity.InventoryType;
@Service
@Scope("prototype")
public class SessionFactoryInstance {
	
	public SessionFactory createSession (){
		SessionFactory factory = null;
		try{
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}catch(Throwable ex){
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
		}
		return factory;
			
	}
	public static void main(String args[]){
		SessionFactoryInstance obj = new SessionFactoryInstance();
		SessionFactory factory = obj.createSession();
		Session session = factory.openSession();
		InventoryType inventoryType = new InventoryType();
		inventoryType.setInventoryTypeId(106);
		inventoryType.setInventoryType("Monitor");
		Transaction transaction = null ;
		try{
			transaction = session.beginTransaction();
			session.persist(inventoryType);
			transaction.commit();		
		}catch(HibernateException e){
	        if (transaction!=null) transaction.rollback();
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
	}
}
