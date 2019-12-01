package main.java.register;

/**
 * @author jalal
 * @since 12/9/19
 *
 * Class to represent index register.
 *
 * There are 3 index register in the machine
 */
public class IndexRegister extends Register {

    public IndexRegister(int registerNumber) {
        super.registerNumber = registerNumber;
    }

    @Override
    public RegisterType getType() {
        return RegisterType.INDEX;
    }

}
