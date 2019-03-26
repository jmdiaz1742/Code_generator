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
// ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
#include "msp430frxxxx_hal.h"
// ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
P4_3,
P1_4,
P1_5,
P1_6,
P1_7,
P6_0,
P6_1,
P6_2,
P6_3,
P6_4,
P6_5,
P6_6,
P2_4,
P2_5,
P2_6,
P2_7,
P10_2,
P5_0,
P5_1,
P5_2,
P5_3,
P3_0,
P3_1,
P3_2,
PJ_0,
PJ_1,
PJ_2,
PJ_3,
P6_7,
P7_5,
P7_6,
P10_1,
P7_7,
P3_3,
P3_4,
P3_5,
P3_6,
P3_7,
P8_0,
P8_1,
P8_2,
P8_3,
P2_3,
P2_2,
P2_1,
P2_0,
P7_0,
P7_1,
P7_2,
P7_3,
P7_4,
P8_4,
P8_5,
P8_6,
P8_7,
P1_3,
P1_2,
P1_1,
P1_0,
P9_0,
P9_1,
P9_2,
P9_3,
P9_4,
P9_5,
P9_6,
P9_7,
PJ_7,
PJ_6,
PJ_4,
PJ_5,
P5_4,
P5_5,
P5_6,
P5_7,
P4_4,
P4_5,
P4_6,
P4_7,
P10_0,
P4_0,
P4_1,
P4_2,
// ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    GPIO_ELEMENTS_MAX
} Gpio_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
#define PULL_NOT_AVAILABLE GPIO_NOPULL
#define SPEED_FAST GPIO_SPEED_HIGH
#define GPIO_ALT_NONE ((uint8_t)0xFF)
#define MODE_INPUT GPIO_MODE_INPUT
// ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// P4_3
#define P4_3_PORT PORT_4
#define P4_3_PIN P4_3
#define P4_3_MODE MODE_INPUT
#define P4_3_ALT GPIO_ALT_NONE
#define P4_3_PULL PULL_NOT_AVAILABLE
#define P4_3_SPEED SPEED_FAST
#define P4_3_INIT_OUT LOW

// P1_4
#define P1_4_PORT PORT_1
#define P1_4_PIN P1_4
#define P1_4_MODE MODE_INPUT
#define P1_4_ALT GPIO_ALT_NONE
#define P1_4_PULL PULL_NOT_AVAILABLE
#define P1_4_SPEED SPEED_FAST
#define P1_4_INIT_OUT LOW

// P1_5
#define P1_5_PORT PORT_1
#define P1_5_PIN P1_5
#define P1_5_MODE MODE_INPUT
#define P1_5_ALT GPIO_ALT_NONE
#define P1_5_PULL PULL_NOT_AVAILABLE
#define P1_5_SPEED SPEED_FAST
#define P1_5_INIT_OUT LOW

// P1_6
#define P1_6_PORT PORT_1
#define P1_6_PIN P1_6
#define P1_6_MODE MODE_INPUT
#define P1_6_ALT GPIO_ALT_NONE
#define P1_6_PULL PULL_NOT_AVAILABLE
#define P1_6_SPEED SPEED_FAST
#define P1_6_INIT_OUT LOW

// P1_7
#define P1_7_PORT PORT_1
#define P1_7_PIN P1_7
#define P1_7_MODE MODE_INPUT
#define P1_7_ALT GPIO_ALT_NONE
#define P1_7_PULL PULL_NOT_AVAILABLE
#define P1_7_SPEED SPEED_FAST
#define P1_7_INIT_OUT LOW

// P6_0
#define P6_0_PORT PORT_6
#define P6_0_PIN P6_0
#define P6_0_MODE MODE_INPUT
#define P6_0_ALT GPIO_ALT_NONE
#define P6_0_PULL PULL_NOT_AVAILABLE
#define P6_0_SPEED SPEED_FAST
#define P6_0_INIT_OUT LOW

