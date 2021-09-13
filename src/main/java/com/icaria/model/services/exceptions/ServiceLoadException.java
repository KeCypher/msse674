/**
 * 
 */
package com.icaria.model.services.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Keanan Cypher
 *
 */
public class ServiceLoadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(ServiceLoadException.class);


	public ServiceLoadException(final String inMessage)
    {
        super(inMessage);
        logger.fatal(inMessage);
    }
	
	
	public ServiceLoadException(final String inMessage, final Throwable inNestedException)
    {
        super(inMessage, inNestedException);
        logger.fatal(inMessage, inNestedException);
    }

}
