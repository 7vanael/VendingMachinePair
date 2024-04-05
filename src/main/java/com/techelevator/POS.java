package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class POS {
    private BigDecimal balance = new BigDecimal("0");
    private static final BigDecimal ZERO = new BigDecimal("0");
    private static final BigDecimal QUARTER = new BigDecimal(".25");
    private static final BigDecimal DIME = new BigDecimal(".10");
    private static final  BigDecimal NICKLE = new BigDecimal(".05");
    File file = new File("Log.txt");
    public void feedMoney(String number){
        BigDecimal bigDecimalNum = new BigDecimal(number);
        BigDecimal prior = balance;
        balance = balance.add(bigDecimalNum);
        log(prior, balance, "Feed Money: ");
    }

    public void finishTransaction(){
        BigDecimal prior = balance;
        BigDecimal remainder;
        BigDecimal quarterCount = ZERO;
        BigDecimal dimeCount = ZERO;
        BigDecimal nickleCount = ZERO;

        List<String> thingsToPrint = new ArrayList<>();


        while(balance.compareTo(ZERO) > 0) {
            if (balance.compareTo(QUARTER) >= 0) {
                remainder = balance.remainder(QUARTER);
                quarterCount = ((balance.subtract(remainder)).divide(QUARTER, RoundingMode.DOWN));
                int quarterCountInt = quarterCount.intValue();
                thingsToPrint.add(quarterCountInt + " quarter(s)");
                balance = remainder;
            }
            if (balance.compareTo(DIME) >= 0) {
                remainder = balance.remainder(DIME);
                dimeCount = ((balance.subtract(remainder)).divide(DIME, RoundingMode.DOWN));
                int dimeCountInt = dimeCount.intValue();
                thingsToPrint.add(dimeCountInt + " dime(s)");
                balance = remainder;
            }
            if (balance.compareTo(NICKLE) >= 0) {
                remainder = balance.remainder(NICKLE);
                nickleCount = ((balance.subtract(remainder)).divide(NICKLE, RoundingMode.DOWN));
                int nickleCountInt = nickleCount.intValue();
                thingsToPrint.add(nickleCountInt + " nickle(s)");
                balance = remainder;
            }
        }

        System.out.println();
        String toPrint = "Your change is: ";
        int length = thingsToPrint.size();
        for (String itemToPrint : thingsToPrint){
            toPrint = toPrint + itemToPrint;
            length--;
            if(length > 0 ){
                toPrint = toPrint + " and ";
            }
        }
        log(prior, balance, "Give Change: ");
        System.out.println(toPrint);
        System.out.println();
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void payPrice(BigDecimal price){
        balance = balance.subtract(price);
    }

    public void log(BigDecimal prior, BigDecimal balance, String string){
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))){
            LocalDateTime rightNow = LocalDateTime.now();
            DateTimeFormatter myTime = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String dateString = rightNow.format(myTime);
            writer.println(dateString + " " + string + " $" + prior + " $" + balance);
        }catch (FileNotFoundException ex){
            System.out.println("Log file not found.");
        }
    }




}
