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

import common.Features;
import configurator.PinConf;
import configurator.GPIO.Mode;
import configurator.GPIO.OutType;
import configurator.GPIO.Pull;
import configurator.GPIO.Speed;

/**
 * Write a XML file
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class ConfXmlWriter {
	/* Private fields */
	private DocumentBuilderFactory xmlFactory;
	private DocumentBuilder xmlBuilder;
	private Document xmlDoc;
	private Element rootElement;
	private Element[] pinElement;
	TransformerFactory xmlTransFact;
	Transformer xmlTrans;
	DOMSource xmlSource;
	StreamResult xmlResult;
	int totalPins = 0;
	
	private static final String	STR_ROOT_EL	= "Microcontroller_Configuration";
	private static final String	STR_PIN_EL	= "Pin";
	private static final String	STR_PORT	= "Port";
	
	/**
	 * Constructor
	 * @param gpioPins NUmber of GPIO pins
	 */
	public ConfXmlWriter (int gpioPins) {
		if (gpioPins > 0 ) {
			try {
				xmlFactory = DocumentBuilderFactory.newInstance();
				xmlBuilder = xmlFactory.newDocumentBuilder();
				
				/* Create root element */
				xmlDoc = xmlBuilder.newDocument();
				rootElement = xmlDoc.createElement(STR_ROOT_EL);
				xmlDoc.appendChild(rootElement);
				
				totalPins = gpioPins;
				pinElement = new Element[totalPins];
				
			} catch (ParserConfigurationException e) {
				Features.verbosePrint("Error creating XML file...");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Add a pin configuration to the file
	 * @param pin Pin configuration
	 * @param pinNum Number of GPIO pin
	 */
	public void addPin(PinConf pin, int pinNum) {
		pinElement[pinNum] = xmlDoc.createElement(STR_PIN_EL);
		pinElement[pinNum].appendChild(xmlDoc.createTextNode(pin.getPin()));
		rootElement.appendChild(pinElement[pinNum]);
		
		/* Write the pins configuration information */
		addPinChild(STR_PORT, pin.getPort(), pinNum);
		addPinChild(Mode.STR_NAME, pin.getMode().name(), pinNum);
		addPinChild(OutType.STR_NAME, pin.getOutType().name(), pinNum);
		addPinChild(Pull.STR_NAME, pin.getPull().name(), pinNum);
		addPinChild(Speed.STR_NAME, pin.getSpeed().name(), pinNum);
	}
	
	/**
	 * Add a configuration child element to the pin
	 * @param elName Configuration name
	 * @param elInfo Configuration information
	 * @param pinNum GPIO pin number
	 */
	private void addPinChild(String elName, String elInfo, int pinNum) {
		Element childEl = xmlDoc.createElement(elName);
		childEl.appendChild(xmlDoc.createTextNode(elInfo));
		pinElement[pinNum].appendChild(childEl);
	}
		
	/**
	 * Write the XMl file
	 * @param fileName Name of XML configuration file
	 */
	public void writeXml(String fileName) {
		try {
			xmlTransFact = TransformerFactory.newInstance();
			xmlTrans = xmlTransFact.newTransformer();
			xmlSource = new DOMSource(xmlDoc);
			xmlResult = new StreamResult(new File(fileName));
			
			xmlTrans.transform(xmlSource, xmlResult);
		} catch (TransformerConfigurationException e) {
			Features.verbosePrint("Error Creating XML file...");
			e.printStackTrace();
		} catch (TransformerException e) {
			Features.verbosePrint("Error writing XML file...");
			e.printStackTrace();
		}
		Features.verbosePrint(fileName + " printed correctly...");
	}
}
