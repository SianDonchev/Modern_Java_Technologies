package bg.sofia.uni.fmi.mjt.smartcity.device;

import java.time.LocalDateTime;

public class SmartCamera extends PhysicalSmartDevice {
    public SmartCamera(String name, double powerConsumption, LocalDateTime installationDateTime) {
        super(name, powerConsumption, installationDateTime);
    }
}
