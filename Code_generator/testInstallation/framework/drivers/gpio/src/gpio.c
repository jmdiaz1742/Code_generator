/**
 * @file gpio.c
 * @author Miguel Diaz
 * @brief GPIO framework module
 */

/************************
 * Includes             *
 ************************/
#include "gpio.h"

/************************
 * Type Definitions     *
 ************************/

/************************
 * Macros               *
 ************************/

/************************
 * Defines              *
 ************************/

/************************
 * Constants            *
 ************************/

/************************
 * Calibrations         *
 ************************/

/************************
 * Global Variables     *
 ************************/
GPIO_TypeDef *GPIO_port[PORT_MAX] = {GPIOA, GPIOB, GPIOC, GPIOD, GPIOF};

uint16_t GPIO_pin[PIN_MAX] = {GPIO_PIN_0,
                              GPIO_PIN_1,
                              GPIO_PIN_2,
                              GPIO_PIN_3,
                              GPIO_PIN_4,
                              GPIO_PIN_5,
                              GPIO_PIN_6,
                              GPIO_PIN_7,
                              GPIO_PIN_8,
                              GPIO_PIN_9,
                              GPIO_PIN_10,
                              GPIO_PIN_11,
                              GPIO_PIN_12,
                              GPIO_PIN_13,
                              GPIO_PIN_14,
                              GPIO_PIN_15};
/************************
 * File Scope Variables *
 ************************/

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/

/**
 * @brief Initialize GPIO port clock
 * @param port pin Gpio port
 */
void GPIO_EnableClock(Gpio_portIdtype port)
{
    /* Enable each GPIO Clock (to be able to program the configuration registers) */
    switch (port)
    {
        case PORT_A:
            __GPIOA_CLK_ENABLE();
            break;
        case PORT_B:
            __GPIOB_CLK_ENABLE();
            break;
        case PORT_C:
            __GPIOC_CLK_ENABLE();
            break;
        case PORT_D:
            __GPIOD_CLK_ENABLE();
            break;
        case PORT_F:
            __GPIOF_CLK_ENABLE();
            break;
        default:
            break;
    }
}

/**
 * @brief Initialize GPIO pin
 * @param cfgPtr pin configuration structure
 */
void GPIO_Init(const Gpio_CfgType *cfgPtr)
{
    GPIO_InitTypeDef GPIO_InitStruct;
    if (NULL != cfgPtr)
    {
        for (uint8_t gpioNum = 0; gpioNum < GPIO_ELEMENTS_MAX; gpioNum++)
        {
            GPIO_EnableClock(cfgPtr[gpioNum].Port);

            GPIO_InitStruct.Mode  = cfgPtr[gpioNum].Mode;
            GPIO_InitStruct.Pull  = cfgPtr[gpioNum].Pull;
            GPIO_InitStruct.Speed = cfgPtr[gpioNum].Speed;
            GPIO_InitStruct.Pin   = GPIO_pin[cfgPtr[gpioNum].Pin];

            if (GPIO_ALT_NONE != cfgPtr[gpioNum].Alternate)
            {
                GPIO_InitStruct.Alternate = cfgPtr[gpioNum].Alternate;
            }

            HAL_GPIO_Init(GPIO_port[cfgPtr[gpioNum].Port], &GPIO_InitStruct);
        }
    }
}

/**
 * @brief Write value to port
 * @param port Port to be written
 * @param value Value to be written to port
 */
void Gpio_WritePort(Gpio_portIdtype port, Gpio_dataType value)
{
    /* Write to port's Output Data Register */
    GPIO_port[port]->ODR = value;
}

/**
 * @brief Write value to GPIO pin
 * @param port Pin's port
 * @param pin Pin to be written
 * @param state Value to be written to pin
 */
void Gpio_SetPin(Gpio_portIdtype port, Gpio_pinIdtype pin, Gpio_pinStateType state)
{
    HAL_GPIO_WritePin(GPIO_port[port], GPIO_pin[pin], state);
}

/**
 * @brief Read port's value
 * @param port Pin's port
 * @return Port's value
 */
Gpio_dataType Gpio_ReadPort(Gpio_portIdtype port)
{
    Gpio_dataType portValue;

    /* Read Input Data Register */
    portValue = GPIO_port[port]->IDR;
    return portValue;
}

/**
 * @brief Read GPIO pin's value
 * @param port Pin's port
 * @param pin Pin to be read
 * @return Pin's value
 */
Gpio_pinStateType Gpio_GetPin(Gpio_portIdtype port, Gpio_pinIdtype pin)
{
    Gpio_pinStateType pinState;

    pinState = HAL_GPIO_ReadPin(GPIO_port[port], GPIO_pin[pin]);
    return pinState;
}
