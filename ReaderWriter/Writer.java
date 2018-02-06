package ReaderWriter;

import utils.Util;

public abstract class Writer implements Runnable {

    protected int id;
    protected Driver driver;

    public Writer(int id, Driver driver) {
        this.id = id;
        this.driver = driver;
    }

    public abstract void startWrite();
    public abstract void endWrite();

    public void run() {
        while (true) {
            startWrite();
            System.out.println("Writer " + id + " is writing.");
            Util.sleep(500, 1000);
            endWrite();
            Util.sleep(500, 1000);
        }
    }
}
