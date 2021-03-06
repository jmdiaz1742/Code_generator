package configurator;

import java.util.ArrayList;
import java.util.List;

import common.ErrorCode;
import configurator.ADC.AdcChannel;
import microcontroller.Adc;

public class AdcConf {

	private Selected Selection;
	private String CodeName;
	private String Sample;
	private String Clock;
	private String Justification;
	private String Prescaler;
	private String Resolution;
	private String Reference;
	private List<AdcChannel> Channels;
	
	/**
	 * Default Pin's selection
	 */
	public static final Selected DF_SELECTED = Selected.NOT;

	public static final String STR_NAME = "name";
	public static final String STR_CODE_NAME = "codeName";
	public static final String STR_SAMPLE = "sample";
	public static final String STR_CLOCK = "clock";
	public static final String STR_JUSTIFICATION = "justification";
	public static final String STR_PRESCALER = "prescaler";
	public static final String STR_RESOLUTION = "resolution";
	public static final String STR_REFERENCE = "reference";
	public static final String STR_CHANNEL = "adcChannel";

	public Adc AdcFeatures;

	/**
	 * ADC configuration constructor
	 * 
	 * @param adc ADC instance
	 */
	public AdcConf(Adc adc) {
		AdcFeatures = adc;
		setSelected(DF_SELECTED);
		/* Initialize default configuration with first element of each feature */
		setCodeName(AdcFeatures.getName());
		setSample(AdcFeatures.getSample(0));
		setClock(AdcFeatures.getClock(0));
		setJustification(AdcFeatures.getJustification(0));
		setPrescaler(AdcFeatures.getPrescaler(0));
		setResolution(AdcFeatures.getResolution(0));
		setReference(AdcFeatures.getReference(0));
	}

	/**
	 * Get the ADC's selection
	 * 
	 * @return Selection
	 */
	public Selected getSelected() {
		return Selection;
	}

	/**
	 * Set the ADC's selection
	 * 
	 * @param selection Selection
	 */
	public void setSelected(Selected selection) {
		Selection = selection;
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
	 * Get ADC's prescaler
	 * 
	 * @return ADC's prescaler
	 */
	public String getPrescaler() {
		return Prescaler;
	}

	/**
	 * Set ADC's prescaler
	 * 
	 * @param prescaler ADC's prescaler
	 */
	public void setPrescaler(String prescaler) {
		Prescaler = prescaler;
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
		AdcFeatures = adc;
		Channels = new ArrayList<AdcChannel>();
		for (int chanNum = 0; chanNum < AdcFeatures.getChannelNum(); chanNum++) {
			Channels.add(AdcFeatures.getChannel(chanNum));
		}
	}
	
	/**
	 * Get the total of channels in the ADC
	 * @return Total of channels in the ADC
	 */
	public int getChannelsNum() {
		return Channels.size();
	}
	
	/**
	 * Get ADC channel
	 * @param index ADC channel index
	 * @return Channel
	 */
	public AdcChannel getChannel(int index) {
		return Channels.get(index);
	}
	
	public int getChannelIndexFromName(String name) {
		int index = ErrorCode.INT_INVALID_INDEX;
		
		for (int chanNum = 0; chanNum < getChannelsNum(); chanNum++) {
			if (name.equals(getChannel(chanNum).getName())) {
				index = chanNum;
				break;
			}
		}
		
		return index;
	}

}
