package ru.mail.polis.concurrency.work;

public class Account implements Comparable<Account> {

    private final String id;
    private volatile long balance;

    Account(String id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    void deposit(long amount) {
        checkPositiveSum(amount);
        balance = balance + amount;
    }

    void withdraw(long amount) {
        checkPositiveSum(amount);
        if (balance < amount) {
            throw new IllegalArgumentException("Недостаточно средств");
        }
        balance = balance - amount;
    }

    private static void checkPositiveSum(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должа быть положительной");
        }
    }

    @Override
    public int compareTo(Account o) {
        return id.compareTo(o.id);
    }
}
