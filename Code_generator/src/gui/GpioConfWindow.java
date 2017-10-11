package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import common.Features;
import microcontroller.Microcontroller;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

/**
 * Window for configuring GPIO pins
 * @author Miguel Díaz
 * @version 0.1
 *
 */
public class GpioConfWindow {

	private JFrame frmGpiosConfiguration;
	private Microcontroller UcConf;
	
	/* Dynamic GUI elements */
	JComboBox<String> comboBox_PortSelection;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GpioConfWindow window = new GpioConfWindow(null);
					window.frmGpiosConfiguration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the GPIO configuration window and show it
	 * @wbp.parser.entryPoint
	 */
	public GpioConfWindow(Microcontroller uCtrl) {
		UcConf = uCtrl;
		
		if (!UcConf.isValid()) {
			Features.verbosePrint("Wrong microcontroller configuration...");
			JOptionPane.showMessageDialog(frmGpiosConfiguration,
					"Wrong microcontroller configuration",
				    "Configuration error",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGpiosConfiguration = new JFrame();
		frmGpiosConfiguration.setTitle(Messages.getString("GpioConfWindow.frmGpiosConfiguration.title")); //$NON-NLS-1$
		frmGpiosConfiguration.setBounds(100, 100, 857, 736);
		frmGpiosConfiguration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{101, 97, 135, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frmGpiosConfiguration.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblt_SelectPort = new JLabel(Messages.getString("GpioConfWindow.lblSelectPort.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_SelectPort = new GridBagConstraints();
		gbc_lblt_SelectPort.insets = new Insets(0, 0, 0, 5);
		gbc_lblt_SelectPort.anchor = GridBagConstraints.WEST;
		gbc_lblt_SelectPort.gridx = 0;
		gbc_lblt_SelectPort.gridy = 0;
		frmGpiosConfiguration.getContentPane().add(lblt_SelectPort, gbc_lblt_SelectPort);
		
		comboBox_PortSelection = new JComboBox<String>();
		comboBox_PortSelection.setToolTipText(Messages.getString("GpioConfWindow.comboBox_PortSelection.toolTipText")); //$NON-NLS-1$
		GridBagConstraints gbc_comboBox_PortSelection = new GridBagConstraints();
		gbc_comboBox_PortSelection.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_PortSelection.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_PortSelection.gridx = 1;
		gbc_comboBox_PortSelection.gridy = 0;
		frmGpiosConfiguration.getContentPane().add(comboBox_PortSelection, gbc_comboBox_PortSelection);
	}

}
