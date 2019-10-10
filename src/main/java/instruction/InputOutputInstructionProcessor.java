package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.device.Device;
import main.java.memory.Address;
import main.java.register.Register;

/**
 * @author jalal
 * @since 10/9/19
 * <p>
 * Input Output Instruction Processor
 * <p>
 */
public class InputOutputInstructionProcessor implements InstructionProcessor {

    private static InstructionProcessor processor;

    static InstructionProcessor getInstance() {
        if (processor == null) {
            processor = new InputOutputInstructionProcessor();
        }

        return processor;
    }

    @Override
    public void process(CiscComputer ciscComputer, Instruction instruction) {
        Address address = AddressDecoder.decodeAddress(instruction);

        Register register = instruction.getFirstRegister();
        Device device = ciscComputer.getDevice(instruction.getDeviceId());

        switch (instruction.getType()) {
            case IN:
                inputCharacterToRegisterFromDevice(register, device);
                break;
            case OUT:
                outputCharacterToDeviceFromRegister(register, device);
                break;
        }

    }

    private void outputCharacterToDeviceFromRegister(Register register, Device device) {
        device.setBinaryValue(register.getValue(true));
    }

    private void inputCharacterToRegisterFromDevice(Register register, Device device) {
        register.setBinaryValue(device.getBinaryValue());
    }
}