// P6_1
#define P6_1_PORT PORT_6
#define P6_1_PIN P6_1
#define P6_1_MODE MODE_INPUT
#define P6_1_ALT GPIO_ALT_NONE
#define P6_1_PULL PULL_NOT_AVAILABLE
#define P6_1_SPEED SPEED_FAST
#define P6_1_INIT_OUT LOW

// P6_2
#define P6_2_PORT PORT_6
#define P6_2_PIN P6_2
#define P6_2_MODE MODE_INPUT
#define P6_2_ALT GPIO_ALT_NONE
#define P6_2_PULL PULL_NOT_AVAILABLE
#define P6_2_SPEED SPEED_FAST
#define P6_2_INIT_OUT LOW

// P6_3
#define P6_3_PORT PORT_6
#define P6_3_PIN P6_3
#define P6_3_MODE MODE_INPUT
#define P6_3_ALT GPIO_ALT_NONE
#define P6_3_PULL PULL_NOT_AVAILABLE
#define P6_3_SPEED SPEED_FAST
#define P6_3_INIT_OUT LOW

// P6_4
#define P6_4_PORT PORT_6
#define P6_4_PIN P6_4
#define P6_4_MODE MODE_INPUT
#define P6_4_ALT GPIO_ALT_NONE
#define P6_4_PULL PULL_NOT_AVAILABLE
#define P6_4_SPEED SPEED_FAST
#define P6_4_INIT_OUT LOW

// P6_5
#define P6_5_PORT PORT_6
#define P6_5_PIN P6_5
#define P6_5_MODE MODE_INPUT
#define P6_5_ALT GPIO_ALT_NONE
#define P6_5_PULL PULL_NOT_AVAILABLE
#define P6_5_SPEED SPEED_FAST
#define P6_5_INIT_OUT LOW

// P6_6
#define P6_6_PORT PORT_6
#define P6_6_PIN P6_6
#define P6_6_MODE MODE_INPUT
#define P6_6_ALT GPIO_ALT_NONE
#define P6_6_PULL PULL_NOT_AVAILABLE
#define P6_6_SPEED SPEED_FAST
#define P6_6_INIT_OUT LOW

// P2_4
#define P2_4_PORT PORT_2
#define P2_4_PIN P2_4
#define P2_4_MODE MODE_INPUT
#define P2_4_ALT GPIO_ALT_NONE
#define P2_4_PULL PULL_NOT_AVAILABLE
#define P2_4_SPEED SPEED_FAST
#define P2_4_INIT_OUT LOW

// P2_5
#define P2_5_PORT PORT_2
#define P2_5_PIN P2_5
#define P2_5_MODE MODE_INPUT
#define P2_5_ALT GPIO_ALT_NONE
#define P2_5_PULL PULL_NOT_AVAILABLE
#define P2_5_SPEED SPEED_FAST
#define P2_5_INIT_OUT LOW

// P2_6
#define P2_6_PORT PORT_2
#define P2_6_PIN P2_6
#define P2_6_MODE MODE_INPUT
#define P2_6_ALT GPIO_ALT_NONE
#define P2_6_PULL PULL_NOT_AVAILABLE
#define P2_6_SPEED SPEED_FAST
#define P2_6_INIT_OUT LOW

// P2_7
#define P2_7_PORT PORT_2
#define P2_7_PIN P2_7
#define P2_7_MODE MODE_INPUT
#define P2_7_ALT GPIO_ALT_NONE
#define P2_7_PULL PULL_NOT_AVAILABLE
#define P2_7_SPEED SPEED_FAST
#define P2_7_INIT_OUT LOW

// P10_2
#define P10_2_PORT PORT_10
#define P10_2_PIN P10_2
#define P10_2_MODE MODE_INPUT
#define P10_2_ALT GPIO_ALT_NONE
#define P10_2_PULL PULL_NOT_AVAILABLE
#define P10_2_SPEED SPEED_FAST
#define P10_2_INIT_OUT LOW

