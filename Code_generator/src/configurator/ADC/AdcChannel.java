package configurator.ADC;

import common.ErrorCode;
import configurator.Selected;

public class AdcChannel {

	public static final String INVALID_NAME = ErrorCode.STR_INVALID;
	public static final int INVALID_INDEX = ErrorCode.INT_INVALID_INDEX;

	private String Name;
	private Selected Selection;
	private String CodeName;
	private int PinIndex;

	public static final String STR_NAME = "name";
	public static final String STR_CODE_NAME = "codeName";
	public static final String STR_PIN_INDEX = "pinIndex";

	/**
	 * Default Pin's selection
	 */
	public static final Selected DF_SELECTED = configurator.Selected.NOT;

	public AdcChannel(String name, int pinIndex) {
		Name = name;
		setSelected(DF_SELECTED);
		setCodeName(name);
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
	 * Get channel's selection
	 * 
	 * @return Channel's selection
	 */
	public Selected getSelected() {
		return Selection;
	}

	/**
	 * Set channel's selection
	 * 
	 * @param selection Channel's selection
	 */
	public void setSelected(Selected selection) {
		Selection = selection;
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
