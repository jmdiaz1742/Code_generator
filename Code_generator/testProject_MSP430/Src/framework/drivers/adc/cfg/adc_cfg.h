/**
 * @file adc_cfg.h
 * @author Miguel Diaz
 * @brief ADC module configuration header
 */

/************************
 * Guard                *
 ************************/
#ifndef _ADC_CFG_H_
#define _ADC_CFG_H_

/************************
 * Includes             *
 ************************/
#include "stm32f3xx_hal.h"
#include "stm32f3xx_hal_cortex.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief STM32 ADC elements enumerator
 */
typedef enum { ADC_POT, ADC_ELEMENTS_MAX } Adc_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
#define ADC_MAX_TIMEOUT HAL_MAX_DELAY

// ADC_POT
#define ADC_POT_INSTANCE ADC1
#define ADC_POT_CLK_PRESCALER ADC_CLOCKPRESCALER_PCLK_DIV2
#define ADC_POT_RESOLUTION ADC_RESOLUTION8b
#define ADC_POT_DATA_ALIGN ADC_DATAALIGN_RIGHT
#define ADC_POT_SCAN_CONV_MODE DISABLE
#define ADC_POT_EOC_SELECTION EOC_SINGLE_CONV
#define ADC_POT_CONT_CONV_MODE DISABLE
#define ADC_POT_NUMBER_OF_CONV 1
#define ADC_POT_EXT_TRIG_CONV ADC_EXTERNALTRIGCONV_T1_CC1
#define ADC_POT_EXT_TRIG_CONV_EDGE ADC_EXTERNALTRIGCONVEDGE_NONE
#define ADC_POT_CHANNEL ADC_CHANNEL_1
#define ADC_POT_RANK 1
#define ADC_POT_SAMPLING_TIME ADC_SAMPLETIME_61CYCLES_5
#define ADC_POT_OFFSET 0

// ADC_TEMP_SENSOR
#define ADC_TEMP_SENSOR_INSTANCE ADC1
#define ADC_TEMP_SENSOR_CLK_PRESCALER ADC_CLOCKPRESCALER_PCLK_DIV2
#define ADC_TEMP_SENSOR_RESOLUTION ADC_RESOLUTION12b
#define ADC_TEMP_SENSOR_DATA_ALIGN ADC_DATAALIGN_RIGHT
#define ADC_TEMP_SENSOR_SCAN_CONV_MODE DISABLE
#define ADC_TEMP_SENSOR_EOC_SELECTION DISABLE
#define ADC_TEMP_SENSOR_CONT_CONV_MODE ENABLE
#define ADC_TEMP_SENSOR_NUMBER_OF_CONV 1
#define ADC_TEMP_SENSOR_EXT_TRIG_CONV ADC_EXTERNALTRIGCONV_T1_CC1
#define ADC_TEMP_SENSOR_EXT_TRIG_CONV_EDGE ADC_EXTERNALTRIGCONVEDGE_NONE
#define ADC_TEMP_SENSOR_CHANNEL ADC_CHANNEL_2
#define ADC_TEMP_SENSOR_RANK 1
#define ADC_TEMP_SENSOR_SAMPLING_TIME ADC_SAMPLETIME_61CYCLES_5
#define ADC_TEMP_SENSOR_OFFSET 0

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
