package lab12;

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
import java.awt.Color;

public class ATM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// GUI components needed for inputs and outputs
	private JTextField txtBalance;
	private JTextField txtDivisor;
	private JLabel lblResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM frame = new ATM();
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
	public ATM() {
		// Window configuration
		setTitle("ATM Balance Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		
		// Content Pane setup with a clean layout (Grid of 4 rows, 2 columns)
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new GridLayout(4, 2, 10, 10)); 
		setContentPane(contentPane);

		// 1. Row 1: Total Balance Inputs
		contentPane.add(new JLabel("Total Balance:"));
		txtBalance = new JTextField();
		contentPane.add(txtBalance);

		// 2. Row 2: Divisor Inputs
		contentPane.add(new JLabel("Number of Accounts (Divisor):"));
		txtDivisor = new JTextField();
		contentPane.add(txtDivisor);

		// 3. Row 3: Action Button
		JButton btnCalculate = new JButton("Calculate Split");
		contentPane.add(btnCalculate);
		
		// 4. Row 4: Result/Error Display Label
		lblResult = new JLabel("Result will appear here.");
		contentPane.add(lblResult);

		// Action Listener - This code runs when the button is clicked
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Reset text color to default black
				lblResult.setForeground(Color.BLACK);
				
				try {
					// Read texts from the fields and convert to numbers
					int totalBalance = Integer.parseInt(txtBalance.getText().trim());
					int divisor = Integer.parseInt(txtDivisor.getText().trim());

					// Perform division (Will jump to catch block if divisor is 0)
					int perAccountBalance = totalBalance / divisor;

					// Display successful result
					lblResult.setText("Each account gets: $" + perAccountBalance);

				} catch (ArithmeticException ex) {
					// Exception handling for Division by Zero
					lblResult.setForeground(Color.RED);
					lblResult.setText("Error: Cannot divide balance by zero!");
					
				} catch (NumberFormatException ex) {
					// Exception handling for letters/symbols or blank fields
					lblResult.setForeground(Color.RED);
					lblResult.setText("Error: Please enter numbers only.");
				}
			}
		});
	}
}