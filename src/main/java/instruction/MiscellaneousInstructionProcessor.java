package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.memory.Address;
import main.java.memory.Cache;
import main.java.memory.Word;
import main.java.util.Utils;

public class MiscellaneousInstructionProcessor  implements InstructionProcessor {

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
                trap(ciscComputer);
                break;
            case HLT:
                stopTheMachine();
                break;
        }
    }

    private void stopTheMachine() {

    }

    private void trap(CiscComputer ciscComputer) {
        Cache.writeToMemory(new Address(2), new Word(Utils.decimalToUnsignedBinary(ciscComputer.getProgramCounter().getDecimalValue() + 1)));
    }
}
