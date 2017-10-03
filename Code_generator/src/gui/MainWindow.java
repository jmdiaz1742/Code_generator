package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import common.Features;
import configurator.ConfigurationFile;
import java.awt.GridBagLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Main application window
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class MainWindow {

	/* Private fields */
	private JFrame FrmCodeGenerator;
	
	/* Public fields */
	public File ProjectSettingsFile;

	/**
	 * Open main window
	 * @param args To be determined
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			Features.verbosePrint("Error loading system theme...");
			e1.printStackTrace();
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
		initialize();
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
		FrmCodeGenerator.setBounds(100, 100, 800, 700);
		FrmCodeGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		FrmCodeGenerator.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewProject = new JMenuItem(Messages.getString("MainWindow.mntmNewProject.text")); //$NON-NLS-1$
		mnFile.add(mntmNewProject);
		
		JMenuItem mntmOpenProject = new JMenuItem(Messages.getString("MainWindow.mntmOpenProject.text")); //$NON-NLS-1$
		mntmOpenProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/****** Begin Open Existing Project button click ******/
				getProjectFile();
				/****** End Open Existing Project button click ******/
			}
		});
		mnFile.add(mntmOpenProject);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		FrmCodeGenerator.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblt_ProjectName = new JLabel(Messages.getString("MainWindow.lblProject.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_ProjectName = new GridBagConstraints();
		gbc_lblt_ProjectName.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_ProjectName.gridx = 0;
		gbc_lblt_ProjectName.gridy = 0;
		FrmCodeGenerator.getContentPane().add(lblt_ProjectName, gbc_lblt_ProjectName);
		
		JLabel lbl_ProjectName = new JLabel(Messages.getString("MainWindow.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lbl_ProjectName = new GridBagConstraints();
		gbc_lbl_ProjectName.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_ProjectName.gridx = 1;
		gbc_lbl_ProjectName.gridy = 0;
		FrmCodeGenerator.getContentPane().add(lbl_ProjectName, gbc_lbl_ProjectName);
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
		
		projectFileFilter = new FileNameExtensionFilter(Messages.getString("MainWindow.projectFileFilter.text"), ConfigurationFile.STR_PROJ_CONF_FILE);
		inFile = OpenFileChooser(initialSearchPath, Messages.getString("MainWindow.projectFileChooser.title"), projectFileFilter);
		if (null != inFile) {
			MainGui.loadProjectFile(inFile);
		}
	}
	
	/**
	 * Open file chooser dialog and get the selected file
	 * @param initialPath Path to search the file in
	 * @param title Dialog title
	 * @param fileFilter Extension filter
	 * @return Selected file
	 */
	public File OpenFileChooser(String initialPath, String title, FileNameExtensionFilter fileFilter) {
		File inFile = null;
		final JFileChooser ProjectFileChooser = new JFileChooser(initialPath);
		
		ProjectFileChooser.setDialogTitle(title);
		ProjectFileChooser.setFileFilter(fileFilter);
		int fileOpenError = ProjectFileChooser.showOpenDialog(FrmCodeGenerator);
		if (fileOpenError == JFileChooser.APPROVE_OPTION) {
			/* File selected */
			inFile = ProjectFileChooser.getSelectedFile();
			Features.verbosePrint("File " + inFile.getName() + " selected...");
		} else {
			/* Operation canceled */
			Features.verbosePrint("No file selected...");
		}
		
		return inFile;
	}
	
	public void setprojectName

}
