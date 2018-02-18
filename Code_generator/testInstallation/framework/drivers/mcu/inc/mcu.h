/**
 * @file mcu.h
 * @author Miguel Diaz
 * @brief MCU framework module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _MCU_H_
#define _MCU_H_

/************************
 * Includes             *
 ************************/
#include "mcu_cfg.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief MCU errors
 */
typedef enum {
	// We start our error definitions from the last element in the HAL error
	// enum HAL_StatusTypeDef. For more information see sm32f3xx_hal_def.h
	MCU_ERROR_OK       = HAL_OK,
	MCU_ERROR_NULL_PTR = HAL_TIMEOUT + 1,
	MCU_ERROR_CLK_CFG,
	MCU_ERROR_OSC_CFG
} framework_McuErrorType;

/**
 * @brief Clock configuration structure
 */
typedef struct
{
	// TODO: Define Mcu_ClkCfgType
} Mcu_ClkCfgType;

/**
 * @brief LLD configuration structure
 */
typedef struct
{
	// TODO: Define Mcu_lldCfgType
} Mcu_lldCfgType;

/**
 * @brief Peripherals clock configuration structure
 */
typedef struct
{
	// TODO: Define Mcu_ClkPeriphCfgType
} Mcu_ClkPeriphCfgType;

/**
 * @brief Main clock configuration structure
 */
typedef struct
{
	uint32_t OscillatorType;
	uint32_t PLLState;
	uint32_t PLLSource;
	uint32_t PLLMUL;
	uint32_t ClockType;
	uint32_t SYSCLKSource;
	uint32_t AHBCLKDivider;
	uint32_t APB1CLKDivider;
	uint32_t APB2CLKDivider;
} Mcu_SysCfgType;

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
Mcu_SysCfgType Mcu_SysCfg;

/************************
 * Public Functions     *
 ************************/
void MCU_Init (Mcu_SysCfgType *cfgPtr);

#endif /* _MCU_H_ */
