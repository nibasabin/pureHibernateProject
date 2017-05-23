package com.dao.managers;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.dao.entity.Inventory;
import com.dao.exception.DataBaseException;
import com.dao.exception.SessionFactoryException;

@Component
public class InventoryManagerImpl implements InventoryManager {



	public SessionFactory createSession ()throws SessionFactoryException{
		SessionFactory factory = null;
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			System.err.println("Failed to create sessionFactory object." + ex);
	       // throw new ExceptionInInitializerError(ex); 
	        throw new SessionFactoryException("Failed to create sessionFactory object");
		}
		return factory;
			
	}

	
	@Override
	public void addInventory(Inventory inventory) throws DataBaseException {
		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null ;
		try{
			factory = createSession();
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.persist(inventory);
			transaction.commit();	
			
		}catch(SessionFactoryException | HibernateException sfe){
		    if (transaction!=null) transaction.rollback();
			System.out.println("could not create session factor");
			throw new DataBaseException(sfe);

	     }finally {
	     
	        session.close(); 
	     }
	}


	@Override
	public Integer getInventoryTypeId(String inventoryType) throws DataBaseException {
		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null ;
		List results = null;
		String hql = "SELECT I.inventoryTypeId FROM InventoryType I WHERE I.inventoryType = :inventoryType" ;
		try{
			factory = createSession();
			session = factory.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("inventoryType", inventoryType);
			results = query.list();
			
		}catch(SessionFactoryException sfe){
			System.out.println("could not create session factor");
			throw new DataBaseException(sfe);
		}

		return (Integer)results.get(0);
			
		
	}

/*		public static void main (String args[]){
			InventoryManagerImpl obj = new InventoryManagerImpl(); 
			Inventory inventory = new Inventory();
			InventoryType inventoryType = new InventoryType();
			inventoryType.setInventoryTypeId(100);
			inventoryType.setInventoryTypeName("Phone");
			
		
			inventory.setInventoryType(inventoryType);
			Transaction transaction = null;
			Session session = null;
			try{

			SessionFactory sessionFactory = obj.createSession();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.persist(inventory);
			session.persist(inventoryType);
			transaction.commit();
		}catch(HibernateException e){
	        if (transaction!=null) transaction.rollback();
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
			
		}*/
	}




