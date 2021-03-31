package bg.sofia.uni.fmi.mjt.warehouse;

import bg.sofia.uni.fmi.mjt.warehouse.exceptions.CapacityExceededException;
import bg.sofia.uni.fmi.mjt.warehouse.exceptions.ParcelNotFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MJTExpressWarehouse<L, P> implements DeliveryServiceWarehouse<L, P> {

    private HashMap<L, PairWithSubmissionDate<P>> parcels;
    private int retentionPeriod;
    private int capacity;

    public MJTExpressWarehouse(int capacity, int retentionPeriod) {
        this.parcels = new HashMap<>(capacity);
        this.capacity = capacity;
        this.retentionPeriod = retentionPeriod;
    }

    @Override
    public void submitParcel(L label, P parcel, LocalDateTime submissionDate) throws CapacityExceededException {
        if (submissionDate == null || submissionDate.isAfter(LocalDateTime.now()) ||
                label == null || parcel == null) {
            throw new IllegalArgumentException("Provided date is a date in the future");
        }
        PairWithSubmissionDate<P> pairOfParcelAndSubmissionDate = new PairWithSubmissionDate<>(parcel, submissionDate);

        if (parcels.size() == capacity) {
            if (!removeItemIfPossible()) {
                throw new CapacityExceededException("No capacity left in the warehouse");
            }
        }
        parcels.put(label, pairOfParcelAndSubmissionDate);
    }

    @Override
    public P getParcel(L label) {
        if (label == null) {
            throw new IllegalArgumentException("Label is null");
        }
        return parcels.get(label).parcel();
    }

    @Override
    public P deliverParcel(L label) throws ParcelNotFoundException {
        if (label == null) {
            throw new IllegalArgumentException("Label is null");
        }
        P parcel = parcels.remove(label).parcel();
        if (parcel == null) {
            throw new ParcelNotFoundException("Parcel with the given label does not exist in the warehouse");
        }
        return parcel;
    }

    @Override
    public double getWarehouseSpaceLeft() {
        return ((Double.valueOf((capacity - parcels.size()) / 100)));
    }

    @Override
    public Map<L, P> getWarehouseItems() {
        Map<L, P> warehouseItems = new HashMap<>(parcels.size());
        for (Map.Entry<L, PairWithSubmissionDate<P>> pair :
                parcels.entrySet()) {
            warehouseItems.put(pair.getKey(), parcels.get(pair.getKey()).parcel());
        }
        return warehouseItems;
    }

    @Override
    public Map deliverParcelsSubmittedBefore(LocalDateTime before) {
        if (before == null) {
            throw new IllegalArgumentException("The given date is null");
        }
        Map<L, P> warehouseItems = new HashMap<>(parcels.size());
        for (Map.Entry<L, PairWithSubmissionDate<P>> pair :
                parcels.entrySet()) {
            if (before.isAfter(pair.getValue().submissionDate())) {
                warehouseItems.put(pair.getKey(), parcels.remove(pair.getKey()).parcel());
            }
        }
        return warehouseItems;
    }

    @Override
    public Map deliverParcelsSubmittedAfter(LocalDateTime after) {
        if (after == null) {
            throw new IllegalArgumentException("The given date is null");
        }
        Map<L, P> warehouseItems = new HashMap<>(parcels.size());
        for (Map.Entry<L, PairWithSubmissionDate<P>> pair :
                parcels.entrySet()) {
            if (after.isBefore(pair.getValue().submissionDate())) {
                warehouseItems.put(pair.getKey(), parcels.remove(pair.getKey()).parcel());
            }
        }
        return warehouseItems;
    }

    private boolean removeItemIfPossible() {
        for (Map.Entry<L, PairWithSubmissionDate<P>> labelAndData :
                parcels.entrySet()) {
            if (isParcelWithExpiredDate(parcels.get(labelAndData.getKey()))) {
                parcels.remove(labelAndData.getKey());
                return true;
            }
        }
        return false;
    }

    private boolean isParcelWithExpiredDate(PairWithSubmissionDate pairOfParcelAndSubmissionDate) {
        return Duration.between(LocalDateTime.now(), pairOfParcelAndSubmissionDate.submissionDate()).toDays()
                > retentionPeriod;
    }

}
