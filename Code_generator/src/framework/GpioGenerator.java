package framework;

import common.ErrorCode;
import microcontroller.Microcontroller;

class GpioGenerator {

	private static final String STR_ALT_MODE_NONE = "GPIO_ALT_NONE";

	private static final String STR_SUFF_PORT = "_PORT";
	private static final String STR_SUFF_PIN = "_PIN";
	private static final String STR_SUFF_MODE = "_MODE";
	private static final String STR_SUFF_ALT = "_ALT";
	private static final String STR_SUFF_PULL = "_PULL";
	private static final String STR_SUFF_SPEED = "_SPEED";
	private static final String STR_SUFF_INIT_OUT = "_INIT_OUT";

	/* Token Strings */
	public static final String STR_TKN_CFG_ARRAY = "FWK_GPIO_CFG_ARRAY";
	public static final String STR_TKN_ELEMENTS = "FWK_GPIO_ELEMENTS";
	public static final String STR_TKN_INC = "FWK_GPIO_INCLUDES";
	public static final String STR_TKN_CFG_DEFS = "FWK_GPIO_CFG_DEFINITIONS";
	public static final String STR_TKN_EL_DEFS = "FWK_GPIO_ELEMENTS_DEFINITIONS";

	/**
	 * Generate configuration array from microcontroller
	 * 
	 * @param uC Microcontroller used
	 * @return Configuration array as string
	 */
	public static String getCfgArray(Microcontroller uC) {
		String cfgArray = framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

		for (int pinNum = 0; pinNum < uC.GpioCfgPin.length; pinNum++) {
			String pinName = uC.GpioCfgPin[pinNum].getCodeName();

			if (uC.GpioCfgPin[pinNum].getSelected().getBoolean()) {
				cfgArray += "{";
				cfgArray += pinName + STR_SUFF_PORT + "," + framework.Common.NL;
				cfgArray += pinName + STR_SUFF_PIN + "," + framework.Common.NL;
				cfgArray += pinName + STR_SUFF_MODE + "," + framework.Common.NL;
				cfgArray += pinName + STR_SUFF_ALT + "," + framework.Common.NL;
				cfgArray += pinName + STR_SUFF_PULL + "," + framework.Common.NL;
				cfgArray += pinName + STR_SUFF_SPEED + "," + framework.Common.NL;
				cfgArray += pinName + STR_SUFF_INIT_OUT + framework.Common.NL;
				cfgArray += "}";
				if (pinNum < uC.GpioCfgPin.length - 1) {
					cfgArray += "," + framework.Common.NL;
				}
			}
		}

		cfgArray += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER + framework.Common.NL;

		return cfgArray;

	}

	/**
	 * 
	 * @param uC Microcontroller used
	 * @return Elements definitions as String
	 */
	public static String getElDefs(Microcontroller uC) {
		String elDefs = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

		for (int pinNum = 0; pinNum < uC.GpioCfgPin.length; pinNum++) {
			String pinName = uC.GpioCfgPin[pinNum].getCodeName();

			if (uC.GpioCfgPin[pinNum].getSelected().getBoolean()) {

				elDefs += "// " + pinName + framework.Common.NL;

				elDefs += framework.Common.STR_DEFINITION + pinName + STR_SUFF_PORT + " ";
				elDefs += uC.GpioCfgPin[pinNum].getPort() + framework.Common.NL;

				elDefs += framework.Common.STR_DEFINITION + pinName + STR_SUFF_PIN + " ";
				elDefs += uC.GpioCfgPin[pinNum].getPortPin() + framework.Common.NL;

				elDefs += framework.Common.STR_DEFINITION + pinName + STR_SUFF_MODE + " ";
				elDefs += uC.GpioCfgPin[pinNum].getMode() + framework.Common.NL;

				elDefs += framework.Common.STR_DEFINITION + pinName + STR_SUFF_ALT + " ";
				elDefs += STR_ALT_MODE_NONE + framework.Common.NL;

				elDefs += framework.Common.STR_DEFINITION + pinName + STR_SUFF_PULL + " ";
				elDefs += uC.GpioCfgPin[pinNum].getPull() + framework.Common.NL;

				elDefs += framework.Common.STR_DEFINITION + pinName + STR_SUFF_SPEED + " ";
				elDefs += uC.GpioCfgPin[pinNum].getSpeed() + framework.Common.NL;

				elDefs += framework.Common.STR_DEFINITION + pinName + STR_SUFF_INIT_OUT + " ";
				elDefs += uC.GpioCfgPin[pinNum].getOutLevel() + framework.Common.NL;

				if (pinNum < uC.GpioCfgPin.length - 1) {
					elDefs += framework.Common.NL;
				}
			}
		}

		elDefs += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;

		return elDefs;
	}

	/**
	 * 
	 * @param uC Microcontroller used
	 * @return Elements list as String
	 */
	public static String getElements(Microcontroller uC) {
		String elements = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

		for (int pinNum = 0; pinNum < uC.GpioCfgPin.length; pinNum++) {
			String pinName = uC.GpioCfgPin[pinNum].getCodeName();

			if (uC.GpioCfgPin[pinNum].getSelected().getBoolean()) {

				elements += pinName;
				elements += "," + framework.Common.NL;
			}
		}

		elements += framework.Common.STR_GEN_CODE_NOTICE_FOOTER;

		return elements;
	}

	/**
	 * 
	 * @param uC Microcontroller used
	 * @return Headers needed for GPIO module
	 */
	public static String getIncludes(Microcontroller uC) {
		String includes = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

		for (int incNum = 0; incNum < uC.Includes_Gpio.length; incNum++) {
			includes += framework.Common.STR_INCLUDE + "\"" + uC.Includes_Gpio[incNum] + "\"";
			if (incNum < uC.Includes_Gpio.length - 1) {
				includes += framework.Common.NL;
			}
		}

		includes += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;

		return includes;
	}

	public static String getCfgDefinitions(Microcontroller uC) {
		String cfgDefs = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

		if (uC.Definitions_Gpio[0] != ErrorCode.STR_INVALID) {
			for (int defNum = 0; defNum < uC.Definitions_Gpio.length; defNum++) {
				cfgDefs += framework.Common.STR_DEFINITION + uC.Definitions_Gpio[defNum];
				if (defNum < uC.Definitions_Gpio.length - 1) {
					cfgDefs += framework.Common.NL;
				}
			}
		}

		cfgDefs += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;

		return cfgDefs;
	}

}
