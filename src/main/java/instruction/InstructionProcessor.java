package main.java.instruction;

import main.java.common.CiscComputer;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * This class process and instruction.
 *
 */
public interface InstructionProcessor {

    void process(CiscComputer ciscComputer, Instruction instruction);

}
