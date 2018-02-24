package xmlParser;

import java.io.File;

import common.ErrorCode;
import common.Features;
import configurator.PinConf;
import microcontroller.Microcontroller;
import microcontroller.Pin;
import xmlCreator.ConfXmlWriter;

/**
 * Dummy main class for testing the other classes
 * 
 * @author Miguel Diaz
 *
 */
public class TestMain {
	/* Private static fields */
	private static final String FILE_NAME = "exampleConf.xml";

	/**
	 * Main without GUI
	 * 
	 * @param openOption
	 *            Options include:
	 */
	public static void main(String[] openOption) {
		ErrorCode errorState = ErrorCode.NO_ERROR;
		int totalPins;
		Pin[] pin;
		Microcontroller uCtrl;
		int gpioRef[];
		PinConf gpio[];

		System.out.println("Initializing XML parser test...");
		Features.debugPrint("Debug mode enabled...");

		/* Get the complete path of the XML file */
		String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
				+ System.getProperty("file.separator") + FILE_NAME;
		System.out.println("File path " + fileName);

		/* Open the file */
		XmlOpener xmlOpener = new XmlOpener();
		File xmlFile = new File(fileName);
		errorState = xmlOpener.OpenFile(xmlFile);
		if (errorState != ErrorCode.NO_ERROR) {
			System.out.println("Error opening file, exiting...");
			return;
		}

		uCtrl = new Microcontroller(xmlOpener.getParsedDoc());

		/* Process the file */
		errorState = uCtrl.processDocument();
		if (errorState != ErrorCode.NO_ERROR) {
			System.out.println("Error parsing file, exiting...");
			return;
		}

		/* Get the microcontroller's pins */
		totalPins = uCtrl.getUc_pinNum();
		System.out.println("Microcontroller: " + uCtrl.getUc_manufacturer() + " " + uCtrl.getUc_model());
		System.out.println("Pins: " + totalPins);

		pin = new Pin[totalPins];
		gpioRef = new int[uCtrl.getUc_gpioNum()];
		int gpioIndex = 0;

		for (int pinNum = 0; pinNum < totalPins; pinNum++) {
			pin[pinNum] = uCtrl.getPin(pinNum);

			if (!pin[pinNum].isValid()) {
				errorState = ErrorCode.EX_ERROR;
				Features.verbosePrint("Pin " + pinNum + " is invalid!");
			}
			/* Save the reference of GPIO pins */
			if (pin[pinNum].getFunc_gpio()) {
				gpioRef[gpioIndex] = pinNum;
				gpioIndex++;
			}
		}

		gpio = new PinConf[uCtrl.getUc_gpioNum()];
		for (int gpioNum = 0; gpioNum < uCtrl.getUc_gpioNum(); gpioNum++) {
			gpio[gpioNum] = new PinConf(pin[gpioRef[gpioNum]]);
			Features.verbosePrint("GPIO: " + gpioRef[gpioNum]);
		}

		/* Print file */
		String confFileName = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
				+ System.getProperty("file.separator") + "conf.xml";
		ConfXmlWriter xmlFileWriter = new ConfXmlWriter(uCtrl);
		xmlFileWriter.writeXml(confFileName);

	}
}
