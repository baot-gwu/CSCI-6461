import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Map;

/**
 * @author baot
 * @since 9/10/19
 *
 * Class is to represent and operate CISC computer with all necessary attributes
 */

public class DebugPanel extends JFrame {
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
    private InstructionDecoder instructionDecoder = new InstructionDecoder();
    private InstructionRegister instructionRegister;
    private Memory memory;
    private boolean pause = true;
    private String r0, r1, r2, r3, ix1, ix2, ix3, mar, mbr, pc, ir, cc, mfr;

    private String memoryAddress[] = new String[Main.MAX_MEMORY_SIZE];
    private String memoryValue[] = new String[Main.MAX_MEMORY_SIZE];
    private String memoryHexValue[] = new String[Main.MAX_MEMORY_SIZE];
    private String memoryAssembleCode[] = new String[Main.MAX_MEMORY_SIZE];

    DebugPanel() {
        // create windows
        super("Machine Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        getContentPane().add(MainForm);
        setVisible(true);

        // Initialize memory lists
        Arrays.fill(memoryValue, "0000000000000000");
        Arrays.fill(memoryHexValue, "x0000");
        Arrays.fill(memoryAssembleCode, "null");
        for (int i = 0; i < memoryAddress.length; i++) {
            memoryAddress[i] = "x" + Utils.autoFill(Utils.decimalToHex(i).toUpperCase(), 4);
        }

        MemoryAddressList.setListData(memoryAddress);
        MemoryValueList.setListData(memoryValue);
        MemoryHexValueList.setListData(memoryHexValue);
        MemoryAssembleCodeList.setListData(memoryAssembleCode);

        // set register = the value in the register_textfield
        R0_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = R0_textField.getText();
                if (Utils.binaryValid(register)) { // check if the input is binary
                    r0 = Utils.autoFill(register, 16); // auto fill 0 at the beginning of the string
                    R0_textField.setText(r0); // refresh textfield
                    R0_value.setText("x" + Utils.binaryToHex(r0)); // convert binary to hex value
                    getData(); // refresh the back end machine
                } else { // invalid input warning
                    System.err.println("R0 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        R1_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = R1_textField.getText();
                if (Utils.binaryValid(register)) {
                    r1 = Utils.autoFill(register, 16);
                    R1_textField.setText(r1);
                    R1_value.setText("x" + Utils.binaryToHex(r1));
                    getData();
                } else {
                    System.err.println("R1 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        R2_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = R2_textField.getText();
                if (Utils.binaryValid(register)) {
                    r2 = Utils.autoFill(register, 16);
                    R2_textField.setText(r2);
                    R2_value.setText("x" + Utils.binaryToHex(r2));
                    getData();
                } else {
                    System.err.println("R2 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        R3_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = R3_textField.getText();
                if (Utils.binaryValid(register)) {
                    r3 = Utils.autoFill(register, 16);
                    R3_textField.setText(r3);
                    R3_value.setText("x" + Utils.binaryToHex(r3));
                    getData();
                } else {
                    System.err.println("R3 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        IX1_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = IX1_textField.getText();
                if (Utils.binaryValid(register)) {
                    ix1 = Utils.autoFill(register, 16);
                    IX1_textField.setText(ix1);
                    IX1_value.setText("x" + Utils.binaryToHex(ix1));
                    getData();
                } else {
                    System.err.println("IX1 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        IX2_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = IX2_textField.getText();
                if (Utils.binaryValid(register)) {
                    ix2 = Utils.autoFill(register, 16);
                    IX2_textField.setText(ix2);
                    IX2_value.setText("x" + Utils.binaryToHex(ix2));
                    getData();
                } else {
                    System.err.println("IX2 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        IX3_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = IX3_textField.getText();
                if (Utils.binaryValid(register)) {
                    ix3 = Utils.autoFill(register, 16);
                    IX3_textField.setText(ix3);
                    IX3_value.setText("x" + Utils.binaryToHex(ix3));
                    getData();
                } else {
                    System.err.println("IX3 is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        PC_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = PC_textField.getText();
                if (Utils.binaryValid(register)) {
                    pc = Utils.autoFill(register, 12);
                    PC_textField.setText(pc);
                    PC_value.setText("x" + Utils.binaryToHex(pc));
                    getData();
                    syncListSelect(Integer.parseInt(pc, 2));
                } else {
                    System.err.println("PC is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        IR_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = IR_textField.getText();
                if (Utils.binaryValid(register)) {
                    ir = Utils.autoFill(register, 16);
                    IR_textField.setText(ir);
                    IR_value.setText("x" + Utils.binaryToHex(ir));
                    getData();
                } else {
                    System.err.println("IR is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        MAR_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = MAR_textField.getText();
                if (Utils.binaryValid(register)) {
                    mar = Utils.autoFill(register, 16);
                    MAR_textField.setText(mar);
                    MAR_value.setText("x" + Utils.binaryToHex(mar));
                    getData();
                } else {
                    System.err.println("MAR is not binary");
                    JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        MBR_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String register = MBR_textField.getText();
                if (Utils.binaryValid(register)) {
                    mbr = Utils.autoFill(register, 16); // format mbr value
                    MBR_textField.setText(mbr); // refresh mbr textfield
                    MBR_value.setText("x" + Utils.binaryToHex(mbr)); // refresh mbr hex label
                    writeMemory(mar, mbr); // set memory
                    mar = Utils.autoFill(Utils.decimalToBinary(Utils.binaryToDecimal(mar) + 1), 16); // mar++
                    mbr = Utils.autoFill("0", 16); // clean mbr
                    MAR_textField.setText(mar); // refresh mar textfield
                    MAR_value.setText("x" + Utils.binaryToHex(mar)); // refresh mar hex label
                    MBR_textField.setText(mbr); // refresh mbr textfield
                    MBR_value.setText("x" + Utils.binaryToHex(mbr)); // refresh mbr hex label
                    getData(); // refresh the back end machine
                } else {
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
                autoRun();
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
                reload();
            }
        });

        // synchronize four memory list selected index
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
                if (!MemoryValueList.isSelectionEmpty() && e.getClickCount() == 2) { // when double click
                    int index = MemoryValueList.getSelectedIndex();
                    data = JOptionPane.showInputDialog("Input binary value").toString();
                    if (!data.isEmpty() && Utils.binaryValid(data)) {
                        setMemory(index, Utils.autoFill(data, 16)); // set memory
                        getMemory(); // refresh memory list
                    } else
                        JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        MemoryHexValueList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String data;
                if (!MemoryHexValueList.isSelectionEmpty() && e.getClickCount() == 2) {
                    int index = MemoryHexValueList.getSelectedIndex();
                    data = JOptionPane.showInputDialog("Input Hex value").toString();
                    if (!data.isEmpty() && Utils.hexValid(data)) {
                        setMemory(index, Utils.autoFill(Utils.hexToBinary(data), 16));
                        getMemory();
                    } else
                        JOptionPane.showMessageDialog(null, "Input is not binary", "Incorrect Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void writeMemory(String mar, String mbr) { // Load MBR to MEM[MAR]
        setMemory(Utils.binaryToDecimal(mar), mbr);
        getMemory();
    }

    private void syncListSelect(int selectedIndex) { // synchronize four memory list selected index
        if (MemoryAddressList.getSelectedIndex() != selectedIndex)
            MemoryAddressList.setSelectedIndex(selectedIndex);
        if (MemoryValueList.getSelectedIndex() != selectedIndex)
            MemoryValueList.setSelectedIndex(selectedIndex);
        if (MemoryHexValueList.getSelectedIndex() != selectedIndex)
            MemoryHexValueList.setSelectedIndex(selectedIndex);
        if (MemoryAssembleCodeList.getSelectedIndex() != selectedIndex)
            MemoryAssembleCodeList.setSelectedIndex(selectedIndex);
        // jump the list to the position of selected index
        JScrollBar place = MemoryListScroll.getVerticalScrollBar();
        int place_value = place.getMaximum() * selectedIndex / Main.MAX_MEMORY_SIZE - 50;
        place.setValue((place_value < 0) ? 0 : place_value);
    }

    private void getMemory() { // fetch memory and show on the memory lists
        int index;
        String value;
        clearMemory();
        for (Map.Entry<Integer, String> entry : memory.memoryMap.entrySet()) {
            index = entry.getKey();
            value = entry.getValue();
            value = value.replace(" ", "");
            if (value.length() != 0) {
                memoryValue[index] = (Utils.binaryValid(value) && value.length() == 16) ? Utils.autoFill(value, 16) : Utils.autoFill(Utils.decimalToBinary(Integer.parseInt(value)), 16);
                memoryHexValue[index] = "x" + Utils.binaryToHex(memoryValue[index]);
                memoryAssembleCode[index] = getSymbolicForm(memoryValue[index]);
                MemoryValueList.setListData(memoryValue);
                MemoryHexValueList.setListData(memoryHexValue);
                MemoryAssembleCodeList.setListData(memoryAssembleCode);
            }

        }
    }

    private void setMemory(int key, String value) { // set MEM[key] = value
        if (memory.memoryMap.containsKey(key))
            memory.memoryMap.replace(key, value);
        else
            memory.memoryMap.put(key, value);
    }

    public void setData(CiscComputer ciscComputer) { // update front-end from back-end
        this.ciscComputer = ciscComputer;
        memory = ciscComputer.getMemory();
        Display data = new Display(ciscComputer, true);
        r0 = (String.valueOf(data.getR0()) == "null" || data.getR0().length() == 0) ? "0" : data.getR0();
        r1 = (String.valueOf(data.getR1()) == "null" || data.getR1().length() == 0) ? "0" : data.getR1();
        r2 = (String.valueOf(data.getR2()) == "null" || data.getR2().length() == 0) ? "0" : data.getR2();
        r3 = (String.valueOf(data.getR3()) == "null" || data.getR3().length() == 0) ? "0" : data.getR3();
        ix1 = (String.valueOf(data.getIx1()) == "null" || data.getIx1().length() == 0) ? "0" : data.getIx1();
        ix2 = (String.valueOf(data.getIx2()) == "null" || data.getIx2().length() == 0) ? "0" : data.getIx2();
        ix3 = (String.valueOf(data.getIx3()) == "null" || data.getIx3().length() == 0) ? "0" : data.getIx3();
        pc = (String.valueOf(data.getPc()) == "null" || data.getPc().length() == 0) ? "0" : data.getPc();
        ir = (String.valueOf(data.getIr()) == "null" || data.getIr().length() == 0) ? "0" : data.getIr();
        mar = (String.valueOf(data.getMar()) == "null" || data.getMar().length() == 0) ? "0" : data.getMar();
        mbr = (String.valueOf(data.getMbr()) == "null" || data.getMbr().length() == 0) ? "0" : data.getMbr();

        R0_textField.setText(Utils.autoFill(r0, 16));
        R1_textField.setText(Utils.autoFill(r1, 16));
        R2_textField.setText(Utils.autoFill(r2, 16));
        R3_textField.setText(Utils.autoFill(r3, 16));
        IX1_textField.setText(Utils.autoFill(ix1, 16));
        IX2_textField.setText(Utils.autoFill(ix2, 16));
        IX3_textField.setText(Utils.autoFill(ix3, 16));
        MAR_textField.setText(Utils.autoFill(mar, 16));
        MBR_textField.setText(Utils.autoFill(mbr, 16));
        PC_textField.setText(Utils.autoFill(pc, 12));
        IR_textField.setText(Utils.autoFill(ir, 16));
        getMemory();
        binaryToHex();
    }

    public void getData() { // update back-end from front-end
        Display data = new Display(this.ciscComputer, true);
        data.setR0(r0);
        data.setR1(r1);
        data.setR2(r2);
        data.setR3(r3);
        data.setIx1(ix1);
        data.setIx2(ix2);
        data.setIx3(ix3);
        data.setMar(mar);
        data.setMbr(mbr);
        data.setPc(pc);
        data.setIr(ir);
    }

    private void binaryToHex() { // convert binary code to hex code
        R0_value.setText("x" + Utils.binaryToHex(Utils.autoFill(r0, 16)));
        R1_value.setText("x" + Utils.binaryToHex(Utils.autoFill(r1, 16)));
        R2_value.setText("x" + Utils.binaryToHex(Utils.autoFill(r2, 16)));
        R3_value.setText("x" + Utils.binaryToHex(Utils.autoFill(r3, 16)));
        IX1_value.setText("x" + Utils.binaryToHex(Utils.autoFill(ix1, 16)));
        IX2_value.setText("x" + Utils.binaryToHex(Utils.autoFill(ix2, 16)));
        IX3_value.setText("x" + Utils.binaryToHex(Utils.autoFill(ix3, 16)));
        PC_value.setText("x" + Utils.binaryToHex(Utils.autoFill(pc, 12)));
        IR_value.setText("x" + Utils.binaryToHex(Utils.autoFill(ir, 16)));
        MAR_value.setText("x" + Utils.binaryToHex(Utils.autoFill(mar, 16)));
        MBR_value.setText("x" + Utils.binaryToHex(Utils.autoFill(mbr, 16)));
    }

    private void restart() {
        new Memory().clear();  // clear all memory
        new Memory().loadBackupContent(); // load the memory with backup
        clear();
    }

    private void stop() {
        pause = true;
    }

    private void pause() {
        pause = true;
    }

    private void singleRun() {
        int address = Integer.parseInt(pc, 2); // get current counter
        ciscComputer.getProgramCounter().setDecimalValue(address + 1); // set pc
        ciscComputer.getInstructionRegister().setBinaryInstruction(memory.memoryMap.get(address)); // set ir
        Instruction instruction = new InstructionDecoder().decode(ciscComputer); // decode instruction
        new InstructionProcessor().processInstruction(ciscComputer, instruction); // execute instruction

        setData(ciscComputer); // update front-end
        syncListSelect(address + 1); // memory selected index jump to the pc

        // print the log to console
        System.out.println(Utils.symbolicForm(instruction));
        Main.printValues(ciscComputer);

        // HALT when address is 39 (HALT instruction is NOT REQUIRED in PHASE I)
        if (address >= 39)
            pause = true;
    }

    private void autoRun() {
        // multi-thread the back-end from main thread (front-end)
        Thread backEnd = new Thread(new BackEnd());
        backEnd.start();
    }

    private void ipl() { // import instructions from file (not available not)
        ciscComputer.getMemory().loadContent();
        getMemory();
    }

    private void reload() { // reload the memory file
        ciscComputer.getMemory().loadContent();
        getMemory();
    }

    private void save() { // save the memory file
        ciscComputer.getMemory().writeContent();
        getMemory();
    }

    private String getSymbolicForm(String binaryInstruction) { // convert binary instruction to assemble code
        // format input binary code
        if (String.valueOf(binaryInstruction) == "null" || binaryInstruction.trim().toString() == "")
            binaryInstruction = "0";
        Utils.autoFill(binaryInstruction, 16);
        // Initialize instruction decoder
        instructionRegister = ciscComputer.getInstructionRegister();
        instructionRegister.setBinaryInstruction(binaryInstruction);
        Instruction instruction = instructionDecoder.decode(ciscComputer);

        String symbolicForm = Utils.symbolicForm(instruction);
        return (symbolicForm == null) ? "null" : symbolicForm;
    }


    private void clear() { // reset machine and front-end (except memory)
        ciscComputer = new Initializer().initialize();
        setData(ciscComputer);
    }

    private void clearMemory() { // reset the memory
        memoryAddress = new String[Main.MAX_MEMORY_SIZE];
        memoryValue = new String[Main.MAX_MEMORY_SIZE];
        memoryHexValue = new String[Main.MAX_MEMORY_SIZE];
        memoryAssembleCode = new String[Main.MAX_MEMORY_SIZE];

        Arrays.fill(memoryValue, "0000000000000000");
        Arrays.fill(memoryHexValue, "x0000");
        Arrays.fill(memoryAssembleCode, "null");
        for (int i = 0; i < memoryAddress.length; i++) {
            memoryAddress[i] = "x" + Utils.autoFill(Utils.decimalToHex(i).toUpperCase(), 4);
        }
    }

    class BackEnd extends Thread {
        // delay AUTORUN_DELAY ms after execute a singleRun
        @Override
        public void run() {
            while (!pause) {
                singleRun();
                try {
                    Thread.sleep(Main.AUTORUN_DELAY);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
