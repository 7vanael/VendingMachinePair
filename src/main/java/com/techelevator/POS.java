package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class POS {
    private BigDecimal balance = new BigDecimal("0");
    private static final BigDecimal ZERO = new BigDecimal("0");
    private static final BigDecimal QUARTER = new BigDecimal(".25");
    private static final BigDecimal DIME = new BigDecimal(".10");
    private static final  BigDecimal NICKLE = new BigDecimal(".05");
    UserInterface userInterface = new UserInterface();
    public void feedMoney(String number){
        BigDecimal bigDecimalNum = new BigDecimal(number);
        balance = balance.add(bigDecimalNum);
    }

    public void finishTransaction(POS pos, InventoryManager inventoryManager, VendingMachine vendingMachine){
        BigDecimal remainder;
        BigDecimal quarterCount = ZERO;
        BigDecimal dimeCount = ZERO;
        BigDecimal nickleCount = ZERO;

        List<String> thingsToPrint = new ArrayList<>();


        while(balance.compareTo(ZERO) > 0) {
            if (balance.compareTo(QUARTER) >= 0) {
                remainder = balance.remainder(QUARTER);
                quarterCount = ((balance.subtract(remainder)).divide(QUARTER, RoundingMode.DOWN));
                thingsToPrint.add(quarterCount.toString() + " quarter(s)");
                balance = remainder;
            }
            if (balance.compareTo(DIME) >= 0) {
                remainder = balance.remainder(DIME);
                dimeCount = ((balance.subtract(remainder)).divide(DIME, RoundingMode.DOWN));
                thingsToPrint.add(dimeCount.toString() + " dime(s)");
                balance = remainder;
            }
            if (balance.compareTo(NICKLE) >= 0) {
                remainder = balance.remainder(NICKLE);
                nickleCount = ((balance.subtract(remainder)).divide(NICKLE, RoundingMode.DOWN));
                thingsToPrint.add(nickleCount.toString() + " nickle(s)");
                balance = remainder;
            }
        }

        String toPrint = "Your change is: ";
        int length = thingsToPrint.size();
        for (String itemToPrint : thingsToPrint){
            toPrint = toPrint + itemToPrint;
            length--;
            if(length > 0 ){
                toPrint = toPrint + " and ";
            }
        }

        System.out.println(toPrint);
//        vendingMachine.run(pos, inventoryManager, vendingMachine);

    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void payPrice(BigDecimal price){
        balance = balance.subtract(price);
        System.out.print("New balance is: $"+ getBalance() + " | ");
    }

}
