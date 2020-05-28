package configurator;

import java.util.ArrayList;
import java.util.List;

import configurator.ADC.AdcChannel;
import microcontroller.Adc;

public class AdcConf {

	private String CodeName;
	private String Sample;
	private String Clock;
	private String Justification;
	private String Resolution;
	private String Reference;
	private List<AdcChannel> Channels;

	/**
	 * ADC configuration constructor
	 * 
	 * @param adc ADC instance
	 */
	public AdcConf(Adc adc) {
		/* Initialize default configuration with first element of each feature */
		setCodeName(adc.geName());
		setSample(adc.getSample(0));
		setClock(adc.getClock(0));
		setJustification(adc.getJustification(0));
		setResolution(adc.getResolution(0));
		setReference(adc.getReference(0));
	}

	/**
	 * Get ADC's code name
	 * 
	 * @return ADC's code name
	 */
	public String getCodeName() {
		return CodeName;
	}

	/**
	 * Set Get ADC's code name
	 * 
	 * @param codeName ADC's code name
	 */
	public void setCodeName(String codeName) {
		CodeName = codeName;
	}

	/**
	 * Get ADC's configured samples
	 * 
	 * @return ADC's configured samples
	 */
	public String getSample() {
		return Sample;
	}

	/**
	 * Get ADC's configured samples
	 * 
	 * @param sample ADC's configured samples
	 */
	public void setSample(String sample) {
		Sample = sample;
	}

	/**
	 * Get ADC's configured clock
	 * 
	 * @return ADC's configured clock
	 */
	public String getClock() {
		return Clock;
	}

	/**
	 * Set ADC's configured clock
	 * 
	 * @param clock ADC's configured clock
	 */
	public void setClock(String clock) {
		Clock = clock;
	}

	/**
	 * Get ADC's configured justification
	 * 
	 * @return ADC's configured justification
	 */
	public String getJustification() {
		return Justification;
	}

	/**
	 * Set ADC's configured justification
	 * 
	 * @param justification ADC's configured justification
	 */
	public void setJustification(String justification) {
		Justification = justification;
	}

	/**
	 * Get ADC's configured resolution
	 * 
	 * @return ADC's configured resolution
	 */
	public String getResolution() {
		return Resolution;
	}

	/**
	 * Set ADC's configured resolution
	 * 
	 * @param resolution ADC's configured resolution
	 */
	public void setResolution(String resolution) {
		Resolution = resolution;
	}

	/**
	 * Get ADC's configured reference
	 * 
	 * @return ADC's configured reference
	 */
	public String getReference() {
		return Reference;
	}

	/**
	 * Set ADC's configured reference
	 * 
	 * @param reference ADC's configured reference
	 */
	public void setReference(String reference) {
		Reference = reference;
	}

	/**
	 * Set ADC channels
	 * 
	 * @param adc ADC instance
	 */
	public void setChannels(Adc adc) {
		Channels = new ArrayList<AdcChannel>();
		for (int chanNum = 0; chanNum < adc.getChannelNum(); chanNum++) {
			Channels.add(adc.getChannel(chanNum));
		}
	}

}
