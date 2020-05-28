package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import microcontroller.Microcontroller;

public class AdcConfWindow {

	private JFrame frmAdcsConfiguration;

	/**
	 * Launch the application.
	 * 
	 * @param args General arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdcConfWindow window = new AdcConfWindow(null);
					window.frmAdcsConfiguration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param uCtrl Microcontroller
	 */
	public AdcConfWindow(Microcontroller uCtrl) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdcsConfiguration = new JFrame();
		frmAdcsConfiguration.setTitle("ADCs configuration");
		frmAdcsConfiguration.setBounds(100, 100, 450, 300);
		frmAdcsConfiguration.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmAdcsConfiguration.setVisible(true);
	}

}
