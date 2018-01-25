package mutex;

public class Bakery implements Lock {
    private int n_process;
    private volatile boolean[] choosing;
    private volatile int[] number;

    protected boolean smaller(int j, int i) {
        return number[j] < number[i] || (number[j] == number[i] && j < i);
    }

    public Bakery(int n_process) {
        this.n_process = n_process;
        choosing = new boolean[n_process];
        number = new int[n_process];
        for (int j = 0; j < n_process; j++) {
            choosing[j] = false;
            number[j] = 0;
        }
    }

    public void requestCS(int i) {
        choosing[i] = true;
        for (int j = 0; j < n_process; j++) {
            if (number[j] > number[i]) {
                number[i] = number[j];
            }
        }
        number[i]++;
        choosing[i] = false;

        for (int j = 0; j < n_process; j++) {
            while (choosing[j]);
            while (number[j] != 0 && smaller(j, i));
        }
    }

    public void releaseCS(int i) {
        number[i] = 0;
    }
}
