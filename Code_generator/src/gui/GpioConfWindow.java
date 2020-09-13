package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.ErrorCode;
import common.Features;
import configurator.PinConf;
import configurator.Selected;
import configurator.ADC.AdcChannel;
import configurator.GPIO.AltMode;
import configurator.GPIO.Mode;
import configurator.GPIO.OutType;
import configurator.GPIO.OutLevel;
import configurator.GPIO.Pull;
import configurator.GPIO.Speed;
import microcontroller.Microcontroller;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

/**
 * Window for configuring GPIO pins
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class GpioConfWindow {
	/* Private fields */
	private JFrame frmGpiosConfiguration;
	private Microcontroller UcConf;
	private boolean GuiRefreshLocked = true;
	private String SelectedPort;
	private int PortPinsTotal;

	/* Static GUI elements */
	private JLabel lblt_SelectPort;
	private JComboBox<String> comboBox_PortSelection;
	private JLabel lblt_Selected;
	private JLabel lblt_Pin;
	private JLabel lblt_Mode;
	private JLabel lblt_AltMode;
	private JLabel lblt_OutputType;
	private JLabel lblt_OutputLevel;
	private JLabel lblt_PullResistor;
	private JLabel lblSpeed;
	private JLabel lblCodename;
	private JScrollPane scrollPane;
	private JPanel panel;

	/* Dynamic GUI elements */
	private JLabel[] lbl_PinName;
	private JCheckBox[] checkBox_PinSelected;
	private JComboBox<String>[] comboBox_PinMode;
	private JComboBox<String>[] comboBox_PinAltMode;
	private JComboBox<String>[] comboBox_PinOutType;
	private JComboBox<String>[] comboBox_PinOutLevel;
	private JComboBox<String>[] comboBox_PinPull;
	private JComboBox<String>[] comboBox_PinSpeed;
	private JTextField[] textField_CodeName;

	/* GUI Constants */
	private static final int PIN_SELECTED_LABEL_INIT_POS_X = 0;
	private static final int PIN_SELECTED_LABEL_INIT_POS_Y = 3;
	private static final int PIN_NAME_LABEL_INIT_POS_X = 1;
	private static final int PIN_NAME_LABEL_INIT_POS_Y = 3;
	private static final int PIN_MODE_CBOX_INIT_POS_X = 2;
	private static final int PIN_MODE_CBOX_INIT_POS_Y = 3;
	private static final int PIN_ALT_MODE_CBOX_INIT_POS_X = 3;
	private static final int PIN_ALT_MODE_CBOX_INIT_POS_Y = 3;
	private static final int PIN_OUT_TYPE_CBOX_INIT_POS_X = 4;
	private static final int PIN_OUT_TYPE_CBOX_INIT_POS_Y = 3;
	private static final int PIN_OUT_LEVEL_CBOX_INIT_POS_X = 5;
	private static final int PIN_OUT_LEVEL_CBOX_INIT_POS_Y = 3;
	private static final int PIN_PULL_CBOX_INIT_POS_X = 6;
	private static final int PIN_PULL_CBOX_INIT_POS_Y = 3;
	private static final int PIN_SPEED_CBOX_INIT_POS_X = 7;
	private static final int PIN_SPEED_CBOX_INIT_POS_Y = 3;
	private static final int PIN_CODE_NAME_TXTF_INIT_POS_X = 8;
	private static final int PIN_CODE_NAME_TXTF_INIT_POS_Y = 3;

	/**
	 * Gpio configuration window main
	 * 
	 * @param args Init parameters
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
	 * 
	 * @param uCtrl Microcontroller object containing all pin's information
	 */
	public GpioConfWindow(Microcontroller uCtrl) {
		UcConf = uCtrl;

		if (!UcConf.isValid()) {
			Features.verbosePrint("Wrong microcontroller configuration...");
			JOptionPane.showMessageDialog(frmGpiosConfiguration, "Wrong microcontroller configuration",
					"Configuration error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		initialize();
		initPortsComboBox();
		selectPort();
		initDynamicPinElements();
		populateDynamicPinElements();
		frmGpiosConfiguration.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmGpiosConfiguration.setVisible(true);
		GuiRefreshLocked = false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGpiosConfiguration = new JFrame();
		frmGpiosConfiguration.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				/* Begin Window closing */
				MainGui.setNewUC(UcConf);
				/* End Window closing */
			}
		});
		frmGpiosConfiguration.setTitle(Messages.getString("GpioConfWindow.frmGpiosConfiguration.title")); //$NON-NLS-1$
		frmGpiosConfiguration.setBounds(100, 100, 980, 740);
		frmGpiosConfiguration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGpiosConfiguration.getContentPane().setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		frmGpiosConfiguration.getContentPane().add(scrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0 };
		panel.setLayout(gbl_panel);

		lblt_SelectPort = new JLabel(Messages.getString("GpioConfWindow.lblPort.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_SelectPort = new GridBagConstraints();
		gbc_lblt_SelectPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblt_SelectPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_SelectPort.gridx = 0;
		gbc_lblt_SelectPort.gridy = 0;
		panel.add(lblt_SelectPort, gbc_lblt_SelectPort);

		comboBox_PortSelection = new JComboBox<String>();
		comboBox_PortSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Port combo box click ******/
				if (!GuiRefreshLocked) {
					GuiRefreshLocked = true;
					selectPort();
					populateDynamicPinElements();
					Features.verbosePrint(
							"Port selected: " + comboBox_PortSelection.getSelectedItem().toString() + "...");
					GuiRefreshLocked = false;
				}
				/****** End Port combo box click ******/
			}
		});
		GridBagConstraints gbc_comboBox_PortSelection = new GridBagConstraints();
		gbc_comboBox_PortSelection.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_PortSelection.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_PortSelection.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_PortSelection.gridx = 1;
		gbc_comboBox_PortSelection.gridy = 0;
		panel.add(comboBox_PortSelection, gbc_comboBox_PortSelection);
		lblt_Selected = new JLabel(Messages.getString("GpioConfWindow.lblSelected.text")); //$NON-NLS-1$
		lblt_Selected.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSelected = new GridBagConstraints();
		gbc_lblSelected.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSelected.anchor = GridBagConstraints.SOUTH;
		gbc_lblSelected.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelected.gridx = 0;
		gbc_lblSelected.gridy = 2;
		panel.add(lblt_Selected, gbc_lblSelected);

		lblt_Pin = new JLabel(Messages.getString("GpioConfWindow.lblPin.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Pin = new GridBagConstraints();
		gbc_lblt_Pin.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblt_Pin.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Pin.gridx = 1;
		gbc_lblt_Pin.gridy = 2;
		panel.add(lblt_Pin, gbc_lblt_Pin);

		lblt_Mode = new JLabel(Messages.getString("GpioConfWindow.lblMode.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Mode = new GridBagConstraints();
		gbc_lblt_Mode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblt_Mode.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Mode.gridx = 2;
		gbc_lblt_Mode.gridy = 2;
		panel.add(lblt_Mode, gbc_lblt_Mode);

		lblt_AltMode = new JLabel(Messages.getString("GpioConfWindow.lblAltMode.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_AltMode = new GridBagConstraints();
		gbc_lblt_AltMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblt_AltMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_AltMode.gridx = 3;
		gbc_lblt_AltMode.gridy = 2;
		panel.add(lblt_AltMode, gbc_lblt_AltMode);

		lblt_OutputType = new JLabel(Messages.getString("GpioConfWindow.lblOutputType.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_OutputType = new GridBagConstraints();
		gbc_lblt_OutputType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblt_OutputType.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_OutputType.gridx = 4;
		gbc_lblt_OutputType.gridy = 2;
		panel.add(lblt_OutputType, gbc_lblt_OutputType);

		lblt_OutputLevel = new JLabel(Messages.getString("GpioConfWindow.lblOutputLevel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_OutputLevel = new GridBagConstraints();
		gbc_lblt_OutputLevel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblt_OutputLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_OutputLevel.gridx = 5;
		gbc_lblt_OutputLevel.gridy = 2;
		panel.add(lblt_OutputLevel, gbc_lblt_OutputLevel);

		lblt_PullResistor = new JLabel(Messages.getString("GpioConfWindow.lblPullResistor.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_PullResistor = new GridBagConstraints();
		gbc_lblt_PullResistor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblt_PullResistor.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_PullResistor.gridx = 6;
		gbc_lblt_PullResistor.gridy = 2;
		panel.add(lblt_PullResistor, gbc_lblt_PullResistor);

		lblSpeed = new JLabel(Messages.getString("GpioConfWindow.lblSpeed.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblSpeed = new GridBagConstraints();
		gbc_lblSpeed.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpeed.gridx = 7;
		gbc_lblSpeed.gridy = 2;
		panel.add(lblSpeed, gbc_lblSpeed);

		lblCodename = new JLabel(Messages.getString("GpioConfWindow.lblCodename.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblCodename = new GridBagConstraints();
		gbc_lblCodename.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCodename.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodename.gridx = 8;
		gbc_lblCodename.gridy = 2;
		panel.add(lblCodename, gbc_lblCodename);
	}

	/**
	 * Initialize elements in the ports selection combo box
	 */
	private void initPortsComboBox() {
		for (int portNum = 0; portNum < UcConf.getUc_portNum(); portNum++) {
			comboBox_PortSelection.addItem(UcConf.Ports[portNum]);
		}
	}

	/**
	 * Initialize all dynamic pins elements as empty
	 */
	@SuppressWarnings("unchecked") // FIXME: Look for way to properly initialize the JComboBox array
	private void initDynamicPinElements() {
		Features.verbosePrint("Initializing dynamic Pin elements...");
		checkBox_PinSelected = new JCheckBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		lbl_PinName = new JLabel[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinMode = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinAltMode = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinOutType = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinOutLevel = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinPull = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinSpeed = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		textField_CodeName = new JTextField[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];

		for (int pinNum = 0; pinNum < Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT; pinNum++) {

			/* Pin's selected */
			checkBox_PinSelected[pinNum] = new JCheckBox();
			GridBagConstraints gbc_chkb_PinSelected = new GridBagConstraints();
			gbc_chkb_PinSelected.insets = new Insets(0, 0, 5, 5);
			gbc_chkb_PinSelected.gridx = PIN_SELECTED_LABEL_INIT_POS_X;
			gbc_chkb_PinSelected.gridy = PIN_SELECTED_LABEL_INIT_POS_Y + pinNum;
			panel.add(checkBox_PinSelected[pinNum], gbc_chkb_PinSelected);
			checkBox_PinSelected[pinNum].setVisible(false);
			checkBox_PinSelected[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Pin's mode combo box click ******/
					if (!GuiRefreshLocked) {
						selectedChange();
					}
					/****** End Pin's mode combo box click ******/
				}
			});

			/* Pin's name */
			lbl_PinName[pinNum] = new JLabel("Pin " + pinNum);
			GridBagConstraints gbc_lbl_PinName = new GridBagConstraints();
			gbc_lbl_PinName.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_PinName.gridx = PIN_NAME_LABEL_INIT_POS_X;
			gbc_lbl_PinName.gridy = PIN_NAME_LABEL_INIT_POS_Y + pinNum;
			panel.add(lbl_PinName[pinNum], gbc_lbl_PinName);
			lbl_PinName[pinNum].setVisible(false);

			/* Pin's mode */
			comboBox_PinMode[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinMode = new GridBagConstraints();
			gbc_comboBox_PinMode.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinMode.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinMode.gridx = PIN_MODE_CBOX_INIT_POS_X;
			gbc_comboBox_PinMode.gridy = PIN_MODE_CBOX_INIT_POS_Y + pinNum;
			panel.add(comboBox_PinMode[pinNum], gbc_comboBox_PinMode);
			comboBox_PinMode[pinNum].setVisible(false);
			comboBox_PinMode[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Pin's mode combo box click ******/
					if (!GuiRefreshLocked) {
						modeChange();
					}
					/****** End Pin's mode combo box click ******/
				}
			});

			/* Pin's alternate mode */
			comboBox_PinAltMode[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinAltMode = new GridBagConstraints();
			gbc_comboBox_PinAltMode.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinAltMode.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinAltMode.gridx = PIN_ALT_MODE_CBOX_INIT_POS_X;
			gbc_comboBox_PinAltMode.gridy = PIN_ALT_MODE_CBOX_INIT_POS_Y + pinNum;
			panel.add(comboBox_PinAltMode[pinNum], gbc_comboBox_PinAltMode);
			comboBox_PinAltMode[pinNum].setVisible(false);
			comboBox_PinAltMode[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Pin's mode combo box click ******/
					if (!GuiRefreshLocked) {
						altModeChange();
					}
					/****** End Pin's mode combo box click ******/
				}
			});

			/* Pin's output type */
			comboBox_PinOutType[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinOutType = new GridBagConstraints();
			gbc_comboBox_PinOutType.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinOutType.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinOutType.gridx = PIN_OUT_TYPE_CBOX_INIT_POS_X;
			gbc_comboBox_PinOutType.gridy = PIN_OUT_TYPE_CBOX_INIT_POS_Y + pinNum;
			panel.add(comboBox_PinOutType[pinNum], gbc_comboBox_PinOutType);
			comboBox_PinOutType[pinNum].setVisible(false);
			comboBox_PinOutType[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Pin's output combo box click ******/
					if (!GuiRefreshLocked) {
						outTypeChange();
					}
					/****** End Pin's output combo box click ******/
				}
			});

			/* Pin's output level */
			comboBox_PinOutLevel[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinOutLevel = new GridBagConstraints();
			gbc_comboBox_PinOutLevel.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinOutLevel.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinOutLevel.gridx = PIN_OUT_LEVEL_CBOX_INIT_POS_X;
			gbc_comboBox_PinOutLevel.gridy = PIN_OUT_LEVEL_CBOX_INIT_POS_Y + pinNum;
			panel.add(comboBox_PinOutLevel[pinNum], gbc_comboBox_PinOutLevel);
			comboBox_PinOutLevel[pinNum].addItem(OutLevel.LOW.name());
			comboBox_PinOutLevel[pinNum].addItem(OutLevel.HIGH.name());
			comboBox_PinOutLevel[pinNum].setVisible(false);
			comboBox_PinOutLevel[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Pin's output level combo box click ******/
					if (!GuiRefreshLocked) {
						outLevelChange();
					}
					/****** End Pin's output level combo box click ******/
				}
			});

			/* Pin's pull resistor */
			comboBox_PinPull[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinPull = new GridBagConstraints();
			gbc_comboBox_PinPull.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinPull.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinPull.gridx = PIN_PULL_CBOX_INIT_POS_X;
			gbc_comboBox_PinPull.gridy = PIN_PULL_CBOX_INIT_POS_Y + pinNum;
			panel.add(comboBox_PinPull[pinNum], gbc_comboBox_PinPull);
			comboBox_PinPull[pinNum].addItem(Pull.PULL_UP.name());
			comboBox_PinPull[pinNum].addItem(Pull.PULL_DOWN.name());
			comboBox_PinPull[pinNum].addItem(Pull.PULL_NOT_AVAILABLE.name());
			comboBox_PinPull[pinNum].setVisible(false);
			comboBox_PinPull[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Pin's pull resistor combo box click ******/
					if (!GuiRefreshLocked) {
						pullChange();
					}
					/****** End Pin's pull resistor combo box click ******/
				}
			});

			/* Pin's speed */
			comboBox_PinSpeed[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinSpeed = new GridBagConstraints();
			gbc_comboBox_PinSpeed.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinSpeed.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinSpeed.gridx = PIN_SPEED_CBOX_INIT_POS_X;
			gbc_comboBox_PinSpeed.gridy = PIN_SPEED_CBOX_INIT_POS_Y + pinNum;
			panel.add(comboBox_PinSpeed[pinNum], gbc_comboBox_PinSpeed);
			comboBox_PinSpeed[pinNum].addItem(Speed.SPEED_FAST.name());
			comboBox_PinSpeed[pinNum].addItem(Speed.SPEED_MEDIUM.name());
			comboBox_PinSpeed[pinNum].addItem(Speed.SPEED_HIGH.name());
			comboBox_PinSpeed[pinNum].addItem(Speed.SPEED_NOT_AVAILABLE.name());
			comboBox_PinSpeed[pinNum].setVisible(false);
			comboBox_PinSpeed[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Pin's speed combo box click ******/
					if (!GuiRefreshLocked) {
						speedChange();
					}
					/****** End Pin's speed combo box click ******/
				}
			});

			/* Pin's Code Name */
			textField_CodeName[pinNum] = new JTextField(20);
			GridBagConstraints gbc_textField_CodeName = new GridBagConstraints();
			gbc_textField_CodeName.insets = new Insets(0, 0, 5, 5);
			gbc_textField_CodeName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_CodeName.gridx = PIN_CODE_NAME_TXTF_INIT_POS_X;
			gbc_textField_CodeName.gridy = PIN_CODE_NAME_TXTF_INIT_POS_Y + pinNum;
			panel.add(textField_CodeName[pinNum], gbc_textField_CodeName);
			textField_CodeName[pinNum].setVisible(false);
			textField_CodeName[pinNum].getDocument().addDocumentListener(new DocumentListener() {
				/****** Begin Pin's Code Name change ******/

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					if (!GuiRefreshLocked) {
						codeNameChange();
					}
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					if (!GuiRefreshLocked) {
						codeNameChange();
					}
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					if (!GuiRefreshLocked) {
						codeNameChange();
					}
				}
				/****** End Pin's Code Name change ******/
			});
		}
	}

	/**
	 * Get the selected port and its properties
	 */
	private void selectPort() {
		SelectedPort = comboBox_PortSelection.getSelectedItem().toString();
		PortPinsTotal = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				PortPinsTotal++;
			}
		}
	}

	/**
	 * Populate all dynamic pin elements
	 */
	private void populateDynamicPinElements() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {

				/* Pin's Selected */
				checkBox_PinSelected[portPinNum].setSelected(UcConf.GpioCfgPin[pinNum].getSelected().getBoolean());
				checkBox_PinSelected[portPinNum].setVisible(true);

				/* Pin's name */
				lbl_PinName[portPinNum].setText(UcConf.GpioCfgPin[pinNum].getPinName());
				lbl_PinName[portPinNum].setVisible(true);

				/* Pin's mode */
				comboBox_PinMode[portPinNum].removeAllItems();
				comboBox_PinMode[portPinNum].addItem(Mode.MODE_INPUT.name());
				comboBox_PinMode[portPinNum].addItem(Mode.MODE_OUTPUT.name());
				if (UcConf.GpioCfgPin[pinNum].isAv_altFunc()) {
					comboBox_PinMode[portPinNum].addItem(Mode.MODE_ALTERNATE_FUNCTION.name());
				}
				comboBox_PinMode[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getMode().name());
				comboBox_PinMode[portPinNum].setVisible(true);
				comboBox_PinMode[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());

				/* Pin's alternate mode */
				comboBox_PinAltMode[portPinNum].removeAllItems();
				if (UcConf.GpioCfgPin[pinNum].isAv_Adc()) {
					comboBox_PinAltMode[portPinNum].addItem(AltMode.ALT_MODE_ANALOG.name());
				}
				if (UcConf.GpioCfgPin[pinNum].isAv_Uart()) {
					comboBox_PinAltMode[portPinNum].addItem(AltMode.ALT_MODE_UART.name());
				}
				if (UcConf.GpioCfgPin[pinNum].isAv_I2c()) {
					comboBox_PinAltMode[portPinNum].addItem(AltMode.ALT_MODE_I2C.name());
				}
				if (UcConf.GpioCfgPin[pinNum].isAv_Spi()) {
					comboBox_PinAltMode[portPinNum].addItem(AltMode.ALT_MODE_SPI.name());
				}
				comboBox_PinAltMode[portPinNum].addItem(AltMode.ALT_MODE_NONE.name());
				comboBox_PinAltMode[portPinNum].setVisible(true);
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_ALTERNATE_FUNCTION.name())) {
					comboBox_PinAltMode[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				} else {
					comboBox_PinAltMode[portPinNum].setEnabled(false);
				}

				/* Pin's output type */
				comboBox_PinOutType[portPinNum].removeAllItems();
				comboBox_PinOutType[portPinNum].addItem(OutType.OTYPE_PUSH_PULL.name());
				comboBox_PinOutType[portPinNum].addItem(OutType.OTYPE_OPEN_DRAIN.name());
				comboBox_PinOutType[portPinNum].setVisible(true);
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name())) {
					/* Set selected output type */
					comboBox_PinOutType[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getOutType().name());
					comboBox_PinOutType[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				} else {
					comboBox_PinOutType[portPinNum].setEnabled(false);
				}

				/* Pin's output level */
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name())
						&& comboBox_PinOutType[portPinNum].getSelectedItem().equals(OutType.OTYPE_PUSH_PULL.name())) {
					comboBox_PinOutLevel[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getOutLevel().name());
					comboBox_PinOutLevel[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				} else {
					comboBox_PinOutLevel[portPinNum].setEnabled(false);
				}
				comboBox_PinOutLevel[portPinNum].setVisible(true);

				/* Pin's pull resistor */
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_INPUT.name())) {
					comboBox_PinPull[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getPull().name());
					comboBox_PinPull[portPinNum].setEnabled(true);
				} else {
					comboBox_PinPull[portPinNum].setEnabled(false);
				}
				comboBox_PinPull[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				comboBox_PinPull[portPinNum].setVisible(true);

				/* Pin's speed resistor */
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name())) {
					comboBox_PinSpeed[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getSpeed().name());
					comboBox_PinSpeed[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				} else {
					comboBox_PinSpeed[portPinNum].setEnabled(false);
				}
				comboBox_PinSpeed[portPinNum].setVisible(true);

				/* Pin's Code name */
				textField_CodeName[portPinNum].setText(UcConf.GpioCfgPin[pinNum].getCodeName());
				textField_CodeName[portPinNum].setVisible(true);
				textField_CodeName[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());

				portPinNum++;
			}
		}
		for (int pinNum = PortPinsTotal; pinNum < Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT; pinNum++) {
			checkBox_PinSelected[pinNum].setVisible(false);
			lbl_PinName[pinNum].setVisible(false);
			comboBox_PinMode[pinNum].setVisible(false);
			comboBox_PinAltMode[pinNum].setVisible(false);
			comboBox_PinOutType[pinNum].setVisible(false);
			comboBox_PinOutLevel[pinNum].setVisible(false);
			comboBox_PinPull[pinNum].setVisible(false);
			comboBox_PinSpeed[pinNum].setVisible(false);
			textField_CodeName[pinNum].setVisible(false);
		}
	}

	private void selectedChange() {
		updateSelected();
		updateMode();
		updateOutputType();
		updateOutLevel();
		updatePull();
		updateSpeed();
	}

	/**
	 * Reflect the pin's mode changes
	 */
	private void modeChange() {

		checkAdcsConf();

		updateMode();
		updateAltMode();
		updateOutputType();
		updateOutLevel();
		updatePull();
		updateSpeed();
	}

	/**
	 * Reflect the pin's alternate mode change
	 */
	private void altModeChange() {
		checkAdcsConf();

		updateAltMode();
	}

	/**
	 * Reflect the pin's output type changes
	 */
	private void outTypeChange() {
		updateOutLevel();
	}

	/**
	 * Save pins' output levels
	 */
	private void outLevelChange() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setOutLevel(
						OutLevel.getConfFromString(comboBox_PinOutLevel[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	/**
	 * Save pin's pull resistors configurations
	 */
	private void pullChange() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum]
						.setPull(Pull.getConfFromString(comboBox_PinPull[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	/**
	 * Save the pins' speed
	 */
	private void speedChange() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum]
						.setSpeed(Speed.getConfFromString(comboBox_PinSpeed[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	/**
	 * Save the pin's code name
	 */
	private void codeNameChange() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setCodeName(textField_CodeName[portPinNum].getText());
				portPinNum++;
			}
		}
	}

	private void updateSelected() {
		int portPinNum = 0;
		boolean selection;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				selection = checkBox_PinSelected[portPinNum].isSelected();

				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum]
						.setSelected(Selected.getConfFromBoolean(checkBox_PinSelected[portPinNum].isSelected()));

				/* Set enabled/disabled settings */
				comboBox_PinMode[portPinNum].setEnabled(selection);
				comboBox_PinOutType[portPinNum].setEnabled(selection);
				comboBox_PinOutLevel[portPinNum].setEnabled(selection);
				comboBox_PinPull[portPinNum].setEnabled(selection);
				comboBox_PinSpeed[portPinNum].setEnabled(selection);
				textField_CodeName[portPinNum].setEnabled(selection);

				portPinNum++;
			}
		}
	}

	/**
	 * Reflect the pin's Mode changes
	 */
	private void updateMode() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_ALTERNATE_FUNCTION.name())) {
					comboBox_PinAltMode[portPinNum].setEnabled(true);
				} else {
					comboBox_PinAltMode[portPinNum].setEnabled(false);
				}

				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum]
						.setMode(Mode.getConfFromString(comboBox_PinMode[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	/**
	 * Reflect the pin's Alternate Mode changes
	 */
	private void updateAltMode() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setAltMode(
						AltMode.getConfFromString(comboBox_PinAltMode[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	/**
	 * Update output type combo boxes
	 */
	private void updateOutputType() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {

				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name())) {
					/* Set selected output type */
					comboBox_PinOutType[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getOutType().ordinal());
					comboBox_PinOutType[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				} else {
					comboBox_PinOutType[portPinNum].setEnabled(false);
				}

				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setOutType(
						OutType.getConfFromString(comboBox_PinOutType[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	/**
	 * Update output level combo boxes
	 */
	private void updateOutLevel() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name())) {
					comboBox_PinOutLevel[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				} else {
					comboBox_PinOutLevel[portPinNum].setEnabled(false);
				}

				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setOutLevel(
						OutLevel.getConfFromString(comboBox_PinOutLevel[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	/**
	 * Update pull resistor combo boxes
	 */
	private void updatePull() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_INPUT.name())) {
					/* Set selected pull resistor */
					comboBox_PinPull[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getOutType().name());
					comboBox_PinPull[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				} else {
					comboBox_PinPull[portPinNum].setEnabled(false);
				}

				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum]
						.setPull(Pull.getConfFromString(comboBox_PinPull[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	/**
	 * update speed combo boxes
	 */
	private void updateSpeed() {
		int portPinNum = 0;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name())) {
					/* Set selected speed */
					comboBox_PinSpeed[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getSpeed().name());
					comboBox_PinSpeed[portPinNum].setEnabled(checkBox_PinSelected[portPinNum].isSelected());
				} else {
					comboBox_PinSpeed[portPinNum].setEnabled(false);
				}

				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum]
						.setSpeed(Speed.getConfFromString(comboBox_PinSpeed[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}

	private void checkAdcsConf() {
		int portPinNum = 0;

		GuiRefreshLocked = true;

		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				PinConf pin = UcConf.GpioCfgPin[pinNum];
				boolean checkAdc = (pin.getSelected() == Selected.YES && pin.isAv_Adc())
						&& ((pin.getMode() == Mode.MODE_ALTERNATE_FUNCTION && !comboBox_PinMode[portPinNum]
								.getSelectedItem().toString().equals(Mode.MODE_ALTERNATE_FUNCTION.name()))
								|| (pin.getAltMode() == AltMode.ALT_MODE_ANALOG && !comboBox_PinAltMode[portPinNum]
										.getSelectedItem().toString().equals(AltMode.ALT_MODE_ANALOG.name())));

				if (checkAdc) {

					/* Get ADC Channel */
					int adcIndex = ErrorCode.INT_INVALID_INDEX;
					int channelIndex = ErrorCode.INT_INVALID_INDEX;

					for (int adcNum = 0; adcNum < UcConf.getUc_adcNum(); adcNum++) {
						for (int chaNum = 0; chaNum < UcConf.AdcCfg[adcNum].getChannelsNum(); chaNum++) {
							AdcChannel channel = UcConf.AdcCfg[adcNum].getChannel(chaNum);
							if (channel.getPinIndex() == pin.getIndex()) {
								adcIndex = adcNum;
								channelIndex = chaNum;
								break;
							}
						}
					}

					String message = "Pin " + pin.getPinName() + " is currently configured as ADC\r\n" + "ADC: "
							+ UcConf.Adcs[adcIndex] + "\r\n" + "Channel: "
							+ UcConf.AdcCfg[adcIndex].getChannel(channelIndex).getName() + "\r\n"
							+ "Do you want to deselct the channel?";
					int result = JOptionPane.showConfirmDialog(frmGpiosConfiguration, message, "ADC configured",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (result == JOptionPane.YES_OPTION) {
						UcConf.AdcCfg[adcIndex].getChannel(channelIndex).setSelected(Selected.NOT);
					} else if (result == JOptionPane.NO_OPTION) {
						int modeIndex = ErrorCode.INT_INVALID_INDEX;
						int altModeIndex = ErrorCode.INT_INVALID_INDEX;

						for (int modeNum = 0; modeNum < comboBox_PinMode[portPinNum].getItemCount(); modeNum++) {
							if (comboBox_PinMode[portPinNum].getItemAt(modeNum)
									.equals(Mode.MODE_ALTERNATE_FUNCTION.name())) {
								modeIndex = modeNum;
								break;
							}
						}

						for (int altModeNum = 0; altModeNum < comboBox_PinAltMode[portPinNum]
								.getItemCount(); altModeNum++) {
							if (comboBox_PinAltMode[portPinNum].getItemAt(altModeNum)
									.equals(AltMode.ALT_MODE_ANALOG.name())) {
								altModeIndex = altModeNum;
								break;
							}
						}

						if (modeIndex != ErrorCode.INT_INVALID_INDEX && altModeIndex != ErrorCode.INT_INVALID_INDEX) {
							comboBox_PinMode[portPinNum].setSelectedIndex(modeIndex);
							comboBox_PinAltMode[portPinNum].setSelectedIndex(altModeIndex);
						}

					}
				}
				portPinNum++;
			}
		}
		GuiRefreshLocked = false;
	}

}
