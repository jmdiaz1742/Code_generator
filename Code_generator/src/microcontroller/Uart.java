package microcontroller;

import java.util.ArrayList;
import java.util.List;

import common.ErrorCode;

public class Uart {

	private String Name;
	private List<String> Clocks = new ArrayList<String>();
	private List<String> Prescalers = new ArrayList<String>();
	private List<String> BaudRates = new ArrayList<String>();
	private List<String> DataBits = new ArrayList<String>();
	private List<String> StopBits = new ArrayList<String>();
	private List<String> Parity = new ArrayList<String>();

	/**
	 * UART instance constructor
	 */
	public Uart() {
	}

	/**
	 * Set UARTs instance name
	 * 
	 * @param name Instance name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Get UARTs instance name
	 * 
	 * @return Instance name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Add ADC supported clock source
	 * 
	 * @param clock Clock source
	 */
	public void addClock(String clock) {
		Clocks.add(clock);
	}

	/**
	 * Get UARTs number of clock sources
	 * 
	 * @return Number of clock sources
	 */
	public int getClockNum() {
		return Clocks.size();
	}

	/**
	 * Get UART's clock source
	 * 
	 * @param index Clock source index
	 * @return Clock source
	 */
	public String getClock(int index) {
		if (index < getClockNum()) {
			return Clocks.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Add UART's supported clock prescaler
	 * 
	 * @param prescaler Clock prescaler
	 */
	public void addPrescaler(String prescaler) {
		Prescalers.add(prescaler);
	}

	/**
	 * Get UART's number of supported prescalers
	 * 
	 * @return Number of supported prescalers
	 */
	public int getPrescalerNum() {
		return Prescalers.size();
	}

	/**
	 * Get UART's clock prescaler
	 * 
	 * @param index Clock prescaler index
	 * @return Clock prescaler
	 */
	public String getPrescaler(int index) {
		if (index < getPrescalerNum()) {
			return Prescalers.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Add UART's supported clock BaudRate
	 * 
	 * @param BaudRate Clock BaudRate
	 */
	public void addBaudRate(String baudRate) {
		BaudRates.add(baudRate);
	}

	/**
	 * Get UART's number of supported BaudRates
	 * 
	 * @return Number of supported BaudRates
	 */
	public int getBaudRateNum() {
		return BaudRates.size();
	}

	/**
	 * Get UART's clock BaudRate
	 * 
	 * @param index Clock BaudRate index
	 * @return Clock BaudRate
	 */
	public String getBaudRate(int index) {
		if (index < getBaudRateNum()) {
			return BaudRates.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Add UART's supported clock DataBits
	 * 
	 * @param DataBits Clock DataBits
	 */
	public void addDataBits(String dataBits) {
		DataBits.add(dataBits);
	}

	/**
	 * Get UART's number of supported DataBits
	 * 
	 * @return Number of supported DataBits
	 */
	public int getDataBitsNum() {
		return DataBits.size();
	}

	/**
	 * Get UART's clock DataBits
	 * 
	 * @param index Clock DataBits index
	 * @return Clock DataBits
	 */
	public String getDataBits(int index) {
		if (index < getDataBitsNum()) {
			return DataBits.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Add UART's supported clock StopBits
	 * 
	 * @param StopBits Clock StopBits
	 */
	public void addStopBits(String stopBits) {
		StopBits.add(stopBits);
	}

	/**
	 * Get UART's number of supported StopBits
	 * 
	 * @return Number of supported StopBits
	 */
	public int getStopBitsNum() {
		return StopBits.size();
	}

	/**
	 * Get UART's clock StopBits
	 * 
	 * @param index Clock StopBits index
	 * @return Clock StopBits
	 */
	public String getStopBits(int index) {
		if (index < getStopBitsNum()) {
			return StopBits.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Add UART's supported clock Parity
	 * 
	 * @param Parity Clock Parity
	 */
	public void addParity(String parity) {
		Parity.add(parity);
	}

	/**
	 * Get UART's number of supported Paritys
	 * 
	 * @return Number of supported Paritys
	 */
	public int getParityNum() {
		return Parity.size();
	}

	/**
	 * Get UART's clock Parity
	 * 
	 * @param index Clock Parity index
	 * @return Clock Parity
	 */
	public String getParity(int index) {
		if (index < getParityNum()) {
			return Parity.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Check validity of ADC
	 * 
	 * @return True if valid
	 */
	public boolean isValid() {
		if (!Name.equals(ErrorCode.STR_INVALID) && (getClockNum() > 0) && (getPrescalerNum() > 0)
				&& (getDataBitsNum() > 0) && (getStopBitsNum() > 0) && (getParityNum() > 0)) {
			return true;
		} else {
			return false;
		}
	}
}
