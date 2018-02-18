/**
 * @file gpio_cfg.h
 * @author Miguel Diaz
 * @brief GPIO gramework configuration header
 */

/************************
 * Guard                *
 ************************/
#ifndef _GPIO_CFG_H_
#define _GPIO_CFG_H_

/************************
 * Includes             *
 ************************/
#include "stm32f3xx_hal.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum {GPIO_CFG_ELEMENTS} Gpio_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
#define GPIO_ALT_NONE ((uint8_t)0xFF)

GPIO_CFG_DEFINITIONS

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

#endif /* _GPIO_CFG_H_ */
