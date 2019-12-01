package main.java.register;

/**
 * Single machine fault register,
 *
 * handles machine faults to execute routine work
 *
 */
public class MachineFaultRegister extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.MACHINE_FAULT_REGISTER;
    }

}
