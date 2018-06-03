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
FWK_GPIO_INCLUDES

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum { LED1,
POT1,
SW1,
NC1,
POT1, GPIO_ELEMENTS_MAX } Gpio_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
FWK_GPIO_CFG_DEFINITIONS

#define LED1_PORT B
#define LED1_PIN PB0
#define LED1_MODE MODE_OUTPUT
#define LED1_ALT GPIO_ALT_NONE
#define LED1_PULL PULL_UP
#define LED1_SPEED SPEED_MEDIUM

#define POT1_PORT B
#define POT1_PIN PB1
#define POT1_MODE MODE_ANALOG
#define POT1_ALT GPIO_ALT_NONE
#define POT1_PULL PULL_DOWN
#define POT1_SPEED SPEED_FAST

#define SW1_PORT B
#define SW1_PIN PB2
#define SW1_MODE MODE_INPUT
#define SW1_ALT GPIO_ALT_NONE
#define SW1_PULL PULL_NOT_AVAILABLE
#define SW1_SPEED SPEED_FAST

#define NC1_PORT B
#define NC1_PIN PB6
#define NC1_MODE MODE_OUTPUT
#define NC1_ALT GPIO_ALT_NONE
#define NC1_PULL PULL_UP
#define NC1_SPEED SPEED_FAST

#define POT1_PORT C
#define POT1_PIN PC1
#define POT1_MODE MODE_INPUT
#define POT1_ALT GPIO_ALT_NONE
#define POT1_PULL PULL_NOT_AVAILABLE
#define POT1_SPEED SPEED_MEDIUM


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
