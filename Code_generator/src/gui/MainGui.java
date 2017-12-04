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
	static private MainWindow CgMainWindow;
	static private ProjectSettings ProjectSettingsConf = new ProjectSettings();
	static private Microcontroller SelectedMicrocontroller;
	static private Microcontroller NewMicrocontroller;
	
	/* Public fields */
	static public File ProjectFile;
	static public String ProjectPath;
	
	/**
	 * Main function
	 * @param args TBD
	 */
	static public void main(String[] args) {
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
	static public ErrorCode loadProjectFile(File inFile) {
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
	static public void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(CgMainWindow.FrmCodeGenerator,
				message,
			    "File error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Show about information window
	 */
	static public void showAboutWindow() {
		new AboutWindow();
	}
	
	/**
	 * Show the GPIOs configuration window
	 */
	static public void showGpioConfWindow() {
		new GpioConfWindow(SelectedMicrocontroller);
	}
	
	/**
	 * Set the project's microcontroller configuration
	 * @param uC Microcontroller configuration
	 */
	static public void setNewUC(Microcontroller uC) {
		if (uC == SelectedMicrocontroller)
		{
			Features.verbosePrint("No uC change...");
		} else
		{
			Features.verbosePrint("uC changed...");
		}
	}
	
	/**
	 * Save the microcontroller's configuration to disk
	 */
	static public void saveUc() {
		ConfXmlWriter pinConfWriter = new ConfXmlWriter(SelectedMicrocontroller);
		pinConfWriter.writeXml(ProjectSettingsConf.getConfFile().getPath());
	}

}
