import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        lock.lock();  // Acquiring the lock
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited $" + amount + " | New Balance: $" + balance);
        } finally {
            lock.unlock(); // Releasing the lock
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew $" + amount + " | New Balance: $" + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " attempted to withdraw $" + amount + " | Insufficient funds!");
            }
        } finally {
            lock.unlock();
        }
    }
}

class BankTransaction implements Runnable {
    private final BankAccount account;
    private final boolean isDeposit;
    private final double amount;

    public BankTransaction(BankAccount account, boolean isDeposit, double amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class MultiThreadedBanking {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Thread t1 = new Thread(new BankTransaction(account, true, 500), "Thread-1");
        Thread t2 = new Thread(new BankTransaction(account, false, 200), "Thread-2");
        Thread t3 = new Thread(new BankTransaction(account, false, 800), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}