// P5_0
#define P5_0_PORT PORT_5
#define P5_0_PIN P5_0
#define P5_0_MODE MODE_INPUT
#define P5_0_ALT GPIO_ALT_NONE
#define P5_0_PULL PULL_NOT_AVAILABLE
#define P5_0_SPEED SPEED_FAST
#define P5_0_INIT_OUT LOW

// P5_1
#define P5_1_PORT PORT_5
#define P5_1_PIN P5_1
#define P5_1_MODE MODE_INPUT
#define P5_1_ALT GPIO_ALT_NONE
#define P5_1_PULL PULL_NOT_AVAILABLE
#define P5_1_SPEED SPEED_FAST
#define P5_1_INIT_OUT LOW

// P5_2
#define P5_2_PORT PORT_5
#define P5_2_PIN P5_2
#define P5_2_MODE MODE_INPUT
#define P5_2_ALT GPIO_ALT_NONE
#define P5_2_PULL PULL_NOT_AVAILABLE
#define P5_2_SPEED SPEED_FAST
#define P5_2_INIT_OUT LOW

// P5_3
#define P5_3_PORT PORT_5
#define P5_3_PIN P5_3
#define P5_3_MODE MODE_INPUT
#define P5_3_ALT GPIO_ALT_NONE
#define P5_3_PULL PULL_NOT_AVAILABLE
#define P5_3_SPEED SPEED_FAST
#define P5_3_INIT_OUT LOW

// P3_0
#define P3_0_PORT PORT_3
#define P3_0_PIN P3_0
#define P3_0_MODE MODE_INPUT
#define P3_0_ALT GPIO_ALT_NONE
#define P3_0_PULL PULL_NOT_AVAILABLE
#define P3_0_SPEED SPEED_FAST
#define P3_0_INIT_OUT LOW

// P3_1
#define P3_1_PORT PORT_3
#define P3_1_PIN P3_1
#define P3_1_MODE MODE_INPUT
#define P3_1_ALT GPIO_ALT_NONE
#define P3_1_PULL PULL_NOT_AVAILABLE
#define P3_1_SPEED SPEED_FAST
#define P3_1_INIT_OUT LOW

// P3_2
#define P3_2_PORT PORT_3
#define P3_2_PIN P3_2
#define P3_2_MODE MODE_INPUT
#define P3_2_ALT GPIO_ALT_NONE
#define P3_2_PULL PULL_NOT_AVAILABLE
#define P3_2_SPEED SPEED_FAST
#define P3_2_INIT_OUT LOW

// PJ_0
#define PJ_0_PORT PORT_J
#define PJ_0_PIN PJ_0
#define PJ_0_MODE MODE_INPUT
#define PJ_0_ALT GPIO_ALT_NONE
#define PJ_0_PULL PULL_NOT_AVAILABLE
#define PJ_0_SPEED SPEED_FAST
#define PJ_0_INIT_OUT LOW

// PJ_1
#define PJ_1_PORT PORT_J
#define PJ_1_PIN PJ_1
#define PJ_1_MODE MODE_INPUT
#define PJ_1_ALT GPIO_ALT_NONE
#define PJ_1_PULL PULL_NOT_AVAILABLE
#define PJ_1_SPEED SPEED_FAST
#define PJ_1_INIT_OUT LOW

// PJ_2
#define PJ_2_PORT PORT_J
#define PJ_2_PIN PJ_2
#define PJ_2_MODE MODE_INPUT
#define PJ_2_ALT GPIO_ALT_NONE
#define PJ_2_PULL PULL_NOT_AVAILABLE
#define PJ_2_SPEED SPEED_FAST
#define PJ_2_INIT_OUT LOW

// PJ_3
#define PJ_3_PORT PORT_J
#define PJ_3_PIN PJ_3
#define PJ_3_MODE MODE_INPUT
#define PJ_3_ALT GPIO_ALT_NONE
#define PJ_3_PULL PULL_NOT_AVAILABLE
#define PJ_3_SPEED SPEED_FAST
#define PJ_3_INIT_OUT LOW

