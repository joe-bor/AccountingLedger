package com.pluralsight;

import java.util.Scanner;

public abstract class Utils {

    public static void main(String[] args) {
        System.out.println(titleCase("heLLo how are yOu guys toDay"));
        System.out.println(titleCase("! wHAT is thE we4ther TOday?"));
    }

    /*
    This function capitalizes the first character of each word in a provided String
    */
    public static String titleCase(String phrase){
        if (phrase.trim().isBlank()){
            return phrase;
        }

        String[] strArray = phrase.trim().toLowerCase().split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : strArray){

            String[] wordArr = word.split(""); // split to chars
            wordArr[0] = wordArr[0].toUpperCase(); // capitalize the first char
            String newWord = String.join("",wordArr); // recombine
            stringBuilder.append(newWord);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    // The following functions are used to continuously prompt the user for values with correct data types
    public static String validateString(Scanner scanner){
        String input;
        while ((input = scanner.nextLine()).trim().isBlank()){
            System.out.println("Input can't be empty. Please try again");
        }
        return input.toUpperCase();
    }

    public static float validateFloat(Scanner scanner){
        float input = 0;

        try {
            while (!scanner.hasNextFloat()){
                System.out.println("Input must be parseable to float (eg. 456.99)");
                scanner.next();
            }
            input = scanner.nextFloat();
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println(e);
        }
        return input;
    }
}
