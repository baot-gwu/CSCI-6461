import java.util.Arrays;
import java.util.List;

/**
 * @author jalal
 * @since 12/9/19
 */
public class Initializer {

    protected CiscComputer initialize(String[] ixValues) {

        CiscComputer ciscComputer = new CiscComputer();

        ciscComputer.setMemory(createMemory());

        ciscComputer.setIndexRegisters(createIndexRegisters(ixValues));

        ciscComputer.setGeneralPurposeRegisters(createGeneralPurposeRegisters());

        return ciscComputer;
    }

    private List<GeneralPurposeRegister> createGeneralPurposeRegisters() {
        return Arrays.asList(new GeneralPurposeRegister(0),
                new GeneralPurposeRegister(1),
                new GeneralPurposeRegister(2),
                new GeneralPurposeRegister(3));
    }

    private List<IndexRegister> createIndexRegisters(String[] valueOfRegisters) {
        return Arrays.asList(new IndexRegister(1, valueOfRegisters[0]),
                new IndexRegister(2, valueOfRegisters[1]),
                new IndexRegister(3, valueOfRegisters[2]));
    }

    private Memory createMemory() {
        Memory memory = new Memory();

        memory.loadContent();

        return memory;
    }
}
