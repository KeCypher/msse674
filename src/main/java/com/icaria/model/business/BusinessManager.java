/**
 * 
 */
package com.icaria.model.business;

import com.icaria.model.services.exceptions.ServiceLoadException;
import com.icaria.model.services.factory.IService;
import com.icaria.model.services.factory.ServiceFactory;

/** Abstract Business Layer Manager SuperType
 * @author Keanan
 *
 */
public abstract class BusinessManager {

	
	/**
	 * Following singleton pattern to insure resource conservation in and improve environment adaptability
	 */
	final transient protected ServiceFactory factory = ServiceFactory.getInstance();
	
	/**
	 * Generic action hook. This provides us with a simple route to add new actions with a ton of development
	 * @param commandString
	 * @param object
	 * @return true is successful, false otherwise
	 */
	public abstract boolean performAction(final String commandString, Object... obj); 
	
	/**
	 * We're going to have a protected method for getting our Services here
	 * Most functions will require a service of some sort, so we should extract it to a single source.
	 * 
	 * 
	 * @return
	 * @throws ServiceLoadException
	 */
	protected static IService getService(String serviceName) throws ServiceLoadException {
		ServiceFactory factory = ServiceFactory.getInstance();
		return factory.getService(serviceName);
	}	
	
}
