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

@Component
public class InventoryManagerImpl implements InventoryManager {



	public SessionFactory createSession (){
		SessionFactory factory = null;
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
		}
		return factory;
			
	}

	
	@Override
	public void addInventory(Inventory inventory) {
		SessionFactory factory = createSession();
		Session session = factory.openSession();
		Transaction transaction = null ;
		try{
			transaction = session.beginTransaction();
			session.persist(inventory);
			transaction.commit();		
		}catch(HibernateException e){
	        if (transaction!=null) transaction.rollback();
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
	}


	@Override
	public Integer getInventoryTypeId(String inventoryType) {
		SessionFactory factory = createSession();
		Session session = factory.openSession();
		String hql = "SELECT I.inventoryTypeId FROM InventoryType I WHERE I.inventoryType = :inventoryType" ;
		Query query = session.createQuery(hql);
		query.setParameter("inventoryType", inventoryType);
		List results = query.list();
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




