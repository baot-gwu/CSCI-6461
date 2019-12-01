package main.java.register;

/**
 * @author jalal
 * @since 14/9/19
 *
 * Class to represent memory buffer register.
 *
 * Contains actual value from memory addressed by memory address register
 */
public class MemoryBufferRegister extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.MEMORY_BUFFER_REGISTER;
    }
}
