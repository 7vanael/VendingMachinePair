package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class POS {
    private BigDecimal balance = new BigDecimal("0");
    UserInterface userInterface = new UserInterface();
    public void feedMoney(Scanner keyboard){
        boolean isDone = false;
        while(!isDone) {
            System.out.println("Type in value of money entered:");
            String input = new String(userInterface.getUserInput(keyboard));
            if(new BigDecimal(input).compareTo(new BigDecimal("0"))< 0){
                //error
            }else{
                balance.add(new BigDecimal(input));
            }
        }
    }
}
