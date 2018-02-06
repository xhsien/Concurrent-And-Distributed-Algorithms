package ReaderWriter;

import utils.Util;

import java.util.Queue;

public class GoodReader extends Reader {

    private Queue<Object> queue;
    public boolean okToGo;

    public GoodReader(int id, Driver driver, Queue<Object> queue) {
        super(id, driver);
        this.queue = queue;
    }

    public void startRead() {
        synchronized (queue) {
            System.out.println("Reader " + id + " is trying to read.");
            if (driver.numWriter > 0 || !queue.isEmpty()) {
                okToGo = false;
                queue.add(this);
            } else {
                okToGo = true;
                driver.numReader++;
            }
        }
        synchronized (this) {
            if (!okToGo)
                Util.wait(this);
        }
    }

    public void endRead() {
        synchronized (queue) {
            System.out.println("Reader " + id + " finished reading.");
            driver.numReader--;
            if (driver.numReader > 0) return;
            if (!queue.isEmpty()) {
                if (queue.peek() instanceof GoodWriter) {
                    driver.numWriter++;
                    GoodWriter request = (GoodWriter) queue.remove();
                    synchronized (request) {
                        request.okToGo = true;
                        request.notify();
                    }
                } else {
                    while (!queue.isEmpty() && queue.peek() instanceof Reader) {
                        driver.numReader++;
                        GoodReader request = (GoodReader) queue.remove();
                        synchronized (request) {
                            request.okToGo = true;
                            request.notify();
                        }
                    }
                }
            }
        }
    }
}
