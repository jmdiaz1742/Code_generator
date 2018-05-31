package framework;

import common.ErrorCode;
import microcontroller.Microcontroller;
import projectConfiguration.ProjectSettings;

/**
 * 
 * @author ovd
 *
 */
public class CodeGenerator {

	private Microcontroller SelectedUc;
	private ProjectSettings CurrentProjectSettings;

	/**
	 * Constructor
	 * 
	 * @param uC
	 *            Project's microcontroller
	 * @param projectSettings
	 *            Project's settings
	 */
	public CodeGenerator(Microcontroller uC, ProjectSettings projectSettings) {
		SelectedUc = uC;
		CurrentProjectSettings = projectSettings;
	}

	/**
	 * Generate project's configuration files
	 * 
	 * @return Error code
	 */
	public ErrorCode Generate() {
		ErrorCode error = ErrorCode.NO_ERROR;

		// Generate GPIO module files:
		error = GpioGenerator.generateCfgCFile(SelectedUc, CurrentProjectSettings);
		error = GpioGenerator.generateCfgHFile(SelectedUc, CurrentProjectSettings);

		return error;
	}

}
