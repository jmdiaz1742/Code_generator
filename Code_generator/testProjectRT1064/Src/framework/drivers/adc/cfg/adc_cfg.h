/**
 * @file adc_cfg.h
 * @author Cesar Rodriguez
 * @brief ADC framework configuration header
 */

/************************
 * Guard                *
 ************************/
#ifndef _ADC_CFG_H_
#define _ADC_CFG_H_

/************************
 * Includes             *
 ************************/
// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
#include "MIMXRT1064.h"
#include "fsl_adc.h"
// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
APP_ADC,
// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    ADC_ELEMENTS_MAX
} Adc_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// APP_ADC
#define APP_ADC_NAME ADC1
#define APP_ADC_SAMPLE ADC_DISABLE_SAMPLE
#define APP_ADC_CLOCK OTHER_CLOCK_FOR_ADC
#define APP_ADC_JUSTIFICATION ADC_NOT_SUPPORTED
#define APP_ADC_PRESCALER DIVIDE_ADC_CLOCK_BY_4
#define APP_ADC_RESOLUTION ADC_12_BIT_RESOLUTION
#define APP_ADC_REFERENCE ADC_NOT_SUPPORTED

// Channel definitions
// ADC ADC1
#define POT_ADC_CHANNEL ADC_CHANNEL_15

// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Constants     *
 ************************/

/************************
 * Public Calibrations  *
 ************************/

/************************
 * Public Variables     *
 ************************/

/************************
 * Public Functions     *
 ************************/

#endif /* _ADC_CFG_H_ */
