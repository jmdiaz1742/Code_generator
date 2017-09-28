package projectConfiguration;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
	private File ProjectFile;
	
	/* Static fields */
	private static final String ROOT_ELEMENT	= "CodeGeneratorProject";
	private static final String STR_PROJECT_NAME = "name";
	
	/**
	 * Constructor
	 */
	public ProjectSettings() {

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
			Features.verbosePrint("Wrong configuration file format!...");
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
	
	/**
	 * Open the project settings file
	 * @return Error Status
	 */
	public ErrorCode openProjectFile(File inFile) {
		ProjectFile = inFile;
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		XmlOpener projectFileOpener = new XmlOpener();

		projectFileOpener.OpenFile(ProjectFile);
		SettingsDoc = projectFileOpener.getParsedDoc();
		errorStatus = processDocument();
		
		return errorStatus;
	}
}
