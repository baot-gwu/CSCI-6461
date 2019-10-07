package main.java.instruction;

import main.java.common.CiscComputer;

public class InputOutputProcessor implements InstructionProcessor {

    private static InstructionProcessor processor;

    static InstructionProcessor getInstance() {
        if (processor == null) {
            processor = new InputOutputProcessor();
        }

        return processor;
    }

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {

    }
}
