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
SWITCH1,
NC1,
PB6,
POT1, GPIO_ELEMENTS_MAX } Gpio_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
FWK_GPIO_CFG_DEFINITIONS

#define LED1_PORT PORT_B
#define LED1_PIN PIN_PB0
#define LED1_MODE MODE_OUTPUT
#define LED1_ALT GPIO_ALT_NONE
#define LED1_PULL PULL_UP
#define LED1_SPEED SPEED_MEDIUM

#define SWITCH1_PORT PORT_B
#define SWITCH1_PIN PIN_PB1
#define SWITCH1_MODE MODE_INPUT
#define SWITCH1_ALT GPIO_ALT_NONE
#define SWITCH1_PULL PULL_DOWN
#define SWITCH1_SPEED SPEED_FAST

#define NC1_PORT PORT_B
#define NC1_PIN PIN_PB2
#define NC1_MODE MODE_INPUT
#define NC1_ALT GPIO_ALT_NONE
#define NC1_PULL PULL_NOT_AVAILABLE
#define NC1_SPEED SPEED_FAST

#define PB6_PORT PORT_B
#define PB6_PIN PIN_PB6
#define PB6_MODE MODE_INPUT
#define PB6_ALT GPIO_ALT_NONE
#define PB6_PULL PULL_UP
#define PB6_SPEED SPEED_FAST

#define POT1_PORT PORT_C
#define POT1_PIN PIN_PC1
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
