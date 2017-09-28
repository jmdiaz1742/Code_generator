package gui;

import projectConfiguration.ProjectSettings;

/**
 * Main GUI state machine
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class MainStateMachine {

	/* Private fields */
	MainWindow Window;
	ProjectSettings ProjectSettingsConf;
	
	/* Public fields */
	
	/**
	 * Main function
	 * @param args TBD
	 */
	public void main(String[] args) {
		// FIXME: Check how this would work, the idea is to call all the GUI
		// windows from here
		Window = new MainWindow();
		ProjectSettingsConf.openProjectFile(Window.ProjectSettingsFile);

	}
	
	

}
