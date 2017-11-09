package common;

/**
 * Error codes enum
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public enum ErrorCode {
	/**
	 * No error message
	 */
	NO_ERROR,
	
	/**
	 * Error during execution
	 */
	EX_ERROR,
	
	/**
	 * File reading error
	 */
	FILE_READ_ERROR,
	
	/**
	 * File writing error
	 */
	FILE_WRITE_ERROR,
	
	/**
	 * File configuration error
	 */
	FILE_CONF_ERROR;
	
	/**
	 * Error string
	 */
	public static final String STR_INVALID = "STR_INVALID";
}
