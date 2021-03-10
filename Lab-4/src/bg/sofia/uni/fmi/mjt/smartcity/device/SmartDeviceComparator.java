package bg.sofia.uni.fmi.mjt.smartcity.device;

import java.util.Comparator;

public class SmartDeviceComparator implements Comparator<SmartDevice> {
    @Override
    public int compare(SmartDevice s1,SmartDevice s2) {
        if(s1.getPowerConsumption() > s2.getPowerConsumption()){
            return 1;
        }
        else if(s1.getPowerConsumption() < s2.getPowerConsumption()){
            return -1;
        }
        return 0;
    }
}
