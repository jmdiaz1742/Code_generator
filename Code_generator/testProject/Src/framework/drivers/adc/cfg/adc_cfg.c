/**
 * @file adc_cfg.c
 * @author Miguel Diaz
 * @brief ADC module configuration
 */

/************************
 * Includes             *
 ************************/
#include "adc.h"

/************************
 * Type Definitions     *
 ************************/

/************************
 * Macros               *
 ************************/

/************************
 * Defines              *
 ************************/

/************************
 * Constants            *
 ************************/

/************************
 * Calibrations         *
 ************************/

/************************
 * Global Variables     *
 ************************/
/**
 * @brief ADC configuration structures array
 */
const Adc_CfgType Adc_Cfg[ADC_ELEMENTS_MAX] = {{ADC_POT_INSTANCE,
                                                ADC_POT_CLK_PRESCALER,
                                                ADC_POT_RESOLUTION,
                                                ADC_POT_DATA_ALIGN,
                                                ADC_POT_SCAN_CONV_MODE,
                                                ADC_POT_EOC_SELECTION,
                                                ADC_POT_CONT_CONV_MODE,
                                                ADC_POT_NUMBER_OF_CONV,
                                                ADC_POT_EXT_TRIG_CONV,
                                                ADC_POT_EXT_TRIG_CONV_EDGE,
                                                ADC_POT_CHANNEL,
                                                ADC_POT_RANK,
                                                ADC_POT_SAMPLING_TIME,
                                                ADC_POT_OFFSET}};

/************************
 * File Scope Variables *
 ************************/

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/
