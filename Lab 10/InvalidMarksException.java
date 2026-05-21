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
// It extends 'Exception' so Java treats it as a real, catchable error.
class InvalidMarksExceptionCase extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidMarksExceptionCase(String message) {
		super(message); // Passes our custom error message to the parent Exception class
	}
}

// 2. THE GUI WINDOW CLASS
public class InvalidMarksException extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// GUI components
	private JTextField txtMarks;
	private JLabel lblResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvalidMarksException frame = new InvalidMarksException();
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
	public InvalidMarksException() {
		// Window setup
		setTitle("Student Result System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		
		// Set up a 3-row layout panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new GridLayout(3, 2, 10, 10));
		setContentPane(contentPane);

		// Row 1: Label and Input Field
		contentPane.add(new JLabel("Enter Student Marks (0-100):"));
		txtMarks = new JTextField();
		contentPane.add(txtMarks);

		// Row 2: Submit Button
		JButton btnSubmit = new JButton("Submit Marks");
		contentPane.add(btnSubmit);
		
		// Row 3: Output Display
		lblResult = new JLabel("Status: Awaiting entry...");
		contentPane.add(lblResult);

		// Button Logic
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResult.setForeground(Color.BLACK); // Reset color to black
				
				try {
					// Read input and parse to integer
					int marks = Integer.parseInt(txtMarks.getText().trim());

					// Business Rule Check: Check if marks are negative or over 100
					if (marks < 0) {
						// This manually triggers ("throws") our custom exception
						throw new InvalidMarksExceptionCase("Marks cannot be negative!");
					} else if (marks > 100) {
						throw new InvalidMarksExceptionCase("Marks cannot exceed 100!");
					}

					// If no exception was thrown, save successfully
					lblResult.setText("Success: Marks saved successfully (" + marks + "/100).");

				} catch (InvalidMarksExceptionCase ex) {
					// Catching our custom exception specifically
					lblResult.setForeground(Color.RED);
					lblResult.setText("Error: " + ex.getMessage());
					
				} catch   (NumberFormatException ex) {
					// Catching standard format mistakes (letters, symbols, or blanks)
					lblResult.setForeground(Color.RED);
					lblResult.setText("Error: Please enter a valid number.");
				}
			}
		});
	}
}