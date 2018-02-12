package DiningPhilosopher;

import mutex.BinarySemaphore;

public class Fork extends BinarySemaphore {

    protected int id;

    public Fork(int id) {
        this.id = id;
    }
}
