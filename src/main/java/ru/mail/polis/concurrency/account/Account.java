package ru.mail.polis.concurrency.account;

public class Account {
    private long balance;

    public Account(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        checkPositiveSum(amount);
        synchronized (this) {
            balance = balance + amount;
        }
    }

    public void withdraw(long amount) {
        checkPositiveSum(amount);
        synchronized (this) {
            if (balance < amount) {
                throw new IllegalArgumentException("Недостаточно средств");
            }
            balance = balance - amount;
        }
    }
//
//    public void waitAndWithdraw(long amount) throws InterruptedException {
//        checkPositiveSum(amount);
//        synchronized (this) {
//            while (balance < amount) {
//                wait();
//            }
//            balance -= amount;
//        }
//    }

    private static void checkPositiveSum(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должа быть положительной");
        }
    }
}
