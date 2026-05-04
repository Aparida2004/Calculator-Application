import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModernCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public ModernCalculator() {

        setTitle("Modern Calculator");
        setSize(350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        // 🔹 Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        add(display, BorderLayout.NORTH);

        // 🔹 Buttons Panel
        JPanel panel = new JPanel(new GridLayout(5,4,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        String[] buttons = {
            "C", "", "", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", ""
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);

            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);

            btn.addActionListener(this);

            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        // 🔹 Number input
        if (command.matches("[0-9.]")) {
            display.setText(display.getText() + command);
        }

        // 🔹 Clear
        else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        }

        // 🔹 Operators
        else if (command.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }

        // 🔹 Equals
        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num2 != 0 ? num1 / num2 : 0; break;
            }

            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new ModernCalculator();
    }
}