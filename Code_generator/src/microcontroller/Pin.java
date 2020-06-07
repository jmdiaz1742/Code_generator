package microcontroller;

/**
 * Basic pin object.
 * <ul>
 * <li>Pin necessary characteristics:
 * <ul>
 * <li>Name
 * <li>Number
 * </ul>
 * <li>Pin optional characteristics:
 * <ul>
 * <li>Port
 * </ul>
 * <li>Pin main functions:
 * <ul>
 * <li>VCC
 * <li>GND
 * <li>GPIO
 * <li>RESET
 * <li>MISC
 * </ul>
 * <li>Pin features:
 * <ul>
 * <li>Interruption
 * <li>ADC
 * <li>UART
 * <li>I2C
 * <li>SPI
 * <li>Clock
 * <li>Reset
 * </ul>
 * </ul>
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Pin {

	/* Pin main functions */
	private boolean func_vcc;
	private boolean func_gnd;
	private boolean func_gpio;
	private boolean func_reset;
	private boolean func_misc;

	/* Pin features availability */
	private boolean feat_int;
	private boolean feat_adc;
	private boolean feat_uart;
	private boolean feat_i2c;
	private boolean feat_spi;
	private boolean feat_clock;
	private boolean feat_reset;
	private boolean feat_timer;

	/* Pin features */
	private String interruption;
	private String adc;
	private String adcChannel;
	private String uart;
	private String i2c;
	private String spi;
	private String clock;
	private String reset;
	private String timer;

	/* Pin necessary characteristics */
	private String name;
	private int number;
	private String portPin;

	/* Pin optional characteristics */
	private String port;

	/* Default variables values */
	private static final String DEF_STRING = "NA";
	private static final int DEF_INT = 0;
	private static final boolean DEF_BOOLEAN = false;

	/**
	 * Enable value for features and functions
	 */
	public static final boolean ENABLE = true;

	/**
	 * Disable value for features and functions
	 */
	public static final boolean DISABLE = false;

	/**
	 * Default value for pin's function as not enabled
	 */
	public static final boolean DEF_FUNCTION = DEF_BOOLEAN;

	/**
	 * Default value for pin's feature availability as not available
	 */
	public static final boolean DEF_FEATURE_AV = DEF_BOOLEAN;

	/**
	 * Default value for pin's feature as not available
	 */
	public static final String DEF_FEATURE = DEF_STRING;

	/**
	 * Default value for pin's name
	 */
	public static final String DEF_NAME = DEF_STRING;

	/**
	 * Default value for pin's number
	 */
	public static final int DEF_NUMBER = DEF_INT;

	/**
	 * Default value for pin's port
	 */
	public static final String DEF_PORT = DEF_STRING;

	/**
	 * Initialize all pin's characteristics and features to their default values
	 */
	public Pin() {
		/* Pin main functions */
		this.func_vcc = DEF_FUNCTION;
		this.func_gnd = DEF_FUNCTION;
		this.func_gpio = DEF_FUNCTION;
		this.func_reset = DEF_FUNCTION;
		this.func_misc = DEF_FUNCTION;

		/* Pin features availability */
		this.feat_int = DEF_FEATURE_AV;
		this.feat_adc = DEF_FEATURE_AV;
		this.feat_uart = DEF_FEATURE_AV;
		this.feat_i2c = DEF_FEATURE_AV;
		this.feat_spi = DEF_FEATURE_AV;
		this.feat_clock = DEF_FEATURE_AV;
		this.feat_reset = DEF_FEATURE_AV;
		this.feat_timer = DEF_FEATURE_AV;

		/* Pin features */
		this.interruption = DEF_FEATURE;
		this.adc = DEF_FEATURE;
		this.uart = DEF_FEATURE;
		this.i2c = DEF_FEATURE;
		this.spi = DEF_FEATURE;
		this.clock = DEF_FEATURE;
		this.reset = DEF_FEATURE;
		this.timer = DEF_FEATURE;

		/* Pin necessary characteristics */
		this.name = DEF_NAME;
		this.number = DEF_NUMBER;

		/* Pin optional characteristics */
		this.port = DEF_PORT;
	}

	/**
	 * Set the pin to Vcc status
	 * 
	 * @param funcState Function availability
	 */
	public void setFunc_vcc(boolean funcState) {
		func_vcc = funcState;
		/* If pin is set as Vcc, disable all other functions */
		if (func_vcc) {
			setFunc_gnd(DISABLE);
			setFunc_gpio(DISABLE);
			setFunc_reset(DISABLE);
			setFunc_misc(DISABLE);
		}
	}

	/**
	 * See if the pin is Vcc
	 * 
	 * @return Function availability
	 */
	public boolean getFunc_vcc() {
		return func_vcc;
	}

	/**
	 * Set the pin to GND status
	 * 
	 * @param funcState Function availability
	 */
	public void setFunc_gnd(boolean funcState) {
		func_gnd = funcState;
		/* If pin is set as GND, disable all other functions */
		if (func_gnd) {
			setFunc_vcc(DISABLE);
			setFunc_gpio(DISABLE);
			setFunc_reset(DISABLE);
			setFunc_misc(DISABLE);
		}
	}

	/**
	 * See if the pin is GND
	 * 
	 * @return Function availability
	 */
	public boolean getFunc_gnd() {
		return func_gnd;
	}

	/**
	 * Set the pin to GPIO status
	 * 
	 * @param funcState Function availability
	 */
	public void setFunc_gpio(boolean funcState) {
		func_gpio = funcState;
		if (func_gpio) {
			/* If pin is set as GPIO, disable all other functions */
			setFunc_vcc(DISABLE);
			setFunc_gnd(DISABLE);
			setFunc_reset(DISABLE);
			setFunc_misc(DISABLE);
		} else {
			/* If the pin is NOT set as GPIO, disable all GPIO related features; */
			setFeat_int(DISABLE);
			setFeat_adc(DISABLE);
			setFeat_uart(DISABLE);
			setFeat_i2c(DISABLE);
			setFeat_spi(DISABLE);
			setFeat_clock(DISABLE);
			setFeat_timer(DISABLE);
		}
	}

	/**
	 * See if the pin is GPIO
	 * 
	 * @return Function availability
	 */
	public boolean getFunc_gpio() {
		return func_gpio;
	}

	/**
	 * Set the pin to RESET status
	 * 
	 * @param funcState Function availability
	 */
	public void setFunc_reset(boolean funcState) {
		func_reset = funcState;
		/* If pin is set as RESET, disable all other functions */
		if (func_reset) {
			setFunc_vcc(DISABLE);
			setFunc_gnd(DISABLE);
			setFunc_gpio(DISABLE);
			setFunc_misc(DISABLE);
		} else {
			setFeat_reset(DISABLE);
		}
	}

	/**
	 * See if the pin is RESET
	 * 
	 * @return Function availability
	 */
	public boolean getFunc_reset() {
		return func_reset;
	}

	/**
	 * Set the pin to MISC status
	 * 
	 * @param funcState Function availability
	 */
	public void setFunc_misc(boolean funcState) {
		func_misc = funcState;
		/* If pin is set as MISC, disable all other functions */
		if (func_misc) {
			setFunc_vcc(DISABLE);
			setFunc_gnd(DISABLE);
			setFunc_gpio(DISABLE);
			setFunc_reset(DISABLE);
		}
	}

	/**
	 * See if the pin is MISC
	 * 
	 * @return Function availability
	 */
	public boolean getFunc_misc() {
		return func_misc;
	}

	/**
	 * Set the pin's interruption feature
	 * 
	 * @param featState Feature availability
	 */
	public void setFeat_int(boolean featState) {
		feat_int = featState;
		if (!feat_int) {
			setInt(DEF_FEATURE);
		}
	}

	/**
	 * See if the pin has an interruption
	 * 
	 * @return Feature availability
	 */
	public boolean getFeat_int() {
		return feat_int;
	}

	/**
	 * Set the pin's ADC feature
	 * 
	 * @param featState Feature availability
	 */
	public void setFeat_adc(boolean featState) {
		feat_adc = featState;
		if (!feat_adc) {
			setAdc(DEF_FEATURE, DEF_FEATURE);
		}
	}

	/**
	 * See if the pin has an ADC
	 * 
	 * @return Feature availability
	 */
	public boolean getFeat_adc() {
		return feat_adc;
	}

	/**
	 * Set the pin's UART feature
	 * 
	 * @param featState Feature availability
	 */
	public void setFeat_uart(boolean featState) {
		feat_uart = featState;
		if (!feat_uart) {
			setUart(DEF_FEATURE);
		}
	}

	/**
	 * See if the pin has a UART
	 * 
	 * @return Feature availability
	 */
	public boolean getFeat_uart() {
		return feat_uart;
	}

	/**
	 * Set the pin's I2C feature
	 * 
	 * @param featState Feature availability
	 */
	public void setFeat_i2c(boolean featState) {
		feat_i2c = featState;
		if (!feat_i2c) {
			setI2c(DEF_FEATURE);
		}
	}

	/**
	 * See if the pin has I2C
	 * 
	 * @return Feature availability
	 */
	public boolean getFeat_i2c() {
		return feat_i2c;
	}

	/**
	 * Set the pin's SPI feature
	 * 
	 * @param featState Feature availability
	 */
	public void setFeat_spi(boolean featState) {
		feat_spi = featState;
		if (!feat_spi) {
			setSpi(DEF_FEATURE);
		}
	}

	/**
	 * See if the pin has SPI
	 * 
	 * @return Feature availability
	 */
	public boolean getFeat_spi() {
		return feat_spi;
	}

	/**
	 * Set the pin's Clock feature
	 * 
	 * @param featState Feature availability
	 */
	public void setFeat_clock(boolean featState) {
		feat_clock = featState;
		if (!feat_clock) {
			setClock(DEF_FEATURE);
		}
	}

	/**
	 * See if the pin supports a clock
	 * 
	 * @return Feature availability
	 */
	public boolean getFeat_clock() {
		return feat_clock;
	}

	/**
	 * Set the pin's timer feature
	 * 
	 * @param featState Feature availability
	 */
	public void setFeat_timer(boolean featState) {
		feat_timer = featState;
		if (!feat_timer) {
			setTimer(DEF_FEATURE);
		}
	}

	/**
	 * See if the pin supports a timer
	 * 
	 * @return Feature availability
	 */
	public boolean getFeat_timer() {
		return feat_timer;
	}

	/**
	 * Set the pin's reset feature
	 * 
	 * @param featState Feature availability
	 */
	public void setFeat_reset(boolean featState) {
		feat_reset = featState;
		if (!feat_reset) {
			setReset(DEF_FEATURE);
		}
	}

	/**
	 * See if the pin has a reset feature
	 * 
	 * @return Feature availability
	 */
	public boolean getFeat_reset() {
		return feat_reset;
	}

	/**
	 * Set the pin's interruption
	 * 
	 * @param feature Pin's interruption
	 */
	public void setInt(String feature) {
		interruption = feature;
		setFeat_int(ENABLE);
	}

	/**
	 * Get the pin's interruption name
	 * 
	 * @return Pin's interruption
	 */
	public String getInt() {
		return interruption;
	}

	/**
	 * Set the pin's ADC
	 * 
	 * @param feature Pin's ADC
	 */
	public void setAdc(String instance, String channel) {
		adc = instance;
		adcChannel = channel;
		setFeat_adc(ENABLE);
	}

	/**
	 * Get the pin's ADC name
	 * 
	 * @return Pin's ADC
	 */
	public String getAdc() {
		return adc;
	}

	/**
	 * Get the pin's ADC channel
	 * 
	 * @return Pin's ADC channel
	 */
	public String getAdcChannel() {
		return adcChannel;
	}

	/**
	 * Set the pin's UART
	 * 
	 * @param feature Pin's UART
	 */
	public void setUart(String feature) {
		uart = feature;
		setFeat_uart(ENABLE);
	}

	/**
	 * Get the pin's UART name
	 * 
	 * @return Pin's UART
	 */
	public String getUart() {
		return uart;
	}

	/**
	 * Set the pin's I2C
	 * 
	 * @param feature Pin's I2C
	 */
	public void setI2c(String feature) {
		i2c = feature;
		setFeat_i2c(ENABLE);
	}

	/**
	 * Get the pin's I2C name
	 * 
	 * @return Pin's I2C
	 */
	public String getI2c() {
		return i2c;
	}

	/**
	 * Set the pin's SPI
	 * 
	 * @param feature Pin's SPI
	 */
	public void setSpi(String feature) {
		spi = feature;
		setFeat_spi(ENABLE);
	}

	/**
	 * Get the pin's SPI name
	 * 
	 * @return Pin's SPI
	 */
	public String getSpi() {
		return spi;
	}

	/**
	 * Set the pin's clock
	 * 
	 * @param feature Pin's clock
	 */
	public void setClock(String feature) {
		clock = feature;
		setFeat_clock(ENABLE);
	}

	/**
	 * Get the pin's clock name
	 * 
	 * @return Pin's clock
	 */
	public String getClock() {
		return clock;
	}

	/**
	 * Set the pin's reset
	 * 
	 * @param feature Pin's reset
	 */
	public void setReset(String feature) {
		reset = feature;
		setFeat_reset(ENABLE);
	}

	/**
	 * Get the pin's reset name
	 * 
	 * @return Pin's reset
	 */
	public String getReset() {
		return reset;
	}

	/**
	 * Set the pin's timer
	 * 
	 * @param feature Pin's timer
	 */
	public void setTimer(String feature) {
		timer = feature;
		setFeat_timer(ENABLE);
	}

	/**
	 * Get the pin's timer name
	 * 
	 * @return Pin's timer
	 */
	public String getTimer() {
		return timer;
	}

	/**
	 * Set the pin's name
	 * 
	 * @param pinName Pin's name
	 */
	public void setName(String pinName) {
		name = pinName;
	}

	/**
	 * Get the pin's name
	 * 
	 * @return Pin's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the pin's number
	 * 
	 * @param pinNum Pin's number
	 */
	public void setNumber(int pinNum) {
		number = pinNum;
	}

	/**
	 * Get the pin's number
	 * 
	 * @return Pin's number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Get port pin number
	 * 
	 * @return port pin number
	 */
	public String getPortPin() {
		return portPin;
	}

	/**
	 * Set port pin number
	 * 
	 * @param portPin Port pin number
	 */
	public void setPortPin(String portPin) {
		this.portPin = portPin;
	}

	/**
	 * Set the pin's port
	 * 
	 * @param pinPort Pin's port
	 */
	public void setPort(String pinPort) {
		port = pinPort;
	}

	/**
	 * Get the pin's port
	 * 
	 * @return Pin's port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Check if the pin is correctly initialized
	 * 
	 * @return True if the pin is correctly initialized
	 */
	public boolean isValid() {
		boolean validPin = true;

		/* Check that the mandatory characteristics are present */
		if (getName().equals(Pin.DEF_NAME)) {
			validPin = false;
		}
		if (getNumber() <= Pin.DEF_NUMBER) {
			validPin = false;
		}

		/* Check that only 1 function is set */
		int numFunc = 0;
		if (getFunc_gpio()) {
			numFunc++;
		}
		if (getFunc_gnd()) {
			numFunc++;
		}
		if (getFunc_vcc()) {
			numFunc++;
		}
		if (getFunc_reset()) {
			numFunc++;
		}
		if (getFunc_misc()) {
			numFunc++;
		}

		if (numFunc != 1) {
			validPin = false;
		}

		return validPin;
	}
}
