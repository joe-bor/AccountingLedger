package com.pluralsight;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private boolean isAppRunning = true;

    public static void main(String[] args) {
        homeScreen();
    }

    static void homeScreen() {
        System.out.println("""
                ----- HOME SCREEN -----
                What would you like to do?
                [D] - Add Deposit
                [P] - Make Payment
                [L] - Ledger Screen
                [X] - Exit
                """);
    }
}
