package xmlParser;
import common.ErrorCode;
import common.Features;
import microcontroller.Microcontroller;
import microcontroller.Pin;

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
		
		Microcontroller uCtrl = new Microcontroller(xmlOpener.getParsedDoc());
		
		errorState = uCtrl.processDocument();
		if (errorState != ErrorCode.NO_ERROR) {
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "Error parsing file, exiting...");
			}
			return;
		}
		
		totalPins = uCtrl.getUc_pinNum();
		System.out.println("Microcontroller: " + uCtrl.getUc_manufacturer() + " " + uCtrl.getUc_model());
		System.out.println("Pins: " + totalPins);
		
		Pin[] pin = new Pin[totalPins];
		
		for (int pinNum = 0; pinNum < totalPins; pinNum++) {
			pin[pinNum] = uCtrl.getPin(pinNum);
		}
		
	}
}
