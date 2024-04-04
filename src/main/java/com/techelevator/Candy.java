package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Vendable{
    private String phrase = "Munch Munch, Yum!";
    public Candy(String slot, String name, BigDecimal price){
        super(slot, name, price);

    }
    public String getPhrase(){
        return phrase;
    }
}
