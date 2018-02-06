package ReaderWriter;

import utils.Util;

public abstract class Reader implements Runnable {

    protected int id;
    protected Driver driver;

    public Reader(int id, Driver driver) {
        this.id = id;
        this.driver = driver;
    }

    public abstract void startRead();
    public abstract void endRead();

    public void run() {
        while (true) {
            startRead();
            System.out.println("Reader " + id + " is reading.");
            Util.sleep(500, 1000);
            endRead();
            Util.sleep(500, 1000);
        }
    }
}