// P6_7
#define P6_7_PORT PORT_6
#define P6_7_PIN P6_7
#define P6_7_MODE MODE_INPUT
#define P6_7_ALT GPIO_ALT_NONE
#define P6_7_PULL PULL_NOT_AVAILABLE
#define P6_7_SPEED SPEED_FAST
#define P6_7_INIT_OUT LOW

// P7_5
#define P7_5_PORT PORT_7
#define P7_5_PIN P7_5
#define P7_5_MODE MODE_INPUT
#define P7_5_ALT GPIO_ALT_NONE
#define P7_5_PULL PULL_NOT_AVAILABLE
#define P7_5_SPEED SPEED_FAST
#define P7_5_INIT_OUT LOW

// P7_6
#define P7_6_PORT PORT_7
#define P7_6_PIN P7_6
#define P7_6_MODE MODE_INPUT
#define P7_6_ALT GPIO_ALT_NONE
#define P7_6_PULL PULL_NOT_AVAILABLE
#define P7_6_SPEED SPEED_FAST
#define P7_6_INIT_OUT LOW

// P10_1
#define P10_1_PORT PORT_10
#define P10_1_PIN P10_1
#define P10_1_MODE MODE_INPUT
#define P10_1_ALT GPIO_ALT_NONE
#define P10_1_PULL PULL_NOT_AVAILABLE
#define P10_1_SPEED SPEED_FAST
#define P10_1_INIT_OUT LOW

// P7_7
#define P7_7_PORT PORT_7
#define P7_7_PIN P7_7
#define P7_7_MODE MODE_INPUT
#define P7_7_ALT GPIO_ALT_NONE
#define P7_7_PULL PULL_NOT_AVAILABLE
#define P7_7_SPEED SPEED_FAST
#define P7_7_INIT_OUT LOW

// P3_3
#define P3_3_PORT PORT_3
#define P3_3_PIN P3_3
#define P3_3_MODE MODE_INPUT
#define P3_3_ALT GPIO_ALT_NONE
#define P3_3_PULL PULL_NOT_AVAILABLE
#define P3_3_SPEED SPEED_FAST
#define P3_3_INIT_OUT LOW

// P3_4
#define P3_4_PORT PORT_3
#define P3_4_PIN P3_4
#define P3_4_MODE MODE_INPUT
#define P3_4_ALT GPIO_ALT_NONE
#define P3_4_PULL PULL_NOT_AVAILABLE
#define P3_4_SPEED SPEED_FAST
#define P3_4_INIT_OUT LOW

// P3_5
#define P3_5_PORT PORT_3
#define P3_5_PIN P3_5
#define P3_5_MODE MODE_INPUT
#define P3_5_ALT GPIO_ALT_NONE
#define P3_5_PULL PULL_NOT_AVAILABLE
#define P3_5_SPEED SPEED_FAST
#define P3_5_INIT_OUT LOW

// P3_6
#define P3_6_PORT PORT_3
#define P3_6_PIN P3_6
#define P3_6_MODE MODE_INPUT
#define P3_6_ALT GPIO_ALT_NONE
#define P3_6_PULL PULL_NOT_AVAILABLE
#define P3_6_SPEED SPEED_FAST
#define P3_6_INIT_OUT LOW

// P3_7
#define P3_7_PORT PORT_3
#define P3_7_PIN P3_7
#define P3_7_MODE MODE_INPUT
#define P3_7_ALT GPIO_ALT_NONE
#define P3_7_PULL PULL_NOT_AVAILABLE
#define P3_7_SPEED SPEED_FAST
#define P3_7_INIT_OUT LOW

// P8_0
#define P8_0_PORT PORT_8
#define P8_0_PIN P8_0
#define P8_0_MODE MODE_INPUT
#define P8_0_ALT GPIO_ALT_NONE
#define P8_0_PULL PULL_NOT_AVAILABLE
#define P8_0_SPEED SPEED_FAST
#define P8_0_INIT_OUT LOW

