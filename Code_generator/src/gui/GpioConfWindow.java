package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import common.Features;
import configurator.GPIO.Mode;
import configurator.GPIO.OutType;
import configurator.GPIO.OutLevel;
import configurator.GPIO.Pull;
import configurator.GPIO.Speed;
import microcontroller.Microcontroller;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Window for configuring GPIO pins
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
	
	/* Dynamic GUI elements */
	private JComboBox<String> comboBox_PortSelection;
	private JLabel lblt_Pin;
	private JLabel lblt_Mode;
	private JLabel lblt_OutputType;
	private JLabel lblt_OutputLevel;
	private JLabel lblt_PullResistor;
	private JLabel lblSpeed;
	private JLabel[] lbl_PinName;
	private JComboBox<String>[] comboBox_PinMode;
	private JComboBox<String>[] comboBox_PinOutType;
	private JComboBox<String>[] comboBox_PinOutLevel;
	private JComboBox<String>[] comboBox_PinPull;
	private JComboBox<String>[] comboBox_PinSpeed;
	
	/* GUI Constants */
	private static final int PIN_NAME_LABEL_INIT_POS_X		= 0;
	private static final int PIN_NAME_LABEL_INIT_POS_Y		= 3;
	private static final int PIN_MODE_CBOX_INIT_POS_X		= 1;
	private static final int PIN_MODE_CBOX_INIT_POS_Y		= 3;
	private static final int PIN_OUT_TYPE_CBOX_INIT_POS_X	= 2;
	private static final int PIN_OUT_TYPE_CBOX_INIT_POS_Y	= 3;
	private static final int PIN_OUT_LEVEL_CBOX_INIT_POS_X	= 3;
	private static final int PIN_OUT_LEVEL_CBOX_INIT_POS_Y	= 3;
	private static final int PIN_PULL_CBOX_INIT_POS_X		= 4;
	private static final int PIN_PULL_CBOX_INIT_POS_Y		= 3;
	private static final int PIN_SPEED_CBOX_INIT_POS_X		= 5;
	private static final int PIN_SPEED_CBOX_INIT_POS_Y		= 3;


	/**
	 * Gpio configuration window main
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
	 * @param uCtrl Microcontroller object containing all pin's information
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
		initPortsComboBox();
		selectPort();
		initDynamicPinElements();
		populateDynamicPinElements();
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
				MainGui.setUC(UcConf);
				/* End Window closing */
			}
		});
		frmGpiosConfiguration.setTitle(Messages.getString("GpioConfWindow.frmGpiosConfiguration.title")); //$NON-NLS-1$
		frmGpiosConfiguration.setBounds(100, 100, 980, 740);
		frmGpiosConfiguration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 169, 135, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmGpiosConfiguration.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblt_SelectPort = new JLabel(Messages.getString("GpioConfWindow.lblSelectPort.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_SelectPort = new GridBagConstraints();
		gbc_lblt_SelectPort.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_SelectPort.anchor = GridBagConstraints.WEST;
		gbc_lblt_SelectPort.gridx = 0;
		gbc_lblt_SelectPort.gridy = 0;
		frmGpiosConfiguration.getContentPane().add(lblt_SelectPort, gbc_lblt_SelectPort);
		
		comboBox_PortSelection = new JComboBox<String>();
		comboBox_PortSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Port combo box click ******/
				if (!GuiRefreshLocked) {
					GuiRefreshLocked = true;
					selectPort();
					populateDynamicPinElements();
					Features.verbosePrint("Port selected: " + comboBox_PortSelection.getSelectedItem().toString() + "...");
					GuiRefreshLocked = false;
				}
				/****** End Port combo box click ******/
			}
		});
		comboBox_PortSelection.setToolTipText(Messages.getString("GpioConfWindow.comboBox_PortSelection.toolTipText")); //$NON-NLS-1$
		GridBagConstraints gbc_comboBox_PortSelection = new GridBagConstraints();
		gbc_comboBox_PortSelection.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_PortSelection.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_PortSelection.gridx = 1;
		gbc_comboBox_PortSelection.gridy = 0;
		frmGpiosConfiguration.getContentPane().add(comboBox_PortSelection, gbc_comboBox_PortSelection);
		
		lblt_Pin = new JLabel(Messages.getString("GpioConfWindow.lblPin.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Pin = new GridBagConstraints();
		gbc_lblt_Pin.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Pin.gridx = 0;
		gbc_lblt_Pin.gridy = 2;
		frmGpiosConfiguration.getContentPane().add(lblt_Pin, gbc_lblt_Pin);
		
		lblt_Mode = new JLabel(Messages.getString("GpioConfWindow.lblMode.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Mode = new GridBagConstraints();
		gbc_lblt_Mode.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Mode.gridx = 1;
		gbc_lblt_Mode.gridy = 2;
		frmGpiosConfiguration.getContentPane().add(lblt_Mode, gbc_lblt_Mode);
		
		lblt_OutputType = new JLabel(Messages.getString("GpioConfWindow.lblOutputType.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_OutputType = new GridBagConstraints();
		gbc_lblt_OutputType.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_OutputType.gridx = 2;
		gbc_lblt_OutputType.gridy = 2;
		frmGpiosConfiguration.getContentPane().add(lblt_OutputType, gbc_lblt_OutputType);
		
		lblt_OutputLevel = new JLabel(Messages.getString("GpioConfWindow.lblOutputLevel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_OutputLevel = new GridBagConstraints();
		gbc_lblt_OutputLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_OutputLevel.gridx = 3;
		gbc_lblt_OutputLevel.gridy = 2;
		frmGpiosConfiguration.getContentPane().add(lblt_OutputLevel, gbc_lblt_OutputLevel);
		
		lblt_PullResistor = new JLabel(Messages.getString("GpioConfWindow.lblPullResistor.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_PullResistor = new GridBagConstraints();
		gbc_lblt_PullResistor.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_PullResistor.gridx = 4;
		gbc_lblt_PullResistor.gridy = 2;
		frmGpiosConfiguration.getContentPane().add(lblt_PullResistor, gbc_lblt_PullResistor);
		
		lblSpeed = new JLabel(Messages.getString("GpioConfWindow.lblSpeed.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblSpeed = new GridBagConstraints();
		gbc_lblSpeed.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpeed.gridx = 5;
		gbc_lblSpeed.gridy = 2;
		frmGpiosConfiguration.getContentPane().add(lblSpeed, gbc_lblSpeed);
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
		Features.verbosePrint("Initializing dynamic elements...");
		lbl_PinName = new JLabel[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinMode = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinOutType = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinOutLevel = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinPull = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		comboBox_PinSpeed = new JComboBox[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		
		for (int pinNum = 0; pinNum < Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT; pinNum++) {
			/* Pin's name */
			lbl_PinName[pinNum] = new JLabel("Pin " + pinNum);
			GridBagConstraints gbc_lbl_PinName = new GridBagConstraints();
			gbc_lbl_PinName.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_PinName.gridx = PIN_NAME_LABEL_INIT_POS_X;
			gbc_lbl_PinName.gridy = PIN_NAME_LABEL_INIT_POS_Y + pinNum;
			frmGpiosConfiguration.getContentPane().add(lbl_PinName[pinNum], gbc_lbl_PinName);
			lbl_PinName[pinNum].setVisible(false);
			
			/* Pin's mode */
			comboBox_PinMode[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinMode = new GridBagConstraints();
			gbc_comboBox_PinMode.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinMode.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinMode.gridx = PIN_MODE_CBOX_INIT_POS_X;
			gbc_comboBox_PinMode.gridy = PIN_MODE_CBOX_INIT_POS_Y + pinNum;
			frmGpiosConfiguration.getContentPane().add(comboBox_PinMode[pinNum], gbc_comboBox_PinMode);		
			comboBox_PinMode[pinNum].setVisible(false);
			comboBox_PinMode[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Port combo box click ******/
					if (!GuiRefreshLocked) {
						modeChange();
					}
					/****** End Port combo box click ******/
				}
			});
			
			/* Pin's output type */
			comboBox_PinOutType[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinOutType = new GridBagConstraints();
			gbc_comboBox_PinOutType.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinOutType.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinOutType.gridx = PIN_OUT_TYPE_CBOX_INIT_POS_X;
			gbc_comboBox_PinOutType.gridy = PIN_OUT_TYPE_CBOX_INIT_POS_Y + pinNum;
			frmGpiosConfiguration.getContentPane().add(comboBox_PinOutType[pinNum], gbc_comboBox_PinOutType);		
			comboBox_PinOutType[pinNum].setVisible(false);
			comboBox_PinOutType[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Port combo box click ******/
					if (!GuiRefreshLocked) {
						outTypeChange();
					}
					/****** End Port combo box click ******/
				}
			});
			
			/* Pin's output level */
			comboBox_PinOutLevel[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinOutLevel = new GridBagConstraints();
			gbc_comboBox_PinOutLevel.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinOutLevel.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinOutLevel.gridx = PIN_OUT_LEVEL_CBOX_INIT_POS_X;
			gbc_comboBox_PinOutLevel.gridy = PIN_OUT_LEVEL_CBOX_INIT_POS_Y + pinNum;
			frmGpiosConfiguration.getContentPane().add(comboBox_PinOutLevel[pinNum], gbc_comboBox_PinOutLevel);		
			comboBox_PinOutLevel[pinNum].addItem(OutLevel.LOW.name());
			comboBox_PinOutLevel[pinNum].addItem(OutLevel.HIGH.name());
			comboBox_PinOutLevel[pinNum].setVisible(false);
			comboBox_PinOutLevel[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Port combo box click ******/
					if (!GuiRefreshLocked) {
						outLevelChange();
					}
					/****** End Port combo box click ******/
				}
			});
			
			/* Pin's pull resistor */
			comboBox_PinPull[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinPull = new GridBagConstraints();
			gbc_comboBox_PinPull.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinPull.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinPull.gridx = PIN_PULL_CBOX_INIT_POS_X;
			gbc_comboBox_PinPull.gridy = PIN_PULL_CBOX_INIT_POS_Y + pinNum;
			frmGpiosConfiguration.getContentPane().add(comboBox_PinPull[pinNum], gbc_comboBox_PinPull);		
			comboBox_PinPull[pinNum].addItem(Pull.PULL_UP.name());
			comboBox_PinPull[pinNum].addItem(Pull.PULL_DOWN.name());
			comboBox_PinPull[pinNum].addItem(Pull.PULL_NOT_AVAILABLE.name());
			comboBox_PinPull[pinNum].setVisible(false);
			comboBox_PinPull[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Port combo box click ******/
					if (!GuiRefreshLocked) {
						pullChange();
					}
					/****** End Port combo box click ******/
				}
			});
			
			/* Pin's speed */
			comboBox_PinSpeed[pinNum] = new JComboBox<String>();
			GridBagConstraints gbc_comboBox_PinSpeed = new GridBagConstraints();
			gbc_comboBox_PinSpeed.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_PinSpeed.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox_PinSpeed.gridx = PIN_SPEED_CBOX_INIT_POS_X;
			gbc_comboBox_PinSpeed.gridy = PIN_SPEED_CBOX_INIT_POS_Y + pinNum;
			frmGpiosConfiguration.getContentPane().add(comboBox_PinSpeed[pinNum], gbc_comboBox_PinSpeed);		
			comboBox_PinSpeed[pinNum].addItem(Speed.SPEED_FAST.name());
			comboBox_PinSpeed[pinNum].addItem(Speed.SPEED_MEDIUM.name());
			comboBox_PinSpeed[pinNum].addItem(Speed.SPEED_HIGH.name());
			comboBox_PinSpeed[pinNum].addItem(Speed.SPEED_NOT_AVAILABLE.name());
			comboBox_PinSpeed[pinNum].setVisible(false);
			comboBox_PinSpeed[pinNum].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					/****** Begin Port combo box click ******/
					if (!GuiRefreshLocked) {
						speedChange();
					}
					/****** End Port combo box click ******/
				}
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
				if (UcConf.GpioCfgPin[pinNum].isAv_Adc()) {
					comboBox_PinMode[portPinNum].addItem(Mode.MODE_ANALOG.name());
				}
				comboBox_PinMode[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getMode().name());
				comboBox_PinMode[portPinNum].setVisible(true);
				
				/* Pin's output type */
				comboBox_PinOutType[portPinNum].removeAllItems();
				comboBox_PinOutType[portPinNum].addItem(OutType.OTYPE_PUSH_PULL.name());
				comboBox_PinOutType[portPinNum].addItem(OutType.OTYPE_OPEN_DRAIN.name());
				comboBox_PinOutType[portPinNum].setVisible(true);
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name())) {
					/* Set selected output type */
					comboBox_PinOutType[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getOutType().name());
					comboBox_PinOutType[portPinNum].setEnabled(true);
				} else {
					comboBox_PinOutType[portPinNum].setEnabled(false);
				}
				
				/* Pin's output level */
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name()) &&
					comboBox_PinOutType[portPinNum].getSelectedItem().equals(OutType.OTYPE_PUSH_PULL.name())) {
					comboBox_PinOutLevel[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getOutLevel().name());
					comboBox_PinOutLevel[portPinNum].setEnabled(true);
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
				comboBox_PinPull[portPinNum].setVisible(true);
				
				/* Pin's speed resistor */
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name())) {
					comboBox_PinSpeed[portPinNum].setSelectedItem(UcConf.GpioCfgPin[pinNum].getSpeed().name());
					comboBox_PinSpeed[portPinNum].setEnabled(true);
				} else {
					comboBox_PinSpeed[portPinNum].setEnabled(false);
				}
				comboBox_PinSpeed[portPinNum].setVisible(true);
				
				portPinNum++;
			}
		}
		for (int pinNum = PortPinsTotal; pinNum < Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT; pinNum++) {
			lbl_PinName[pinNum].setVisible(false);
			comboBox_PinMode[pinNum].setVisible(false);
			comboBox_PinOutType[pinNum].setVisible(false);
			comboBox_PinOutLevel[pinNum].setVisible(false);
			comboBox_PinPull[pinNum].setVisible(false);
			comboBox_PinSpeed[pinNum].setVisible(false);
		}
	}
	
	/**
	 * Reflect the pin's mode changes
	 */
	private void modeChange() {
		updateOutputType();
		updateOutLevel();
		updatePull();
		updateSpeed();
	}
	
	/**
	 * Reflect the pin's output type changes
	 */
	private void outTypeChange() {
		updateOutLevel();
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
					comboBox_PinOutType[portPinNum].setEnabled(true);
				} else {
					comboBox_PinOutType[portPinNum].setEnabled(false);
				}
				
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setOutType(OutType.getConfFromString(comboBox_PinOutType[portPinNum].getSelectedItem().toString()));
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
				if (comboBox_PinMode[portPinNum].getSelectedItem().equals(Mode.MODE_OUTPUT.name()) &&
					comboBox_PinOutType[portPinNum].getSelectedItem().equals(OutType.OTYPE_PUSH_PULL.name())) {
					comboBox_PinOutLevel[portPinNum].setEnabled(true);
				} else {
					comboBox_PinOutLevel[portPinNum].setEnabled(false);
				}
				
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setOutLevel(OutLevel.getConfFromString(comboBox_PinOutLevel[portPinNum].getSelectedItem().toString()));
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
					comboBox_PinPull[portPinNum].setEnabled(true);
				} else {
					comboBox_PinPull[portPinNum].setEnabled(false);
				}
				
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setPull(Pull.getConfFromString(comboBox_PinPull[portPinNum].getSelectedItem().toString()));
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
					comboBox_PinSpeed[portPinNum].setEnabled(true);
				} else {
					comboBox_PinSpeed[portPinNum].setEnabled(false);
				}
				
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setSpeed(Speed.getConfFromString(comboBox_PinSpeed[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}
	
	/**
	 * Save pins' output levels
	 */
	private void outLevelChange() {
		int portPinNum = 0;
		
		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(SelectedPort)) {
				/* Save configuration to microcontroller object */
				UcConf.GpioCfgPin[pinNum].setOutLevel(OutLevel.getConfFromString(comboBox_PinOutLevel[portPinNum].getSelectedItem().toString()));
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
				UcConf.GpioCfgPin[pinNum].setPull(Pull.getConfFromString(comboBox_PinPull[portPinNum].getSelectedItem().toString()));
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
				UcConf.GpioCfgPin[pinNum].setSpeed(Speed.getConfFromString(comboBox_PinSpeed[portPinNum].getSelectedItem().toString()));
				portPinNum++;
			}
		}
	}
}
