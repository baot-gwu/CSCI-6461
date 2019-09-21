/**
 * @author jalal
 * @since 12/9/19
 *
 * Class to represent index register
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
