package configurator.GPIO;

/**
 * Pin's pull resistor
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public enum Pull {
	/**
	 * Pull Up
	 */
	PULL_UP,

	/**
	 * Pull Down
	 */
	PULL_DOWN,

	/**
	 * If the pin is configured as output, or there is no resistor available
	 */
	PULL_NOT_AVAILABLE,

	/**
	 * Maximum value for Pull enum
	 */
	PULL_MAX_VALUE;

	/**
	 * Name as String
	 */
	public static final String STR_NAME = "Pull";

	/**
	 * Get the corresponding Pull configuration from its name as String
	 * 
	 * @param conf Configuration name
	 * @return Pull configuration
	 */
	public static Pull getConfFromString(String conf) {
		Pull pull = PULL_MAX_VALUE;

		switch (conf) {
		case "PULL_UP":
			pull = Pull.PULL_UP;
			break;
		case "PULL_DOWN":
			pull = Pull.PULL_DOWN;
			break;
		case "PULL_NOT_AVAILABLE":
			pull = Pull.PULL_NOT_AVAILABLE;
			break;
		case "PULL_MAX_VALUE":
		default:
			pull = PULL_MAX_VALUE;
			break;
		}
		return pull;
	}
}
