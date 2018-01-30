package synchronization;

import mutex.BinarySemaphore;

public class DiningPhilosopher implements Resource {

    private int n = 0;
    private BinarySemaphore[] fork;

    public DiningPhilosopher(int n) {
        this.n = n;

        fork = new BinarySemaphore[n];
        for (int i = 0; i < n; i++) {
            fork[i] = new BinarySemaphore(true);
        }
    }

    public void acquire(int i) {
        int left = Math.min(i, (i + 1) % n);
        int right = Math.max(i, (i + 1) % n);

        fork[left].P();
        fork[right].P();
    }

    public void release(int i) {
        int left = Math.min(i, (i + 1) % n);
        int right = Math.max(i, (i + 1) % n);

        fork[left].V();
        fork[right].V();
    }

    public static void main(String[] args) {
        DiningPhilosopher dp = new DiningPhilosopher(5);
        for (int i = 0; i < 5; i++) {
            new Philosopher(i, dp);
        }
    }
}
