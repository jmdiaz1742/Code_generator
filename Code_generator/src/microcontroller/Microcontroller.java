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
	
	/* Microcontroller characteristics */
	
	private String	uc_model;
	private String	uc_manufacturer;
	private int		uc_pinNum;
	
	private static final String STR_ATT_MODEL	= "model";
	private static final String STR_ATT_MFCT	= "manufacturer";
	private static final String STR_PIN			= "pin";
	
	/* Pin's mandatory characteristics */
	
	private static final String STR_PIN_NAME	= "name";
	private static final String STR_PIN_NUMBER	= "number";
	
	/* Pin's optional characteristics */
	private static final String STR_PIN_INT		= "interruption";
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
	}
	
	/**
	 * Process the document obtained from XML file
	 * @return Error status
	 */
	public ErrorCode processDocument() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		
		/* Get the root microcontroller element */
		Element pinRoot = ucDoc.getDocumentElement();
		if (Features.VERBOSE) {
			System.out.println("# Root element: " + pinRoot.getTagName());
		}
		
		if (!pinRoot.getTagName().equals("microcontroller")) {
			if (Features.VERBOSE) {
				System.out.println("# Wrong root element!...");
			}
			return ErrorCode.EX_ERROR;
		}
		
		if (loadMandatoryElements() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}
		
		if (loadPinNum() != ErrorCode.NO_ERROR) {
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
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "Manufacturer: " + element.item(0).getTextContent());
			}
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "No manufacturer found...");
			}
		}
		
		/* Get model */
		element = ucDoc.getElementsByTagName(STR_ATT_MODEL);
		if (element.getLength() > 0) {
			setUc_model(element.item(0).getTextContent());
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "Model: " + element.item(0).getTextContent());
			}
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "No model found...");
			}
		}
		
		return errorStatus;
	}
	
	/**
	 * Load the microcontroller's number of pins
	 * @return Error status
	 */
	private ErrorCode loadPinNum() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList pinList;
		
		pinList = ucDoc.getElementsByTagName(STR_PIN);
		if (pinList.getLength() > 0) {
			setUc_pinNum(pinList.getLength());
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "Number of pins: " + pinList.getLength());
			}
		} else {
			errorStatus = ErrorCode.EX_ERROR;
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "No pins found...");
			}
		}
		
		return errorStatus;
	}
	
	/**
	 * Get a pin's characteristics
	 * @param pinNum Number of pin
	 * @return Pin's characteristics
	 */
	public Pin getPin(int pinNum) {
		Pin pin = new Pin();
		Element pinEl;
		NodeList optChar;
		
		if (Features.VERBOSE) {
			System.out.println(Features.VERBOSE_STR + "Getting pin " + pinNum + " characteristics...");
		}
		
		pinEl = (Element)ucDoc.getElementsByTagName(STR_PIN).item(pinNum);
		
		/* Get mandatory characteristics as attributes */
		pin.setName(pinEl.getAttribute(STR_PIN_NAME));
		if (Features.VERBOSE) {
			System.out.println(Features.VERBOSE_STR + "\tName: " + pin.getName());
		}
		pin.setNumber(Integer.parseInt(pinEl.getAttribute(STR_PIN_NUMBER)));
		if (Features.VERBOSE) {
			System.out.println(Features.VERBOSE_STR + "\tNumber: " + pin.getNumber());
		}
		
		/* Get Pin functions */
		/* VCC */
		optChar = pinEl.getElementsByTagName(STR_PIN_VCC);
		if (optChar.getLength() > 0) {
			pin.setFunc_vcc(true);
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "\tFunction: VCC");
			}
		}
		/* GND */
		optChar = pinEl.getElementsByTagName(STR_PIN_GND);
		if (optChar.getLength() > 0) {
			pin.setFunc_gnd(true);
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "\tFunction: GND");
			}
		}
		/* GPIO */
		optChar = pinEl.getElementsByTagName(STR_PIN_GPIO);
		if (optChar.getLength() > 0) {
			pin.setFunc_gpio(true);
			if (Features.VERBOSE) {
				System.out.println(Features.VERBOSE_STR + "\tFunction: GPIO");
			}
		}
		
		/* Get optional GPIO characteristics */
		if (pin.getFunc_gpio()) {
			/* Port */
			optChar = pinEl.getElementsByTagName(STR_PIN_PORT);
			if (optChar.getLength() > 0) {
				pin.setPort(optChar.item(0).getTextContent());
				if (Features.VERBOSE) {
					System.out.println(Features.VERBOSE_STR + "\tPort: " + pin.getPort());
				}
			}
			/* Interruption */
			optChar = pinEl.getElementsByTagName(STR_PIN_INT);
			if (optChar.getLength() > 0) {
				pin.setFeat_int(true);
				pin.setInt(optChar.item(0).getTextContent());
				if (Features.VERBOSE) {
					System.out.println(Features.VERBOSE_STR + "\tInterruption: " + pin.getInt());
				}
			}
			/* ADC */
			optChar = pinEl.getElementsByTagName(STR_PIN_ADC);
			if (optChar.getLength() > 0) {
				pin.setFeat_adc(true);
				pin.setAdc(optChar.item(0).getTextContent());
				if (Features.VERBOSE) {
					System.out.println(Features.VERBOSE_STR + "\tADC: " + pin.getAdc());
				}
			}
			/* Clock */
			optChar = pinEl.getElementsByTagName(STR_PIN_CLOCK);
			if (optChar.getLength() > 0) {
				pin.setFeat_clock(true);
				pin.setClock(optChar.item(0).getTextContent());
				if (Features.VERBOSE) {
					System.out.println(Features.VERBOSE_STR + "\tClock: " + pin.getClock());
				}
			}
			/* Timer */
			optChar = pinEl.getElementsByTagName(STR_PIN_TIMER);
			if (optChar.getLength() > 0) {
				pin.setFeat_timer(true);
				pin.setTimer(optChar.item(0).getTextContent());
				if (Features.VERBOSE) {
					System.out.println(Features.VERBOSE_STR + "\tTimer: " + pin.getTimer());
				}
			}
			/* Reset */
			optChar = pinEl.getElementsByTagName(STR_PIN_RESET);
			if (optChar.getLength() > 0) {
				pin.setFeat_reset(true);
				pin.setReset(optChar.item(0).getTextContent());
				if (Features.VERBOSE) {
					System.out.println(Features.VERBOSE_STR + "\tReset: " + pin.getReset());
				}
			}
		}
		return pin;
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
}
