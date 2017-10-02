package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import common.Features;
import configurator.ConfigurationFile;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

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
		mnFile.add(mntmOpenProject);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		FrmCodeGenerator.getContentPane().setLayout(gridBagLayout);
		
		JButton btnNewProject = new JButton(Messages.getString("MainWindow.btnNewProject.text")); //$NON-NLS-1$
		GridBagConstraints gbc_btnNewProject = new GridBagConstraints();
		gbc_btnNewProject.anchor = GridBagConstraints.WEST;
		gbc_btnNewProject.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewProject.gridx = 0;
		gbc_btnNewProject.gridy = 0;
		FrmCodeGenerator.getContentPane().add(btnNewProject, gbc_btnNewProject);
		
		JButton btnOpenExistingProject = new JButton(Messages.getString("MainWindow.btnOpenExistingProject.text")); //$NON-NLS-1$
		btnOpenExistingProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileNameExtensionFilter projectFileFilter;
				final JFileChooser ProjectFileChooser;
				
				String initialSearchPath = System.getProperty("user.dir");
				if (Features.DEBUG) {
					/* Use the example project when debugging */
					initialSearchPath += System.getProperty("file.separator") + "testProject";
				}
				
				ProjectFileChooser = new JFileChooser(initialSearchPath);
				projectFileFilter = new FileNameExtensionFilter("Project configuration files", ConfigurationFile.STR_PROJ_CONF_FILE);
				
				ProjectFileChooser.setFileFilter(projectFileFilter);
				int fileOpenError = ProjectFileChooser.showOpenDialog(FrmCodeGenerator);
				if (fileOpenError == JFileChooser.APPROVE_OPTION) {
					/* Load the configuration file */
					MainGui.loadProjectFile(ProjectFileChooser.getSelectedFile());
				} else {
					Features.verbosePrint("Error opening project configuration file...");
				}
			}
		});
		GridBagConstraints gbc_btnOpenExistingProject = new GridBagConstraints();
		gbc_btnOpenExistingProject.gridx = 0;
		gbc_btnOpenExistingProject.gridy = 1;
		FrmCodeGenerator.getContentPane().add(btnOpenExistingProject, gbc_btnOpenExistingProject);
	}
	
	


}
