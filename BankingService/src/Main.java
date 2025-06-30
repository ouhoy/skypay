//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Abdallah DAHMOU Banking Service Demo for Skypay");
        System.out.println("===================");

        Account account = new Account();

        try {
            // Simulate the acceptance test scenario
            account.deposit(1000);
            System.out.println("Deposited 1000");

            account.deposit(2000);
            System.out.println("Deposited 2000");

            account.withdraw(500);
            System.out.println("Withdrew 500");

            System.out.println("\nCurrent balance: " + account.getBalance());

            System.out.println("\nBank Statement:");
            account.printStatement();

            // Test exception handling
            System.out.println("\nTesting exception handling:");
            try {
                account.withdraw(5000); // Should throw exception
            } catch (IllegalArgumentException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

            try {
                account.deposit(-100); // Should throw exception
            } catch (IllegalArgumentException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}