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
// ################## Kamino generator v1.0.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
#include "stm32f3xx_hal.h"
// ################## Kamino generator v1.0.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v1.0.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
USER_BUTTON,
PC3,
USER_LED,
// ################## Kamino generator v1.0.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    GPIO_ELEMENTS_MAX
} Gpio_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v1.0.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.0.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v1.0.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// USER_BUTTON
#define USER_BUTTON_PORT PORT_C
#define USER_BUTTON_PIN PIN_13
#define USER_BUTTON_MODE MODE_INPUT
#define USER_BUTTON_ALT GPIO_ALT_NONE
#define USER_BUTTON_PULL PULL_UP
#define USER_BUTTON_SPEED SPEED_FAST
#define USER_BUTTON_INIT_OUT LOW

// PC3
#define PC3_PORT PORT_C
#define PC3_PIN PIN_3
#define PC3_MODE MODE_OUTPUT
#define PC3_ALT GPIO_ALT_NONE
#define PC3_PULL PULL_NOT_AVAILABLE
#define PC3_SPEED SPEED_FAST
#define PC3_INIT_OUT LOW

// USER_LED
#define USER_LED_PORT PORT_A
#define USER_LED_PIN PIN_12
#define USER_LED_MODE MODE_OUTPUT
#define USER_LED_ALT GPIO_ALT_NONE
#define USER_LED_PULL PULL_NOT_AVAILABLE
#define USER_LED_SPEED SPEED_FAST
#define USER_LED_INIT_OUT LOW

// ################## Kamino generator v1.0.0: Generated code! ################
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
