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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.Features;
import configurator.AdcConf;
import configurator.Selected;
import configurator.ADC.AdcChannel;

import javax.swing.JCheckBox;

public class AdcConfWindow {

	private JFrame frmAdcsConfiguration;
	private JScrollPane scrollPane;
	private JPanel panel;
	private Microcontroller UcConf;
	private boolean GuiRefreshLocked = true;
	private int selectedAdc;

	/* Static GUI elements */
	private JComboBox<String> comboBox_SelectAdc;
	private JCheckBox chckbx_Selected;
	private JTextField textField_CodeName;
	private JComboBox<String> comboBox_Sample;
	private JComboBox<String> comboBox_Clock;
	private JComboBox<String> comboBox_Justification;
	private JComboBox<String> comboBox_Prescaler;
	private JComboBox<String> comboBox_Resolution;
	private JComboBox<String> comboBox_Reference;

	/* Dynamic GUI elements */
	private JCheckBox[] checkBox_ChannelSelected;
	private JLabel[] lbl_ChannelName;
	private JLabel[] lbl_ChannelPin;
	private JTextField[] textFieldChannelCodeName;

	/* GUI Constants */
	private static final int CHANNEL_SELECTED_INIT_POS_X = 3;
	private static final int CHANNEL_SELECTED_INIT_POS_Y = 1;
	private static final int CHANNEL_NAME_INIT_POS_X = 4;
	private static final int CHANNEL_NAME_INIT_POS_Y = 1;
	private static final int CHANNEL_PIN_INIT_POS_X = 5;
	private static final int CHANNEL_PIN_INIT_POS_Y = 1;
	private static final int CHANNEL_CODE_NAME_INIT_POS_X = 6;
	private static final int CHANNEL_CODE_NAME_INIT_POS_Y = 1;

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
		UcConf = uCtrl;
		initialize();
		initAdcComboBox();
		initDynamicAdcElements();
		selectAdc();
		frmAdcsConfiguration.setVisible(true);
		GuiRefreshLocked = false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdcsConfiguration = new JFrame();
		frmAdcsConfiguration.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				/* Begin Window closing */
				MainGui.setNewUC(UcConf);
				/* End Window closing */
			}
		});
		frmAdcsConfiguration.setTitle(Messages.getString("AdcConfWindow.frmAdcsConfiguration.title")); //$NON-NLS-1$
		frmAdcsConfiguration.setBounds(100, 100, 1182, 606);
		frmAdcsConfiguration.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmAdcsConfiguration.getContentPane().setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		frmAdcsConfiguration.getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblt_SelectAdc = new JLabel(Messages.getString("AdcConfWindow.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_SelectAdc = new GridBagConstraints();
		gbc_lblt_SelectAdc.anchor = GridBagConstraints.EAST;
		gbc_lblt_SelectAdc.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_SelectAdc.gridx = 0;
		gbc_lblt_SelectAdc.gridy = 0;
		panel.add(lblt_SelectAdc, gbc_lblt_SelectAdc);

		comboBox_SelectAdc = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_SelectAdc = new GridBagConstraints();
		gbc_comboBox_SelectAdc.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_SelectAdc.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_SelectAdc.gridx = 1;
		gbc_comboBox_SelectAdc.gridy = 0;
		panel.add(comboBox_SelectAdc, gbc_comboBox_SelectAdc);
		comboBox_SelectAdc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Port combo box click ******/
				if (!GuiRefreshLocked) {
					GuiRefreshLocked = true;
					selectAdc();
					Features.verbosePrint("ADC selected: " + comboBox_SelectAdc.getSelectedItem().toString() + "...");
					GuiRefreshLocked = false;
				}
				/****** End Port combo box click ******/
			}
		});

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

		JLabel lblt_Selected = new JLabel(Messages.getString("AdcConfWindow.lblSelected.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Selected = new GridBagConstraints();
		gbc_lblt_Selected.anchor = GridBagConstraints.EAST;
		gbc_lblt_Selected.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Selected.gridx = 0;
		gbc_lblt_Selected.gridy = 1;
		panel.add(lblt_Selected, gbc_lblt_Selected);

		chckbx_Selected = new JCheckBox(Messages.getString("AdcConfWindow.chckbxSelected.text")); //$NON-NLS-1$
		GridBagConstraints gbc_chckbx_Selected = new GridBagConstraints();
		gbc_chckbx_Selected.insets = new Insets(0, 0, 5, 5);
		gbc_chckbx_Selected.gridx = 1;
		gbc_chckbx_Selected.gridy = 1;
		panel.add(chckbx_Selected, gbc_chckbx_Selected);
		chckbx_Selected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin ADC configuration change ******/
				if (!GuiRefreshLocked) {
					adcConfChanged();
				}
				/****** End ADC configuration change ******/
			}
		});

		JLabel lblt_CodeName = new JLabel(Messages.getString("AdcConfWindow.lblCodeName.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_CodeName = new GridBagConstraints();
		gbc_lblt_CodeName.anchor = GridBagConstraints.EAST;
		gbc_lblt_CodeName.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_CodeName.gridx = 0;
		gbc_lblt_CodeName.gridy = 2;
		panel.add(lblt_CodeName, gbc_lblt_CodeName);

		textField_CodeName = new JTextField();
		textField_CodeName.setText(Messages.getString("AdcConfWindow.textField.text")); //$NON-NLS-1$
		GridBagConstraints gbc_textField_CodeName = new GridBagConstraints();
		gbc_textField_CodeName.insets = new Insets(0, 0, 5, 5);
		gbc_textField_CodeName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_CodeName.gridx = 1;
		gbc_textField_CodeName.gridy = 2;
		panel.add(textField_CodeName, gbc_textField_CodeName);
		textField_CodeName.setColumns(10);
		textField_CodeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin ADC configuration change ******/
				if (!GuiRefreshLocked) {
					adcConfChanged();
				}
				/****** End ADC configuration change ******/
			}
		});

		JLabel lblt_Sample = new JLabel(Messages.getString("AdcConfWindow.lblSamples.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Sample = new GridBagConstraints();
		gbc_lblt_Sample.anchor = GridBagConstraints.EAST;
		gbc_lblt_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Sample.gridx = 0;
		gbc_lblt_Sample.gridy = 3;
		panel.add(lblt_Sample, gbc_lblt_Sample);

		comboBox_Sample = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Sample = new GridBagConstraints();
		gbc_comboBox_Sample.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Sample.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Sample.gridx = 1;
		gbc_comboBox_Sample.gridy = 3;
		panel.add(comboBox_Sample, gbc_comboBox_Sample);
		comboBox_Sample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin ADC configuration change ******/
				if (!GuiRefreshLocked) {
					adcConfChanged();
				}
				/****** End ADC configuration change ******/
			}
		});

		JLabel lblt_Clock = new JLabel(Messages.getString("AdcConfWindow.lblClock.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Clock = new GridBagConstraints();
		gbc_lblt_Clock.anchor = GridBagConstraints.EAST;
		gbc_lblt_Clock.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Clock.gridx = 0;
		gbc_lblt_Clock.gridy = 4;
		panel.add(lblt_Clock, gbc_lblt_Clock);

		comboBox_Clock = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Clock = new GridBagConstraints();
		gbc_comboBox_Clock.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Clock.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Clock.gridx = 1;
		gbc_comboBox_Clock.gridy = 4;
		panel.add(comboBox_Clock, gbc_comboBox_Clock);
		comboBox_Clock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin ADC configuration change ******/
				if (!GuiRefreshLocked) {
					adcConfChanged();
				}
				/****** End ADC configuration change ******/
			}
		});

		JLabel lblt_Justification = new JLabel(Messages.getString("AdcConfWindow.lblJustification.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Justification = new GridBagConstraints();
		gbc_lblt_Justification.anchor = GridBagConstraints.EAST;
		gbc_lblt_Justification.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Justification.gridx = 0;
		gbc_lblt_Justification.gridy = 5;
		panel.add(lblt_Justification, gbc_lblt_Justification);

		comboBox_Justification = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Justification = new GridBagConstraints();
		gbc_comboBox_Justification.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Justification.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Justification.gridx = 1;
		gbc_comboBox_Justification.gridy = 5;
		panel.add(comboBox_Justification, gbc_comboBox_Justification);
		comboBox_Justification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin ADC configuration change ******/
				if (!GuiRefreshLocked) {
					adcConfChanged();
				}
				/****** End ADC configuration change ******/
			}
		});

		JLabel lblt_Prescaler = new JLabel(Messages.getString("AdcConfWindow.lblPrescaler.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Prescaler = new GridBagConstraints();
		gbc_lblt_Prescaler.anchor = GridBagConstraints.EAST;
		gbc_lblt_Prescaler.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Prescaler.gridx = 0;
		gbc_lblt_Prescaler.gridy = 6;
		panel.add(lblt_Prescaler, gbc_lblt_Prescaler);

		comboBox_Prescaler = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Prescaler = new GridBagConstraints();
		gbc_comboBox_Prescaler.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Prescaler.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Prescaler.gridx = 1;
		gbc_comboBox_Prescaler.gridy = 6;
		panel.add(comboBox_Prescaler, gbc_comboBox_Prescaler);
		comboBox_Prescaler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin ADC configuration change ******/
				if (!GuiRefreshLocked) {
					adcConfChanged();
				}
				/****** End ADC configuration change ******/
			}
		});

		JLabel lblt_Resolution = new JLabel(Messages.getString("AdcConfWindow.lblResolution.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Resolution = new GridBagConstraints();
		gbc_lblt_Resolution.anchor = GridBagConstraints.EAST;
		gbc_lblt_Resolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Resolution.gridx = 0;
		gbc_lblt_Resolution.gridy = 7;
		panel.add(lblt_Resolution, gbc_lblt_Resolution);

		comboBox_Resolution = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Resolution = new GridBagConstraints();
		gbc_comboBox_Resolution.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Resolution.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Resolution.gridx = 1;
		gbc_comboBox_Resolution.gridy = 7;
		panel.add(comboBox_Resolution, gbc_comboBox_Resolution);
		comboBox_Resolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin ADC configuration change ******/
				if (!GuiRefreshLocked) {
					adcConfChanged();
				}
				/****** End ADC configuration change ******/
			}
		});

		JLabel lblVoltajeReference = new JLabel(Messages.getString("AdcConfWindow.lblVoltajeReference.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblVoltajeReference = new GridBagConstraints();
		gbc_lblVoltajeReference.anchor = GridBagConstraints.EAST;
		gbc_lblVoltajeReference.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoltajeReference.gridx = 0;
		gbc_lblVoltajeReference.gridy = 8;
		panel.add(lblVoltajeReference, gbc_lblVoltajeReference);

		comboBox_Reference = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Reference = new GridBagConstraints();
		gbc_comboBox_Reference.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Reference.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Reference.gridx = 1;
		gbc_comboBox_Reference.gridy = 8;
		panel.add(comboBox_Reference, gbc_comboBox_Reference);
		comboBox_Reference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin ADC configuration change ******/
				if (!GuiRefreshLocked) {
					adcConfChanged();
				}
				/****** End ADC configuration change ******/
			}
		});
	}

	/**
	 * Initialize ADC selection comboBox
	 */
	private void initAdcComboBox() {
		for (int adcNum = 0; adcNum < UcConf.getUc_adcNum(); adcNum++) {
			comboBox_SelectAdc.addItem(UcConf.Adcs[adcNum]);
		}
	}

	/**
	 * Initialize dynamic elements
	 */
	private void initDynamicAdcElements() {
		int chaNum = 0;
		int chaMax = Microcontroller.MAX_NUMBER_OF_ADCS;
		GridBagConstraints gbc_element;
		boolean initVisibleState = false;

		Features.verbosePrint("Initializing dynamic ADC elements...");
		checkBox_ChannelSelected = new JCheckBox[chaMax];
		lbl_ChannelName = new JLabel[chaMax];
		lbl_ChannelPin = new JLabel[chaMax];
		textFieldChannelCodeName = new JTextField[chaMax];

		for (chaNum = 0; chaNum < chaMax; chaNum++) {
			checkBox_ChannelSelected[chaNum] = new JCheckBox();
			gbc_element = new GridBagConstraints();
			gbc_element.insets = new Insets(0, 0, 5, 5);
			gbc_element.fill = GridBagConstraints.HORIZONTAL;
			gbc_element.gridx = CHANNEL_SELECTED_INIT_POS_X;
			gbc_element.gridy = CHANNEL_SELECTED_INIT_POS_Y + chaNum;
			panel.add(checkBox_ChannelSelected[chaNum], gbc_element);
			checkBox_ChannelSelected[chaNum].setVisible(initVisibleState);
			checkBox_ChannelSelected[chaNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Channel selection combo box click ******/
					if (!GuiRefreshLocked) {
						dynamicElementsChange();
					}
					/****** End Channel selection combo box click ******/
				}
			});

			lbl_ChannelName[chaNum] = new JLabel();
			gbc_element = new GridBagConstraints();
			gbc_element.insets = new Insets(0, 0, 5, 5);
			gbc_element.fill = GridBagConstraints.HORIZONTAL;
			gbc_element.gridx = CHANNEL_NAME_INIT_POS_X;
			gbc_element.gridy = CHANNEL_NAME_INIT_POS_Y + chaNum;
			panel.add(lbl_ChannelName[chaNum], gbc_element);
			lbl_ChannelName[chaNum].setVisible(initVisibleState);

			lbl_ChannelPin[chaNum] = new JLabel();
			gbc_element = new GridBagConstraints();
			gbc_element.insets = new Insets(0, 0, 5, 5);
			gbc_element.fill = GridBagConstraints.HORIZONTAL;
			gbc_element.gridx = CHANNEL_PIN_INIT_POS_X;
			gbc_element.gridy = CHANNEL_PIN_INIT_POS_Y + chaNum;
			panel.add(lbl_ChannelPin[chaNum], gbc_element);
			lbl_ChannelPin[chaNum].setVisible(initVisibleState);

			textFieldChannelCodeName[chaNum] = new JTextField();
			gbc_element = new GridBagConstraints();
			gbc_element.insets = new Insets(0, 0, 5, 5);
			gbc_element.fill = GridBagConstraints.HORIZONTAL;
			gbc_element.gridx = CHANNEL_CODE_NAME_INIT_POS_X;
			gbc_element.gridy = CHANNEL_CODE_NAME_INIT_POS_Y + chaNum;
			panel.add(textFieldChannelCodeName[chaNum], gbc_element);
			textFieldChannelCodeName[chaNum].setVisible(initVisibleState);
			textFieldChannelCodeName[chaNum].getDocument().addDocumentListener(new DocumentListener() {
				/****** Begin Pin's Code Name change ******/

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					if (!GuiRefreshLocked) {
						dynamicElementsChange();
					}
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					if (!GuiRefreshLocked) {
						dynamicElementsChange();
					}
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					if (!GuiRefreshLocked) {
						dynamicElementsChange();
					}
				}
				/****** End Pin's Code Name change ******/
			});
		}

	}

	private void selectAdc() {
		int featNum = 0;
		int selectedItem = 0;
		AdcConf adcCfg;
		boolean fieldsEditable;

		selectedAdc = comboBox_SelectAdc.getSelectedIndex();
		adcCfg = UcConf.AdcCfg[selectedAdc];
		fieldsEditable = adcCfg.getSelected().getBoolean();
		chckbx_Selected.setSelected(fieldsEditable);

		textField_CodeName.setText(adcCfg.getCodeName());
		textField_CodeName.setEnabled(fieldsEditable);

		comboBox_Sample.removeAllItems();
		for (featNum = 0; featNum < adcCfg.AdcFeatures.getSampleNum(); featNum++) {
			comboBox_Sample.addItem(adcCfg.AdcFeatures.getSample(featNum));
			if (adcCfg.getSample().equals(comboBox_Sample.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Sample.setSelectedIndex(selectedItem);
		comboBox_Sample.setEnabled(fieldsEditable);

		comboBox_Clock.removeAllItems();
		for (featNum = 0; featNum < adcCfg.AdcFeatures.getClockNum(); featNum++) {
			comboBox_Clock.addItem(adcCfg.AdcFeatures.getClock(featNum));
			if (adcCfg.getClock().equals(comboBox_Clock.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Clock.setSelectedIndex(selectedItem);
		comboBox_Clock.setEnabled(fieldsEditable);

		comboBox_Justification.removeAllItems();
		for (featNum = 0; featNum < adcCfg.AdcFeatures.getJustificationNum(); featNum++) {
			comboBox_Justification.addItem(adcCfg.AdcFeatures.getJustification(featNum));
			if (adcCfg.getJustification().equals(comboBox_Justification.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Justification.setSelectedIndex(selectedItem);
		comboBox_Justification.setEnabled(fieldsEditable);

		comboBox_Prescaler.removeAllItems();
		for (featNum = 0; featNum < adcCfg.AdcFeatures.getPrescalerNum(); featNum++) {
			comboBox_Prescaler.addItem(adcCfg.AdcFeatures.getPrescaler(featNum));
			if (adcCfg.getPrescaler().equals(comboBox_Prescaler.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Prescaler.setSelectedIndex(selectedItem);
		comboBox_Prescaler.setEnabled(fieldsEditable);

		comboBox_Resolution.removeAllItems();
		for (featNum = 0; featNum < adcCfg.AdcFeatures.getResolutionNum(); featNum++) {
			comboBox_Resolution.addItem(adcCfg.AdcFeatures.getResolution(featNum));
			if (adcCfg.getResolution().equals(comboBox_Resolution.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Resolution.setSelectedIndex(selectedItem);
		comboBox_Resolution.setEnabled(fieldsEditable);

		comboBox_Reference.removeAllItems();
		for (featNum = 0; featNum < adcCfg.AdcFeatures.getReferenceNum(); featNum++) {
			comboBox_Reference.addItem(adcCfg.AdcFeatures.getReference(featNum));
			if (adcCfg.getReference().equals(comboBox_Reference.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Reference.setSelectedIndex(selectedItem);
		comboBox_Reference.setEnabled(fieldsEditable);

		populateDynamicAdcElements();
	}

	/**
	 * Save ADC's configuration to uC
	 */
	private void adcConfChanged() {
		boolean fieldsEditable = false;

		UcConf.AdcCfg[selectedAdc].setSelected(Selected.getConfFromBoolean(chckbx_Selected.isSelected()));
		UcConf.AdcCfg[selectedAdc].setCodeName(textField_CodeName.getText());
		UcConf.AdcCfg[selectedAdc].setSample(comboBox_Sample.getSelectedItem().toString());
		UcConf.AdcCfg[selectedAdc].setClock(comboBox_Clock.getSelectedItem().toString());
		UcConf.AdcCfg[selectedAdc].setJustification(comboBox_Justification.getSelectedItem().toString());
		UcConf.AdcCfg[selectedAdc].setPrescaler(comboBox_Prescaler.getSelectedItem().toString());
		UcConf.AdcCfg[selectedAdc].setResolution(comboBox_Resolution.getSelectedItem().toString());
		UcConf.AdcCfg[selectedAdc].setReference(comboBox_Reference.getSelectedItem().toString());

		/* Check if fields should be editable */
		fieldsEditable = UcConf.AdcCfg[selectedAdc].getSelected().getBoolean();
		textField_CodeName.setEnabled(fieldsEditable);
		comboBox_Sample.setEnabled(fieldsEditable);
		comboBox_Clock.setEnabled(fieldsEditable);
		comboBox_Justification.setEnabled(fieldsEditable);
		comboBox_Prescaler.setEnabled(fieldsEditable);
		comboBox_Resolution.setEnabled(fieldsEditable);
		comboBox_Reference.setEnabled(fieldsEditable);
		populateDynamicAdcElements();
	}

	private void populateDynamicAdcElements() {
		AdcConf adcCfg = UcConf.AdcCfg[selectedAdc];
		int chanTotal = adcCfg.getChannelsNum();

		for (int chanNum = 0; chanNum < chanTotal; chanNum++) {
			AdcChannel channel = adcCfg.getChannel(chanNum);
			boolean isEditable = channel.getSelected().getBoolean() && adcCfg.getSelected().getBoolean();

			checkBox_ChannelSelected[chanNum].setSelected(isEditable);
			checkBox_ChannelSelected[chanNum].setEnabled(adcCfg.getSelected().getBoolean());

			lbl_ChannelName[chanNum].setText(channel.getName());
			lbl_ChannelName[chanNum].setEnabled(isEditable);

			lbl_ChannelPin[chanNum].setText(UcConf.getPin(channel.getPinIndex()).getName());
			lbl_ChannelPin[chanNum].setEnabled(isEditable);

			textFieldChannelCodeName[chanNum].setText(channel.getCodeName());
			textFieldChannelCodeName[chanNum].setEnabled(isEditable);
		}

		for (int chanNum = 0; chanNum < Microcontroller.MAX_NUMBER_OF_ADCS; chanNum++) {
			boolean channelExists = chanNum < UcConf.AdcCfg[selectedAdc].getChannelsNum();

			checkBox_ChannelSelected[chanNum].setVisible(channelExists);
			lbl_ChannelName[chanNum].setVisible(channelExists);
			lbl_ChannelPin[chanNum].setVisible(channelExists);
			textFieldChannelCodeName[chanNum].setVisible(channelExists);
		}
	}

	private void dynamicElementsChange() {
		int chanTotal = UcConf.AdcCfg[selectedAdc].getChannelsNum();

		for (int chanNum = 0; chanNum < chanTotal; chanNum++) {
			boolean isSelected = checkBox_ChannelSelected[chanNum].isSelected();
			UcConf.AdcCfg[selectedAdc].getChannel(chanNum).setSelected(Selected.getConfFromBoolean(isSelected));
			UcConf.AdcCfg[selectedAdc].getChannel(chanNum).setCodeName(textFieldChannelCodeName[chanNum].getText());

			lbl_ChannelName[chanNum].setEnabled(isSelected);
			lbl_ChannelPin[chanNum].setEnabled(isSelected);
			textFieldChannelCodeName[chanNum].setEnabled(isSelected);
		}
	}

}
