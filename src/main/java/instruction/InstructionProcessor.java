package main.java.instruction;

import main.java.common.CiscComputer;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * This the interface for all types of instructions.
 *
 * Any instruction processor must implement this class.
 *
 */
public interface InstructionProcessor {

    void process(CiscComputer ciscComputer, Instruction instruction);

}
