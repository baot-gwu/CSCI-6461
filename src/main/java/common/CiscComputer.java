package main.java.common;

import main.java.device.Device;
import main.java.instruction.Instruction;
import main.java.memory.Memory;
import main.java.memory.Word;
import main.java.register.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author jalal
 * @since 12/9/19
 *
 *
 * Class is to represent CISC computer with all necessary attributes
 *
 * 4 General purpose register
 * 3 Index register
 * 2 Floating Point register
 * 1 Instruction register
 * 1 Program Counter
 * and Memory with length of 2048 words
 */
public class CiscComputer {

    public static final int TOTAL_PIPE_LINE = 4;

    private List<GeneralPurposeRegister> generalPurposeRegisters = new ArrayList<>(4);
    private List<FloatingPointRegister> floatingPointRegisters = new ArrayList<>(2);
    private List<IndexRegister> indexRegisters = new ArrayList<>(3);
    private MemoryAddressRegister memoryAddressRegister;
    private MemoryBufferRegister memoryBufferRegister;
    private InstructionRegister instructionRegister;
    private ProgramCounter programCounter;
    private ConditionCode conditionCode;
    private MachineFaultRegister machineFaultRegister;
    private Memory memory;
    private List<Device> devices = new ArrayList<>(Device.MAX_DEVICES);
    private int clockCycle;

    public Queue<Word> fetch = new LinkedList<>();
    public Queue<Instruction> decode = new LinkedList<>();
    public Queue<Instruction> execute = new LinkedList<>();
    public Queue<Instruction> write = new LinkedList<>();

    public List<GeneralPurposeRegister> getGeneralPurposeRegisters() {
        return generalPurposeRegisters;
    }

    public void setGeneralPurposeRegisters(List<GeneralPurposeRegister> generalPurposeRegisters) {
        this.generalPurposeRegisters = generalPurposeRegisters;
    }

    public List<IndexRegister> getIndexRegisters() {
        return indexRegisters;
    }

    public void setIndexRegisters(List<IndexRegister> indexRegisters) {
        this.indexRegisters = indexRegisters;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<FloatingPointRegister> getFloatingPointRegisters() {
        return floatingPointRegisters;
    }

    public void setFloatingPointRegisters(List<FloatingPointRegister> floatingPointRegisters) {
        this.floatingPointRegisters = floatingPointRegisters;
    }

    public MemoryAddressRegister getMemoryAddressRegister() {
        return memoryAddressRegister;
    }

    public void setMemoryAddressRegister(MemoryAddressRegister memoryAddressRegister) {
        this.memoryAddressRegister = memoryAddressRegister;
    }

    public MemoryBufferRegister getMemoryBufferRegister() {
        return memoryBufferRegister;
    }

    public void setMemoryBufferRegister(MemoryBufferRegister memoryBufferRegister) {
        this.memoryBufferRegister = memoryBufferRegister;
    }

    public InstructionRegister getInstructionRegister() {
        return instructionRegister;
    }

    public void setInstructionRegister(InstructionRegister instructionRegister) {
        this.instructionRegister = instructionRegister;
    }

    public ProgramCounter getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(ProgramCounter programCounter) {
        this.programCounter = programCounter;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public ConditionCode getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(ConditionCode conditionCode) {
        this.conditionCode = conditionCode;
    }

    public MachineFaultRegister getMachineFaultRegister() {
        return machineFaultRegister;
    }

    public void setMachineFaultRegister(MachineFaultRegister machineFaultRegister) {
        this.machineFaultRegister = machineFaultRegister;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void setDevice(Device device) {
        devices.set(device.getDeviceType().getDeviceId(), device);
    }

    public Device getDevice(int deviceId) {
        return devices.get(deviceId);
    }

    public int getClockCycle() {
        return clockCycle;
    }

    public void setClockCycle(int clockCycle) {
        this.clockCycle = clockCycle;
    }

    public void incrementClockCycle() {
        setClockCycle(getClockCycle() + 1);
    }

    public GeneralPurposeRegister getGeneralPurposeRegister(int registerNumber) {
        for (GeneralPurposeRegister register : generalPurposeRegisters) {
            if (register.getRegisterNumber() == registerNumber) {
                return register;
            }
        }

        throw new IllegalArgumentException("Invalid General Purpose Register Number" + registerNumber);
    }

    public FloatingPointRegister getFloatingPointRegister(int registerNumber) {
        for (FloatingPointRegister register : floatingPointRegisters) {
            if (register.getRegisterNumber() == registerNumber) {
                return register;
            }
        }

        throw new IllegalArgumentException("Invalid Floating Point Register Number" + registerNumber);
    }

    public IndexRegister getIndexRegister(int registerNumber) {
        for (IndexRegister register : indexRegisters) {
            if (register.getRegisterNumber() == registerNumber) {
                return register;
            }
        }

        throw new IllegalArgumentException("Invalid Index Register Number" + registerNumber);
    }
}
