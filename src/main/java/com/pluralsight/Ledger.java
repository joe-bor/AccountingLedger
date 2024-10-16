package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class Ledger {
    private boolean isLedgerScreenShown = false;
    private List<Transaction> transactionList;
    private Report report;

    // CONSTRUCTOR(S):
    public Ledger(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        this.report = new Report(transactionList);
    }

    // GETTERS & SETTERS:
    public boolean isLedgerScreenShown() {
        return isLedgerScreenShown;
    }

    public void setLedgerScreenShown(boolean ledgerScreenShown) {
        isLedgerScreenShown = ledgerScreenShown;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    // OTHER METHODS:
    public void displayScreen() {
        do {
            System.out.println("""
                    
                    ----- Ledger Screen -----
                    Which entries would you like to see? Newest entries are shown first.
                    
                    [A] - All entries
                    [D] - Deposits
                    [P] - Payments
                    [R] - Reports
                    [H] - Go back to Home Screen
                    
                    """);
            this.setLedgerScreenShown(true);
            executeOptions();
        } while (this.isLedgerScreenShown());
    }

    public void executeOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().trim().toUpperCase();

        switch (option) {
            case "A" -> displayAllEntries();
            case "D" -> displayDeposits();
            case "P" -> displayPayments();
            case "R" -> report.displayScreen();
            case "H" -> returnToHomeScreen();
            case null, default -> System.err.println("Invalid option. Please try again!");
        }
    }

    public void displayAllEntries() {
        System.out.println("Showing all entries:");
        for (Transaction transaction : this.getTransactionList()) {
            System.out.println(transaction);
        }
    }

    public void displayDeposits() {
        System.out.println("Showing All Deposits:");

        // iterate through transaction list
        // filter products whose price are positive
        for (Transaction transaction : this.getTransactionList()) {
            if (transaction.getProduct().price() > 0) {
                System.out.println(transaction);
            }
        }
    }

    public void displayPayments() {
        System.out.println("Showing All Payments:");
        for (Transaction transaction : this.getTransactionList()) {
            if (transaction.getProduct().price() < 0) {
                System.out.println(transaction);
            }
        }
    }

    public void returnToHomeScreen() {
        System.out.println("Switching to Home Screen...");
        this.setLedgerScreenShown(false);
    }

    // TODO: Add a formatter for the entries (eg. capitalize 1st letter of each word)
    public void createTransaction(boolean isDeposit) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(isDeposit ? "What are you depositing?" : "What did you buy?");
        String description = scanner.nextLine().trim();

        System.out.println(isDeposit ? "Who is it from?" : "Who did you pay?");
        String from = scanner.nextLine().trim();

        System.out.println(isDeposit ? "How much is this?" : "How much did you pay?");
        float price = scanner.nextFloat();
        scanner.nextLine();

        // create a product -> create transaction
        Transaction transaction = new Transaction(new Product(description, from, isDeposit ? price : -price));
        transaction.logToCSVFile();
        // then add transaction to transaction list that belongs to ledger
        this.getTransactionList().add(transaction);
    }
}
