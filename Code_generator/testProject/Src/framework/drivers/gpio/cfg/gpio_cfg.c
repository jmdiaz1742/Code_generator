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
{SWITCH1_PORT,
SWITCH1_PIN,
SWITCH1_MODE,
SWITCH1_ALT,
SWITCH1_PULL,
SWITCH1_SPEED
},
{NC1_PORT,
NC1_PIN,
NC1_MODE,
NC1_ALT,
NC1_PULL,
NC1_SPEED
},
{PB6_PORT,
PB6_PIN,
PB6_MODE,
PB6_ALT,
PB6_PULL,
PB6_SPEED
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
