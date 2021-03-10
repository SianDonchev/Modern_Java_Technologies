package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.Duration;
import java.time.LocalDateTime;

abstract public class PhysicalSmartDevice implements SmartDevice {
    private String ID;
    private String name;
    private LocalDateTime installationDateTime;
    private DeviceType type;

    public PhysicalSmartDevice(String name, double powerConsumption, LocalDateTime installationDateTime) {
        this.name = name;
        this.installationDateTime = installationDateTime;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPowerConsumption() {
        return Duration.between(LocalDateTime.now(), installationDateTime).toHours();
    }

    @Override
    public LocalDateTime getInstallationDateTime() {
        return installationDateTime;
    }

    @Override
    public DeviceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PhysicalSmartDevice)) {
            return false;
        }
        PhysicalSmartDevice physicalSmartDevice = (PhysicalSmartDevice) obj;

        return this.ID.equals(physicalSmartDevice.ID);
    }

    @Override
    public int hashCode() {
        return this.ID.hashCode();
    }
}
