/**
 * @author jalal
 * @since 12/9/19
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
