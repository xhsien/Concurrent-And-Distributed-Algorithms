package synchronization;

import utils.Util;

public class Writer implements Runnable {

    private int id;
    private ReaderWriterMonitor monitor;

    public Writer(int id, ReaderWriterMonitor monitor) {
        this.id = id;
        this.monitor = monitor;

        new Thread(this).start();
    }

    public void writeDB() {
        synchronized (monitor) {
            System.out.println("Writer " + id + " is trying to write");
            while (monitor.numReader > 0 || monitor.numWriter > 0) {
                Util.wait(monitor);
            }
            monitor.numWriter = 1;
        }

        System.out.println("Writer " + id + " is writing");
        Util.sleep(1000);

        synchronized (monitor) {
            System.out.println("Writer " + id + " is done writing");
            monitor.numWriter = 0;
            monitor.notifyAll();
        }
    }

    public void run() {
        while (true) {
            writeDB();
        }
    }
}
