package configurator.GPIO;

/**
 * GPIO modes
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public enum AltMode {
	/**
	 * Analog
	 */
	ALT_MODE_ANALOG,
	
	/**
	 * UART
	 */
	ALT_MODE_UART,
	
	/**
	 * I2C
	 */
	ALT_MODE_I2C,
	
	/**
	 * SPI
	 */
	ALT_MODE_SPI,

	/**
	 * No alternate mode
	 */
	ALT_MODE_NONE,
	/**
	 * Maximum value for Mode enum
	 */
	ALT_MODE_MAX_VALUE;

	/**
	 * Name as String
	 */
	public static final String STR_NAME = "AltMode";

	/* Public methods */

	/**
	 * Get the corresponding mode from its name as String
	 * 
	 * @param conf Configuration name
	 * @return Mode
	 */
	public static AltMode getConfFromString(String conf) {
		AltMode altMode = ALT_MODE_MAX_VALUE;

		switch (conf) {
		case "ALT_MODE_ANALOG":
			altMode = AltMode.ALT_MODE_ANALOG;
			break;
		case "ALT_MODE_UART":
			altMode = AltMode.ALT_MODE_UART;
			break;
		case "ALT_MODE_I2C":
			altMode = AltMode.ALT_MODE_I2C;
			break;
		case "ALT_MODE_SPI":
			altMode = AltMode.ALT_MODE_SPI;
			break;
		case "ALT_MODE_NONE":
			altMode = AltMode.ALT_MODE_NONE;
			break;
		case "ALT_MODE_MAX_VALUE":
		default:
			altMode = AltMode.ALT_MODE_MAX_VALUE;
			break;
		}
		return altMode;
	}
}
