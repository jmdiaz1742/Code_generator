/**
 * @file i2c_cfg.h
 * @author Miguel Diaz
 * @brief I2C framework configuration module header
 */

/************************
 * Guard                *
 ************************/
#ifndef _I2C_CFG_H_
#define _I2C_CFG_H_

/************************
 * Includes             *
 ************************/
FWK_I2C_INCLUDES

/************************
 * Public Types         *
 ************************/
/**
 * @brief I2C elements
 */
typedef enum { FWK_I2C_ELEMENTS, I2C_ELEMENTS_MAX } I2C_elementsType;

/************************
 * Public Macros        *
 ************************/

/************************
 * Public Defines       *
 ************************/
FWK_I2C_CFG_DEFINITIONS

FWK_I2C_ELEMENTS_DEFINITIONS

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
extern int i2c_cfgmainFunc (void);

#endif /* _I2C_CFG_H_ */
