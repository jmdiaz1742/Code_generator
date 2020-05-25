package configurator;

import microcontroller.Pin;
import common.Features;
import configurator.GPIO.AltMode;
import configurator.GPIO.Mode;
import configurator.GPIO.OutLevel;
import configurator.GPIO.OutType;
import configurator.GPIO.Pull;
import configurator.GPIO.Selected;
import configurator.GPIO.Speed;

/**
 * GPIO pin configuration
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class PinConf {

	/* Private fields */
	private Pin GpioPin;

	/* Pin configuration */
	private String Port;
	private String PinName;
	private String CodeName;
	private Selected Selection;
	private Mode PinMode;
	private AltMode PinAltMode;
	private Speed PinSpeed;
	private OutType PinOutType;
	private OutLevel PinOutLevel;
	private Pull PinPull;

	/* Public fields */

	/**
	 * Default Pin's selection
	 */
	public static final Selected DF_SELECTED = Selected.NOT;

	/**
	 * Default Pin mode
	 */
	public static final Mode DF_MODE = Mode.MODE_INPUT;
	/**
	 * Default Pin alternative mode
	 */
	public static final AltMode DF_ALT_MODE = AltMode.ALT_MODE_NONE;
	/**
	 * Default pin's speed
	 */
	public static final Speed DF_SPEED = Speed.SPEED_FAST;
	/**
	 * Default pin's output type
	 */
	public static final OutType DF_OUTTYPE = OutType.OTYPE_PUSH_PULL;
	/**
	 * Default pin's output level
	 */
	public static final OutLevel DF_OUT_LEVEL = OutLevel.LOW;
	/**
	 * Default pin's pull resistor
	 */
	public static final Pull DF_PULL = Pull.PULL_NOT_AVAILABLE;

	/**
	 * Default pin's code name
	 */
	public static final String DF_CODE_NAME = "";

	/* Methods */

	/**
	 * Constructor
	 * 
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
		setCodeName(gpioPin.getName());
		setMode(DF_MODE);
		setAltMode(DF_ALT_MODE);
		setSpeed(DF_SPEED);
		setOutType(DF_OUTTYPE);
		setOutLevel(DF_OUT_LEVEL);
		setPull(DF_PULL);
	}

	/**
	 * Check if the GPIO pin is valid
	 * 
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
	 * 
	 * @return Port
	 */
	public String getPort() {
		return Port;
	}

	/**
	 * Set the pin's port
	 * 
	 * @param port Port
	 */
	private void setPort(String port) {
		this.Port = port;
	}

	/**
	 * Get the port pin number
	 * 
	 * @return Port pin number
	 */
	public String getPortPin() {
		return GpioPin.getPortPin();
	}

	/**
	 * Get the pin's number
	 * 
	 * @return Pin's number
	 */
	public String getPinName() {
		return PinName;
	}

	/**
	 * Set the pin's number
	 * 
	 * @param pin Pin's number
	 */
	private void setPinName(String pin) {
		this.PinName = pin;
	}

	/**
	 * Get the pin's user selected name
	 * 
	 * @return pin's name
	 */
	public String getCodeName() {
		return CodeName;
	}

	/**
	 * Set the pin's user selected name
	 * 
	 * @param name Pin's name
	 */
	public void setCodeName(String name) {
		CodeName = name;
	}

	/**
	 * Get the pin's selection
	 * 
	 * @return Selection
	 */
	public Selected getSelected() {
		return Selection;
	}

	/**
	 * Set the pin's selection
	 * 
	 * @param selection Selection
	 */
	public void setSelected(Selected selection) {
		Selection = selection;
	}

	/**
	 * Get the pin's mode configuration
	 * 
	 * @return Mode
	 */
	public Mode getMode() {
		return PinMode;
	}

	/**
	 * Set the pin's mode configuration
	 * 
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
		default:
			this.PinMode = DF_MODE;
			break;
		}
		this.PinMode = mode;
	}

	/**
	 * Get pin's alternative mode
	 * 
	 * @return Alternative mode
	 */
	public AltMode getAltMode() {
		return PinAltMode;
	}

	/**
	 * Set pin's alternative mode
	 * 
	 * @param altMode Alternative mode
	 */
	public void setAltMode(AltMode altMode) {
		switch (altMode) {
		case ALT_MODE_ANALOG:
			if (isAv_Adc()) {
				this.PinAltMode = AltMode.ALT_MODE_ANALOG;
			}
			break;
		case ALT_MODE_UART:
			if (isAv_Uart()) {
				this.PinAltMode = AltMode.ALT_MODE_UART;
			}
		case ALT_MODE_I2C:
			if (isAv_I2c()) {
				this.PinAltMode = AltMode.ALT_MODE_I2C;
			}
		case ALT_MODE_SPI:
			if (isAv_Spi()) {
				this.PinAltMode = AltMode.ALT_MODE_SPI;
			}
		case ALT_MODE_NONE:
			this.PinAltMode = AltMode.ALT_MODE_NONE;
		default:
			this.PinAltMode = DF_ALT_MODE;
			break;
		}

	}

	/**
	 * Get the pin's output configuration
	 * 
	 * @return Output configuration
	 */
	public OutType getOutType() {
		return PinOutType;
	}

	/**
	 * Set the pin's output configuration
	 * 
	 * @param outType Output configuration
	 */
	public void setOutType(OutType outType) {
		this.PinOutType = outType;
	}

	/**
	 * Get the pin's output level
	 * 
	 * @return Pin's output level
	 */
	public OutLevel getOutLevel() {
		return PinOutLevel;
	}

	/**
	 * Set the pin's output level
	 * 
	 * @param level Pin's output level
	 */
	public void setOutLevel(OutLevel level) {
		PinOutLevel = level;
	}

	/**
	 * Get the pin's speed
	 * 
	 * @return Speed
	 */
	public Speed getSpeed() {
		return PinSpeed;
	}

	/**
	 * Set the pin's speed
	 * 
	 * @param speed Speed
	 */
	public void setSpeed(Speed speed) {
		this.PinSpeed = speed;
	}

	/**
	 * Get the pin's pull resistor configuration
	 * 
	 * @return Pull Resistor configuration
	 */
	public Pull getPull() {
		return PinPull;
	}

	/**
	 * Set the pull resistor configuration
	 * 
	 * @param pull Resistor configuration
	 */
	public void setPull(Pull pull) {
		this.PinPull = pull;
	}

	/**
	 * Check availability of ADC
	 * 
	 * @return True if ADC is available
	 */
	public boolean isAv_Adc() {
		return GpioPin.getFeat_adc();
	}

	/**
	 * Check availability of UART
	 * 
	 * @return True id UART is available
	 */
	public boolean isAv_Uart() {
		return GpioPin.getFeat_uart();
	}

	/**
	 * Check availability of I2C
	 * 
	 * @return True if I2C is available
	 */
	public boolean isAv_I2c() {
		return GpioPin.getFeat_i2c();
	}

	/**
	 * Check availability of SPI
	 * 
	 * @return True if SPI is available
	 */
	public boolean isAv_Spi() {
		return GpioPin.getFeat_spi();
	}

	/**
	 * Check the availability of alternate function
	 * 
	 * @return True if alternate function is available
	 */
	public boolean isAv_altFunc() {
		boolean altFunc = false;
		if (GpioPin.getFeat_clock() || GpioPin.getFeat_i2c() || GpioPin.getFeat_reset() || GpioPin.getFeat_spi()
				|| GpioPin.getFeat_uart()) {
			altFunc = true;
		}
		return altFunc;
	}
}
