package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class ListShoppingCart implements ShoppingCart {

    private ArrayList<Item> items;
    private ProductCatalog catalog;

    public ListShoppingCart(final ProductCatalog catalog) {
        this.catalog = catalog;
        this.items = new ArrayList<>();
    }

    @Override
    public Collection<Item> getUniqueItems() {
        return new HashSet<>(items);
    }

    @Override
    public void addItem(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to be added is null");
        }
        items.add(item);
    }

    @Override
    public void removeItem(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item to be removed is null");
        }
        if (!items.remove(item)) {
            throw new ItemNotFoundException("Item is not found");
        }
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            ProductInfo info = catalog.getProductInfo(item.getId());
            total += info.price();
        }
        return total;
    }

    @Override
    public Collection<Item> getSortedItems() {
        Map<Item, Integer> itemToQuantity = createMap();
        Map<Item, Integer> sortedItems = new TreeMap<>(new Comparator<Item>() {
            public int compare(final Item item1, final Item item2) {
                return itemToQuantity.get(item1).compareTo(itemToQuantity.get(item2));
            }
        });
        sortedItems.putAll(itemToQuantity);
        return sortedItems.keySet();
    }

    private Map<Item, Integer> createMap() {
        HashMap<Item, Integer> itemToQuantity = new HashMap<>();
        for (Item item : items) {
            boolean condition = itemToQuantity.containsKey(item);
            itemToQuantity.put(item, condition ? itemToQuantity.get(item) + 1 : 1);
        }
        return itemToQuantity;
    }
}
