package com.dao.entityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityMangerInstance {
	public EntityManager createEntityManager(){
		EntityManagerFactory factory = null ;
		EntityManager manager = null;
		try{
			factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			manager = factory.createEntityManager();
			}catch(Exception e){
				System.out.println("Unable to create ENTITY MANAGER");
			}
		return manager;
	}
}
