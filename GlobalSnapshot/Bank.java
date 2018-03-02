package GlobalSnapshot;

import utils.Util;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private int numAccounts;
    private ArrayList<Account> accounts;

    private Random rand;

    public Bank(int numAccounts, int initialBalance) {
        this.numAccounts = numAccounts;
        accounts = new ArrayList<>();

        rand = new Random();

        for (int i = 0; i < numAccounts; i++)
            accounts.add(new Account(i, initialBalance));

        for (int i = 0; i < numAccounts; i++)
            for (int j = 0; j < numAccounts; j++)
                if (i != j) accounts.get(i).addPayee(accounts.get(j));

        for (int i = 0; i < numAccounts; i++)
            new Thread(accounts.get(i)).start();
    }

    public void takeGlobalSnapshot() {
        getRandomAccount().takeGlobalSnapshot();
    }

    public static void main(String[] args) {
        int numAccounts = 3;

        Bank bank = new Bank(numAccounts, 100);

        Util.sleep(5000);

        bank.takeGlobalSnapshot();
    }

    private Account getRandomAccount() {
        return accounts.get(rand.nextInt(numAccounts));
    }
}
