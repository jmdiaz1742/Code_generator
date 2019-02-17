/**
 * @file frameworkCommon.h
 * @author Miguel Diaz
 * @brief framework common header
 */

/************************
 * Guard                *
 ************************/
#ifndef _FRAMEWORK_COMMON_H_
#define _FRAMEWORK_COMMON_H_

/************************
 * Includes             *
 ************************/
#include <stdlib.h>
#include <stdbool.h>

/************************
 * Public Types         *
 ************************/

/************************
 * Public Macros        *
 ************************/
#define FRAMEWORK_VERSION_MAJOR 0
#define FRAMEWORK_VERSION_MINOR 1
#define FRAMEWORK_VERSION_PATCH 0

/************************
 * Public Defines       *
 ************************/

// Bit/Byte related definitions
#define SHIFT_8_BITS (8)
#define LOW_BYTE_IN_16B_MASK (0x00FF)
#define HIGH_BYTE_IN_16B_MASK (0xFF00)

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

#endif /* _FRAMEWORK_COMMON_H_ */
