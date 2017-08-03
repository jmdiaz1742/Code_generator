package xmlParser;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import common.Features;

/**
 * 
 * @author H112943
 * @version 0.1
 *
 */
public class FileOpener {
	
	/* Private fields */
	private File xmlFile;
	private DocumentBuilderFactory xmlfactory;
	DocumentBuilder xmlbuilder;
	
	/**
	 * 
	 */
	public FileOpener() {
		
	}
	
	/* Methods */
	public void OpenFile(String fileName) {
		xmlFile = new File(fileName);
		xmlfactory = DocumentBuilderFactory.newInstance();
		try {
			xmlbuilder = xmlfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			if (Features.DEBUG) {
				System.out.println("Failed to open " + fileName);
			}
			e.printStackTrace();
		}
	}
}
