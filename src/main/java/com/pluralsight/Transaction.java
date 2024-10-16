package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate transactionDate;
    private LocalTime transactionTime;
    private Product product;

    public Transaction(LocalDate transactionDate, LocalTime transactionTime, Product product) {
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.product = product;
    }

    public Transaction(Product product) {
        this(LocalDate.now(), LocalTime.now(), product);
    }

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

    @Override
    public String toString() {
        return String.format("%s || Transaction Date: %s || Transaction Time: %s", this.getProduct(), this.getTransactionDate(), this.getTransactionTime());
    }
}
