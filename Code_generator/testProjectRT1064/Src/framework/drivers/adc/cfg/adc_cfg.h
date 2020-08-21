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
// ################## Kamino generator v1.1.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.1.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v1.1.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
ADC2,
// ################## Kamino generator v1.1.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    ADC_ELEMENTS_MAX
} Adc_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v1.1.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.1.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v1.1.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// ADC2
#define ADC2_NAME ADC2
#define ADC2_SAMPLE ADC_DISABLE_SAMPLE
#define ADC2_CLOCK OTHER_CLOCK_FOR_ADC
#define ADC2_JUSTIFICATION ADC_NOT_SUPPORTED
#define ADC2_PRESCALER DIVIDE_ADC_CLOCK_BY_4
#define ADC2_RESOLUTION ADC_12_BIT_RESOLUTION
#define ADC2_REFERENCE ADC_NOT_SUPPORTED

// Channel definitions
// ADC ADC2

// ################## Kamino generator v1.1.0: Generated code! ################
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
