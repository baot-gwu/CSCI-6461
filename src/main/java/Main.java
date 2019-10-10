package main.java;

import main.java.common.CiscComputer;
import main.java.common.Display;
import main.java.common.Initializer;

public class Main {
    public static final int AUTORUN_DELAY = 1000; // in milliseconds (ms)
    public static final int MAX_MEMORY_SIZE = 2048; // Memory size 2048 expand to 4096

    public static void main(String[] args) {
        Initializer initializer = new Initializer();

        // Initialize all classes
        CiscComputer ciscComputer = initializer.initialize();
        DebugPanel dp = new DebugPanel();
        OperationPanel op = new OperationPanel();
        dp.setData(ciscComputer);
        op.setData(ciscComputer);
    }

    static void printValues(CiscComputer ciscComputer) {
        // print back-end information to console
        System.out.println("In Binary  -> " + new Display(ciscComputer, true).toString());
        System.out.println("In Decimal -> " + new Display(ciscComputer, false).toString());
    }
}
