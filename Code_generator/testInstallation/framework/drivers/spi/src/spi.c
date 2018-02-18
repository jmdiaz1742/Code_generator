/**
 * @file spi.c
 * @author Miguel Diaz
 * @brief SPI framework module
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
static SPI_HandleTypeDef SpiCfgStruct[SPI_ELEMENTS_MAX];

/************************
 * File Scope Variables *
 ************************/

/************************
 * Function Prototypes  *
 ************************/

/************************
 * Function Definitions *
 ************************/
/**
 * @brief Initialize SPI peripheral clock
 * @param channel SPI channel
 */
static void Spi_clkInit (Spi_IdType channel)
{
	switch (channel)
	{
		case SPI_1:
		{
			__SPI1_CLK_ENABLE ();
			break;
		}
		default:
		{
			break;
		}
	}
}

/**
 * @brief Initialize SPI port
 * @param cfgPtr SPI configuration structure
 */
void Spi_Init (const Spi_CfgType *cfgPtr)
{
	// STM32F334R8 has only 1 SPI channel, all these code is left as
	// it is to be able to scale in other microcontrollers
	if (NULL != cfgPtr)
	{
		for (uint8_t spiNum = 0; spiNum < SPI_CHANNEL_MAX; spiNum++)
		{
			// Default configuration:
			SpiCfgStruct[spiNum].Init.CRCCalculation    = SPI_CRCCALCULATION_DISABLED;
			SpiCfgStruct[spiNum].Init.NSS               = SPI_NSS_SOFT;
			SpiCfgStruct[spiNum].Init.TIMode            = SPI_TIMODE_DISABLED;
			SpiCfgStruct[spiNum].Instance               = cfgPtr[spiNum].Channel;
			SpiCfgStruct[spiNum].Init.BaudRatePrescaler = cfgPtr[spiNum].BaudRatePrescaler;
			SpiCfgStruct[spiNum].Init.Direction         = cfgPtr[spiNum].Direction;
			SpiCfgStruct[spiNum].Init.CLKPhase          = cfgPtr[spiNum].CLKPhase;
			SpiCfgStruct[spiNum].Init.CLKPolarity       = cfgPtr[spiNum].CLKPolarity;
			SpiCfgStruct[spiNum].Init.DataSize          = cfgPtr[spiNum].DataSize;
			SpiCfgStruct[spiNum].Init.FirstBit          = cfgPtr[spiNum].FirstBit;
			SpiCfgStruct[spiNum].Init.Mode              = cfgPtr[spiNum].Mode;

			Spi_clkInit (spiNum);
			if (HAL_SPI_Init (&SpiCfgStruct[spiNum]) != HAL_OK)
			{
				// TODO: Insert error handling stuff
			}
		}
	}
}

/**
 * @brief Transmit 8 bits of data
 * @param channel SPI channel
 * @param src Pointer to the data to transfer
 * @param dest Pointer to the place where incoming data is going into
 */
void Spi_TransmitU8 (Spi_IdType channel, uint8_t *src, uint8_t *dest)
{
	if ((NULL != src) && (NULL != dest))
	{
		// TODO: Insert error handling stuff
		HAL_SPI_TransmitReceive (&SpiCfgStruct[channel], src, dest, 1, SPI_TRANSMISSION_TIMEOUT);
	}
}

/**
 * @brief Transmit 16 bits of data
 * @param channel SPI channel
 * @param src Pointer to the data to transfer
 * @param dest Pointer to the place where incoming data is going into
 */
void Spi_TransmitU16 (Spi_IdType channel, uint16_t *src, uint16_t *dest)
{
	uint8_t txData[SPI_BYTES_IN_16_BITS];
	uint8_t rxData[SPI_BYTES_IN_16_BITS];

	if ((NULL != src) && (NULL != dest))
	{
		txData[0] = (uint8_t) ((*src & HIGH_BYTE_IN_16B_MASK) >> SHIFT_8_BITS);
		txData[1] = (uint8_t) (*src & LOW_BYTE_IN_16B_MASK);

		// TODO: Insert error handling stuff
		HAL_SPI_TransmitReceive (
		    &SpiCfgStruct[channel], txData, rxData, SPI_BYTES_IN_16_BITS, SPI_TRANSMISSION_TIMEOUT);

		*dest = (uint16_t) ((rxData[0] << SHIFT_8_BITS) | rxData[1]);
	}
}
