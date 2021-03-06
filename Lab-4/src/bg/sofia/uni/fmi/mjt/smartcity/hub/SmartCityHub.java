package bg.sofia.uni.fmi.mjt.smartcity.hub;

import bg.sofia.uni.fmi.mjt.smartcity.device.SmartDevice;
import bg.sofia.uni.fmi.mjt.smartcity.device.SmartDeviceComparator;
import bg.sofia.uni.fmi.mjt.smartcity.enums.DeviceType;

import java.util.*;

public class SmartCityHub {
    private LinkedHashMap<String, SmartDevice> smartDevices;

    /**
     * Adds a @device to the SmartCityHub.
     *
     * @throws IllegalArgumentException         in case @device is null.
     * @throws DeviceAlreadyRegisteredException in case the @device is already registered.
     */
    public void register(SmartDevice device) throws DeviceAlreadyRegisteredException {
        if(device == null) {
            throw new IllegalArgumentException();
        }
        if (smartDevices.containsKey(device.getId())) {
            throw new DeviceAlreadyRegisteredException("Device already registered");
        }
        smartDevices.put(device.getId(), device);
    }

    /**
     * Removes the @device from the SmartCityHub.
     *
     * @throws IllegalArgumentException in case null is passed.
     * @throws DeviceNotFoundException  in case the @device is not found.
     */
    public void unregister(SmartDevice device) throws DeviceNotFoundException {
        if(device == null) {
            throw new IllegalArgumentException();
        }
        if (!smartDevices.containsKey(device.getId())) {
            throw new DeviceNotFoundException("Device not found");
        }
        smartDevices.remove(device.getId());
    }

    /**
     * Returns a SmartDevice with an ID @id.
     *
     * @throws IllegalArgumentException in case @id is null.
     * @throws DeviceNotFoundException  in case device with ID @id is not found.
     */
    public SmartDevice getDeviceById(String id) throws DeviceNotFoundException {
        if(id == null) {
            throw new IllegalArgumentException();
        }
        if (!smartDevices.containsKey(id)) {
            throw new DeviceNotFoundException("Device not found");
        }
        return smartDevices.remove(id);
    }

    /**
     * Returns the total number of devices with type @type registered in SmartCityHub.
     *
     * @throws IllegalArgumentException in case @type is null.
     */
    public int getDeviceQuantityPerType(DeviceType type) {
        if(type == null) {
            throw new IllegalArgumentException();
        }
        int numberOfDevicesOfType = 0;
        for (Map.Entry<String, SmartDevice> smartDeviceEntry : smartDevices.entrySet()) {
         if(smartDeviceEntry.getValue().getType().equals(type)){
             ++numberOfDevicesOfType;
         }
        }
        return numberOfDevicesOfType;
    }

    /**
     * Returns a collection of IDs of the top @n devices which consumed
     * the most power from the time of their installation until now.
     * <p>
     * The total power consumption of a device is calculated by the hours elapsed
     * between the two LocalDateTime-s: the installation time and the current time (now)
     * multiplied by the stated nominal hourly power consumption of the device.
     * <p>
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<String> getTopNDevicesByPowerConsumption(int n) {
        if(n < 0){
            throw new IllegalArgumentException();
        }
        Comparator smartDeviceComparator = new SmartDeviceComparator();
        Collection<SmartDevice> topNDevices = new TreeSet<>(smartDeviceComparator);

        for (Map.Entry<String, SmartDevice> smartDeviceEntry : smartDevices.entrySet()) {
            topNDevices.add(smartDeviceEntry.getValue());
        }
        return convertToStringCollection(topNDevices);

    }

    /**
     * Returns a collection of the first @n registered devices, i.e the first @n that were added
     * in the SmartCityHub (registration != installation).
     * <p>
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<SmartDevice> getFirstNDevicesByRegistration(int n) {
        if(n < 0) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        int until = n > smartDevices.size() ? smartDevices.size() : n;
        Collection<SmartDevice> firstNDevices = new ArrayList<>(n);
        for (Map.Entry<String, SmartDevice> smartDeviceEntry : smartDevices.entrySet()) {
            if (i >= until) {
                break;
            }
            firstNDevices.add(smartDeviceEntry.getValue());
            ++i;
        }
        return firstNDevices;
    }

    private Collection<String> convertToStringCollection(Collection<SmartDevice> smartDevices){
        Collection<String> stringCollectionOfDevices = new ArrayList<String>(smartDevices.size());
        for(SmartDevice smartDevice : smartDevices){
            stringCollectionOfDevices.add(smartDevice.getId());
        }
        return stringCollectionOfDevices;
    }
}

