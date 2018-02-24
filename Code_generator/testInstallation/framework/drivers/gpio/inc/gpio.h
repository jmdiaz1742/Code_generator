/**
 * @file gpio.h
 * @author Miguel Diaz
 * @brief GPIO framework module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _GPIO_H_
#define _GPIO_H_

/************************
 * Includes             *
 ************************/
#include "frameworkCommon.h"
#include "gpio_cfg.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO errors
 */
typedef enum {
    // We start our error definitions from the last element in the HAL error
    // enum framework_McuErrorType. For more information see mcu.h
    GPIO_ERROR_OK       = HAL_OK,
    GPIO_ERROR_NULL_PTR = 7
} framework_GpioErrorType;

/**
 * @brief GPIO port IDs
 */
typedef enum { PORT_A, PORT_B, PORT_C, PORT_D, PORT_F, PORT_MAX } Gpio_portIdtype;

/**
 * @brief GPIO pin IDs
 */
typedef enum {
    PIN_0 = 0,
    PIN_1,
    PIN_2,
    PIN_3,
    PIN_4,
    PIN_5,
    PIN_6,
    PIN_7,
    PIN_8,
    PIN_9,
    PIN_10,
    PIN_11,
    PIN_12,
    PIN_13,
    PIN_14,
    PIN_15,
    PIN_MAX
} Gpio_pinIdtype;

/**
 * @brief GPIO pinstate
 */
typedef GPIO_PinState Gpio_pinStateType;

/**
 * @brief GPIO data type
 */
typedef uint16_t Gpio_dataType;

/**
 * @brief GPIO configuration structure
 */
typedef struct
{
    Gpio_portIdtype Port;
    uint32_t        Pin;
    uint32_t        Mode;
    uint8_t         Alternate;
    uint32_t        Pull;
    uint32_t        Speed;
} Gpio_CfgType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/

/************************
 * Public Constants     *
 ************************/

/************************
 * Public Calibrations  *
 ************************/

/************************
 * Public Variables     *
 ************************/
const Gpio_CfgType Gpio_Cfg[GPIO_ELEMENTS_MAX];

/************************
 * Public Functions     *
 ************************/
void              GPIO_Init(const Gpio_CfgType *cfgPtr);
void              Gpio_WritePort(Gpio_portIdtype port, Gpio_dataType value);
void              Gpio_SetPin(Gpio_portIdtype port, Gpio_pinIdtype pin, Gpio_pinStateType state);
Gpio_dataType     Gpio_ReadPort(Gpio_portIdtype port);
Gpio_pinStateType Gpio_GetPin(Gpio_portIdtype port, Gpio_pinIdtype pin);

#endif /* _GPIO_H_ */
