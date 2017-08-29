package common;


/**
 * Class that includes all project features
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Features {
	/* Features flags */
	
	/**
	 * Enables debug functions
	 */
	public static final boolean DEBUG = false;
	
	/**
	 * Enables console messages
	 */
	public static final boolean VERBOSE = true;
	
	/* Features indicators for system console */
	
	/**
	 * Verbose messages indicator on system console
	 */
	public static final String VERBOSE_STR	= "# ";
	
	/**
	 * Debug messages indicator on system console
	 */
	public static final String DEBUG_STR	= "#$ ";
	
	/* Static Methods */
	
	/**
	 * Print Verbose message to console
	 * @param verboseMessage Message to display
	 */
	public static void verbosePrint(String verboseMessage) {
		if (VERBOSE) {
			System.out.println(VERBOSE_STR + verboseMessage);
		}
	}
	
	/**
	 * Print Debug message to console
	 * @param debugMessage Message to display
	 */
	public static void debugPrint(String debugMessage) {
		if (DEBUG) {
			System.out.println(DEBUG_STR + debugMessage);
		}
	}
	
}
