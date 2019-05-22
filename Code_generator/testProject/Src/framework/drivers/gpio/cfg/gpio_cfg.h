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
#include "stm32f3xx_hal.h"
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
USER_BUTTON,
PC14,
PC15,
PF0,
PF1,
PC0,
PC1,
PC2,
PC3,
PA0,
PA1,
PA2,
PA3,
PA4,
PA5,
PA6,
PA7,
PC4,
PC5,
PB0,
PB1,
PB2,
PB10,
PB11,
PB12,
PB13,
PB14,
PB15,
PC6,
PC7,
PC8,
PC9,
PA8,
PA9,
PA10,
PA11,
USER_LED,
PA13,
PA14,
PA15,
PC10,
PC11,
PC12,
PD2,
PB3,
PB4,
PB5,
PB6,
PB7,
PB8,
PB9,
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

// ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v0.3.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// USER_BUTTON
#define USER_BUTTON_PORT PORT_C
#define USER_BUTTON_PIN PIN_13
#define USER_BUTTON_MODE MODE_INPUT
#define USER_BUTTON_ALT GPIO_ALT_NONE
#define USER_BUTTON_PULL PULL_UP
#define USER_BUTTON_SPEED SPEED_FAST
#define USER_BUTTON_INIT_OUT HIGH

// PC14
#define PC14_PORT PORT_C
#define PC14_PIN PIN_14
#define PC14_MODE MODE_INPUT
#define PC14_ALT GPIO_ALT_NONE
#define PC14_PULL PULL_NOT_AVAILABLE
#define PC14_SPEED SPEED_FAST
#define PC14_INIT_OUT HIGH

// PC15
#define PC15_PORT PORT_C
#define PC15_PIN PIN_15
#define PC15_MODE MODE_INPUT
#define PC15_ALT GPIO_ALT_NONE
#define PC15_PULL PULL_NOT_AVAILABLE
#define PC15_SPEED SPEED_FAST
#define PC15_INIT_OUT LOW

// PF0
#define PF0_PORT PORT_F
#define PF0_PIN PIN_0
#define PF0_MODE MODE_INPUT
#define PF0_ALT GPIO_ALT_NONE
#define PF0_PULL PULL_NOT_AVAILABLE
#define PF0_SPEED SPEED_FAST
#define PF0_INIT_OUT LOW

// PF1
#define PF1_PORT PORT_F
#define PF1_PIN PIN_1
#define PF1_MODE MODE_INPUT
#define PF1_ALT GPIO_ALT_NONE
#define PF1_PULL PULL_NOT_AVAILABLE
#define PF1_SPEED SPEED_FAST
#define PF1_INIT_OUT LOW

// PC0
#define PC0_PORT PORT_C
#define PC0_PIN PIN_0
#define PC0_MODE MODE_INPUT
#define PC0_ALT GPIO_ALT_NONE
#define PC0_PULL PULL_NOT_AVAILABLE
#define PC0_SPEED SPEED_FAST
#define PC0_INIT_OUT LOW

// PC1
#define PC1_PORT PORT_C
#define PC1_PIN PIN_1
#define PC1_MODE MODE_INPUT
#define PC1_ALT GPIO_ALT_NONE
#define PC1_PULL PULL_NOT_AVAILABLE
#define PC1_SPEED SPEED_FAST
#define PC1_INIT_OUT LOW

// PC2
#define PC2_PORT PORT_C
#define PC2_PIN PIN_2
#define PC2_MODE MODE_INPUT
#define PC2_ALT GPIO_ALT_NONE
#define PC2_PULL PULL_NOT_AVAILABLE
#define PC2_SPEED SPEED_FAST
#define PC2_INIT_OUT LOW

// PC3
#define PC3_PORT PORT_C
#define PC3_PIN PIN_3
#define PC3_MODE MODE_INPUT
#define PC3_ALT GPIO_ALT_NONE
#define PC3_PULL PULL_NOT_AVAILABLE
#define PC3_SPEED SPEED_FAST
#define PC3_INIT_OUT LOW

// PA0
#define PA0_PORT PORT_A
#define PA0_PIN PIN_0
#define PA0_MODE MODE_INPUT
#define PA0_ALT GPIO_ALT_NONE
#define PA0_PULL PULL_NOT_AVAILABLE
#define PA0_SPEED SPEED_FAST
#define PA0_INIT_OUT HIGH

// PA1
#define PA1_PORT PORT_A
#define PA1_PIN PIN_1
#define PA1_MODE MODE_INPUT
#define PA1_ALT GPIO_ALT_NONE
#define PA1_PULL PULL_NOT_AVAILABLE
#define PA1_SPEED SPEED_FAST
#define PA1_INIT_OUT HIGH

