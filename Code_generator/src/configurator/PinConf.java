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
	Pin gpioPin;
	
	/* Pin configuration */
	private String	port;
	private String	pin;
	private Mode 	mode;
	private Speed	speed;
	private OutType	outType;
	private Pull	pull;
	
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
			this.gpioPin = gpioPin;
		} else {
			Features.verbosePrint("Pin " + gpioPin.getName() + "is NOT a GPIO!");
		}
		/* TODO: Set port and pin correctly */
		setPort(gpioPin.getPort());
		setPin(gpioPin.getName());
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
		
		if (gpioPin.isValid() && gpioPin.getFunc_gpio()) {
			valid = true;
		}
		
		return valid;
	}

	/**
	 * Get the pin's port
	 * @return Port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Set the pin's port
	 * @param port Port
	 */
	private void setPort(String port) {
		this.port = port;
	}

	/**
	 * Get the pin's number
	 * @return Pin's number
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * Set the pin's number
	 * @param pin Pin's number
	 */
	private void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * Get the pin's mode configuration
	 * @return Mode
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * Set the pin's mode configuration
	 * @param mode Mode
	 */
	public void setMode(Mode mode) {
		switch (mode) {
		case MODE_INPUT:
			this.mode = mode;
			break;
		case MODE_OUTPUT:
			this.mode = mode;
			break;
		case MODE_ALTERNATE_FUNCTION:
			if (isAv_altFunc()) {
				this.mode = Mode.MODE_ALTERNATE_FUNCTION;
			}
			break;
		case MODE_ANALOG:
			if (isAv_Adc()) {
				this.mode = Mode.MODE_ANALOG;
			}
			break;
		default:
			this.mode = DF_MODE;
			break;
		}
		this.mode = mode;
	}

	/**
	 * Get the pin's output configuration
	 * @return Output configuration
	 */
	public OutType getOutType() {
		return outType;
	}
	
	/**
	 * Set the pin's output configuration
	 * @param outType Output configuration
	 */
	public void setOutType(OutType outType) {
		this.outType = outType;
	}

	/**
	 * Get the pin's speed
	 * @return Speed
	 */
	public Speed getSpeed() {
		return speed;
	}

	/**
	 * Set the pin's speed
	 * @param speed Speed
	 */
	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	/**
	 * Get the pin's pull resistor configuration
	 * @return Pull Resistor configuration
	 */
	public Pull getPull() {
		return pull;
	}

	/**
	 * Set the pull resistor configuration
	 * @param pull Resistor configuration
	 */
	public void setPull(Pull pull) {
		this.pull = pull;
	}
	
	/**
	 * Check availability of ADC
	 * @return True if ADC is available
	 */
	public boolean isAv_Adc() {
		return gpioPin.getFeat_adc();
	}
	
	/**
	 * Check the availability of alternate function
	 * @return True if alternate function is available
	 */
	public boolean isAv_altFunc() {
		boolean altFunc = false;
		if (gpioPin.getFeat_clock()	|| 
			gpioPin.getFeat_i2c()	|| 
			gpioPin.getFeat_reset()	|| 
			gpioPin.getFeat_spi()	|| 
			gpioPin.getFeat_uart()) {
			altFunc = true;
		}
		return altFunc;
	}
}
