package bg.sofia.uni.fmi.mjt.shopping;


import bg.sofia.uni.fmi.mjt.shopping.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MapShoppingCart implements ShoppingCart {

    public Map<Item, Integer> items;
    public ProductCatalog catalog;

    public MapShoppingCart(ProductCatalog catalog) {
        this.items = new HashMap<>();
        this.catalog = catalog;
    }

    public Collection<Item> getUniqueItems() {
        Collection<Item> uniqueItems = new HashSet<>();
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            uniqueItems.add(entry.getKey());
        }
        return uniqueItems;
    }

    @Override
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to be added is null");
        }
        if (items.containsKey(item)) {
            Integer i = items.get(item);
            items.put(item, ++i);
        } else {
            items.put(item, 1);
        }
    }

    @Override
    public void removeItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to be removed is null");
        }

        Integer occurrences = items.get(item);
        if (occurrences == null || occurrences == 0) {
            throw new ItemNotFoundException("Item is not found");
        } else if (occurrences == 1) {
            items.remove(item);
        } else {
            items.put(item, occurrences - 1);
        }
    }

    @Override
    public double getTotal() {
        int total = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            ProductInfo info = catalog.getProductInfo(entry.getKey().getId());
            total += (info.price() * entry.getValue());
        }
        return total;
    }

    @Override
    public Collection<Item> getSortedItems() {
        List<Item> sortedItems = new ArrayList<>(items.keySet());
        Collections.sort(sortedItems, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                Integer info1 = items.get(item1);
                Integer info2 = items.get(item2);
                ;
                if (info1 > info2) {
                    return 1;
                } else if (info1 < info2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return sortedItems;
    }

}