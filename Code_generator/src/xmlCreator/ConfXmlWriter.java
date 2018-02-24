package xmlCreator;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import common.ErrorCode;
import common.Features;
import configurator.PinConf;
import configurator.GPIO.CodeName;
import configurator.GPIO.Mode;
import configurator.GPIO.OutLevel;
import configurator.GPIO.OutType;
import configurator.GPIO.Pull;
import configurator.GPIO.Speed;
import microcontroller.Microcontroller;

/**
 * Write a XML file
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class ConfXmlWriter {
	/* Private fields */
	private Document XmlDoc;
	private Element RootElement;
	private Element[] PinElement;
	Microcontroller UCConf;

	private static final String STR_ROOT_EL = "Microcontroller_Configuration";
	private static final String STR_PIN_EL = "pin";
	private static final String STR_PORT = "port";
	private static final String STR_NAME = "name";

	/**
	 * Constructor
	 * 
	 * @param uC
	 *            Microcontroller configuration
	 */
	public ConfXmlWriter(Microcontroller uC) {
		DocumentBuilderFactory xmlFactory;
		DocumentBuilder xmlBuilder;

		UCConf = uC;

		if (UCConf.getUc_gpioNum() > 0) {
			try {
				xmlFactory = DocumentBuilderFactory.newInstance();
				xmlBuilder = xmlFactory.newDocumentBuilder();

				/* Create root element */
				XmlDoc = xmlBuilder.newDocument();
				RootElement = XmlDoc.createElement(STR_ROOT_EL);
				XmlDoc.appendChild(RootElement);

				PinElement = new Element[UCConf.getUc_gpioNum()];
				configurePins();

			} catch (ParserConfigurationException e) {
				Features.verbosePrint("Error creating XML file...");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Configure all pins
	 */
	private void configurePins() {
		for (int pinNum = 0; pinNum < UCConf.getUc_gpioNum(); pinNum++) {
			addPin(UCConf.GpioCfgPin[pinNum], pinNum);
		}
	}

	/**
	 * Add a pin configuration to the file
	 * 
	 * @param pin
	 *            Pin configuration
	 * @param pinNum
	 *            Number of GPIO pin
	 */
	public void addPin(PinConf pin, int pinNum) {
		PinElement[pinNum] = XmlDoc.createElement(STR_PIN_EL);
		PinElement[pinNum].appendChild(XmlDoc.createTextNode(pin.getPinName()));
		RootElement.appendChild(PinElement[pinNum]);

		/* Write the pins configuration information */
		addPinChild(STR_NAME, pin.getPinName(), pinNum);
		addPinChild(STR_PORT, pin.getPort(), pinNum);
		addPinChild(Mode.STR_NAME, pin.getMode().name(), pinNum);
		addPinChild(OutType.STR_NAME, pin.getOutType().name(), pinNum);
		addPinChild(OutLevel.STR_NAME, pin.getOutLevel().name(), pinNum);
		addPinChild(Pull.STR_NAME, pin.getPull().name(), pinNum);
		addPinChild(Speed.STR_NAME, pin.getSpeed().name(), pinNum);
		addPinChild(CodeName.STR_NAME, pin.getCodeName(), pinNum);
	}

	/**
	 * Add a configuration child element to the pin
	 * 
	 * @param elName
	 *            Configuration name
	 * @param elInfo
	 *            Configuration information
	 * @param pinNum
	 *            GPIO pin number
	 */
	private void addPinChild(String elName, String elInfo, int pinNum) {
		Element childEl = XmlDoc.createElement(elName);
		childEl.appendChild(XmlDoc.createTextNode(elInfo));
		PinElement[pinNum].appendChild(childEl);
	}

	/**
	 * Write the XMl file
	 * 
	 * @param fileName
	 *            Name of XML configuration file
	 * @return Error status
	 */
	public ErrorCode writeXml(String fileName) {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		TransformerFactory xmlTransFact;
		Transformer xmlTrans;
		DOMSource xmlSource;
		StreamResult xmlResult;
		File xmlFile;

		try {
			xmlFile = new File(fileName);
			xmlFile.getParentFile().mkdirs();

			xmlTransFact = TransformerFactory.newInstance();
			xmlTrans = xmlTransFact.newTransformer();
			xmlSource = new DOMSource(XmlDoc);
			xmlResult = new StreamResult(xmlFile);

			xmlTrans.transform(xmlSource, xmlResult);
		} catch (TransformerConfigurationException e) {
			errorStatus = ErrorCode.FILE_WRITE_ERROR;
			Features.verbosePrint("Error Creating XML file...");
			e.printStackTrace();
		} catch (TransformerException e) {
			errorStatus = ErrorCode.FILE_WRITE_ERROR;
			Features.verbosePrint("Error writing XML file...");
			e.printStackTrace();
		}
		Features.verbosePrint("Saved file " + fileName);

		return errorStatus;
	}
}
