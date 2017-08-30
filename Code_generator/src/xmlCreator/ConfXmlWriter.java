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
import microcontroller.Pin;

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
	int totalPins;
	
	private static final String	STR_ROOT_EL	= "Microcontroller_Configuration";
	private static final String	STR_PIN_EL	= "Pin";
	
	/**
	 * Create configuration file
	 * @param pins Total number of GPIO pins to save
	 */
	public ConfXmlWriter (int pins) {
		try {
			xmlFactory = DocumentBuilderFactory.newInstance();
			xmlBuilder = xmlFactory.newDocumentBuilder();
			
			/* Create root element */
			xmlDoc = xmlBuilder.newDocument();
			rootElement = xmlDoc.createElement(STR_ROOT_EL);
			xmlDoc.appendChild(rootElement);
			
			/* TODO: Add pins correctly */
			totalPins = pins;
			pinElement = new Element[pins];
			for (int pinNum = 0; pinNum < totalPins; pinNum++) {
				
			}
				
		} catch (ParserConfigurationException e) {
			Features.verbosePrint("Error creating XML file...");
			e.printStackTrace();
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
