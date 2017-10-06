package configurator.GPIO;

/**
 * Pin's pull resistor
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public enum Pull {
	PULL_UP,
	PULL_DOWN,
	PULL_NOT_AVAILABLE,
	PULL_MAX_VALUE;
	
	/**
	 * Name as String
	 */
	public static final String STR_NAME = "Pull";
}
