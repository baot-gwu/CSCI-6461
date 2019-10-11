package main.java;

import main.java.common.CiscComputer;
import main.java.common.Display;
import main.java.common.Initializer;
import main.java.panel.DebugPanel;
import main.java.panel.OperationPanel;
import mdlaf.MaterialLookAndFeel;

import javax.swing.*;

public class Main {
    public static final int AUTORUN_DELAY = 1000; // in milliseconds (ms)
    public static final int MAX_MEMORY_SIZE = 2048; // Memory size 2048 expand to 4096
    public static boolean busy = true; // Status of machine
    public static DebugPanel dp;
    public static OperationPanel op;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel (new MaterialLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        }

        Initializer initializer = new Initializer();

        // Initialize all classes
        CiscComputer ciscComputer = initializer.initialize();
        dp = new DebugPanel();
        op = new OperationPanel();
        dp.setData(ciscComputer);
        op.setData(ciscComputer);
        busy = false;

//        int memoryAddressStartPoint = 2000;
//        Program1 program1 = new Program1();
//        int[] inputNumbers = {4587, 15541, 1554, 9455, 1551,
//                5858, 8951, 3211, 4558, 2554, 5454, 7893, 4845, 3684, 1234, 1225, 15441, 15548, 1364, 6987};
//        program1.inputAndStoreNumber(ciscComputer, inputNumbers, memoryAddressStartPoint);
//        System.out.println(program1.findClosestNumber(ciscComputer, 2258, inputNumbers.length, memoryAddressStartPoint));
    }

    public static void printValues(CiscComputer ciscComputer) {
        // print back-end information to console
        System.out.println("In Binary  -> " + new Display(ciscComputer, true).toString());
        System.out.println("In Decimal -> " + new Display(ciscComputer, false).toString());
    }
}