// PA2
#define PA2_PORT PORT_A
#define PA2_PIN PIN_2
#define PA2_MODE MODE_INPUT
#define PA2_ALT GPIO_ALT_NONE
#define PA2_PULL PULL_NOT_AVAILABLE
#define PA2_SPEED SPEED_FAST
#define PA2_INIT_OUT LOW

// PA3
#define PA3_PORT PORT_A
#define PA3_PIN PIN_3
#define PA3_MODE MODE_INPUT
#define PA3_ALT GPIO_ALT_NONE
#define PA3_PULL PULL_NOT_AVAILABLE
#define PA3_SPEED SPEED_FAST
#define PA3_INIT_OUT LOW

// PA4
#define PA4_PORT PORT_A
#define PA4_PIN PIN_4
#define PA4_MODE MODE_INPUT
#define PA4_ALT GPIO_ALT_NONE
#define PA4_PULL PULL_NOT_AVAILABLE
#define PA4_SPEED SPEED_FAST
#define PA4_INIT_OUT LOW

// PA5
#define PA5_PORT PORT_A
#define PA5_PIN PIN_5
#define PA5_MODE MODE_INPUT
#define PA5_ALT GPIO_ALT_NONE
#define PA5_PULL PULL_NOT_AVAILABLE
#define PA5_SPEED SPEED_FAST
#define PA5_INIT_OUT LOW

// PA6
#define PA6_PORT PORT_A
#define PA6_PIN PIN_6
#define PA6_MODE MODE_INPUT
#define PA6_ALT GPIO_ALT_NONE
#define PA6_PULL PULL_NOT_AVAILABLE
#define PA6_SPEED SPEED_FAST
#define PA6_INIT_OUT LOW

// PA7
#define PA7_PORT PORT_A
#define PA7_PIN PIN_7
#define PA7_MODE MODE_INPUT
#define PA7_ALT GPIO_ALT_NONE
#define PA7_PULL PULL_NOT_AVAILABLE
#define PA7_SPEED SPEED_FAST
#define PA7_INIT_OUT LOW

// PC4
#define PC4_PORT PORT_C
#define PC4_PIN PIN_4
#define PC4_MODE MODE_INPUT
#define PC4_ALT GPIO_ALT_NONE
#define PC4_PULL PULL_NOT_AVAILABLE
#define PC4_SPEED SPEED_FAST
#define PC4_INIT_OUT LOW

// PC5
#define PC5_PORT PORT_C
#define PC5_PIN PIN_5
#define PC5_MODE MODE_INPUT
#define PC5_ALT GPIO_ALT_NONE
#define PC5_PULL PULL_NOT_AVAILABLE
#define PC5_SPEED SPEED_FAST
#define PC5_INIT_OUT LOW

// PB0
#define PB0_PORT PORT_B
#define PB0_PIN PIN_0
#define PB0_MODE MODE_INPUT
#define PB0_ALT GPIO_ALT_NONE
#define PB0_PULL PULL_NOT_AVAILABLE
#define PB0_SPEED SPEED_FAST
#define PB0_INIT_OUT LOW

// PB1
#define PB1_PORT PORT_B
#define PB1_PIN PIN_1
#define PB1_MODE MODE_INPUT
#define PB1_ALT GPIO_ALT_NONE
#define PB1_PULL PULL_NOT_AVAILABLE
#define PB1_SPEED SPEED_FAST
#define PB1_INIT_OUT LOW

// PB2
#define PB2_PORT PORT_B
#define PB2_PIN PIN_2
#define PB2_MODE MODE_INPUT
#define PB2_ALT GPIO_ALT_NONE
#define PB2_PULL PULL_NOT_AVAILABLE
#define PB2_SPEED SPEED_FAST
#define PB2_INIT_OUT LOW

// PB10
#define PB10_PORT PORT_B
#define PB10_PIN PIN_10
#define PB10_MODE MODE_INPUT
#define PB10_ALT GPIO_ALT_NONE
#define PB10_PULL PULL_NOT_AVAILABLE
#define PB10_SPEED SPEED_FAST
#define PB10_INIT_OUT LOW

// PB11
#define PB11_PORT PORT_B
#define PB11_PIN PIN_11
#define PB11_MODE MODE_INPUT
#define PB11_ALT GPIO_ALT_NONE
#define PB11_PULL PULL_NOT_AVAILABLE
#define PB11_SPEED SPEED_FAST
#define PB11_INIT_OUT LOW

