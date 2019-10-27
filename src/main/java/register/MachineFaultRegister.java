package main.java.register;

public class MachineFaultRegister extends Register {

    @Override
    RegisterType getType() {
        return RegisterType.MACHINE_FAULT_REGISTER;
    }

}
