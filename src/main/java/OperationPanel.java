package main.java;

import main.java.common.CiscComputer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OperationPanel extends JFrame{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JPanel MainForm;
    private JTextField textField;
    private JButton enterButton;
    private JTextPane screen;
    private CiscComputer ciscComputer;
    private int user_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int user_height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private StringBuilder screen_content = new StringBuilder();
    private Color white = new Color(255,255,255);
    private Color gray = new Color(133, 133, 133);
    private Color black = new Color(0, 0, 0);
    private String banner = "Welcome to CISC Computer Simulator!";
    private String cleanScreenBanner = "==== New Screen =====";
    private String tip = "Input Command here...";

    public OperationPanel() {
        // create windows
        super("CISC Machine Simulator Console");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        getContentPane().add(MainForm);
        setLocation((user_width - 600 - WIDTH) / 2 + 600, (user_height - HEIGHT) / 2);
        setVisible(true);
        screen_content.append(banner).append("\n>");
        updateScreen();

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pushCommand();
                textField.setText(tip);
                textField.setForeground(gray);
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(tip)){
                    textField.setText("");
                    textField.setForeground(black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()){
                    textField.setText(tip);
                    textField.setForeground(gray);
                } else {
                    textField.setForeground(black);
                }
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    pushCommand();
                }
            }
        });
    }

    public void setData(CiscComputer ciscComputer) { // update front-end from back-end
        this.ciscComputer = ciscComputer;
    }

    private void updateScreen() {
        screen.setText(screen_content.toString());
    }

    private void pushCommand() {
        String command = textField.getText().trim();
        if (command.equals(tip) || command.isEmpty()) {

        } else if (command.equals("cls") || command.equals("clean")){
            System.err.println("Clear!");
            screen_content.delete(0, screen_content.length()).append("").append(cleanScreenBanner).append("\n>");
            updateScreen();
        } else if (command.equals("reset")) {
            System.err.println("Reset!");
            screen_content.delete(0, screen_content.length()).append(banner).append("\n>");
            updateScreen();
        }else {
            screen_content.append(command).append("\n>");
            updateScreen();
        }
        textField.setText("");
    }
}
