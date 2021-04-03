package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MapShoppingCartTest {
    private ShoppingCart mapShoppingCart;

    @Mock
    private ProductCatalog productCatalog;

    @Before
    public void setUp() {
        mapShoppingCart = new MapShoppingCart(productCatalog);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithArgumentNull() {
        mapShoppingCart.addItem(null);
    }

    @Test
    public void testAddItemWhenThereAreNoItems() {
        Item item = new Apple("1");
        mapShoppingCart.addItem(item);

        assertTrue("Added item is not found", mapShoppingCart.getUniqueItems().contains(item));
    }

    @Test
    public void testAddItemWithMultipleItems() {
        Item item1 = new Apple("1");
        Item item2 = new Chocolate("2");
        Item item3 = new Apple("3");

        mapShoppingCart.addItem(item1);
        mapShoppingCart.addItem(item2);
        mapShoppingCart.addItem(item3);

        Collection<Item> uniqueItems = mapShoppingCart.getUniqueItems();
        assertTrue(uniqueItems.contains(item1));
        assertTrue(uniqueItems.contains(item2));
        assertTrue(uniqueItems.contains(item3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemWhenArgumentIsNull() {
        mapShoppingCart.removeItem(null);
    }

    @Test (expected = ItemNotFoundException.class)
    public void testRemoveNoExistingItem() {
        mapShoppingCart.addItem(new Apple("1"));
        mapShoppingCart.removeItem(new Chocolate("2"));
    }

    @Test
    public void testRemoveItemWhenThereIsOneItem() {
        Item item = new Apple("1");
        mapShoppingCart.addItem(item);
        mapShoppingCart.removeItem(item);

        assertFalse("Item is not removed", mapShoppingCart.getUniqueItems().contains(item));
    }

    @Test
    public void testRemoveItemWhenThereIsMultipleItems() {
        Item item1 = new Apple("1");
        Item item2 = new Apple("1");
        Item item3 = new Apple("1");
        mapShoppingCart.addItem(item1);
        mapShoppingCart.addItem(item2);
        mapShoppingCart.addItem(item3);

        mapShoppingCart.removeItem(item1);;
        when(productCatalog.getProductInfo("1")).thenReturn(new ProductInfo("1","Test1",5));
        double expectedTotal = 10;

        assertEquals("Remove of an item with no success", expectedTotal, mapShoppingCart.getTotal(), 0.01);
    }

    @Test
    public void testGetTotalWithNoItems() {
        double expectedTotal = 0;

        assertEquals("Difference in total amount", expectedTotal, mapShoppingCart.getTotal(), 0.01);
    }

    @Test
    public void testGetTotalWithMultipleItems() {
        Item item1 = new Apple("1");
        Item item2 = new Chocolate("2");
        Item item3 = new Chocolate("3");

        mapShoppingCart.addItem(item1);
        mapShoppingCart.addItem(item2);
        mapShoppingCart.addItem(item3);

        when(productCatalog.getProductInfo("1")).thenReturn(new ProductInfo("1","Test1",5));
        when(productCatalog.getProductInfo("2")).thenReturn(new ProductInfo("2","Test2",10));
        when(productCatalog.getProductInfo("3")).thenReturn(new ProductInfo("3","Test3",12));

        double expectedTotal = 27;
        assertEquals("Difference in expected total amount", expectedTotal, mapShoppingCart.getTotal(), 0.01);
    }

    @Test
    public void testGetSortedItems() {
        Item item1 = new Apple("1");
        Item item2 = new Apple("1");
        Item item3 = new Chocolate("4");
        Item item4 = new Chocolate("4");
        Item item5 = new Chocolate("4");
        Item item6 = new Chocolate("5");

        mapShoppingCart.addItem(item1);
        mapShoppingCart.addItem(item2);
        mapShoppingCart.addItem(item3);
        mapShoppingCart.addItem(item4);
        mapShoppingCart.addItem(item5);
        mapShoppingCart.addItem(item6);

        List expectedSortedItems = new ArrayList();
        expectedSortedItems.add(item6);
        expectedSortedItems.add(item1);
        expectedSortedItems.add(item3);

        Collection<Item> actualSortedItems = mapShoppingCart.getSortedItems();

        int actualSize = actualSortedItems.size();
        int expectedSize = expectedSortedItems.size();
        assertEquals("Different length of expected and actual sorted items", expectedSize, actualSize);

        Iterator<Item> iteratorForActual = actualSortedItems.iterator();
        Iterator<Item> iteratorForExpected = expectedSortedItems.iterator();

        while (iteratorForActual.hasNext()) {
            assertEquals("Different item", iteratorForExpected.next(), iteratorForActual.next());
        }
    }

    @Test
    public void testGetUniqueItems() {
        Item item1 = new Apple("1");
        Item item2 = new Apple("1");
        Item item3 = new Chocolate("4");
        Item item4 = new Chocolate("4");
        Item item5 = new Chocolate("4");
        Item item6 = new Chocolate("5");

        mapShoppingCart.addItem(item1);
        mapShoppingCart.addItem(item2);
        mapShoppingCart.addItem(item3);
        mapShoppingCart.addItem(item4);
        mapShoppingCart.addItem(item5);
        mapShoppingCart.addItem(item6);

        List expectedUniqueItems = new ArrayList();
        expectedUniqueItems.add(item6);
        expectedUniqueItems.add(item1);
        expectedUniqueItems.add(item3);

        Collection<Item> actualUniqueItems = mapShoppingCart.getUniqueItems();

        int actualSize = actualUniqueItems.size();
        int expectedSize = expectedUniqueItems.size();
        assertEquals("Different length of expected and actual unique items", expectedSize, actualSize);

        for(Item item : actualUniqueItems) {
            assertTrue("Unique item missing", expectedUniqueItems.contains(item));
        }
    }

}
