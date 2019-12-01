package main.java.register;

/**
 * @author jalal
 * @since 14/9/19
 *
 * Class to represent program counter.
 *
 * For pipeline and continuous operations value of this register increments.
 */
public class ProgramCounter extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.PROGRAM_COUNTER;
    }
}
