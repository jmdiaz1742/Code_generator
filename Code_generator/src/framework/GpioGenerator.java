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
import java.io.OutputStream;

import common.ErrorCode;
import common.Features;
import microcontroller.Microcontroller;
import projectConfiguration.ProjectSettings;

class GpioGenerator {

	private static final String MODULE_GPIO = "gpio";
	private static final String STR_ALT_MODE_NONE = "GPIO_ALT_NONE";
	private static final String STR_ALT_MODE_ADC = "GPIO_MODE_ANALOG";

	private static final String STR_SUFF_PORT = "_PORT";
	private static final String STR_SUFF_PIN = "_PIN";
	private static final String STR_SUFF_MODE = "_MODE";
	private static final String STR_SUFF_ALT = "_ALT";
	private static final String STR_SUFF_PULL = "_PULL";
	private static final String STR_SUFF_SPEED = "_SPEED";

	/* Toke Strings */
	private final static String STR_TKN_CFG_ARRAY = "FWK_GPIO_CFG_ARRAY";
	private final static String STR_TKN_ELEMENTS = "FWK_GPIO_ELEMENTS";
	private final static String STR_TKN_INC = "FWK_GPIO_INCLUDES"; // TODO: Get correct use
	private final static String STR_TKN_CFG_DEFS = "FWK_GPIO_CFG_DEFINITIONS"; // TODO: Get correct use
	private final static String STR_TKN_EL_DEFS = "FWK_GPIO_ELEMENTS_DEFINITIONS";

	/**
	 * 
	 * @param uC
	 *            Microcontroller
	 * @param projectSettings
	 *            Project's settings
	 * @return Error code
	 */
	public static ErrorCode generateCfgCFile(Microcontroller uC, ProjectSettings projectSettings) {
		ErrorCode error = ErrorCode.NO_ERROR;
		File cfgFile = null;
		File fwkFile = null;
		String currentFileText = "";
		String newFileText = "";
		InputStream inFile = null;
		OutputStream outFile = null;

		try {
			// Copy template file from installation folder
			fwkFile = new File(
					framework.Common.getCfgFileCPath(framework.Common.getInstallationFwkPath(), MODULE_GPIO));
			cfgFile = new File(framework.Common.getCfgFileCPath(projectSettings.getFrameworkPath(), MODULE_GPIO));
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
					+ framework.Common.getCfgFileCPath(projectSettings.getFrameworkPath(), MODULE_GPIO));
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
			newFileText = currentFileText.replaceAll(STR_TKN_CFG_ARRAY, generateCfgArray(uC));

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
	 * 
	 * @param uC
	 *            Microcontroller
	 * @param projectSettings
	 *            Project's settings
	 * @return Error code
	 */
	public static ErrorCode generateCfgHFile(Microcontroller uC, ProjectSettings projectSettings) {
		ErrorCode error = ErrorCode.NO_ERROR;
		File cfgFile = null;
		File fwkFile = null;
		String currentFileText = "";
		String newFileText = "";
		InputStream inFile = null;
		OutputStream outFile = null;

		try {
			// Copy template file from installation folder
			fwkFile = new File(
					framework.Common.getCfgFileHPath(framework.Common.getInstallationFwkPath(), MODULE_GPIO));
			cfgFile = new File(framework.Common.getCfgFileHPath(projectSettings.getFrameworkPath(), MODULE_GPIO));
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
					+ framework.Common.getCfgFileCPath(projectSettings.getFrameworkPath(), MODULE_GPIO));
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
			newFileText = currentFileText.replaceAll(STR_TKN_EL_DEFS, generateElDefs(uC));
			newFileText = newFileText.replaceAll(STR_TKN_ELEMENTS, generateElements(uC));

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
	private static String generateCfgArray(Microcontroller uC) {
		String cfgArray = "";

		for (int pinNum = 0; pinNum < uC.GpioCfgPin.length; pinNum++) {
			String pinName = uC.GpioCfgPin[pinNum].getCodeName();

			cfgArray += "{";
			cfgArray += pinName + STR_SUFF_PORT + "," + framework.Common.NL;
			cfgArray += pinName + STR_SUFF_PIN + "," + framework.Common.NL;
			cfgArray += pinName + STR_SUFF_MODE + "," + framework.Common.NL;
			cfgArray += pinName + STR_SUFF_ALT + "," + framework.Common.NL;
			cfgArray += pinName + STR_SUFF_PULL + "," + framework.Common.NL;
			cfgArray += pinName + STR_SUFF_SPEED + framework.Common.NL;
			cfgArray += "}";
			if (pinNum < uC.GpioCfgPin.length - 1) {
				cfgArray += "," + framework.Common.NL;
			}
		}

		return cfgArray;

	}

	/**
	 * 
	 * @param module
	 *            Framework module
	 * @return Elements definitions as String
	 */
	private static String generateElDefs(Microcontroller uC) {
		String elDefs = "";

		for (int pinNum = 0; pinNum < uC.GpioCfgPin.length; pinNum++) {
			String pinName = uC.GpioCfgPin[pinNum].getCodeName();
			String defineStr = framework.Common.STR_DEFINITION;

			elDefs += defineStr + pinName + STR_SUFF_PORT + " ";
			elDefs += uC.GpioCfgPin[pinNum].getPort() + framework.Common.NL;

			elDefs += defineStr + pinName + STR_SUFF_PIN + " ";
			elDefs += uC.GpioCfgPin[pinNum].getPinName() + framework.Common.NL;

			elDefs += defineStr + pinName + STR_SUFF_MODE + " ";
			elDefs += uC.GpioCfgPin[pinNum].getMode() + framework.Common.NL;

			elDefs += defineStr + pinName + STR_SUFF_ALT + " ";
			elDefs += STR_ALT_MODE_NONE + framework.Common.NL;

			elDefs += defineStr + pinName + STR_SUFF_PULL + " ";
			elDefs += uC.GpioCfgPin[pinNum].getPull() + framework.Common.NL;

			elDefs += defineStr + pinName + STR_SUFF_SPEED + " ";
			elDefs += uC.GpioCfgPin[pinNum].getSpeed() + framework.Common.NL;

			if (pinNum < uC.GpioCfgPin.length - 1) {
				elDefs += framework.Common.NL;
			}
		}

		return elDefs;
	}

	/**
	 * 
	 * @param module
	 *            Framework module
	 * @return Elements list as String
	 */
	private static String generateElements(Microcontroller uC) {
		String elements = "";

		for (int pinNum = 0; pinNum < uC.GpioCfgPin.length; pinNum++) {
			String pinName = uC.GpioCfgPin[pinNum].getCodeName();

			elements += pinName;

			if (pinNum < uC.GpioCfgPin.length - 1) {
				elements += "," + framework.Common.NL;
			}
		}

		return elements;
	}

}
