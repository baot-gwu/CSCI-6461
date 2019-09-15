/**
 * @author jalal
 * @since 14/9/19
 */
public class InstructionRegister extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.INSTRUCTION_REGISTER;
    }

    public void setBinaryInstruction(String instruction) {
        setBinaryValue(instruction);
    }

    public void setOctalInstruction(String instruction, int registerSize) {
        setBinaryValue(Utils.octalToBinary(instruction, registerSize));
    }

    public String getBinaryInstruction() {
        return getBinaryValue();
    }
}
