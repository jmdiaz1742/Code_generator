package xmlParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import common.Features;
import common.ErrorCode;

/**
 * 
 * @author H112943
 * @version 0.1
 *
 */
public class XmlOpener {
	
	/* Private fields */
	private File xmlFile;
	private DocumentBuilderFactory xmlfactory;
	private DocumentBuilder xmlBuilder;
	private Document xmlDoc;

	/* Microcontroller characteristics */
	
	private String	uc_model;
	private String	uc_manufacturer;
	private int		uc_pinNum;
	
	private static final String STR_ATT_MODEL	= "model";
	private static final String STR_ATT_MFCT	= "manufacturer";
	private static final String STR_PIN			= "pin";
	
	
	/* Public fields */
	
	/**
	 * Constructor
	 */
	public XmlOpener() {
		
	}
	
	/* Methods */
	/**
	 * Open the XML file
	 * @param fileName Complete path and name of XML file
	 * @return Error code
	 */
	public ErrorCode OpenFile(String fileName) {
		
		
		
		xmlFile = new File(fileName);
		xmlfactory = DocumentBuilderFactory.newInstance();
		/* Try opening the file */
		try {
			xmlBuilder = xmlfactory.newDocumentBuilder();
			xmlDoc = xmlBuilder.parse(xmlFile);
			if (Features.VERBOSE) {
				System.out.println("# Opening file " + xmlFile.getName() + "...");
			}
		/* Exceptions */
		} catch (ParserConfigurationException e) {
			if (Features.VERBOSE) {
				System.out.println("# Failed to parse " + xmlFile.getName());
				System.out.println("# Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		} catch (SAXException e) {
			if (Features.VERBOSE) {
				System.out.println("# Failed to parse " + xmlFile.getName());
				System.out.println("# Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		} catch (IOException e) {
			if (Features.VERBOSE) {
				System.out.println("# Failed to open " + xmlFile.getName());
				System.out.println("# Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		}
		
		/* Get the root microcontroller element */
		Element xmlRoot = xmlDoc.getDocumentElement();
		if (Features.VERBOSE) {
			System.out.println("# Root element: " + xmlRoot.getTagName());
		}
		
		if (!xmlRoot.getTagName().equals("microcontroller")) {
			if (Features.VERBOSE) {
				System.out.println("# Wrong root element " + xmlFile.getName());
			}
			return ErrorCode.EX_ERROR;
		}
		
		if (loadMandatoryElements() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}
		
		if (loadPinNum() != ErrorCode.NO_ERROR) {
			return ErrorCode.EX_ERROR;
		}
		
		return ErrorCode.NO_ERROR;
	}
	
//	/**
//	 * Validate the XML file
//	 * @param xmlFile XML file
//	 * @param xmlDocument Document created from the XML file
//	 * @return True if the XML file is valid
//	 */
//	private boolean validateXml(File xmlFile, Document xmlDocument) {
//		// FIXME: Find a way to validate the document
//		Schema schema = null;
//		try {
//		  String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
//		  SchemaFactory factory = SchemaFactory.newInstance(language);
//		  schema = factory.newSchema(xmlFile);
//		} catch (Exception e) {
//			System.out.println("File " + xmlFile.getName() + " not valid");
//			System.out.println("Exception: " + e);
//		}
//		Validator validator = schema.newValidator();
////		validator.validate(source);
//		return false; //validator.validate(new DOMSource(xmlDocument));
//	}
	
	/**
	 * Load mandatory microcontroller elements Manufacturer and Model
	 * @return Error status
	 */
	private ErrorCode loadMandatoryElements() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		NodeList element;
		
		/* Get manufacturer */
		element = xmlDoc.getElementsByTagName(STR_ATT_MFCT);
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
		element = xmlDoc.getElementsByTagName(STR_ATT_MODEL);
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
		
		pinList = xmlDoc.getElementsByTagName(STR_PIN);
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
