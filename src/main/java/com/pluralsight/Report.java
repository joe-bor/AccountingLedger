package com.pluralsight;

import java.util.Scanner;

public class Report {
    private boolean isReportsScreenShown = false;

    public boolean isReportsScreenShown() {
        return isReportsScreenShown;
    }

    public void setReportsScreenShown(boolean reportsScreenShown) {
        isReportsScreenShown = reportsScreenShown;
    }

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
            case "1" -> System.out.println("Month To Date");
            case "2" -> System.out.println("Previous Month");
            case "3" -> System.out.println(" Year To Date");
            case "4" -> System.out.println("Previous Year");
            case "5" -> System.out.println("Search By Vendor");
            case "0" -> quitReportScreen();
            default -> System.err.println("Invalid Options. Please Try Again! ");
        }
    }

    public void showMontToDate() {
    }

    public void showPreviousMonth() {
    }

    public void showYearToDate() {
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

