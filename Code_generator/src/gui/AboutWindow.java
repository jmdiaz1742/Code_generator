package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import common.Features;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * About Window, contains version and contact information
 * @author ovd
 *
 */
public class AboutWindow {

	private JFrame frmAboutCodeGenerator;

	/**
	 * About window main
	 * 
	 * @param args
	 *            Init parameters
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutWindow window = new AboutWindow();
					window.frmAboutCodeGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AboutWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			Features.verbosePrint("Error loading system theme...");
			e1.printStackTrace();
		}
		initialize();
		this.frmAboutCodeGenerator.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String version = Features.SW_VERSION;
		String codename = Features.VERSION_NAME;

		if (!Features.VERSION_STATUS.equals("Release")) {
			version += ", " + Features.VERSION_STATUS + " build";
		}
		if (Features.DEBUG) {
			version += " - Debug mode";
		}

		frmAboutCodeGenerator = new JFrame();
		frmAboutCodeGenerator.setTitle(Messages.getString("AboutWindow.frmAboutCodeGenerator.title")); //$NON-NLS-1$
		frmAboutCodeGenerator.setBounds(100, 100, 361, 138);
		frmAboutCodeGenerator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frmAboutCodeGenerator.getContentPane().setLayout(gridBagLayout);

		JLabel lblt_Version = new JLabel(Messages.getString("AboutWindow.lblVersion.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Version = new GridBagConstraints();
		gbc_lblt_Version.anchor = GridBagConstraints.WEST;
		gbc_lblt_Version.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Version.gridx = 0;
		gbc_lblt_Version.gridy = 0;
		frmAboutCodeGenerator.getContentPane().add(lblt_Version, gbc_lblt_Version);

		JLabel lbl_Version = new JLabel(version);
		GridBagConstraints gbc_lbl_Version = new GridBagConstraints();
		gbc_lbl_Version.anchor = GridBagConstraints.WEST;
		gbc_lbl_Version.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Version.gridx = 1;
		gbc_lbl_Version.gridy = 0;
		frmAboutCodeGenerator.getContentPane().add(lbl_Version, gbc_lbl_Version);

		JLabel lblt_Codename = new JLabel(Messages.getString("AboutWindow.lblCodename.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Codename = new GridBagConstraints();
		gbc_lblt_Codename.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Codename.gridx = 0;
		gbc_lblt_Codename.gridy = 1;
		frmAboutCodeGenerator.getContentPane().add(lblt_Codename, gbc_lblt_Codename);

		JLabel lbl_Codename = new JLabel(codename); // $NON-NLS-1$
		GridBagConstraints gbc_lbl_Codename = new GridBagConstraints();
		gbc_lbl_Codename.anchor = GridBagConstraints.WEST;
		gbc_lbl_Codename.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Codename.gridx = 1;
		gbc_lbl_Codename.gridy = 1;
		frmAboutCodeGenerator.getContentPane().add(lbl_Codename, gbc_lbl_Codename);

		JLabel lblt_Author = new JLabel(Messages.getString("AboutWindow.lblAuthor.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Author = new GridBagConstraints();
		gbc_lblt_Author.anchor = GridBagConstraints.WEST;
		gbc_lblt_Author.insets = new Insets(0, 0, 5, 5);
		gbc_lblt_Author.gridx = 0;
		gbc_lblt_Author.gridy = 2;
		frmAboutCodeGenerator.getContentPane().add(lblt_Author, gbc_lblt_Author);

		JLabel lbl_Author = new JLabel(Messages.getString("AboutWindow.lblJos.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lbl_Author = new GridBagConstraints();
		gbc_lbl_Author.anchor = GridBagConstraints.WEST;
		gbc_lbl_Author.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_Author.gridx = 1;
		gbc_lbl_Author.gridy = 2;
		frmAboutCodeGenerator.getContentPane().add(lbl_Author, gbc_lbl_Author);

		JLabel lblt_Contact = new JLabel(Messages.getString("AboutWindow.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblt_Contact = new GridBagConstraints();
		gbc_lblt_Contact.anchor = GridBagConstraints.WEST;
		gbc_lblt_Contact.insets = new Insets(0, 0, 0, 5);
		gbc_lblt_Contact.gridx = 0;
		gbc_lblt_Contact.gridy = 3;
		frmAboutCodeGenerator.getContentPane().add(lblt_Contact, gbc_lblt_Contact);

		JLabel lbl_Contact = new JLabel(Messages.getString("AboutWindow.lblNewLabel.text_1")); //$NON-NLS-1$
		GridBagConstraints gbc_lbl_Contact = new GridBagConstraints();
		gbc_lbl_Contact.anchor = GridBagConstraints.WEST;
		gbc_lbl_Contact.gridx = 1;
		gbc_lbl_Contact.gridy = 3;
		frmAboutCodeGenerator.getContentPane().add(lbl_Contact, gbc_lbl_Contact);
	}

}
