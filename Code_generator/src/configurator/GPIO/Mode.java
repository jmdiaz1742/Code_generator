package configurator.GPIO;

/**
 * GPIO modes
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public enum Mode {
	MODE_INPUT,
	MODE_OUTPUT,
	MODE_ALTERNATE_FUNCTION,
	MODE_ANALOG,
	MODE_MAX_VALUE;
	
	/**
	 * Name as String
	 */
	public static final String STR_NAME = "Mode";
	
	/* Public methods */
	
	/**
	 * Get the corresponding mode from its name as String
	 * @param conf Configuration name 
	 * @return Mode
	 */
	public static Mode getConfFromString(String conf) {
		Mode mode = MODE_MAX_VALUE;
		
		switch(conf) {
		case "MODE_INPUT":
			mode = Mode.MODE_INPUT;
			break;
		case "MODE_OUTPUT":
			mode = Mode.MODE_OUTPUT;
			break;
		case "MODE_ALTERNATE_FUNCTION":
			mode = Mode.MODE_ALTERNATE_FUNCTION;
			break;
		case "MODE_ANALOG":
			mode = Mode.MODE_ANALOG;
			break;
		case "MODE_MAX_VALUE":
		default:
			mode = MODE_MAX_VALUE;
			break;
		}
		return mode;
	}
}
