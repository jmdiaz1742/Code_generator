package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that includes all project features
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Features {

	/* Features flags */

	/**
	 * Enables debug functions
	 */
	public static final boolean DEBUG = true;

	/**
	 * Enables console messages
	 */
	public static final boolean VERBOSE = true;

	/* Features indicators for system console */

	/**
	 * Verbose messages indicator on system console
	 */
	public static final String VERBOSE_STR = "# ";

	/**
	 * Debug messages indicator on system console
	 */
	public static final String DEBUG_STR = "#$ ";

	/* Static fields */

	/* Software version numbers */
	private static final int VERSION_MAJOR = 1;
	private static final int VERSION_MINOR = 2;
	private static final int VERSION_PATCH = 2;

	/**
	 * Complete Software version
	 */
	public static final String SW_VERSION = VERSION_MAJOR + "." + VERSION_MINOR + "." + VERSION_PATCH;

	/**
	 * Status of the software version
	 */
	public static final String VERSION_STATUS = "Alpha";

	/**
	 * Code name of the software version
	 */
	public static final String VERSION_NAME = "Felucia";

	public static final String GENERATOR_NAME = "Kamino";

	/* Static Methods */

	/**
	 * Print Verbose message to console
	 * 
	 * @param verboseMessage Message to display
	 */
	public static void verbosePrint(String verboseMessage) {
		if (VERBOSE) {
			String message = VERBOSE_STR + verboseMessage;
			System.out.println(message);
			if (GeneralSettings.LOG_FILE) {
				try {
					GeneralSettings.logWriter = new BufferedWriter(
							new FileWriter(GeneralSettings.logFile.getAbsolutePath(), true));
					GeneralSettings.logWriter.write(message + "\r\n");
					GeneralSettings.logWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println(VERBOSE_STR + "Error writing to log file!");
				}
			}
		}
	}

	/**
	 * Print Debug message to console
	 * 
	 * @param debugMessage Message to display
	 */
	public static void debugPrint(String debugMessage) {
		if (DEBUG) {
			String message = DEBUG_STR + debugMessage;
			System.out.println(message);
			if (GeneralSettings.LOG_FILE) {
				try {
					GeneralSettings.logWriter = new BufferedWriter(
							new FileWriter(GeneralSettings.logFile.getAbsolutePath(), true));
					GeneralSettings.logWriter.write(message + "\r\n");
					GeneralSettings.logWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println(DEBUG_STR + "Error writing to log file!");
				}
			}
		}
	}

}
