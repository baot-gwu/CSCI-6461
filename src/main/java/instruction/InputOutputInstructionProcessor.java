package main.java.instruction;

import main.java.common.CiscComputer;
import main.java.device.Device;
import main.java.register.Register;

/**
 * @author jalal
 * @since 10/9/19
 * <p>
 * Input Output Instruction Processor. This one executes IN, OUT and CHK instruction
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
        Register register = instruction.getFirstRegister();
        Device device = ciscComputer.getDevice(instruction.getDeviceId());

        switch (instruction.getType()) {
            case IN:
                inputCharacterToRegisterFromDevice(register, device);
                break;
            case OUT:
                outputCharacterToDeviceFromRegister(register, device);
                break;
            case CHK:
                checkDeviceStatusToRegister(register, device);
                break;
        }

    }

    private void checkDeviceStatusToRegister(Register register, Device device) {
        boolean isReady = device.getBinaryValue() != null && device.getBinaryValue().length() > 0;

        register.setDecimalValue(isReady ? 1 : 0);
    }

    private void outputCharacterToDeviceFromRegister(Register register, Device device) {
        device.setBinaryValue(register.getValue(true));
        device.run();
    }

    private void inputCharacterToRegisterFromDevice(Register register, Device device) {
        device.run();
        register.setBinaryValue(device.getBinaryValue());
    }
}
