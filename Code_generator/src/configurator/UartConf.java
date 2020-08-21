package configurator;

import microcontroller.Uart;

public class UartConf {

	private Selected Selection;
	private String CodeName;
	private String Clock;
	private String Prescaler;
	private String BaudRate;
	private String DataBits;
	private String StopBits;
	private String Parity;

	/**
	 * Default Pin's selection
	 */
	public static final Selected DF_SELECTED = Selected.NOT;

	public static final String STR_NAME = "name";
	public static final String STR_CODE_NAME = "codeName";
	public static final String STR_CLOCK = "clock";
	public static final String STR_PRESCALER = "prescaler";
	public static final String STR_BAUD_RATE = "baudRate";
	public static final String STR_DATA_BITS = "dataBits";
	public static final String STR_STOP_BITS = "stopBits";
	public static final String STR_PARITY = "parity";

	public Uart UartFeatures;

	/**
	 * UART configuration constructor
	 * 
	 * @param uart UART instance
	 */
	public UartConf(Uart uart) {
		UartFeatures = uart;
		setSelected(DF_SELECTED);
		/* Initialize default configuration with first element of each feature */
		setCodeName(UartFeatures.getName());
		setClock(UartFeatures.getClock(0));
		setPrescaler(UartFeatures.getPrescaler(0));
		setBaudRate(UartFeatures.getBaudRate(0));
		setDataBits(UartFeatures.getDataBits(0));
		setStopBits(UartFeatures.getStopBits(0));
		setParity(UartFeatures.getParity(0));
	}

	/**
	 * Get the UART's selection
	 * 
	 * @return Selection
	 */
	public Selected getSelected() {
		return Selection;
	}

	/**
	 * Set the UART's selection
	 * 
	 * @param selection Selection
	 */
	public void setSelected(Selected selection) {
		Selection = selection;
	}

	/**
	 * Get UART's code name
	 * 
	 * @return UART's code name
	 */
	public String getCodeName() {
		return CodeName;
	}

	/**
	 * Set Get UART's code name
	 * 
	 * @param codeName UART's code name
	 */
	public void setCodeName(String codeName) {
		CodeName = codeName;
	}

	/**
	 * Get UART's configured Clock
	 * 
	 * @return UART's configured Clock
	 */
	public String getClock() {
		return Clock;
	}

	/**
	 * Get UART's configured Clock
	 * 
	 * @param clock UART's configured Clock
	 */
	public void setClock(String clock) {
		Clock = clock;
	}

	/**
	 * Get UART's configured Prescaler
	 * 
	 * @return UART's configured Prescaler
	 */
	public String getPrescaler() {
		return Prescaler;
	}

	/**
	 * Get UART's configured Prescaler
	 * 
	 * @param prescaler UART's configured Prescaler
	 */
	public void setPrescaler(String prescaler) {
		Prescaler = prescaler;
	}

	/**
	 * Get UART's configured BaudRate
	 * 
	 * @return UART's configured BaudRate
	 */
	public String getBaudRate() {
		return BaudRate;
	}

	/**
	 * Get UART's configured BaudRate
	 * 
	 * @param baudRate UART's configured BaudRate
	 */
	public void setBaudRate(String baudRate) {
		BaudRate = baudRate;
	}

	/**
	 * Get UART's configured DataBits
	 * 
	 * @return UART's configured DataBits
	 */
	public String getDataBits() {
		return DataBits;
	}

	/**
	 * Get UART's configured DataBits
	 * 
	 * @param dataBits UART's configured DataBits
	 */
	public void setDataBits(String dataBits) {
		DataBits = dataBits;
	}

	/**
	 * Get UART's configured StopBits
	 * 
	 * @return UART's configured StopBits
	 */
	public String getStopBits() {
		return StopBits;
	}

	/**
	 * Get UART's configured StopBits
	 * 
	 * @param stopBits UART's configured StopBits
	 */
	public void setStopBits(String stopBits) {
		StopBits = stopBits;
	}

	/**
	 * Get UART's configured Parity
	 * 
	 * @return UART's configured Parity
	 */
	public String getParity() {
		return Parity;
	}

	/**
	 * Get UART's configured Parity
	 * 
	 * @param parity UART's configured Parity
	 */
	public void setParity(String parity) {
		Parity = parity;
	}

}
