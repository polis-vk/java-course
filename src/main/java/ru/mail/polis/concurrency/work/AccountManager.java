package ru.mail.polis.concurrency.work;

import java.util.concurrent.CountDownLatch;

public class AccountManager {

    static CountDownLatch countDownLatchStart = new CountDownLatch(100);
    static CountDownLatch countDownLatchFinish = new CountDownLatch(100);

    public static void transfer(Account in, Account out, long sum) {
        // a -> b (lock a); b -> a (lock b)
        // a -> b (lock a); b -> a (lock a)
        Account first = in.compareTo(out) > 0 ? in : out;
        Account second = first == in ? out : in;
        synchronized (first) {
            synchronized (second) {
                out.withdraw(sum);
                in.deposit(sum);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account("first", 1000);
        Account account2 = new Account("second", 1000);
        Account account3 = new Account("third", 1000);


        for (int i = 0; i < 50; i++) {
            new Thread( () -> transferWithCountDown(account1, account2, 100), "!!!!!!" + i).start();
            new Thread(() -> transferWithCountDown(account2, account1, 100), "?????" + i).start();
        }

        countDownLatchFinish.await();
//        Thread.sleep(1000);
        System.out.println(account1.getBalance() + " " + account2.getBalance() + " " + account3.getBalance());
    }

    private static void transferWithCountDown(Account in, Account out, long sum) {
        countDownLatchStart.countDown();
        try {
            countDownLatchStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        transfer(in, out, sum);
        countDownLatchFinish.countDown();
    }
}
