package synchronization;

import utils.Util;

public class Reader implements Runnable {

    private int id;
    private ReaderWriterMonitor monitor;

    public Reader(int id, ReaderWriterMonitor monitor) {
        this.id = id;
        this.monitor = monitor;

        new Thread(this).start();
    }

    public void readDB() {
        synchronized (monitor) {
            System.out.println("Reader " + id + " is trying to read");
            while (monitor.numWriter > 0) {
                Util.wait(monitor);
            }
            monitor.numReader++;
        }

        System.out.println("Reader " + id + " is reading");
        Util.sleep(1000);

        synchronized (monitor) {
            System.out.println("Reader " + id + " is done reading");
            monitor.numReader--;
            monitor.notify();
        }
    }

    public void run() {
        while (true) {
            readDB();
        }
    }
}
