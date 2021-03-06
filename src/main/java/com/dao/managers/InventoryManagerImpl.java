package com.dao.managers;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.entity.FilterCriteriaObject;
import com.dao.entity.GlobalInventory;
import com.dao.entity.Inventory;
import com.dao.entityManagerFactory.SessionFactoryInstance;
import com.dao.exception.DataBaseException;
import com.dao.exception.SessionFactoryException;

@Component
public class InventoryManagerImpl implements InventoryManager {

	@Autowired
	SessionFactoryInstance sessionFactoryInstance;

	public SessionFactory createSession() throws SessionFactoryException {
		SessionFactory factory = null;
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
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
		Transaction transaction = null;
		try {
			factory = createSession();
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.persist(inventory);
			transaction.commit();

		} catch (SessionFactoryException | HibernateException sfe) {
			if (transaction != null)
				transaction.rollback();
			System.out.println("could not create session factor");
			throw new DataBaseException(sfe);

		} finally {

			session.close();
		}
	}

	@Override
	public Integer getInventoryTypeId(String inventoryType) throws DataBaseException {
		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		List results = null;
		String hql = "SELECT I.inventoryTypeId FROM InventoryType I WHERE I.inventoryType = :inventoryType";
		try {
			factory = createSession();
			session = factory.openSession();
			Query query = session.createQuery(hql);
			query.setParameter("inventoryType", inventoryType);
			results = query.list();

		} catch (SessionFactoryException sfe) {
			System.out.println("could not create session factor");
			throw new DataBaseException(sfe);
		}

		return (Integer) results.get(0);

	}

	@Override
	public List<Inventory> getAllInventory(String category) {
		SessionFactory factory = sessionFactoryInstance.createSession();
		Session session = factory.openSession();
		Transaction transaction = null;
		List<Inventory> inventoryList = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("FROM Inventory I where I.inventoryCategory = :category and I.inventoryId IN (FROM GlobalInventory)");
			query.setParameter("category", category);
			inventoryList = query.list();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return inventoryList;
	}

	@Override
	public List<Inventory> getFilteredInventory(FilterCriteriaObject filterCriteriaObject) {
		SessionFactory factory = sessionFactoryInstance.createSession();
		Session session = factory.openSession();
		Transaction transaction = null;
		List<Inventory> inventoryList = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Inventory.class);
			criteria.add(Restrictions.eq("inventoryCategory", filterCriteriaObject.getCategory()));
			if (filterCriteriaObject.getMaxAmount() != 0 && filterCriteriaObject.getMinAmount() != 0) {
				criteria.add(Restrictions.between("inventoryPrice", filterCriteriaObject.getMinAmount(),
						filterCriteriaObject.getMaxAmount()));
			}
			if (filterCriteriaObject.getMinAmount() == 0 && filterCriteriaObject.getMaxAmount() != 0) {
				criteria.add(
						Restrictions.between("inventoryPrice", new Double("0"), filterCriteriaObject.getMaxAmount()));
			}

			if (filterCriteriaObject.getMinAmount() != 0 && filterCriteriaObject.getMaxAmount() == 0) {
				criteria.add(Restrictions.ge("inventoryPrice", filterCriteriaObject.getMinAmount()));
			}
			if (filterCriteriaObject.getSubCategoryList().size() > 0) {
				criteria.add(Restrictions.in("inventoryType", filterCriteriaObject.getSubCategoryList()));
			}
			if (filterCriteriaObject.getNewItem()== true && filterCriteriaObject.getUsedItem() == false) {
				criteria.add(Restrictions.eq("newInventory", true));
			}
			if (filterCriteriaObject.getUsedItem() == true && filterCriteriaObject.getNewItem() == false ) {
				criteria.add(Restrictions.eq("usedInventory", true));
			}
			if (filterCriteriaObject.getUsedItem() == false && filterCriteriaObject.getNewItem() == false ) {
				criteria.add(Restrictions.eq("usedInventory", true));
				criteria.add(Restrictions.eq("newInventory", true));
			}
			inventoryList = criteria.list();

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return inventoryList;
	}

	@Override
	public List<Inventory> getUsersInventory(String userName) {
		SessionFactory factory = sessionFactoryInstance.createSession();
		Session session = factory.openSession();
		List<Inventory> inventoryList = null;
			Query query = session.createQuery(" FROM Inventory where sellerName = :sellerName");
			query.setParameter("sellerName", userName);
			inventoryList = query.list(); 
			session.close();

		return inventoryList;
	}

	@Override
	public void addToGlobalInventory(Integer itemNo) throws DataBaseException {
		GlobalInventory globalInventory = new GlobalInventory();
		globalInventory.setInventoryId(itemNo);		
		markAsSold(itemNo);
		SessionFactory factory = null;
		Session session = null;
		Transaction transaction = null;
		try {
			factory = createSession();
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.persist(globalInventory);
			transaction.commit();

		} catch (SessionFactoryException | HibernateException sfe) {
			if (transaction != null)
				transaction.rollback();
			System.out.println("could not create session factor");
			throw new DataBaseException(sfe);

		} finally {

			session.close();
		}
	}
	

	public void markAsSold(Integer itemNo) throws DataBaseException {
		SessionFactory factory = null;
		Session session = null;
			try {
				factory = createSession();
				session = factory.openSession();
				Query query = session.createQuery("Update Inventory set itemSold = true where Inventory_Id=:itemNo");
				query.setParameter("itemNo", itemNo);
				query.executeUpdate();
			} catch (SessionFactoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{

			session.close();
			}
	}

	@Override
	public void deleteItem(Integer itemNo) throws DataBaseException {
		SessionFactory factory = null;
		Session session = null;
			try {
				factory = createSession();
				session = factory.openSession();
				Query query = session.createQuery("delete from Inventory where Inventory_Id=:itemNo");
				query.setParameter("itemNo", itemNo);
				query.executeUpdate();
				Query query2 = session.createQuery("delete from GlobalInventory where Inventory_Id=:itemNo");
				query2.setParameter("itemNo", itemNo);
				query2.executeUpdate();
			} catch (SessionFactoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{

			session.close();
			}

		
	}

}
