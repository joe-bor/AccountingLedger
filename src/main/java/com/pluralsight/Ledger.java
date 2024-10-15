package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    private List<Transaction> transactionList = new ArrayList<>();

    public Ledger(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void displayScreen() {
        System.out.println("""
                
                ***** Ledger Screen *****
                Which entries would you like to see? Newest entries are shown first.
                
                [A] - All entries
                [D] - Deposits
                [P] - Payments
                [R] - Reports
                [H] - Go back to Home Screen
                
                """);
        executeOptions();
    }

    public void executeOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().trim().toUpperCase();

        switch (option) {
            case "A" -> displayAllEntries();
            case "D" -> System.out.println("Deposits");
            case "P" -> System.out.println("Payments");
            case "R" -> System.out.println("Reports");
            case "H" -> System.out.println("Go back to home screen");
            default -> System.err.println("Invalid option. Please try again!");
        }
    }

    public void displayAllEntries(){
        for (Transaction transaction : this.getTransactionList()){
            System.out.println(transaction);
        }
    }
}
