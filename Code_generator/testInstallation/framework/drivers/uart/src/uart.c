/**
 * @file uart.c
 * @author Miguel Diaz
 * @brief UART framework module
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

/************************
 * File Scope Variables *
 ************************/
static UART_HandleTypeDef uartHandle[UART_CHANNEL_MAX];

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/

/**
 * @brief Initialize UART peripheral clock
 * @param channel UART channel
 */
void Uart_clkInit(Uart_IdType channel)
{
    switch (channel)
    {
        case UART1:
        {
            __USART1_CLK_ENABLE();
            break;
        }
        case UART2:
        {
            __USART2_CLK_ENABLE();
            break;
        }
        case UART3:
        {
            __USART3_CLK_ENABLE();
            break;
        }
        default:
        {
            break;
        }
    }
}

/**
 * @brief Initialize UART module
 * @param cfgPtr Configuration structure
 */
void Uart_Init(const Uart_CfgType *cfgPtr)
{
    uint32_t uartNum;

    if (NULL != cfgPtr)
    {
        for (uartNum = 0; uartNum < UART_CHANNEL_MAX; uartNum++)
        {
            uartHandle[uartNum].Instance        = cfgPtr[uartNum].Channel;
            uartHandle[uartNum].Init.BaudRate   = cfgPtr[uartNum].BaudRate;
            uartHandle[uartNum].Init.WordLength = cfgPtr[uartNum].WordLength;
            uartHandle[uartNum].Init.StopBits   = cfgPtr[uartNum].StopBits;
            uartHandle[uartNum].Init.Parity     = cfgPtr[uartNum].Parity;
            uartHandle[uartNum].Init.HwFlowCtl  = cfgPtr[uartNum].HwFlowCtl;
            uartHandle[uartNum].Init.Mode       = cfgPtr[uartNum].Mode;

            Uart_clkInit(uartNum);
            HAL_UART_Init(&uartHandle[uartNum]);
        }
    }
    else
    {
        // TODO: error handling
    }
}

/**
 * @brief Send a single character through the UART interface
 * @param chr Character to send
 */
void Uart_SendChar(Uart_IdType channel, uint8_t chr)
{
    Uart_SendBuffer(channel, &chr, UART_1_CHAR_SIZE);
}

/**
 * @brief Send several characters
 * @param srcBuff pointer to the characters buffer
 * @param size Number of characters to send
 */
void Uart_SendBuffer(Uart_IdType channel, uint8_t *srcBuff, size_t size)
{
    if ((NULL != srcBuff) && (0 < size))
    {
        HAL_UART_Transmit(&uartHandle[channel], (uint8_t *)srcBuff, size, HAL_MAX_DELAY);
    }
    else
    {
        // TODO: error handling
    }
}

/**
 * @brief Receive a single character
 * @return Character
 */
uint8_t Uart_ReceiveChar(Uart_IdType channel)
{
    uint8_t chr;

    Uart_ReceiveBuffer(channel, &chr, UART_1_CHAR_SIZE);

    return chr;
}

/**
 * @brief Receive a stream of characters
 * @param dstBuff Buffer to save the incoming characters
 * @param size Number of characters to receive
 */
void Uart_ReceiveBuffer(Uart_IdType channel, uint8_t *dstBuff, size_t size)
{
    if ((NULL != dstBuff) && (0 < size))
    {
        HAL_UART_Receive(&uartHandle[channel], (uint8_t *)dstBuff, size, HAL_MAX_DELAY);
    }
    else
    {
        // TODO: error handling
    }
}
