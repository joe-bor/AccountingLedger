package com.pluralsight;

import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private  static boolean isAppRunning = true;
    private static Ledger ledger = new Ledger(new ArrayList<Transaction>());


    public static void main(String[] args) {
        greetings();
        do {
            homeScreen();
        } while (isAppRunning);
    }

    private static void greetings(){
        System.out.println("""
                
                ====== Accounting Ledger =====
                This
                is
                just
                a
                placeholder.
                """);
    }
    private static void homeScreen() {
        System.out.println("""
                
                ----- HOME SCREEN -----
                What would you like to do?
                [D] - Add Deposit
                [P] - Make Payment
                [L] - Ledger Screen
                [X] - Exit
                """);
        executeHomeScreenOptions();
    }

    private static void executeHomeScreenOptions(){
        String option = scanner.nextLine().trim().toUpperCase();
        switch (option){
            case "D" -> addDeposit();
            case "P" -> makePayment();
            case "L" -> goToLedgerScreen();
            case "X" -> quitApp();
            default -> System.err.println("Invalid option. Please try again!");
        }
    }

    private static void quitApp(){
        System.out.println("Quitting the application...");
        setIsAppRunning(false);
    }

    public static boolean isAppRunning() {
        return isAppRunning;
    }

    public static void setIsAppRunning(boolean isAppRunning) {
        App.isAppRunning = isAppRunning;
    }

    private static void addDeposit(){
        // prompt user
        System.out.println("What are you depositing?");
        String description = scanner.nextLine().trim();

        System.out.println("Who is it from?");
        String from = scanner.nextLine().trim();

        System.out.println("How much is this?");
        float price = scanner.nextFloat();
        scanner.nextLine();

        // create a product -> create transaction
        Transaction transaction = new Transaction(new Product(description, from, price));
        // then add transaction to transaction list that belongs to ledger
        ledger.getTransactionList().add(transaction);
        logToCSVFile(transaction);
    }

    private static void makePayment(){
        System.out.println("What did you buy?");
        String productDescription = scanner.nextLine().trim();

        System.out.println("Who did you pay?");
        String paidTo = scanner.nextLine().trim();

        System.out.println("How much did you pay?");
        float price = scanner.nextFloat();
        scanner.nextLine();

        Transaction transaction = new Transaction(new Product(productDescription, paidTo, -price));
        ledger.getTransactionList().add(transaction);
        logToCSVFile(transaction);
    }

    private static void logToCSVFile(Transaction transaction) {
        String dateStr = transaction.getTransactionDate().toString();
        String timeStr = transaction.getTransactionTime().truncatedTo(ChronoUnit.SECONDS).toString();
        String description = transaction.getProduct().description();
        String vendor = transaction.getProduct().vendor();
        float price = transaction.getProduct().price();

        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.append(String.format("%s|%s|%s|%s|%.2f\n", dateStr, timeStr, description, vendor, price));
            System.out.println("Success! Transaction recorded to `transactions.csv`");
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static void goToLedgerScreen(){
        System.out.println("Switching to Ledger Screen...");
        ledger.displayScreen();
    }
}
