package xmlParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

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
		
		File xmlFile;
		DocumentBuilderFactory xmlfactory;
		DocumentBuilder xmlBuilder;
		Document xmlDoc;
		
		NamedNodeMap attributesList;
		
		xmlFile = new File(fileName);
		xmlfactory = DocumentBuilderFactory.newInstance();
		/* Try opening the file */
		try {
			xmlBuilder = xmlfactory.newDocumentBuilder();
			xmlDoc = xmlBuilder.parse(xmlFile);
			if (Features.VERBOSE) {
				System.out.println("Opening file " + xmlFile.getName() + "...");
			}
		/* Exceptions */
		} catch (ParserConfigurationException e) {
			if (Features.VERBOSE) {
				System.out.println("Failed to parse " + xmlFile.getName());
				System.out.println("Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		} catch (SAXException e) {
			if (Features.VERBOSE) {
				System.out.println("Failed to parse " + xmlFile.getName());
				System.out.println("Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		} catch (IOException e) {
			if (Features.VERBOSE) {
				System.out.println("Failed to open " + xmlFile.getName());
				System.out.println("Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		}
		
		/* Get the root microcontroller element */
		Element xmlRoot = xmlDoc.getDocumentElement();
		if (Features.VERBOSE) {
			System.out.println("Root element: " + xmlRoot.getTagName());
		}
		
		if (!xmlRoot.getTagName().equals("microcontroller")) {
			if (Features.VERBOSE) {
				System.out.println("Wrong root element " + xmlFile.getName());
			}
			return ErrorCode.EX_ERROR;
		}
		
		/* Get the attributes */
		attributesList = xmlRoot.getAttributes();
		
		// TODO: Get the attributes
		
		return ErrorCode.NO_ERROR;
	}
	
	/**
	 * Validate the XML file
	 * @param xmlFile XML file
	 * @param xmlDocument Document created from the XML file
	 * @return True if the XML file is valid
	 */
	private boolean validateXml(File xmlFile, Document xmlDocument) {
		// FIXME: Find a way to validate the document
		Schema schema = null;
		try {
		  String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		  SchemaFactory factory = SchemaFactory.newInstance(language);
		  schema = factory.newSchema(xmlFile);
		} catch (Exception e) {
			System.out.println("File " + xmlFile.getName() + " not valid");
			System.out.println("Exception: " + e);
		}
		Validator validator = schema.newValidator();
//		validator.validate(source);
		return false; //validator.validate(new DOMSource(xmlDocument));
	}
}
