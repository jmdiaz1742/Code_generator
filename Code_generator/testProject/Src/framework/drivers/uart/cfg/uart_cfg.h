/**
 * @file uart_cfg.h
 * @author Cesar Rodriguez
 * @brief UART framework configuration header
 */

/************************
 * Guard                *
 ************************/
#ifndef _UART_CFG_H_
#define _UART_CFG_H_

/************************
 * Includes             *
 ************************/
// ################## Kamino generator v1.2.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.2.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

/************************
 * Public Types         *
 ************************/
/**
 * @brief GPIO elements
 */
typedef enum
{
    // ################## Kamino generator v1.2.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
DebugUart,
// ################## Kamino generator v1.2.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######
    UART_ELEMENTS_MAX
} Uart_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// ################## Kamino generator v1.2.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######

// ################## Kamino generator v1.2.0: Generated code! ################
// ######## Do NOT modify code between this footer and the header above #######

// ################## Kamino generator v1.2.0: Generated code! ################
// ######## Do NOT modify code between this header and the footer below #######
// DebugUart
#define DebugUart_NAME USART1
#define DebugUart_CLOCK RCC_USART1CLKSOURCE_SYSCLK
#define DebugUart_PRESCALER UART_NOT_SUPPORTED
#define DebugUart_BAUD_RATE 2500
#define DebugUart_DATA_BITS UART_WORDLENGTH_8B
#define DebugUart_STOP_BITS UART_STOPBITS_1
#define DebugUart_PARITY UART_PARITY_NONE

// ################## Kamino generator v1.2.0: Generated code! ################
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

#endif /* _UART_CFG_H_ */
