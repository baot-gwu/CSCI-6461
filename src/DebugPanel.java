import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Map;

public class DebugPanel extends JFrame{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 720;
    private JPanel MainForm;
    private JPanel Controller;
    private JButton autoRunButton;
    private JButton singleRunButton;
    private JButton reloadButton;
    private JButton stopButton;
    private JButton restartButton;
    private JButton program1Button;
    private JButton program2Button;
    private JButton floatingTestButton;
    private JButton vectorTestButton;
    private JButton IPLButton;
    private JTextField R0_textField;
    private JTextField R1_textField;
    private JTextField R2_textField;
    private JPanel Memory;
    private JPanel Indicators;
    private JPanel Index;
    private JPanel Registers;
    private JTextField IX1_textField;
    private JTextField IX2_textField;
    private JTextField IX3_textField;
    private JLabel IX1_label;
    private JLabel IX2_label;
    private JLabel IX3_label;
    private JLabel R0_label;
    private JLabel R1_label;
    private JLabel R2_label;
    private JLabel R3_label;
    private JTextField R3_textField;
    private JPanel MemoryRegister;
    private JPanel BasicIndicators;
    private JTextField MAR_textField;
    private JTextField MBR_textField;
    private JLabel MAR_label;
    private JLabel MBR_label;
    private JTextField PC_textField;
    private JTextField IR_textField;
    private JTextField CC_textField3;
    private JLabel PC_label;
    private JLabel IR_label;
    private JLabel CC_label;
    private JList MemoryAddressList;
    private JScrollPane MemoryListScroll;
    private JLabel R0_value;
    private JLabel R1_value;
    private JLabel R2_value;
    private JLabel R3_value;
    private JLabel IX1_value;
    private JLabel IX2_value;
    private JLabel IX3_value;
    private JLabel MAR_value;
    private JLabel PC_value;
    private JLabel CC_value;
    private JTextField Instruction_textField;
    private JButton saveButton;
    private JButton pauseButton;
    private JLabel Instruction_label;
    private JLabel MFR;
    private JTextField MFR_textField;
    private JLabel MFR_value;
    private JButton R0_Button;
    private JButton IX1_Button;
    private JList MemoryValueList;
    private JList MemoryHexValueList;
    private JList MemoryAssembleCodeList;
    private JLabel MemoryAddressLabel;
    private JLabel MemoryValueLabel;
    private JLabel MemoryHexValue;
    private JLabel MemoryAssembleCode;
    private JButton R1_Button;
    private JButton R2_Button;
    private JButton R3_Button;
    private JButton IX2_Button;
    private JButton IX3_Button;
    private JButton MAR_Button;
    private JButton MBR_Button;
    private JButton PC_Button;
    private JButton IR_Button;
    private JButton CC_Button;
    private JButton MFR_Button;
    private JLabel MBR_value;
    private JLabel IR_value;
    private CiscComputer ciscComputer;
    private Memory memory;
    private boolean pause = true;

    public static final int MAX_MEMORY_SIZE = 2048;
    String memoryAddress[] = new String[MAX_MEMORY_SIZE];
    String memoryValue[] = new String[MAX_MEMORY_SIZE];
    String memoryHexValue[] = new String[MAX_MEMORY_SIZE];
    String memoryAssembleCode[] = new String[MAX_MEMORY_SIZE];

