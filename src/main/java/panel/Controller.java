package main.java.panel;

import main.java.Main;
import main.java.common.CiscComputer;

public class Controller {
    public static void reset() {
        Main.op.reset();
        Main.dp.restart();
    }

    public static void clear() {
        Main.op.clear();
        Main.dp.clear();
    }

    public static void stop() {
        Main.dp.stop();
    }

    public static void pause() {
        Main.dp.pause();
    }

    public static void singleRun() {
        Main.dp.singleRun();
    }

    public static void autoRun() {
        Main.dp.autoRun();
    }

    public static void ipl() {
        Main.dp.ipl();
    }

    public static void save() {
        Main.dp.save();
    }

    public static void reload() {
        Main.dp.reload();
    }

    public static void update(CiscComputer ciscComputer) {
        Main.dp.setData(ciscComputer);
    }
}
