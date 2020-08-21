package gui;

import java.io.File;

import javax.swing.JOptionPane;

import common.ErrorCode;
import common.Features;
import common.GeneralSettings;
import framework.CodeGenerator;
import microcontroller.Microcontroller;
import projectConfiguration.ProjectSettings;
import xmlCreator.ConfXmlWriter;
import xmlParser.XmlOpener;

/**
 * Main GUI state machine
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class MainGui {

	/* Private fields */
	static private MainWindow CgMainWindow;
	static private ProjectSettings ProjectSettingsConf = new ProjectSettings();
	static private GeneralSettings Settings;
	static private Microcontroller SelectedMicrocontroller;
	static private CodeGenerator generator;

	/* Public fields */

	/**
	 * Project configuration file
	 */
	static public File ProjectFile;

	/**
	 * Project's location
	 */
	static public String ProjectPath;

	/**
	 * 
	 * @param args TBD
	 */
	static public void main(String[] args) {
		GeneralSettings.initLog();
		printInitInfo();
		Features.verbosePrint("Starting GUI...");
		CgMainWindow = new MainWindow();
		CgMainWindow.setVisible(true);
	}

	static private void printInitInfo() {
		String version = Features.SW_VERSION;
		String codename = Features.VERSION_NAME;

		if (!Features.VERSION_STATUS.equals("Release")) {
			version += ", " + Features.VERSION_STATUS + " build";
		}
		if (Features.DEBUG) {
			version += " - Debug mode";
		}
		Features.verbosePrint("Kamino " + "version " + version + " " + codename);
	}

	/**
	 * Load the project settings file
	 * 
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
		errorStatus = ProjectSettingsConf.processDocument();
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
			errorStatus = SelectedMicrocontroller.loadAdcsConf(fileOpener.getParsedDoc());
			errorStatus = SelectedMicrocontroller.loadAdcChannelsConf(fileOpener.getParsedDoc());
			errorStatus = SelectedMicrocontroller.loadUartsConf(fileOpener.getParsedDoc());
		} else {
			Features.verbosePrint("No pin configuration file found...");
		}

		CgMainWindow.setProjectInformation(SelectedMicrocontroller, ProjectSettingsConf.getProjectName());
		return errorStatus;
	}

	/**
	 * Show an error dialog
	 * 
	 * @param message Message to display
	 */
	static public void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(CgMainWindow.FrmCodeGenerator, message, "File error", JOptionPane.ERROR_MESSAGE);
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
	 * Show the ADCs configuration window
	 */
	static public void showAdcConfWindow() {
		new AdcConfWindow(SelectedMicrocontroller);
	}
	
	/**
	 * Show the UARTs configuration window
	 */
	static public void showUartConfWindow() {
		new UartConfWindow(SelectedMicrocontroller);
	}

	/**
	 * Show the Project Preferences window
	 */
	static public void showProjectPreferencesWindow() {
		new ProjectSettingsWindow(ProjectSettingsConf);
	}

	/**
	 * Show the General Settings window
	 */
	static public void showGeneralSettingsWindow() {
		new GeneralSettingsWindow(Settings);
	}

	/**
	 * Set the project's microcontroller configuration
	 * 
	 * @param uC Microcontroller configuration
	 */
	static public void setNewUC(Microcontroller uC) {
		if (uC.equals(SelectedMicrocontroller)) {
			Features.verbosePrint("No uC change...");
		} else {
			Features.verbosePrint("uC changed...");
		}
	}

	/**
	 * Save the project's preferences
	 * 
	 * @param preferences Project's preferences
	 */
	static public void saveProjectPreferences(ProjectSettings preferences) {
		ProjectSettingsConf = preferences;
	}

	/**
	 * Save the General Settings
	 * 
	 * @param settings General Settings
	 */
	static public void saveGeneralSettings(GeneralSettings settings) {
		Settings = settings;
	}

	/**
	 * Save the microcontroller's configuration to disk
	 */
	static public void saveUc() {
		ConfXmlWriter pinConfWriter = new ConfXmlWriter(SelectedMicrocontroller);
		pinConfWriter.writeXml(ProjectSettingsConf.getConfFile().getPath());
	}

	/**
	 * Generate source code files
	 * 
	 * @return Error code
	 */
	static public ErrorCode generateCode() {
		ErrorCode errorCode;
		generator = new CodeGenerator(SelectedMicrocontroller, ProjectSettingsConf);

		errorCode = generator.Generate();

		return errorCode;
	}

}
