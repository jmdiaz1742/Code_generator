/**
 * @file mcu.c
 * @author Miguel Diaz
 * @brief MCU framework module
 */

/************************
 * Includes             *
 ************************/
#include "frameworkCommon.h"
#include "mcu.h"

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

/************************
 * Function Prototypes  *
 ************************/
void        MCU_Init(Mcu_SysCfgType *cfgPtr);
static void Mcu_ClkInit(Mcu_ClkCfgType *cfgPtr);
static void Mcu_PeriphClkInit(Mcu_ClkPeriphCfgType *cfgPtr);
static void Mcu_lldInit(Mcu_lldCfgType *cfgPtr);
static void Mcu_ErrorHandler(framework_McuErrorType errorStatus);

/************************
 * Function Definitions *
 ************************/
/**
 * @brief Initialize MCU
 * @param cfgPtr System configuration structure
 */
void MCU_Init(Mcu_SysCfgType *cfgPtr)
{
    framework_McuErrorType errorStatus = MCU_ERROR_OK;
    RCC_OscInitTypeDef     RCC_OscInitStruct;
    RCC_ClkInitTypeDef     RCC_ClkInitStruct;

    if (NULL != cfgPtr)
    {
        RCC_OscInitStruct.OscillatorType = cfgPtr->OscillatorType;
        RCC_OscInitStruct.PLL.PLLState   = cfgPtr->PLLState;
        RCC_OscInitStruct.PLL.PLLSource  = cfgPtr->PLLSource;
        RCC_OscInitStruct.PLL.PLLMUL     = cfgPtr->PLLMUL;

        RCC_ClkInitStruct.ClockType      = cfgPtr->ClockType;
        RCC_ClkInitStruct.SYSCLKSource   = cfgPtr->SYSCLKSource;
        RCC_ClkInitStruct.AHBCLKDivider  = cfgPtr->AHBCLKDivider;
        RCC_ClkInitStruct.APB1CLKDivider = cfgPtr->APB1CLKDivider;
        RCC_ClkInitStruct.APB2CLKDivider = cfgPtr->APB2CLKDivider;

        errorStatus = HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_2);
        errorStatus = HAL_RCC_OscConfig(&RCC_OscInitStruct);
    }
    else
    {
        errorStatus = MCU_ERROR_NULL_PTR;
    }
    if (MCU_ERROR_OK != errorStatus)
    {
        Mcu_ErrorHandler(errorStatus);
    }
}

/**
 * @brief Initialize Clock
 * @param cfgPtr Clock configuration structure
 */
static void Mcu_ClkInit(Mcu_ClkCfgType *cfgPtr)
{
    // TODO: Complete this function
}

/**
 * @brief Peripherals initialization
 * @param cfgPtr Peripherals configuration structure
 */
static void Mcu_PeriphClkInit(Mcu_ClkPeriphCfgType *cfgPtr)
{
    // TODO: Complete this function
}

/**
 * @brief LLD initialization
 * @param cfgPtr LLD configuration structure
 */
static void Mcu_lldInit(Mcu_lldCfgType *cfgPtr)
{
    // TODO: Complete this function
}

/**
 * @brief Microcontroller error handling
 * @param errorStatus Error status
 */
static void Mcu_ErrorHandler(framework_McuErrorType errorStatus)
{
    // TODO: Complete this function
}
