package com.techelevator;

import jdk.jshell.Diag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
	public static final Scanner keyboard = new Scanner(System.in);
	private static final String DISPLAY_MENU = "(1) Display Vending Machine Items";
	private static final String PURCHASE = "(2) Purchase";
	private static final String EXIT = "(3) Exit";
	private static final String SALES_REPORT = "";
	private static final String FEED_MONEY = "(1) Feed Money";
	private static final String SELECT_PRODUCT = "(2) Select Product";
	private static final String FINISH_TRANSACTION = "(3) Finish Transaction";
	private static final String ONE_DOLLAR_AMOUNT = "(1) $1";
	private static final String FIVE_DOLLAR_AMOUNT = "(2) $5";
	private static final String TEN_DOLLAR_AMOUNT = "(3) $10";

	private static final String[] HOME_SCREEN = {DISPLAY_MENU, PURCHASE, EXIT, SALES_REPORT};
	private static final String[] PURCHASE_SCREEN = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};
	private static final String [] FEED_MONEY_OPTIONS = {ONE_DOLLAR_AMOUNT, FIVE_DOLLAR_AMOUNT, TEN_DOLLAR_AMOUNT};


	public static void main(String[] args) {
		VendingMachine vendingMachine = new VendingMachine();
		InventoryManager inventoryManager = new InventoryManager();
		POS pos = new POS();
		vendingMachine.run(pos, inventoryManager, vendingMachine);
	}


	public void run(POS pos, InventoryManager inventoryManager, VendingMachine vendingMachine){
		inventoryManager.PopulateInventory();
		boolean exit = false;
		UserInterface userInterface = new UserInterface();

		do {
			System.out.println("Hello! Welcome to the Vending Machine!");
			System.out.println();
			userInterface.printMenu(HOME_SCREEN);
			String choice = userInterface.getUserMenuInput(keyboard, HOME_SCREEN);


			if(choice.equalsIgnoreCase(DISPLAY_MENU)){
				inventoryManager.printInventory();
			}else if(choice.equalsIgnoreCase(PURCHASE)){
				vendingMachine.purchaseMenu(pos,inventoryManager ,vendingMachine, keyboard);
			}else if(choice.equalsIgnoreCase(EXIT)){
				exit = true;
			}else if (choice.equalsIgnoreCase(SALES_REPORT)){

			}
		}while(!exit);
	}

	public void purchaseMenu(POS pos, InventoryManager inventoryManager,VendingMachine vendingMachine, Scanner keyboard){
		boolean exit = false;
		UserInterface userInterface = new UserInterface();


		do {
			System.out.println();
			System.out.println("Current Balance: $" + pos.getBalance());
			System.out.println();
			userInterface.printMenu(PURCHASE_SCREEN);
			String choice = userInterface.getUserMenuInput(keyboard, PURCHASE_SCREEN);

			if(choice.equalsIgnoreCase(FEED_MONEY)){
				vendingMachine.FeedMoneyOptions(pos);
			}else if(choice.equalsIgnoreCase(SELECT_PRODUCT)){
				inventoryManager.selectProduct(pos, userInterface, keyboard);
			}else if(choice.equalsIgnoreCase(FINISH_TRANSACTION)){
				pos.finishTransaction();
				exit = true;

			}
		}while(!exit);

	}

	public void FeedMoneyOptions(POS pos){
		UserInterface userInterface = new UserInterface();

			System.out.println();
			System.out.println("Current Balance: $" + pos.getBalance());
			System.out.println();
			userInterface.printMenu(FEED_MONEY_OPTIONS);
			String choice = userInterface.getUserMenuInput(keyboard, FEED_MONEY_OPTIONS);


			if(choice.equalsIgnoreCase(ONE_DOLLAR_AMOUNT)){
				pos.feedMoney("1");
			}else if(choice.equalsIgnoreCase(FIVE_DOLLAR_AMOUNT)){
				pos.feedMoney("5");
			}else if(choice.equalsIgnoreCase(TEN_DOLLAR_AMOUNT)){
				pos.feedMoney("10");
			}


	}

}
