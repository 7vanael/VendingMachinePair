package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Vendable{
    private String phrase = "Crunch Crunch, Yum!";
    public Chips(String slot, String name, BigDecimal price){
        super(slot, name, price);

    }
    public String getPhrase(){
        return phrase;
    }
}
