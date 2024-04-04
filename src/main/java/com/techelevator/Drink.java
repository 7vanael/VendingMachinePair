package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Vendable{
    private String phrase = "Glug Glug, Yum!";
    public Drink(String slot, String name, BigDecimal price){
        super(slot, name, price);

    }
    public String getPhrase(){
        return phrase;
    }
}
