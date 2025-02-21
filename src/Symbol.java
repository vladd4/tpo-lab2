class Symbol implements Runnable {
    private final char symbol;
    private final int count;

    public Symbol(char symbol, int count) {
        this.symbol = symbol;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.print(symbol);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}