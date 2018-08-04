/**
 * @file gpio_cfg.c
 * @author Miguel Diaz
 * @brief GPIO gramework configuration
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

/**
 * @brief GPIO initial configuration structures array
 */
const Gpio_CfgType Gpio_Cfg[GPIO_ELEMENTS_MAX] = {{LED1_PORT,
LED1_PIN,
LED1_MODE,
LED1_ALT,
LED1_PULL,
LED1_SPEED
},
{POT1_PORT,
POT1_PIN,
POT1_MODE,
POT1_ALT,
POT1_PULL,
POT1_SPEED
},
{SW1_PORT,
SW1_PIN,
SW1_MODE,
SW1_ALT,
SW1_PULL,
SW1_SPEED
},
{NC1_PORT,
NC1_PIN,
NC1_MODE,
NC1_ALT,
NC1_PULL,
NC1_SPEED
},
{POT1_PORT,
POT1_PIN,
POT1_MODE,
POT1_ALT,
POT1_PULL,
POT1_SPEED
}};
/************************
 * File Scope Variables *
 ************************/

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/
