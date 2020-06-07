package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import projectConfiguration.ProjectSettings;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

public class ProjectPreferencesWindow {

	private JFrame frmProjectPreferences;
	private ProjectSettings Settings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectPreferencesWindow window = new ProjectPreferencesWindow(null);
					window.frmProjectPreferences.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProjectPreferencesWindow(ProjectSettings settings) {
		Settings = settings;
		initialize();
		frmProjectPreferences.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjectPreferences = new JFrame();
		frmProjectPreferences.setTitle("Project Preferences");
		frmProjectPreferences.setBounds(100, 100, 450, 300);
		frmProjectPreferences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		frmProjectPreferences.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0};
		gbl_panel.rowHeights = new int[]{0};
		gbl_panel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
	}

}
