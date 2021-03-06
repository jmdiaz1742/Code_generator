package framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

	private Microcontroller uC;
	private ProjectSettings projectSettings;
	private boolean generateGpio = false;
	private boolean generateAdc = false;
	private boolean generateUart = false;

	private final static String STR_TKN_FWK_COMMON_INC = "FWK_COMMON_INCLUDES";
	private final static String STR_TKN_FWK_MODULES_INC = "FWK_MODULES_INCLUDES";

	public static final String STR_TKN_CFG_DEFS_COMMON = "FWK_GPIO_COMMON_DEFINITIONS";
	public static final String STR_TKN_CFG_DEFS_GPIO = "FWK_GPIO_CFG_DEFINITIONS";

	/**
	 * Constructor
	 * 
	 * @param uC              Project's microcontroller
	 * @param projectSettings Project's settings
	 */
	public CodeGenerator(Microcontroller uC, ProjectSettings projectSettings) {
		this.uC = uC;
		this.projectSettings = projectSettings;
	}

	/**
	 * Generate project's configuration files
	 * 
	 * @return Error code
	 */
	public ErrorCode Generate() {
		ErrorCode error = ErrorCode.NO_ERROR;

		/* Check which files to generate */
		generateGpio = uC.getUc_selectedPinsNum() > 0;
		generateAdc = uC.getUc_selectedAdcsNum() > 0;
		generateUart = uC.getUc_selectedUartsNum() > 0;

		// Generate module files:
		if (generateGpio) {
			error = generateCfgFiles(framework.Common.STR_MODULE_GPIO);
		}
		if (generateAdc) {
			error = generateCfgFiles(framework.Common.STR_MODULE_ADC);
		}
		if (generateUart) {
			error = generateCfgFiles(framework.Common.STR_MODULE_UART);
		}
		error = generateCommonFiles();

		return error;
	}

	/**
	 * Generate Common framework files
	 * 
	 * @return Error Code
	 */
	private ErrorCode generateCommonFiles() {
		ErrorCode error = ErrorCode.NO_ERROR;
		File cfgFile = null;
		File fwkFile = null;
		String modules = "";

		/* Set modules in use */
		modules = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;
		if (generateGpio) {
			modules += framework.Common.STR_INCLUDE + "\"" + framework.Common.STR_MODULE_GPIO;
			modules += framework.Common.STR_HEADER_EXT + "\"" + framework.Common.NL;
		}
		if (generateAdc) {
			modules += framework.Common.STR_INCLUDE + "\"" + framework.Common.STR_MODULE_ADC;
			modules += framework.Common.STR_HEADER_EXT + "\"" + framework.Common.NL;
		}
		if (generateUart) {
			modules += framework.Common.STR_INCLUDE + "\"" + framework.Common.STR_MODULE_UART;
			modules += framework.Common.STR_HEADER_EXT + "\"" + framework.Common.NL;
		}
		modules += framework.Common.STR_GEN_CODE_NOTICE_FOOTER;

		/* Generate frameworkCommon.h */
		Features.verbosePrint("Generating framework common file...");

		fwkFile = new File(framework.Common.getFrameworkCommonFilePath(framework.Common.getInstallationFwkPath()));
		cfgFile = new File(framework.Common.getFrameworkCommonFilePath(projectSettings.getFrameworkPath()));

		error = copyFile(fwkFile, cfgFile);

		error = replaceInFile(cfgFile, STR_TKN_FWK_COMMON_INC, framework.Common.getCommonIncludes(uC));
		error = replaceInFile(cfgFile, STR_TKN_CFG_DEFS_COMMON, framework.Common.getCommonCfgDefinitions(uC));
		if (error == ErrorCode.NO_ERROR) {
			Features.verbosePrint("Framework common file generated");
		}

		/* Generate frameworkIncludes.h */
		Features.verbosePrint("Generating framework common file...");

		fwkFile = new File(framework.Common.getFrameworkIncludesFilePath(framework.Common.getInstallationFwkPath()));
		cfgFile = new File(framework.Common.getFrameworkIncludesFilePath(projectSettings.getFrameworkPath()));

		error = copyFile(fwkFile, cfgFile);
		error = replaceInFile(cfgFile, STR_TKN_FWK_MODULES_INC, modules);
		if (error == ErrorCode.NO_ERROR) {
			Features.verbosePrint("Framework common file generated");
		}

		return error;
	}

	/**
	 * Generate configuration .c and .h files
	 * 
	 * @param module Framework module
	 * @return Error Code
	 */
	private ErrorCode generateCfgFiles(String module) {
		ErrorCode error = ErrorCode.NO_ERROR;
		File cfgFile = null;
		File fwkFile = null;

		Features.verbosePrint("Generating " + module + " configuration C file...");

		fwkFile = new File(framework.Common.getCfgFileCPath(framework.Common.getInstallationFwkPath(), module));
		cfgFile = new File(framework.Common.getCfgFileCPath(projectSettings.getFrameworkPath(), module));

		error = copyFile(fwkFile, cfgFile);

		switch (module) {
		case framework.Common.STR_MODULE_GPIO: {
			error = replaceInFile(cfgFile, GpioGenerator.STR_TKN_CFG_ARRAY, GpioGenerator.getCfgArray(uC));
			break;
		}
		case framework.Common.STR_MODULE_ADC: {
			error = replaceInFile(cfgFile, AdcGenerator.STR_TKN_CFG_ARRAY, AdcGenerator.getCfgArray(uC));
			break;
		}
		case framework.Common.STR_MODULE_UART: {
			error = replaceInFile(cfgFile, UartGenerator.STR_TKN_CFG_ARRAY, UartGenerator.getCfgArray(uC));
			break;
		}
		default: {
			error = ErrorCode.FILE_CONF_ERROR;
			break;
		}
		}
		// beautifyFile(cfgFile);
		if (error == ErrorCode.NO_ERROR) {
			Features.verbosePrint(module + " configuration C file generated");
		}

		Features.verbosePrint("Generating " + module + " configuration H file...");

		fwkFile = new File(framework.Common.getCfgFileHPath(framework.Common.getInstallationFwkPath(), module));
		cfgFile = new File(framework.Common.getCfgFileHPath(projectSettings.getFrameworkPath(), module));

		error = copyFile(fwkFile, cfgFile);

		switch (module) {
		case framework.Common.STR_MODULE_GPIO: {
			replaceInFile(cfgFile, GpioGenerator.STR_TKN_INC, GpioGenerator.getIncludes(uC));
			replaceInFile(cfgFile, GpioGenerator.STR_TKN_EL_DEFS, GpioGenerator.getElDefs(uC));
			replaceInFile(cfgFile, GpioGenerator.STR_TKN_ELEMENTS, GpioGenerator.getElements(uC));
			replaceInFile(cfgFile, GpioGenerator.STR_TKN_INC, GpioGenerator.getIncludes(uC));
			replaceInFile(cfgFile, GpioGenerator.STR_TKN_CFG_DEFS, GpioGenerator.getCfgDefinitions(uC));
			break;
		}
		case framework.Common.STR_MODULE_ADC: {
			replaceInFile(cfgFile, AdcGenerator.STR_TKN_INC, AdcGenerator.getIncludes(uC));
			replaceInFile(cfgFile, AdcGenerator.STR_TKN_EL_DEFS, AdcGenerator.getElDefs(uC));
			replaceInFile(cfgFile, AdcGenerator.STR_TKN_ELEMENTS, AdcGenerator.getElements(uC));
			replaceInFile(cfgFile, AdcGenerator.STR_TKN_INC, AdcGenerator.getIncludes(uC));
			replaceInFile(cfgFile, AdcGenerator.STR_TKN_CFG_DEFS, AdcGenerator.getCfgDefinitions(uC));
			break;
		}
		case framework.Common.STR_MODULE_UART: {
			replaceInFile(cfgFile, UartGenerator.STR_TKN_INC, UartGenerator.getIncludes(uC));
			replaceInFile(cfgFile, UartGenerator.STR_TKN_EL_DEFS, UartGenerator.getElDefs(uC));
			replaceInFile(cfgFile, UartGenerator.STR_TKN_ELEMENTS, UartGenerator.getElements(uC));
			replaceInFile(cfgFile, UartGenerator.STR_TKN_INC, UartGenerator.getIncludes(uC));
			replaceInFile(cfgFile, UartGenerator.STR_TKN_CFG_DEFS, UartGenerator.getCfgDefinitions(uC));
			break;
		}
		default: {
			error = ErrorCode.FILE_CONF_ERROR;
			break;
		}
		}
		// beautifyFile(cfgFile);
		if (error == ErrorCode.NO_ERROR) {
			Features.verbosePrint(module + " configuration H file generated");
		}

		return error;
	}

	/**
	 * Copies one file from one location to another
	 * 
	 * @param src  Original file
	 * @param dest New file
	 * @return Error Code
	 */
	static private ErrorCode copyFile(File src, File dest) {
		ErrorCode error = ErrorCode.NO_ERROR;
		InputStream inFile = null;
		OutputStream outFile = null;

		try {
//			if (!dest.exists()) {
//				dest.createNewFile();
//			}
			// Copy template file from installation folder
			inFile = new FileInputStream(src);
			outFile = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = inFile.read(buffer)) > 0) {
				outFile.write(buffer, 0, length);
			}

		} catch (FileNotFoundException e1) {
			error = ErrorCode.FILE_READ_ERROR;
			Features.verbosePrint("Error opening file " + src.getPath());
			e1.printStackTrace();
			return error;
		} catch (IOException e) {
			error = ErrorCode.FILE_WRITE_ERROR;
			Features.verbosePrint("Error saving file " + dest.getPath());
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

		return error;
	}

	/**
	 * Replaces text inside a file
	 * 
	 * @param file        Configuration File
	 * @param originalStr String to replace
	 * @param newStf      New String
	 * @return Error Code
	 */
	private static ErrorCode replaceInFile(File file, String originalStr, String newStf) {
		ErrorCode error = ErrorCode.NO_ERROR;
		String currentFileText = "";
		String newFileText = "";

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				currentFileText += line + "\r\n";
			}
			reader.close();

			// Replace token string on template with configuration array
			newFileText = currentFileText.replaceAll(originalStr, newStf);

			FileWriter writer = new FileWriter(file.getAbsolutePath());
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
	 * Beautifies a .c or .h file using the .clang_format configuration settings
	 * 
	 * @param file File to beautify
	 */
	@SuppressWarnings("unused") /* TODO: Fix this method */
	private static void beautifyFile(File file) {
		// String command = "clang-format -i -style=file " + "\"" + file.getPath() +
		// "\"";
		String command = "cd " + "\"" + file.getParent() + "\" && echo clang-format -i -style=file " + file.getName()
				+ " >> beautify.sh && sh beautify.sh";
		String consoleText = "";
		String consoleBuffer = null;
		Process process;

		if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
			command = "cmd.exe /c" + command;
		} else if (System.getProperty("os.name").toLowerCase().startsWith("linux")) {
			Features.verbosePrint("Executing command: " + command);
			try {
				process = Runtime.getRuntime().exec(command);

				BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

				BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				// read the output from the command
				System.out.println("Here is the standard output of the command:\n");
				while ((consoleBuffer = stdInput.readLine()) != null) {
					consoleText += consoleBuffer;
				}
				Features.verbosePrint("Console output: " + consoleText);

				consoleText = "";
				consoleBuffer = null;

				// read any errors from the attempted command
				while ((consoleBuffer = stdError.readLine()) != null) {
					consoleText += consoleBuffer;
				}
				Features.verbosePrint("Console error: " + consoleText);

				System.exit(0);
			} catch (IOException e) {
				Features.verbosePrint("Error executing command " + command);
				e.printStackTrace();
				System.exit(-1);
			}
		} else {
			Features.verbosePrint("Error: OS not found!");
		}
	}

}