// PB12
#define PB12_PORT PORT_B
#define PB12_PIN PIN_12
#define PB12_MODE MODE_INPUT
#define PB12_ALT GPIO_ALT_NONE
#define PB12_PULL PULL_NOT_AVAILABLE
#define PB12_SPEED SPEED_FAST
#define PB12_INIT_OUT LOW

// PB13
#define PB13_PORT PORT_B
#define PB13_PIN PIN_13
#define PB13_MODE MODE_INPUT
#define PB13_ALT GPIO_ALT_NONE
#define PB13_PULL PULL_NOT_AVAILABLE
#define PB13_SPEED SPEED_FAST
#define PB13_INIT_OUT LOW

// PB14
#define PB14_PORT PORT_B
#define PB14_PIN PIN_14
#define PB14_MODE MODE_INPUT
#define PB14_ALT GPIO_ALT_NONE
#define PB14_PULL PULL_NOT_AVAILABLE
#define PB14_SPEED SPEED_FAST
#define PB14_INIT_OUT LOW

// PB15
#define PB15_PORT PORT_B
#define PB15_PIN PIN_15
#define PB15_MODE MODE_INPUT
#define PB15_ALT GPIO_ALT_NONE
#define PB15_PULL PULL_NOT_AVAILABLE
#define PB15_SPEED SPEED_FAST
#define PB15_INIT_OUT LOW

// PC6
#define PC6_PORT PORT_C
#define PC6_PIN PIN_6
#define PC6_MODE MODE_INPUT
#define PC6_ALT GPIO_ALT_NONE
#define PC6_PULL PULL_NOT_AVAILABLE
#define PC6_SPEED SPEED_FAST
#define PC6_INIT_OUT LOW

// PC7
#define PC7_PORT PORT_C
#define PC7_PIN PIN_7
#define PC7_MODE MODE_INPUT
#define PC7_ALT GPIO_ALT_NONE
#define PC7_PULL PULL_NOT_AVAILABLE
#define PC7_SPEED SPEED_FAST
#define PC7_INIT_OUT LOW

// PC8
#define PC8_PORT PORT_C
#define PC8_PIN PIN_8
#define PC8_MODE MODE_INPUT
#define PC8_ALT GPIO_ALT_NONE
#define PC8_PULL PULL_NOT_AVAILABLE
#define PC8_SPEED SPEED_FAST
#define PC8_INIT_OUT LOW

// PC9
#define PC9_PORT PORT_C
#define PC9_PIN PIN_9
#define PC9_MODE MODE_INPUT
#define PC9_ALT GPIO_ALT_NONE
#define PC9_PULL PULL_NOT_AVAILABLE
#define PC9_SPEED SPEED_FAST
#define PC9_INIT_OUT LOW

// PA8
#define PA8_PORT PORT_A
#define PA8_PIN PIN_8
#define PA8_MODE MODE_INPUT
#define PA8_ALT GPIO_ALT_NONE
#define PA8_PULL PULL_NOT_AVAILABLE
#define PA8_SPEED SPEED_FAST
#define PA8_INIT_OUT LOW

// PA9
#define PA9_PORT PORT_A
#define PA9_PIN PIN_9
#define PA9_MODE MODE_INPUT
#define PA9_ALT GPIO_ALT_NONE
#define PA9_PULL PULL_NOT_AVAILABLE
#define PA9_SPEED SPEED_FAST
#define PA9_INIT_OUT LOW

// PA10
#define PA10_PORT PORT_A
#define PA10_PIN PIN_10
#define PA10_MODE MODE_INPUT
#define PA10_ALT GPIO_ALT_NONE
#define PA10_PULL PULL_NOT_AVAILABLE
#define PA10_SPEED SPEED_FAST
#define PA10_INIT_OUT LOW

// PA11
#define PA11_PORT PORT_A
#define PA11_PIN PIN_11
#define PA11_MODE MODE_INPUT
#define PA11_ALT GPIO_ALT_NONE
#define PA11_PULL PULL_NOT_AVAILABLE
#define PA11_SPEED SPEED_FAST
#define PA11_INIT_OUT LOW

// USER_LED
#define USER_LED_PORT PORT_A
#define USER_LED_PIN PIN_12
#define USER_LED_MODE MODE_OUTPUT
#define USER_LED_ALT GPIO_ALT_NONE
#define USER_LED_PULL PULL_NOT_AVAILABLE
#define USER_LED_SPEED SPEED_FAST
#define USER_LED_INIT_OUT LOW

// PA13
#define PA13_PORT PORT_A
#define PA13_PIN PIN_13
#define PA13_MODE MODE_INPUT
#define PA13_ALT GPIO_ALT_NONE
#define PA13_PULL PULL_NOT_AVAILABLE
#define PA13_SPEED SPEED_FAST
#define PA13_INIT_OUT LOW

