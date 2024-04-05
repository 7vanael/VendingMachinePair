package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class InventoryManagerTest {

    @Test
    public void populateInventory() {
        /*
           Can only test size due to the fact that the file is populating a List<>, and there is no
           way to compare files exactly with every detail without just copying and pasting the method
           and then comparing the same method to each other.
         */

        //Arrange
        InventoryManager inventoryManager = new InventoryManager();
        int expected = 16;

        //Act

        inventoryManager.PopulateInventory();
        int result = inventoryManager.getInventory().size();

        //Assert
        Assert.assertEquals(expected, result);
    }



    @Test
    public void dispenseItem() {
        /*
        Only main change to system in method is decrementing the count on each item after
        one is chosen. Past that the only other thing it does to print to terminal. Didn't check if dispensing
        is possible for product with count of 0 because method isn't even called until it's confirmed
        that product has a count of greater than 0 in inventoryManager.selectProduct - line: 42.
         */

        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.PopulateInventory();
        List<Vendable> test = inventoryManager.getInventory();

        int expectedCount = 4;

        inventoryManager.dispenseItem(test.get(2));
        int result = test.get(2).getCount();

        Assert.assertEquals(expectedCount,result );

    }
}