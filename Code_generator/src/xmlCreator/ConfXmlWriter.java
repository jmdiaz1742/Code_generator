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
import configurator.Selected;
import configurator.UartConf;
import configurator.ADC.AdcChannel;
import configurator.GPIO.AltMode;
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
	private Element[] ConfElement;
	private Microcontroller UCConf;

	private static final String STR_ROOT_EL = "Microcontroller_Configuration";
	private static final String STR_PIN_EL = "pin";
	private static final String STR_PIN_PORT = "port";
	private static final String STR_PIN_NAME = "name";
	private static final String STR_ADC = "adcInstance";
	private static final String STR_UART = "uartInstance";

	/**
	 * Constructor
	 * 
	 * @param uC Microcontroller configuration
	 */
	public ConfXmlWriter(Microcontroller uC) {
		DocumentBuilderFactory xmlFactory;
		DocumentBuilder xmlBuilder;

		UCConf = uC;
		int totalElements = 0;
		/* GPIOs */
		totalElements = UCConf.getUc_gpioNum();
		/* ADCs */
		totalElements += UCConf.getUc_adcNum();
		for (int adcNum = 0; adcNum < UCConf.getUc_adcNum(); adcNum++) {
			totalElements += UCConf.AdcCfg[adcNum].getChannelsNum();
		}
		/* UARTs */
		totalElements += UCConf.getUc_uartNum();

		if (UCConf.getUc_gpioNum() > 0) {
			try {
				xmlFactory = DocumentBuilderFactory.newInstance();
				xmlBuilder = xmlFactory.newDocumentBuilder();

				/* Create root element */
				XmlDoc = xmlBuilder.newDocument();
				RootElement = XmlDoc.createElement(STR_ROOT_EL);
				XmlDoc.appendChild(RootElement);

				ConfElement = new Element[totalElements];
				configurePins();
				configureAdcs();
				configureUarts();

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
		ConfElement[pinNum] = XmlDoc.createElement(STR_PIN_EL);
		ConfElement[pinNum].appendChild(XmlDoc.createTextNode(pin.getPinName()));
		RootElement.appendChild(ConfElement[pinNum]);

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
		ConfElement[pinNum].appendChild(childEl);
	}

	/**
	 * Configure ADCs
	 */
	private void configureAdcs() {
		int adcOffset = UCConf.getUc_gpioNum();
		for (int adcNum = 0; adcNum < UCConf.getUc_adcNum(); adcNum++) {
			int elNum = adcOffset + adcNum;
			AdcConf adc = UCConf.AdcCfg[adcNum];

			ConfElement[elNum] = XmlDoc.createElement(STR_ADC);
			ConfElement[elNum].appendChild(XmlDoc.createTextNode(adc.AdcFeatures.getName()));
			RootElement.appendChild(ConfElement[elNum]);

			/* Write the ADCs configuration information */
			addAdcChild(AdcConf.STR_NAME, adc.AdcFeatures.getName(), elNum);
			addPinChild(Selected.STR_NAME, adc.getSelected().name(), elNum);
			addAdcChild(AdcConf.STR_CODE_NAME, adc.getCodeName(), elNum);
			addAdcChild(AdcConf.STR_SAMPLE, adc.getSample(), elNum);
			addAdcChild(AdcConf.STR_CLOCK, adc.getClock(), elNum);
			addAdcChild(AdcConf.STR_JUSTIFICATION, adc.getJustification(), elNum);
			addAdcChild(AdcConf.STR_PRESCALER, adc.getPrescaler(), elNum);
			addAdcChild(AdcConf.STR_RESOLUTION, adc.getResolution(), elNum);
			addAdcChild(AdcConf.STR_REFERENCE, adc.getReference(), elNum);
			addChannels(adc, elNum);
		}
	}

	private void addChannels(AdcConf adc, int offset) {

		for (int chanNum = 0; chanNum < adc.getChannelsNum(); chanNum++) {
			Element channelEl = XmlDoc.createElement(AdcConf.STR_CHANNEL);
			AdcChannel channel = adc.getChannel(chanNum);
			Element featureEl;

			featureEl = XmlDoc.createElement(AdcChannel.STR_NAME);
			featureEl.appendChild(XmlDoc.createTextNode(channel.getName()));
			channelEl.appendChild(featureEl);

			featureEl = XmlDoc.createElement(Selected.STR_NAME);
			featureEl.appendChild(XmlDoc.createTextNode(channel.getSelected().name()));
			channelEl.appendChild(featureEl);

			featureEl = XmlDoc.createElement(AdcChannel.STR_CODE_NAME);
			featureEl.appendChild(XmlDoc.createTextNode(channel.getCodeName()));
			channelEl.appendChild(featureEl);

			featureEl = XmlDoc.createElement(AdcChannel.STR_PIN_INDEX);
			featureEl.appendChild(XmlDoc.createTextNode(String.valueOf(channel.getPinIndex())));
			channelEl.appendChild(featureEl);

			ConfElement[offset].appendChild(channelEl);
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
		ConfElement[adcNum].appendChild(childEl);
	}

	/**
	 * Configure UARTs
	 */
	private void configureUarts() {
		int uartOffset = UCConf.getUc_gpioNum();
		uartOffset += UCConf.getUc_adcNum();
		for (int adcNum = 0; adcNum < UCConf.getUc_adcNum(); adcNum++) {
			uartOffset += UCConf.AdcCfg[adcNum].getChannelsNum();
		}
		for (int uartNum = 0; uartNum < UCConf.getUc_adcNum(); uartNum++) {
			int elNum = uartOffset + uartNum;
			UartConf uart = UCConf.UartCfg[uartNum];

			ConfElement[elNum] = XmlDoc.createElement(STR_UART);
			ConfElement[elNum].appendChild(XmlDoc.createTextNode(uart.UartFeatures.getName()));
			RootElement.appendChild(ConfElement[elNum]);

			/* Write the UARTs configuration information */
			addUartChild(UartConf.STR_NAME, uart.UartFeatures.getName(), elNum);
			addPinChild(Selected.STR_NAME, uart.getSelected().name(), elNum);
			addUartChild(UartConf.STR_CODE_NAME, uart.getCodeName(), elNum);
			addUartChild(UartConf.STR_CLOCK, uart.getClock(), elNum);
			addUartChild(UartConf.STR_PRESCALER, uart.getPrescaler(), elNum);
			addUartChild(UartConf.STR_BAUD_RATE, uart.getBaudRate(), elNum);
			addUartChild(UartConf.STR_DATA_BITS, uart.getDataBits(), elNum);
			addUartChild(UartConf.STR_STOP_BITS, uart.getStopBits(), elNum);
			addUartChild(UartConf.STR_PARITY, uart.getParity(), elNum);
		}
	}

	/**
	 * Add UART configuration
	 * 
	 * @param elName  UART's feature name
	 * @param elInfo  UART's feature configuration
	 * @param uartNum UART's number
	 */
	private void addUartChild(String elName, String elInfo, int uartNum) {
		Element childEl = XmlDoc.createElement(elName);
		childEl.appendChild(XmlDoc.createTextNode(elInfo));
		ConfElement[uartNum].appendChild(childEl);
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
