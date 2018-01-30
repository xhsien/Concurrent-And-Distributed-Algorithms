package utils;

public class Util {
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }
    public static void wait(Object obj) {
        try {
            obj.wait();
        } catch (InterruptedException e) {}
    }
}
