package DiningPhilosopher;

import MutualExclusion.BinarySemaphore;

public class Fork extends BinarySemaphore {

    protected int id;

    public Fork(int id) {
        this.id = id;
    }
}
