/**
 * @author jalal
 * @since 12/9/19
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
