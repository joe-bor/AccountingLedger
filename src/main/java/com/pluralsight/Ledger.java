package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

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

    public void displayScreen(){
        System.out.println("""
                
                ***** Ledger Screen *****
                Which entries would you like to see? Newest entries are shown first
                
                [A] - All entries
                [D] - Deposits
                [P] - Payments
                [R] - Reports
                [H] - Go back to Home Screen
                
                """);
    }
}
