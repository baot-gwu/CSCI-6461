package main.java.panel;

import main.java.Main;
import main.java.common.CiscComputer;
import main.java.program.Program1;
import main.java.program.Program2;
import main.java.theme.Theme;
import main.java.util.Convert;
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
    private JPanel monitor;
    private JPanel Input;
    private JScrollPane screenScroll;
    private CiscComputer ciscComputer;
    private int user_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int user_height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private StringBuilder screenContent = new StringBuilder();
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
    private Thread pg2;
    private Thread dg;
    private Theme theme;
    private CountDownLatch keyboardSignal = new CountDownLatch(1);
    private final String[] COMMAND_LIST = {
            "Command\t\tUsage\t\tDescription\t\t",

            "autorun\t\tautorun\t\tRun The Instructions Until TRAP or HALT (Same as \"auto run\" and \"run\")",
            "auto run\t\tauto run\t\tRun The Instructions Until TRAP or HALT (Same as \"autorun\" and \"run\")",
            "clean\t\tclean\t\tClean The Console (Same as \"cls\")",
            "cls\t\tcls\t\tClean The Console (Same as \"clean\")",
            "exit\t\texit\t\tShutdown The Machine (Same as \"quit\" and \"power off\")",
            "floating test\tfloating test\tRun The Floating Test",
            "ipl\t\tipl\t\tLoad The Program From I/O",
            "pause\t\tpause\t\tPause The Workload on The Machine (not finished)",
            "power off\tpower off\tShutdown The Machine (Same as \"exit\" and \"quit\")",
            "Program1\t\tProgram1\t\tRun Program 1 (Same as \"Program 1\")",
            "Program 1\tProgram 1\tRun Program 1 (Same as \"Program1\")",
            "Program2\t\tProgram2\t\tRun Program 2 (Same as \"Program 2\")",
            "Program 2\tProgram 2\tRun Program 2 (Same as \"Program2\")",
            "quit\t\tquit\t\tShutdown The Machine (Same as \"exit\" and \"power off\")",
            "reload\t\treload\t\tReload The Data From memory.txt to Memory",
            "reset\t\treset\t\tRestart The Machine (Same as \"restart\")",
            "restart\t\trestart\t\tRestart The Machine (Same as \"reset\")",
            "run\t\trun\t\tRun The Instructions Until TRAP or HALT (Same as \"autorun\" and \"auto run\")",
            "save\t\tsave\t\tSave The Data in Memory to memory.txt",
            "singlerun\tsinglerun\tRun One Instruction (Same as \"single run\")",
            "single run\tsingle run\tRun One Instruction (Same as \"singlerun\")",
            "status\t\tstatus\t\tShow The Status of The Machine",
            "stop\t\tstop\t\tStop The Workload on The Machine",
            "switch theme\tswitch theme {$THEME_NAME}\tSwitch the Theme of UI. Now Support \"Material Design Ocean\" (or \"MaterialDesignOcean\") and \"Material Design Lighter\" (or \"MaterialDesignLighter\")",
            "vector test\tvector test\tRun The Vector Test",
            "/help\t\t/help\t\tShow The Command List",

    };

    public OperationPanel() {
        // create windows
        super("CISC Machine Simulator Console");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        getContentPane().add(MainForm);
        setLocation((user_width - 600 - WIDTH) / 2 + 600, (user_height - HEIGHT) / 2);
        setVisible(true);
        setTheme();
        screenContent.append(banner).append(arrow);
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
                        textField.setForeground(theme.getComments());
                    } else {
                        pushToScreen("Machine is BUSY!", true);
                    }
                }
                textField.setText(commandMode? tip_command: tip_data);
                textField.setForeground(theme.getComments());
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if ((commandMode && textField.getText().equals(tip_command)) || (!commandMode && textField.getText().equals(tip_data))){
                    textField.setText("");
                    textField.setForeground(theme.getForeground());
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()){
                    textField.setText(commandMode? tip_command: tip_data);
                    textField.setForeground(theme.getComments());
                } else {
                    textField.setForeground(theme.getForeground());
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
        screen.setText(screenContent.toString());
        screenScroll.getVerticalScrollBar().setValue(screenScroll.getVerticalScrollBar().getMaximum() + 10);
    }

    private void newLine() {
        screenContent.append(arrow);
        updateScreen();
    }

    public void pushNewLine() {
        newLine();
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
        } else if (command.length() > 12 && command.toLowerCase().substring(0,12).equals("switch theme")) {
            screenContent.append(command).append("\n");
            String themeName = command.toLowerCase().substring(13, command.length());
            if (themeName.equals("materialdesignocean") || themeName.equals("material design ocean"))
                Controller.switchTheme("Material Design Ocean");
            else if (themeName.equals("materialdesignlighter") || themeName.equals("material design lighter"))
                Controller.switchTheme("Material Design Lighter");
            else
                pushToScreen(themeName + " is not support.\n", false);
            newLine();
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
        screenContent.append(command).append("\n");
        if (command.equals("status")){
            screenContent.append("Machine Status is ").append(Main.busy);
            newLine();
        } else if (command.toLowerCase().equals("program1") || command.toLowerCase().equals("program 1")) {
            pg1 = new Thread(new program1());
            threadType = "program1";
            pg1.start();
        } else if (command.toLowerCase().equals("program2") || command.toLowerCase().equals("program 2")) {
            pg2 = new Thread(new program2());
            threadType = "program2";
            pg2.start();
        } else if (command.toLowerCase().equals("vector test")) {
            vectorTest();
        } else if (command.toLowerCase().equals("floating test")) {
            floatingTest();
        } else if (command.toLowerCase().equals("/help")) {
            pushToScreen(Utils.arrayToStringParagraph(COMMAND_LIST), false);
            newLine();
        } else if (command.toLowerCase().equals("convert")) {
            Convert.assembleToBinary();
            pushToScreen("Finished", false);
            newLine();
        } else {
            screenContent.append(command).append(" is not support. You can type \"/help\" to get command list.");
            newLine();
        }
    }

    public void pushToScreen(String content, boolean isIndependentContent) {
        if (isIndependentContent)
            screenContent.delete(screenContent.length() - 2, screenContent.length());
        screenContent.append(content);
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
                dg = new Thread(new DataGetter(signal, "Please input 20 numbers (using comma to split numbers): \n", 20, "int", false));
                dg.start();

                signal.await();

                int[] inputNumbers = Utils.stringToIntegerArray(dataBuffer, ",");

                signal = new CountDownLatch(1);
                dg = new Thread(new DataGetter(signal, "Please input the number you want to compare: \n", 1, "int", false));
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

    class program2 extends Thread {
        @Override
        public void run() {
            Main.busy = true;
            Program2 pg2 = new Program2();
            switchCommandMode(false);

            try {
                CountDownLatch signal = new CountDownLatch(1);

                int endAddress = pg2.readAndStoreParagraphIntoMemory(ciscComputer);
                String paragraph = pg2.loadParagraph(ciscComputer, endAddress);

                dg = new Thread(new DataGetter(signal, "Paragraph:\n" + paragraph +
                        "\n\nPlease input the word you want to match: \n", 1, "string", false));
                dg.start();

                signal.await();

                String result = pg2.matchWord(ciscComputer, endAddress, dataBuffer);

                pushToScreen(result.trim().equals("")? "Given Word: " + dataBuffer + " No matches" : result, false);
                newLine();
                threadType = "";
            } catch (InterruptedException e) {
                System.err.println("Program 2 interrupted");
                newLine();
            }

            switchCommandMode(true);
            Controller.update(ciscComputer);
            Main.busy = false;
        }
    }

    private void vectorTest() {
        pushToScreen("Vector Test not available now", false);
    }

    private void floatingTest() {
        pushToScreen("Floating Test not available now", false);
    }

    void reset() {
        screenContent.delete(0, screenContent.length()).append(banner).append(arrow);
        updateScreen();
    }

    void clear() {
        screenContent.delete(0, screenContent.length()).append("").append(cleanScreenBanner).append(arrow);
        updateScreen();
    }

    private void switchCommandMode(boolean commandMode) {
        this.commandMode = commandMode;
        textField.setText(commandMode? tip_command: tip_data);
        textField.setForeground(theme.getComments());
    }

    class DataGetter implements Runnable {
        private CountDownLatch signal;
        private String banner;
        private int size;
        private String mode;
        private boolean silent;

        DataGetter(CountDownLatch signal, String banner, int size, String mode, boolean silent){
            this.signal = signal;
            this.banner = banner;
            this.size = size;
            this.mode = mode;
            this.silent = silent;
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
                    if (!silent) {
                        if (size == data_size)
                            pushToScreen("All " + size + " data(s) got. (Now we got: " + Utils.arrayToString(dataList.toArray()) + ")\n", false);
                        else
                            pushToScreen(size - data_size + " data(s) need to input. (Now we got: " + Utils.arrayToString(dataList.toArray()) + ")\n", false);
                    }
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
                break;
            case "program2":
                System.err.println("Try to interrupt.");
                if (dg != null)
                    dg.interrupt();
                if (pg2 != null)
                    pg2.interrupt();
                break;
            default:
                System.err.println("Stop");
        }
    }

    protected void pause() {

    }

    public void setTheme() {
        theme = Main.theme;
//        SwingUtilities.updateComponentTreeUI(MainForm.getRootPane());
//        repaint();

        monitor.setBackground(theme.getBackground());
        screen.setBackground(theme.getBackground());
        screen.setForeground(theme.getForeground());
        screenScroll.setBackground(theme.getBackground());
    }

    public CiscComputer getCiscComputer() {
        return this.ciscComputer;
    }

    public StringBuilder getScreenContent() {
        return screenContent;
    }

    public void setScreenContent(StringBuilder screenContent) {
        this.screenContent = screenContent;
    }

    public void getFromKeyboard () {
        threadType = "IN";
        System.err.println("Input from keyboard");
        new Thread(new keyboard()).start();
    }

    class keyboard extends Thread {
        @Override
        public void run() {
            Main.busy = true;
            int memoryAddressStartPoint = 2000;
            switchCommandMode(false);

            try {
                CountDownLatch signal = new CountDownLatch(1);
                dg = new Thread(new DataGetter(signal, "", 1, "string", true));
                dg.start();

                signal.await();
                threadType = "";
                Main.dp.setMemory(7, Utils.autoFill(Utils.decimalToBinary(memoryAddressStartPoint), 16));
                if (Utils.numberValid(dataBuffer)) {
                    Main.dp.setMemory(memoryAddressStartPoint++, Utils.autoFill(Utils.decimalToBinary(Integer.valueOf(dataBuffer)), 16));
                    Main.dp.setMemory(memoryAddressStartPoint, Utils.autoFill("1111111111111111", 16));
                }
                else {
//                    String buffer = "";
                    for (int i = 0; i < dataBuffer.length(); i++){
                        Main.dp.setMemory(memoryAddressStartPoint++, Utils.autoFill(Utils.decimalToBinary(dataBuffer.charAt(i)), 16));
//                        if (i % 2 == 0 && i != 0){
//                            Main.dp.setMemory(memoryAddressStartPoint++, Utils.autoFill(buffer + "00", 16));
//                            buffer = "";
//                        }
//                        buffer += Utils.decimalToBinary(dataBuffer.charAt(i));
                    }
                    Main.dp.setMemory(memoryAddressStartPoint, Utils.autoFill("1111111111111111", 16));
                }
                pushToScreen(dataBuffer + "\n", false);
            } catch (InterruptedException e) {
                System.err.println("INPUT interrupted");
                newLine();
            }

            switchCommandMode(true);
            Controller.update(ciscComputer);
            Main.busy = false;
            Main.IO.countDown();
        }
    }
}
