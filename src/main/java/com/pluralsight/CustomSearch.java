package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class CustomSearch {

    public static List<Transaction> filterByStartDate(List<Transaction> listToBeFiltered, LocalDate startDate){
        List<Transaction> filteredList = new ArrayList<>();

        for (Transaction transaction : listToBeFiltered){
            if (transaction.getTransactionDate().isAfter(startDate)){
                filteredList.add(transaction);
            }
        }
        return filteredList;
    }

    public static List<Transaction> filterByEndDate(List<Transaction> listToBeFiltered, LocalDate endDate){
        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction transaction : listToBeFiltered){
            if (transaction.getTransactionDate().isBefore(endDate)){
                filteredList.add(transaction);
            }
        }
        return filteredList;
    }

    public static List<Transaction> filterByDescription(List<Transaction> listToBeFiltered, String description){
        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction transaction : listToBeFiltered){
            if (transaction.getProduct().description().contains(description)){
                filteredList.add(transaction);
            }
        }
        return filteredList;
    }

    public static List<Transaction> filterByVendor(List<Transaction> listToBeFiltered, String vendorName){
        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction transaction : listToBeFiltered){
            if (transaction.getProduct().description().contains(vendorName)){
                filteredList.add(transaction);
            }
        }
        return filteredList;
    }

    public static List<Transaction> filterByPrice(List<Transaction> listToBeFiltered, float price){
        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction transaction : listToBeFiltered){
            if (transaction.getProduct().price() == price){
                filteredList.add(transaction);
            }
        }
        return filteredList;
    }

}
