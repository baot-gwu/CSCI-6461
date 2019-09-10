import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DebugPanel extends JFrame{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 720;
    private JPanel MainForm;
    private JPanel Controller;
    private JButton autoRunButton;
    private JButton singleRunButton;
    private JButton loadButton;
    private JButton startButton;
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
    private JButton importButton;
    private JButton pauseButton;
    private JLabel Instruction_label;
    private JLabel MFR;
    private JTextField MFR_textField;
    private JLabel MFR_value;
    private JButton wButton;
    private JButton wButton1;
    private JList MemoryValueList;
    private JList MemoryHexValueList;
    private JList MemoryAssembleCodeList;
    private JLabel MemoryAddressLabel;
    private JLabel MemoryValueLabel;
    private JLabel MemoryHexValue;
    private JLabel MemoryAssembleCode;

    public DebugPanel () {
        super("Machine Simulator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        getContentPane().add(MainForm);
        setVisible(true);

        MemoryAddressList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2){
                    JDialog.getWindows();
                }
            }
        });
    }
}
