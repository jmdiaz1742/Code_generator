package xmlParser;
import common.ErrorCode;
import common.Features;

/**
 * Dummy main class for testing the other classes 
 * @author Miguel Diaz
 *
 */
public class TestMain {
	/* Private static fields */
	private static String FILE_NAME = "exampleConf.xml";

	/**
	 * Main without GUI
	 * @param openOption Options include:
	 */
	public static void main(String[] openOption) {
		ErrorCode errorState = ErrorCode.NO_ERROR;
		int totalPins;
		
		if (Features.DEBUG) {
			System.out.println(Features.DEBUG_STR + "Initializing XML parser test in debug mode...");
		} else {
			System.out.println("Initializing XML parser test...");
		}
		
		/* Get the complete path of the xml file */
		String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + FILE_NAME;
		System.out.println("File path " + fileName);
		
		XmlOpener xmlOpener = new XmlOpener();
		errorState = xmlOpener.OpenFile(fileName);
		if (errorState != ErrorCode.NO_ERROR) {
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "Error opening file, exiting...");
			}
			return;
		}
		
		totalPins = xmlOpener.getUc_pinNum();
		System.out.println("Microcontroller: " + xmlOpener.getUc_manufacturer() + " " + xmlOpener.getUc_model());
		System.out.println("Pins: " + totalPins);
		
	}
}
