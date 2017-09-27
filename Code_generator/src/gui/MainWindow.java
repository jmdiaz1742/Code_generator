package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import common.Features;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;

/**
 * Main application window
 * @author Miguel Diaz
 * @version 0.1
 *
 */
public class MainWindow {

	private JFrame frmCodeGenerator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmCodeGenerator.setVisible(true);
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
			windowName += " " + Features.VERSION_NAME + " (" + Features.VERSION_STATUS + ")";
		}
		if (Features.DEBUG) {
			windowName += " Debug mode!";
		}
		
		frmCodeGenerator = new JFrame();
		frmCodeGenerator.setTitle(windowName);
		frmCodeGenerator.setBounds(100, 100, 800, 700);
		frmCodeGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmCodeGenerator.setJMenuBar(menuBar);
		
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
		frmCodeGenerator.getContentPane().setLayout(gridBagLayout);
		
		JButton btnNewProject = new JButton(Messages.getString("MainWindow.btnNewProject.text")); //$NON-NLS-1$
		GridBagConstraints gbc_btnNewProject = new GridBagConstraints();
		gbc_btnNewProject.anchor = GridBagConstraints.WEST;
		gbc_btnNewProject.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewProject.gridx = 0;
		gbc_btnNewProject.gridy = 0;
		frmCodeGenerator.getContentPane().add(btnNewProject, gbc_btnNewProject);
		
		JButton btnOpenExistingProject = new JButton(Messages.getString("MainWindow.btnOpenExistingProject.text")); //$NON-NLS-1$
		GridBagConstraints gbc_btnOpenExistingProject = new GridBagConstraints();
		gbc_btnOpenExistingProject.gridx = 0;
		gbc_btnOpenExistingProject.gridy = 1;
		frmCodeGenerator.getContentPane().add(btnOpenExistingProject, gbc_btnOpenExistingProject);
	}

}
