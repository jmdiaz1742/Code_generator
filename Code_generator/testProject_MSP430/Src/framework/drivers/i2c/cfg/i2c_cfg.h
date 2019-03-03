/**
 * @file i2c_cfg.h
 * @author Miguel Diaz
 * @brief I2C framework configuration module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _I2C_CFG_H_
#define _I2C_CFG_H_

/************************
 * Includes             *
 ************************/
#include "stm32f3xx_hal.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief I2C elements
 */
typedef enum { LED_I2C, I2C_ELEMENTS_MAX } I2C_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
#define I2C_TRANSMISSION_TIMEOUT HAL_MAX_DELAY

#define LED_CTL_I2C_CHANNEL I2C1
#define LED_CTL_I2C_ADDRESSING_MODE I2C_ADDRESSINGMODE_7BIT
#define LED_CTL_I2C_ADDRESS 0x01
#define LED_CTL_I2C_TIMING 0x20000500

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
extern int i2c_cfgmainFunc (void);

#endif /* _I2C_CFG_H_ */
