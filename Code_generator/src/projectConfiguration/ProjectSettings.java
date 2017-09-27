package projectConfiguration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import common.ErrorCode;
import common.Features;
import xmlParser.XmlOpener;

/**
 * Project settings class
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class ProjectSettings {

	/* Private fields */
	private Document SettingsDoc;
	private String ProjectName;
	
	/* Static fields */
	private static final String ROOT_ELEMENT	= "CodeGeneratorProject";
	private static final String STR_PROJECT_NAME = "name";
	
	/**
	 * Constructor
	 * @param psDoc Document obtained rom XML file
	 */
	public ProjectSettings(Document settingsDoc) {
		this.SettingsDoc = settingsDoc;
	}
	
	/**
	 * Process the document obtained from the XML file
	 * @return Error Status
	 */
	public ErrorCode processDocument() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		
		/* Get the root element */
		Element settingsRoot = SettingsDoc.getDocumentElement();
		if (!settingsRoot.getTagName().equals(ROOT_ELEMENT)) {
			Features.verbosePrint("Wrong root element!...");
			return ErrorCode.FILE_READ_ERROR;
		}
		
		errorStatus = loadProjectInfo();
		
		return errorStatus;
	}
	
	/**
	 * Load project information from file
	 * @return Error status
	 */
	private ErrorCode loadProjectInfo() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		
		ProjectName = XmlOpener.getElementInfoFromDoc(SettingsDoc, STR_PROJECT_NAME);
		if (!ProjectName.equals(ErrorCode.STR_INVALID)) {
			Features.verbosePrint("Project Name: " + ProjectName);
		}
		
		return errorStatus;
	}
}
