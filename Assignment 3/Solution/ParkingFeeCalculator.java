import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ParkingFeeCalculator extends JFrame implements ActionListener {

    private JTextField txtHours;
    private JTextField txtFee;

    private JButton btnGenerate;
    private JButton btnReset;

    private ParkingFeeService feeService;

    public ParkingFeeCalculator() {

       
        feeService = new ParkingFeeService(
                new RegularFeeStrategy());

        initializeUI();
    }

    private void initializeUI() {

        setTitle("Parking Fee Calculator");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblHours = new JLabel("Enter Parking Hours:");
        JLabel lblFee = new JLabel("Total Fee (Rs.):");

        txtHours = new JTextField();
        txtFee = new JTextField();

        txtFee.setEditable(false);

        btnGenerate = new JButton("Generate Fee");
        btnReset = new JButton("Reset");

        btnGenerate.addActionListener(this);
        btnReset.addActionListener(this);

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(lblHours);
        panel.add(txtHours);

        panel.add(lblFee);
        panel.add(txtFee);

        panel.add(btnGenerate);
        panel.add(btnReset);

        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnGenerate) {
            generateFee();
        }

        if (e.getSource() == btnReset) {
            clearFields();
        }
    }

    private void generateFee() {

        try {

            String input = txtHours.getText().trim();

            // Empty Field Validation
            if (input.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Parking hours cannot be empty!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            int hours = Integer.parseInt(input);

            if (hours < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hours cannot be negative!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);

                return;
            }

            double totalFee =
                    feeService.calculateParkingFee(hours);

            txtFee.setText(
                    String.format("%.2f", totalFee));

        }

        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid numeric value!",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }

        finally {

            JOptionPane.showMessageDialog(
                    this,
                    "Operation Completed.");
        }
    }

    private void clearFields() {

        txtHours.setText("");
        txtFee.setText("");

        JOptionPane.showMessageDialog(
                this,
                "Fields Cleared Successfully.");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new ParkingFeeCalculator());
    }
}