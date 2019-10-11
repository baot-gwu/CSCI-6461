package main.java.panel;

import main.java.Main;
import main.java.common.CiscComputer;
import main.java.program.Program1;
import main.java.util.Utils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
    private String tip_command = "Input Command here...";
    private String tip_data = "Input Data here...";
    private String arrow = "\n> ";
    private Boolean commandMode = true;
    private String dataBuffer = "";
    private Object dataBufferFlag = new Object();
    private String threadType;
    private Thread pg1;
    private Thread dg;

    public OperationPanel() {
        // create windows
        super("CISC Machine Simulator Console");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        getContentPane().add(MainForm);
        setLocation((user_width - 600 - WIDTH) / 2 + 600, (user_height - HEIGHT) / 2);
        setVisible(true);
        screen_content.append(banner).append(arrow);
        updateScreen();

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!commandMode) {
                    pushData();
                } else {
                    if (!Main.busy) {
                        pushCommand();
                        textField.setText(commandMode? tip_command: tip_data);
                        textField.setForeground(gray);
                    } else {
                        pushToScreen("Machine is BUSY!", true);
                    }
                }
                textField.setText(commandMode? tip_command: tip_data);
                textField.setForeground(gray);
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if ((commandMode && textField.getText().equals(tip_command)) || (!commandMode && textField.getText().equals(tip_data))){
                    textField.setText("");
                    textField.setForeground(black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()){
                    textField.setText(commandMode? tip_command: tip_data);
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
                    if (!commandMode) {
                        pushData();
                    } else {
                        if (!Main.busy) {
                            pushCommand();
                        } else {
                            pushToScreen("Machine is BUSY!", true);
                        }
                    }
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

    private void newLine() {
        screen_content.append(arrow);
        updateScreen();
    }

    private void pushCommand() {
        String command = textField.getText().trim();
        if (((commandMode && command.equals(tip_command)) || (!commandMode && command.equals(tip_data))) || command.isEmpty()) {

        } else if (command.toLowerCase().equals("cls") || command.toLowerCase().equals("clean")){
            Controller.clear();
        } else if (command.toLowerCase().equals("reset") || command.toLowerCase().equals("restart")) {
            Controller.reset();
        } else if (command.toLowerCase().equals("stop")) {
            Controller.stop();
        } else if (command.toLowerCase().equals("pause")) {
            Controller.pause();
        } else if (command.toLowerCase().equals("singlerun") || command.toLowerCase().equals("single run")) {
            Controller.singleRun();
        } else if (command.toLowerCase().equals("autorun") || command.toLowerCase().equals("auto run") || command.toLowerCase().equals("run")) {
            Controller.autoRun();
        } else if (command.toLowerCase().equals("ipl")) {
            Controller.ipl();
        } else if (command.toLowerCase().equals("save")) {
            Controller.save();
        } else if (command.toLowerCase().equals("reload")) {
            Controller.reload();
        } else if (command.toLowerCase().equals("exit") || command.toLowerCase().equals("quit") || command.toLowerCase().equals("shutdown") || command.toLowerCase().equals("power off")) {
            System.exit(EXIT_ON_CLOSE);
        } else {
            sendCommand(command);
        }
        textField.setText("");
    }

    private void pushData() {
        dataBuffer = textField.getText();
        textField.setText("");
        synchronized (dataBufferFlag){
            dataBufferFlag.notify();
        }
    }

    public void sendCommand(String command){
        screen_content.append(command).append("\n");
        if (command.equals("status")){
            screen_content.append("Machine Status is ").append(Main.busy);
            newLine();
        } else if (command.toLowerCase().equals("program1") || command.toLowerCase().equals("program 1")) {
            pg1 = new Thread(new program1());
            threadType = "program1";
            pg1.start();
        } else if (command.toLowerCase().equals("program2") || command.toLowerCase().equals("program 2")) {
            program2();
        } else if (command.toLowerCase().equals("vector test")) {
            vectorTest();
        } else if (command.toLowerCase().equals("floating test")) {
            floatingTest();
        } else {
            screen_content.append(command).append(" is not support.");
            newLine();
        }
    }

    public void pushToScreen(String content, boolean isIndependentContent) {
        if (isIndependentContent)
            screen_content.delete(screen_content.length() - 2, screen_content.length());
        screen_content.append(content);
        updateScreen();
    }

    class program1 extends Thread {
        @Override
        public void run() {
            Main.busy = true;
            int memoryAddressStartPoint = 2000;
            Program1 pg1 = new Program1();
            switchCommandMode(false);

            try {
                CountDownLatch signal = new CountDownLatch(1);
                dg = new Thread(new DataGetter(signal, "Please input 20 numbers (using comma to split numbers): \n", 20, "int"));
                dg.start();

                signal.await();

                int[] inputNumbers = Utils.stringToIntegerArray(dataBuffer, ",");

                signal = new CountDownLatch(1);
                dg = new Thread(new DataGetter(signal, "Please input the number you want to compare: \n", 1, "int"));
                dg.start();

                signal.await();

                int numberToBeComparedWith = Integer.parseInt(dataBuffer);
                pg1.inputAndStoreNumber(ciscComputer, inputNumbers, memoryAddressStartPoint);

                pushToScreen("The closest number compare with " + numberToBeComparedWith + " is " + pg1.findClosestNumber(ciscComputer, numberToBeComparedWith, inputNumbers.length, memoryAddressStartPoint), false);
                newLine();
                threadType = "";
            } catch (InterruptedException e) {
                System.err.println("Program 1 interrupted");
                newLine();
            }

            switchCommandMode(true);
            Controller.update(ciscComputer);
            Main.busy = false;
        }
    }

    private void program2() {
        pushToScreen("Program 2 not available now", false);
    }

    private void vectorTest() {
        pushToScreen("Vector Test not available now", false);
    }

    private void floatingTest() {
        pushToScreen("Floating Test not available now", false);
    }

    protected void reset() {
        screen_content.delete(0, screen_content.length()).append(banner).append(arrow);
        updateScreen();
    }

    protected void clear() {
        screen_content.delete(0, screen_content.length()).append("").append(cleanScreenBanner).append(arrow);
        updateScreen();
    }

    private void switchCommandMode(boolean commandMode) {
        this.commandMode = commandMode;
        textField.setText(commandMode? tip_command: tip_data);
        textField.setForeground(gray);
    }

    class DataGetter implements Runnable {
        private CountDownLatch signal;
        private String banner;
        private int size;
        private String mode;

        DataGetter(CountDownLatch signal, String banner, int size, String mode){
            this.signal = signal;
            this.banner = banner;
            this.size = size;
            this.mode = mode;
        }

        @Override
        public void run() {
            dataBuffer = "";
            List<String> dataList = new ArrayList<String>();
            int data_size = 0;
            pushToScreen(banner, false);
            try {
                while (data_size < size) {
                    synchronized (dataBufferFlag){
                        dataBufferFlag.wait();
                    }
                    String[] temp = dataBuffer.split(",");
                    for (int i = 0; i < temp.length; i++){
                        if (!temp[i].trim().isEmpty() && data_size < size){
                            if (mode.equals("int") && !StringUtils.isNumeric(temp[i].trim())){

                            } else {
                                dataList.add(temp[i]);
                                data_size++;
                            }
                        }
                    }
                    if (size == data_size)
                        pushToScreen("All " + size + " data(s) got. (Now we got: " + Utils.arrayToString(dataList.toArray()) + ")\n", false);
                    else
                        pushToScreen(size - data_size + " data(s) need to input. (Now we got: " + Utils.arrayToString(dataList.toArray()) + ")\n", false);
                }
                dataBuffer = StringUtils.join(dataList.toArray(), ",");
                signal.countDown();
            } catch (InterruptedException e) {
            }
        }
    }

    protected void stopThread() {
        switch (threadType) {
            case "program1":
                System.err.println("Try to interrupt.");
                if (dg != null)
                    dg.interrupt();
                if (pg1 != null)
                    pg1.interrupt();
        }
    }

    protected void pause() {

    }
}
