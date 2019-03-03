/**
 * @file adc.c
 * @author Miguel Diaz
 * @brief ADC module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _ADC_H_
#define _ADC_H_

/************************
 * Includes             *
 ************************/
#include "frameworkCommon.h"
#include "adc_cfg.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief ADC IDs
 */
typedef enum {
	ADC_1,
	ADC_2,
	ADC_3,
	ADC_4,
	ADC_5,
	ADC_6,
	ADC_7,
	ADC_8,
	ADC_9,
	ADC_10,
	ADC_11,
	ADC_12,
	ADC_13,
	ADC_14,
	ADC_15,
	ADC_16,
	ADC_17,
	ADC_18,
	ADC_ID_MAX
} Adc_IdType;

/**
 * @brief ADC configuration structure
 */
typedef struct
{
	ADC_TypeDef *Instance;
	uint32_t     ClockPrescaler;
	uint32_t     Resolution;
	uint32_t     DataAlign;
	uint32_t     ScanConvMode;
	uint32_t     EOCSelection;
	uint32_t     ContinuousConvMode;
	uint32_t     NbrOfConversion;
	uint32_t     ExternalTrigConv;
	uint32_t     ExternalTrigConvEdge;
	uint32_t     Channel;
	uint32_t     Rank;
	uint32_t     SamplingTime;
	uint32_t     Offset;
} Adc_CfgType;

/**
 * @brief ADC data type
 */
typedef uint32_t Adc_DataType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/

/************************
 * Public Constants     *
 ************************/

/************************
 * Public Calibrations  *
 ************************/

/************************
 * Public Variables     *
 ************************/

const Adc_CfgType Adc_Cfg[ADC_ELEMENTS_MAX];

/************************
 * Public Functions     *
 ************************/
void         Adc_Init (const Adc_CfgType *cfgPtr);
void         Adc_StartOneShotConversion (Adc_elementsType channel);
void         Adc_StartContinuousConversion (Adc_elementsType channel);
Adc_DataType Adc_GetConversion (Adc_elementsType channel);

#endif /* _ADC_H_ */
