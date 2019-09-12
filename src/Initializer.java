import java.util.Arrays;
import java.util.List;

/**
 * @author jalal
 * @since 12/9/19
 */
public class Initializer {

    protected CiscComputer initialize() {

        CiscComputer ciscComputer = new CiscComputer();

        ciscComputer.setMemory(createMemory());

        ciscComputer.setIndexRegisters(createIndexRegisters());

        ciscComputer.setGeneralPurposeRegisters(createGeneralPurposeRegisters());

        return ciscComputer;
    }

    protected void initializeIndexRegister(List<IndexRegister> indexRegisters, String[] ixValues) {
        for (IndexRegister indexRegister : indexRegisters) {
            indexRegister.setValue(ixValues[indexRegisters.indexOf(indexRegister)]);
        }
    }

    private List<GeneralPurposeRegister> createGeneralPurposeRegisters() {
        return Arrays.asList(new GeneralPurposeRegister(0),
                new GeneralPurposeRegister(1),
                new GeneralPurposeRegister(2),
                new GeneralPurposeRegister(3));
    }

    private List<IndexRegister> createIndexRegisters() {
        return Arrays.asList(new IndexRegister(1),
                new IndexRegister(2),
                new IndexRegister(3));
    }

    private Memory createMemory() {
        Memory memory = new Memory();

        memory.loadContent();

        return memory;
    }
}
