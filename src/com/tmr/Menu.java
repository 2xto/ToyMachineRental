package com.tmr;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private static Menu instance = null;

    public static Menu getInstance(){
        if (instance == null){
            instance = new Menu();
        }
        return instance;
    }

    RentalDatabase rentalDatabase = new RentalDatabase(Main.getFileName());

    public void mainMenu(){
        int choice = -1;

        while (choice!=0)   {
            System.out.println("""

                    -Toy Machine Rental System-
                    1. Rental transactions
                    2. Customer operations
                    3. Rentals operations
                    0. Exit""");

            choice = scanner.nextInt();

            switch (choice) {
                case 0 -> System.out.println("\n Program closed.");
                case 1 -> transactionMenu();
                case 2 -> customerMenu();
                case 3 -> rentalMenu();
                default -> System.out.println("Input [" + choice + "] is not found, please enter correct one.");
            }
        }
    }

    private void transactionMenu() {
        int subChoice;

        // Transaction rental submenu

        do {
            System.out.println("""
                                    
                    1. Create transaction
                    2. Edit transaction
                    3. Show transaction(s) of customer (by ID)
                    4. Show all transactions
                    5. Show rentals available for transactions
                    6. Close transaction (by rental ID)
                    0. Go back
                    """);

            switch (subChoice = scanner.nextInt()) {
                case 0:
                    return;

                case 1:
                    System.out.println("Enter customer ID:");
                    int customerId = scanner.nextInt();
                    System.out.println("Enter rental ID:");
                    int rentalId = scanner.nextInt();
                    rentalDatabase.createTransaction(customerId,rentalId);
                    break;

                case 2:
                    scanner.nextLine();
                    System.out.println("Enter rental ID:");
                    int id = scanner.nextInt();
                    System.out.println("Set customer ID:");
                    customerId = scanner.nextInt();
                    System.out.println("Set rental ID:");
                    rentalId = scanner.nextInt();
                    rentalDatabase.updateTransaction(id,customerId,rentalId);
                    break;

                case 3:
                    System.out.println("Enter customer ID:");
                    id = scanner.nextInt();
                    rentalDatabase.showCustomerTransactions(id);
                    break;

                case 4:
                    rentalDatabase.showTransactions();
                    break;

                case 5:
                    rentalDatabase.showRentalAvailable();
                    break;

                case  6:
                    System.out.println("Enter client ID:");
                    System.out.println("Enter rental ID:");
                    rentalId = scanner.nextInt();
                    rentalDatabase.closeTransaction(rentalId);
                    break;

                default:
                    System.out.println("Input [" + subChoice + "] is not found, please enter correct one.");
                    break;
            }

        }while (subChoice != 0);
    }

    private void rentalMenu() {
        int subChoice;

        // Rental submenu

        do {
            System.out.println("""
                                    
                    1. Create rental
                    2. Edit rental data
                    3. Show list of rentals
                    4. Delete rental
                    0. Go back
                    """);

            switch (subChoice = scanner.nextInt()) {
                case 0:
                    return;

                case 1:
                    scanner.nextLine();
                    System.out.println("Enter type:");
                    String type = scanner.nextLine();
                    System.out.println("Enter brand:");
                    String brand = scanner.nextLine();
                    System.out.println("Enter model:");
                    String model = scanner.nextLine();
                    System.out.println("Enter price:");
                    double price = scanner.nextDouble();
                    System.out.println("Enter deposit");
                    double deposit = scanner.nextDouble();
                    rentalDatabase.createRental(type,brand,model,price,deposit);
                    break;

                case 2:
                    scanner.nextLine();
                    System.out.println("Enter rental ID:");
                    int id = scanner.nextInt();
                    System.out.println("Set rental type:");
                    scanner.nextLine();
                    String setType = scanner.nextLine();
                    System.out.println("Set rental brand:");
                    String setBrand = scanner.nextLine();
                    System.out.println("Set rental model");
                    String setModel = scanner.nextLine();
                    System.out.println("Set rental price:");
                    double setPrice = scanner.nextDouble();
                    System.out.println("Set rental deposit:");
                    double setDeposit = scanner.nextDouble();
                    rentalDatabase.updateRental(id,setType,setBrand,setModel,setPrice,setDeposit);
                    break;

                case 3:
                    rentalDatabase.showRental();
                    break;

                case 4:
                    System.out.println("Enter rental ID to be deleted:");
                    scanner.nextLine();
                    int deleteId = scanner.nextInt();
                    rentalDatabase.deleteRental(deleteId);
                    break;

                default:
                    System.out.println("Input [" + subChoice + "] is not found, please enter correct one.");
                    break;
            }

        }while (subChoice != 0);
    }


    public void customerMenu() {
        int subChoice;

        // Customer submenu


        do {
            System.out.println("""
                                    
                    1. Create customer
                    2. Edit customer data
                    3. Show list of customers
                    4. Delete customer
                    0. Go back
                    """);

            switch (subChoice = scanner.nextInt()) {
                case 0:
                    return;
                case 1:
                    scanner.nextLine();
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter surename:");
                    String surename = scanner.nextLine();
                    rentalDatabase.createCustomer(name,surename);

                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("Enter customer ID:");
                    int id = scanner.nextInt();
                    System.out.println("Set customer name:");
                    scanner.nextLine();
                    String setName = scanner.nextLine();
                    System.out.println("Set customer surename:");
                    String setSurename = scanner.nextLine();
                    rentalDatabase.updateCustomer(id,setName,setSurename);
                    break;
                case 3:
                    rentalDatabase.showCustomers();
                    break;
                case 4:
                    System.out.println("Enter customer ID to be deleted:");
                    scanner.nextLine();
                    int deleteId = scanner.nextInt();
                    rentalDatabase.deleteCustomer(deleteId);
                    break;


                default:
                    System.out.println("Input [" + subChoice + "] is not found, please enter correct one.");
                    break;
            }

        }while (subChoice != 0);


        }

}



