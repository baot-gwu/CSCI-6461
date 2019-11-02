package main.java.program;

import main.java.common.CiscComputer;
import main.java.device.ConsoleKeyboard;
import main.java.device.Device;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionDecoder;
import main.java.instruction.InstructionType;
import main.java.register.GeneralPurposeRegister;
import main.java.register.IndexRegister;
import main.java.util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Program2 {

    private final Path path = Paths.get("Paragraph.txt").toAbsolutePath();
    private static final int STARTING_ADDRESS_TO_STORE_PARAGRAPH = 600;
    private int TOTAL_SENTENCES = 6;
    private Instruction instruction;

    public int readAndStoreParagraphIntoMemory(CiscComputer ciscComputer) {
        IndexRegister indexRegister = ciscComputer.getIndexRegister(1);
        Device consoleKeyboard = new ConsoleKeyboard();
        ciscComputer.setDevice(consoleKeyboard);

        try {
            List<String> lines = Files.readAllLines(path);
            String paragraph = makeParagraph(lines);

            byte[] paragraphBytes = paragraph.getBytes();

            for (byte paragraphByte : paragraphBytes) {
                processInstruction(ciscComputer, "1010010001001111");

                consoleKeyboard.setBinaryValue(Utils.decimalToUnsignedBinary(paragraphByte));

                processInstruction(ciscComputer, "1111010000000000");
                processInstruction(ciscComputer, "0000100001000000");

                // incrementIndexRegisterValue
                processInstruction(ciscComputer, "0000010100001111");
                processInstruction(ciscComputer, "0001100100000001");
                processInstruction(ciscComputer, "0000100100001111");
            }

            return indexRegister.getDecimalValue();
        } catch (IOException e) {
            return 0;
        }
    }

    private void processInstruction(CiscComputer ciscComputer, String binaryInstruction) {
        ciscComputer.getInstructionRegister().setBinaryInstruction(binaryInstruction);
        instruction = new InstructionDecoder().decode(ciscComputer);
        instruction.getType().getProcessor().process(ciscComputer, instruction);
    }

    private String makeParagraph(List<String> lines) {
        StringBuilder sb = new StringBuilder(80 * TOTAL_SENTENCES);

        lines.forEach(l -> sb.append(l).append(" "));

        return sb.toString();
    }

    public int loadValue(CiscComputer ciscComputer, int address) {
        GeneralPurposeRegister generalPurposeRegister = ciscComputer.getGeneralPurposeRegister(0);
        IndexRegister indexRegister = null;

        instruction = new Instruction(generalPurposeRegister, indexRegister, InstructionType.LDR, address,
                false, false, false, null, null, null);

        instruction.getType().getProcessor().process(ciscComputer, instruction);

        System.out.println(instruction.symbolicForm());

        return generalPurposeRegister.getDecimalValue();
    }

    public String matchWord(CiscComputer ciscComputer, int endAddress, String word) {
        String paragraph = loadParagraph(ciscComputer, endAddress);

        String[] sentences = paragraph.split("\\. ");

        for (int i = 0; i < TOTAL_SENTENCES; i++) {
            String[] words = sentences[i].split("[^a-zA-Z0-9]+");
            for (int j = 0; j < words.length; j++) {
                if (word.equalsIgnoreCase(words[j])) {
                    return "Given Word: " + word + ", Line Number: " + (i + 1) + ", Word Number: " + (j + 1);
                }
            }
        }

        return "";
    }

    public String loadParagraph(CiscComputer ciscComputer, int endAddress) {
        byte[] bytes = new byte[endAddress - STARTING_ADDRESS_TO_STORE_PARAGRAPH + 1];

        for (int i = 0; i < endAddress - STARTING_ADDRESS_TO_STORE_PARAGRAPH; i++) {
            bytes[i] = (byte) (loadValue(ciscComputer, STARTING_ADDRESS_TO_STORE_PARAGRAPH + i));
        }

        return new String(bytes);
    }
}
