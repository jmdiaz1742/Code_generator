package framework;

import microcontroller.Microcontroller;

public class AdcGenerator {

	private static final String STR_SUFF_CODE_SAMPLE = "_SAMPLE";
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
		String cfgArray = framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_HEADER + framework.Common.NL;
		int generatedAdcsNum = 0;

//		for (int adcNum = 0; adcNum < uC.AdcCfg.length; adcNum++) {
//			String adcName = uC.AdcCfg[adcNum].getCodeName();
//
//			if (uC.AdcCfg[adcNum].getSelected()) {
//				cfgArray += "{";
//				cfgArray += adcName + STR_SUFF_CODE_SAMPLE + "," + framework.Common.NL;
//				cfgArray += adcName + STR_SUFF_CLOCK + "," + framework.Common.NL;
//				cfgArray += adcName + STR_SUFF_JUSTIFICATION + "," + framework.Common.NL;
//				cfgArray += adcName + STR_SUFF_PRESCALER + "," + framework.Common.NL;
//				cfgArray += adcName + STR_SUFF_RESOLUTION + "," + framework.Common.NL;
//				cfgArray += adcName + STR_SUFF_REFERENCE + "," + framework.Common.NL;
//				cfgArray += "}";
//				generatedAdcsNum++;
//				if (generatedAdcsNum < uC.getUc_selectedGpioNum()) {
//					cfgArray += "," + framework.Common.NL;
//				}
//			}
//		}

		cfgArray += framework.Common.NL + framework.Common.STR_GEN_CODE_NOTICE_FOOTER + framework.Common.NL;

		return cfgArray;

	}

}
