package com.pluralsight;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Report {
    private boolean isReportsScreenShown = false;
    private List<Transaction> transactionList = new ArrayList<>();

    // CONSTRUCTOR(S):
    Report(List<Transaction> transactionList){
        this.transactionList = transactionList;
    }

    // GETTERS & SETTERS:
    public boolean isReportsScreenShown() {
        return isReportsScreenShown;
    }

    public void setReportsScreenShown(boolean reportsScreenShown) {
        isReportsScreenShown = reportsScreenShown;
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
                    
                    ----- Reports Screen -----
                    Which reports would you like to see?
                    
                    [1] - Month To Date
                    [2] - Previous Month
                    [3] - Year To Date
                    [4] - Previous Year
                    [5] - Search By Vendor
                    [0] - Back
                    
                    """);
            this.setReportsScreenShown(true);
            executeReportOptions();
        } while (this.isReportsScreenShown());
    }

    public void executeReportOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        switch (option) {
            case "1" -> showMonthToDate();
            case "2" -> showPreviousMonth();
            case "3" -> showYearToDate();
            case "4" -> System.out.println("Previous Year");
            case "5" -> System.out.println("Search By Vendor");
            case "0" -> quitReportScreen();
            default -> System.err.println("Invalid Options. Please Try Again! ");
        }
    }

    public void showMonthToDate() {
        Month currentMonth = LocalDate.now().getMonth();
        int foundTransactionCount = 0;

        System.out.println(String.format("Showing all entries for the month of %s: ", currentMonth));
        for (Transaction transaction : this.getTransactionList()){
            if (transaction.getTransactionDate().getMonth() == currentMonth){
                System.out.println(transaction);
                foundTransactionCount++;
            }
        }

        if (foundTransactionCount == 0) {
            System.out.println("No entries found.");
        }


    }

    public void showPreviousMonth() {
        Month previousMonth = LocalDate.now().getMonth().minus(1);
        int foundTransactionCount = 0;

        System.out.println(String.format("Showing all entries for the month of %s: ", previousMonth));
        for (Transaction transaction : this.getTransactionList()){
            if (transaction.getTransactionDate().getMonth() == previousMonth){
                System.out.println(transaction);
                foundTransactionCount++;
            }
        }

        if (foundTransactionCount == 0) {
            System.out.println("No entries found.");
        }
    }

    public void showYearToDate() {
        int currentMonth = LocalDate.now().getMonthValue();
        int foundTransactionCount = 0;

        System.out.println("Here are all the entries for the current year:");
        for (Transaction transaction : this.getTransactionList()){
            if (transaction.getTransactionDate().getMonthValue() <= currentMonth){
                System.out.println(transaction);
                foundTransactionCount++;
            }
        }

        if (foundTransactionCount == 0) {
            System.out.println("No entries found.");
        }
    }

    public void showByVendor() {
    }

    public void searchByVendor() {
    }

    public void quitReportScreen() {
        System.out.println("Switching to Ledger Screen...");
        this.setReportsScreenShown(false);
    }
}

