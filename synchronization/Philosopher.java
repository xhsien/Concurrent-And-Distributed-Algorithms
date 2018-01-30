package synchronization;

public class Philosopher implements Runnable {

    private int id;
    private Resource r;

    public Philosopher(int id, Resource r) {
        this.id = id;
        this.r = r;

        new Thread(this).start();
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Phil " + id + " thinking");
                Thread.sleep(30);
                System.out.println("Phil " + id + " hungry");
                r.acquire(id);
                System.out.println("Phil " + id + " eating");
                Thread.sleep(40);
                r.release(id);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
