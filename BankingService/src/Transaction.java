import java.time.LocalDate;

public class Transaction {
    private final LocalDate date;
    private final int amount;
    private final int balance;
    private final String type;

    public Transaction(LocalDate date, int amount, int balance, String type) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    public String getType() {
        return type;
    }
}
