package main.java.device;

public enum DeviceType {

    CONSOLE_KEYBOARD(0),
    CONSOLE_PRINTER(1),
    CARD_READER(2);

    private int deviceId;

    DeviceType(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public static DeviceType getDevice(int deviceId) {
        for (DeviceType deviceType : DeviceType.values()) {
            if (deviceType.getDeviceId() == deviceId) {
                return deviceType;
            }
        }

        return null;
    }
}
