package main.java.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Class to convert assembly instruction to binary
 */
public class Convert {
    public static void assembleToBinary() {
        Path PATH_ASSEMBLE = Paths.get("in.assemble").toAbsolutePath();
        Path PATH_BINARY = Paths.get("out.dump").toAbsolutePath();
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(PATH_ASSEMBLE);
            for (String assemble_code: lines) {
                sb.append(Utils.binaryInstruction(assemble_code)).append("\n");
            }
            Files.write(PATH_BINARY, sb.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