// P8_1
#define P8_1_PORT PORT_8
#define P8_1_PIN P8_1
#define P8_1_MODE MODE_INPUT
#define P8_1_ALT GPIO_ALT_NONE
#define P8_1_PULL PULL_NOT_AVAILABLE
#define P8_1_SPEED SPEED_FAST
#define P8_1_INIT_OUT LOW

// P8_2
#define P8_2_PORT PORT_8
#define P8_2_PIN P8_2
#define P8_2_MODE MODE_INPUT
#define P8_2_ALT GPIO_ALT_NONE
#define P8_2_PULL PULL_NOT_AVAILABLE
#define P8_2_SPEED SPEED_FAST
#define P8_2_INIT_OUT LOW

// P8_3
#define P8_3_PORT PORT_8
#define P8_3_PIN P8_3
#define P8_3_MODE MODE_INPUT
#define P8_3_ALT GPIO_ALT_NONE
#define P8_3_PULL PULL_NOT_AVAILABLE
#define P8_3_SPEED SPEED_FAST
#define P8_3_INIT_OUT LOW

// P2_3
#define P2_3_PORT PORT_2
#define P2_3_PIN P2_3
#define P2_3_MODE MODE_INPUT
#define P2_3_ALT GPIO_ALT_NONE
#define P2_3_PULL PULL_NOT_AVAILABLE
#define P2_3_SPEED SPEED_FAST
#define P2_3_INIT_OUT LOW

// P2_2
#define P2_2_PORT PORT_2
#define P2_2_PIN P2_2
#define P2_2_MODE MODE_INPUT
#define P2_2_ALT GPIO_ALT_NONE
#define P2_2_PULL PULL_NOT_AVAILABLE
#define P2_2_SPEED SPEED_FAST
#define P2_2_INIT_OUT LOW

// P2_1
#define P2_1_PORT PORT_2
#define P2_1_PIN P2_1
#define P2_1_MODE MODE_INPUT
#define P2_1_ALT GPIO_ALT_NONE
#define P2_1_PULL PULL_NOT_AVAILABLE
#define P2_1_SPEED SPEED_FAST
#define P2_1_INIT_OUT LOW

// P2_0
#define P2_0_PORT PORT_2
#define P2_0_PIN P2_0
#define P2_0_MODE MODE_INPUT
#define P2_0_ALT GPIO_ALT_NONE
#define P2_0_PULL PULL_NOT_AVAILABLE
#define P2_0_SPEED SPEED_FAST
#define P2_0_INIT_OUT LOW

// P7_0
#define P7_0_PORT PORT_7
#define P7_0_PIN P7_0
#define P7_0_MODE MODE_INPUT
#define P7_0_ALT GPIO_ALT_NONE
#define P7_0_PULL PULL_NOT_AVAILABLE
#define P7_0_SPEED SPEED_FAST
#define P7_0_INIT_OUT LOW

// P7_1
#define P7_1_PORT PORT_7
#define P7_1_PIN P7_1
#define P7_1_MODE MODE_INPUT
#define P7_1_ALT GPIO_ALT_NONE
#define P7_1_PULL PULL_NOT_AVAILABLE
#define P7_1_SPEED SPEED_FAST
#define P7_1_INIT_OUT LOW

// P7_2
#define P7_2_PORT PORT_7
#define P7_2_PIN P7_2
#define P7_2_MODE MODE_INPUT
#define P7_2_ALT GPIO_ALT_NONE
#define P7_2_PULL PULL_NOT_AVAILABLE
#define P7_2_SPEED SPEED_FAST
#define P7_2_INIT_OUT LOW

// P7_3
#define P7_3_PORT PORT_7
#define P7_3_PIN P7_3
#define P7_3_MODE MODE_INPUT
#define P7_3_ALT GPIO_ALT_NONE
#define P7_3_PULL PULL_NOT_AVAILABLE
#define P7_3_SPEED SPEED_FAST
#define P7_3_INIT_OUT LOW

