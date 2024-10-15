package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    private List<Transaction> transactionList = new ArrayList<>();
    private boolean isLedgerScreenShown = false;
    private Report report = new Report(transactionList);

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

    public void displayAllEntries(){
        System.out.println("Showing all entries:");
        for (Transaction transaction : this.getTransactionList()){
            System.out.println(transaction);
        }
    }

    public void displayDeposits(){
        System.out.println("Showing All Deposits:");

        // iterate through transaction list
        // filter products whose price are positive
        for (Transaction transaction : this.getTransactionList()){
            if (transaction.getProduct().price() > 0) {
                System.out.println(transaction);
            }
        }
    }

    public void displayPayments(){
        System.out.println("Showing All Payments:");
        for (Transaction transaction : this.getTransactionList()){
            if (transaction.getProduct().price() < 0) {
                System.out.println(transaction);
            }
        }
    }

    public void returnToHomeScreen(){
        System.out.println("Switching to Home Screen...");
        this.setLedgerScreenShown(false);
    }
}
