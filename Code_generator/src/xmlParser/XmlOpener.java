package xmlParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import common.Features;
import common.ErrorCode;

/**
 * Open and process XML files
 * @author H112943
 * @version 0.1
 *
 */
public class XmlOpener {
	
	/* Private fields */
	private File XmlFile;
	private DocumentBuilderFactory Xmlfactory;
	private DocumentBuilder XmlBuilder;
	private Document XmlDoc;
	
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
		XmlFile = new File(fileName);
		Xmlfactory = DocumentBuilderFactory.newInstance();
		/* Try opening the file */
		try {
			XmlBuilder = Xmlfactory.newDocumentBuilder();
			XmlDoc = XmlBuilder.parse(XmlFile);
			if (Features.VERBOSE) {
				System.out.println("# Opening file " + XmlFile.getName() + "...");
			}
		/* Exceptions */
		} catch (ParserConfigurationException e) {
			if (Features.VERBOSE) {
				System.out.println("# Failed to parse " + XmlFile.getName());
				System.out.println("# Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		} catch (SAXException e) {
			if (Features.VERBOSE) {
				System.out.println("# Failed to parse " + XmlFile.getName());
				System.out.println("# Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		} catch (IOException e) {
			if (Features.VERBOSE) {
				System.out.println("# Failed to open " + XmlFile.getName());
				System.out.println("# Exception: " + e);
			}
			return ErrorCode.EX_ERROR;
		}
		
		return ErrorCode.NO_ERROR;
	}
	
	/**
	 * Get the parsed document AFTER opening the file
	 * @return Parsed document
	 */
	public Document getParsedDoc() {
		return XmlDoc;
	}
	
	/**
	 * Get an XML element information
	 * @param doc Document from XML file
	 * @param elementName Element's name
	 * @return Element's information
	 */
	public static String getElementInfoFromDoc(Document doc, String elementName) {
		String info = ErrorCode.STR_INVALID;
		NodeList element;
		
		element = doc.getElementsByTagName(elementName);
		if (element.getLength() > 0) {
			info = element.item(0).getTextContent();
		}
		else {
			Features.verbosePrint("Error reading " + elementName);
		}
		
		return info;
	}
	
	/**
	 * Get an XML sub element information
	 * @param element XML main element
	 * @param elementName Sub element's name
	 * @return Sub elemen't information
	 */
	public static String getElementInfo(Element element, String elementName) {
		String info = ErrorCode.STR_INVALID;
		NodeList subElement;
		
		subElement = element.getElementsByTagName(elementName);
		if (subElement.getLength() > 0) {
			info = subElement.item(0).getTextContent();
		}
		else {
			Features.verbosePrint(elementName + " not found");
		}
		
		return info;
	}
}
