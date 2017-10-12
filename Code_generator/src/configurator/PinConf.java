package configurator;

import microcontroller.Pin;
import common.Features;
import configurator.GPIO.Mode;
import configurator.GPIO.OutType;
import configurator.GPIO.Pull;
import configurator.GPIO.Speed;

/**
 * GPIO pin configuration
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class PinConf {

	/* Private fields */
	Pin GpioPin;
	
	/* Pin configuration */
	private String	Port;
	private String	PinName;
	private Mode 	PinMode;
	private Speed	PinSpeed;
	private OutType	PinOutType;
	private boolean	PinOutLevel;
	private Pull	PinPull;
	
	/* Public fields */
	
	/**
	 * Default Pin mode
	 */
	public static final Mode	DF_MODE		= Mode.MODE_INPUT;
	/**
	 * Default pin's speed
	 */
	public static final Speed	DF_SPEED	= Speed.SPEED_FAST;
	/**
	 * Default pin's output type
	 */
	public static final OutType	DF_OUTTYPE	= OutType.OTYPE_PUSH_PULL;
	/**
	 * Default pin'r pull resistor
	 */
	public static final Pull	DF_PULL		= Pull.PULL_NOT_AVAILABLE;
	
	/* Methods */
	
	/**
	 * Constructor
	 * @param gpioPin Pin information
	 */
	public PinConf(Pin gpioPin) {
		if (gpioPin.isValid() && gpioPin.getFunc_gpio()) {
			this.GpioPin = gpioPin;
		} else {
			Features.verbosePrint("Pin " + gpioPin.getName() + "is NOT a GPIO!");
		}
		setPort(gpioPin.getPort());
		setPinName(gpioPin.getName());
		setMode(DF_MODE);
		setSpeed(DF_SPEED);
		setOutType(DF_OUTTYPE);
		setPull(DF_PULL);
	}
	
	/**
	 * Check if the GPIO pin is valid
	 * @return True if valid
	 */
	public boolean isValid() {
		boolean valid = false;
		
		if (GpioPin.isValid() && GpioPin.getFunc_gpio()) {
			valid = true;
		}
		
		return valid;
	}

	/**
	 * Get the pin's port
	 * @return Port
	 */
	public String getPort() {
		return Port;
	}

	/**
	 * Set the pin's port
	 * @param port Port
	 */
	private void setPort(String port) {
		this.Port = port;
	}

	/**
	 * Get the pin's number
	 * @return Pin's number
	 */
	public String getPinName() {
		return PinName;
	}

	/**
	 * Set the pin's number
	 * @param pin Pin's number
	 */
	private void setPinName(String pin) {
		this.PinName = pin;
	}

	/**
	 * Get the pin's mode configuration
	 * @return Mode
	 */
	public Mode getMode() {
		return PinMode;
	}

	/**
	 * Set the pin's mode configuration
	 * @param mode Mode
	 */
	public void setMode(Mode mode) {
		switch (mode) {
		case MODE_INPUT:
			this.PinMode = mode;
			break;
		case MODE_OUTPUT:
			this.PinMode = mode;
			break;
		case MODE_ALTERNATE_FUNCTION:
			if (isAv_altFunc()) {
				this.PinMode = Mode.MODE_ALTERNATE_FUNCTION;
			}
			break;
		case MODE_ANALOG:
			if (isAv_Adc()) {
				this.PinMode = Mode.MODE_ANALOG;
			}
			break;
		default:
			this.PinMode = DF_MODE;
			break;
		}
		this.PinMode = mode;
	}

	/**
	 * Get the pin's output configuration
	 * @return Output configuration
	 */
	public OutType getOutType() {
		return PinOutType;
	}
	
	/**
	 * Set the pin's output configuration
	 * @param outType Output configuration
	 */
	public void setOutType(OutType outType) {
		this.PinOutType = outType;
	}

	/**
	 * Get the pin's output status
	 * @return true if HIGH
	 */
	public boolean getPinOutLevel() {
		return PinOutLevel;
	}

	/**
	 * Set the pin's output status
	 * @param pinOutStatus true if HIGH
	 */
	public void setPinOutLevel(boolean level) {
		PinOutLevel = level;
	}

	/**
	 * Get the pin's speed
	 * @return Speed
	 */
	public Speed getSpeed() {
		return PinSpeed;
	}

	/**
	 * Set the pin's speed
	 * @param speed Speed
	 */
	public void setSpeed(Speed speed) {
		this.PinSpeed = speed;
	}

	/**
	 * Get the pin's pull resistor configuration
	 * @return Pull Resistor configuration
	 */
	public Pull getPull() {
		return PinPull;
	}

	/**
	 * Set the pull resistor configuration
	 * @param pull Resistor configuration
	 */
	public void setPull(Pull pull) {
		this.PinPull = pull;
	}
	
	/**
	 * Check availability of ADC
	 * @return True if ADC is available
	 */
	public boolean isAv_Adc() {
		return GpioPin.getFeat_adc();
	}
	
	/**
	 * Check the availability of alternate function
	 * @return True if alternate function is available
	 */
	public boolean isAv_altFunc() {
		boolean altFunc = false;
		if (GpioPin.getFeat_clock()	|| 
			GpioPin.getFeat_i2c()	|| 
			GpioPin.getFeat_reset()	|| 
			GpioPin.getFeat_spi()	|| 
			GpioPin.getFeat_uart()) {
			altFunc = true;
		}
		return altFunc;
	}
}
