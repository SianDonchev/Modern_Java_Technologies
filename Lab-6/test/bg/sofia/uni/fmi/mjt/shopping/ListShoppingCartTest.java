package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListShoppingCartTest {

    @Mock
    private ProductCatalog productCatalog;

    @InjectMocks
    private ListShoppingCart listShoppingCart;

    @Test
    public void testGetSortedItems() {
        Item item1 = new Apple("1");
        Item item2 = new Apple("1");
        Item item3 = new Chocolate("4");
        Item item4 = new Chocolate("4");
        Item item5 = new Chocolate("4");
        Item item6 = new Chocolate("5");

        listShoppingCart.addItem(item1);
        listShoppingCart.addItem(item2);
        listShoppingCart.addItem(item3);
        listShoppingCart.addItem(item4);
        listShoppingCart.addItem(item5);
        listShoppingCart.addItem(item6);

        List expectedSortedItems = new ArrayList();
        expectedSortedItems.add(item6);
        expectedSortedItems.add(item1);
        expectedSortedItems.add(item3);

        Collection<Item> actualSortedItems = listShoppingCart.getSortedItems();

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

        listShoppingCart.addItem(item1);
        listShoppingCart.addItem(item2);
        listShoppingCart.addItem(item3);
        listShoppingCart.addItem(item4);
        listShoppingCart.addItem(item5);
        listShoppingCart.addItem(item6);
        
        List expectedUniqueItems = new ArrayList();
        expectedUniqueItems.add(item6);
        expectedUniqueItems.add(item1);
        expectedUniqueItems.add(item3);
        
        Collection<Item> actualUniqueItems = listShoppingCart.getUniqueItems();

        int actualSize = actualUniqueItems.size();
        int expectedSize = expectedUniqueItems.size();
        assertEquals("Different length of expected and actual unique items", expectedSize, actualSize);

        for(Item item : actualUniqueItems) {
            assertTrue("Unique item missing", expectedUniqueItems.contains(item));
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddItemWithArgumentNull() {
        listShoppingCart.addItem(null);
    }

    @Test
    public void testAddItemWithValidArgument() {
        Item item = new Apple("1");
        listShoppingCart.addItem(item);

        assertTrue(listShoppingCart.items.contains(item));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveItemWithArgumentNull() {
        listShoppingCart.removeItem(null);
    }

    @Test (expected = ItemNotFoundException.class)
    public void testRemoveItemWithNoExistingItem() {
        listShoppingCart.addItem(new Apple("1"));
        listShoppingCart.removeItem(new Apple("2"));
    }

    @Test
    public void testRemoveExistingItem() {
        Item item = new Chocolate("1");
        listShoppingCart.addItem(item);
        listShoppingCart.removeItem(item);

        assertFalse(listShoppingCart.items.contains(item));
    }

    @Test
    public void testGetTotal() {
        Item item1 = new Chocolate("1");
        Item item2 = new Apple("2");
        Item item3 = new Apple("3");

        listShoppingCart.addItem(item1);
        listShoppingCart.addItem(item2);
        listShoppingCart.addItem(item3);

        when(productCatalog.getProductInfo("1")).thenReturn(new ProductInfo("1","Test1",5));
        when(productCatalog.getProductInfo("2")).thenReturn(new ProductInfo("2","Test2",10));
        when(productCatalog.getProductInfo("3")).thenReturn(new ProductInfo("3","Test3",11));
        int expectedTotalPrice = 26;
        assertEquals( "Difference in expected and actual price",expectedTotalPrice,listShoppingCart.getTotal(),0.01);
    }
}

