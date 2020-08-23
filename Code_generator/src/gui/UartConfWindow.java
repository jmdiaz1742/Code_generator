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
import configurator.Selected;
import configurator.UartConf;
import javax.swing.JCheckBox;

public class UartConfWindow {

	private final long MIN_BAUD_RATE = 0x0L;
	private final long MAX_BAUD_RATE = 0xFFFFFFFFL;

	private JFrame frmUartsConfiguration;
	private JScrollPane scrollPane;
	private JPanel panel;
	private Microcontroller UcConf;
	private boolean GuiRefreshLocked = true;
	private int selectedUart;
	private JLabel lbl_BaudRateValidity;

	/* Static GUI elements */
	private JComboBox<String> comboBox_SelectUart;
	private JCheckBox chckbx_Selected;
	private JTextField textField_CodeName;
	private JTextField textField_BaudRate;
	private JComboBox<String> comboBox_Clock;
	private JComboBox<String> comboBox_Prescaler;
	private JComboBox<String> comboBox_DataBits;
	private JComboBox<String> comboBox_StopBits;
	private JComboBox<String> comboBox_Parity;

	/**
	 * Launch the application.
	 * 
	 * @param args General arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UartConfWindow window = new UartConfWindow(null);
					window.frmUartsConfiguration.setVisible(true);
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
	public UartConfWindow(Microcontroller uCtrl) {
		UcConf = uCtrl;
		initialize();
		initUartComboBox();
//		initDynamicAdcElements();
		selectUart();
		frmUartsConfiguration.setVisible(true);
		GuiRefreshLocked = false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUartsConfiguration = new JFrame();
		frmUartsConfiguration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmUartsConfiguration.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				/* Begin Window closing */
				MainGui.setNewUC(UcConf);
				/* End Window closing */
			}
		});
		frmUartsConfiguration.setTitle(Messages.getString("UartConfWindow.frmUartsConfiguration.title")); //$NON-NLS-1$
		frmUartsConfiguration.setBounds(100, 100, 732, 385);
		frmUartsConfiguration.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmUartsConfiguration.getContentPane().setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		frmUartsConfiguration.getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblt_SelectUart = new JLabel(Messages.getString("UartConfWindow.lblt_SelectUart.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_SelectUart = new GridBagConstraints();
		gbc_lblt_SelectUart.anchor = GridBagConstraints.EAST;
		gbc_lblt_SelectUart.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_SelectUart.gridx = 0;
		gbc_lblt_SelectUart.gridy = 0;
		panel.add(lblt_SelectUart, gbc_lblt_SelectUart);

		comboBox_SelectUart = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_SelectUart = new GridBagConstraints();
		gbc_comboBox_SelectUart.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_SelectUart.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_SelectUart.gridx = 1;
		gbc_comboBox_SelectUart.gridy = 0;
		panel.add(comboBox_SelectUart, gbc_comboBox_SelectUart);
		comboBox_SelectUart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Port combo box click ******/
				if (!GuiRefreshLocked) {
					GuiRefreshLocked = true;
					selectUart();
					Features.verbosePrint("ADC selected: " + comboBox_SelectUart.getSelectedItem().toString() + "...");
					GuiRefreshLocked = false;
				}
				/****** End Port combo box click ******/
			}
		});

		JLabel lblt_Selected = new JLabel(Messages.getString("UartConfWindow.lblSelected.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Selected = new GridBagConstraints();
		gbc_lblt_Selected.anchor = GridBagConstraints.EAST;
		gbc_lblt_Selected.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Selected.gridx = 0;
		gbc_lblt_Selected.gridy = 1;
		panel.add(lblt_Selected, gbc_lblt_Selected);

		chckbx_Selected = new JCheckBox(Messages.getString("UartConfWindow.chckbxSelected.text")); //$NON-NLS-1$
		GridBagConstraints gbc_chckbx_Selected = new GridBagConstraints();
		gbc_chckbx_Selected.insets = new Insets(0, 0, 5, 5);
		gbc_chckbx_Selected.gridx = 1;
		gbc_chckbx_Selected.gridy = 1;
		panel.add(chckbx_Selected, gbc_chckbx_Selected);
		chckbx_Selected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin UART configuration change ******/
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
				/****** End UART configuration change ******/
			}
		});

		JLabel lblt_CodeName = new JLabel(Messages.getString("UartConfWindow.lblCodeName.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_CodeName = new GridBagConstraints();
		gbc_lblt_CodeName.anchor = GridBagConstraints.EAST;
		gbc_lblt_CodeName.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_CodeName.gridx = 0;
		gbc_lblt_CodeName.gridy = 2;
		panel.add(lblt_CodeName, gbc_lblt_CodeName);

		textField_CodeName = new JTextField();
		textField_CodeName.setText(Messages.getString("UartConfWindow.textField.text")); //$NON-NLS-1$
		GridBagConstraints gbc_textField_CodeName = new GridBagConstraints();
		gbc_textField_CodeName.insets = new Insets(0, 0, 5, 5);
		gbc_textField_CodeName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_CodeName.gridx = 1;
		gbc_textField_CodeName.gridy = 2;
		panel.add(textField_CodeName, gbc_textField_CodeName);
		textField_CodeName.setColumns(10);
		textField_CodeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin UART configuration change ******/
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
				/****** End UART configuration change ******/
			}
		});

		textField_CodeName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
			}
		});

		JLabel lblt_Clock = new JLabel(Messages.getString("UartConfWindow.lblClock.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Clock = new GridBagConstraints();
		gbc_lblt_Clock.anchor = GridBagConstraints.EAST;
		gbc_lblt_Clock.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Clock.gridx = 0;
		gbc_lblt_Clock.gridy = 3;
		panel.add(lblt_Clock, gbc_lblt_Clock);

		comboBox_Clock = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Clock = new GridBagConstraints();
		gbc_comboBox_Clock.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Clock.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Clock.gridx = 1;
		gbc_comboBox_Clock.gridy = 3;
		panel.add(comboBox_Clock, gbc_comboBox_Clock);
		comboBox_Clock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin UART configuration change ******/
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
				/****** End UART configuration change ******/
			}
		});

		JLabel lblt_Prescaler = new JLabel(Messages.getString("UartConfWindow.lblPrescaler.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Prescaler = new GridBagConstraints();
		gbc_lblt_Prescaler.anchor = GridBagConstraints.EAST;
		gbc_lblt_Prescaler.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Prescaler.gridx = 0;
		gbc_lblt_Prescaler.gridy = 4;
		panel.add(lblt_Prescaler, gbc_lblt_Prescaler);

		comboBox_Prescaler = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Prescaler = new GridBagConstraints();
		gbc_comboBox_Prescaler.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Prescaler.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Prescaler.gridx = 1;
		gbc_comboBox_Prescaler.gridy = 4;
		panel.add(comboBox_Prescaler, gbc_comboBox_Prescaler);
		comboBox_Prescaler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin UART configuration change ******/
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
				/****** End UART configuration change ******/
			}
		});

		JLabel lblt_BaudRate = new JLabel(Messages.getString("UartConfWindow.lblBaudRate.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_BaudRate = new GridBagConstraints();
		gbc_lblt_BaudRate.anchor = GridBagConstraints.EAST;
		gbc_lblt_BaudRate.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_BaudRate.gridx = 0;
		gbc_lblt_BaudRate.gridy = 5;
		panel.add(lblt_BaudRate, gbc_lblt_BaudRate);

		textField_BaudRate = new JTextField();
		textField_BaudRate.setEnabled(false);
		GridBagConstraints gbc_textField_BaudRate = new GridBagConstraints();
		gbc_textField_BaudRate.insets = new Insets(0, 0, 5, 5);
		gbc_textField_BaudRate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_BaudRate.gridx = 1;
		gbc_textField_BaudRate.gridy = 5;
		panel.add(textField_BaudRate, gbc_textField_BaudRate);
		textField_BaudRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin UART configuration change ******/
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
				/****** End UART configuration change ******/
			}
		});

		textField_BaudRate.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
			}
		});

		lbl_BaudRateValidity = new JLabel(Messages.getString("UartConfWindow.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lbl_BaudRateValidity = new GridBagConstraints();
		gbc_lbl_BaudRateValidity.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_BaudRateValidity.gridx = 2;
		gbc_lbl_BaudRateValidity.gridy = 5;
		panel.add(lbl_BaudRateValidity, gbc_lbl_BaudRateValidity);

		JLabel lblt_DataBits = new JLabel(Messages.getString("UartConfWindow.lblDataBits.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_DataBits = new GridBagConstraints();
		gbc_lblt_DataBits.anchor = GridBagConstraints.EAST;
		gbc_lblt_DataBits.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_DataBits.gridx = 0;
		gbc_lblt_DataBits.gridy = 6;
		panel.add(lblt_DataBits, gbc_lblt_DataBits);

		comboBox_DataBits = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_DataBits = new GridBagConstraints();
		gbc_comboBox_DataBits.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_DataBits.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_DataBits.gridx = 1;
		gbc_comboBox_DataBits.gridy = 6;
		panel.add(comboBox_DataBits, gbc_comboBox_DataBits);
		comboBox_DataBits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin UART configuration change ******/
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
				/****** End UART configuration change ******/
			}
		});

		JLabel lblt_StopBits = new JLabel(Messages.getString("UartConfWindow.lblStopBits.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_StopBits = new GridBagConstraints();
		gbc_lblt_StopBits.anchor = GridBagConstraints.EAST;
		gbc_lblt_StopBits.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_StopBits.gridx = 0;
		gbc_lblt_StopBits.gridy = 7;
		panel.add(lblt_StopBits, gbc_lblt_StopBits);

		comboBox_StopBits = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_StopBits = new GridBagConstraints();
		gbc_comboBox_StopBits.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_StopBits.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_StopBits.gridx = 1;
		gbc_comboBox_StopBits.gridy = 7;
		panel.add(comboBox_StopBits, gbc_comboBox_StopBits);
		comboBox_StopBits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin UART configuration change ******/
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
				/****** End UART configuration change ******/
			}
		});

		JLabel lblt_Parity = new JLabel(Messages.getString("UartConfWindow.lblParity.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Parity = new GridBagConstraints();
		gbc_lblt_Parity.anchor = GridBagConstraints.EAST;
		gbc_lblt_Parity.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Parity.gridx = 0;
		gbc_lblt_Parity.gridy = 8;
		panel.add(lblt_Parity, gbc_lblt_Parity);

		comboBox_Parity = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_Parity = new GridBagConstraints();
		gbc_comboBox_Parity.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Parity.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Parity.gridx = 1;
		gbc_comboBox_Parity.gridy = 8;
		panel.add(comboBox_Parity, gbc_comboBox_Parity);
		comboBox_Parity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin UART configuration change ******/
				if (!GuiRefreshLocked) {
					UartConfChanged();
				}
				/****** End UART configuration change ******/
			}
		});

	}

	/**
	 * Initialize UART selection comboBox
	 */
	private void initUartComboBox() {
		for (int uartNum = 0; uartNum < UcConf.getUc_uartNum(); uartNum++) {
			comboBox_SelectUart.addItem(UcConf.Uarts[uartNum]);
		}
	}

	private void selectUart() {
		int featNum = 0;
		int selectedItem = 0;
		UartConf uartCfg;
		boolean fieldsEditable;

		selectedUart = comboBox_SelectUart.getSelectedIndex();
		uartCfg = UcConf.UartCfg[selectedUart];
		fieldsEditable = uartCfg.getSelected().getBoolean();
		chckbx_Selected.setSelected(fieldsEditable);

		textField_CodeName.setText(uartCfg.getCodeName());
		textField_CodeName.setEnabled(fieldsEditable);

		comboBox_Clock.removeAllItems();
		for (featNum = 0; featNum < uartCfg.UartFeatures.getClockNum(); featNum++) {
			comboBox_Clock.addItem(uartCfg.UartFeatures.getClock(featNum));
			if (uartCfg.getClock().equals(comboBox_Clock.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Clock.setSelectedIndex(selectedItem);
		comboBox_Clock.setEnabled(fieldsEditable);

		comboBox_Prescaler.removeAllItems();
		for (featNum = 0; featNum < uartCfg.UartFeatures.getPrescalerNum(); featNum++) {
			comboBox_Prescaler.addItem(uartCfg.UartFeatures.getPrescaler(featNum));
			if (uartCfg.getPrescaler().equals(comboBox_Prescaler.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Prescaler.setSelectedIndex(selectedItem);
		comboBox_Prescaler.setEnabled(fieldsEditable);

		textField_BaudRate.setText(uartCfg.getBaudRate());
		textField_BaudRate.setEnabled(fieldsEditable);
		isBaudRateValid();

		comboBox_DataBits.removeAllItems();
		for (featNum = 0; featNum < uartCfg.UartFeatures.getDataBitsNum(); featNum++) {
			comboBox_DataBits.addItem(uartCfg.UartFeatures.getDataBits(featNum));
			if (uartCfg.getDataBits().equals(comboBox_DataBits.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_DataBits.setSelectedIndex(selectedItem);
		comboBox_DataBits.setEnabled(fieldsEditable);

		comboBox_StopBits.removeAllItems();
		for (featNum = 0; featNum < uartCfg.UartFeatures.getStopBitsNum(); featNum++) {
			comboBox_StopBits.addItem(uartCfg.UartFeatures.getStopBits(featNum));
			if (uartCfg.getStopBits().equals(comboBox_StopBits.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_StopBits.setSelectedIndex(selectedItem);
		comboBox_StopBits.setEnabled(fieldsEditable);

		comboBox_Parity.removeAllItems();
		for (featNum = 0; featNum < uartCfg.UartFeatures.getParityNum(); featNum++) {
			comboBox_Parity.addItem(uartCfg.UartFeatures.getParity(featNum));
			if (uartCfg.getParity().equals(comboBox_Parity.getItemAt(featNum).toString())) {
				selectedItem = featNum;
			}
		}
		comboBox_Parity.setSelectedIndex(selectedItem);
		comboBox_Parity.setEnabled(fieldsEditable);
	}

	/**
	 * Save UART's configuration to uC
	 */
	private void UartConfChanged() {
		boolean fieldsEditable = false;

		UcConf.UartCfg[selectedUart].setSelected(Selected.getConfFromBoolean(chckbx_Selected.isSelected()));
		fieldsEditable = UcConf.UartCfg[selectedUart].getSelected().getBoolean();
		/* Check if fields should be editable */
		textField_CodeName.setEnabled(fieldsEditable);
		comboBox_Clock.setEnabled(fieldsEditable);
		comboBox_Prescaler.setEnabled(fieldsEditable);
		textField_BaudRate.setEnabled(fieldsEditable);
		comboBox_DataBits.setEnabled(fieldsEditable);
		comboBox_StopBits.setEnabled(fieldsEditable);
		comboBox_Parity.setEnabled(fieldsEditable);

		if (fieldsEditable) {
			UcConf.UartCfg[selectedUart].setCodeName(textField_CodeName.getText());
			UcConf.UartCfg[selectedUart].setClock(comboBox_Clock.getSelectedItem().toString());
			UcConf.UartCfg[selectedUart].setPrescaler(comboBox_Prescaler.getSelectedItem().toString());
			if (isBaudRateValid()) {
				UcConf.UartCfg[selectedUart].setBaudRate(textField_BaudRate.getText());
			} else {
				UcConf.UartCfg[selectedUart].setBaudRate("0");
			}
			UcConf.UartCfg[selectedUart].setDataBits(comboBox_DataBits.getSelectedItem().toString());
			UcConf.UartCfg[selectedUart].setStopBits(comboBox_StopBits.getSelectedItem().toString());
			UcConf.UartCfg[selectedUart].setParity(comboBox_Parity.getSelectedItem().toString());
		}
	}

	@SuppressWarnings("deprecation")
	private boolean isBaudRateValid() {
		boolean isValid = true;
		String strBaudRate;
		long numBaudRate = 0;

		strBaudRate = textField_BaudRate.getText();
		if (isValid && strBaudRate == null) {
			isValid = false;
		}
		if (isValid && strBaudRate == "") {
			isValid = false;
		}
		if (isValid) {
			try {
				numBaudRate = (long) (new Long(strBaudRate));
			} catch (NumberFormatException e) {
				isValid = false;
			}
		}
		if (isValid && numBaudRate < MIN_BAUD_RATE) {
			isValid = false;
		}
		if (isValid && numBaudRate > MAX_BAUD_RATE) {
			isValid = false;
		}

		if (isValid) {
			lbl_BaudRateValidity.setText("Baud Rate OK");
		} else {
			lbl_BaudRateValidity.setText("Baud Rate Invalid");
		}

		return isValid;
	}

}
