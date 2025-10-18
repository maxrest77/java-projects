import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1, num2, result;
    private char operator;

    public Calculator() {
        // Frame setup
        setTitle("Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);

        // Display field
        display = new JTextField();
        display.setBounds(20, 40, 290, 60);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        add(display);

        // Buttons
        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+",
            "C", "←"
        };

        int x = 20, y = 120;
        for (int i = 0; i < buttons.length; i++) {
            JButton btn = new JButton(buttons[i]);
            btn.setBounds(x, y, 65, 55);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setFocusable(false);
            btn.addActionListener(this);
            add(btn);

            x += 70;
            if ((i + 1) % 4 == 0) {
                x = 20;
                y += 65;
            }

            // Last two buttons (C and backspace) position adjust
            if (i == 15) {
                y += 10;
                x = 20;
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            display.setText(display.getText() + command);
        } 
        else if (command.charAt(0) == 'C') {
            display.setText("");
        } 
        else if (command.charAt(0) == '←') {
            String text = display.getText();
            if (!text.isEmpty())
                display.setText(text.substring(0, text.length() - 1));
        } 
        else if (command.charAt(0) == '=') {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': 
                        if (num2 == 0) {
                            display.setText("Error");
                            return;
                        }
                        result = num1 / num2; 
                        break;
                }
                display.setText(String.valueOf(result));
                num1 = result; // Allow chaining
            } catch (Exception ex) {
                display.setText("Error");
            }
        } 
        else {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                display.setText("");
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
