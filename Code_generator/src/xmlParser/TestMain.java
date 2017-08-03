package xmlParser;
import common.Features;

/**
 * Dummy main class for testing the other classes 
 * @author Miguel Diaz
 *
 */
public class TestMain {
	
	/* Private fields */
	private FileOpener xmlOpener;
	private Pin[] pin;
	
	/* Private static fields */
	private static final String FILE_NAME = "";

	/**
	 * Main without GUI
	 * @param args Dummy argument
	 */
	public static void main(String[] args) {
		if (Features.DEBUG) {
			System.out.println("Initializing with debug messages...");
		}

	}
}
