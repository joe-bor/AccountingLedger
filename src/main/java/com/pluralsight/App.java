package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;

public class App {
    private static boolean isAppRunning = true;
    private static Ledger ledger = initLedger();

    public static void main(String[] args) {
        greetings();
        do {
            homeScreen();
        } while (isAppRunning());
    }

    private static void greetings() {
        System.out.println("""
                
                ====== Accounting Ledger =====
                
                Welcome to your personal accounting ledger.
                Track deposits, payments, and review your ledger with ease.
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

    private static void executeHomeScreenOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = Utils.validateString(scanner);
        switch (option) {
            case "D" -> ledger.createTransaction(true);
            case "P" -> ledger.createTransaction(false);
            case "L" -> goToLedgerScreen();
            case "X" -> quitApp();
            default -> System.err.println("Invalid option. Please try again!");
        }
    }

    private static void quitApp() {
        System.out.println("Quitting the application...");
        setIsAppRunning(false);
    }

    public static boolean isAppRunning() {
        return isAppRunning;
    }

    public static void setIsAppRunning(boolean isAppRunning) {
        App.isAppRunning = isAppRunning;
    }

    private static void goToLedgerScreen() {
        System.out.println("Switching to Ledger Screen...");
        ledger.displayScreen();
    }

    // Initializes the ledger's transactions field based on the file 'transactions.csv'
    private static Ledger initLedger() {
        List<Transaction> transactionList = new ArrayList<>();

        // This logic assumes 'transactions.csv' is formatted correctly
        // date|time|description|vendor|amount
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"))) {
            String transactionLine = bufferedReader.readLine(); // skips the 1st line (headers)
            while ((transactionLine = bufferedReader.readLine()) != null) {
                String[] transactionDetails = transactionLine.trim().split(Pattern.quote("|"));
                LocalDate transactionDate = LocalDate.parse(transactionDetails[0]);
                LocalTime transactionTime = LocalTime.parse(transactionDetails[1]);
                // construct product to be added to transaction list
                Product product = new Product(transactionDetails[2], transactionDetails[3], Float.parseFloat(transactionDetails[4]));
                transactionList.add(new Transaction(transactionDate, transactionTime, product));
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        Collections.sort(transactionList, Comparator.comparing(Transaction::getTransactionDate));
        Collections.reverse(transactionList);

        return new Ledger(transactionList);
    }
}
