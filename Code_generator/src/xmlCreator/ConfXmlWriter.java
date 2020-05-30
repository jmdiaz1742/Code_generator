package xmlCreator;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
import configurator.AdcConf;
import configurator.PinConf;
import configurator.GPIO.AltMode;
import configurator.GPIO.CodeName;
import configurator.GPIO.Mode;
import configurator.GPIO.OutLevel;
import configurator.GPIO.OutType;
import configurator.GPIO.Pull;
import configurator.GPIO.Selected;
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
	private Element[] AdcElement;
	private Microcontroller UCConf;

	private static final String STR_ROOT_EL = "Microcontroller_Configuration";
	private static final String STR_PIN_EL = "pin";
	private static final String STR_PIN_PORT = "port";
	private static final String STR_PIN_NAME = "name";

	/**
	 * Constructor
	 * 
	 * @param uC Microcontroller configuration
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

				AdcElement = new Element[UCConf.getUc_adcNum()];
//				configureAdcs();

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
	 * @param pin    Pin configuration
	 * @param pinNum Number of GPIO pin
	 */
	public void addPin(PinConf pin, int pinNum) {
		PinElement[pinNum] = XmlDoc.createElement(STR_PIN_EL);
		PinElement[pinNum].appendChild(XmlDoc.createTextNode(pin.getPinName()));
		RootElement.appendChild(PinElement[pinNum]);

		/* Write the pins configuration information */
		addPinChild(STR_PIN_NAME, pin.getPinName(), pinNum);
		addPinChild(STR_PIN_PORT, pin.getPort(), pinNum);
		addPinChild(Selected.STR_NAME, pin.getSelected().name(), pinNum);
		addPinChild(Mode.STR_NAME, pin.getMode().name(), pinNum);
		addPinChild(AltMode.STR_NAME, pin.getAltMode().name(), pinNum);
		addPinChild(OutType.STR_NAME, pin.getOutType().name(), pinNum);
		addPinChild(OutLevel.STR_NAME, pin.getOutLevel().name(), pinNum);
		addPinChild(Pull.STR_NAME, pin.getPull().name(), pinNum);
		addPinChild(Speed.STR_NAME, pin.getSpeed().name(), pinNum);
		addPinChild(CodeName.STR_NAME, pin.getCodeName(), pinNum);
	}

	/**
	 * Add a configuration child element to the pin
	 * 
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
	 * Configure ADCs
	 */
	private void configureAdcs() {
		for (int adcNum = 0; adcNum < UCConf.getUc_adcNum(); adcNum++) {
			AdcConf adc = UCConf.AdcCfg[adcNum];

			AdcElement[adcNum] = XmlDoc.createElement(AdcConf.STR_NAME);
			AdcElement[adcNum].appendChild(XmlDoc.createTextNode(adc.AdcFeatures.getName()));
			RootElement.appendChild(AdcElement[adcNum]);

			/* Write the pins configuration information */
			addAdcChild(AdcConf.STR_CODE_NAME, adc.getCodeName(), adcNum);
			addAdcChild(AdcConf.STR_SAMPLE, adc.getSample(), adcNum);
			addAdcChild(AdcConf.STR_CLOCK, adc.getClock(), adcNum);
			addAdcChild(AdcConf.STR_JUSTIFICATION, adc.getJustification(), adcNum);
			addAdcChild(AdcConf.STR_PRESCALER, adc.getPrescaler(), adcNum);
			addAdcChild(AdcConf.STR_RESOLUTION, adc.getResolution(), adcNum);
			addAdcChild(AdcConf.STR_REFERENCE, adc.getReference(), adcNum);
		}
	}

	/**
	 * Add ADC configuration
	 * 
	 * @param elName ADC's feature name
	 * @param elInfo ADC's feature configuration
	 * @param adcNum ADC's number
	 */
	private void addAdcChild(String elName, String elInfo, int adcNum) {
		Element childEl = XmlDoc.createElement(elName);
		childEl.appendChild(XmlDoc.createTextNode(elInfo));
		PinElement[adcNum].appendChild(childEl);
	}

	/**
	 * Write the XMl file
	 * 
	 * @param fileName Name of XML configuration file
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

			xmlTrans.setOutputProperty(OutputKeys.INDENT, "yes");
			xmlTrans.setOutputProperty(OutputKeys.METHOD, "xml");
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
