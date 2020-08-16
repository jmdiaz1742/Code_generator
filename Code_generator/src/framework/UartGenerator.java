package framework;

import common.ErrorCode;
import microcontroller.Microcontroller;

public class UartGenerator {

	private static final String STR_SUFF_NAME = "_NAME";
	private static final String STR_SUFF_CLOCK = "_CLOCK";
	private static final String STR_SUFF_PRESCALER = "_PRESCALER";
	private static final String STR_SUFF_BAUD_RATE = "_BAUD_RATE";
	private static final String STR_SUFF_DATA_BITS = "_DATA_BITS";
	private static final String STR_SUFF_STOP_BITS = "_STOP_BITS";
	private static final String STR_SUFF_PARITY = "_PARITY";

	/* Token Strings */
	public static final String STR_TKN_CFG_ARRAY = "FWK_UART_CFG_ARRAY";
	public static final String STR_TKN_ELEMENTS = "FWK_UART_ELEMENTS";
	public static final String STR_TKN_INC = "FWK_UART_INCLUDES";
	public static final String STR_TKN_CFG_DEFS = "FWK_UART_CFG_DEFINITIONS";
	public static final String STR_TKN_EL_DEFS = "FWK_UART_ELEMENTS_DEFINITIONS";

	public static String getCfgArray(Microcontroller uC) {
		String cfgArray = "";

		if (uC.getUc_selectedUartsNum() > 0) {
			cfgArray = framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;
			int generatedUartsNum = 0;

			for (int uartNum = 0; uartNum < uC.UartCfg.length; uartNum++) {
				String uartName = uC.UartCfg[uartNum].getCodeName();

				if (uC.UartCfg[uartNum].getSelected().getBoolean()) {
					cfgArray += "{";
					cfgArray += uartName + STR_SUFF_NAME + "," + framework.Common.NL;
					cfgArray += uartName + STR_SUFF_CLOCK + "," + framework.Common.NL;
					cfgArray += uartName + STR_SUFF_PRESCALER + "," + framework.Common.NL;
					cfgArray += uartName + STR_SUFF_BAUD_RATE + "," + framework.Common.NL;
					cfgArray += uartName + STR_SUFF_DATA_BITS + "," + framework.Common.NL;
					cfgArray += uartName + STR_SUFF_STOP_BITS + "," + framework.Common.NL;
					cfgArray += uartName + STR_SUFF_PARITY + "," + framework.Common.NL;
					cfgArray += "}";
					generatedUartsNum++;
					if (generatedUartsNum < uC.getUc_selectedUartsNum()) {
						cfgArray += "," + framework.Common.NL;
					}
				}
			}

			cfgArray += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER + framework.Common.NL;
		}

		return cfgArray;

	}

	/**
	 * 
	 * @param uC Microcontroller used
	 * @return Elements definitions as String
	 */
	public static String getElDefs(Microcontroller uC) {
		String elDefs = "";

		if ((uC.Definitions_Uart.length > 0) && (uC.getUc_selectedUartsNum() > 0)) {
			elDefs = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;
			int generatedUartsNum = 0;

			for (int uartNum = 0; uartNum < uC.UartCfg.length; uartNum++) {
				String uartName = uC.UartCfg[uartNum].getCodeName();

				if (uC.UartCfg[uartNum].getSelected().getBoolean()) {

					elDefs += "// " + uartName + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + uartName + STR_SUFF_NAME + " ";
					elDefs += uC.UartCfg[uartNum].UartFeatures.getName() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + uartName + STR_SUFF_CLOCK + " ";
					elDefs += uC.UartCfg[uartNum].getClock() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + uartName + STR_SUFF_PRESCALER + " ";
					elDefs += uC.UartCfg[uartNum].getPrescaler() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + uartName + STR_SUFF_BAUD_RATE + " ";
					elDefs += uC.UartCfg[uartNum].getBaudRate() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + uartName + STR_SUFF_DATA_BITS + " ";
					elDefs += uC.UartCfg[uartNum].getDataBits() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + uartName + STR_SUFF_STOP_BITS + " ";
					elDefs += uC.UartCfg[uartNum].getStopBits() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + uartName + STR_SUFF_PARITY + " ";
					elDefs += uC.UartCfg[uartNum].getParity() + framework.Common.NL;

					generatedUartsNum++;

					if (generatedUartsNum < uC.getUc_selectedUartsNum()) {
						elDefs += framework.Common.NL;
					}
				}
			}

			elDefs += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;
		}

		return elDefs;
	}

	/**
	 * 
	 * @param uC Microcontroller used
	 * @return Elements list as String
	 */
	public static String getElements(Microcontroller uC) {
		String elements = "";

		if (uC.getUc_selectedUartsNum() > 0) {
			elements = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

			for (int uartNum = 0; uartNum < uC.UartCfg.length; uartNum++) {
				String uartName = uC.UartCfg[uartNum].getCodeName();

				if (uC.UartCfg[uartNum].getSelected().getBoolean()) {

					elements += uartName;
					elements += "," + framework.Common.NL;
				}
			}

			elements += framework.Common.STR_GEN_CODE_NOTICE_FOOTER;
		}

		return elements;
	}

	/**
	 * 
	 * @param uC Microcontroller used
	 * @return Headers needed for GPIO module
	 */
	public static String getIncludes(Microcontroller uC) {
		String includes = "";

		if ((uC.Includes_Uart.length > 0) && (uC.getUc_selectedUartsNum() > 0)) {
			includes = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

			for (int incNum = 0; incNum < uC.Includes_Uart.length; incNum++) {
				String includeModule = uC.Includes_Uart[incNum];
				if (!includeModule.equals(ErrorCode.STR_INVALID)) {
					includes += framework.Common.STR_INCLUDE + "\"" + includeModule + "\"";
					if (incNum < uC.Includes_Uart.length - 1) {
						includes += framework.Common.NL;
					}
				}
			}

			includes += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;
		}

		return includes;
	}

	public static String getCfgDefinitions(Microcontroller uC) {
		String cfgDefs = "";

		if ((uC.Definitions_Uart.length > 0) && (uC.getUc_selectedUartsNum() > 0)) {
			cfgDefs = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

			if (uC.Definitions_Gpio[0] != ErrorCode.STR_INVALID) {
				for (int defNum = 0; defNum < uC.Definitions_Uart.length; defNum++) {
					cfgDefs += framework.Common.STR_DEFINITION + uC.Definitions_Uart[defNum];
					if (defNum < uC.Definitions_Uart.length - 1) {
						cfgDefs += framework.Common.NL;
					}
				}
			}

			cfgDefs += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;
		}

		return cfgDefs;
	}

}
