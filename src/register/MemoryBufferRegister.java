package register;

/**
 * @author jalal
 * @since 14/9/19
 *
 * Class to represent memory buffer register
 */
public class MemoryBufferRegister extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.MEMORY_BUFFER_REGISTER;
    }
}
