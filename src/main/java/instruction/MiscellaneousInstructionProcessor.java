package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.memory.Word;
import main.java.util.Utils;

/*
 * This is class for TRAP instruction
 *
 * Half instruction executed by Debug Panel for integration with UI.
 */
import static main.java.memory.Memory.RESERVE_ADDRESS_TO_STORE_PC_FOR_TRAP;

public class MiscellaneousInstructionProcessor  implements InstructionProcessor {

    private static final int TABLE_ADDRESS_IN_MEMORY_FOR_TRAP = 1000;

    private static InstructionProcessor processor;

    static InstructionProcessor getInstance() {
        if (processor == null) {
            processor = new MiscellaneousInstructionProcessor();
        }

        return processor;
    }

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {
        switch (instruction.getType()) {
            case TRAP:
                trap(ciscComputer, instruction.getTrapCode());
                break;
            case HLT:
                stopTheMachine();
                break;
        }
    }

    private void stopTheMachine() {

    }

    private void trap(CiscComputer ciscComputer, Integer trapCode) {
        Cache.writeToMemory(new Address(RESERVE_ADDRESS_TO_STORE_PC_FOR_TRAP),
                new Word(Utils.decimalToUnsignedBinary(ciscComputer.getProgramCounter().getDecimalValue() + 1)));

        int addressOfRoutineInstruction = TABLE_ADDRESS_IN_MEMORY_FOR_TRAP + trapCode;

        ciscComputer.getProgramCounter().setDecimalValue(addressOfRoutineInstruction);
        ciscComputer.getInstructionRegister().setBinaryInstruction(Cache.getWordStringValue(new Address(addressOfRoutineInstruction)));
        Instruction instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);

        ciscComputer.getProgramCounter().setDecimalValue(Cache.getWordDecimalValue(new Address(RESERVE_ADDRESS_TO_STORE_PC_FOR_TRAP)));
    }
}
