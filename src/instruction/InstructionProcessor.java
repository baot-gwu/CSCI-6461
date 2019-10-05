package instruction;

import common.CiscComputer;
import memory.Address;
import memory.Cache;
import register.GeneralPurposeRegister;
import register.IndexRegister;

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
