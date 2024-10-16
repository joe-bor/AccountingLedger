# AccountingLedger

This is Java-based CLI application for tracking financial transactions. The app allows you to easily track deposits, payments and review your transactions with ease through the ledger.

---

## Requirements

- [Git](https://git-scm.com/downloads)
- [Java 17](https://www.oracle.com/th/java/technologies/downloads/) or Higher
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- A properly formatted [`transactions.csv`](#file-transactionscsv-format) file in the project directory
---

## Usage

1. Clone the repo:
```bash
git clone https://github.com/joe-bor/AccountingLedger.git
cd AccountingLedger
```

2. Launch Project in IntelliJ IDEA:
   - Click `Open` and select project directory.
   - Go to `Build` > `Build Project`

3. Run the Application:
   - Open `App.java` file insidei `src/com/pluralsight` directory.
   - Right-click on the `App` class and select `Run 'App.main()'`
   
4. Ready to interact with the CLI

---

## How It Works

- **Add Deposit(D)**: Record an incoming transaction with an amount, description, and vendor.
- **Make Payment(P)**: Log a payment transaction.
- **View Ledger(L)**: Shows options for displaying transactions (sorted by date in descending order, unless specified)
- **Exit(X)**: Terminate the program.

---

## Features

- Add Deposit: Easily log deposits.
- Make Payment: Record expenses and payments.
- View Ledger: Review all transactions.

---

## Limitations

- The project does not currently have unit tests
- The app assumes [`transactions.csv`](#file-transactionscsv-format) file is correctly formatted, with no validations.

---

## Screenshots


---

## File `transactions.csv` Format
Each entry to this file must follow the following structure:

```text
date|time|description|vendor|amount
2024-10-01|12:00|Netflix Subscription|Netflix|15.99
```

- **date**: The transaction date in `YYYY-MM-DD` format.
- **time**: The transaction time in `HH:mm` (24-hour) format.
- **description**: A brief description of the transaction.
- **vendor**: The name of the vendor/payee.
- **amount**: The transaction amount as a floating-point value.