// P7_4
#define P7_4_PORT PORT_7
#define P7_4_PIN P7_4
#define P7_4_MODE MODE_INPUT
#define P7_4_ALT GPIO_ALT_NONE
#define P7_4_PULL PULL_NOT_AVAILABLE
#define P7_4_SPEED SPEED_FAST
#define P7_4_INIT_OUT LOW

// P8_4
#define P8_4_PORT PORT_8
#define P8_4_PIN P8_4
#define P8_4_MODE MODE_INPUT
#define P8_4_ALT GPIO_ALT_NONE
#define P8_4_PULL PULL_NOT_AVAILABLE
#define P8_4_SPEED SPEED_FAST
#define P8_4_INIT_OUT LOW

// P8_5
#define P8_5_PORT PORT_8
#define P8_5_PIN P8_5
#define P8_5_MODE MODE_INPUT
#define P8_5_ALT GPIO_ALT_NONE
#define P8_5_PULL PULL_NOT_AVAILABLE
#define P8_5_SPEED SPEED_FAST
#define P8_5_INIT_OUT LOW

// P8_6
#define P8_6_PORT PORT_8
#define P8_6_PIN P8_6
#define P8_6_MODE MODE_INPUT
#define P8_6_ALT GPIO_ALT_NONE
#define P8_6_PULL PULL_NOT_AVAILABLE
#define P8_6_SPEED SPEED_FAST
#define P8_6_INIT_OUT LOW

// P8_7
#define P8_7_PORT PORT_8
#define P8_7_PIN P8_7
#define P8_7_MODE MODE_INPUT
#define P8_7_ALT GPIO_ALT_NONE
#define P8_7_PULL PULL_NOT_AVAILABLE
#define P8_7_SPEED SPEED_FAST
#define P8_7_INIT_OUT LOW

// P1_3
#define P1_3_PORT PORT_1
#define P1_3_PIN P1_3
#define P1_3_MODE MODE_INPUT
#define P1_3_ALT GPIO_ALT_NONE
#define P1_3_PULL PULL_NOT_AVAILABLE
#define P1_3_SPEED SPEED_FAST
#define P1_3_INIT_OUT LOW

// P1_2
#define P1_2_PORT PORT_1
#define P1_2_PIN P1_2
#define P1_2_MODE MODE_INPUT
#define P1_2_ALT GPIO_ALT_NONE
#define P1_2_PULL PULL_NOT_AVAILABLE
#define P1_2_SPEED SPEED_FAST
#define P1_2_INIT_OUT LOW

// P1_1
#define P1_1_PORT PORT_1
#define P1_1_PIN P1_1
#define P1_1_MODE MODE_INPUT
#define P1_1_ALT GPIO_ALT_NONE
#define P1_1_PULL PULL_NOT_AVAILABLE
#define P1_1_SPEED SPEED_FAST
#define P1_1_INIT_OUT LOW

// P1_0
#define P1_0_PORT PORT_1
#define P1_0_PIN P1_0
#define P1_0_MODE MODE_INPUT
#define P1_0_ALT GPIO_ALT_NONE
#define P1_0_PULL PULL_NOT_AVAILABLE
#define P1_0_SPEED SPEED_FAST
#define P1_0_INIT_OUT LOW

// P9_0
#define P9_0_PORT PORT_9
#define P9_0_PIN P9_0
#define P9_0_MODE MODE_INPUT
#define P9_0_ALT GPIO_ALT_NONE
#define P9_0_PULL PULL_NOT_AVAILABLE
#define P9_0_SPEED SPEED_FAST
#define P9_0_INIT_OUT LOW

// P9_1
#define P9_1_PORT PORT_9
#define P9_1_PIN P9_1
#define P9_1_MODE MODE_INPUT
#define P9_1_ALT GPIO_ALT_NONE
#define P9_1_PULL PULL_NOT_AVAILABLE
#define P9_1_SPEED SPEED_FAST
#define P9_1_INIT_OUT LOW

