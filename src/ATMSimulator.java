import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMSimulator {
    private static String accountNumber = null;
    private static String pin = null;
    private static double balance = 5000.0;
    private static JTextField cardNumberField, pinField;
    private static JFrame frame;
    private static JPanel panel;

    public static void main(String[] args) {
        frame = new JFrame("ATM System");
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel cardNumberLabel = new JLabel("Card Number: ");
        cardNumberField = new JTextField();
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);

        JLabel pinLabel = new JLabel("PIN: ");
        pinField = new JPasswordField();
        panel.add(pinLabel);
        panel.add(pinField);

        JButton loginButton = new JButton("Login");
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNumberInput = cardNumberField.getText();
                String pinInput = pinField.getText();

                if (accountNumber == null || pin == null) {
                    setupAccount(cardNumberInput, pinInput);
                } else {
                    if (cardNumberInput.equals(accountNumber) && pinInput.equals(pin)) {
                        JOptionPane.showMessageDialog(frame, "Login successful!");
                        showATMMenu();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid card number or PIN!");
                    }
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }

    private static void setupAccount(String cardNumberInput, String pinInput) {
        int choice = JOptionPane.showConfirmDialog(frame, "No account found. Would you like to set up an account?", "Account Setup", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            accountNumber = cardNumberInput;
            pin = pinInput;
            JOptionPane.showMessageDialog(frame, "Account created successfully!\nAccount Number: " + accountNumber);
            showATMMenu();
        } else {
            JOptionPane.showMessageDialog(frame, "Account setup was canceled. Please restart the application to create an account.");
            System.exit(0);
        }
    }

    private static void showATMMenu() {
        frame.dispose();
        frame = new JFrame("ATM Menu");
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton exitButton = new JButton("Exit");

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Current Balance: $" + balance);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw: ");
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (amount > balance) {
                        JOptionPane.showMessageDialog(frame, "Insufficient funds!");
                    } else {
                        balance -= amount;
                        JOptionPane.showMessageDialog(frame, "You withdrew: $" + amount);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount entered!");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter amount to deposit: ");
                try {
                    double amount = Double.parseDouble(amountStr);
                    balance += amount;
                    JOptionPane.showMessageDialog(frame, "You deposited: $" + amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount entered!");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Thank you for using ATM!");
                System.exit(0);
            }
        });

        panel.add(checkBalanceButton);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
}
