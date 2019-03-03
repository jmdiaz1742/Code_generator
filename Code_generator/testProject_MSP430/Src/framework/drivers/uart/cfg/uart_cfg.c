/**
 * @file uart_cfg.c
 * @author Miguel Diaz
 * @brief UART framework module configuration
 */

/************************
 * Includes             *
 ************************/
#include "uart.h"

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
 * @brief UART initial configuration structures array
 */
const Uart_CfgType Uart_Cfg[UART_CHANNEL_MAX] = {{USART1,
                                                  ST_UART_BAUDRATE,
                                                  ST_UART_WORD_LENGTH,
                                                  ST_UART_STOP_BITS,
                                                  ST_UART_PARITY,
                                                  ST_UART_HW_CONTROL,
                                                  ST_UART_MODE},
                                                 {USART2,
                                                  ST_UART_BAUDRATE,
                                                  ST_UART_WORD_LENGTH,
                                                  ST_UART_STOP_BITS,
                                                  ST_UART_PARITY,
                                                  ST_UART_HW_CONTROL,
                                                  ST_UART_MODE},
                                                 {USART3,
                                                  ST_UART_BAUDRATE,
                                                  ST_UART_WORD_LENGTH,
                                                  ST_UART_STOP_BITS,
                                                  ST_UART_PARITY,
                                                  ST_UART_HW_CONTROL,
                                                  ST_UART_MODE}};

/************************
 * File Scope Variables *
 ************************/

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/
