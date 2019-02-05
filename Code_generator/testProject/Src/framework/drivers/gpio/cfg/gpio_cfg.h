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
typedef enum { NOMBRE,
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
PA12,
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
PB9, GPIO_ELEMENTS_MAX } Gpio_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
FWK_GPIO_CFG_DEFINITIONS

// NOMBRE
#define NOMBRE_PORT C
#define NOMBRE_PIN PC13
#define NOMBRE_MODE MODE_INPUT
#define NOMBRE_ALT GPIO_ALT_NONE
#define NOMBRE_PULL PULL_NOT_AVAILABLE
#define NOMBRE_SPEED SPEED_FAST

// PC14
#define PC14_PORT C
#define PC14_PIN PC14
#define PC14_MODE MODE_INPUT
#define PC14_ALT GPIO_ALT_NONE
#define PC14_PULL PULL_NOT_AVAILABLE
#define PC14_SPEED SPEED_FAST

// PC15
#define PC15_PORT C
#define PC15_PIN PC15
#define PC15_MODE MODE_INPUT
#define PC15_ALT GPIO_ALT_NONE
#define PC15_PULL PULL_NOT_AVAILABLE
#define PC15_SPEED SPEED_FAST

// PF0
#define PF0_PORT F
#define PF0_PIN PF0
#define PF0_MODE MODE_INPUT
#define PF0_ALT GPIO_ALT_NONE
#define PF0_PULL PULL_NOT_AVAILABLE
#define PF0_SPEED SPEED_FAST

// PF1
#define PF1_PORT F
#define PF1_PIN PF1
#define PF1_MODE MODE_INPUT
#define PF1_ALT GPIO_ALT_NONE
#define PF1_PULL PULL_NOT_AVAILABLE
#define PF1_SPEED SPEED_FAST

// PC0
#define PC0_PORT C
#define PC0_PIN PC0
#define PC0_MODE MODE_INPUT
#define PC0_ALT GPIO_ALT_NONE
#define PC0_PULL PULL_NOT_AVAILABLE
#define PC0_SPEED SPEED_FAST

// PC1
#define PC1_PORT C
#define PC1_PIN PC1
#define PC1_MODE MODE_INPUT
#define PC1_ALT GPIO_ALT_NONE
#define PC1_PULL PULL_NOT_AVAILABLE
#define PC1_SPEED SPEED_FAST

// PC2
#define PC2_PORT C
#define PC2_PIN PC2
#define PC2_MODE MODE_INPUT
#define PC2_ALT GPIO_ALT_NONE
#define PC2_PULL PULL_NOT_AVAILABLE
#define PC2_SPEED SPEED_FAST

// PC3
#define PC3_PORT C
#define PC3_PIN PC3
#define PC3_MODE MODE_INPUT
#define PC3_ALT GPIO_ALT_NONE
#define PC3_PULL PULL_NOT_AVAILABLE
#define PC3_SPEED SPEED_FAST

// PA0
#define PA0_PORT A
#define PA0_PIN PA0
#define PA0_MODE MODE_INPUT
#define PA0_ALT GPIO_ALT_NONE
#define PA0_PULL PULL_NOT_AVAILABLE
#define PA0_SPEED SPEED_FAST

// PA1
#define PA1_PORT A
#define PA1_PIN PA1
#define PA1_MODE MODE_INPUT
#define PA1_ALT GPIO_ALT_NONE
#define PA1_PULL PULL_NOT_AVAILABLE
#define PA1_SPEED SPEED_FAST

// PA2
#define PA2_PORT A
#define PA2_PIN PA2
#define PA2_MODE MODE_INPUT
#define PA2_ALT GPIO_ALT_NONE
#define PA2_PULL PULL_NOT_AVAILABLE
#define PA2_SPEED SPEED_FAST

// PA3
#define PA3_PORT A
#define PA3_PIN PA3
#define PA3_MODE MODE_INPUT
#define PA3_ALT GPIO_ALT_NONE
#define PA3_PULL PULL_NOT_AVAILABLE
#define PA3_SPEED SPEED_FAST

// PA4
#define PA4_PORT A
#define PA4_PIN PA4
#define PA4_MODE MODE_INPUT
#define PA4_ALT GPIO_ALT_NONE
#define PA4_PULL PULL_NOT_AVAILABLE
#define PA4_SPEED SPEED_FAST

// PA5
#define PA5_PORT A
#define PA5_PIN PA5
#define PA5_MODE MODE_INPUT
#define PA5_ALT GPIO_ALT_NONE
#define PA5_PULL PULL_NOT_AVAILABLE
#define PA5_SPEED SPEED_FAST

// PA6
#define PA6_PORT A
#define PA6_PIN PA6
#define PA6_MODE MODE_INPUT
#define PA6_ALT GPIO_ALT_NONE
#define PA6_PULL PULL_NOT_AVAILABLE
#define PA6_SPEED SPEED_FAST

