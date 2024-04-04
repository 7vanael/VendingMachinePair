package com.techelevator;

import java.math.BigDecimal;

public abstract class Vendable {
    private String slot;
    private String name;
    private BigDecimal price;
    private int count;
    private final int MAX_COUNT = 5;

    public Vendable(String slot, String name, BigDecimal price) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.count = MAX_COUNT;
    }

    public String getSlot(){
        return slot;
    }
    public String getName(){
        return name;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        if(count >=0 && count <= MAX_COUNT) {
            this.count = count;
        }else{
            System.out.println("Invalid count entered; count not changed");
        }

    }

}