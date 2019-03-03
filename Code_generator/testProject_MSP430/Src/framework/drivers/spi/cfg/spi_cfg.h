/**
 * @file spi_cfg.h
 * @author Miguel Diaz
 * @brief SPI framework module configuration header
 */

/************************
 * Guard                *
 ************************/
#ifndef _SPI_CFG_H_
#define _SPI_CFG_H_

/************************
 * Includes             *
 ************************/
#include "stm32f3xx_hal.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief SPI elements
 */
typedef enum { EEPROM_SPI, SPI_ELEMENTS_MAX } SPI_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
// EEPROM_SPI
#define EEPROM_SPI_CHANNEL SPI1
#define EEPROM_SPI_BAUDRATEPRESCALER SPI_BAUDRATEPRESCALER_256
#define EEPROM_SPI_DIRECTION SPI_DIRECTION_2LINES
#define EEPROM_SPI_CLK_PHASE SPI_PHASE_2EDGE
#define EEPROM_SPI_CLK_POLARITY SPI_POLARITY_HIGH
#define EEPROM_SPI_DATA_SIZE SPI_DATASIZE_8BIT
#define EEPROM_SPI_FIRST_BIT SPI_FIRSTBIT_MSB
#define EEPROM_SPI_MODE SPI_MODE_MASTER

// TODO: Confirm this timeout duration
#define SPI_TRANSMISSION_TIMEOUT HAL_MAX_DELAY

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

#endif /* _SPI_CFG_H_ */
