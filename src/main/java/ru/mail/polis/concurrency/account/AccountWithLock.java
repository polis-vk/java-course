package ru.mail.polis.concurrency.account;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithLock {

    private long balance;
    private Lock lock = new ReentrantLock();
    private Condition balanceIncreased = lock.newCondition();

    public AccountWithLock(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public void deposit(long amount) {
        checkPositiveSum(amount);
        lock.lock();
        try {
            balance += amount;
            balanceIncreased.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(long amount) {
        checkPositiveSum(amount);
        lock.lock();
        try {
            if (balance < amount) {
                throw new IllegalArgumentException("Недостаточно средств");
            }
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public void waitAndWithdraw(long amount) throws InterruptedException {
        checkPositiveSum(amount);
        lock.lock();
        try {
            while (balance < amount) {
                balanceIncreased.await();
            }
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    private static void checkPositiveSum(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должа быть положительной");
        }
    }
}
