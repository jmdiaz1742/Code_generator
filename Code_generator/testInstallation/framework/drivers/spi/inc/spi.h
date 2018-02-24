/**
 * @file spi.h
 * @author Miguel Diaz
 * @brief SPI framework module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _SPI_H_
#define _SPI_H_

/************************
 * Includes             *
 ************************/
#include "frameworkCommon.h"
#include "spi_cfg.h"

/************************
 * Public Types         *
 ************************/
// STM32F334R8 has only 1 SPI channel, all these code is left as
// it is to be able to scale in other microcontrollers
/**
 * @brief SPI IDs
 */
typedef enum { SPI_1, SPI_CHANNEL_MAX } Spi_IdType;

/**
 * @brief SPI configuration structure
 */
typedef struct
{
    SPI_TypeDef *Channel;
    uint32_t     BaudRatePrescaler;
    uint32_t     Direction;
    uint32_t     CLKPhase;
    uint32_t     CLKPolarity;
    uint32_t     DataSize;
    uint32_t     FirstBit;
    uint32_t     Mode;
} Spi_CfgType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
#define SPI_1_BYTE (1)
#define SPI_2_BYTES (2)
#define SPI_BYTES_IN_16_BITS (2)

/************************
 * Public Constants     *
 ************************/
const Spi_CfgType Spi_Cfg[SPI_ELEMENTS_MAX];

/************************
 * Public Calibrations  *
 ************************/

/************************
 * Public Variables     *
 ************************/

/************************
 * Public Functions     *
 ************************/
void Spi_Init(const Spi_CfgType *cfgPtr);
void Spi_TransmitU8(Spi_IdType channel, uint8_t *src, uint8_t *dest);
void Spi_TransmitU16(Spi_IdType channel, uint16_t *src, uint16_t *dest);

#endif /* _SPI_H_ */
