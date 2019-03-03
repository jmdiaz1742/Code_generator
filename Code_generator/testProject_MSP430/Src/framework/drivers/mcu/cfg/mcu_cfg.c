/**
 * @file mcu_cfg.c
 * @author Miguel Diaz
 * @brief MCU framework configuration module
 */

/************************
 * Includes             *
 ************************/
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
/**
 * @brief MCU initial configuration structure
 */
Mcu_SysCfgType Mcu_SysCfg = {MCU_OSC_TYPE,
                             MCU_PLL_STATE,
                             MCU_PLL_SOURCE,
                             MCU_PLLMUL,
                             MCU_CLOCK_TYPE,
                             MCU_SYS_CLOCK_SOURCE,
                             MCU_AHB_CLOCK_DIVIDER,
                             MCU_APB1_CLOCK_DIVIDER,
                             MCU_APB2_CLOCK_DIVIDER};

/************************
 * File Scope Variables *
 ************************/

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/
