/**
 * @file uart.h
 * @author Miguel Diaz
 * @brief UART framework module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _UART_H_
#define _UART_H_

/************************
 * Includes             *
 ************************/
#include "frameworkCommon.h"
#include "uart_cfg.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief UART configuration structure
 */
typedef struct
{
    USART_TypeDef *Channel;
    uint32_t       BaudRate;
    uint32_t       WordLength;
    uint32_t       StopBits;
    uint32_t       Parity;
    uint32_t       HwFlowCtl;
    uint32_t       Mode;
} Uart_CfgType;

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
const Uart_CfgType Uart_Cfg[UART_CHANNEL_MAX];

/************************
 * Public Functions     *
 ************************/
void    Uart_Init(const Uart_CfgType *cfgPtr);
void    Uart_SendChar(Uart_IdType channel, uint8_t chr);
void    Uart_SendBuffer(Uart_IdType channel, uint8_t *srcBuff, size_t size);
uint8_t Uart_ReceiveChar(Uart_IdType channel);
void    Uart_ReceiveBuffer(Uart_IdType channel, uint8_t *dstBuff, size_t size);

#endif /* _UART_H_ */
