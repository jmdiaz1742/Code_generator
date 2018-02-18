/**
 * @file i2c.h
 * @author Miguel Diaz
 * @brief I2C framework module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _I2C_H_
#define _I2C_H_

/************************
 * Includes             *
 ************************/
#include "frameworkCommon.h"
#include "i2c_cfg.h"

/************************
 * Public Types         *
 ************************/
/**
 * @brief I2C IDs
 */
typedef enum { I2C_1, I2C_CHANNEL_MAX } I2C_IdType;

/**
 * I2C configuration structure
 */
typedef struct
{
	I2C_TypeDef *Channel;
	uint32_t     AddressingMode;
	uint32_t     Address;
	uint32_t     Timing;
} I2C_CfgType;

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
const I2C_CfgType I2C_Cfg[I2C_ELEMENTS_MAX];

/************************
 * Public Functions     *
 ************************/
void i2c_Init (const I2C_CfgType *cfgPtr);
void i2c_Write (I2C_IdType channel, uint16_t address, uint8_t *src, size_t size);
void i2c_Read (I2C_IdType channel, uint16_t address, uint8_t *dest, size_t size);

#endif /* _I2C_H_ */
