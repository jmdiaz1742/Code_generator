package framework;

import common.ErrorCode;
import microcontroller.Microcontroller;

public class AdcGenerator {

	private static final String STR_SUFF_SAMPLE = "_SAMPLE";
	private static final String STR_SUFF_CLOCK = "_CLOCK";
	private static final String STR_SUFF_JUSTIFICATION = "_JUSTIFICATION";
	private static final String STR_SUFF_PRESCALER = "_PRESCALER";
	private static final String STR_SUFF_RESOLUTION = "_RESOLUTION";
	private static final String STR_SUFF_REFERENCE = "_REFERENCE";

	/* Token Strings */
	public static final String STR_TKN_CFG_ARRAY = "FWK_ADC_CFG_ARRAY";
	public static final String STR_TKN_ELEMENTS = "FWK_ADC_ELEMENTS";
	public static final String STR_TKN_INC = "FWK_ADC_INCLUDES";
	public static final String STR_TKN_CFG_DEFS = "FWK_ADC_CFG_DEFINITIONS";
	public static final String STR_TKN_EL_DEFS = "FWK_ADC_ELEMENTS_DEFINITIONS";

	public static String getCfgArray(Microcontroller uC) {
		String cfgArray = "";

		if (uC.getUc_selectedAdcsNum() > 0) {
			cfgArray = framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;
			int generatedAdcsNum = 0;

			for (int adcNum = 0; adcNum < uC.AdcCfg.length; adcNum++) {
				String adcName = uC.AdcCfg[adcNum].getCodeName();

				if (uC.AdcCfg[adcNum].getSelected().getBoolean()) {
					cfgArray += "{";
					cfgArray += adcName + STR_SUFF_SAMPLE + "," + framework.Common.NL;
					cfgArray += adcName + STR_SUFF_CLOCK + "," + framework.Common.NL;
					cfgArray += adcName + STR_SUFF_JUSTIFICATION + "," + framework.Common.NL;
					cfgArray += adcName + STR_SUFF_PRESCALER + "," + framework.Common.NL;
					cfgArray += adcName + STR_SUFF_RESOLUTION + "," + framework.Common.NL;
					cfgArray += adcName + STR_SUFF_REFERENCE + "," + framework.Common.NL;
					cfgArray += "}";
					generatedAdcsNum++;
					if (generatedAdcsNum < uC.getUc_selectedAdcsNum()) {
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

		if (uC.Definitions_Adc.length > 0) {
			elDefs = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;
			int generatedAdcsNum = 0;

			for (int adcNum = 0; adcNum < uC.AdcCfg.length; adcNum++) {
				String adcName = uC.AdcCfg[adcNum].getCodeName();

				if (uC.AdcCfg[adcNum].getSelected().getBoolean()) {

					elDefs += "// " + adcName + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + adcName + STR_SUFF_SAMPLE + " ";
					elDefs += uC.AdcCfg[adcNum].getSample() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + adcName + STR_SUFF_CLOCK + " ";
					elDefs += uC.AdcCfg[adcNum].getClock() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + adcName + STR_SUFF_JUSTIFICATION + " ";
					elDefs += uC.AdcCfg[adcNum].getJustification() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + adcName + STR_SUFF_PRESCALER + " ";
					elDefs += uC.AdcCfg[adcNum].getPrescaler() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + adcName + STR_SUFF_RESOLUTION + " ";
					elDefs += uC.AdcCfg[adcNum].getResolution() + framework.Common.NL;

					elDefs += framework.Common.STR_DEFINITION + adcName + STR_SUFF_REFERENCE + " ";
					elDefs += uC.AdcCfg[adcNum].getReference() + framework.Common.NL;

					generatedAdcsNum++;

					if (generatedAdcsNum < uC.getUc_selectedAdcsNum()) {
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

		if (uC.getUc_selectedAdcsNum() > 0) {
			elements = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

			for (int adcNum = 0; adcNum < uC.AdcCfg.length; adcNum++) {
				String adcName = uC.AdcCfg[adcNum].getCodeName();

				if (uC.AdcCfg[adcNum].getSelected().getBoolean()) {

					elements += adcName;
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

		includes = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

		for (int incNum = 0; incNum < uC.Includes_Adc.length; incNum++) {
			String includeModule = uC.Includes_Adc[incNum];
			if (!includeModule.equals(ErrorCode.STR_INVALID)) {
				includes += framework.Common.STR_INCLUDE + "\"" + includeModule + "\"";
				if (incNum < uC.Includes_Adc.length - 1) {
					includes += framework.Common.NL;
				}
			}
		}

		includes += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;

		return includes;
	}

	public static String getCfgDefinitions(Microcontroller uC) {
		String cfgDefs = "";

		if (uC.Definitions_Adc.length > 0) {
			cfgDefs = framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;

			if (uC.Definitions_Gpio[0] != ErrorCode.STR_INVALID) {
				for (int defNum = 0; defNum < uC.Definitions_Adc.length; defNum++) {
					cfgDefs += framework.Common.STR_DEFINITION + uC.Definitions_Adc[defNum];
					if (defNum < uC.Definitions_Adc.length - 1) {
						cfgDefs += framework.Common.NL;
					}
				}
			}

			cfgDefs += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER;
		}

		return cfgDefs;
	}

}
