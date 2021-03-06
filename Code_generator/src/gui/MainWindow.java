package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import common.ErrorCode;
import common.Features;
import configurator.ConfigurationFile;
import microcontroller.Microcontroller;

import java.awt.GridBagLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * Main application window
 * 
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class MainWindow {

	/* Private fields */

	/**
	 * Frame for the main Window
	 */
	public JFrame FrmCodeGenerator;

	/* Public fields */

	/* Dynamic GUI elements */
	private JLabel lbl_ProjectName;
	private JLabel lbl_Microcontroller;
	private JButton btn_ConfigureGpios;
	private JButton btn_ConfigureAdcs;
	private JButton btn_ConfigureUarts;
	private JButton btn_GenerateCode;

	/* Dynamic menu elements */
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;

	/* Menu elements */
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNewProject;
	private JMenuItem mntmOpenProject;
	private JMenu mnTools;
	private JMenuItem mntmProjectPreferences;
	private JMenuItem mntmGeneralSettings;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;

	/**
	 * Open main window
	 * 
	 * @param args To be determined
	 */
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.FrmCodeGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			Features.verbosePrint("Error loading system theme...");
			e1.printStackTrace();
		}
		initialize();

	}

	/**
	 * Set visibility of About window
	 * 
	 * @param status true if visible
	 */
	public void setVisible(boolean status) {
		this.FrmCodeGenerator.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.FrmCodeGenerator.setVisible(status);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String windowName = "Code generator " + Features.SW_VERSION;

		if (!Features.VERSION_STATUS.equals("Release")) {
			windowName += " - " + Features.VERSION_NAME + " (" + Features.VERSION_STATUS + ")";
		}
		if (Features.DEBUG) {
			windowName += " Debug mode!";
		}

		FrmCodeGenerator = new JFrame();
		FrmCodeGenerator.setTitle(windowName);
		FrmCodeGenerator.setBounds(100, 100, 466, 270);
		FrmCodeGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		FrmCodeGenerator.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmNewProject = new JMenuItem(Messages.getString("MainWindow.mntmNewProject.text")); //$NON-NLS-1$
		mntmNewProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mntmNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin New Project button click ******/
				showComingSoonMessage();
				/****** End New Project button click ******/
			}
		});
		mnFile.add(mntmNewProject);

		mntmOpenProject = new JMenuItem(Messages.getString("MainWindow.mntmOpenProject.text")); //$NON-NLS-1$
		mntmOpenProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		mntmOpenProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Open Existing Project button click ******/
				getProjectFile();
				/****** End Open Existing Project button click ******/
			}
		});
		mnFile.add(mntmOpenProject);

		mntmSave = new JMenuItem(Messages.getString("MainWindow.mntmSave.text"));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/****** Begin Save button click ******/
				saveProject();
				/****** End Save button click ******/
			}
		});
		mnFile.add(mntmSave);

		mntmSaveAs = new JMenuItem(Messages.getString("MainWindow.mntmSaveAs.text")); //$NON-NLS-1$
		mntmSaveAs.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Save as button click ******/
				showComingSoonMessage();
				/****** End Save as button click ******/
			}
		});
		mntmSaveAs.setEnabled(false);
		mnFile.add(mntmSaveAs);

		mnTools = new JMenu(Messages.getString("MainWindow.mnTools.text")); //$NON-NLS-1$
		menuBar.add(mnTools);

		mntmProjectPreferences = new JMenuItem(Messages.getString("MainWindow.mntmProjectPreferences.text")); //$NON-NLS-1$
		mntmProjectPreferences.setEnabled(false);
		mnTools.add(mntmProjectPreferences);
		mntmProjectPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin About button click ******/
				MainGui.showProjectPreferencesWindow();
				/****** Begin About button click ******/
			}
		});

		mntmGeneralSettings = new JMenuItem(Messages.getString("MainWindow.mntmGeneralSettings.text")); //$NON-NLS-1$
		mnTools.add(mntmGeneralSettings);
		mntmGeneralSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin About button click ******/
				MainGui.showGeneralSettingsWindow();
				/****** Begin About button click ******/
			}
		});

		mnHelp = new JMenu(Messages.getString("MainWindow.mnHelp.text")); //$NON-NLS-1$
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem(Messages.getString("MainWindow.mntmAbout.text")); //$NON-NLS-1$
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin About button click ******/
				MainGui.showAboutWindow();
				/****** Begin About button click ******/
			}
		});
		mnHelp.add(mntmAbout);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		FrmCodeGenerator.getContentPane().setLayout(gridBagLayout);

		JLabel lblt_ProjectName = new JLabel(Messages.getString("MainWindow.lblProject.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_ProjectName = new GridBagConstraints();
		gbc_lblt_ProjectName.anchor = GridBagConstraints.WEST;
		gbc_lblt_ProjectName.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_ProjectName.gridx = 0;
		gbc_lblt_ProjectName.gridy = 0;
		FrmCodeGenerator.getContentPane().add(lblt_ProjectName, gbc_lblt_ProjectName);

		lbl_ProjectName = new JLabel(Messages.getString("MainWindow.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lbl_ProjectName = new GridBagConstraints();
		gbc_lbl_ProjectName.anchor = GridBagConstraints.WEST;
		gbc_lbl_ProjectName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_ProjectName.gridx = 1;
		gbc_lbl_ProjectName.gridy = 0;
		FrmCodeGenerator.getContentPane().add(lbl_ProjectName, gbc_lbl_ProjectName);

		JLabel lblt_Microcontroller = new JLabel(Messages.getString("MainWindow.lblSelectedMicrocontroller.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Microcontroller = new GridBagConstraints();
		gbc_lblt_Microcontroller.anchor = GridBagConstraints.WEST;
		gbc_lblt_Microcontroller.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Microcontroller.gridx = 0;
		gbc_lblt_Microcontroller.gridy = 1;
		FrmCodeGenerator.getContentPane().add(lblt_Microcontroller, gbc_lblt_Microcontroller);

		lbl_Microcontroller = new JLabel(Messages.getString("MainWindow.label.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lbl_Microcontroller = new GridBagConstraints();
		gbc_lbl_Microcontroller.anchor = GridBagConstraints.WEST;
		gbc_lbl_Microcontroller.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Microcontroller.gridx = 1;
		gbc_lbl_Microcontroller.gridy = 1;
		FrmCodeGenerator.getContentPane().add(lbl_Microcontroller, gbc_lbl_Microcontroller);

		btn_ConfigureGpios = new JButton(Messages.getString("MainWindow.btnConfigureGpios.text")); //$NON-NLS-1$
		btn_ConfigureGpios.setMnemonic('G');
		btn_ConfigureGpios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/****** Begin Configure GPIOs button click ******/
				MainGui.showGpioConfWindow();
				/****** End Configure GPIOs button click ******/
			}
		});
		btn_ConfigureGpios.setEnabled(false);
		GridBagConstraints gbc_btn_ConfigureGpios = new GridBagConstraints();
		gbc_btn_ConfigureGpios.insets = new Insets(0, 0, 5, 5);
		gbc_btn_ConfigureGpios.gridx = 0;
		gbc_btn_ConfigureGpios.gridy = 2;
		FrmCodeGenerator.getContentPane().add(btn_ConfigureGpios, gbc_btn_ConfigureGpios);

		btn_GenerateCode = new JButton(Messages.getString("MainWindow.btnGenerateCode.text")); //$NON-NLS-1$
		btn_GenerateCode.setEnabled(false);
		btn_GenerateCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Generate Code button click ******/
				generateCode();
				/****** End Generate Code button click ******/
			}
		});

		btn_ConfigureAdcs = new JButton(Messages.getString("MainWindow.btn_ConfigureAdcs.text")); //$NON-NLS-1$
		btn_ConfigureAdcs.setEnabled(false);
		GridBagConstraints gbc_btn_ConfigureAdcs = new GridBagConstraints();
		gbc_btn_ConfigureAdcs.insets = new Insets(0, 0, 5, 5);
		gbc_btn_ConfigureAdcs.gridx = 0;
		gbc_btn_ConfigureAdcs.gridy = 3;
		FrmCodeGenerator.getContentPane().add(btn_ConfigureAdcs, gbc_btn_ConfigureAdcs);
		btn_ConfigureAdcs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Configure ADCs button click ******/
				MainGui.showAdcConfWindow();
				/****** End Configure ADCs button click ******/
			}
		});
		
		btn_ConfigureUarts = new JButton(Messages.getString("MainWindow.btnNewButton.text")); //$NON-NLS-1$
		btn_ConfigureUarts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Configure UARTs button click ******/
				MainGui.showUartConfWindow();
				/****** End Configure UARTs button click ******/
			}
		});
		btn_ConfigureUarts.setEnabled(false);
		GridBagConstraints gbc_btn_ConfigureUarts = new GridBagConstraints();
		gbc_btn_ConfigureUarts.insets = new Insets(0, 0, 5, 5);
		gbc_btn_ConfigureUarts.gridx = 0;
		gbc_btn_ConfigureUarts.gridy = 4;
		FrmCodeGenerator.getContentPane().add(btn_ConfigureUarts, gbc_btn_ConfigureUarts);
		GridBagConstraints gbc_btn_GenerateCode = new GridBagConstraints();
		gbc_btn_GenerateCode.insets = new Insets(0, 0, 0, 5);
		gbc_btn_GenerateCode.gridx = 0;
		gbc_btn_GenerateCode.gridy = 5;
		FrmCodeGenerator.getContentPane().add(btn_GenerateCode, gbc_btn_GenerateCode);
	}

	/**
	 * Call the file chooser to get the project configuration file
	 */
	private void getProjectFile() {
		FileNameExtensionFilter projectFileFilter;
		String initialSearchPath = System.getProperty("user.dir");
		File inFile;

		if (Features.DEBUG) {
			/* Use the example project when debugging */
			initialSearchPath += System.getProperty("file.separator") + "testProject";
		}

		projectFileFilter = new FileNameExtensionFilter(Messages.getString("MainWindow.projectFileFilter.text"),
				ConfigurationFile.STR_PROJ_CONF_FILE);
		inFile = OpenFileChooser(initialSearchPath, Messages.getString("MainWindow.projectFileChooser.title"),
				projectFileFilter);
		if (null != inFile) {
			MainGui.loadProjectFile(inFile);
		}
	}

	/**
	 * Open file chooser dialog and get the selected file
	 * 
	 * @param initialPath Path to search the file in
	 * @param title       Dialog title
	 * @param fileFilter  Extension filter
	 * @return Selected file
	 */
	public File OpenFileChooser(String initialPath, String title, FileNameExtensionFilter fileFilter) {
		File inFile = null;
		final JFileChooser projectFileChooser = new JFileChooser(initialPath);

		projectFileChooser.setDialogTitle(title);
		projectFileChooser.setFileFilter(fileFilter);
		int fileOpenError = projectFileChooser.showOpenDialog(FrmCodeGenerator);
		if (fileOpenError == JFileChooser.APPROVE_OPTION) {
			/* File selected */
			inFile = projectFileChooser.getSelectedFile();
			Features.verbosePrint("File " + inFile.getName() + " selected...");
		} else {
			/* Operation canceled */
			Features.verbosePrint("No file selected...");
		}

		return inFile;
	}

	/**
	 * Set Project's name in its label
	 * 
	 * @param projectName    Project's name
	 * @param ucManufacturer Microcontroller's manufacturer
	 * @param ucName         Microcontroller's model
	 * @return Error status
	 */
	public ErrorCode setProjectInformation(Microcontroller uC, String projectName) {
		ErrorCode errorStatus = ErrorCode.NO_ERROR;
		String ucManufacturer = uC.getUc_manufacturer();
		String ucName = uC.getUc_model();
		boolean hasAdc = uC.getUc_adcNum() > 0;
		boolean hasUart = uC.getUc_uartNum() > 0;

		if (projectName.equals("") || ucManufacturer.equals("") || ucName.equals("")) {
			Features.verbosePrint("Wrong project information...");
			MainGui.showErrorDialog("Wrong project information");
			errorStatus = ErrorCode.FILE_CONF_ERROR;
			return errorStatus;
		}
		lbl_ProjectName.setText(projectName);
		lbl_Microcontroller.setText(ucManufacturer + " " + ucName);
		btn_ConfigureGpios.setEnabled(true);
		btn_ConfigureAdcs.setEnabled(hasAdc);
		btn_ConfigureUarts.setEnabled(hasUart);
		btn_GenerateCode.setEnabled(true);
		mntmSaveAs.setEnabled(true);
		mntmProjectPreferences.setEnabled(true);
		return errorStatus;
	}

	/**
	 * Show a Coming Soon! dialog
	 */
	private void showComingSoonMessage() {
		JOptionPane.showMessageDialog(FrmCodeGenerator, "Coming soon!", "Feature not ready",
				JOptionPane.WARNING_MESSAGE);
	}

	private void generateCode() {
		ErrorCode errorCode;

		errorCode = MainGui.generateCode();

		if (errorCode == ErrorCode.NO_ERROR) {
			JOptionPane.showMessageDialog(FrmCodeGenerator, "Code generated succesfully", "Code generation",
					JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(FrmCodeGenerator, "Error generating code!", "Code generation",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Save the project files
	 */
	private void saveProject() {
		MainGui.saveUc();
	}
}
