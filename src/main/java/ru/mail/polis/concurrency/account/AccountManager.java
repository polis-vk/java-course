package ru.mail.polis.concurrency.account;

public class AccountManager {

//    public static void main(String[] args) throws InterruptedException {
////        race();
//        waitAccount();
//    }

    private static void race() throws InterruptedException {
        Account account = new Account(200_000_000);
        System.out.println("Start balance = " + account.getBalance());

        Thread positive = new Thread(() -> positive(account));
        Thread negative = new Thread(() -> negative(account));
        positive.start();
        negative.start();

        positive.join();
        negative.join();

        System.out.println("Finish balance = " + account.getBalance());
    }

    private static void waitAccount() throws InterruptedException {
        Account account = new Account(0);
        System.out.println("Start balance = " + account.getBalance());

        Thread positive = new Thread(() -> positive(account));
        positive.start();

        System.out.println("waiting");
//        account.waitAndWithdraw(900_000_000);

        positive.join();

        System.out.println("Finish balance = " + account.getBalance());
    }

    public static void positive(Account account) {
        for (int i = 0; i < 1_000_000_000; i++) {
            account.deposit(1);
        }
    }

    public static void negative(Account account) {
        for (int i = 0; i < 10_000_000; i++) {
            account.withdraw(1);
        }
    }

    public static void transfer(Account in, Account out, long sum) {
        out.withdraw(sum);
        in.deposit(sum);
    }

    public static void main(String[] args) throws InterruptedException {
        Account account1 = new Account(1000);
        Account account2 = new Account(1000);
        Account account3 = new Account(1000);

        new Thread(() -> transfer(account1, account2, 100)).start();
        new Thread(() -> transfer(account2, account1, 100)).start();
        new Thread(() -> transfer(account3, account1, 100)).start();
        new Thread(() -> transfer(account1, account2, 100)).start();
        new Thread(() -> transfer(account2, account3, 100)).start();
        new Thread(() -> transfer(account3, account1, 100)).start();
        Thread.sleep(1000);
        System.out.println(account1.getBalance() + " " + account2.getBalance() + " " + account3.getBalance());
    }
}
