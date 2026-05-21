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

// 1. THE CUSTOM EXCEPTION CLASS
class SemesterOutOfBoundsException extends Exception {
	private static final long serialVersionUID = 1L;

	public SemesterOutOfBoundsException(String message) {
		super(message);
	}
}

// 2. THE GUI WINDOW CLASS
public class InvalidSemesterException extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// GUI components
	private JTextField txtSemester;
	private JLabel lblResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvalidSemesterException frame = new InvalidSemesterException();
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
	public InvalidSemesterException() {
		// Window setup
		setTitle("University Portal - Semester Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		
		// Set up a 3-row layout panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new GridLayout(3, 2, 10, 10));
		setContentPane(contentPane);

		// Row 1: Label and Input Field
		contentPane.add(new JLabel("Enter Semester Number (1-8):"));
		txtSemester = new JTextField();
		contentPane.add(txtSemester);

		// Row 2: Validation Button
		JButton btnVerify = new JButton("Verify Registration");
		contentPane.add(btnVerify);
		
		// Row 3: Output Display
		lblResult = new JLabel("Status: Awaiting registration...");
		contentPane.add(lblResult);

		// Button Logic
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResult.setForeground(Color.BLACK); // Reset text color
				
				try {
					// Read input data
					String inputText = txtSemester.getText().trim();
					int semester = Integer.parseInt(inputText);

					// Portal Rule Check: Must be between 1 and 8
					if (semester < 1 || semester > 8) {
						// Throwing our custom exception with a specific feedback message
						throw new SemesterOutOfBoundsException("Semesters must strictly be between 1 and 8.");
					}

					// If validation succeeds
					lblResult.setText("Success: Registered for Semester " + semester + "!");

				} catch (SemesterOutOfBoundsException ex) {
					// Catching our custom exception rule violation
					lblResult.setForeground(Color.RED);
					lblResult.setText("Error: " + ex.getMessage());
					
				} catch (NumberFormatException ex) {
					// Catching general alphanumeric typing errors or empty clicks
					lblResult.setForeground(Color.RED);
					lblResult.setText("Error: Please enter a valid number.");
				}
			}
		});
	}
}