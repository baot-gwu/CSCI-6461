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
        setValue(instruction);
    }

    public void setOctalInstruction(String instruction) {
        setValue(Utils.octalToBinary(instruction));
    }

    public String getBinaryInstruction() {
        return getValue();
    }
}
