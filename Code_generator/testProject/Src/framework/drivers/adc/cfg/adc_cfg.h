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
// ################## Kamino generator v1.0.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
#include "stm32f3xx_hal.h"
// ################## Kamino generator v1.0.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v1.0.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
MyAdc,
// ################## Kamino generator v1.0.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    ADC_ELEMENTS_MAX
} Adc_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v1.0.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.0.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v1.0.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// MyAdc
#define MyAdc_SAMPLE ADC_SAMPLETIME_2CYCLES_5
#define MyAdc_CLOCK ADC_NOT_SUPPORTED
#define MyAdc_JUSTIFICATION ADC_DATAALIGN_LEFT
#define MyAdc_PRESCALER ADC_CLOCK_SYNC_PCLK_DIV2
#define MyAdc_RESOLUTION ADC_RESOLUTION10b
#define MyAdc_REFERENCE ADC_NOT_SUPPORTED

// ################## Kamino generator v1.0.3: Generated code! ################
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
