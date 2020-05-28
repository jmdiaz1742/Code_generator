package configurator.ADC;

import common.ErrorCode;

public class AdcChannel {

	public static final String INVALID_NAME = ErrorCode.STR_INVALID;
	public static final int INVALID_INDEX = ErrorCode.INT_INVALID_INDEX;

	private String Name;
	private String CodeName;
	private boolean Selected;
	private int PinIndex;

	public AdcChannel(String name, int pinIndex) {
		Name = name;
		setCodeName(name);
		setSelected(false);
		PinIndex = pinIndex;
	}

	/**
	 * Get ADC channel's name
	 * 
	 * @return ADC channel's name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Get ADC channel's code name
	 * 
	 * @return ADC channel's code name
	 */
	public String getCodeName() {
		return CodeName;
	}

	/**
	 * Set ADC channel's code name
	 * 
	 * @param codeName ADC channel's code name
	 */
	public void setCodeName(String codeName) {
		CodeName = codeName;
	}

	/**
	 * Get ADC channel's selection
	 * 
	 * @return ADC channel's selection
	 */
	public boolean isSelected() {
		return Selected;
	}

	/**
	 * Set ADC channel's selection
	 * 
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		Selected = selected;
	}

	/**
	 * Get ADC channel's pin index
	 * 
	 * @return
	 */
	public int getPinIndex() {
		return PinIndex;
	}

	/**
	 * Check channel validity
	 * 
	 * @return True if valid
	 */
	public boolean isValid() {
		boolean validity = true;

		if (Name.equals(INVALID_NAME) && PinIndex == INVALID_INDEX) {
			validity = false;
		}

		return validity;
	}

}
