package microcontroller;

import java.util.ArrayList;
import java.util.List;

import common.ErrorCode;
import configurator.ADC.AdcChannel;

public class Adc {

	private String Name;
	private List<String> Samples = new ArrayList<String>();
	private List<String> Clocks = new ArrayList<String>();
	private List<String> Justifications = new ArrayList<String>();
	private List<String> Prescalers = new ArrayList<String>();
	private List<String> Resolutions = new ArrayList<String>();
	private List<String> References = new ArrayList<String>();
	private List<AdcChannel> Channels = new ArrayList<AdcChannel>();

	/**
	 * ADC instance constructor
	 */
	public Adc() {
	}

	/**
	 * Set ADCs instance name
	 * 
	 * @param name Instance name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Get ADCs instance name
	 * 
	 * @return Instance name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Add ADC supported samples
	 * 
	 * @param sample Sample definition
	 */
	public void addSample(String sample) {
		Samples.add(sample);
	}

	/**
	 * Get ADCs number of samples definitions
	 * 
	 * @return Number of samples definitions
	 */
	public int getSampleNum() {
		return Samples.size();
	}

	/**
	 * Get ADC's Sample definition
	 * 
	 * @param index sample definition index
	 * @return Sample definition
	 */
	public String getSample(int index) {
		if (index < getSampleNum()) {
			return Samples.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
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
	 * Get ADCs number of clock sources
	 * 
	 * @return Number of clock sources
	 */
	public int getClockNum() {
		return Clocks.size();
	}

	/**
	 * Get ADC's clock source
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
	 * Add ADC's supported bits justification
	 * 
	 * @param justification Bits justification
	 */
	public void addJustification(String justification) {
		Justifications.add(justification);
	}

	/**
	 * Get ADC's number of supported justifications
	 * 
	 * @return Number of supported justifications
	 */
	public int getJustificationNum() {
		return Justifications.size();
	}

	/**
	 * Get ADC's bits justification
	 * 
	 * @param index bits justification index
	 * @return Bits justification
	 */
	public String getJustification(int index) {
		if (index < getJustificationNum()) {
			return Justifications.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Add ADC's supported clock prescaler
	 * 
	 * @param prescaler Clock prescaler
	 */
	public void addPrescaler(String prescaler) {
		Prescalers.add(prescaler);
	}

	/**
	 * Get ADC's number of supported prescalers
	 * 
	 * @return Number of supported prescalers
	 */
	public int getPrescalerNum() {
		return Prescalers.size();
	}

	/**
	 * Get ADC's clock prescaler
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
	 * Add ADC's supported bits resolution
	 * 
	 * @param resolution bits resolution
	 */
	public void addResolution(String resolution) {
		Resolutions.add(resolution);
	}

	/**
	 * Get ADC's number of supported bits resolutions
	 * 
	 * @return Number of supported bits resolutions
	 */
	public int getResolutionNum() {
		return Resolutions.size();
	}

	/**
	 * Get ADC's bits resolution
	 * 
	 * @param index bits resolution index
	 * @return bits resolution
	 */
	public String getResolution(int index) {
		if (index < getResolutionNum()) {
			return Resolutions.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Add ADC's supported voltage references
	 * 
	 * @param reference Voltage references
	 */
	public void addReference(String reference) {
		References.add(reference);
	}

	/**
	 * Get ADC's number of supported voltage references
	 * 
	 * @return Number of supported voltage references
	 */
	public int getReferenceNum() {
		return References.size();
	}

	/**
	 * Get ADC's voltage references
	 * 
	 * @param index Voltage references index
	 * @return Voltage references
	 */
	public String getReference(int index) {
		if (index < getReferenceNum()) {
			return References.get(index);
		} else {
			return ErrorCode.STR_INVALID;
		}
	}

	/**
	 * Add ADC's channel
	 * 
	 * @param channel Channel
	 */
	public void addChannel(AdcChannel channel) {
		Channels.add(channel);
	}

	/**
	 * Get ADC's number of channels
	 * 
	 * @return Number of channels
	 */
	public int getChannelNum() {
		return Channels.size();
	}

	/**
	 * Get ADC's channel
	 * 
	 * @param index Channel index
	 * @return Channel
	 */
	public AdcChannel getChannel(int index) {
		if (index < getChannelNum()) {
			return Channels.get(index);
		} else {
			return new AdcChannel(AdcChannel.INVALID_NAME, AdcChannel.INVALID_INDEX);
		}
	}

	/**
	 * Check validity of ADC
	 * 
	 * @return True if valid
	 */
	public boolean isValid() {
		if (!Name.equals(ErrorCode.STR_INVALID) && (getSampleNum() > 0) && (getClockNum() > 0)
				&& (getJustificationNum() > 0) && (getResolutionNum() > 0) && (getReferenceNum() > 0)) {
			return true;
		} else {
			return false;
		}
	}
}
