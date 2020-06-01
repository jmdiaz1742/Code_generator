package microcontroller;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import common.ErrorCode;
import common.Features;
import configurator.AdcConf;
import configurator.PinConf;
import configurator.Selected;
import configurator.ADC.AdcChannel;
import configurator.GPIO.CodeName;
import configurator.GPIO.Mode;
import configurator.GPIO.OutLevel;
import configurator.GPIO.OutType;
import configurator.GPIO.Pull;
import configurator.GPIO.Speed;
import xmlParser.XmlOpener;

/**
 * Microcontroller related methods
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Microcontroller {

	/* private fields */
	private Document UcDoc;
	private Pin[] CurrentPin;
	private Adc[] CurrentAdc;

	/* Public fields */

	/**
	 * Ports name list
	 */
	public String[] Ports;

	/**
	 * List of common includes that will be available for all framework
	 */
	public String[] Includes_Common;

	/**
	 * List of Includes for GPIO module
	 */
	public String[] Includes_Gpio;

	/**
	 * List of common definitions that will be available for all framework
	 */
	public String[] Definitions_Common;

	/**
	 * List of definitions for GPIO module
	 */
	public String[] Definitions_Gpio;

	/**
	 * Configured pins list
	 */
	public PinConf[] GpioCfgPin;

	/**
	 * List of ADCs
	 */
	public String[] Adcs;

	/**
	 * Configured ADCs list
	 */
	public AdcConf[] AdcCfg;

	/* Microcontroller characteristics */
	private String Uc_model;
	private String Uc_manufacturer;
	private int Uc_pinNum;
	private int Uc_gpioNum;
	private int Uc_portNum;
	private int Uc_adcNum;

	/* Pin's mandatory characteristics */
	private static final String STR_PIN_NAME = "name";
	private static final String STR_PIN_NUMBER = "number";
	private static final String STR_PORT_PIN = "portPin";

	/* Pin's optional characteristics */
	private static final String STR_PIN_INT = "interrupt";
	private static final String STR_PIN_ADC = "adc";
	private static final String STR_PIN_ADC_CHANNEL = "adcChannel";
	private static final String STR_PIN_UART = "uart";
	private static final String STR_PIN_I2C = "i2c";
	private static final String STR_PIN_SPI = "spi";
	private static final String STR_PIN_PORT = "port";
	private static final String STR_PIN_CLOCK = "clock";
	private static final String STR_PIN_TIMER = "timer";
	private static final String STR_PIN_VCC = "vcc";
	private static final String STR_PIN_GND = "gnd";
	private static final String STR_PIN_GPIO = "gpio";
	private static final String STR_PIN_RESET = "reset";
	private static final String STR_PIN_MISC = "misc";

	/* Strings to extract information from XML file */
	private static final String ROOT_ELEMENT = "microcontroller";
	private static final String STR_ATT_MODEL = "model";
	private static final String STR_ATT_MFCT = "manufacturer";
	private static final String STR_PIN = "pin";
	private static final String STR_ADC = "adcInstance";
	private static final String CFG_ROOT_ELEMENT = "Microcontroller_Configuration";

	/**
	 * Maximum number of pins allowed in a single port
	 */
	public static final int MAX_NUMBER_OF_PINS_PER_PORT = 32;

	/**
	 * Maximum number of ADCs allowed
	 */
	public static final int MAX_NUMBER_OF_ADCS = 16;

	/* Include files needed */
	private static final String STR_INCLUDE_COMMON = "include_common";
	private static final String STR_INCLUDE_GPIO = "include_gpio";
	private static final String STR_DEFINITION_COMMON = "cfg_def_common";
	private static final String STR_DEFINITION_GPIO = "cfg_def_gpio";

	/**
	 * Constructor
	 * 
	 * @param ucDoc Document obtained from XML file
	 */
	public Microcontroller(Document ucDoc) {
		this.UcDoc = ucDoc;
		setUc_pinNum(0);
		setUc_gpioNum(0);
	}

	/**
	 * Process the document obtained from XML file
	 * 
	 * @return Error status
	 */
	public ErrorCode processDocument() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;

		/* Get the root microcontroller element */
		Element pinRoot = UcDoc.getDocumentElement();
		Features.verbosePrint("Root element: " + pinRoot.getTagName());

		if (!pinRoot.getTagName().equals(ROOT_ELEMENT)) {
			Features.verbosePrint("Wrong root element!...");
			return ErrorCode.EX_ERROR;
		}

		if (loadMandatoryElements() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}

		if (loadAdcs() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}

		if (loadPins() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}

		/* ADC channels need to be set after scanning GPIO pins */
		for (int adcNum = 0; adcNum < Uc_adcNum; adcNum++) {
			AdcCfg[adcNum].setChannels(CurrentAdc[adcNum]);
		}

		if (loadIncludes() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}

		if (loadDefinitions() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}

		calculatePortNum();

		return errorStatus;
	}

	/**
	 * Load mandatory microcontroller elements Manufacturer and Model
	 * 
	 * @return Error status
	 */
	private ErrorCode loadMandatoryElements() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		String manufacturer = "";
		String model = "";

		/* Get manufacturer */
		manufacturer = XmlOpener.getElementInfoFromDoc(UcDoc, STR_ATT_MFCT);
		if (!manufacturer.equals(ErrorCode.STR_INVALID)) {
			Features.verbosePrint("Manufaturer: " + manufacturer);
			setUc_manufacturer(manufacturer);
		}

		/* Get model */
		model = XmlOpener.getElementInfoFromDoc(UcDoc, STR_ATT_MODEL);
		if (!model.equals(ErrorCode.STR_INVALID)) {
			Features.verbosePrint("Model: " + model);
			setUc_model(model);
		}

		return errorStatus;
	}

	/**
	 * Load the microcontroller's number of pins
	 * 
	 * @return Error status
	 */
	private ErrorCode loadPins() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList pinList;
		int pinsVcc = 0;
		int pinsGnd = 0;
		int pinsRst = 0;
		int pinsGpio = 0;

		pinList = UcDoc.getElementsByTagName(STR_PIN);
		if (pinList.getLength() > 0) {
			setUc_pinNum(pinList.getLength());
			Features.verbosePrint("Number of pins: " + getUc_pinNum());
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			Features.verbosePrint("No pins found...");
		}

		if (errorStatus == ErrorCode.NO_ERROR) {

			CurrentPin = new Pin[getUc_pinNum()];

			/* Parse the pins */
			for (int pinNum = 0; pinNum < getUc_pinNum(); pinNum++) {
				CurrentPin[pinNum] = parsePin(pinNum);
				/* Check the pin is valid */
				if (!CurrentPin[pinNum].isValid()) {
					errorStatus = ErrorCode.EX_ERROR;
					Features.verbosePrint("Pin " + pinNum + " not valid!");
					break;
				}

				/* Get pins functions */
				if (CurrentPin[pinNum].getFunc_vcc()) {
					pinsVcc++;
				} else if (CurrentPin[pinNum].getFunc_gnd()) {
					pinsGnd++;
				} else if (CurrentPin[pinNum].getFunc_gpio()) {
					pinsGpio++;
				}
				if (CurrentPin[pinNum].getFeat_reset()) {
					pinsRst++;
				}
			}
			setUc_gpioNum(pinsGpio);
			Features.verbosePrint("Found " + getUc_gpioNum() + " GPIOs...");

			int gpioNum = 0;

			/* Load all GPIO pins without configuration */
			GpioCfgPin = new PinConf[getUc_gpioNum()];
			for (int pinNum = 0; pinNum < getUc_pinNum(); pinNum++) {
				if (CurrentPin[pinNum].getFunc_gpio()) {
					GpioCfgPin[gpioNum] = new PinConf(CurrentPin[pinNum]);
					gpioNum++;
				}
			}

			/* We should have at least 1 Vcc, 1 Gnd, 1 GPIO and 1 Reset */
			if ((errorStatus == ErrorCode.NO_ERROR)
					&& ((pinsVcc <= 0) || (pinsGnd <= 0) || (pinsGpio <= 0) || (pinsRst <= 0))) {
				errorStatus = ErrorCode.EX_ERROR;
				Features.verbosePrint("The microcontroller does NOT have all the required pins!");
			}
		}

		return errorStatus;
	}

	/**
	 * Load pins from XML
	 * 
	 * @param pinNum Pin's number
	 * @return Pin's information
	 */
	private Pin parsePin(int pinNum) {
		Pin pin = new Pin();
		Element pinEl;
		String name;
		String pinsNum;
		String portPin;
		String vcc;
		String gnd;
		String gpio;
		String reset;
		String misc;
		Features.verbosePrint("Getting pin " + pinNum + " characteristics:");

		pinEl = (Element) UcDoc.getElementsByTagName(STR_PIN).item(pinNum);

		/* Get mandatory characteristics */
		/* Name */

		name = XmlOpener.getElementInfo(pinEl, STR_PIN_NAME);
		if (!name.equals(ErrorCode.STR_INVALID)) {
			pin.setName(name);
			Features.verbosePrint("\tName: " + name);
		}

		/* Number */
		pinsNum = XmlOpener.getElementInfo(pinEl, STR_PIN_NUMBER);
		if (!pinsNum.equals(ErrorCode.STR_INVALID)) {
			pin.setNumber(Integer.parseInt(pinsNum));
			Features.verbosePrint("\tNumber: " + pinsNum);
		}

		/* Port pin */
		portPin = XmlOpener.getElementInfo(pinEl, STR_PORT_PIN);
		if (!portPin.equals(ErrorCode.STR_INVALID)) {
			pin.setPortPin(portPin);
			Features.verbosePrint("\tPort Pin: " + portPin);
		}

		/* Get Pin functions */
		/* VCC */
		vcc = XmlOpener.getElementInfo(pinEl, STR_PIN_VCC);
		if (!vcc.equals(ErrorCode.STR_INVALID)) {
			pin.setFunc_vcc(true);
			Features.verbosePrint("\tFunction: VCC");
		}
		/* GND */
		gnd = XmlOpener.getElementInfo(pinEl, STR_PIN_GND);
		if (!gnd.equals(ErrorCode.STR_INVALID)) {
			pin.setFunc_gnd(true);
			Features.verbosePrint("\tFunction: GND");
		}
		/* GPIO */
		gpio = XmlOpener.getElementInfo(pinEl, STR_PIN_GPIO);
		if (!gpio.equals(ErrorCode.STR_INVALID)) {
			pin.setFunc_gpio(true);
			Features.verbosePrint("\tFunction: GPIO");
		}
		/* RESET */
		reset = XmlOpener.getElementInfo(pinEl, STR_PIN_RESET);
		if (!reset.equals(ErrorCode.STR_INVALID)) {
			pin.setFunc_reset(true);
			pin.setReset(reset);
			Features.verbosePrint("\tReset: " + pin.getReset());
		}
		/* MISC */
		misc = XmlOpener.getElementInfo(pinEl, STR_PIN_MISC);
		if (!misc.equals(ErrorCode.STR_INVALID)) {
			pin.setFunc_misc(true);
			Features.verbosePrint("\tFunction: MISC");
		}

		/* Get optional GPIO characteristics */
		if (pin.getFunc_gpio()) {
			String port;
			String interruption;
			String adc;
			int adcIndex;
			String adcChannel;
			String uart;
			String i2c;
			String spi;
			String clock;
			String timer;

			/* Port */
			port = XmlOpener.getElementInfo(pinEl, STR_PIN_PORT);
			if (!port.equals(ErrorCode.STR_INVALID)) {
				pin.setPort(port);
				Features.verbosePrint("\tPort: " + pin.getPort());
			}
			/* Interruption */
			interruption = XmlOpener.getElementInfo(pinEl, STR_PIN_INT);
			if (!interruption.equals(ErrorCode.STR_INVALID)) {
				pin.setInt(interruption);
				Features.verbosePrint("\tInterruption: " + pin.getInt());
			}
			/* ADC */
			adc = XmlOpener.getElementInfo(pinEl, STR_PIN_ADC);
			adcChannel = XmlOpener.getElementInfo(pinEl, STR_PIN_ADC_CHANNEL);
			if (!adc.equals(ErrorCode.STR_INVALID) && !adcChannel.equals(ErrorCode.STR_INVALID)) {
				pin.setAdc(adc, adcChannel);
				Features.verbosePrint("\tADC: " + pin.getAdc() + ", Channel: " + pin.getAdcChannel());

				/* Add channel to ADC */
				adcIndex = getAdcIndexFromName(pin.getAdc());
				if (adcIndex != ErrorCode.INT_INVALID_INDEX) {
					CurrentAdc[adcIndex].addChannel(new AdcChannel(pin.getAdcChannel(), pinNum));
				}
			}
			/* UART */
			uart = XmlOpener.getElementInfo(pinEl, STR_PIN_UART);
			if (!uart.equals(ErrorCode.STR_INVALID)) {
				pin.setUart(uart);
				Features.verbosePrint("\tUART: " + pin.getUart());
			}
			/* I2C */
			i2c = XmlOpener.getElementInfo(pinEl, STR_PIN_I2C);
			if (!i2c.equals(ErrorCode.STR_INVALID)) {
				pin.setI2c(i2c);
				Features.verbosePrint("\tI2C: " + pin.getI2c());
			}
			/* SPI */
			spi = XmlOpener.getElementInfo(pinEl, STR_PIN_SPI);
			if (!spi.equals(ErrorCode.STR_INVALID)) {
				pin.setSpi(spi);
				Features.verbosePrint("\tSPI: " + pin.getSpi());
			}
			/* Clock */
			clock = XmlOpener.getElementInfo(pinEl, STR_PIN_CLOCK);
			if (!clock.equals(ErrorCode.STR_INVALID)) {
				pin.setClock(clock);
				Features.verbosePrint("\tClock: " + pin.getClock());
			}
			/* Timer */
			timer = XmlOpener.getElementInfo(pinEl, STR_PIN_TIMER);
			if (!timer.equals(ErrorCode.STR_INVALID)) {
				pin.setTimer(timer);
				Features.verbosePrint("\tTimer: " + pin.getTimer());
			}
		}
		return pin;
	}

	/**
	 * Load pins' configuration
	 * 
	 * @param confDoc Document with pins
	 * @return Error Code
	 */
	public ErrorCode loadPinsConf(Document confDoc) {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList pinList;

		/* Get the root microcontroller element */
		Element pinRoot = confDoc.getDocumentElement();
		Features.verbosePrint("Configuration Root element: " + pinRoot.getTagName());

		if (!pinRoot.getTagName().equals(CFG_ROOT_ELEMENT)) {
			Features.verbosePrint("Wrong root element!...");
			return ErrorCode.FILE_READ_ERROR;
		}

		/* Get the pin's configuration */

		pinList = confDoc.getElementsByTagName(STR_PIN);
		if (pinList.getLength() > 0) {
			setUc_gpioNum(pinList.getLength());
			Features.verbosePrint("Number of configured pins: " + getUc_gpioNum());
		} else {
			Features.verbosePrint("No pins configurations found...");
			return ErrorCode.EX_ERROR;
		}

		for (int pinNum = 0; pinNum < pinList.getLength(); pinNum++) {
			String name;
			String configuration;
			Element pinEl;
			int gpioIndex = 0;

			pinEl = (Element) confDoc.getElementsByTagName(STR_PIN).item(pinNum);

			/* Set the pin's configurations if available */

			name = XmlOpener.getElementInfo(pinEl, STR_PIN_NAME);
			if (!name.equals(ErrorCode.STR_INVALID)) {
				gpioIndex = getGpioIndexFromPinName(name);
				if (gpioIndex >= getUc_pinNum()) {
					Features.verbosePrint("Pin " + name + " not found...");
					return ErrorCode.EX_ERROR;
				}
			}

			configuration = XmlOpener.getElementInfo(pinEl, Selected.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[pinNum].setSelected(Selected.getConfFromString(configuration));
				Features.verbosePrint("Found " + name + "'s Selection: " + configuration);
			} else {
				GpioCfgPin[pinNum].setSelected(PinConf.DF_SELECTED);
			}

			configuration = XmlOpener.getElementInfo(pinEl, Mode.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[pinNum].setMode(Mode.getConfFromString(configuration));
				Features.verbosePrint("Found " + name + "'s Mode: " + configuration);
			} else {
				GpioCfgPin[pinNum].setMode(PinConf.DF_MODE);
			}

			configuration = XmlOpener.getElementInfo(pinEl, OutType.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[pinNum].setOutType(OutType.getConfFromString(configuration));
				Features.verbosePrint("Found " + name + "'s Output type: " + configuration);
			} else {
				GpioCfgPin[pinNum].setOutType(PinConf.DF_OUTTYPE);
			}

			configuration = XmlOpener.getElementInfo(pinEl, OutLevel.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[pinNum].setOutLevel(OutLevel.getConfFromString(configuration));
				Features.verbosePrint("Found " + name + "'s Output Level: " + configuration);
			} else {
				GpioCfgPin[pinNum].setOutLevel(PinConf.DF_OUT_LEVEL);
			}

			configuration = XmlOpener.getElementInfo(pinEl, Pull.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[pinNum].setPull(Pull.getConfFromString(configuration));
				Features.verbosePrint("Found " + name + "'s Pull resistor: " + configuration);
			} else {
				GpioCfgPin[gpioIndex].setPull(PinConf.DF_PULL);
			}

			configuration = XmlOpener.getElementInfo(pinEl, Speed.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[pinNum].setSpeed(Speed.getConfFromString(configuration));
				Features.verbosePrint("Found " + name + "'s Speed: " + configuration);
			} else {
				GpioCfgPin[pinNum].setSpeed(PinConf.DF_SPEED);
			}

			configuration = XmlOpener.getElementInfo(pinEl, CodeName.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[pinNum].setCodeName(configuration);
				Features.verbosePrint("Found " + name + "'s Code name: " + configuration);
			} else {
				GpioCfgPin[pinNum].setCodeName(PinConf.DF_CODE_NAME);
			}
		}

		return errorStatus;
	}

	/**
	 * Load microcontroller's ADCs
	 * 
	 * @return ErrorStatus
	 */
	private ErrorCode loadAdcs() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList adcList;

		adcList = UcDoc.getElementsByTagName(STR_ADC);
		if (adcList.getLength() > 0) {
			setUc_adcNum(adcList.getLength());
			Adcs = new String[getUc_adcNum()];
			CurrentAdc = new Adc[getUc_adcNum()];
			AdcCfg = new AdcConf[getUc_adcNum()];
			Features.verbosePrint("Number of ADCs: " + getUc_adcNum());

			/* Parse the ADCs */
			for (int adcNum = 0; adcNum < getUc_adcNum(); adcNum++) {
				CurrentAdc[adcNum] = parseAdc(adcNum);
				/* Check the ADC is valid */
				if (!CurrentAdc[adcNum].isValid()) {
					errorStatus = ErrorCode.EX_ERROR;
					Features.verbosePrint("ADC " + adcNum + " not valid!");
					break;
				}
				Adcs[adcNum] = CurrentAdc[adcNum].getName();
			}

		} else {
			Features.verbosePrint("No ADCs found...");
		}

		return errorStatus;
	}

	/**
	 * Parse the ADC
	 * 
	 * @param adcNum ADC index
	 * @return ADC
	 */
	private Adc parseAdc(int adcNum) {
		Adc adc = new Adc();
		String name;
		Element adcEl;
		String feature;
		String featureStr;
		NodeList featureList;

		Features.verbosePrint("Getting ADC " + adcNum + " characteristics:");

		adcEl = (Element) UcDoc.getElementsByTagName(STR_ADC).item(adcNum);

		/* Name */

		name = XmlOpener.getElementInfo(adcEl, AdcConf.STR_NAME);
		if (!name.equals(ErrorCode.STR_INVALID)) {
			adc.setName(name);
			Features.verbosePrint("\tName: " + name);
		}

		/* Samples */
		feature = AdcConf.STR_SAMPLE;
		featureList = adcEl.getElementsByTagName(feature);
		for (int featNum = 0; featNum < featureList.getLength(); featNum++) {
			featureStr = featureList.item(featNum).getTextContent();

			if (!featureStr.equals(ErrorCode.STR_INVALID)) {
				adc.addSample(featureStr);
				Features.verbosePrint("\tSample: " + featureStr);
			}
		}

		/* Clocks */
		feature = AdcConf.STR_CLOCK;
		featureList = adcEl.getElementsByTagName(feature);
		for (int featNum = 0; featNum < featureList.getLength(); featNum++) {
			featureStr = featureList.item(featNum).getTextContent();

			if (!featureStr.equals(ErrorCode.STR_INVALID)) {
				adc.addClock(featureStr);
				Features.verbosePrint("\tClock: " + featureStr);
			}
		}

		/* Justifications */
		feature = AdcConf.STR_JUSTIFICATION;
		featureList = adcEl.getElementsByTagName(feature);
		for (int featNum = 0; featNum < featureList.getLength(); featNum++) {
			featureStr = featureList.item(featNum).getTextContent();

			if (!featureStr.equals(ErrorCode.STR_INVALID)) {
				adc.addJustification(featureStr);
				Features.verbosePrint("\tJustification: " + featureStr);
			}
		}

		/* Prescalers */
		feature = AdcConf.STR_PRESCALER;
		featureList = adcEl.getElementsByTagName(feature);
		for (int featNum = 0; featNum < featureList.getLength(); featNum++) {
			featureStr = featureList.item(featNum).getTextContent();

			if (!featureStr.equals(ErrorCode.STR_INVALID)) {
				adc.addPrescaler(featureStr);
				Features.verbosePrint("\tPrescaler: " + featureStr);
			}
		}

		/* Resolutions */
		feature = AdcConf.STR_RESOLUTION;
		featureList = adcEl.getElementsByTagName(feature);
		for (int featNum = 0; featNum < featureList.getLength(); featNum++) {
			featureStr = featureList.item(featNum).getTextContent();

			if (!featureStr.equals(ErrorCode.STR_INVALID)) {
				adc.addResolution(featureStr);
				Features.verbosePrint("\tResolution: " + featureStr);
			}
		}

		/* References */
		feature = AdcConf.STR_REFERENCE;
		featureList = adcEl.getElementsByTagName(feature);
		for (int featNum = 0; featNum < featureList.getLength(); featNum++) {
			featureStr = featureList.item(featNum).getTextContent();

			if (!featureStr.equals(ErrorCode.STR_INVALID)) {
				adc.addReference(featureStr);
				Features.verbosePrint("\tReference: " + featureStr);
			}
		}

		if (adc.isValid()) {
			AdcCfg[adcNum] = new AdcConf(adc);
		}

		return adc;
	}

	public ErrorCode loadAdcsConf(Document confDoc) {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList adcList;

		/* Get the root microcontroller element */
		Element adcRoot = confDoc.getDocumentElement();
		Features.verbosePrint("Configuration Root element: " + adcRoot.getTagName());

		if (!adcRoot.getTagName().equals(CFG_ROOT_ELEMENT)) {
			Features.verbosePrint("Wrong root element!...");
			return ErrorCode.FILE_READ_ERROR;
		}

		/* Get the ADC's configuration */

		adcList = confDoc.getElementsByTagName(STR_ADC);
		if (adcList.getLength() > 0) {
			Features.verbosePrint("Number of configured ADCs: " + adcList.getLength());
		} else {
			Features.verbosePrint("No ADCs configurations found...");
			return ErrorCode.EX_ERROR;
		}

		for (int adcNum = 0; adcNum < adcList.getLength(); adcNum++) {
			String name;
			String configuration;
			Element adcEl;
			int adcIndex = 0;

			adcEl = (Element) confDoc.getElementsByTagName(STR_ADC).item(adcNum);

			/* Set the ADC's configurations if available */

			name = XmlOpener.getElementInfo(adcEl, AdcConf.STR_NAME);
			if (!name.equals(ErrorCode.STR_INVALID)) {
				adcIndex = getAdcIndexFromName(name);
				if (adcIndex >= getUc_pinNum()) {
					Features.verbosePrint("ADC " + name + " not found...");
					return ErrorCode.EX_ERROR;
				}
			}

			configuration = XmlOpener.getElementInfo(adcEl, Selected.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				AdcCfg[adcNum].setSelected(Selected.getConfFromString(configuration));
				Features.verbosePrint("Found " + name + "'s selection: " + configuration);
			}
			
			configuration = XmlOpener.getElementInfo(adcEl, AdcConf.STR_CODE_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				AdcCfg[adcNum].setCodeName(configuration);
				Features.verbosePrint("Found " + name + "'s Code name: " + configuration);
			}

			configuration = XmlOpener.getElementInfo(adcEl, AdcConf.STR_SAMPLE);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				AdcCfg[adcNum].setSample(configuration);
				Features.verbosePrint("Found " + name + "'s Sample: " + configuration);
			}

			configuration = XmlOpener.getElementInfo(adcEl, AdcConf.STR_CLOCK);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				AdcCfg[adcNum].setClock(configuration);
				Features.verbosePrint("Found " + name + "'s Clock: " + configuration);
			}

			configuration = XmlOpener.getElementInfo(adcEl, AdcConf.STR_JUSTIFICATION);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				AdcCfg[adcNum].setJustification(configuration);
				Features.verbosePrint("Found " + name + "'s Justification: " + configuration);
			}

			configuration = XmlOpener.getElementInfo(adcEl, AdcConf.STR_PRESCALER);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				AdcCfg[adcNum].setPrescaler(configuration);
				Features.verbosePrint("Found " + name + "'s Prescaler: " + configuration);
			}

			configuration = XmlOpener.getElementInfo(adcEl, AdcConf.STR_RESOLUTION);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				AdcCfg[adcNum].setResolution(configuration);
				Features.verbosePrint("Found " + name + "'s Resolution: " + configuration);
			}

			configuration = XmlOpener.getElementInfo(adcEl, AdcConf.STR_REFERENCE);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				AdcCfg[adcNum].setReference(configuration);
				Features.verbosePrint("Found " + name + "'s Reference: " + configuration);
			}
		}
		return errorStatus;
	}

	/**
	 * Get the GPIO index from the Pin's name
	 * 
	 * @param name Pin's name
	 * @return GPIO pin index
	 */
	private int getGpioIndexFromPinName(String name) {
		int gpioIndex;

		for (gpioIndex = 0; gpioIndex < getUc_pinNum(); gpioIndex++) {
			if (name.equals(CurrentPin[gpioIndex].getName())) {
				break;
			}
		}

		return gpioIndex;
	}

	/**
	 * Get the ADC index from it's instance name
	 * 
	 * @param name ADC instance name
	 * @return ADC index
	 */
	private int getAdcIndexFromName(String name) {
		int index = ErrorCode.INT_INVALID_INDEX;
		for (int adcNum = 0; adcNum < CurrentAdc.length; adcNum++) {
			if (name.equals(CurrentAdc[adcNum].getName())) {
				index = adcNum;
				break;
			}
		}
		return index;
	}

	/**
	 * Get a pin's characteristics
	 * 
	 * @param pinNum Number of pin
	 * @return Pin's characteristics
	 */
	public Pin getPin(int pinNum) {
		return CurrentPin[pinNum];
	}

	/**
	 * Get the microcontroller's model
	 * 
	 * @return Microcontroller's model
	 */
	public String getUc_model() {
		return Uc_model;
	}

	/**
	 * Set the microcontroller's model
	 * 
	 * @param uc_model Microcontroller's model
	 */
	private void setUc_model(String uc_model) {
		this.Uc_model = uc_model;
	}

	/**
	 * Get the microcontroller's manufacturer
	 * 
	 * @return Microcontroller's manufacturer
	 */
	public String getUc_manufacturer() {
		return Uc_manufacturer;
	}

	/**
	 * Set the microcontroller's manufacturer
	 * 
	 * @param uc_manufacturer microcontroller's manufacturer
	 */
	private void setUc_manufacturer(String uc_manufacturer) {
		this.Uc_manufacturer = uc_manufacturer;
	}

	/**
	 * Get the microcontroller's pins number
	 * 
	 * @return Number of pins
	 */
	public int getUc_pinNum() {
		return Uc_pinNum;
	}

	/**
	 * Set the microcontroller's pins number
	 * 
	 * @param uc_pinNum Number of pins
	 */
	private void setUc_pinNum(int uc_pinNum) {
		this.Uc_pinNum = uc_pinNum;
	}

	/**
	 * Get the number of GPIOs in the microcontroller
	 * 
	 * @return Number of GPIOs
	 */
	public int getUc_gpioNum() {
		return Uc_gpioNum;
	}

	/**
	 * Set the number of GPIOs in the microcontroller
	 * 
	 * @param uc_gpioNum Number of GPIOs
	 */
	private void setUc_gpioNum(int uc_gpioNum) {
		this.Uc_gpioNum = uc_gpioNum;
	}

	/**
	 * Get the number of ports in the microcontroller
	 * 
	 * @return Number of ports
	 */
	public int getUc_portNum() {
		return Uc_portNum;
	}

	/**
	 * Set the number of ports in the microcontroller
	 * 
	 * @param uc_portNum Number of ports
	 */
	private void setUc_portNum(int uc_portNum) {
		Uc_portNum = uc_portNum;
	}

	/**
	 * Get the number of ADCs in the microcontroller
	 * 
	 * @return Number of ADCs
	 */
	public int getUc_adcNum() {
		return Uc_adcNum;
	}

	/**
	 * Set the number of ADCs in the microcontroller
	 * 
	 * @param uc_adcNum Number of ADCs
	 */
	private void setUc_adcNum(int uc_adcNum) {
		Uc_adcNum = uc_adcNum;
	}

	/**
	 * Get the total pins selected
	 * 
	 * @return Total of pins selected
	 */
	public int getUc_selectedPinsNum() {
		int selectedPins = 0;

		for (int pinNum = 0; pinNum < GpioCfgPin.length; pinNum++) {
			if (GpioCfgPin[pinNum].getSelected().getBoolean()) {
				selectedPins++;
			}
		}

		return selectedPins;
	}

	/**
	 * Get the configuration of a pin
	 * 
	 * @param gpioName Name of the pin
	 * @return Pin configuration
	 */
	public PinConf getConfiguredPin(String gpioName) {
		return GpioCfgPin[getGpioPinIndexFromName(gpioName)];
	}

	/**
	 * Get the Gpio index of the gpio pins array
	 * 
	 * @param gpioName Pin's name
	 * @return Pin's index in the GPIO array
	 */
	private int getGpioPinIndexFromName(String gpioName) {
		int gpioNum;

		for (gpioNum = 0; gpioNum < GpioCfgPin.length; gpioNum++) {
			if (GpioCfgPin[gpioNum].getPinName().equals(gpioName)) {
				break;
			}
		}

		return gpioNum;
	}

	/**
	 * Calculate the number of different ports in the microcontroller
	 */
	private void calculatePortNum() {
		ArrayList<String> diffPorts = new ArrayList<>();

		for (int portNum = 0; portNum < CurrentPin.length; portNum++) {
			String portName = CurrentPin[portNum].getPort();
			if ((!diffPorts.contains(portName)) && (!portName.equals(Pin.DEF_PORT))) {
				diffPorts.add(portName);
			}
		}

		if (diffPorts.size() == 1) {
			setUc_portNum(0);
		} else {
			setUc_portNum(diffPorts.size());
		}

		Ports = new String[getUc_portNum()];

		for (int portNum = 0; portNum < getUc_portNum(); portNum++) {
			Ports[portNum] = diffPorts.get(portNum);
		}
	}

	/**
	 * Check if the microcontroller configuration is valid
	 * 
	 * @return true if valid
	 */
	public boolean isValid() {
		boolean validity = true;

		if ((getUc_gpioNum() <= 0) || (getUc_pinNum() <= 0) || (getUc_model().equals(""))) {
			validity = false;
		}

		return validity;
	}

	private ErrorCode loadIncludes() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList includeList;
		String incName;

		Features.verbosePrint("Looking for Include files list...");

		/* Get Common includes */
		includeList = UcDoc.getElementsByTagName(STR_INCLUDE_COMMON);
		if (includeList.getLength() > 0) {
			Includes_Common = new String[includeList.getLength()];
			for (int incNum = 0; incNum < Includes_Common.length; incNum++) {
				incName = includeList.item(incNum).getTextContent();
				if (!incName.equals(ErrorCode.STR_INVALID)) {
					Includes_Common[incNum] = incName;
					Features.verbosePrint("\tInclude file added: " + incName);
				}
			}
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			Features.verbosePrint("No Common includes found...");
		}

		/* Get GPIO includes */
		includeList = UcDoc.getElementsByTagName(STR_INCLUDE_GPIO);
		if (includeList.getLength() > 0) {
			Includes_Gpio = new String[includeList.getLength()];

			for (int incNum = 0; incNum < Includes_Gpio.length; incNum++) {
				incName = includeList.item(incNum).getTextContent();
				if (!incName.equals(ErrorCode.STR_INVALID)) {
					Includes_Gpio[incNum] = incName;
					Features.verbosePrint("\tInclude file added: " + incName);
				}
			}
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			Features.verbosePrint("No GPIO includes found...");
		}

		return errorStatus;
	}

	private ErrorCode loadDefinitions() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList includeList;
		String defName;

		Features.verbosePrint("Looking for Common definitions...");

		/* Get Common definitions */
		includeList = UcDoc.getElementsByTagName(STR_DEFINITION_COMMON);
		if (includeList.getLength() > 0) {
			Definitions_Common = new String[includeList.getLength()];
			for (int incNum = 0; incNum < Definitions_Common.length; incNum++) {
				defName = includeList.item(incNum).getTextContent();
				if (!defName.equals(ErrorCode.STR_INVALID)) {
					Definitions_Common[incNum] = defName;
					Features.verbosePrint("\tDefinition found: " + defName);
				}
			}
		} else {
			Definitions_Common = new String[1];
			Definitions_Common[0] = ErrorCode.STR_INVALID;
			Features.verbosePrint("No Common definitions found...");
		}

		/* Get GPIO includes */
		includeList = UcDoc.getElementsByTagName(STR_DEFINITION_GPIO);
		if (includeList.getLength() > 0) {
			Definitions_Gpio = new String[includeList.getLength()];

			for (int incNum = 0; incNum < Definitions_Gpio.length; incNum++) {
				defName = includeList.item(incNum).getTextContent();
				if (!defName.equals(ErrorCode.STR_INVALID)) {
					Definitions_Gpio[incNum] = defName;
					Features.verbosePrint("\tDefinition found: " + defName);
				}
			}
		} else {
			Definitions_Gpio = new String[1];
			Definitions_Gpio[0] = ErrorCode.STR_INVALID;
			Features.verbosePrint("No GPIO definitions found...");
		}

		return errorStatus;
	}
}
