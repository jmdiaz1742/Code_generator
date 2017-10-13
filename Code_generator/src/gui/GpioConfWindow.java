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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Window for configuring GPIO pins
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class GpioConfWindow {

	private JFrame frmGpiosConfiguration;
	private Microcontroller UcConf;
	private boolean GuiInitialized = false;
	
	/* Dynamic GUI elements */
	JComboBox<String> comboBox_PortSelection;
	private JLabel lblt_Pin;
	private JLabel lblt_Mode;
	private JLabel lblt_OutputType;
	private JLabel lblt_OutputLevel;
	private JLabel lblt_PullResistor;
	private JLabel lblSpeed;
	JLabel[] lbl_PinName;
	
	/* GUI Constants */
	private static final int PIN_NAME_LABEL_INIT_POS_X = 0;
	private static final int PIN_NAME_LABEL_INIT_POS_Y = 3;

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
		initPortsComboBox();
		initDynamicPinElements();
		loadPortsPins();
		frmGpiosConfiguration.setVisible(true);
		GuiInitialized = true;
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
		gridBagLayout.columnWidths = new int[]{85, 97, 135, 0, 0, 0, 0};
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
//				/****** Begin Port combo box click ******/
				if (GuiInitialized) {
					Features.verbosePrint("Combo box clicked!");
//					loadPortsPins();
				}
//				/****** End Port combo box click ******/
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
	 * Get how many pins are in a given port
	 * @param port Port's name
	 * @return Number of pins in the port
	 */
	private int getPinsNumInPort(String port) {
		int totalPins = 0;
		
		for (int pinNum = 0; pinNum < UcConf.getUc_gpioNum(); pinNum++) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(port)) {
				totalPins++;
			}
		}
		
		return totalPins;
	}
	
	/**
	 * Initialize all dynamic pins elements as empty
	 */
	private void initDynamicPinElements() {	
		Features.verbosePrint("Initializing dynamic elements...");
		lbl_PinName = new JLabel[Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT];
		
		for (int pinNum = 0; pinNum < Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT; pinNum++) {
			/* Pin's name */
			lbl_PinName[pinNum] = new JLabel("Pin " + pinNum);
			GridBagConstraints gbc_lbl_PinName = new GridBagConstraints();
			gbc_lbl_PinName.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_PinName.gridx = PIN_NAME_LABEL_INIT_POS_X;
			gbc_lbl_PinName.gridy = PIN_NAME_LABEL_INIT_POS_Y + pinNum;
			frmGpiosConfiguration.getContentPane().add(lbl_PinName[pinNum], gbc_lbl_PinName);
			lbl_PinName[pinNum].setVisible(false);
		}
	}
	
	/**
	 * Populate all dynamic pin elements
	 */
	private void loadPortsPins() {
		String currentPort = comboBox_PortSelection.getSelectedItem().toString();;
		int totalPins = getPinsNumInPort(currentPort);
		
		/* Pin's name */
		for (int pinNum = 0; pinNum < totalPins; ) {
			if (UcConf.GpioCfgPin[pinNum].getPort().equals(currentPort)) {
				lbl_PinName[pinNum].setText(UcConf.GpioCfgPin[pinNum].getPinName());
				lbl_PinName[pinNum].setVisible(true);
				pinNum++;
			}
		}
		for (int pinNum = totalPins; pinNum < Microcontroller.MAX_NUMBER_OF_PINS_PER_PORT; pinNum++) {
			lbl_PinName[pinNum].setVisible(false);
		}
	}

}
