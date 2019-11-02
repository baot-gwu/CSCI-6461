package main.java.program;

import main.java.common.CiscComputer;
import main.java.instruction.Instruction;
import main.java.instruction.InstructionType;
import main.java.register.GeneralPurposeRegister;
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
    private int registerNumber = 0;
    private Instruction instruction;

    public int readAndStoreParagraphIntoMemory(CiscComputer ciscComputer) {
        GeneralPurposeRegister generalPurposeRegister = ciscComputer.getGeneralPurposeRegister(registerNumber);
        int address = STARTING_ADDRESS_TO_STORE_PARAGRAPH;
        try {
            List<String> lines = Files.readAllLines(path);
            String paragraph = makeParagraph(lines);

            byte[] paragraphBytes = paragraph.getBytes();

            for (byte paragraphByte : paragraphBytes) {
                generalPurposeRegister.setBinaryValue(Utils.decimalToUnsignedBinary(paragraphByte));

                storeValue(ciscComputer, address++, generalPurposeRegister);
            }

            return address;
        } catch (IOException e) {
            return 0;
        }
    }

    private String makeParagraph(List<String> lines) {
        StringBuilder sb = new StringBuilder(80 * TOTAL_SENTENCES);

        lines.forEach(l -> sb.append(l).append(" "));

        return sb.toString();
    }

    private void storeValue(CiscComputer ciscComputer, int address, GeneralPurposeRegister generalPurposeRegister) {
        instruction = new Instruction(generalPurposeRegister, null, InstructionType.STR, address,
                false, false, false, null, null, null);

        instruction.getType().getProcessor().process(ciscComputer, instruction);

        System.out.println(instruction.symbolicForm());
    }

    public int loadValue(CiscComputer ciscComputer, int address) {
        GeneralPurposeRegister generalPurposeRegister = ciscComputer.getGeneralPurposeRegister(registerNumber);

        instruction = new Instruction(generalPurposeRegister, null, InstructionType.LDR, address,
                false, false, false, null, null, null);

        instruction.getType().getProcessor().process(ciscComputer, instruction);

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
