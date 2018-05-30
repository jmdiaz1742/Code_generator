package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import common.ErrorCode;
import common.Features;
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

	private static final String STR_ALT_FUNC_DEF = "GPIO_ALT_NONE";
	private static final String NL = "\r\n";
	private static final String STR_DEFINITION = "#define ";

	/**
	 * Constructor
	 * 
	 * @param uC
	 *            Project microcontroller
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
		error = generateCfgCFile(framework.Common.STR_MODULE_GPIO);
		error = generateCfgHFile(framework.Common.STR_MODULE_GPIO);

		return error;
	}

	/**
	 * Generate configuration file
	 * 
	 * @param module
	 *            Framework module to generate
	 * @return Error code
	 */
	private ErrorCode generateCfgCFile(String module) {
		ErrorCode error = ErrorCode.NO_ERROR;
		File cfgFile = null;
		File fwkFile = null;
		String currentFileText = "";
		String newFileText = "";
		InputStream inFile = null;
		OutputStream outFile = null;

		try {
			// Copy template file from installation folder
			fwkFile = new File(framework.Common.getCfgFileCPath(framework.Common.getInstallationFwkPath(), module));
			cfgFile = new File(framework.Common.getCfgFileCPath(CurrentProjectSettings.getFrameworkPath(), module));
			inFile = new FileInputStream(fwkFile);
			outFile = new FileOutputStream(cfgFile);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = inFile.read(buffer)) > 0) {
				outFile.write(buffer, 0, length);
			}

		} catch (FileNotFoundException e1) {
			error = ErrorCode.FILE_READ_ERROR;
			Features.verbosePrint("Error opening file"
					+ framework.Common.getCfgFileCPath(CurrentProjectSettings.getFrameworkPath(), module));
			e1.printStackTrace();
			return error;
		} catch (IOException e) {
			error = ErrorCode.FILE_WRITE_ERROR;
			e.printStackTrace();
			return error;
		} finally {
			try {
				inFile.close();
				outFile.close();
			} catch (IOException e) {
				error = ErrorCode.FILE_WRITE_ERROR;
				e.printStackTrace();
				return error;
			}

		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(cfgFile));
			String line = "";
			while ((line = reader.readLine()) != null) {
				currentFileText += line + "\r\n";
			}
			reader.close();

			// Replace token string on template with configuration array
			newFileText = currentFileText.replaceAll(getCfgArrayTokenString(module), generateCfgArray(module));

			FileWriter writer = new FileWriter(cfgFile.getAbsolutePath());
			writer.write(newFileText);
			writer.close();

		} catch (FileNotFoundException e) {
			error = ErrorCode.FILE_READ_ERROR;
			e.printStackTrace();
		} catch (IOException e) {
			error = ErrorCode.FILE_WRITE_ERROR;
			e.printStackTrace();
		}

		return error;
	}

	private ErrorCode generateCfgHFile(String module) {
		ErrorCode error = ErrorCode.NO_ERROR;
		File cfgFile = null;
		File fwkFile = null;
		String currentFileText = "";
		String newFileText = "";
		InputStream inFile = null;
		OutputStream outFile = null;

		try {
			// Copy template file from installation folder
			fwkFile = new File(framework.Common.getCfgFileHPath(framework.Common.getInstallationFwkPath(), module));
			cfgFile = new File(framework.Common.getCfgFileHPath(CurrentProjectSettings.getFrameworkPath(), module));
			inFile = new FileInputStream(fwkFile);
			outFile = new FileOutputStream(cfgFile);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = inFile.read(buffer)) > 0) {
				outFile.write(buffer, 0, length);
			}

		} catch (FileNotFoundException e1) {
			error = ErrorCode.FILE_READ_ERROR;
			Features.verbosePrint("Error opening file"
					+ framework.Common.getCfgFileCPath(CurrentProjectSettings.getFrameworkPath(), module));
			e1.printStackTrace();
			return error;
		} catch (IOException e) {
			error = ErrorCode.FILE_WRITE_ERROR;
			e.printStackTrace();
			return error;
		} finally {
			try {
				inFile.close();
				outFile.close();
			} catch (IOException e) {
				error = ErrorCode.FILE_WRITE_ERROR;
				e.printStackTrace();
				return error;
			}

		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(cfgFile));
			String line = "";
			while ((line = reader.readLine()) != null) {
				currentFileText += line + "\r\n";
			}
			reader.close();

			// Replace token string on template with configuration array
			newFileText = currentFileText.replaceAll(getElDefsTokenString(module), generateElDefs(module));
			newFileText = newFileText.replaceAll(getElementsTokenString(module), generateElements(module));

			FileWriter writer = new FileWriter(cfgFile.getAbsolutePath());
			writer.write(newFileText);
			writer.close();

		} catch (FileNotFoundException e) {
			error = ErrorCode.FILE_READ_ERROR;
			e.printStackTrace();
		} catch (IOException e) {
			error = ErrorCode.FILE_WRITE_ERROR;
			e.printStackTrace();
		}

		return error;
	}

	/**
	 * Generate configuration array from microcontroller
	 * 
	 * @param module
	 *            Framework module
	 * @return Configuration array as string
	 */
	private String generateCfgArray(String module) {
		String cfgArray = "";

		switch (module) {
		case framework.Common.STR_MODULE_GPIO:
			for (int pinNum = 0; pinNum < SelectedUc.GpioCfgPin.length; pinNum++) {
				String pinName = SelectedUc.GpioCfgPin[pinNum].getCodeName();

				cfgArray += "{";
				cfgArray += pinName + "_PORT," + NL;
				cfgArray += pinName + "_PIN," + NL;
				cfgArray += pinName + "_MODE," + NL;
				cfgArray += pinName + "_ALT," + NL;
				cfgArray += pinName + "_PULL," + NL;
				cfgArray += pinName + "_SPEED" + NL;
				cfgArray += "}";
				if (pinNum < SelectedUc.GpioCfgPin.length - 1) {
					cfgArray += "," + NL;
				}
			}
			break;
		}

		return cfgArray;

	}

	/**
	 * 
	 * @param module
	 *            Framework module
	 * @return Elements definitions as String
	 */
	private String generateElDefs(String module) {
		String elDefs = "";

		switch (module) {
		case framework.Common.STR_MODULE_GPIO:
			for (int pinNum = 0; pinNum < SelectedUc.GpioCfgPin.length; pinNum++) {
				String pinName = SelectedUc.GpioCfgPin[pinNum].getCodeName();

				elDefs += STR_DEFINITION + pinName + "_PORT ";
				elDefs += "PORT_" + SelectedUc.GpioCfgPin[pinNum].getPort() + NL;

				elDefs += STR_DEFINITION + pinName + "_PIN ";
				elDefs += "PIN_" + SelectedUc.GpioCfgPin[pinNum].getPinName() + NL;

				elDefs += STR_DEFINITION + pinName + "_MODE ";
				elDefs += SelectedUc.GpioCfgPin[pinNum].getMode() + NL;

				elDefs += STR_DEFINITION + pinName + "_ALT ";
				elDefs += STR_ALT_FUNC_DEF + NL;

				elDefs += STR_DEFINITION + pinName + "_PULL ";
				elDefs += SelectedUc.GpioCfgPin[pinNum].getPull() + NL;

				elDefs += STR_DEFINITION + pinName + "_SPEED ";
				elDefs += SelectedUc.GpioCfgPin[pinNum].getSpeed() + NL;

				if (pinNum < SelectedUc.GpioCfgPin.length - 1) {
					elDefs += NL;
				}
			}
			break;
		}

		return elDefs;
	}
	
	private String generateElements(String module) {
		String elements = "";

		switch (module) {
		case framework.Common.STR_MODULE_GPIO:
			for (int pinNum = 0; pinNum < SelectedUc.GpioCfgPin.length; pinNum++) {
				String pinName = SelectedUc.GpioCfgPin[pinNum].getCodeName();
				
				elements += pinName;

				if (pinNum < SelectedUc.GpioCfgPin.length - 1) {
					elements += "," + NL;
				}
			}
			break;
		}

		return elements;
	}

	/**
	 * Get the configuration array token string to replace in template file
	 * 
	 * @param module
	 *            Framework module
	 * @return Configuration String to replace
	 */
	private String getCfgArrayTokenString(String module) {
		String cfgArray = "";

		switch (module) {
		case framework.Common.STR_MODULE_GPIO:
			cfgArray = framework.Common.STR_TKN_GPIO_CFG_ARRAY;
			break;
		}

		return cfgArray;
	}

	/**
	 * 
	 * @param module
	 *            Framework module
	 * @return Elements definitions String to replace
	 */
	private String getElDefsTokenString(String module) {
		String cfgArray = "";

		switch (module) {
		case framework.Common.STR_MODULE_GPIO:
			cfgArray = framework.Common.STR_TKN_GPIO_EL_DEFS;
			break;
		}

		return cfgArray;
	}
	
	private String getElementsTokenString(String module) {
		String cfgArray = "";

		switch (module) {
		case framework.Common.STR_MODULE_GPIO:
			cfgArray = framework.Common.STR_TKN_GPIO_ELEMENTS;
			break;
		}

		return cfgArray;
	}

}
