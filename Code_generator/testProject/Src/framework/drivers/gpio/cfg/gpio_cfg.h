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
// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
#include "stm32f3xx_hal.h"
// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
USER_BUTTON,
test,
PA0,
USER_LED,
// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    GPIO_ELEMENTS_MAX
} Gpio_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v1.2.4: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// USER_BUTTON
#define USER_BUTTON_PORT PORT_C
#define USER_BUTTON_PIN PIN_13
#define USER_BUTTON_MODE MODE_INPUT
#define USER_BUTTON_ALT_MODE ALT_MODE_NONE
#define USER_BUTTON_OUT_TYPE OTYPE_PUSH_PULL
#define USER_BUTTON_PULL PULL_UP
#define USER_BUTTON_SPEED SPEED_FAST
#define USER_BUTTON_INIT_OUT LOW

// test
#define test_PORT PORT_C
#define test_PIN PIN_14
#define test_MODE MODE_INPUT
#define test_ALT_MODE ALT_MODE_NONE
#define test_OUT_TYPE OTYPE_PUSH_PULL
#define test_PULL PULL_NOT_AVAILABLE
#define test_SPEED SPEED_FAST
#define test_INIT_OUT LOW

// PA0
#define PA0_PORT PORT_A
#define PA0_PIN PIN_0
#define PA0_MODE MODE_ALTERNATE_FUNCTION
#define PA0_ALT_MODE ALT_MODE_NONE
#define PA0_OUT_TYPE OTYPE_PUSH_PULL
#define PA0_PULL PULL_NOT_AVAILABLE
#define PA0_SPEED SPEED_FAST
#define PA0_INIT_OUT LOW

// USER_LED
#define USER_LED_PORT PORT_A
#define USER_LED_PIN PIN_12
#define USER_LED_MODE MODE_OUTPUT
#define USER_LED_ALT_MODE ALT_MODE_NONE
#define USER_LED_OUT_TYPE OTYPE_PUSH_PULL
#define USER_LED_PULL PULL_NOT_AVAILABLE
#define USER_LED_SPEED SPEED_FAST
#define USER_LED_INIT_OUT LOW

// ################## Kamino generator v1.2.4: Generated code! ################
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
