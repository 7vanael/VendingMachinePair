package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class POSTest {
    @Before
    public void setUp() {
        POS pos = new POS();
    }

    @Test
    public void valid_number_adds_correctly_feedMoney() {
        /*
        This method can only be called by a valid input from
        the PurchaseMenu which only permits values of
        "1", "5" or "10" to be passed into this method.
        */

        //Arrange
       POS pos = new POS();
       BigDecimal initalBalance = pos.getBalance();
       BigDecimal amtAdded = new BigDecimal("5");
       BigDecimal expected = initalBalance.add(amtAdded);

        //Act
       pos.feedMoney("5");
       BigDecimal actual = pos.getBalance();

       //Assert
       assertTrue(expected.compareTo(actual) == 0);

    }

    @Test
    public void Balance_of_65cents_dispenses_correct_change_finishTransaction() {
        //Arrange
        POS pos = new POS();
        pos.feedMoney(".65");
        String expected = "Your change is: 2 quarter(s) and 1 dime(s) and 1 nickle(s)";

        //Act
        pos.finishTransaction();
        assertTrue(expected.compareTo(pos.getToPrint()) == 0);
    }
    @Test
    public void finishTransaction() {
    }



    @Test
    public void payPrice() {
    }

//    @Test
//    public void log() {
//    }
}