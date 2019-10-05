package register;

import util.Utils;

/**
 * @author jalal
 * @since 14/9/19
 *
 * Class to represent instruction register
 */
public class InstructionRegister extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.INSTRUCTION_REGISTER;
    }

    public void setBinaryInstruction(String instruction) {
        if (Utils.isValidBinaryInstruction(instruction)) {
            setBinaryValue(instruction);
        }
    }

    public void setOctalInstruction(String instruction, int registerSize) {
        if (Utils.isValidOctalInstruction(instruction)) {
            setBinaryValue(Utils.octalToBinary(instruction, registerSize));
        }
    }

    public String getBinaryInstruction() {
        return getBinaryValue();
    }
}
