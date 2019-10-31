package test.java.instruction;


import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.device.ConsoleKeyboard;
import main.java.device.ConsolePrinter;
import main.java.device.Device;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionDecoder;
import main.java.register.Register;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputOutputRegisterProcessorTest {
    private CiscComputer ciscComputer;
    private Register register1;
    private Device device;

    @Before
    public void setUp() {
        ciscComputer = new Initializer().initialize();
        register1 = ciscComputer.getGeneralPurposeRegister(1);
    }

    @Test
    public void testInConsoleKeyboard() {
        device = new ConsoleKeyboard();
        device.setBinaryValue("0010101101010111");
        ciscComputer.setDevice(device);

        ciscComputer.getInstructionRegister().setBinaryInstruction("1111010100000000");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("IN 1,0", instruction.symbolicForm());
        assertEquals("0010101101010111", register1.getValue(true));
    }

    @Test
    public void testOutConsolePrinter() {
        device = new ConsolePrinter();
        ciscComputer.setDevice(device);
        register1.setBinaryValue("0000000100000110");

        ciscComputer.getInstructionRegister().setBinaryInstruction("1111100100000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("OUT 1,1", instruction.symbolicForm());
        assertEquals("0000000100000110", device.getBinaryValue());
    }

    @Test
    public void testDeviceStatus() {
        device = new ConsolePrinter();
        ciscComputer.setDevice(device);

        ciscComputer.getInstructionRegister().setBinaryInstruction("1111110100000001");
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        assertEquals("CHK 1,1", instruction.symbolicForm());
    }
}
