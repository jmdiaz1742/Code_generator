package xmlParser;

/**
 * Basic pin object.
 * <ul>
 * 	<li>Pin necessary characteristics:
 * 	<ul>
 * 		<li>Name
 * 		<li>Number
 * 	</ul>
 * 	<li>Pin optional characteristics:
 * 	<ul>
 * 		<li>Port
 * 	</ul>
 * 	<li>Pin main functions:
 * 	<ul>
 * 		<li>VCC
 * 		<li>GND
 * 		<li>GPIO
 * 	</ul>
 * 	<li>Pin features:
 * 	<ul>
 * 		<li>Interruption
 * 		<li>ADC
 * 		<li>UART
 * 		<li>I2C
 * 		<li>SPI
 * 		<li>Clock
 * 		<li>Reset
 * 	</ul>
 * </ul>
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Pin {
	
	/* Pin main functions */
	private boolean func_vcc;
	private boolean func_gnd;
	private boolean func_gpio;
	
	/* Pin features availability */
	private boolean feat_int;
	private boolean feat_adc;
	private boolean feat_uart;
	private boolean feat_i2c;
	private boolean feat_spi;
	private boolean feat_clock;
	private boolean feat_reset;
	
	/* Pin features */
	private String interruption;
	private String adc;
	private String uart;
	private String i2c;
	private String spi;
	private String clock;
	
	/* Pin necessary characteristics */
	private String	name;
	private int		number;
	
	/* Pin optional characteristics */
	private String	port;
	
	/* Default values */
	private static final String		DEF_STRING	= "NA";
	private static final int		DEF_INT		= 0;
	private static final boolean	DEF_BOOLEAN	= false;
	
	/**
	 * Initialize all pin's characteristics
	 */
	public Pin() {
		/* Pin main functions */
		this.func_vcc	= DEF_BOOLEAN;
		this.func_gnd	= DEF_BOOLEAN;
		this.func_gpio	= DEF_BOOLEAN;
		
		/* Pin features availability */
		this.feat_int	= DEF_BOOLEAN;
		this.feat_adc	= DEF_BOOLEAN;
		this.feat_uart	= DEF_BOOLEAN;
		this.feat_i2c	= DEF_BOOLEAN;
		this.feat_spi	= DEF_BOOLEAN;
		this.feat_clock	= DEF_BOOLEAN;
		this.feat_reset	= DEF_BOOLEAN;
		
		/* Pin features */
		this.interruption = DEF_STRING;
		this.adc	= DEF_STRING;
		this.uart	= DEF_STRING;
		this.i2c	= DEF_STRING;
		this.spi	= DEF_STRING;
		this.clock	= DEF_STRING;
		
		/* Pin necessary characteristics */
		this.name	= DEF_STRING;
		this.number	= DEF_INT;
		
		/* Pin optional characteristics */
		this.port	= DEF_STRING;
	}
	
	/**
	 * Set the pin to Vcc status
	 * @param funcState Function availability
	 */
	public void setFunc_vcc(boolean funcState) {
		func_vcc = funcState;
	}
	
	/**
	 * See if the pin is Vcc
	 * @return Function availability
	 */
	public boolean getFunc_vcc() {
		return func_vcc;
	}
	
	/**
	 * Set the pin to GND status
	 * @param funcState Function availability
	 */
	public void setFunc_gnd(boolean funcState) {
		func_gnd = funcState;
	}
	
	/**
	 * See if the pin is GND
	 * @return Function availability
	 */
	public boolean getFunc_gnd() {
		return func_gnd;
	}
	
	/**
	 * Set the pin to GPIO status
	 * @param funcState Function availability
	 */
	public void setFunc_gpio(boolean funcState) {
		func_gpio = funcState;
	}
	
	/**
	 * See if the pin is GPIO
	 * @return Function availability
	 */
	public boolean getFunc_gpio() {
		return func_gpio;
	}

}
