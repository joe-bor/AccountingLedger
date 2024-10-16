package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isAppRunning = true;
    private static Ledger ledger = initLedger();


    public static void main(String[] args) {
        greetings();
        do {
            homeScreen();
        } while (isAppRunning);
    }

    private static void greetings() {
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

    private static void executeHomeScreenOptions() {
        String option = scanner.nextLine().trim().toUpperCase();
        switch (option) {
            case "D" -> addDeposit();
            case "P" -> makePayment();
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

    // TODO: merge with makePayment() and encapsulate in Ledger + enforce +/- floats
    // TODO: Add a formatter for the entries (eg. capitalize 1st letter of each word)
    private static void addDeposit() {
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

    private static void makePayment() {
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

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.append(String.format("%s|%s|%s|%s|%.2f\n", dateStr, timeStr, description, vendor, price));
            System.out.println("Success! Transaction recorded to `transactions.csv`");
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println(e);
        }
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
