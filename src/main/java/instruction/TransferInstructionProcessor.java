package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.memory.Address;
import main.java.register.Register;

public class TransferInstructionProcessor implements InstructionProcessor {

    private static InstructionProcessor processor;

    static InstructionProcessor getInstance() {
        if (processor == null) {
            processor = new TransferInstructionProcessor();
        }

        return processor;
    }

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {
        Address address = AddressDecoder.decodeAddress(instruction);
        Register firstRegister = instruction.getFirstRegister();
        Register secondRegister = instruction.getSecondRegister();
        ciscComputer.getConditionCode().setConditionCodeType(null);
        ciscComputer.getMemoryAddressRegister().setDecimalValue(0);
        ciscComputer.getMemoryBufferRegister().setDecimalValue(0);

        switch (instruction.getType()) {

        }

    }
}
