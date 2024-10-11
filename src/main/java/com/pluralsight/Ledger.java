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
}
