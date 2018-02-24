package framework;

/**
 * Framework common fileds and methods
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Common {
	/* public fields */
	private static String installationFwkPath;
	private static String projectFwkPath;

	private final static String STR_DRIVERS = "drivers";
	private final static String STR_CFG = "cfg";
	private final static String STR_CFG_FILE = "_cfg.";
	private final static String STR_EXT_FILE_C = "c";
	private final static String STR_EXT_FILE_H = "h";

	/* Module related fields */
	private final static String STR_GPIO = "gpio";

	/**
	 * Get GPIO configuration files folder path
	 * 
	 * @param fwkPath
	 *            Path to framework folder
	 * @return GPIO configuration files folder path
	 */
	private static String getGpioCfgPath(String fwkPath) {
		String path;

		path = fwkPath + System.getProperty("file.separator") + STR_DRIVERS + System.getProperty("file.separator")
				+ STR_GPIO + System.getProperty("file.separator") + STR_CFG;

		return path;
	}

	/**
	 * Get GPIO configuration file path
	 * 
	 * @param fwkPath
	 *            Path to framework folder
	 * @return GPIO configuration file path
	 */
	public static String getGpioCfgFileCPath(String fwkPath) {
		String cfgFileC;

		cfgFileC = getGpioCfgPath(fwkPath) + STR_GPIO + STR_CFG_FILE + STR_EXT_FILE_C;

		return cfgFileC;
	}

	/**
	 * Get GPIO configuration header file path
	 * 
	 * @param fwkPath
	 *            Path to framework folder
	 * @return GPIO configuration header file path
	 */
	public static String getGpioCfgFileHPath(String fwkPath) {
		String cfgFileC;

		cfgFileC = getGpioCfgPath(fwkPath) + STR_GPIO + STR_CFG_FILE + STR_EXT_FILE_H;

		return cfgFileC;
	}
}
