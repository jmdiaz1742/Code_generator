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
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AdcConfWindow {

	private JFrame frmAdcsConfiguration;
	private Microcontroller UCtrl;
	
	/* Static GUI elements */
	private JComboBox<String> comboBox_SelectAdc;
	private JTextField textField_CodeName;
	private JComboBox<String> comboBox_Sample;
	private JComboBox<String> comboBox_Clock;
	private JComboBox<String> comboBox_Justification;
	private JComboBox<String> comboBox_Prescaler;
	private JComboBox<String> comboBox_Resolution;
	private JComboBox<String> comboBox_Reference;

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
		UCtrl = uCtrl;
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
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblt_SelectAdc = new JLabel(Messages.getString("AdcConfWindow.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_SelectAdc = new GridBagConstraints();
		gbc_lblt_SelectAdc.anchor = GridBagConstraints.EAST;
		gbc_lblt_SelectAdc.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_SelectAdc.gridx = 0;
		gbc_lblt_SelectAdc.gridy = 0;
		panel.add(lblt_SelectAdc, gbc_lblt_SelectAdc);
		
		comboBox_SelectAdc = new JComboBox();
		GridBagConstraints gbc_comboBox_SelectAdc = new GridBagConstraints();
		gbc_comboBox_SelectAdc.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_SelectAdc.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_SelectAdc.gridx = 1;
		gbc_comboBox_SelectAdc.gridy = 0;
		panel.add(comboBox_SelectAdc, gbc_comboBox_SelectAdc);
		
		JLabel lblt_ChannelSelected = new JLabel(Messages.getString("AdcConfWindow.lblSelected.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_ChannelSelected = new GridBagConstraints();
		gbc_lblt_ChannelSelected.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_ChannelSelected.gridx = 3;
		gbc_lblt_ChannelSelected.gridy = 0;
		panel.add(lblt_ChannelSelected, gbc_lblt_ChannelSelected);
		
		JLabel lblt_ChannelName = new JLabel(Messages.getString("AdcConfWindow.lblChannel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_ChannelName = new GridBagConstraints();
		gbc_lblt_ChannelName.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_ChannelName.gridx = 4;
		gbc_lblt_ChannelName.gridy = 0;
		panel.add(lblt_ChannelName, gbc_lblt_ChannelName);
		
		JLabel lblt_ChannelPin = new JLabel(Messages.getString("AdcConfWindow.lblPin.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_ChannelPin = new GridBagConstraints();
		gbc_lblt_ChannelPin.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_ChannelPin.gridx = 5;
		gbc_lblt_ChannelPin.gridy = 0;
		panel.add(lblt_ChannelPin, gbc_lblt_ChannelPin);
		
		JLabel lblt_ChannelCodeName = new JLabel(Messages.getString("AdcConfWindow.lblCodeName.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_ChannelCodeName = new GridBagConstraints();
		gbc_lblt_ChannelCodeName.insets = new Insets(0, 0, 5, 0);
		gbc_lblt_ChannelCodeName.gridx = 6;
		gbc_lblt_ChannelCodeName.gridy = 0;
		panel.add(lblt_ChannelCodeName, gbc_lblt_ChannelCodeName);
		
		JLabel lblt_CodeName = new JLabel(Messages.getString("AdcConfWindow.lblCodeName.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_CodeName = new GridBagConstraints();
		gbc_lblt_CodeName.anchor = GridBagConstraints.EAST;
		gbc_lblt_CodeName.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_CodeName.gridx = 0;
		gbc_lblt_CodeName.gridy = 1;
		panel.add(lblt_CodeName, gbc_lblt_CodeName);
		
		textField_CodeName = new JTextField();
		textField_CodeName.setText(Messages.getString("AdcConfWindow.textField.text")); //$NON-NLS-1$
		GridBagConstraints gbc_textField_CodeName = new GridBagConstraints();
		gbc_textField_CodeName.insets = new Insets(0, 0, 5, 5);
		gbc_textField_CodeName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_CodeName.gridx = 1;
		gbc_textField_CodeName.gridy = 1;
		panel.add(textField_CodeName, gbc_textField_CodeName);
		textField_CodeName.setColumns(10);
		
		JLabel lblt_Sample = new JLabel(Messages.getString("AdcConfWindow.lblSamples.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Sample = new GridBagConstraints();
		gbc_lblt_Sample.anchor = GridBagConstraints.EAST;
		gbc_lblt_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Sample.gridx = 0;
		gbc_lblt_Sample.gridy = 2;
		panel.add(lblt_Sample, gbc_lblt_Sample);
		
		comboBox_Sample = new JComboBox();
		GridBagConstraints gbc_comboBox_Sample = new GridBagConstraints();
		gbc_comboBox_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Sample.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Sample.gridx = 1;
		gbc_comboBox_Sample.gridy = 2;
		panel.add(comboBox_Sample, gbc_comboBox_Sample);
		
		JLabel lblt_Clock = new JLabel(Messages.getString("AdcConfWindow.lblClock.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Clock = new GridBagConstraints();
		gbc_lblt_Clock.anchor = GridBagConstraints.EAST;
		gbc_lblt_Clock.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Clock.gridx = 0;
		gbc_lblt_Clock.gridy = 3;
		panel.add(lblt_Clock, gbc_lblt_Clock);
		
		comboBox_Clock = new JComboBox();
		GridBagConstraints gbc_comboBox_Clock = new GridBagConstraints();
		gbc_comboBox_Clock.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Clock.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Clock.gridx = 1;
		gbc_comboBox_Clock.gridy = 3;
		panel.add(comboBox_Clock, gbc_comboBox_Clock);
		
		JLabel lblt_Justification = new JLabel(Messages.getString("AdcConfWindow.lblJustification.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Justification = new GridBagConstraints();
		gbc_lblt_Justification.anchor = GridBagConstraints.EAST;
		gbc_lblt_Justification.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Justification.gridx = 0;
		gbc_lblt_Justification.gridy = 4;
		panel.add(lblt_Justification, gbc_lblt_Justification);
		
		comboBox_Justification = new JComboBox();
		GridBagConstraints gbc_comboBox_Justification = new GridBagConstraints();
		gbc_comboBox_Justification.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Justification.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Justification.gridx = 1;
		gbc_comboBox_Justification.gridy = 4;
		panel.add(comboBox_Justification, gbc_comboBox_Justification);
		
		JLabel lblt_Prescaler = new JLabel(Messages.getString("AdcConfWindow.lblPrescaler.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Prescaler = new GridBagConstraints();
		gbc_lblt_Prescaler.anchor = GridBagConstraints.EAST;
		gbc_lblt_Prescaler.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Prescaler.gridx = 0;
		gbc_lblt_Prescaler.gridy = 5;
		panel.add(lblt_Prescaler, gbc_lblt_Prescaler);
		
		comboBox_Prescaler = new JComboBox();
		GridBagConstraints gbc_comboBox_Prescaler = new GridBagConstraints();
		gbc_comboBox_Prescaler.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Prescaler.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Prescaler.gridx = 1;
		gbc_comboBox_Prescaler.gridy = 5;
		panel.add(comboBox_Prescaler, gbc_comboBox_Prescaler);
		
		JLabel lblt_Resolution = new JLabel(Messages.getString("AdcConfWindow.lblResolution.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Resolution = new GridBagConstraints();
		gbc_lblt_Resolution.anchor = GridBagConstraints.EAST;
		gbc_lblt_Resolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Resolution.gridx = 0;
		gbc_lblt_Resolution.gridy = 6;
		panel.add(lblt_Resolution, gbc_lblt_Resolution);
		
		comboBox_Resolution = new JComboBox();
		GridBagConstraints gbc_comboBox_Resolution = new GridBagConstraints();
		gbc_comboBox_Resolution.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Resolution.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Resolution.gridx = 1;
		gbc_comboBox_Resolution.gridy = 6;
		panel.add(comboBox_Resolution, gbc_comboBox_Resolution);
		
		JLabel lblVoltajeReference = new JLabel(Messages.getString("AdcConfWindow.lblVoltajeReference.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblVoltajeReference = new GridBagConstraints();
		gbc_lblVoltajeReference.anchor = GridBagConstraints.EAST;
		gbc_lblVoltajeReference.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoltajeReference.gridx = 0;
		gbc_lblVoltajeReference.gridy = 7;
		panel.add(lblVoltajeReference, gbc_lblVoltajeReference);
		
		comboBox_Reference = new JComboBox();
		GridBagConstraints gbc_comboBox_Reference = new GridBagConstraints();
		gbc_comboBox_Reference.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Reference.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Reference.gridx = 1;
		gbc_comboBox_Reference.gridy = 7;
		panel.add(comboBox_Reference, gbc_comboBox_Reference);
		frmAdcsConfiguration.setVisible(true);
	}

}
