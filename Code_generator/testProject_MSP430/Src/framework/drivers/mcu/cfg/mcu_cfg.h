/**
 * @file mcu_cfg.h
 * @author Miguel Diaz
 * @brief MCU framework configuration module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _MCU_CGF_H_
#define _MCU_CGF_H_

/************************
 * Includes             *
 ************************/

/************************
 * Public Types         *
 ************************/

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
#define MCU_OSC_TYPE RCC_OSCILLATORTYPE_NONE
#define MCU_PLL_STATE RCC_PLL_ON
#define MCU_PLL_SOURCE RCC_PLLSOURCE_HSI
#define MCU_PLLMUL RCC_PLL_MUL16
#define MCU_CLOCK_TYPE (RCC_CLOCKTYPE_SYSCLK | RCC_CLOCKTYPE_HCLK | RCC_CLOCKTYPE_PCLK1 | RCC_CLOCKTYPE_PCLK2)
#define MCU_SYS_CLOCK_SOURCE RCC_SYSCLKSOURCE_PLLCLK
#define MCU_AHB_CLOCK_DIVIDER RCC_SYSCLK_DIV1
#define MCU_APB1_CLOCK_DIVIDER RCC_HCLK_DIV2
#define MCU_APB2_CLOCK_DIVIDER RCC_HCLK_DIV1
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

#endif /* _MCU_CGF_H_ */
