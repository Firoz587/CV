import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    // Components
    private JTextField inputField;
    private JButton[] numberButtons;
    private JButton addBtn, subBtn, mulBtn, divBtn, decBtn, eqBtn, clrBtn, negBtn;
    private JPanel panel;

    // Variables
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public SimpleCalculator() {
        // Frame setup
        setTitle("Simple Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input field
        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setHorizontalAlignment(SwingConstants.RIGHT);
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        // Buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            numberButtons[i].addActionListener(this);
        }

        decBtn = new JButton(".");
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        eqBtn = new JButton("=");
        clrBtn = new JButton("C");
        negBtn = new JButton("+/-");

        JButton[] funcButtons = {decBtn, addBtn, subBtn, mulBtn, divBtn, eqBtn, clrBtn, negBtn};
        for (JButton btn : funcButtons) {
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
        }

        // Panel layout (grid for buttons)
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        // Add buttons to panel
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(addBtn);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subBtn);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(mulBtn);

        panel.add(decBtn);
        panel.add(numberButtons[0]);
        panel.add(negBtn);
        panel.add(divBtn);

        panel.add(clrBtn);
        panel.add(eqBtn);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Numbers
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                inputField.setText(inputField.getText().concat(String.valueOf(i)));
            }
        }

        // Decimal
        if (e.getSource() == decBtn) {
            if (!inputField.getText().contains(".")) {
                inputField.setText(inputField.getText().concat("."));
            }
        }

        // Operators
        if (e.getSource() == addBtn) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '+';
            inputField.setText("");
        }
        if (e.getSource() == subBtn) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '-';
            inputField.setText("");
        }
        if (e.getSource() == mulBtn) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '*';
            inputField.setText("");
        }
        if (e.getSource() == divBtn) {
            num1 = Double.parseDouble(inputField.getText());
            operator = '/';
            inputField.setText("");
        }

        // Equals
        if (e.getSource() == eqBtn) {
            try {
                num2 = Double.parseDouble(inputField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            inputField.setText("Division by zero");
                            return;
                        } else {
                            result = num1 / num2;
                        }
                        break;
                }
                inputField.setText(String.valueOf(result));
                num1 = result; // allows chaining
            } catch (Exception ex) {
                inputField.setText("Error");
            }
        }

        // Clear
        if (e.getSource() == clrBtn) {
            inputField.setText("");
            num1 = num2 = result = 0;
        }

        // Negative toggle
        if (e.getSource() == negBtn) {
            try {
                double temp = Double.parseDouble(inputField.getText());
                temp *= -1;
                inputField.setText(String.valueOf(temp));
            } catch (Exception ex) {
                inputField.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
