class TransferThread extends Thread {
    private final Object bank;
    private final int fromAccount;
    private final int maxAmount;
    private static final int REPS = 100;

    public TransferThread(Object b, int from, int max) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < REPS; i++) {
                int toAccount = (int) (((Bank)bank).size() * Math.random());
                int amount = (int) (maxAmount * Math.random()/REPS);
                ((Bank)bank).transfer(fromAccount, toAccount, amount);
            }
        }
    }
}