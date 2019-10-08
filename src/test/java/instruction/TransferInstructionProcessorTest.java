package test.java.instruction;

import main.java.common.CiscComputer;
import main.java.common.Initializer;
import main.java.register.ConditionCode;
import main.java.register.ProgramCounter;
import main.java.register.Register;
import org.junit.Before;

public class TransferInstructionProcessorTest {

    private CiscComputer ciscComputer;
    private Register register0;
    private Register register1;
    private Register register2;
    private Register register3;
    private ProgramCounter pc = new ProgramCounter();
    private ConditionCode cc;

    @Before
    public void setUp() {
        ciscComputer = new Initializer().initialize();
        register0 = ciscComputer.getGeneralPurposeRegister(0);
        register1 = ciscComputer.getGeneralPurposeRegister(1);
        register2 = ciscComputer.getGeneralPurposeRegister(2);
        register3 = ciscComputer.getGeneralPurposeRegister(3);
        pc.setDecimalValue(0);
        cc = ciscComputer.getConditionCode();
    }

}
