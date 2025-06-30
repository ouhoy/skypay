import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Account implements AccountService {
    private int balance;
    private final ArrayList<Transaction> transactions;
    private final DateTimeFormatter dateFormatter;

    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        balance += amount;
        transactions.add(new Transaction(LocalDate.now(), amount, balance, "DEPOSIT"));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        
        balance -= amount;
        transactions.add(new Transaction(LocalDate.now(), amount, balance, "WITHDRAWAL"));
    }

    @Override
    public void printStatement() {
        System.out.println("DATE       | AMOUNT  | BALANCE");
        
        // Print transactions in reverse order (most recent first)
        ArrayList<Transaction> reversedTransactions = new ArrayList<>(transactions);
        Collections.reverse(reversedTransactions);
        
        for (Transaction transaction : reversedTransactions) {
            String formattedDate = transaction.getDate().format(dateFormatter);
            String amountStr = transaction.getType().equals("DEPOSIT") ? 
                String.valueOf(transaction.getAmount()) : 
                "-" + transaction.getAmount();
            
            System.out.printf("%-10s | %-7s | %d%n", 
                formattedDate, amountStr, transaction.getBalance());
        }
    }

    public int getBalance() {
        return balance;
    }
}
