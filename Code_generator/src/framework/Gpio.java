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
		;
		this.uC = uC;
		Cfg_c = new File(Common.getGpioCfgFileCPath(fwkFolder));
		Cfg_h = new File(Common.getGpioCfgFileHPath(fwkFolder));
	}

	private String getGpioCfgArray() {
		String cfgArray;

		cfgArray = "";

		return cfgArray;
	}

}
