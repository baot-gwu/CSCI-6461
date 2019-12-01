package main.java.device;

import main.java.Main;
import main.java.panel.Controller;

import java.util.concurrent.CountDownLatch;

/**
 * @author jalal
 * @since 10/9/19
 * <p>
 * Console Keyboard device for input
 * <p>
 */
public class ConsoleKeyboard extends Device {

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CONSOLE_KEYBOARD;
    }

    @Override
    public void run(){
        try {
            Main.IO = new CountDownLatch(1);
            Controller.pullFromKeyboard();
            Main.IO.await();
        } catch (InterruptedException e) {

        }
        setBinaryValue(Main.dp.getMemoryAt(7));
    }
}