    DebugPanel() {
        super("Machine Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        getContentPane().add(MainForm);
        setVisible(true);

        Arrays.fill(memoryValue, "0000000000000000");
        Arrays.fill(memoryHexValue, "x0000");
        Arrays.fill(memoryAssembleCode, "null");
        for (int i = 0; i < memoryAddress.length; i++){
            memoryAddress[i] = "x" + Utils.autoFill(Utils.decimalToHex(i).toUpperCase(), 4);
        }

        MemoryAddressList.setListData(memoryAddress);
        MemoryValueList.setListData(memoryValue);
        MemoryHexValueList.setListData(memoryHexValue);
        MemoryAssembleCodeList.setListData(memoryAssembleCode);

        MemoryAddressList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2){
                    JDialog.getWindows();
                }
            }
        });

        R0_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String r0 = R0_textField.getText();
                if (Utils.binaryValid(r0)){
                    r0 = Utils.autoFill(r0, 16);
                    R0_textField.setText(r0);
                    R0_value.setText("x" + Utils.binaryToHex(r0));
                    getData();
                }
                else{
                    System.err.println("R0 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        R1_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = R1_textField.getText();
                if (Utils.binaryValid(register)){
                    register = Utils.autoFill(register, 16);
                    R1_textField.setText(register);
                    R1_value.setText("x" + Utils.binaryToHex(register));
                    getData();
                }
                else{
                    System.err.println("R1 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        R2_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = R2_textField.getText();
                if (Utils.binaryValid(register)){
                    register = Utils.autoFill(register, 16);
                    R2_textField.setText(register);
                    R2_value.setText("x" + Utils.binaryToHex(register));
                    getData();
                }
                else{
                    System.err.println("R2 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        R3_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = R3_textField.getText();
                if (Utils.binaryValid(register)){
                    register = Utils.autoFill(register, 16);
                    R3_textField.setText(register);
                    R3_value.setText("x" + Utils.binaryToHex(register));
                    getData();
                }
                else{
                    System.err.println("R3 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        IX1_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = IX1_textField.getText();
                if (Utils.binaryValid(register)){
                    register = Utils.autoFill(register, 16);
                    IX1_textField.setText(register);
                    IX1_value.setText("x" + Utils.binaryToHex(register));
                    getData();
                }
                else{
                    System.err.println("IX1 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        IX2_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = IX2_textField.getText();
                if (Utils.binaryValid(register)){
                    register = Utils.autoFill(register, 16);
                    IX2_textField.setText(register);
                    IX2_value.setText("x" + Utils.binaryToHex(register));
                    getData();
                }
                else{
                    System.err.println("IX2 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        IX3_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = IX3_textField.getText();
                if (Utils.binaryValid(register)){
                    register = Utils.autoFill(register, 16);
                    IX3_textField.setText(register);
                    IX3_value.setText("x" + Utils.binaryToHex(register));
                    getData();
                }
                else{
                    System.err.println("IX3 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        PC_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String pc = PC_textField.getText();
                if (Utils.binaryValid(pc)){
                    pc = Utils.autoFill(pc, 12);
                    PC_textField.setText(pc);
                    PC_value.setText("x" + Utils.binaryToHex(pc));
                    getData();
                }
                else{
                    System.err.println("PC is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        IR_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String ir = IR_textField.getText();
                if (Utils.binaryValid(ir)){
                    ir = Utils.autoFill(ir, 16);
                    IR_textField.setText(ir);
                    IR_value.setText("x" + Utils.binaryToHex(ir));
                    getData();
                }
                else{
                    System.err.println("IR is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        MAR_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String mar = MAR_textField.getText();
                if (Utils.binaryValid(mar)){
                    mar = Utils.autoFill(mar, 16);
                    MAR_textField.setText(mar);
                    MAR_value.setText("x" + Utils.binaryToHex(mar));
                    getData();
                }
                else{
                    System.err.println("MAR is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        MBR_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String mbr = MBR_textField.getText();
                if (Utils.binaryValid(mbr)){
                    mbr = Utils.autoFill(mbr, 16);
                    MBR_textField.setText(mbr);
                    MBR_value.setText("x" + Utils.binaryToHex(mbr));
                    getData();
                }
                else{
                    System.err.println("MBR is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                restart();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stop();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pause();
            }
        });

        singleRunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                singleRun();
            }
        });

        autoRunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pause = false;
                run();
            }
        });

        IPLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ipl();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                save();
            }
        });

        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reload();            }
        });

        MemoryAddressList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                syncListSelect(MemoryAddressList.getSelectedIndex());
            }
        });

        MemoryValueList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                syncListSelect(MemoryValueList.getSelectedIndex());
            }
        });

        MemoryHexValueList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                syncListSelect(MemoryHexValueList.getSelectedIndex());
            }
        });

        MemoryAssembleCodeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                syncListSelect(MemoryAssembleCodeList.getSelectedIndex());
            }
        });

        MemoryValueList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String data;
                if (!MemoryValueList.isSelectionEmpty() && e.getClickCount() == 2){
                    int index = MemoryValueList.getSelectedIndex();
                    data = JOptionPane.showInputDialog("Input binary value").toString();
                    if (!data.isEmpty() && Utils.binaryValid(data)){
//                        InstructionProcessor ip = new InstructionProcessor();
                        memoryValue[index] = Utils.autoFill(data, 16);
                        memoryHexValue[index] = "x" + Utils.binaryToHex(memoryValue[index]);
//                        memoryAssembleCode[index] = ip.getsymbolicForm(memoryValue[index]);
                        MemoryValueList.setListData(memoryValue);
                        MemoryHexValueList.setListData(memoryHexValue);
                        MemoryAssembleCodeList.setListData(memoryAssembleCode);
                        setMemory(index, memoryValue[index]);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        MemoryHexValueList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String data;
                if (!MemoryHexValueList.isSelectionEmpty() && e.getClickCount() == 2){
                    int index = MemoryHexValueList.getSelectedIndex();
                    data = JOptionPane.showInputDialog("Input Hex value").toString();
                    if (!data.isEmpty() && Utils.hexValid(data)){
//                        InstructionProcessor ip = new InstructionProcessor();
                        memoryHexValue[index] = "x" + data;
                        memoryValue[index] = Utils.autoFill(Utils.hexToBinary(data), 16);
//                        memoryAssembleCode[index] = ip.getsymbolicForm(memoryValue[index]);
                        MemoryValueList.setListData(memoryValue);
                        MemoryHexValueList.setListData(memoryHexValue);
                        MemoryAssembleCodeList.setListData(memoryAssembleCode);
                        setMemory(index, memoryValue[index]);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void syncListSelect(int selectedIndex) {
        if (MemoryAddressList.getSelectedIndex() != selectedIndex)
            MemoryAddressList.setSelectedIndex(selectedIndex);
        if (MemoryValueList.getSelectedIndex() != selectedIndex)
            MemoryValueList.setSelectedIndex(selectedIndex);
        if (MemoryHexValueList.getSelectedIndex() != selectedIndex)
            MemoryHexValueList.setSelectedIndex(selectedIndex);
        if (MemoryAssembleCodeList.getSelectedIndex() != selectedIndex)
            MemoryAssembleCodeList.setSelectedIndex(selectedIndex);
    }

    private void getMemory() {
        int index;
        String value;
        for (Map.Entry<Integer, String> entry : memory.memoryMap.entrySet()) {
            index = entry.getKey();
            value = entry.getValue();
            value = value.replace(" ","");
//            System.err.println("Key: " + index + ", Value: " + value);
            if (value.length() != 0){
//            InstructionProcessor ip = new InstructionProcessor();
                memoryValue[index] = (Utils.binaryValid(value) && value.length() == 16) ? Utils.autoFill(value, 16) : Utils.autoFill(Utils.decimalToBinary(Integer.parseInt(value)), 16);
                memoryHexValue[index] = "x" + Utils.binaryToHex(memoryValue[index]);
//            memoryAssembleCode[index] = ip.getsymbolicForm(memoryValue[index]);
                MemoryValueList.setListData(memoryValue);
                MemoryHexValueList.setListData(memoryHexValue);
                MemoryAssembleCodeList.setListData(memoryAssembleCode);
            }

        }
    }

    private void setMemory(int key, String value) {
        if (memory.memoryMap.containsKey(key))
            memory.memoryMap.replace(key, value);
        else
            memory.memoryMap.put(key, value);
    }

    public void setData(CiscComputer ciscComputer) {
        this.ciscComputer = ciscComputer;
        memory = ciscComputer.getMemory();
        Display data = new Display(ciscComputer, true);
        R0_textField.setText(Utils.autoFill(data.getR0(),16));
        R1_textField.setText(Utils.autoFill(data.getR1(),16));
        R2_textField.setText(Utils.autoFill(data.getR2(),16));
        R3_textField.setText(Utils.autoFill(data.getR3(),16));
        IX1_textField.setText(Utils.autoFill(data.getIx1(),16));
        IX2_textField.setText(Utils.autoFill(data.getIx2(),16));
        IX3_textField.setText(Utils.autoFill(data.getIx3(),16));
        MAR_textField.setText(Utils.autoFill(data.getMar(),16));
        MBR_textField.setText(Utils.autoFill(data.getMbr(),16));
        PC_textField.setText(Utils.autoFill(data.getPc(),12));
        IR_textField.setText(Utils.autoFill(data.getIr(),16));
        getMemory();
        binaryToHex();
    }

    public void getData() {
        Display data = new Display(this.ciscComputer, true);
        data.setR0(R0_textField.getText());
        data.setR1(R1_textField.getText());
        data.setR2(R2_textField.getText());
        data.setR3(R3_textField.getText());
        data.setIx1(IX1_textField.getText());
        data.setIx2(IX2_textField.getText());
        data.setIx3(IX3_textField.getText());
        data.setMar(MAR_textField.getText());
        data.setMbr(MBR_textField.getText());
        data.setPc(PC_textField.getText());
        data.setIr(IR_textField.getText());
    }

    public boolean isModified(Display data) {
        if (R0_textField.getText() != null ? !R0_textField.getText().equals(data.getR0()) : data.getR0() != null)
            return true;
        if (R1_textField.getText() != null ? !R1_textField.getText().equals(data.getR1()) : data.getR1() != null)
            return true;
        if (R2_textField.getText() != null ? !R2_textField.getText().equals(data.getR2()) : data.getR2() != null)
            return true;
        if (R3_textField.getText() != null ? !R3_textField.getText().equals(data.getR3()) : data.getR3() != null)
            return true;
        if (IX1_textField.getText() != null ? !IX1_textField.getText().equals(data.getIx1()) : data.getIx1() != null)
            return true;
        if (IX2_textField.getText() != null ? !IX2_textField.getText().equals(data.getIx2()) : data.getIx2() != null)
            return true;
        if (IX3_textField.getText() != null ? !IX3_textField.getText().equals(data.getIx3()) : data.getIx3() != null)
            return true;
        if (MAR_textField.getText() != null ? !MAR_textField.getText().equals(data.getMar()) : data.getMar() != null)
            return true;
        if (MBR_textField.getText() != null ? !MBR_textField.getText().equals(data.getMbr()) : data.getMbr() != null)
            return true;
        if (PC_textField.getText() != null ? !PC_textField.getText().equals(data.getPc()) : data.getPc() != null)
            return true;
        if (IR_textField.getText() != null ? !IR_textField.getText().equals(data.getIr()) : data.getIr() != null)
            return true;
        return false;
    }

    private void binaryToHex() {
        R0_value.setText("x" + Utils.binaryToHex(R0_textField.getText()));
        R1_value.setText("x" + Utils.binaryToHex(R1_textField.getText()));
        R2_value.setText("x" + Utils.binaryToHex(R2_textField.getText()));
        R3_value.setText("x" + Utils.binaryToHex(R3_textField.getText()));
        IX1_value.setText("x" + Utils.binaryToHex(IX1_textField.getText()));
        IX2_value.setText("x" + Utils.binaryToHex(IX2_textField.getText()));
        IX3_value.setText("x" + Utils.binaryToHex(IX3_textField.getText()));
        PC_value.setText("x" + Utils.binaryToHex(PC_textField.getText()));
        IR_value.setText("x" + Utils.binaryToHex(IR_textField.getText()));
        MAR_value.setText("x" + Utils.binaryToHex(MAR_textField.getText()));
        MBR_value.setText("x" + Utils.binaryToHex(MBR_textField.getText()));
    }

    private void restart() {
        // TODO: Reset machine
    }

    private void stop() {
        pause = true;
    }

    private void pause() {
        pause = true;
    }

    private void singleRun() {
        int address = Integer.parseInt(PC_textField.getText(), 2);
        ciscComputer.getProgramCounter().setDecimalValue(address + 1);
        ciscComputer.getInstructionRegister().setBinaryInstruction(memory.memoryMap.get(address));
        new InstructionProcessor().processInstruction(ciscComputer);

        Main.printValues(ciscComputer);
        setData(ciscComputer);
        if (address >= 39)
            pause = true;
    }

    private void run() {
         while (!pause){
             singleRun();
             try {
                Thread.sleep(250);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
         }
    }

    private void ipl() {
        ciscComputer.getMemory().loadContent();
    }

    private void reload() {
        ciscComputer.getMemory().loadContent();
    }

    private void save() {
        ciscComputer.getMemory().writeContent();
    }
}
