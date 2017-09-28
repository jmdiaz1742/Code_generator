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
	private DocumentBuilderFactory XmlFactory;
	private DocumentBuilder XmlBuilder;
	private Document XmlDoc;
	private Element RootElement;
	private Element[] PinElement;
	TransformerFactory XmlTransFact;
	Transformer XmlTrans;
	DOMSource XmlSource;
	StreamResult XmlResult;
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
				XmlFactory = DocumentBuilderFactory.newInstance();
				XmlBuilder = XmlFactory.newDocumentBuilder();
				
				/* Create root element */
				XmlDoc = XmlBuilder.newDocument();
				RootElement = XmlDoc.createElement(STR_ROOT_EL);
				XmlDoc.appendChild(RootElement);
				
				totalPins = gpioPins;
				PinElement = new Element[totalPins];
				
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
		PinElement[pinNum] = XmlDoc.createElement(STR_PIN_EL);
		PinElement[pinNum].appendChild(XmlDoc.createTextNode(pin.getPin()));
		RootElement.appendChild(PinElement[pinNum]);
		
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
		Element childEl = XmlDoc.createElement(elName);
		childEl.appendChild(XmlDoc.createTextNode(elInfo));
		PinElement[pinNum].appendChild(childEl);
	}
		
	/**
	 * Write the XMl file
	 * @param fileName Name of XML configuration file
	 */
	public void writeXml(String fileName) {
		try {
			XmlTransFact = TransformerFactory.newInstance();
			XmlTrans = XmlTransFact.newTransformer();
			XmlSource = new DOMSource(XmlDoc);
			XmlResult = new StreamResult(new File(fileName));
			
			XmlTrans.transform(XmlSource, XmlResult);
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
