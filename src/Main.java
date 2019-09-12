public class Main {
    public static void main (String[] args){
        //new DebugPanel();

        Initializer initializer = new Initializer();

        // Values of IX registers
        CiscComputer ciscComputer = initializer.initialize();

        InstructionProcessor instructionProcessor = new InstructionProcessor();

        initializer.initializeIndexRegister(ciscComputer.getIndexRegisters(), new String[]{"80", "95", "100"});

        instructionProcessor.parseAndProcessInstruction(ciscComputer,"0000011100001011"); // LDR 3, 0, 11
        instructionProcessor.parseAndProcessInstruction(ciscComputer,"0000010000101011"); // LDR 0, 0, 11, 1
        instructionProcessor.parseAndProcessInstruction(ciscComputer,"0000010111000011"); // LDR 1, 3, 3
        instructionProcessor.parseAndProcessInstruction(ciscComputer,"0000011011100011"); // LDR 2, 3, 3, 1

        instructionProcessor.parseAndProcessInstruction(ciscComputer,"0000101000000001"); // STR 2, 0, 1
        ciscComputer.getMemory().writeContent();

        Display display = new Display(ciscComputer);

        System.out.println(display.toString());
    }
}
