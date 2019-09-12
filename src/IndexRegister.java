/**
 * @author jalal
 * @since 12/9/19
 */
public class IndexRegister extends AbstractRegister {

    public IndexRegister(int registerNumber, String value) {
        super.registerNumber = registerNumber;

        setValue(value);
    }

    @Override
    public RegisterType getType() {
        return RegisterType.INDEX;
    }

}
