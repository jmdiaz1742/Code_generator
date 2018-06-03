package framework;

import microcontroller.Microcontroller;

class GpioGenerator {

	private static final String STR_ALT_MODE_NONE = "GPIO_ALT_NONE";

	private static final String STR_SUFF_PORT = "_PORT";
	private static final String STR_SUFF_PIN = "_PIN";
	private static final String STR_SUFF_MODE = "_MODE";
	private static final String STR_SUFF_ALT = "_ALT";
	private static final String STR_SUFF_PULL = "_PULL";
	private static final String STR_SUFF_SPEED = "_SPEED";

	/* Toke Strings */
	public static final String STR_TKN_CFG_ARRAY = "FWK_GPIO_CFG_ARRAY";
	public static final String STR_TKN_ELEMENTS = "FWK_GPIO_ELEMENTS";
	public static final String STR_TKN_INC = "FWK_GPIO_INCLUDES"; // TODO: Get correct use
	public static final String STR_TKN_CFG_DEFS = "FWK_GPIO_CFG_DEFINITIONS"; // TODO: Get correct use
	public static final String STR_TKN_EL_DEFS = "FWK_GPIO_ELEMENTS_DEFINITIONS";

	/**
	 * Generate configuration array from microcontroller
	 * 
	 * @param module
	 *            Framework module
	 * @return Configuration array as string
	 */
	public static String getCfgArray(Microcontroller uC) {
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
	public static String getElDefs(Microcontroller uC) {
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
	public static String getElements(Microcontroller uC) {
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
