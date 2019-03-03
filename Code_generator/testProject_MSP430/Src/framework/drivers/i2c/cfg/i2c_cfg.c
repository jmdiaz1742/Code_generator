/**
 * @file i2c_cfg.c
 * @author Miguel Diaz
 * @brief I2C framework configuration module
 */

/************************
 * Includes             *
 ************************/
#include "i2c.h"

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
 * @brief I2C initial configuration structures array
 */
const I2C_CfgType I2C_Cfg[I2C_ELEMENTS_MAX] = {{
    LED_CTL_I2C_CHANNEL,
    LED_CTL_I2C_ADDRESSING_MODE,
    LED_CTL_I2C_ADDRESS,
    LED_CTL_I2C_TIMING,
}};

/************************
 * File Scope Variables *
 ************************/

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/
