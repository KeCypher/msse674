package com.icaria.model.services.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.icaria.utils.HibernateUtil;


/**
 * @author Keanan
 *
 */
public interface IService {
	
	default Session getSession() {
		SessionFactory factory = HibernateUtil.getSessionFactory();   
	    Session session = factory.getCurrentSession();
		return session;
	}
}