// PA14
#define PA14_PORT PORT_A
#define PA14_PIN PIN_14
#define PA14_MODE MODE_INPUT
#define PA14_ALT GPIO_ALT_NONE
#define PA14_PULL PULL_NOT_AVAILABLE
#define PA14_SPEED SPEED_FAST
#define PA14_INIT_OUT LOW

// PA15
#define PA15_PORT PORT_A
#define PA15_PIN PIN_15
#define PA15_MODE MODE_INPUT
#define PA15_ALT GPIO_ALT_NONE
#define PA15_PULL PULL_NOT_AVAILABLE
#define PA15_SPEED SPEED_FAST
#define PA15_INIT_OUT LOW

// PC10
#define PC10_PORT PORT_C
#define PC10_PIN PIN_10
#define PC10_MODE MODE_INPUT
#define PC10_ALT GPIO_ALT_NONE
#define PC10_PULL PULL_NOT_AVAILABLE
#define PC10_SPEED SPEED_FAST
#define PC10_INIT_OUT LOW

// PC11
#define PC11_PORT PORT_C
#define PC11_PIN PIN_11
#define PC11_MODE MODE_INPUT
#define PC11_ALT GPIO_ALT_NONE
#define PC11_PULL PULL_NOT_AVAILABLE
#define PC11_SPEED SPEED_FAST
#define PC11_INIT_OUT LOW

// PC12
#define PC12_PORT PORT_C
#define PC12_PIN PIN_12
#define PC12_MODE MODE_INPUT
#define PC12_ALT GPIO_ALT_NONE
#define PC12_PULL PULL_NOT_AVAILABLE
#define PC12_SPEED SPEED_FAST
#define PC12_INIT_OUT LOW

// PD2
#define PD2_PORT PORT_D
#define PD2_PIN PIN_2
#define PD2_MODE MODE_INPUT
#define PD2_ALT GPIO_ALT_NONE
#define PD2_PULL PULL_NOT_AVAILABLE
#define PD2_SPEED SPEED_FAST
#define PD2_INIT_OUT LOW

// PB3
#define PB3_PORT PORT_B
#define PB3_PIN PIN_3
#define PB3_MODE MODE_INPUT
#define PB3_ALT GPIO_ALT_NONE
#define PB3_PULL PULL_NOT_AVAILABLE
#define PB3_SPEED SPEED_FAST
#define PB3_INIT_OUT LOW

// PB4
#define PB4_PORT PORT_B
#define PB4_PIN PIN_4
#define PB4_MODE MODE_INPUT
#define PB4_ALT GPIO_ALT_NONE
#define PB4_PULL PULL_NOT_AVAILABLE
#define PB4_SPEED SPEED_FAST
#define PB4_INIT_OUT LOW

// PB5
#define PB5_PORT PORT_B
#define PB5_PIN PIN_5
#define PB5_MODE MODE_INPUT
#define PB5_ALT GPIO_ALT_NONE
#define PB5_PULL PULL_NOT_AVAILABLE
#define PB5_SPEED SPEED_FAST
#define PB5_INIT_OUT LOW

// PB6
#define PB6_PORT PORT_B
#define PB6_PIN PIN_6
#define PB6_MODE MODE_INPUT
#define PB6_ALT GPIO_ALT_NONE
#define PB6_PULL PULL_NOT_AVAILABLE
#define PB6_SPEED SPEED_FAST
#define PB6_INIT_OUT LOW

// PB7
#define PB7_PORT PORT_B
#define PB7_PIN PIN_7
#define PB7_MODE MODE_INPUT
#define PB7_ALT GPIO_ALT_NONE
#define PB7_PULL PULL_NOT_AVAILABLE
#define PB7_SPEED SPEED_FAST
#define PB7_INIT_OUT LOW

// PB8
#define PB8_PORT PORT_B
#define PB8_PIN PIN_8
#define PB8_MODE MODE_INPUT
#define PB8_ALT GPIO_ALT_NONE
#define PB8_PULL PULL_NOT_AVAILABLE
#define PB8_SPEED SPEED_FAST
#define PB8_INIT_OUT LOW

// PB9
#define PB9_PORT PORT_B
#define PB9_PIN PIN_9
#define PB9_MODE MODE_INPUT
#define PB9_ALT GPIO_ALT_NONE
#define PB9_PULL PULL_NOT_AVAILABLE
#define PB9_SPEED SPEED_FAST
#define PB9_INIT_OUT LOW

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
