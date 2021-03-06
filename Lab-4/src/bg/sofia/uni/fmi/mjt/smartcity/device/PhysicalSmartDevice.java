package bg.sofia.uni.fmi.mjt.smartcity.device;

import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.time.LocalDateTime;

abstract public class PhysicalSmartDevice implements SmartDevice {
    private String ID;
    private String name;
    private double powerConsumption;
    private LocalDateTime installationDateTime;
    private DeviceType type;

    /**
     * Returns the ID of the device.
     */
    @Override
    public String getId() {
        return ID;
    }

    /**
     * Returns the name of the device.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the power consumption of the device.
     * For example, a lamp may consume 1kW/hour.
     */
    @Override
    public double getPowerConsumption() {
        return powerConsumption;
    }

    /**
     * Returns the date and time of device installation.
     * This is a time in the past when the device was 'physically' installed.
     * It is not related to the time when the device is registered in the Hub.
     */
    @Override
    public LocalDateTime getInstallationDateTime() {
        return installationDateTime;
    }

    /**
     * Returns the type of the device.
     */
    @Override
    public DeviceType getType() {
        return type;
    }
}
