package bg.sofia.uni.fmi.mjt.warehouse;

import bg.sofia.uni.fmi.mjt.warehouse.exceptions.CapacityExceededException;
import bg.sofia.uni.fmi.mjt.warehouse.exceptions.ParcelNotFoundException;

import java.time.LocalDateTime;
import java.util.Map;

public class MJTExpressWarehouse implements DeliveryServiceWarehouse {
    @Override
    public void submitParcel(Object label, Object parcel, LocalDateTime submissionDate) throws CapacityExceededException {

    }

    @Override
    public Object getParcel(Object label) {
        return null;
    }

    @Override
    public Object deliverParcel(Object label) throws ParcelNotFoundException {
        return null;
    }

    @Override
    public double getWarehouseSpaceLeft() {
        return 0;
    }

    @Override
    public Map getWarehouseItems() {
        return null;
    }

    @Override
    public Map deliverParcelsSubmittedBefore(LocalDateTime before) {
        return null;
    }

    @Override
    public Map deliverParcelsSubmittedAfter(LocalDateTime after) {
        return null;
    }
}
