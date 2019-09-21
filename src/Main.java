public class Main {
    public static void main(String[] args) {

        Initializer initializer = new Initializer();

        // Initialize all classes
        CiscComputer ciscComputer = initializer.initialize();
//        InstructionProcessor instructionProcessor = new InstructionProcessor();
//        InstructionDecoder instructionDecoder = new InstructionDecoder();
//        InstructionRegister instructionRegister = ciscComputer.getInstructionRegister();
//        ProgramCounter programCounter = ciscComputer.getProgramCounter();
        DebugPanel dp = new DebugPanel();
        dp.setData(ciscComputer);

//        instructionRegister.setBinaryInstruction("0000011011100011");
//
//            Instruction instruction = instructionDecoder.decode(ciscComputer);
//
//            System.out.println(Utils.symbolicForm(instruction));


        // On address 30 have instruction: LDX 1, 20, in binary format: 1010010001010100
//        for (int address = 30; address <= 39; address++) {
//            programCounter.setDecimalValue(address + 1);
//
//            instructionRegister.setBinaryInstruction(Memory.memoryMap.get(address));
//
//            Instruction instruction = instructionDecoder.decode(ciscComputer);
//
//            System.out.println(Utils.symbolicForm(instruction));
//
//            instructionProcessor.processInstruction(ciscComputer, instruction);
//
//            printValues(ciscComputer);
//        }
//

        //new Memory().clear();  clear all memory
        //new Memory().loadBackupContent(); // load the memory with backup
        //System.out.println(Utils.symbolicForm(instruction));
        // to Halt just break the loop here.

//        ciscComputer.getMemory().writeContent();
    }

    static void printValues(CiscComputer ciscComputer) {
        System.out.println("In Binary  -> " + new Display(ciscComputer, true).toString());
        System.out.println("In Decimal -> " + new Display(ciscComputer, false).toString());
    }
}
