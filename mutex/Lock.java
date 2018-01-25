package mutex;

public interface Lock {
    void requestCS(int id);
    void releaseCS(int id);
}
