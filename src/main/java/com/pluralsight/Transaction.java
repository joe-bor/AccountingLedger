package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private Product product;

    // CONSTRUCTOR(S):
    public Transaction(LocalDate transactionDate, LocalTime transactionTime, Product product) {
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.product = product;
    }

    public Transaction(Product product) {
        this(LocalDate.now(), LocalTime.now(), product);
    }


    // GETTERS & SETTERS:
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // OTHER METHODS:
    public void logToCSVFile() {
        String dateStr = this.getTransactionDate().toString();
        String timeStr = this.getTransactionTime().truncatedTo(ChronoUnit.SECONDS).toString();
        String description = this.getProduct().description();
        String vendor = this.getProduct().vendor();
        float price = this.getProduct().price();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            bufferedWriter.append(String.format("%s|%s|%s|%s|%.2f\n", dateStr, timeStr, description, vendor, price));
            System.out.println("Success! Transaction recorded to `transactions.csv`");
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public String toString() {
        return String.format("%s || Transaction Date: %s || Transaction Time: %s", this.getProduct(), this.getTransactionDate(), this.getTransactionTime());
    }
}
