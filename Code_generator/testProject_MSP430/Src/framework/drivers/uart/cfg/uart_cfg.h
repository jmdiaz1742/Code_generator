/**
 * @file uart_cfg.c
 * @author Miguel Diaz
 * @brief UART framework module configuration header
 */

/************************
 * Guard                *
 ************************/
#ifndef _UART_CFG_H_
#define _UART_CFG_H_

/************************
 * Includes             *
 ************************/
#include "stm32f3xx_hal.h"

/************************
 * Public Types         *
 ************************/
typedef enum { UART1, UART2, UART3, UART_CHANNEL_MAX } Uart_IdType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// TODO: Confirm this timeout value
#define UART_SEND_TIMEOUT (0xFFFF)
#define UART_1_CHAR_SIZE (1)

#define ST_UART_CHANNEL UART2
#define ST_UART_BAUDRATE 115200
#define ST_UART_WORD_LENGTH UART_WORDLENGTH_8B
#define ST_UART_STOP_BITS UART_STOPBITS_1
#define ST_UART_PARITY UART_PARITY_NONE
#define ST_UART_HW_CONTROL UART_HWCONTROL_NONE
#define ST_UART_MODE UART_MODE_TX_RX

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
