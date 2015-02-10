package model;

import java.sql.SQLException;

/**
 * Is thrown when a parsing error occurs. Extends from sql exception, since SQL
 * is never used in the application. So no missmatch can happen
 * 
 * @author rohx89
 *
 */
public class ParserException extends SQLException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

}
