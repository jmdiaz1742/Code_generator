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
// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
#include "stm32f3xx_hal.h"
// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
FirstAdc,
MyAdc,
// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    ADC_ELEMENTS_MAX
} Adc_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// FirstAdc
#define FirstAdc_NAME ADC1
#define FirstAdc_SAMPLE ADC_SAMPLETIME_1CYCLE_5
#define FirstAdc_CLOCK ADC_NOT_SUPPORTED
#define FirstAdc_JUSTIFICATION ADC_DATAALIGN_RIGHT
#define FirstAdc_PRESCALER ADC_CLOCK_SYNC_PCLK_DIV1
#define FirstAdc_RESOLUTION ADC_RESOLUTION12b
#define FirstAdc_REFERENCE ADC_NOT_SUPPORTED

// MyAdc
#define MyAdc_NAME ADC2
#define MyAdc_SAMPLE ADC_SAMPLETIME_2CYCLES_5
#define MyAdc_CLOCK ADC_NOT_SUPPORTED
#define MyAdc_JUSTIFICATION ADC_DATAALIGN_LEFT
#define MyAdc_PRESCALER ADC_CLOCK_SYNC_PCLK_DIV2
#define MyAdc_RESOLUTION ADC_RESOLUTION10b
#define MyAdc_REFERENCE ADC_NOT_SUPPORTED

// Channel definitions
// ADC ADC1
#define ADC_CHANNEL_1 ADC_CHANNEL_1
// ADC ADC2
#define TEMP_CHANNEL ADC_CHANNEL_4

// ################## Kamino generator v1.2.4: Generated code! ################
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
