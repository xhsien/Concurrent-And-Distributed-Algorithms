package synchronization;

public interface Resource {
    void acquire(int i);
    void release(int i);
}
