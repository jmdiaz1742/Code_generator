package projectConfiguration;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import common.ErrorCode;
import common.Features;
import xmlParser.XmlOpener;

/**
 * Project settings class
 * 
 * @author Miguel Diaz
 * @version 0.2
 *
 */
public class ProjectSettings {

	/* Private fields */
	private Document SettingsDoc;
	private String ProjectName;
	private File ProjectFile;
	private String ProjectPath;
	private String FrameworkPath;

	private File ConfFile;
	private File UcFile;

	/* Static fields */
	private static final String STR_SETTINGS_FOLDER = "settings";
	private static final String STR_SRC_FOLDER = "Src";

	/* XML elements names */
	private static final String STR_ROOT_ELEMENT = "CodeGeneratorProject";
	private static final String STR_PROJECT_NAME = "name";
	private static final String STR_CONFIG_FILE = "ConfigFile";
	private static final String STR_UC_FILE = "ucFile";
	private static final String STR_FWK_FOLDER = "frameworkFolder";

	/**
	 * Constructor
	 */
	public ProjectSettings() {

	}

	/**
	 * Process the document obtained from the XML file
	 * 
	 * @return Error Status
	 */
	public ErrorCode processDocument() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;

		/* Get the root element */
		Element settingsRoot = SettingsDoc.getDocumentElement();
		if (!settingsRoot.getTagName().equals(STR_ROOT_ELEMENT)) {
			Features.verbosePrint("Wrong configuration file format!...");
			return ErrorCode.FILE_READ_ERROR;
		}

		errorStatus = loadProjectInfo();

		return errorStatus;
	}

	/**
	 * Load project information from file
	 * 
	 * @return Error status
	 */
	private ErrorCode loadProjectInfo() {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		String confFileName;
		String ucFileName;
		String fwkFolderName;

		ProjectPath = ProjectFile.getAbsolutePath();
		ProjectPath = ProjectPath.substring(0, ProjectPath.lastIndexOf(System.getProperty("file.separator")));

		/* Get project name */
		ProjectName = XmlOpener.getElementInfoFromDoc(SettingsDoc, STR_PROJECT_NAME);
		if (!ProjectName.equals(ErrorCode.STR_INVALID)) {
			Features.verbosePrint("Project Name: " + ProjectName);
			ConfFile = new File(ProjectPath + System.getProperty("file.separator") + ProjectName);
		} else {
			errorStatus = ErrorCode.FILE_READ_ERROR;
		}

		/* Get Configuration file */
		confFileName = XmlOpener.getElementInfoFromDoc(SettingsDoc, STR_CONFIG_FILE);
		if (!confFileName.equals(ErrorCode.STR_INVALID)) {
			Features.verbosePrint("Configuration file: " + confFileName);
			ConfFile = new File(ProjectPath + System.getProperty("file.separator") + STR_SETTINGS_FOLDER
					+ System.getProperty("file.separator") + confFileName);
		} else {
			errorStatus = ErrorCode.FILE_READ_ERROR;
		}

		/* Get microcontroller file */
		ucFileName = XmlOpener.getElementInfoFromDoc(SettingsDoc, STR_UC_FILE);
		if (!ucFileName.equals(ErrorCode.STR_INVALID)) {
			UcFile = new File(ProjectPath + System.getProperty("file.separator") + STR_SETTINGS_FOLDER
					+ System.getProperty("file.separator") + ucFileName);
			Features.verbosePrint("Microcontroller file: " + ucFileName);
		} else {
			errorStatus = ErrorCode.FILE_READ_ERROR;
		}

		/* Get framework folder */
		fwkFolderName = XmlOpener.getElementInfoFromDoc(SettingsDoc, STR_FWK_FOLDER);
		if (!fwkFolderName.equals(ErrorCode.STR_INVALID)) {
			FrameworkPath = ProjectPath + System.getProperty("file.separator") + STR_SRC_FOLDER
					+ System.getProperty("file.separator") + fwkFolderName;
			Features.verbosePrint("Framework folder: " + fwkFolderName);
		} else {
			errorStatus = ErrorCode.FILE_READ_ERROR;
		}

		return errorStatus;
	}

	/**
	 * Open the project settings file
	 * 
	 * @param inFile Project file
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

	/**
	 * Get the project configuration file
	 * 
	 * @return Project configuration file
	 */
	public File getConfFile() {
		return ConfFile;
	}

	/**
	 * Get the project microcontroller file
	 * 
	 * @return Project microcontroller file
	 */
	public File getUcFile() {
		return UcFile;
	}

	/**
	 * Get the project's name
	 * 
	 * @return Project's name
	 */
	public String getProjectName() {
		return ProjectName;
	}

	/**
	 * Get the framework folder
	 * 
	 * @return framework folder
	 */
	public String getFrameworkPath() {
		return FrameworkPath;
	}
	
	/**
	 * Set the framework folder
	 * @param path Framework folder
	 */
	public void setFrameworkPath(String path) {
		FrameworkPath = path;
	}
}
