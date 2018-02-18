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
FWK_SPI_INCLUDES

/************************
 * Public Types         *
 ************************/
/**
 * @brief SPI elements
 */
typedef enum { FWK_SPI_ELEMENTS, SPI_ELEMENTS_MAX } SPI_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
FWK_SPI_CFG_DEFINES

FWK_SPI_ELEMENTS_DEFINES

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
