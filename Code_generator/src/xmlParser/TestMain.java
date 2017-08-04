package xmlParser;
import common.ErrorCode;
import common.Features;

/**
 * Dummy main class for testing the other classes 
 * @author Miguel Diaz
 *
 */
public class TestMain {
	
	/* Private fields */
	
	/* Private static fields */
	private static String FILE_NAME = "exampleConf.xml";

	/**
	 * Main without GUI
	 * @param openOption Options include:
	 */
	public static void main(String[] openOption) {

		ErrorCode ErrorState = ErrorCode.NO_ERROR;
		if (Features.DEBUG) {
			System.out.println("Initializing in debug mode...");
		}
		
		/* Get the complete path of the xml file */
		String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + FILE_NAME;
		System.out.println("File path " + fileName);
		
		XmlOpener xmlOpener= new XmlOpener();
		ErrorState = xmlOpener.OpenFile(fileName);
		if (ErrorState != ErrorCode.NO_ERROR) {
			if (Features.VERBOSE) {
				System.out.println("Error opening file, exiting...");
			}
			return;
		}

	}
}
