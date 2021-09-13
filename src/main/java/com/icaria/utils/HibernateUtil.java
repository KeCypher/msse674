/**
 * 
 */
package com.icaria.utils;

/**
 * @author Keanan
 *
 */
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

 
public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
 
    private static SessionFactory buildSessionFactory() {
        try {
        	URL cfgResource = Thread.currentThread().getContextClassLoader().getResource("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
                    .configure(new File(cfgResource.toURI())).build();
 
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
 
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
         
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    @SuppressWarnings("rawtypes")
	public static List query(String queryString) {
    	List results = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery(queryString);
			results = q.getResultList();
		} catch (Exception e) {
			logger.fatal(e);
		} finally {
			session.getTransaction().commit();
		}
		return results;
    }
 
    public static void shutdown() {
        getSessionFactory().close();
    }

	public static Object queryUnique(String query) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Query q = session.createQuery(query);
			q.setMaxResults(1);
			return q.getSingleResult();
		} catch (Exception e) {
			logger.fatal(e);
		} finally {
			session.getTransaction().commit();
		}
		return null;
	}
 
}