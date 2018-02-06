package ReaderWriter;

import utils.Util;

public class StarvingWriter extends Writer {

    public StarvingWriter(int id, Driver driver) {
        super(id, driver);
    }

    public void startWrite() {
        synchronized (driver) {
            System.out.println("Writer " + id + " is trying to write.");
            while (driver.numReader > 0 || driver.numWriter > 0) {
                Util.wait(driver);
            }
            driver.numWriter++;
        }
    }

    public void endWrite() {
        synchronized (driver) {
            System.out.println("Writer " + id + " finished writing.");
            driver.numWriter = 0;
            driver.notifyAll();
        }
    }
}
