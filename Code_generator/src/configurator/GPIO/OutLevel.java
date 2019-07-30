package configurator.GPIO;

/**
 * Pin's output/input level
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public enum OutLevel {
	/**
	 * Low, logical 0, Ground
	 */
	LOW,

	/**
	 * High, logical 1, Vcc
	 */
	HIGH,

	/**
	 * Maximum value for OutLevel enum
	 */
	MAX_VALUE;

	/**
	 * Name as String
	 */
	public static final String STR_NAME = "OutLevel";

	/* Public methods */

	/**
	 * Get the corresponding mode from its name as String
	 * 
	 * @param conf Configuration name
	 * @return level
	 */
	public static OutLevel getConfFromString(String conf) {
		OutLevel level = MAX_VALUE;

		switch (conf) {
		case "LOW":
			level = OutLevel.LOW;
			break;
		case "HIGH":
			level = OutLevel.HIGH;
			break;
		default:
			level = OutLevel.MAX_VALUE;
			break;
		}
		return level;
	}
}
