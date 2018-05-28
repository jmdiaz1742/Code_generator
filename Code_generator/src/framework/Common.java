package framework;

/**
 * Framework common fields and methods
 *
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Common {
	/* public fields */

	/* Private fields */

	private static String installationFwkPath;
	private static String projectFwkPath;

	private final static String STR_DRIVERS = "drivers";
	private final static String STR_CFG = "cfg";
	private final static String STR_CFG_FILE = "_cfg.";
	private final static String STR_EXT_FILE_C = "c";
	private final static String STR_EXT_FILE_H = "h";

	public final static String STR_MODULE_GPIO = "gpio";

	/* Methods */

	/**
	 * Get installation framework path
	 * 
	 * @return installation framework path
	 */
	public static String getInstallationFwkPath() {
		return installationFwkPath;
	}

	/**
	 * Set installation framework path
	 * 
	 * @param installationFwkPath
	 *            installation framework path
	 */
	public static void setInstallationFwkPath(String installationFwkPath) {
		Common.installationFwkPath = installationFwkPath;
	}

	/**
	 * Get project's framework path
	 * 
	 * @return project's framework path
	 */
	public static String getProjectFwkPath() {
		return projectFwkPath;
	}

	/**
	 * Set project's framework path
	 * 
	 * @param projectFwkPath
	 *            project's framework path
	 */
	public static void setProjectFwkPath(String projectFwkPath) {
		Common.projectFwkPath = projectFwkPath;
	}

	/**
	 * Get configuration module files folder path
	 *
	 * @param fwkPath
	 *            Framework folder path
	 *
	 * @param cfgModule
	 *            Configuration module name
	 * @return Configuration files folder path
	 */
	public static String getCfgPath(String fwkPath, String cfgModule) {
		String path;

		path = fwkPath + System.getProperty("file.separator");
		path += STR_DRIVERS + System.getProperty("file.separator");
		path += cfgModule + System.getProperty("file.separator") + STR_CFG;

		return path;
	}

	/**
	 * Get GPIO configuration file path
	 *
	 * @param fwkPath
	 *            Framework folder path
	 * @param cfgModule
	 *            Configuration module name
	 * @return GPIO configuration file path
	 */
	public static String getCfgFileCPath(String fwkPath, String cfgModule) {
		String cfgFileC;

		cfgFileC = fwkPath + System.getProperty("file.separator");
		cfgFileC += STR_DRIVERS + System.getProperty("file.separator");
		cfgFileC += cfgModule + System.getProperty("file.separator");
		cfgFileC += STR_CFG + System.getProperty("file.separator");
		cfgFileC += cfgModule + STR_CFG_FILE + STR_EXT_FILE_C;

		return cfgFileC;
	}

	/**
	 * Get GPIO configuration header file path
	 *
	 * @param fwkPath
	 *            Framework folder path
	 * @param cfgModule
	 *            Configuration module name
	 * @return GPIO configuration header file path
	 */
	public static String getCfgFileHPath(String fwkPath, String cfgModule) {
		String cfgFileH;

		cfgFileH = fwkPath + System.getProperty("file.separator");
		cfgFileH += STR_DRIVERS + System.getProperty("file.separator");
		cfgFileH += cfgModule + System.getProperty("file.separator");
		cfgFileH += STR_CFG + System.getProperty("file.separator");
		cfgFileH += cfgModule + STR_CFG_FILE + STR_EXT_FILE_H;

		return cfgFileH;
	}
}
