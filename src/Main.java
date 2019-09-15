public class Main {
    public static void main (String[] args){
        //new DebugPanel();

        Initializer initializer = new Initializer();

        // Initialize all classes
        CiscComputer ciscComputer = initializer.initialize();
        InstructionProcessor instructionProcessor = new InstructionProcessor();
        InstructionRegister instructionRegister = ciscComputer.getInstructionRegister();
        ProgramCounter programCounter = ciscComputer.getProgramCounter();

        // On address 30 have instruction: LDX 1, 20, in binary format: 1010010001010100
        for (int address = 30; address <= 39; address++) {
            programCounter.setDecimalValue(address + 1);

            instructionRegister.setBinaryInstruction(Memory.memoryMap.get(address));

            instructionProcessor.processInstruction(ciscComputer);

            printValues(ciscComputer);
        }

        // Save all changes before quit
        ciscComputer.getMemory().writeContent();
    }

    private static void printValues(CiscComputer ciscComputer) {
        System.out.println("In Binary  -> " + new Display(ciscComputer, true).toString());
        System.out.println("In Decimal -> " + new Display(ciscComputer, false).toString());
    }
}
