package com.cg.pp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = 
		Persistence.createEntityManagerFactory("JPA-PU");
	}
	
	public static EntityManager getEntityManager() {
		if(em == null || !em.isOpen()) {
			em = emf.createEntityManager();
		}
		return em;
	}
}
