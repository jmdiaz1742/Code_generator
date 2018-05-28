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

	private Microcontroller SelectedUC;
	private ProjectSettings CurrentProjectSettings;

	/**
	 * Constructor
	 * 
	 * @param uC
	 *            Project microcontroller
	 * @param projectSettings
	 *            Project's settings
	 */
	public CodeGenerator(Microcontroller uC, ProjectSettings projectSettings) {
		SelectedUC = uC;
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
		error = generateCfgFile(framework.Common.STR_MODULE_GPIO);

		return error;
	}

	/**
	 * Generate configuration file
	 * 
	 * @param module
	 *            Framework module to generate
	 * @return Error code
	 */
	private ErrorCode generateCfgFile(String module) {
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
			// Replace token string on template with configuration array
			BufferedReader reader = new BufferedReader(new FileReader(cfgFile));
			String line = "";
			while ((line = reader.readLine()) != null) {
				currentFileText += line + "\r\n";
			}
			reader.close();

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

	/**
	 * Generate configuration array from microcontroller
	 * 
	 * @param module
	 *            Framework module
	 * @return Configuration array as string
	 */
	private String generateCfgArray(String module) {
		String cfgArray = "";

		return cfgArray;

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

		return cfgArray;
	}

}
