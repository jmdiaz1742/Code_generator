package gui;

import java.io.File;

import common.ErrorCode;
import projectConfiguration.ProjectSettings;

/**
 * Main GUI state machine
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class MainGui {

	/* Private fields */
	static MainWindow Window;
	static ProjectSettings ProjectSettingsConf = new ProjectSettings();
	
	/* Public fields */
	static File ProjectFile;
	
	/**
	 * Main function
	 * @param args TBD
	 */
	public static void main(String[] args) {
		// FIXME: Check how this would work, the idea is to call all the GUI
		// windows from here
		Window = new MainWindow();
	}
	
	/**
	 * Load the project settings file
	 * @param inFile Settings file
	 * @return Error status
	 */
	public static ErrorCode loadProjectFile(File inFile) {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		ProjectFile = inFile;
		errorStatus = ProjectSettingsConf.openProjectFile(ProjectFile);
		
		return errorStatus;
	}

}