package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import projectConfiguration.ProjectSettings;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjectSettingsWindow {

	private JFrame frmProjectPreferences;
	private ProjectSettings Settings;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectSettingsWindow window = new ProjectSettingsWindow(null);
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
	public ProjectSettingsWindow(ProjectSettings settings) {
		Settings = settings;
		initialize();
		frmProjectPreferences.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjectPreferences = new JFrame();
		frmProjectPreferences.setTitle(Messages.getString("ProjectSettingsWindow.frmProjectPreferences.title")); //$NON-NLS-1$
		frmProjectPreferences.setBounds(100, 100, 954, 572);
		frmProjectPreferences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		frmProjectPreferences.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblFrameworkFolder = new JLabel(Messages.getString("ProjectSettingsWindow.lblFrameworkFolder.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblFrameworkFolder = new GridBagConstraints();
		gbc_lblFrameworkFolder.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrameworkFolder.anchor = GridBagConstraints.EAST;
		gbc_lblFrameworkFolder.gridx = 0;
		gbc_lblFrameworkFolder.gridy = 0;
		panel.add(lblFrameworkFolder, gbc_lblFrameworkFolder);

		textField = new JTextField();
		textField.setText(Messages.getString("ProjectSettingsWindow.textField.text")); //$NON-NLS-1$
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnChange = new JButton(Messages.getString("ProjectSettingsWindow.btnChange.text")); //$NON-NLS-1$
		GridBagConstraints gbc_btnChange = new GridBagConstraints();
		gbc_btnChange.insets = new Insets(0, 0, 5, 0);
		gbc_btnChange.gridx = 2;
		gbc_btnChange.gridy = 0;
		panel.add(btnChange, gbc_btnChange);

		JButton btnSaveSettings = new JButton(Messages.getString("ProjectSettingsWindow.btnSaveSettings.text")); //$NON-NLS-1$
		btnSaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainGui.saveProjectPreferences(Settings);
			}
		});
		GridBagConstraints gbc_btnSaveSettings = new GridBagConstraints();
		gbc_btnSaveSettings.insets = new Insets(0, 0, 0, 5);
		gbc_btnSaveSettings.gridx = 0;
		gbc_btnSaveSettings.gridy = 2;
		panel.add(btnSaveSettings, gbc_btnSaveSettings);
	}

}