// P9_2
#define P9_2_PORT PORT_9
#define P9_2_PIN P9_2
#define P9_2_MODE MODE_INPUT
#define P9_2_ALT GPIO_ALT_NONE
#define P9_2_PULL PULL_NOT_AVAILABLE
#define P9_2_SPEED SPEED_FAST
#define P9_2_INIT_OUT LOW

// P9_3
#define P9_3_PORT PORT_9
#define P9_3_PIN P9_3
#define P9_3_MODE MODE_INPUT
#define P9_3_ALT GPIO_ALT_NONE
#define P9_3_PULL PULL_NOT_AVAILABLE
#define P9_3_SPEED SPEED_FAST
#define P9_3_INIT_OUT LOW

// P9_4
#define P9_4_PORT PORT_9
#define P9_4_PIN P9_4
#define P9_4_MODE MODE_INPUT
#define P9_4_ALT GPIO_ALT_NONE
#define P9_4_PULL PULL_NOT_AVAILABLE
#define P9_4_SPEED SPEED_FAST
#define P9_4_INIT_OUT LOW

// P9_5
#define P9_5_PORT PORT_9
#define P9_5_PIN P9_5
#define P9_5_MODE MODE_INPUT
#define P9_5_ALT GPIO_ALT_NONE
#define P9_5_PULL PULL_NOT_AVAILABLE
#define P9_5_SPEED SPEED_FAST
#define P9_5_INIT_OUT LOW

// P9_6
#define P9_6_PORT PORT_9
#define P9_6_PIN P9_6
#define P9_6_MODE MODE_INPUT
#define P9_6_ALT GPIO_ALT_NONE
#define P9_6_PULL PULL_NOT_AVAILABLE
#define P9_6_SPEED SPEED_FAST
#define P9_6_INIT_OUT LOW

// P9_7
#define P9_7_PORT PORT_9
#define P9_7_PIN P9_7
#define P9_7_MODE MODE_INPUT
#define P9_7_ALT GPIO_ALT_NONE
#define P9_7_PULL PULL_NOT_AVAILABLE
#define P9_7_SPEED SPEED_FAST
#define P9_7_INIT_OUT LOW

// PJ_7
#define PJ_7_PORT PORT_J
#define PJ_7_PIN PJ_7
#define PJ_7_MODE MODE_INPUT
#define PJ_7_ALT GPIO_ALT_NONE
#define PJ_7_PULL PULL_NOT_AVAILABLE
#define PJ_7_SPEED SPEED_FAST
#define PJ_7_INIT_OUT LOW

// PJ_6
#define PJ_6_PORT PORT_J
#define PJ_6_PIN PJ_6
#define PJ_6_MODE MODE_INPUT
#define PJ_6_ALT GPIO_ALT_NONE
#define PJ_6_PULL PULL_NOT_AVAILABLE
#define PJ_6_SPEED SPEED_FAST
#define PJ_6_INIT_OUT LOW

// PJ_4
#define PJ_4_PORT PORT_J
#define PJ_4_PIN PJ_4
#define PJ_4_MODE MODE_INPUT
#define PJ_4_ALT GPIO_ALT_NONE
#define PJ_4_PULL PULL_NOT_AVAILABLE
#define PJ_4_SPEED SPEED_FAST
#define PJ_4_INIT_OUT LOW

// PJ_5
#define PJ_5_PORT PORT_J
#define PJ_5_PIN PJ_5
#define PJ_5_MODE MODE_INPUT
#define PJ_5_ALT GPIO_ALT_NONE
#define PJ_5_PULL PULL_NOT_AVAILABLE
#define PJ_5_SPEED SPEED_FAST
#define PJ_5_INIT_OUT LOW

// P5_4
#define P5_4_PORT PORT_5
#define P5_4_PIN P5_4
#define P5_4_MODE MODE_INPUT
#define P5_4_ALT GPIO_ALT_NONE
#define P5_4_PULL PULL_NOT_AVAILABLE
#define P5_4_SPEED SPEED_FAST
#define P5_4_INIT_OUT LOW

