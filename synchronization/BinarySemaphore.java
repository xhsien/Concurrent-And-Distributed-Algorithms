package mutex;

import utils.Util;

public class BinarySemaphore {
    private boolean value;

    public BinarySemaphore(boolean iv) {
        value = iv;
    }

    public synchronized void P() {
        while (value == false)
            Util.wait(this);
        value = false;
    }

    public synchronized void V() {
        value = true;
        notify();
    }
}
