package configurator.GPIO;

/**
 * Pin's selection
 * 
 * @author Miguel Díaz
 * @version 0.1
 *
 */
public enum Selected {
	/**
	 * Pin not selected
	 */
	NOT,

	/**
	 * Pin selected
	 */
	YES;

	/**
	 * Name as String
	 */
	public static final String STR_NAME = "selected";

	/**
	 * Get the corresponding mode from its name as String
	 * 
	 * @param conf Configuration name
	 * @return Selected
	 */
	public static Selected getConfFromString(String conf) {
		Selected selected = NOT;

		switch (conf) {
		case "NOT":
			selected = Selected.NOT;
			break;
		case "YES":
			selected = Selected.YES;
			break;
		default:
			selected = Selected.NOT;
			break;
		}
		return selected;
	}

	/**
	 * Get the corresponding mode from a boolean
	 * 
	 * @param conf Configuration value
	 * @return Selected
	 */
	public static Selected getConfFromBoolean(Boolean conf) {
		Selected selected = NOT;

		if (conf) {
			selected = Selected.YES;
		}
		return selected;
	}

	/**
	 * Get the corresponding boolean from its selection
	 * 
	 * @return Selected pin state
	 */
	public boolean getBoolean() {

		boolean selection = false;

		switch (this) {
		case NOT:
			selection = false;
			break;
		case YES:
			selection = true;
			break;
		default:
			selection = false;
			break;
		}
		return selection;
	}
}