// PA7
#define PA7_PORT A
#define PA7_PIN PA7
#define PA7_MODE MODE_INPUT
#define PA7_ALT GPIO_ALT_NONE
#define PA7_PULL PULL_NOT_AVAILABLE
#define PA7_SPEED SPEED_FAST

// PC4
#define PC4_PORT C
#define PC4_PIN PC4
#define PC4_MODE MODE_INPUT
#define PC4_ALT GPIO_ALT_NONE
#define PC4_PULL PULL_NOT_AVAILABLE
#define PC4_SPEED SPEED_FAST

// PC5
#define PC5_PORT C
#define PC5_PIN PC5
#define PC5_MODE MODE_INPUT
#define PC5_ALT GPIO_ALT_NONE
#define PC5_PULL PULL_NOT_AVAILABLE
#define PC5_SPEED SPEED_FAST

// PB0
#define PB0_PORT B
#define PB0_PIN PB0
#define PB0_MODE MODE_INPUT
#define PB0_ALT GPIO_ALT_NONE
#define PB0_PULL PULL_NOT_AVAILABLE
#define PB0_SPEED SPEED_FAST

// PB1
#define PB1_PORT B
#define PB1_PIN PB1
#define PB1_MODE MODE_INPUT
#define PB1_ALT GPIO_ALT_NONE
#define PB1_PULL PULL_NOT_AVAILABLE
#define PB1_SPEED SPEED_FAST

// PB2
#define PB2_PORT B
#define PB2_PIN PB2
#define PB2_MODE MODE_INPUT
#define PB2_ALT GPIO_ALT_NONE
#define PB2_PULL PULL_NOT_AVAILABLE
#define PB2_SPEED SPEED_FAST

// PB10
#define PB10_PORT B
#define PB10_PIN PB10
#define PB10_MODE MODE_INPUT
#define PB10_ALT GPIO_ALT_NONE
#define PB10_PULL PULL_NOT_AVAILABLE
#define PB10_SPEED SPEED_FAST

// PB11
#define PB11_PORT B
#define PB11_PIN PB11
#define PB11_MODE MODE_INPUT
#define PB11_ALT GPIO_ALT_NONE
#define PB11_PULL PULL_NOT_AVAILABLE
#define PB11_SPEED SPEED_FAST

// PB12
#define PB12_PORT B
#define PB12_PIN PB12
#define PB12_MODE MODE_INPUT
#define PB12_ALT GPIO_ALT_NONE
#define PB12_PULL PULL_NOT_AVAILABLE
#define PB12_SPEED SPEED_FAST

// PB13
#define PB13_PORT B
#define PB13_PIN PB13
#define PB13_MODE MODE_INPUT
#define PB13_ALT GPIO_ALT_NONE
#define PB13_PULL PULL_NOT_AVAILABLE
#define PB13_SPEED SPEED_FAST

// PB14
#define PB14_PORT B
#define PB14_PIN PB14
#define PB14_MODE MODE_INPUT
#define PB14_ALT GPIO_ALT_NONE
#define PB14_PULL PULL_NOT_AVAILABLE
#define PB14_SPEED SPEED_FAST

// PB15
#define PB15_PORT B
#define PB15_PIN PB15
#define PB15_MODE MODE_INPUT
#define PB15_ALT GPIO_ALT_NONE
#define PB15_PULL PULL_NOT_AVAILABLE
#define PB15_SPEED SPEED_FAST

// PC6
#define PC6_PORT C
#define PC6_PIN PC6
#define PC6_MODE MODE_INPUT
#define PC6_ALT GPIO_ALT_NONE
#define PC6_PULL PULL_NOT_AVAILABLE
#define PC6_SPEED SPEED_FAST

// PC7
#define PC7_PORT C
#define PC7_PIN PC7
#define PC7_MODE MODE_INPUT
#define PC7_ALT GPIO_ALT_NONE
#define PC7_PULL PULL_NOT_AVAILABLE
#define PC7_SPEED SPEED_FAST

// PC8
#define PC8_PORT C
#define PC8_PIN PC8
#define PC8_MODE MODE_INPUT
#define PC8_ALT GPIO_ALT_NONE
#define PC8_PULL PULL_NOT_AVAILABLE
#define PC8_SPEED SPEED_FAST

// PC9
#define PC9_PORT C
#define PC9_PIN PC9
#define PC9_MODE MODE_INPUT
#define PC9_ALT GPIO_ALT_NONE
#define PC9_PULL PULL_NOT_AVAILABLE
#define PC9_SPEED SPEED_FAST

// PA8
#define PA8_PORT A
#define PA8_PIN PA8
#define PA8_MODE MODE_INPUT
#define PA8_ALT GPIO_ALT_NONE
#define PA8_PULL PULL_NOT_AVAILABLE
#define PA8_SPEED SPEED_FAST

// PA9
#define PA9_PORT A
#define PA9_PIN PA9
#define PA9_MODE MODE_INPUT
#define PA9_ALT GPIO_ALT_NONE
#define PA9_PULL PULL_NOT_AVAILABLE
#define PA9_SPEED SPEED_FAST

