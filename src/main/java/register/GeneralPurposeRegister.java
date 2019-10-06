package main.java.register;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Class to represent general purpose register
 */
public class GeneralPurposeRegister extends Register {

    public GeneralPurposeRegister(int registerNumber) {
        super.registerNumber = registerNumber;
    }

    @Override
    public RegisterType getType() {
        return RegisterType.GENERAL_PURPOSE;
    }

}
