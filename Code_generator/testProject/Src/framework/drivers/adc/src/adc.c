/**
 * @file adc.c
 * @author Miguel Diaz
 * @brief ADC module
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

/************************
 * File Scope Variables *
 ************************/
static ADC_HandleTypeDef      AdcCfgStruct[ADC_ELEMENTS_MAX];
static ADC_ChannelConfTypeDef AdcChannelCfgStruct[ADC_ELEMENTS_MAX];

/************************
 * Function Prototypes  *
 ************************/
static void Adc_InitClk (ADC_TypeDef* instance);

/************************
 * Function Definitions *
 ************************/

/**
 * @brief Initialize ADC clock
 * @param instance ADC instance
 */
static void Adc_InitClk (ADC_TypeDef* instance)
{
	if (ADC1 == instance)
	{
		__ADC1_CLK_ENABLE ();
	}
	else if (ADC2 == instance)
	{
		__ADC2_CLK_ENABLE ();
	}
}

/**
 * @brief Initialize ADC pin
 * @param cfgPtr ADC pin configuration structure
 */
void Adc_Init (const Adc_CfgType* cfgPtr)
{
	if (NULL != cfgPtr)
	{
		for (uint32_t adcNum = 0; adcNum < ADC_ELEMENTS_MAX; adcNum++)
		{
			AdcCfgStruct[adcNum].Instance                  = cfgPtr[adcNum].Instance;
			AdcCfgStruct[adcNum].Init.ClockPrescaler       = cfgPtr[adcNum].ClockPrescaler;
			AdcCfgStruct[adcNum].Init.Resolution              = cfgPtr[adcNum].Resolution;
			AdcCfgStruct[adcNum].Init.DataAlign            = cfgPtr[adcNum].DataAlign;
			AdcCfgStruct[adcNum].Init.ScanConvMode         = cfgPtr[adcNum].ScanConvMode;
			AdcCfgStruct[adcNum].Init.EOCSelection         = cfgPtr[adcNum].EOCSelection;
			AdcCfgStruct[adcNum].Init.ContinuousConvMode   = cfgPtr[adcNum].ContinuousConvMode;
			AdcCfgStruct[adcNum].Init.NbrOfConversion      = cfgPtr[adcNum].NbrOfConversion;
			AdcCfgStruct[adcNum].Init.ExternalTrigConv     = cfgPtr[adcNum].ExternalTrigConv;
			AdcCfgStruct[adcNum].Init.ExternalTrigConvEdge = cfgPtr[adcNum].ExternalTrigConvEdge;

			AdcCfgStruct[adcNum].Init.DiscontinuousConvMode = DISABLE;
			AdcCfgStruct[adcNum].Init.NbrOfDiscConversion   = 0;
			AdcCfgStruct[adcNum].Init.DMAContinuousRequests = ENABLE;

			AdcChannelCfgStruct[adcNum].Channel      = cfgPtr[adcNum].Channel;
			AdcChannelCfgStruct[adcNum].Rank         = cfgPtr[adcNum].Rank;
			AdcChannelCfgStruct[adcNum].SamplingTime = cfgPtr[adcNum].SamplingTime;
			AdcChannelCfgStruct[adcNum].Offset       = cfgPtr[adcNum].Offset;

			Adc_InitClk (cfgPtr[adcNum].Instance);
			HAL_ADC_Init (&AdcCfgStruct[adcNum]);
			HAL_ADC_ConfigChannel (&AdcCfgStruct[adcNum], &AdcChannelCfgStruct[adcNum]);
		}
	}
	else
	{
		// TODO: Insert error handling
	}
}

/**
 * @brief Start 1 ADC conversion
 * @param channel ADC channel
 */
void Adc_StartOneShotConversion (Adc_elementsType channel)
{
	AdcCfgStruct[channel].Init.ContinuousConvMode = DISABLE;
	AdcCfgStruct[channel].Init.EOCSelection       = EOC_SINGLE_CONV;
	HAL_ADC_Start (&AdcCfgStruct[channel]);
}

/**
 * @brief Start ADC continuous conversion
 * @param channel ADC channel
 */
void Adc_StartContinuousConversion (Adc_elementsType channel)
{
	// TODO: Implement continuous conversion
	HAL_ADC_Start (&AdcCfgStruct[channel]);
}

/**
 * @brief Start ADC continuous conversion
 * @param channel ADC channel
 * @return ADC conversion value
 */
Adc_DataType Adc_GetConversion (Adc_elementsType channel)
{
	while (HAL_ADC_PollForConversion (&AdcCfgStruct[channel], ADC_MAX_TIMEOUT) != HAL_OK)
	{
		;
		;
	}
	return HAL_ADC_GetValue (&AdcCfgStruct[channel]);
}
