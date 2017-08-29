package microcontroller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import common.ErrorCode;
import common.Features;

/**
 * Microcontroller related methods
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Microcontroller {

	/* private fields */
	private Document ucDoc;
	private Pin[] pin;
	
	/* Microcontroller characteristics */
	
	private String	uc_model;
	private String	uc_manufacturer;
	private int		uc_pinNum;
	private int		uc_gpioNum;
	
	private static final String ROOT_ELEMENT	= "microcontroller";
	private static final String STR_ATT_MODEL	= "model";
	private static final String STR_ATT_MFCT	= "manufacturer";
	private static final String STR_PIN			= "pin";
	
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
	
	/**
	 * Constructor
	 * @param ucDoc Document obtained from XML file
	 */
	public Microcontroller (Document ucDoc) {
		this.ucDoc = ucDoc;
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
		Element pinRoot = ucDoc.getDocumentElement();
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
		return errorStatus;
	}
	
	/**
	 * Load mandatory microcontroller elements Manufacturer and Model
	 * @return Error status
	 */
	private ErrorCode loadMandatoryElements() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList element;
		
		/* Get manufacturer */
		element = ucDoc.getElementsByTagName(STR_ATT_MFCT);
		if (element.getLength() > 0) {
			setUc_manufacturer(element.item(0).getTextContent());
			Features.verbosePrint("Manufacturer: " + element.item(0).getTextContent());
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			Features.verbosePrint("No manufacturer found...");
		}
		
		/* Get model */
		element = ucDoc.getElementsByTagName(STR_ATT_MODEL);
		if (element.getLength() > 0) {
			setUc_model(element.item(0).getTextContent());
			Features.verbosePrint("Model: " + element.item(0).getTextContent());
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			Features.verbosePrint("No model found...");
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
		
		pinList = ucDoc.getElementsByTagName(STR_PIN);
		if (pinList.getLength() > 0) {
			setUc_pinNum(pinList.getLength());
			Features.verbosePrint("Number of pins: " + pinList.getLength());
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			Features.verbosePrint("No pins found...");
		}
		
		if (errorStatus == ErrorCode.NO_ERROR) {
			
			pin = new Pin[getUc_pinNum()];
			
			/* Parse the pins */
			for (int pinNum = 0; pinNum < getUc_pinNum(); pinNum++) {
				pin[pinNum] = parsePin(pinNum);
				/* Check the pin is valid */
				if (!pin[pinNum].isValid()) {
					errorStatus = ErrorCode.EX_ERROR;
					Features.verbosePrint("Pin " + pinNum + " not valid!");
					break;
				}
				
				/* Get pins functions */
				if (pin[pinNum].getFunc_vcc()) {
					pinsVcc++;
				} else if (pin[pinNum].getFunc_gnd()) {
					pinsGnd++;
				} else if (pin[pinNum].getFunc_gpio()) {
					pinsGpio++;
				}
				if (pin[pinNum].getFeat_reset()) {
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
		pin[pinNum] = new Pin();
		Element pinEl;
		NodeList mandChar;
		NodeList optChar;
		
		Features.verbosePrint("Getting pin " + pinNum + " characteristics:");
		
		pinEl = (Element)ucDoc.getElementsByTagName(STR_PIN).item(pinNum);
		
		/* Get mandatory characteristics */
		/* Name */
		mandChar = pinEl.getElementsByTagName(STR_PIN_NAME);
		if (mandChar.getLength() > 0) {
			pin[pinNum].setName(mandChar.item(0).getTextContent());
			Features.verbosePrint("\tName: " + pin[pinNum].getName());
		}
		/* Number */
		mandChar = pinEl.getElementsByTagName(STR_PIN_NUMBER);
		if (mandChar.getLength() > 0) {
			pin[pinNum].setNumber(Integer.parseInt(mandChar.item(0).getTextContent()));
			Features.verbosePrint("\tNumber: " + pin[pinNum].getNumber());
		}
		
		/* Get Pin functions */
		/* VCC */
		optChar = pinEl.getElementsByTagName(STR_PIN_VCC);
		if (optChar.getLength() > 0) {
			pin[pinNum].setFunc_vcc(true);
			Features.verbosePrint("\tFunction: VCC");
		}
		/* GND */
		optChar = pinEl.getElementsByTagName(STR_PIN_GND);
		if (optChar.getLength() > 0) {
			pin[pinNum].setFunc_gnd(true);
			Features.verbosePrint("\tFunction: GND");
		}
		/* GPIO */
		optChar = pinEl.getElementsByTagName(STR_PIN_GPIO);
		if (optChar.getLength() > 0) {
			pin[pinNum].setFunc_gpio(true);
			Features.verbosePrint("\tFunction: GPIO");
		}
		
		/* Get optional GPIO characteristics */
		if (pin[pinNum].getFunc_gpio()) {
			/* Port */
			optChar = pinEl.getElementsByTagName(STR_PIN_PORT);
			if (optChar.getLength() > 0) {
				pin[pinNum].setPort(optChar.item(0).getTextContent());
				Features.verbosePrint("\tPort: " + pin[pinNum].getPort());
			}
			/* Interruption */
			optChar = pinEl.getElementsByTagName(STR_PIN_INT);
			if (optChar.getLength() > 0) {
				pin[pinNum].setInt(optChar.item(0).getTextContent());
				Features.verbosePrint("\tInterruption: " + pin[pinNum].getInt());
			}
			/* ADC */
			optChar = pinEl.getElementsByTagName(STR_PIN_ADC);
			if (optChar.getLength() > 0) {
				pin[pinNum].setAdc(optChar.item(0).getTextContent());
				Features.verbosePrint("\tADC: " + pin[pinNum].getAdc());
			}
			/* Clock */
			optChar = pinEl.getElementsByTagName(STR_PIN_CLOCK);
			if (optChar.getLength() > 0) {
				pin[pinNum].setClock(optChar.item(0).getTextContent());
				Features.verbosePrint("\tClock: " + pin[pinNum].getClock());
			}
			/* Timer */
			optChar = pinEl.getElementsByTagName(STR_PIN_TIMER);
			if (optChar.getLength() > 0) {
				pin[pinNum].setTimer(optChar.item(0).getTextContent());
				Features.verbosePrint("\tTimer: " + pin[pinNum].getTimer());
			}
			/* Reset */
			optChar = pinEl.getElementsByTagName(STR_PIN_RESET);
			if (optChar.getLength() > 0) {
				pin[pinNum].setReset(optChar.item(0).getTextContent());
				Features.verbosePrint("\tReset: " + pin[pinNum].getReset());
			}
		}
		return pin[pinNum];
	}
	
	/**
	 * Get a pin's characteristics
	 * @param pinNum Number of pin
	 * @return Pin's characteristics
	 */
	public Pin getPin(int pinNum) {
		return pin[pinNum];
	}
	
	/**
	 * Get the microcontroller's model
	 * @return Microcontroller's model
	 */
	public String getUc_model() {
		return uc_model;
	}

	/**
	 * Set the microcontroller's model
	 * @param uc_model Microcontroller's model
	 */
	private void setUc_model(String uc_model) {
		this.uc_model = uc_model;
	}

	/**
	 * Get the microcontroller's manufacturer
	 * @return Microcontroller's manufacturer
	 */
	public String getUc_manufacturer() {
		return uc_manufacturer;
	}

	/**
	 * Set the microcontroller's manufacturer
	 * @param uc_manufacturer microcontroller's manufacturer
	 */
	private void setUc_manufacturer(String uc_manufacturer) {
		this.uc_manufacturer = uc_manufacturer;
	}

	/**
	 * Get the microcontroller's pins number
	 * @return Number of pins
	 */
	public int getUc_pinNum() {
		return uc_pinNum;
	}

	/**
	 * Set the microcontroller's pins number
	 * @param uc_pinNum Number of pins
	 */
	private void setUc_pinNum(int uc_pinNum) {
		this.uc_pinNum = uc_pinNum;
	}

	/**
	 * Get the number of GPIOs in the microcontroller
	 * @return Number of GPIOs
	 */
	public int getUc_gpioNum() {
		return uc_gpioNum;
	}

	/**
	 * Set the number of GPIOs in the microcontroller
	 * @param uc_gpioNum Number of GPIOs
	 */
	private void setUc_gpioNum(int uc_gpioNum) {
		this.uc_gpioNum = uc_gpioNum;
	}
}
