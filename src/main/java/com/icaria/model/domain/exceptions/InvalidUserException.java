/**
 * 
 */
package com.icaria.model.domain.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Keanan
 *
 */
public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(InvalidUserException.class);


	public InvalidUserException(final String inMessage)
    {
        super(inMessage);
        logger.fatal(inMessage);
    }
	
	
	public InvalidUserException(final String inMessage, final Throwable inNestedException)
    {
        super(inMessage, inNestedException);
        logger.fatal(inMessage, inNestedException);
    }

}
