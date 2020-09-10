/**
 * @file gpio_cfg.h
 * @author Miguel Diaz
 * @brief GPIO framework configuration header
 */

/************************
 * Guard                *
 ************************/
#ifndef _GPIO_CFG_H_
#define _GPIO_CFG_H_

/************************
 * Includes             *
 ************************/
// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
#include "MIMXRT1064.h"
#include "fsl_gpio.h"
// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
POT,
USER_LED,
TX,
RX,
// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    GPIO_ELEMENTS_MAX
} Gpio_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// POT
#define POT_PORT PORT_1
#define POT_PIN PIN_26
#define POT_MODE MODE_ALTERNATE_FUNCTION
#define POT_ALT_MODE ALT_MODE_ANALOG
#define POT_OUT_TYPE OTYPE_PUSH_PULL
#define POT_PULL PULL_NOT_AVAILABLE
#define POT_SPEED SPEED_FAST
#define POT_INIT_OUT LOW

// USER_LED
#define USER_LED_PORT PORT_1
#define USER_LED_PIN PIN_9
#define USER_LED_MODE MODE_OUTPUT
#define USER_LED_ALT_MODE ALT_MODE_NONE
#define USER_LED_OUT_TYPE OTYPE_PUSH_PULL
#define USER_LED_PULL PULL_NOT_AVAILABLE
#define USER_LED_SPEED SPEED_FAST
#define USER_LED_INIT_OUT LOW

// TX
#define TX_PORT PORT_1
#define TX_PIN PIN_12
#define TX_MODE MODE_ALTERNATE_FUNCTION
#define TX_ALT_MODE ALT_MODE_ANALOG
#define TX_OUT_TYPE OTYPE_PUSH_PULL
#define TX_PULL PULL_NOT_AVAILABLE
#define TX_SPEED SPEED_FAST
#define TX_INIT_OUT LOW

// RX
#define RX_PORT PORT_1
#define RX_PIN PIN_13
#define RX_MODE MODE_ALTERNATE_FUNCTION
#define RX_ALT_MODE ALT_MODE_ANALOG
#define RX_OUT_TYPE OTYPE_PUSH_PULL
#define RX_PULL PULL_NOT_AVAILABLE
#define RX_SPEED SPEED_FAST
#define RX_INIT_OUT LOW

// ################## Kamino generator v1.2.3: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

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
