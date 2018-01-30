package synchronization;

import mutex.BinarySemaphore;
import utils.Util;

public class GoodDiningPhilosopher extends DiningPhilosopher {

    public GoodDiningPhilosopher(int n) {
        super(n);
    }

    public void acquire(int i) {
        int left = Math.min(i, (i + 1) % n);
        int right = Math.max(i, (i + 1) % n);

        fork[left].P();
        System.out.println("Phil " + i + " grab fork " + left);

        Util.sleep(1000);

        fork[right].P();
        System.out.println("Phil " + i + " grab fork " + right);
    }

    public void release(int i) {
        int left = Math.min(i, (i + 1) % n);
        int right = Math.max(i, (i + 1) % n);

        fork[left].V();
        System.out.println("Phil " + i + " put down fork " + left);

        Util.sleep(1000);

        fork[right].V();
        System.out.println("Phil " + i + " put down fork " + right);
    }

    public static void main(String[] args) {
        GoodDiningPhilosopher dp = new GoodDiningPhilosopher(5);
        for (int i = 0; i < 5; i++) {
            new Philosopher(i, dp);
        }
    }
}
