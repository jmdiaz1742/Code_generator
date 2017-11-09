package gui;

import java.io.File;

import javax.swing.JOptionPane;

import common.ErrorCode;
import common.Features;
import microcontroller.Microcontroller;
import projectConfiguration.ProjectSettings;
import xmlCreator.ConfXmlWriter;
import xmlParser.XmlOpener;

/**
 * Main GUI state machine
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class MainGui {

	/* Private fields */
	static MainWindow CgMainWindow;
	static GpioConfWindow CgGpioConfWindow;
	static ProjectSettings ProjectSettingsConf = new ProjectSettings();
	static Microcontroller SelectedMicrocontroller;
	
	/* Public fields */
	static File ProjectFile;
	static String ProjectPath;
	
	/**
	 * Main function
	 * @param args TBD
	 */
	public static void main(String[] args) {
		// FIXME: Check how this would work, the idea is to call all the GUI
		// windows from here
		Features.verbosePrint("Starting GUI...");
		CgMainWindow = new MainWindow();
		CgMainWindow.setVisible(true);
	}
	
	/**
	 * Load the project settings file
	 * @param inFile Settings file
	 * @return Error status
	 */
	public static ErrorCode loadProjectFile(File inFile) {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		XmlOpener fileOpener = new XmlOpener();
		
		ProjectFile = inFile;
		ProjectPath = ProjectFile.getPath();
		errorStatus = ProjectSettingsConf.openProjectFile(ProjectFile);
		if (errorStatus != ErrorCode.NO_ERROR) {
			showErrorDialog("Error opening configuration file");
			return errorStatus;
		}
		/* Send information to GUI */	
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
		SelectedMicrocontroller.processDocument();
		
		if (!ProjectSettingsConf.getConfFile().getName().equals("")) {
			/* If a configuration file is found */
			errorStatus = fileOpener.OpenFile(ProjectSettingsConf.getConfFile());
			errorStatus = SelectedMicrocontroller.loadPinsConf(fileOpener.getParsedDoc());
		} else {
			Features.verbosePrint("No pin configuration file found...");
		}
		
		CgMainWindow.setProjectInformation(ProjectSettingsConf.getProjectName(), SelectedMicrocontroller.getUc_manufacturer(), SelectedMicrocontroller.getUc_model());
		return errorStatus;
	}
	
	/**
	 * Show an error dialog
	 * @param message Message to display
	 */
	public static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(CgMainWindow.FrmCodeGenerator,
				message,
			    "File error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Show about information window
	 */
	public static void showAboutWindow() {
		new AboutWindow();
	}
	
	/**
	 * Show the GPIOs configuration window
	 */
	public static void showGpioConfWindow() {
		CgGpioConfWindow = new GpioConfWindow(SelectedMicrocontroller);
	}
	
	/**
	 * Set the project's microcontroller configuration
	 * @param uC
	 */
	public static void setUC(Microcontroller uC) {
		SelectedMicrocontroller = uC;
	}
	
	/**
	 * Save the microcontroller's configuration to disk
	 */
	public static void saveUc() {
		ConfXmlWriter pinConfWriter = new ConfXmlWriter(SelectedMicrocontroller);
		pinConfWriter.writeXml(ProjectSettingsConf.getConfFile().getPath());
	}

}
