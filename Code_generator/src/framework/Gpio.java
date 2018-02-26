package framework;

import java.io.File;

import microcontroller.Microcontroller;

/**
 * generate GPIO configuration files
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class Gpio {

	/* Public fields */
	private final static String STR_CFG_MODULE_GPIO = "gpio";
	
	private Microcontroller uC;
	private File Cfg_c;
	private File Cfg_h;

	/**
	 * Constructor
	 * 
	 * @param uC
	 *            Microcontroller to be used
	 * @param fwkFolder
	 *            Framework folder path
	 */
	public Gpio(Microcontroller uC, String fwkFolder) {
		this.uC = uC;
		Cfg_c = new File(framework.Common.getCfgFileCPath(fwkFolder, STR_CFG_MODULE_GPIO));
		Cfg_h = new File(framework.Common.getCfgFileHPath(fwkFolder, STR_CFG_MODULE_GPIO));
	}

	private String getGpioCfgArray() {
		String cfgArray;

		cfgArray = "";

		return cfgArray;
	}

}
