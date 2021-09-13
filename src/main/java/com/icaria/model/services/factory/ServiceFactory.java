/**
 * 
 */
package com.icaria.model.services.factory;

import java.io.FileInputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.icaria.model.services.exceptions.ServiceLoadException;

/**
 * @author Keanan
 *
 */
public class ServiceFactory {
	private ServiceFactory() {}
	private static ServiceFactory serviceFactory = new ServiceFactory();
    private static final Logger logger = LogManager.getLogger(ServiceFactory.class);
	
	public static ServiceFactory getInstance() { 
		return serviceFactory; 
	}
	
	public IService getService(String serviceName) throws ServiceLoadException {
		IService retVal;
		try {
			String implName = getImplName(serviceName);
			Class<?> serviceClass = Class.forName(implName);
			/**
			 * Using getDeclaredConstructor as straight calling newInstance is deprecated.
			 * There might be a more elegant way to translate args to constructors for a more complex project
			 * ie. Multiple variations on User Service for multiple business verticals
			 */
			
			retVal = (IService)serviceClass.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new ServiceLoadException("Error loading " + serviceName + " Service", e);
		}
		return retVal;
	}
	
	private String getImplName(String serviceName) throws Exception {
		String retVal = null;

		URL resource = getClass().getClassLoader().getResource("config.xml");
		if (resource == null) {
			throw new IllegalArgumentException("file not found!");
		} else {
			//TODO: Should probably build a "config" class that only walks through loading a config once and holds it in memory.
		    try {
		    	XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
		    	XMLEventReader reader = xmlFactory.createXMLEventReader(new FileInputStream(resource.getPath()));
		    	while (reader.hasNext()) {
		    		XMLEvent next = reader.nextEvent();
		    		if(next.isStartElement()) {
		    			StartElement element = next.asStartElement();
		    			if (element.getName().getLocalPart().equals("service") && element.getAttributeByName(new QName("ServiceName")).getValue().equals(serviceName)) {
		    				retVal = element.getAttributeByName(new QName("FQDN")).getValue();
		    			}
		    		}
		    	}
		    } catch (Exception e) {
		    	logger.fatal("Error Reading xml", e);
		    }
		  }
		return retVal;
	}
    
    public static void main(String[] args) throws Exception {
    	ServiceFactory sf = ServiceFactory.getInstance();
    	System.out.println(sf.getImplName("IUserSvc"));
    }

}