package synchronization;

import mutex.BinarySemaphore;
import utils.Util;

public class DiningPhilosopher implements Resource {

    protected int n = 0;
    protected BinarySemaphore[] fork;

    public DiningPhilosopher(int n) {
        this.n = n;

        fork = new BinarySemaphore[n];
        for (int i = 0; i < n; i++) {
            fork[i] = new BinarySemaphore(true);
        }
    }

    public void acquire(int i) {
        int left = i;
        int right = (i + 1) % n;

        fork[left].P();
        System.out.println("Phil " + i + " grab fork " + left);

        Util.sleep(1000);

        fork[right].P();
        System.out.println("Phil " + i + " grab fork " + right);
    }

    public void release(int i) {
        int left = i;
        int right = (i + 1) % n;

        fork[left].V();
        System.out.println("Phil " + i + " put down fork " + left);

        Util.sleep(1000);

        fork[right].V();
        System.out.println("Phil " + i + " put down fork " + right);
    }

    public static void main(String[] args) {
        DiningPhilosopher dp = new DiningPhilosopher(5);
        for (int i = 0; i < 5; i++) {
            new Philosopher(i, dp);
        }
    }
}