// P5_5
#define P5_5_PORT PORT_5
#define P5_5_PIN P5_5
#define P5_5_MODE MODE_INPUT
#define P5_5_ALT GPIO_ALT_NONE
#define P5_5_PULL PULL_NOT_AVAILABLE
#define P5_5_SPEED SPEED_FAST
#define P5_5_INIT_OUT LOW

// P5_6
#define P5_6_PORT PORT_5
#define P5_6_PIN P5_6
#define P5_6_MODE MODE_INPUT
#define P5_6_ALT GPIO_ALT_NONE
#define P5_6_PULL PULL_NOT_AVAILABLE
#define P5_6_SPEED SPEED_FAST
#define P5_6_INIT_OUT LOW

// P5_7
#define P5_7_PORT PORT_5
#define P5_7_PIN P5_7
#define P5_7_MODE MODE_INPUT
#define P5_7_ALT GPIO_ALT_NONE
#define P5_7_PULL PULL_NOT_AVAILABLE
#define P5_7_SPEED SPEED_FAST
#define P5_7_INIT_OUT LOW

// P4_4
#define P4_4_PORT PORT_4
#define P4_4_PIN P4_4
#define P4_4_MODE MODE_INPUT
#define P4_4_ALT GPIO_ALT_NONE
#define P4_4_PULL PULL_NOT_AVAILABLE
#define P4_4_SPEED SPEED_FAST
#define P4_4_INIT_OUT LOW

// P4_5
#define P4_5_PORT PORT_4
#define P4_5_PIN P4_5
#define P4_5_MODE MODE_INPUT
#define P4_5_ALT GPIO_ALT_NONE
#define P4_5_PULL PULL_NOT_AVAILABLE
#define P4_5_SPEED SPEED_FAST
#define P4_5_INIT_OUT LOW

// P4_6
#define P4_6_PORT PORT_4
#define P4_6_PIN P4_6
#define P4_6_MODE MODE_INPUT
#define P4_6_ALT GPIO_ALT_NONE
#define P4_6_PULL PULL_NOT_AVAILABLE
#define P4_6_SPEED SPEED_FAST
#define P4_6_INIT_OUT LOW

// P4_7
#define P4_7_PORT PORT_9
#define P4_7_PIN P4_7
#define P4_7_MODE MODE_INPUT
#define P4_7_ALT GPIO_ALT_NONE
#define P4_7_PULL PULL_NOT_AVAILABLE
#define P4_7_SPEED SPEED_FAST
#define P4_7_INIT_OUT LOW

// P10_0
#define P10_0_PORT PORT_10
#define P10_0_PIN P10_0
#define P10_0_MODE MODE_INPUT
#define P10_0_ALT GPIO_ALT_NONE
#define P10_0_PULL PULL_NOT_AVAILABLE
#define P10_0_SPEED SPEED_FAST
#define P10_0_INIT_OUT LOW

// P4_0
#define P4_0_PORT PORT_4
#define P4_0_PIN P4_0
#define P4_0_MODE MODE_INPUT
#define P4_0_ALT GPIO_ALT_NONE
#define P4_0_PULL PULL_NOT_AVAILABLE
#define P4_0_SPEED SPEED_FAST
#define P4_0_INIT_OUT LOW

// P4_1
#define P4_1_PORT PORT_4
#define P4_1_PIN P4_1
#define P4_1_MODE MODE_INPUT
#define P4_1_ALT GPIO_ALT_NONE
#define P4_1_PULL PULL_NOT_AVAILABLE
#define P4_1_SPEED SPEED_FAST
#define P4_1_INIT_OUT LOW

// P4_2
#define P4_2_PORT PORT_4
#define P4_2_PIN P4_2
#define P4_2_MODE MODE_INPUT
#define P4_2_ALT GPIO_ALT_NONE
#define P4_2_PULL PULL_NOT_AVAILABLE
#define P4_2_SPEED SPEED_FAST
#define P4_2_INIT_OUT LOW

// ################## Kamino generator v0.3.0: Generated code! ################
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
