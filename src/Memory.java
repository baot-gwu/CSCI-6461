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

    public static Map<Integer, Word> memoryMap = new LinkedHashMap<>(MAX_MEMORY_SIZE);

    Path path = Paths.get(FILE_NAME).toAbsolutePath();

    protected void clear() {
        for (int i = 1; i <= MAX_MEMORY_SIZE; i++) {
            memoryMap.put(i, new Word(""));
        }

        writeContent();
    }

    protected void loadBackupContent() {
        try {
            List<String> list = Files.readAllLines(Paths.get(BACK_UP_FILE_NAME).toAbsolutePath());

            for (int i = 1; i <= MAX_MEMORY_SIZE; i++) {
                memoryMap.put(i, new Word(list.get(i - 1)));
            }
        } catch (IOException e) {
            e.printStackTrace();

            System.err.println("Cannot read file");
        }

        writeContent();
    }

    protected void loadContent() {
        try {
            List<String> list = Files.readAllLines(path);

            for (int i = 1; i <= MAX_MEMORY_SIZE; i++) {
                memoryMap.put(i, new Word(list.get(i - 1)));
            }
        } catch (IOException e) {
            e.printStackTrace();

            System.err.println("Cannot read file");
        }
    }

    protected void writeContent() {
        try {
            Files.write(path, getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();

            System.err.println("Cannot write file");
        }
    }

    private byte[] getBytes() {
        StringBuilder sb = new StringBuilder((Word.MAX_SIZE + 1) * MAX_MEMORY_SIZE);

        memoryMap.values().forEach(v -> sb.append(v.getValue()).append("\n"));

        return sb.toString().getBytes();
    }

}
