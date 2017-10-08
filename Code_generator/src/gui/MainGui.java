package gui;

import java.io.File;

import javax.swing.JOptionPane;

import common.ErrorCode;
import common.Features;
import microcontroller.Microcontroller;
import projectConfiguration.ProjectSettings;
import xmlParser.XmlOpener;

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
	static Microcontroller SelectedMicrocontroller;
	
	/* Public fields */
	static File ProjectFile;
	
	/**
	 * Main function
	 * @param args TBD
	 */
	public static void main(String[] args) {
		// FIXME: Check how this would work, the idea is to call all the GUI
		// windows from here
		Features.verbosePrint("Starting GUI...");
		Window = new MainWindow();
		Window.setVisible(true);
	}
	
	/**
	 * Load the project settings file
	 * @param inFile Settings file
	 * @return Error status
	 */
	public static ErrorCode loadProjectFile(File inFile) {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		XmlOpener fileOpener = new XmlOpener();;
		
		ProjectFile = inFile;
		errorStatus = ProjectSettingsConf.openProjectFile(ProjectFile);
		if (errorStatus != ErrorCode.NO_ERROR) {
			showErrorDialog("Error opening configuration file");
			return errorStatus;
		}
		/* Send information to GUI */
		Window.setProjectName(ProjectSettingsConf.getProjectName());
		
		errorStatus  = ProjectSettingsConf.processDocument();
		if (errorStatus != ErrorCode.NO_ERROR) {
			showErrorDialog("Error prcessing configuration file");
			return errorStatus;
		}
		
		errorStatus = fileOpener.OpenFile(ProjectSettingsConf.getUcFile());
		if (errorStatus != ErrorCode.NO_ERROR) {
			showErrorDialog("Error opening microcontroller file");
			return errorStatus;
		}
		
		SelectedMicrocontroller = new Microcontroller(fileOpener.getParsedDoc());
		Window.setMicrocontroller(SelectedMicrocontroller.getUc_model());
		return errorStatus;
	}
	
	/**
	 * Show an error dialog
	 * @param message Message to display
	 */
	private static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(Window.FrmCodeGenerator,
				message,
			    "File error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showAboutWindow() {
		AboutWindow aboutWindow = new AboutWindow();
		aboutWindow.setVisible(true);
	}

}
