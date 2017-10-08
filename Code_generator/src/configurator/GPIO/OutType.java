package configurator.GPIO;

/**
 * Pin's output type
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public enum OutType {
	OTYPE_PUSH_PULL,
	OTYPE_OPEN_DRAIN,
	OTYPE_NOT_AVAILABLE,
	OTYPE_MAX_VALUE;
	
	/**
	 * Name as String
	 */
	public static final String STR_NAME = "OutType";
	
	/**
	 * Get the corresponding output type from its name as String
	 * @param conf Configuration name 
	 * @return Output type
	 */
	public static OutType getConfFromString(String conf) {
		OutType outType = OTYPE_MAX_VALUE;
		
		switch(conf) {
		case "OTYPE_PUSH_PULL":
			outType = OutType.OTYPE_PUSH_PULL;
			break;
		case "OTYPE_OPEN_DRAIN":
			outType = OutType.OTYPE_OPEN_DRAIN;
			break;
		case "OTYPE_NOT_AVAILABLE":
			outType = OutType.OTYPE_NOT_AVAILABLE;
			break;
		case "OTYPE_MAX_VALUE":
		default:
			outType = OTYPE_MAX_VALUE;
			break;
		}
		return outType;
	}
}
