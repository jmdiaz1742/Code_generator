package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import microcontroller.Microcontroller;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JComboBox;

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
		frmAdcsConfiguration.setTitle(Messages.getString("AdcConfWindow.frmAdcsConfiguration.title")); //$NON-NLS-1$
		frmAdcsConfiguration.setBounds(100, 100, 1182, 606);
		frmAdcsConfiguration.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmAdcsConfiguration.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		frmAdcsConfiguration.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lbt_SelectAdc = new JLabel(Messages.getString("AdcConfWindow.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lbt_SelectAdc = new GridBagConstraints();
		gbc_lbt_SelectAdc.anchor = GridBagConstraints.EAST;
		gbc_lbt_SelectAdc.insets = new Insets(0, 0, 0, 5);
		gbc_lbt_SelectAdc.gridx = 0;
		gbc_lbt_SelectAdc.gridy = 0;
		panel.add(lbt_SelectAdc, gbc_lbt_SelectAdc);
		
		JComboBox comboBox_SelectAdc = new JComboBox();
		GridBagConstraints gbc_comboBox_SelectAdc = new GridBagConstraints();
		gbc_comboBox_SelectAdc.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_SelectAdc.gridx = 1;
		gbc_comboBox_SelectAdc.gridy = 0;
		panel.add(comboBox_SelectAdc, gbc_comboBox_SelectAdc);
		frmAdcsConfiguration.setVisible(true);
	}

}
