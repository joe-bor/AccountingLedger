package com.pluralsight;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

public class Report {
    private boolean isReportsScreenShown = false;
    private List<Transaction> transactionList;

    // CONSTRUCTOR(S):
    Report(List<Transaction> transactionList) {
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
                    [6] - Custom Search
                    [7] - Year Overview
                    [8] - Month Overview
                    [0] - Back
                    
                    """);
            this.setReportsScreenShown(true);
            executeReportOptions();
        } while (this.isReportsScreenShown());
    }

    public void executeReportOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = Utils.validateString(scanner);

        switch (option) {
            case "1" -> showMonthToDate();
            case "2" -> showPreviousMonth();
            case "3" -> showYearToDate();
            case "4" -> showPreviousYear();
            case "5" -> showSearchByVendor();
            case "6" -> customSearch();
            case "7" -> yearlyOverview();
            case "8" -> monthlyOverview();
            case "0" -> quitReportScreen();
            default -> System.err.println("Invalid Options. Please Try Again! ");
        }
    }

    public void showMonthToDate() {
        Month currentMonth = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();
        int foundTransactionCount = 0;

        System.out.println(String.format("Showing all entries for the month of %s: ", currentMonth));
        for (Transaction transaction : this.getTransactionList()) {
            if (transaction.getTransactionDate().getMonth() == currentMonth && transaction.getTransactionDate().getYear() == currentYear) {
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
        for (Transaction transaction : this.getTransactionList()) {
            if (transaction.getTransactionDate().getMonth() == previousMonth) {
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
        for (Transaction transaction : this.getTransactionList()) {
            if (transaction.getTransactionDate().getMonthValue() <= currentMonth) {
                System.out.println(transaction);
                foundTransactionCount++;
            }
        }

        if (foundTransactionCount == 0) {
            System.out.println("No entries found.");
        }
    }

    public void showPreviousYear() {
        int previousYear = LocalDate.now().getYear() - 1;
        int foundTransactionCount = 0;

        System.out.printf("Showing transactions from last year (%d)\n", previousYear);
        for (Transaction transaction : this.getTransactionList()) {
            if (transaction.getTransactionDate().getYear() == previousYear) {
                System.out.println(transaction);
                foundTransactionCount++;
            }
        }

        if (foundTransactionCount == 0) {
            System.out.println("No entries found.");
        }
    }

    public void showSearchByVendor() {
        System.out.println("Please provide the vendor name: ");
        Scanner scanner = new Scanner(System.in);
        String vendorName = Utils.validateString(scanner);
        int foundTransactionCount = 0;

        System.out.println("Transactions sorted by provided vendor name: ");
        for (Transaction transaction : this.getTransactionList()) {
            if (transaction.getProduct().vendor().contains(vendorName)) {
                System.out.println(transaction);
                foundTransactionCount++;
            }
        }

        if (foundTransactionCount == 0) {
            System.out.println("No entries found.");
        }
    }

    public void quitReportScreen() {
        System.out.println("Switching to Ledger Screen...");
        this.setReportsScreenShown(false);
    }

    public void customSearch() {
        System.out.println("""
                            ----- Custom Search -----
                Looking to narrow down the search results?
                
                You can filter the entries with the following fields:
                - Start Date
                - End Date
                - Description
                - Vendor
                - Amount
                (If a field is left BLANK, it will not be part of the filter.)
                
                """);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine().trim();

        System.out.print("End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine().trim();

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Vendor:");
        String vendor = scanner.nextLine().trim();

        System.out.print("Amount: ");
        String amountStr = scanner.nextLine().trim();
        float amount = 0;
        try {
            amount = !amountStr.isBlank() ? Float.parseFloat(amountStr) : 0f;
        } catch (NumberFormatException e) {
            System.err.println(e);
        }

        // Creating a copy here so data and csv stays in-sync
        List<Transaction> copyOfOriginalList = List.copyOf(this.getTransactionList());

        if (!startDate.isBlank()) {
            copyOfOriginalList = CustomSearch.filterByStartDate(copyOfOriginalList, LocalDate.parse(startDate));
        }

        if (!endDate.isBlank()) {
            copyOfOriginalList = CustomSearch.filterByEndDate(copyOfOriginalList, LocalDate.parse(endDate));
        }

        if (!description.isBlank()) {
            copyOfOriginalList = CustomSearch.filterByDescription(copyOfOriginalList, description);
        }

        if (!vendor.isBlank()) {
            copyOfOriginalList = CustomSearch.filterByVendor(copyOfOriginalList, vendor);
        }

        if (!amountStr.isBlank()) {
            copyOfOriginalList = CustomSearch.filterByPrice(copyOfOriginalList, amount);
        }

        // Print out filtered list
        System.out.printf("\n%d transactions found.\n", copyOfOriginalList.size());
        for (Transaction transaction : copyOfOriginalList) {
            System.out.println(transaction);
        }

    }

    public void monthlyOverview(){
        Month currMonth = LocalDate.now().getMonth();
        System.out.printf("""
                            --- %s Overview ---
                How is your month going?
                Let's have a look:
                
                """, currMonth);

        float totalDeposits = this.getTransactionList().stream()
                .filter(transaction -> transaction.getTransactionDate().getYear() == LocalDate.now().getYear())
                .filter(transaction -> transaction.getTransactionDate().getMonthValue() == LocalDate.now().getMonthValue())
                .filter(transaction -> transaction.getProduct().price() > 0)
                .map(transaction -> transaction.getProduct().price())
                .reduce(0f, (acc, curr) -> acc + curr);

        float totalPayments = this.getTransactionList().stream()
                .filter(transaction -> transaction.getTransactionDate().getYear() == LocalDate.now().getYear())
                .filter(transaction -> transaction.getTransactionDate().getMonthValue() == LocalDate.now().getMonthValue())
                .filter(transaction -> transaction.getProduct().price() < 0)
                .map(transaction -> transaction.getProduct().price())
                .reduce(0f, (acc, curr) -> acc + curr);

        float netIncome = totalPayments + totalDeposits;

        System.out.printf("Total Income: %.2f\n", totalDeposits);
        System.out.printf("Total Expenses: %.2f\n", totalPayments);
        System.out.printf("Net Income for the year: %.2f\n", netIncome);
    }

    public void yearlyOverview() {
        System.out.println("""
                            --- Yearly Income & Net Overview ---
                Curious about how your finances are looking this year?
                Look at the breakdown below:
                
                """);

        float totalDeposits = this.getTransactionList().stream()
                .filter(transaction -> transaction.getTransactionDate().getYear() == LocalDate.now().getYear())
                .filter(transaction -> transaction.getProduct().price() > 0)
                .map(transaction -> transaction.getProduct().price())
                .reduce(0f, (acc, curr) -> acc + curr);

        float totalPayments = this.getTransactionList().stream()
                .filter(transaction -> transaction.getTransactionDate().getYear() == LocalDate.now().getYear())
                .filter(transaction -> transaction.getProduct().price() < 0)
                .map(transaction -> transaction.getProduct().price())
                .reduce(0f, (acc, curr) -> acc + curr);

        float netIncome = totalPayments + totalDeposits;

        System.out.printf("Total Income: %.2f\n", totalDeposits);
        System.out.printf("Total Expenses: %.2f\n", totalPayments);
        System.out.printf("Net Income for the year: %.2f\n", netIncome);
    }
}

