/**
 * @author jalal
 * @since 14/9/19
 *
 * Class to represent program counter
 */
public class ProgramCounter extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.PROGRAM_COUNTER;
    }
}
