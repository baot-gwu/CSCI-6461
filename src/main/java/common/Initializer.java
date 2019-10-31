package main.java.common;

import main.java.device.CardReader;
import main.java.device.ConsoleKeyboard;
import main.java.device.ConsolePrinter;
import main.java.device.Device;
import main.java.memory.Memory;
import main.java.register.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jalal
 * @since 12/9/19
 *
 * This is the class to initialize all the class associated with {@link CiscComputer}
 */
public class Initializer {

    public CiscComputer initialize() {

        CiscComputer ciscComputer = new CiscComputer();

        ciscComputer.setMemory(createMemory());

        ciscComputer.setIndexRegisters(createIndexRegisters());

        ciscComputer.setGeneralPurposeRegisters(createGeneralPurposeRegisters());

        ciscComputer.setFloatingPointRegisters(createFloatingPointRegisters());

        ciscComputer.setInstructionRegister(new InstructionRegister());

        ciscComputer.setMemoryAddressRegister(new MemoryAddressRegister());

        ciscComputer.setMemoryBufferRegister(new MemoryBufferRegister());

        ciscComputer.setProgramCounter(new ProgramCounter());

        ciscComputer.setConditionCode(new ConditionCode());

        ciscComputer.setDevices(createDevices());

        ciscComputer.setMachineFaultRegister(new MachineFaultRegister());

        return ciscComputer;
    }

    private List<Device> createDevices() {
        List<Device> devices = new ArrayList<>(Device.MAX_DEVICES);

        devices.add(new ConsoleKeyboard());
        devices.add(new ConsolePrinter());
        devices.add(new CardReader());

        return devices;
    }

    private List<GeneralPurposeRegister> createGeneralPurposeRegisters() {
        return Arrays.asList(new GeneralPurposeRegister(0),
                new GeneralPurposeRegister(1),
                new GeneralPurposeRegister(2),
                new GeneralPurposeRegister(3));
    }

    private List<FloatingPointRegister> createFloatingPointRegisters() {
        return Arrays.asList(new FloatingPointRegister(0),
                new FloatingPointRegister(1));
    }

    private List<IndexRegister> createIndexRegisters() {
        return Arrays.asList(new IndexRegister(1),
                new IndexRegister(2),
                new IndexRegister(3));
    }

    private Memory createMemory() {
        Memory memory = new Memory();

        memory.loadContent();

        return memory;
    }
}
