package DiningPhilosopher;

import utils.Util;

import java.util.ArrayList;
import java.util.List;

public abstract class Philosopher implements Runnable {

    protected int id;
    protected List<Fork> forks;

    public Philosopher(int id, Fork... forks) {
        this.id = id;
        this.forks = new ArrayList<>();
        for (Fork fork : forks) {
            this.forks.add(fork);
        }
    }

    public abstract void acquire();
    public abstract void release();

    public void run() {
        while (true) {
            acquire();
            Util.sleep(1000);
            System.out.println("Philosopher " + id + " is eating.");
            release();
        }
    }
}
