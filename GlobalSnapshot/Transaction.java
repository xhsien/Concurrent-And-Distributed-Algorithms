package GlobalSnapshot;

public class Transaction extends Event {

    private int amount;

    public Transaction(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
