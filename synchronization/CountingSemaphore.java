package synchronization;

import utils.Util;

public class CountingSemaphore {
    private int value;

    public CountingSemaphore(int iv) {
        value = iv;
    }

    public synchronized void P() {
        value--;
        if (value < 0)
            Util.wait(this);
    }

    public synchronized void V() {
        value++;
        if (value <= 0)
            notify();
    }
}
