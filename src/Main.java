public class Main {
    public static void main (String[] args){
        //new DebugPanel();

        Initializer initializer = new Initializer();

        // Values of IX registers
        CiscComputer ciscComputer = initializer.initialize();

        InstructionProcessor instructionProcessor = new InstructionProcessor();

        InstructionRegister instructionRegister = ciscComputer.getInstructionRegister();

        instructionRegister.setBinaryInstruction("1010010001010100");  // LDX 1, 20
        // instructionRegister.setOctalInstruction("122124"); //You can also set this as octal instruction
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("1010010010010110"); // LDX 2, 22
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("1010010011111000"); // LDX 3, 24, 1
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("0000011100001011"); // LDR 3, 0, 11
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("0000010000101011"); // LDR 0, 0, 11, 1
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("0000010111000011"); // LDR 1, 3, 3
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("0000011011100011"); // LDR 2, 3, 3, 1
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("0000101000000001"); // STR 2, 0, 1
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("1010100011010000"); // STX 3, 0, 16
        instructionProcessor.processInstruction(ciscComputer);

        instructionRegister.setBinaryInstruction("0000110100000100"); // LDA 1, 0, 4
        instructionProcessor.processInstruction(ciscComputer);

        // Save all changes before quit
        ciscComputer.getMemory().writeContent();
    }
}
