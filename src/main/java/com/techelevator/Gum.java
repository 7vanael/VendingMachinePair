package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Vendable{
    private String phrase = "Chew Chew, Yum!";
    public Gum(String slot, String name, BigDecimal price){
        super(slot, name, price);

    }
    public String getPhrase(){
        return phrase;
    }
}
