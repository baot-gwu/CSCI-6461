/*
* panel/Controller.java
* This class is to control either Debug Panel and Operation Panel.
 */

package main.java.panel;

import main.java.Main;
import main.java.common.CiscComputer;


/**
 * Main controller for UI Panel.
 *
 * All the UI operation reset, clear, stop, run, save, load, theme
 * controller from here.
 */
public class Controller {
    // reset the machine
    public static void reset() {
        Main.op.reset();
        Main.dp.restart();
    }

    // clean the screen
    public static void clear() {
        Main.op.clear();
        Main.dp.clear();
    }

    // stop the machine
    public static void stop() {
        Main.dp.stop();
        Main.op.stopThread();
    }

    // pause the machine
    public static void pause() {
        Main.dp.pause();
    }

    // run the machine one step
    public static void singleRun() {
        Main.dp.singleRun();
    }

    // run the machine until HALT
    public static void autoRun() {
        Main.dp.autoRun();
    }

    // insert card
    public static void ipl() {
        Main.dp.ipl();
    }

    // save the memory dump
    public static void save() {
        Main.dp.save();
    }

    // reload the machine
    public static void reload() {
        Main.dp.reload();
    }

    // update the cisc data to front end
    public static void update(CiscComputer ciscComputer) {
        Main.dp.setData(ciscComputer);
    }

    // switch theme of the UI
    public static void switchTheme(String themeName) {
        Main.setTheme(themeName);
        Main.repaintUI();
        Main.op.pushToScreen("Theme switch to " + themeName, false);
        Main.op.pushNewLine();
    }

    // push message to operation panel
    public static void pushToScreen(String content, boolean isIndependentContent) {
        Main.op.pushToScreen(content, isIndependentContent);
    }

    // enable keyboard input model
    public static void pullFromKeyboard() {
        Main.op.getFromKeyboard();
    }

    // enable card input model
    public static void pullFromCard() {
        Main.op.pushToScreen("Please insert the card...\n", false);
        Main.dp.readFromFile();
    }
}
