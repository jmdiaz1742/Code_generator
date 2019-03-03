/**
 * @file i2c.c
 * @author Miguel Diaz
 * @brief I2C framework module
 */

/************************
 * Includes             *
 ************************/
#include "i2c.h"

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
static I2C_HandleTypeDef I2cCfgStruct[I2C_ELEMENTS_MAX];

/************************
 * Function Prototypes  *
 ************************/
static void i2c_InitClk (I2C_IdType channel);

/************************
 * Function Definitions *
 ************************/
/**
 * @brief Initialize I2C clock
 * @param channel I2C instance to initialize
 */
static void i2c_InitClk (I2C_IdType channel)
{
	switch (channel)
	{
		case I2C_1:
		{
			__I2C1_CLK_ENABLE ();
			break;
		}
		default:
		{
			break;
		}
	}
}

/**
 * @brief Initialize I2C instance
 * @param cfgPtr Configuration structure
 */
void i2c_Init (const I2C_CfgType *cfgPtr)
{
	if (NULL != cfgPtr)
	{
		for (uint32_t i2cNum = 0; i2cNum < I2C_CHANNEL_MAX; i2cNum++)
		{
			I2cCfgStruct[i2cNum].Instance            = cfgPtr[i2cNum].Channel;
			I2cCfgStruct[i2cNum].Init.AddressingMode = cfgPtr[i2cNum].AddressingMode;
			I2cCfgStruct[i2cNum].Init.Timing         = cfgPtr[i2cNum].Timing;

			I2cCfgStruct[i2cNum].Init.OwnAddress1      = 0x00;
			I2cCfgStruct[i2cNum].Init.OwnAddress2      = 0x00;
			I2cCfgStruct[i2cNum].Init.OwnAddress2Masks = I2C_OA2_NOMASK;
			I2cCfgStruct[i2cNum].Init.DualAddressMode  = I2C_DUALADDRESS_DISABLED;
			I2cCfgStruct[i2cNum].Init.GeneralCallMode  = I2C_GENERALCALL_DISABLED;
			I2cCfgStruct[i2cNum].Init.NoStretchMode    = I2C_NOSTRETCH_DISABLED;

			i2c_InitClk (i2cNum);
			HAL_I2C_Init (&I2cCfgStruct[i2cNum]);
		}
	}
	else
	{
		// TODO: Error handling
	}
}

/**
 * @brief Write to the I2C port
 * @param channel I2C channel
 * @param *src Pointer to the data buffer to write
 * @param size Number of bytes to write
 */
void i2c_Write (I2C_IdType channel, uint16_t address, uint8_t *src, size_t size)
{
	address <<= 1;
	HAL_I2C_Master_Transmit (&I2cCfgStruct[channel], address, src, size, I2C_TRANSMISSION_TIMEOUT);
}

/**
 * @brief Write to the I2C port
 * @param channel I2C channel
 * @param *dest Pointer to the data buffer destination
 * @param size Number of bytes to read
 */
void i2c_Read (I2C_IdType channel, uint16_t address, uint8_t *dest, size_t size)
{
	address <<= 1;
	HAL_I2C_Master_Receive (&I2cCfgStruct[channel], address, dest, size, I2C_TRANSMISSION_TIMEOUT);
}
