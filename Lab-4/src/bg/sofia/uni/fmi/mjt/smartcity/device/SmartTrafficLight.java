package bg.sofia.uni.fmi.mjt.smartcity.device;

import java.time.LocalDateTime;

public class SmartTrafficLight extends PhysicalSmartDevice {
    public SmartTrafficLight(String name, double powerConsumption, LocalDateTime installationDateTime) {
        super(name, powerConsumption, installationDateTime);
    }
}
