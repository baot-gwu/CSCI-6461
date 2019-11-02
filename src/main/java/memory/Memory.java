package main.java.memory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jalal
 * @since 12/9/19
 * <p>
 * This is the class to represent memory.
 * <p>
 * Its a LinkedHashMap with contains 2048 entries, Each entry present a word with line number.
 * <p>
 * Key is line number and value is word
 */
public class Memory {

    public static final int MAX_MEMORY_SIZE = 2048;
    private static final String FILE_NAME = "memory.txt";
    private static final String BACK_UP_FILE_NAME = "memory-backup.txt";

    public static final int RESERVE_ADDRESS_FOR_TRAP_INSTRUCTION = 0;
    public static final int RESERVE_ADDRESS_FOR_MACHINE_FAULT = 1;
    public static final int RESERVE_ADDRESS_TO_STORE_PC_FOR_TRAP = 2;
    public static final int RESERVE_ADDRESS_TO_STORE_PC_FOR_MACHINE_FAULT = 4;

    public static Map<Integer, Word> memoryMap = new LinkedHashMap<>(MAX_MEMORY_SIZE);

    Path path = Paths.get(FILE_NAME).toAbsolutePath();

    public void clear() {
        for (int i = 0; i < MAX_MEMORY_SIZE; i++) {
            memoryMap.put(i, new Word(""));
        }

        writeContent(null);
    }

    public void loadBackupContent() {
        try {
            List<String> list = Files.readAllLines(Paths.get(BACK_UP_FILE_NAME).toAbsolutePath());

            for (int i = 0; i < MAX_MEMORY_SIZE; i++) {
                memoryMap.put(i, new Word(list.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();

            System.err.println("Cannot read file");
        }

        writeContent(null);
    }

    public void loadContent(Path filepath) {
        if (filepath == null)
            filepath = path;
        try {
            List<String> list = Files.readAllLines(filepath);

            for (int i = 0; i < Math.min(MAX_MEMORY_SIZE, list.size()); i++) {
                memoryMap.put(i, new Word(list.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();

            System.err.println("Cannot read file");
        }
    }

    public void writeContent(Path filepath) {
        if (filepath == null)
            filepath = path;
        try {
            Files.write(filepath, getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();

            System.err.println("Cannot write file");
        }
    }

    private byte[] getBytes() {
        StringBuilder sb = new StringBuilder(Word.MAX_SIZE * MAX_MEMORY_SIZE);

        memoryMap.values().forEach(v -> sb.append(v.getValue()).append("\n"));

        return sb.toString().getBytes();
    }

}
