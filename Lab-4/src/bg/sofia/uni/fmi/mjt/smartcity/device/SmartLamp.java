package bg.sofia.uni.fmi.mjt.smartcity.device;

import java.time.LocalDateTime;

public class SmartLamp extends PhysicalSmartDevice {
    public SmartLamp(String name, double powerConsumption, LocalDateTime installationDateTime) {
        super(name, powerConsumption, installationDateTime);
    }
}