// PA10
#define PA10_PORT A
#define PA10_PIN PA10
#define PA10_MODE MODE_INPUT
#define PA10_ALT GPIO_ALT_NONE
#define PA10_PULL PULL_NOT_AVAILABLE
#define PA10_SPEED SPEED_FAST

// PA11
#define PA11_PORT A
#define PA11_PIN PA11
#define PA11_MODE MODE_INPUT
#define PA11_ALT GPIO_ALT_NONE
#define PA11_PULL PULL_NOT_AVAILABLE
#define PA11_SPEED SPEED_FAST

// PA12
#define PA12_PORT A
#define PA12_PIN PA12
#define PA12_MODE MODE_INPUT
#define PA12_ALT GPIO_ALT_NONE
#define PA12_PULL PULL_NOT_AVAILABLE
#define PA12_SPEED SPEED_FAST

// PA13
#define PA13_PORT A
#define PA13_PIN PA13
#define PA13_MODE MODE_INPUT
#define PA13_ALT GPIO_ALT_NONE
#define PA13_PULL PULL_NOT_AVAILABLE
#define PA13_SPEED SPEED_FAST

// PA14
#define PA14_PORT A
#define PA14_PIN PA14
#define PA14_MODE MODE_INPUT
#define PA14_ALT GPIO_ALT_NONE
#define PA14_PULL PULL_NOT_AVAILABLE
#define PA14_SPEED SPEED_FAST

// PA15
#define PA15_PORT A
#define PA15_PIN PA15
#define PA15_MODE MODE_INPUT
#define PA15_ALT GPIO_ALT_NONE
#define PA15_PULL PULL_NOT_AVAILABLE
#define PA15_SPEED SPEED_FAST

// PC10
#define PC10_PORT C
#define PC10_PIN PC10
#define PC10_MODE MODE_INPUT
#define PC10_ALT GPIO_ALT_NONE
#define PC10_PULL PULL_NOT_AVAILABLE
#define PC10_SPEED SPEED_FAST

// PC11
#define PC11_PORT C
#define PC11_PIN PC11
#define PC11_MODE MODE_INPUT
#define PC11_ALT GPIO_ALT_NONE
#define PC11_PULL PULL_NOT_AVAILABLE
#define PC11_SPEED SPEED_FAST

// PC12
#define PC12_PORT C
#define PC12_PIN PC12
#define PC12_MODE MODE_INPUT
#define PC12_ALT GPIO_ALT_NONE
#define PC12_PULL PULL_NOT_AVAILABLE
#define PC12_SPEED SPEED_FAST

// PD2
#define PD2_PORT D
#define PD2_PIN PD2
#define PD2_MODE MODE_INPUT
#define PD2_ALT GPIO_ALT_NONE
#define PD2_PULL PULL_NOT_AVAILABLE
#define PD2_SPEED SPEED_FAST

// PB3
#define PB3_PORT B
#define PB3_PIN PB3
#define PB3_MODE MODE_INPUT
#define PB3_ALT GPIO_ALT_NONE
#define PB3_PULL PULL_NOT_AVAILABLE
#define PB3_SPEED SPEED_FAST

// PB4
#define PB4_PORT B
#define PB4_PIN PB4
#define PB4_MODE MODE_INPUT
#define PB4_ALT GPIO_ALT_NONE
#define PB4_PULL PULL_NOT_AVAILABLE
#define PB4_SPEED SPEED_FAST

// PB5
#define PB5_PORT B
#define PB5_PIN PB5
#define PB5_MODE MODE_INPUT
#define PB5_ALT GPIO_ALT_NONE
#define PB5_PULL PULL_NOT_AVAILABLE
#define PB5_SPEED SPEED_FAST

// PB6
#define PB6_PORT B
#define PB6_PIN PB6
#define PB6_MODE MODE_INPUT
#define PB6_ALT GPIO_ALT_NONE
#define PB6_PULL PULL_NOT_AVAILABLE
#define PB6_SPEED SPEED_FAST

// PB7
#define PB7_PORT B
#define PB7_PIN PB7
#define PB7_MODE MODE_INPUT
#define PB7_ALT GPIO_ALT_NONE
#define PB7_PULL PULL_NOT_AVAILABLE
#define PB7_SPEED SPEED_FAST

// PB8
#define PB8_PORT B
#define PB8_PIN PB8
#define PB8_MODE MODE_INPUT
#define PB8_ALT GPIO_ALT_NONE
#define PB8_PULL PULL_NOT_AVAILABLE
#define PB8_SPEED SPEED_FAST

// PB9
#define PB9_PORT B
#define PB9_PIN PB9
#define PB9_MODE MODE_INPUT
#define PB9_ALT GPIO_ALT_NONE
#define PB9_PULL PULL_NOT_AVAILABLE
#define PB9_SPEED SPEED_FAST


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
