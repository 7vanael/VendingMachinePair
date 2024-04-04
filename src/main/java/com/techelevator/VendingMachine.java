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
	private static final String[] HOME_SCREEN = {DISPLAY_MENU, PURCHASE, EXIT, SALES_REPORT};
	private static final String[] PURCHASE_SCREEN = {FEED_MONEY, SELECT_PRODUCT, FINISH_TRANSACTION};


	public static void main(String[] args) {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.run();
	}


	public void run(){
		VendingMachine vendingMachine = new VendingMachine();
		boolean exit = false;
		UserInterface userInterface = new UserInterface();

		do {
			System.out.println();
			System.out.println("Hello! Welcome to the Vending Machine!");
			System.out.println();
			userInterface.printMenu(HOME_SCREEN);
			String choice = userInterface.getUserMenuInput(keyboard, HOME_SCREEN);


			if(choice.equalsIgnoreCase(DISPLAY_MENU)){
				System.out.println("Hi");
			}else if(choice.equalsIgnoreCase(PURCHASE)){
				vendingMachine.purchaseMenu();
			}else if(choice.equalsIgnoreCase(EXIT)){
				exit = true;
			}else if (choice.equalsIgnoreCase(SALES_REPORT)){

			}
		}while(!exit);
	}

	public void purchaseMenu(){
		boolean exit = false;
		UserInterface userInterface = new UserInterface();

		do {
			System.out.println();
//			System.out.println("Current Balance: " + POS.getBalance);
			System.out.println();
			userInterface.printMenu(PURCHASE_SCREEN);
			String choice = userInterface.getUserMenuInput(keyboard, PURCHASE_SCREEN);

			if(choice.equalsIgnoreCase(FEED_MONEY)){
				System.out.println("Hi");
			}else if(choice.equalsIgnoreCase(SELECT_PRODUCT)){

			}else if(choice.equalsIgnoreCase(FINISH_TRANSACTION)){

				exit = true;
			}
		}while(!exit);

	}

}
