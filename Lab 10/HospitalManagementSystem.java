package lab12;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class HospitalManagementSystem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// GUI components
	private JTextField txtPatientName;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HospitalManagementSystem frame = new HospitalManagementSystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HospitalManagementSystem() {
		// Window configuration
		setTitle("Hospital Management System - Patient Records");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		
		// Set up grid layout (3 rows, 2 columns)
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new GridLayout(3, 2, 10, 10));
		setContentPane(contentPane);

		// Row 1: Input field for patient name
		contentPane.add(new JLabel("Patient Full Name:"));
		txtPatientName = new JTextField();
		contentPane.add(txtPatientName);

		// Row 2: Admission Action Button
		JButton btnRegister = new JButton("Register Patient");
		contentPane.add(btnRegister);
		
		// Row 3: Status display panel
		lblStatus = new JLabel("Status: System Ready.");
		contentPane.add(lblStatus);

		// Button Logic
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatus.setForeground(Color.BLACK); // Reset status color
				
				try {
					// Fetch text from textfield
					String patientName = txtPatientName.getText();

					// Scenario Logic: Check if the string object itself or its content is completely missing
					if (patientName == null || patientName.trim().isEmpty()) {
						// Manually trigger a NullPointerException to force a safe catch routine
						throw new NullPointerException("Patient record cannot be created with a missing name.");
					}

					// Safe execution path: Process name formatting safely
					String formattedName = patientName.trim().toUpperCase();
					lblStatus.setText("Success: Registered patient '" + formattedName + "'");

				} catch (NullPointerException ex) {
					// Gracefully intercept the Null error state without crashing the frame window
					lblStatus.setForeground(Color.RED);
					lblStatus.setText("Error: " + ex.getMessage());
				}
			}
		});
	}
}