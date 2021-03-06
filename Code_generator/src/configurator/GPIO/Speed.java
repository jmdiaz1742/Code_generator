package configurator.GPIO;

/**
 * Pin's speed
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public enum Speed {
	/**
	 * Fast
	 */
	SPEED_FAST,

	/**
	 * Medium
	 */
	SPEED_MEDIUM,

	/**
	 * High
	 */
	SPEED_HIGH,

	/**
	 * Not all MCUs will have this setting
	 */
	SPEED_NOT_AVAILABLE,

	/**
	 * Maximum value for Speed enum
	 */
	SPEED_MAX_VALUE;

	/**
	 * Name as String
	 */
	public static final String STR_NAME = "Speed";

	/**
	 * Get the corresponding Speed configuration from its name as String
	 * 
	 * @param conf Configuration name
	 * @return Speed
	 */
	public static Speed getConfFromString(String conf) {
		Speed speed = SPEED_MAX_VALUE;

		switch (conf) {
		case "SPEED_FAST":
			speed = Speed.SPEED_FAST;
			break;
		case "SPEED_MEDIUM":
			speed = Speed.SPEED_MEDIUM;
			break;
		case "SPEED_HIGH":
			speed = Speed.SPEED_HIGH;
			break;
		case "SPEED_NOT_AVAILABLE":
			speed = Speed.SPEED_NOT_AVAILABLE;
			break;
		case "SPEED_MAX_VALUE":
		default:
			speed = SPEED_MAX_VALUE;
			break;
		}
		return speed;
	}
}
