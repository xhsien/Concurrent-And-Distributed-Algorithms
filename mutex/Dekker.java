package mutex;

public class Dekker implements Lock {
    private volatile boolean wantCS[] = {false, false};
    private volatile int turn = 1;

    public void requestCS(int i) {
        int j = 1 - i;
        wantCS[i] = true;
        while (wantCS[j]) {
            if (turn == j) {
                wantCS[i] = false;
                while (turn == j);
                wantCS[i] = true;
            }
        }
    }

    public void releaseCS(int i) {
        turn = 1 - i;
        wantCS[i] = false;
    }
}
