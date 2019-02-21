package framework;

import common.ErrorCode;
import common.Features;
import microcontroller.Microcontroller;

/**
 * Framework common fields and methods
 *
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Common {
	/* public fields */

	/**
	 * Common implementation of New Line
	 */
	public final static String NL = "\r\n";

	/**
	 * Header for indicating generated code
	 */
	public final static String STR_GEN_CODE_NOTICE_HEADER = "// " + Features.GENERATOR_NAME
			+ " header: Generated code! ##########################################" + NL
			+ "// Do NOT modify code between this header and the footer below ################";

	/**
	 * Footer for indicating generated code
	 */
	public final static String STR_GEN_CODE_NOTICE_FOOTER = "// " + Features.GENERATOR_NAME
			+ " footer: Generated code! ##########################################" + NL
			+ "// Do NOT modify code between this footer and the header above ################";

	/* Private fields */

	private static String installationFwkPath;
	private static String projectFwkPath;

	private final static String STR_COMMON = "common";
	private final static String STR_DRIVERS = "drivers";
	private final static String STR_CFG = "cfg";
	private final static String STR_CFG_FILE = "_cfg.";
	private final static String STR_EXT_FILE_C = "c";
	private final static String STR_EXT_FILE_H = "h";

	private final static String STR_FRAMEWORK_COMMON = "frameworkCommon";
	private final static String STR_FRAMEWORK_INCLUDES = "frameworkIncludes";

	/**
	 * GPIO module name
	 */
	public final static String STR_MODULE_GPIO = "gpio";

	/**
	 * Macro definition String
	 */
	public final static String STR_DEFINITION = "#define ";

	/**
	 * Include header file string
	 */
	public final static String STR_INCLUDE = "#include ";

	/**
	 * Header file extension
	 */
	public final static String STR_HEADER_EXT = ".h";

	/* Methods */

	/**
	 * Get installation framework path
	 * 
	 * @return installation framework path
	 */
	public static String getInstallationFwkPath() {
		if (Features.DEBUG) {
			String fwkPath;
			fwkPath = System.getProperty("user.dir") + System.getProperty("file.separator");
			fwkPath += "testInstallation" + System.getProperty("file.separator");
			fwkPath += "framework";
			return fwkPath;
		} else {
			return installationFwkPath;
		}
	}

	/**
	 * Set installation framework path
	 * 
	 * @param installationFwkPath installation framework path
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
	 * @param projectFwkPath project's framework path
	 */
	public static void setProjectFwkPath(String projectFwkPath) {
		Common.projectFwkPath = projectFwkPath;
	}

	/**
	 * Get configuration module files folder path
	 *
	 * @param fwkPath   Framework folder path
	 *
	 * @param cfgModule Configuration module name
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
	 * @param fwkPath   Framework folder path
	 * @param cfgModule Configuration module name
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
	 * @param fwkPath   Framework folder path
	 * @param cfgModule Configuration module name
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

	/**
	 * Get the framework common header path
	 * 
	 * @param fwkPath Framework folder path
	 * @return Framework common header path
	 */
	public static String getFrameworkCommonFilePath(String fwkPath) {
		String FwkcommonPath;

		FwkcommonPath = fwkPath + System.getProperty("file.separator");
		FwkcommonPath += STR_COMMON + System.getProperty("file.separator");
		FwkcommonPath += STR_FRAMEWORK_COMMON + STR_HEADER_EXT;

		return FwkcommonPath;
	}

	/**
	 * Get the framework includes header path
	 * 
	 * @param fwkPath Framework folder path
	 * @return Framework includes header path
	 */
	public static String getFrameworkIncludesFilePath(String fwkPath) {
		String FwkcommonPath;

		FwkcommonPath = fwkPath + System.getProperty("file.separator");
		FwkcommonPath += STR_COMMON + System.getProperty("file.separator");
		FwkcommonPath += STR_FRAMEWORK_INCLUDES + STR_HEADER_EXT;

		return FwkcommonPath;
	}

	/**
	 * Get Framework common headers
	 * 
	 * @param uC Microcontroller used
	 * @return Common headers needed for framework
	 */
	public static String getCommonIncludes(Microcontroller uC) {
		String includes = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;
		includes += "// Framework Common header files:" + framework.Common.NL;

		for (int incNum = 0; incNum < uC.Includes_Common.length; incNum++) {
			includes += framework.Common.STR_INCLUDE + "<" + uC.Includes_Common[incNum] + ">";
			if (incNum < uC.Includes_Common.length - 1) {
				includes += framework.Common.NL;
			}
		}

		includes += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;

		return includes;
	}

	/**
	 * Get Framework Common definitions
	 * 
	 * @param uCMicrocontroller used
	 * @return Common definitions needed for framework
	 */
	public static String getCommonCfgDefinitions(Microcontroller uC) {
		String commonDefs = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;
		commonDefs += "// " + uC.getUc_manufacturer() + " " + uC.getUc_model() + " Common definitions:"
				+ framework.Common.NL;

		if (uC.Definitions_Common[0] != ErrorCode.STR_INVALID) {
			for (int defNum = 0; defNum < uC.Definitions_Common.length; defNum++) {
				commonDefs += framework.Common.STR_DEFINITION + uC.Definitions_Common[defNum];
				if (defNum < uC.Definitions_Common.length - 1) {
					commonDefs += framework.Common.NL;
				}
			}
		}

		commonDefs += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;

		return commonDefs;
	}
}
