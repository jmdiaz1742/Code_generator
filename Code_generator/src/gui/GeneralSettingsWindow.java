package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import common.GeneralSettings;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GeneralSettingsWindow {

	private JFrame frmGeneralSettings;
	private GeneralSettings Settings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneralSettingsWindow window = new GeneralSettingsWindow(null);
					window.frmGeneralSettings.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GeneralSettingsWindow(GeneralSettings settings) {
		Settings = settings;
		initialize();
		frmGeneralSettings.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGeneralSettings = new JFrame();
		frmGeneralSettings.setTitle(Messages.getString("GeneralSettingsWindow.frmGeneralSettings.title")); //$NON-NLS-1$
		frmGeneralSettings.setBounds(100, 100, 450, 300);
		frmGeneralSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGeneralSettings.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				/* Begin Window closing */
				MainGui.saveGeneralSettings(Settings);
				/* End Window closing */
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		frmGeneralSettings.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0 };
		gbl_panel.rowHeights = new int[] { 0 };
		gbl_panel.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
	}

}
