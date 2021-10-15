package org.goit;

import org.goit.grocer_store.GrocerStore;
import org.junit.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    @Test
    public void calculateTotalCostTest() {
        String basket = "";
        Assert.assertEquals("Basket is empty ", 0.00,
            GrocerStore.calculateTotalCost(basket), 0.0);

        Assert.assertEquals("Basket contains nonexistent products ", 0.00,
            GrocerStore.calculateTotalCost(basket), 0.0);
    }


}
