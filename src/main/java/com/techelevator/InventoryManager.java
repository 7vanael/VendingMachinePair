package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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

    public void selectProduct(POS pos, UserInterface userInterface, Scanner keyboard){
        if(pos.getBalance().compareTo(new BigDecimal("0")) > 0) {
            printInventory();
            System.out.println("Please enter the slot code for the item you'd like to purchase: ");
            String userSelection = userInterface.getUserInput(keyboard);
            boolean itemFound = false;
            for (Vendable item: inventory){
                if(item.getSlot().compareToIgnoreCase(userSelection) == 0){
                    if(item.getCount() > 0) {

                        if(pos.getBalance().compareTo(item.getPrice()) >= 0){
                            String itemDetails = item.getName() + " " + item.getSlot();
                            BigDecimal prior = pos.getBalance();
                            dispenseItem(item);
                            pos.payPrice(item.getPrice());
                            pos.log(prior, pos.getBalance(), itemDetails);
                            itemFound = true;
                        }
                    }else{
                        System.out.println("This item is sold out!");
                        itemFound = true;
                    }
                }
            }
            if (!itemFound){
                System.out.println("The entered code does not match a valid slot! Try again silly person.");
            }

        }else{
            System.out.println();
            System.out.println("Your current balance is: $" + pos.getBalance() + "... Snacks aren't free!");
        }
    }

    public void printInventory(){
        System.out.println();
        for(Vendable item : inventory){
            if(item.getCount() > 0){
                System.out.println(item.getSlot() + " | " + item.getName() + " | $" + item.getPrice() + " | " + item.getCount()+ " remaining");
            }else{
                System.out.println(item.getSlot() + " | " + item.getName() + " is Sold Out");
            }
        }
        System.out.println();
    }

    public void dispenseItem(Vendable item){
        item.setCount(item.getCount() - 1);
        System.out.print(item.getName() + " | $" + item.getPrice() + " | ");
        if(item instanceof Candy){
            Candy candy = (Candy) item;
            System.out.println(candy.getPhrase());
        }else if(item instanceof Chips){
            Chips chips = (Chips) item;
            System.out.println(chips.getPhrase());
        }else if (item instanceof Drink){
            Drink drink  = (Drink) item;
            System.out.println(drink.getPhrase());
        }else if (item instanceof Gum){
            Gum gum = (Gum) item;
            System.out.println(gum.getPhrase());
        }
    }

    public List<Vendable> getInventory() {
        return inventory;
    }
}
