package ReaderWriter;

import utils.Util;

public class StarvingReader extends Reader {

    public StarvingReader(int id, Driver driver) {
        super(id, driver);
    }

    public void startRead() {
        synchronized (driver) {
            System.out.println("Reader " + id + " is trying to read.");
            while (driver.numWriter > 0) {
                Util.wait(driver);
            }
            driver.numReader++;
        }
    }

    public void endRead() {
        synchronized (driver) {
            System.out.println("Reader " + id + " finished reading.");
            driver.numReader--;
            driver.notify();
        }
    }
}
