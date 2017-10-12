package microcontroller;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import common.ErrorCode;
import common.Features;
import configurator.PinConf;
import configurator.GPIO.Mode;
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
	private PinConf[] GpioCfgPin;
	
	/* Public fields */
	
	/**
	 * Ports name list
	 */
	public String[] Ports;
	
	/* Microcontroller characteristics */
	private String	Uc_model;
	private String	Uc_manufacturer;
	private int		Uc_pinNum;
	private int		Uc_gpioNum;
	private int		Uc_portNum;
	
	/* Pin's mandatory characteristics */
	private static final String STR_PIN_NAME	= "name";
	private static final String STR_PIN_NUMBER	= "number";
	
	/* Pin's optional characteristics */
	private static final String STR_PIN_INT		= "interrupt";
	private static final String STR_PIN_ADC		= "adc";
	private static final String STR_PIN_PORT	= "port";
	private static final String STR_PIN_CLOCK	= "clock";
	private static final String STR_PIN_TIMER	= "timer";
	private static final String STR_PIN_VCC		= "vcc";
	private static final String STR_PIN_GND		= "gnd";
	private static final String STR_PIN_GPIO	= "gpio";
	private static final String STR_PIN_RESET	= "reset";
	
	/* Strings to extract information from XML file */
	private static final String ROOT_ELEMENT	= "microcontroller";
	private static final String STR_ATT_MODEL	= "model";
	private static final String STR_ATT_MFCT	= "manufacturer";
	private static final String STR_PIN			= "pin";
	private static final String CFG_ROOT_ELEMENT	= "Microcontroller_Configuration";
	
	/**
	 * Constructor
	 * @param ucDoc Document obtained from XML file
	 */
	public Microcontroller (Document ucDoc) {
		this.UcDoc = ucDoc;
		setUc_pinNum(0);
		setUc_gpioNum(0);
	}
	
	/**
	 * Process the document obtained from XML file
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
		
		if (loadPins() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}
		
		calculatePortNum();
		
		return errorStatus;
	}
	
	/**
	 * Load mandatory microcontroller elements Manufacturer and Model
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
			Features.verbosePrint("Number of pins: " + pinList.getLength());
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
			
			/* We should have at least 1 Vcc, 1 Gnd, 1 GPIO and 1 Reset */
			if ((errorStatus == ErrorCode.NO_ERROR) && ((pinsVcc <= 0) || (pinsGnd <= 0) || (pinsGpio <= 0) || (pinsRst <= 0))) {
				errorStatus = ErrorCode.EX_ERROR;
				Features.verbosePrint("The microcontroller does NOT have all the required pins!");
			}
		}
		
		return errorStatus;
	}
	
	/**
	 * Load pins from XML
	 * @param pinNum Pin's number
	 * @return Pin's information
	 */
	private Pin parsePin(int pinNum) {
		CurrentPin[pinNum] = new Pin();
		Element pinEl;
		String name;
		String pinsNum;
		String vcc;
		String gnd;
		String gpio;
		Features.verbosePrint("Getting pin " + pinNum + " characteristics:");
		
		pinEl = (Element)UcDoc.getElementsByTagName(STR_PIN).item(pinNum);
		
		/* Get mandatory characteristics */
		/* Name */
		
		name = XmlOpener.getElementInfo(pinEl, STR_PIN_NAME);
		if (!name.equals(ErrorCode.STR_INVALID)) {
			CurrentPin[pinNum].setName(name);
			Features.verbosePrint("\tName: " + name);
		}
		
		/* Number */
		pinsNum = XmlOpener.getElementInfo(pinEl, STR_PIN_NUMBER);
		if (!pinsNum.equals(ErrorCode.STR_INVALID)) {
			CurrentPin[pinNum].setNumber(Integer.parseInt(pinsNum));
			Features.verbosePrint("\tNumber: " + pinsNum);
		}
		
		/* Get Pin functions */
		/* VCC */
		vcc = XmlOpener.getElementInfo(pinEl, STR_PIN_VCC);
		if (!vcc.equals(ErrorCode.STR_INVALID)) {
			CurrentPin[pinNum].setFunc_vcc(true);
			Features.verbosePrint("\tFunction: VCC");
		}
		/* GND */
		gnd = XmlOpener.getElementInfo(pinEl, STR_PIN_GND);
		if (!gnd.equals(ErrorCode.STR_INVALID)) {
			CurrentPin[pinNum].setFunc_gnd(true);
			Features.verbosePrint("\tFunction: GND");
		}
		/* GPIO */
		gpio = XmlOpener.getElementInfo(pinEl, STR_PIN_GPIO);
		if (!gpio.equals(ErrorCode.STR_INVALID)) {
			CurrentPin[pinNum].setFunc_gpio(true);
			Features.verbosePrint("\tFunction: GPIO");
		}
		
		/* Get optional GPIO characteristics */
		if (CurrentPin[pinNum].getFunc_gpio()) {
			String port;
			String interruption;
			String adc;
			String clock;
			String timer;
			String reset;
			
			/* Port */
			port = XmlOpener.getElementInfo(pinEl, STR_PIN_PORT);
			if (!port.equals(ErrorCode.STR_INVALID)) {
				CurrentPin[pinNum].setPort(port);
				Features.verbosePrint("\tPort: " + CurrentPin[pinNum].getPort());
			}
			/* Interruption */
			interruption = XmlOpener.getElementInfo(pinEl, STR_PIN_INT);
			if (!interruption.equals(ErrorCode.STR_INVALID)) {
				CurrentPin[pinNum].setInt(interruption);
				Features.verbosePrint("\tInterruption: " + CurrentPin[pinNum].getInt());
			}
			/* ADC */
			adc = XmlOpener.getElementInfo(pinEl, STR_PIN_ADC);
			if (!adc.equals(ErrorCode.STR_INVALID)) {
				CurrentPin[pinNum].setAdc(adc);
				Features.verbosePrint("\tADC: " + CurrentPin[pinNum].getAdc());
			}
			/* Clock */
			clock = XmlOpener.getElementInfo(pinEl, STR_PIN_CLOCK);
			if (!clock.equals(ErrorCode.STR_INVALID)) {
				CurrentPin[pinNum].setClock(clock);
				Features.verbosePrint("\tClock: " + CurrentPin[pinNum].getClock());
			}
			/* Timer */
			timer = XmlOpener.getElementInfo(pinEl, STR_PIN_TIMER);
			if (!timer.equals(ErrorCode.STR_INVALID)) {
				CurrentPin[pinNum].setTimer(timer);
				Features.verbosePrint("\tTimer: " + CurrentPin[pinNum].getTimer());
			}
			/* Reset */
			reset = XmlOpener.getElementInfo(pinEl, STR_PIN_RESET);
			if (!reset.equals(ErrorCode.STR_INVALID)) {
				CurrentPin[pinNum].setReset(reset);
				Features.verbosePrint("\tReset: " + CurrentPin[pinNum].getReset());
			}
		}
		return CurrentPin[pinNum];
	}
	
	public ErrorCode loadPinsConf(Document confDoc) {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList pinList;
		int gpioNum = 0;
		
		/* Load all GPIO pins without configuration */
		GpioCfgPin = new PinConf[getUc_gpioNum()];
		for (int pinNum = 0; pinNum < getUc_pinNum(); pinNum++) {
			if (CurrentPin[pinNum].getFunc_gpio()) {
				GpioCfgPin[gpioNum] = new PinConf(CurrentPin[pinNum]);
				gpioNum++;
			}
		}
		
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
			setUc_pinNum(pinList.getLength());
			Features.verbosePrint("Number of configured pins: " + pinList.getLength());
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			Features.verbosePrint("No pins configurations found...");
		}
		
		for (int pinNum = 0; pinNum < pinList.getLength(); pinNum++) {
			String name;
			String configuration;
			Element pinEl;
			int gpioIndex = 0;
			
			pinEl = (Element)confDoc.getElementsByTagName(STR_PIN).item(pinNum);
			
			/* Set the pin's configurations if available */ 
			
			name = XmlOpener.getElementInfo(pinEl, STR_PIN_NAME);
			if (!name.equals(ErrorCode.STR_INVALID)) {	
				gpioIndex = getGpioIndexFromPinName(name);
			}
			
			configuration = XmlOpener.getElementInfo(pinEl, Mode.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[gpioIndex].setMode(Mode.getConfFromString(configuration));
				Features.verbosePrint("Found " + name + "'s Mode: " + configuration);
			}
			
			configuration = XmlOpener.getElementInfo(pinEl, OutType.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[gpioIndex].setOutType(OutType.getConfFromString(configuration));
			}
			
			configuration = XmlOpener.getElementInfo(pinEl, Pull.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[gpioIndex].setPull(Pull.getConfFromString(configuration));
			}
			
			configuration = XmlOpener.getElementInfo(pinEl, Speed.STR_NAME);
			if (!configuration.equals(ErrorCode.STR_INVALID)) {
				GpioCfgPin[gpioIndex].setSpeed(Speed.getConfFromString(configuration));
			}
		}
		
		
		
		return errorStatus;
	}
	
	private int getGpioIndexFromPinName(String name) {
		int gpioIndex;
		
		for (gpioIndex = 0; gpioIndex < getUc_pinNum(); gpioIndex++) {
			if (name == CurrentPin[gpioIndex].getName()) {
				break;
			}
		}
		
		return gpioIndex;
	}
	
	/**
	 * Get a pin's characteristics
	 * @param pinNum Number of pin
	 * @return Pin's characteristics
	 */
	public Pin getPin(int pinNum) {
		return CurrentPin[pinNum];
	}
	
	/**
	 * Get the microcontroller's model
	 * @return Microcontroller's model
	 */
	public String getUc_model() {
		return Uc_model;
	}

	/**
	 * Set the microcontroller's model
	 * @param uc_model Microcontroller's model
	 */
	private void setUc_model(String uc_model) {
		this.Uc_model = uc_model;
	}

	/**
	 * Get the microcontroller's manufacturer
	 * @return Microcontroller's manufacturer
	 */
	public String getUc_manufacturer() {
		return Uc_manufacturer;
	}

	/**
	 * Set the microcontroller's manufacturer
	 * @param uc_manufacturer microcontroller's manufacturer
	 */
	private void setUc_manufacturer(String uc_manufacturer) {
		this.Uc_manufacturer = uc_manufacturer;
	}

	/**
	 * Get the microcontroller's pins number
	 * @return Number of pins
	 */
	public int getUc_pinNum() {
		return Uc_pinNum;
	}

	/**
	 * Set the microcontroller's pins number
	 * @param uc_pinNum Number of pins
	 */
	private void setUc_pinNum(int uc_pinNum) {
		this.Uc_pinNum = uc_pinNum;
	}

	/**
	 * Get the number of GPIOs in the microcontroller
	 * @return Number of GPIOs
	 */
	public int getUc_gpioNum() {
		return Uc_gpioNum;
	}

	/**
	 * Set the number of GPIOs in the microcontroller
	 * @param uc_gpioNum Number of GPIOs
	 */
	private void setUc_gpioNum(int uc_gpioNum) {
		this.Uc_gpioNum = uc_gpioNum;
	}
	
	/**
	 * Get the number of ports in the microcontroller
	 * @return Number of ports
	 */
	public int getUc_portNum() {
		return Uc_portNum;
	}

	/**
	 * Set the number of ports in the microcontroller
	 * @param uc_portNum Number of ports
	 */
	private void setUc_portNum(int uc_portNum) {
		Uc_portNum = uc_portNum;
	}
	
	/**
	 * Get the configuration of a pin
	 * @param gpioName Name of the pin
	 * @return Pin configuration
	 */
	public PinConf getConfiguredPin(String gpioName) {
		return GpioCfgPin[getGpioPinIndexFromName(gpioName)];
	}
	
	/**
	 * Get the Gpio index of the gpio pins array
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
	 * @return true if valid
	 */
	public boolean isValid() {
		boolean validity = true;
		
		if (	(getUc_gpioNum() <= 0)	||
				(getUc_pinNum() <= 0)	||
				(getUc_model().equals(""))) {
			validity = false;
		}
		
		return validity;
	}
}
