/**
 * @file spi_cfg.c
 * @author Miguel Diaz
 * @brief SPI framework module configuration
 */

/************************
 * Includes             *
 ************************/
#include "spi.h"

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
 * @brief SPI initial configuration structures array
 */
const Spi_CfgType Spi_Cfg[SPI_ELEMENTS_MAX] = {
    // EEPROM_SPI
    {EEPROM_SPI_CHANNEL,
     EEPROM_SPI_BAUDRATEPRESCALER,
     EEPROM_SPI_DIRECTION,
     EEPROM_SPI_CLK_PHASE,
     EEPROM_SPI_CLK_POLARITY,
     EEPROM_SPI_DATA_SIZE,
     EEPROM_SPI_FIRST_BIT,
     EEPROM_SPI_MODE}};

/************************
 * File Scope Variables *
 ************************/

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/
