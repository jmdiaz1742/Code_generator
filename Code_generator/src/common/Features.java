package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that includes all project features
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Features {
	private static final String LOG_NAME_SUFFIX = "_log.log";
	private static final String logFilePath = System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "logs";
	private static File logFile;
	private static BufferedWriter logWriter;

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

	public static boolean LOG_FILE = true;

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
	private static final int VERSION_MINOR = 1;
	private static final int VERSION_PATCH = 0;

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
	public static final String VERSION_NAME = "Endor";

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
			if (LOG_FILE) {
				try {
					logWriter = new BufferedWriter(new FileWriter(logFile.getAbsolutePath(), true));
					logWriter.write(message + "\r\n");
					logWriter.close();
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
			if (LOG_FILE) {
				try {
					logWriter = new BufferedWriter(new FileWriter(logFile.getAbsolutePath(), true));
					logWriter.write(message + "\r\n");
					logWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println(DEBUG_STR + "Error writing to log file!");
				}
			}
		}
	}

	public static void initLog() {
		if (LOG_FILE) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			File logDirectory = new File(logFilePath);
			if (!logDirectory.exists()) {
				logDirectory.mkdir();
			}

			logFile = new File(logFilePath + System.getProperty("file.separator") + dtf.format(now) + LOG_NAME_SUFFIX);

			try {
				if (logFile.createNewFile()) {
					System.out.println(VERBOSE_STR + "Created log file in " + logFile.getAbsolutePath().toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out
						.println(VERBOSE_STR + "Failed to create log file in " + logFile.getAbsolutePath().toString());
			}
		}

	}

}
