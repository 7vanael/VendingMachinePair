package com.techelevator;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManager {
    private List<Vendable> inventory = new ArrayList<>();
    public void PopulateInventory(){
        File contents = new File("vendingmachine.csv");
        try(Scanner fileReader = new Scanner(contents)){
            String[] lineArray;
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                lineArray = line.split("\\|");
                if(lineArray[3].equalsIgnoreCase("Chip")){
                    inventory.add(new Chips(lineArray[0], lineArray[1], new BigDecimal(lineArray[2])));
                }else if(lineArray[3].equalsIgnoreCase("Candy")){
                    inventory.add(new Candy(lineArray[0], lineArray[1], new BigDecimal(lineArray[2])));
                }else if(lineArray[3].equalsIgnoreCase("Gum")){
                    inventory.add(new Gum(lineArray[0], lineArray[1], new BigDecimal(lineArray[2])));
                }else if(lineArray[3].equalsIgnoreCase("Drink")){
                    inventory.add(new Drink(lineArray[0], lineArray[1], new BigDecimal(lineArray[2])));
                }
            }

        }catch (FileNotFoundException ex){
            System.out.println("Your inventory file was not found");
        }

    }
    public List<Vendable> getInventory(){
        return inventory;
    }
    public void printInventory(){
        for(Vendable item : inventory){
            if(item.getCount() > 0){
                System.out.println(item.getSlot() + " | " + item.getName() + " | $" + item.getPrice() + " | " + item.getCount()+ " remaining");
            }else{
                System.out.println(item.getSlot() + " | " + item.getName() + "Sold Out");
            }
        }
    }

    public void dispenseItem(Vendable item){
        item.setCount(item.getCount() - 1);
    }
}
