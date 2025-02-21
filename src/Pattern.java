class Pattern implements Runnable {
    private final char symbol;
    private static final Object lock = new Object();
    private static int currentThread = 0;
    private final int threadNumber;
    private final int totalThreads;
    private final int iterations;

    public Pattern(char symbol, int threadNumber, int totalThreads, int iterations) {
        this.symbol = symbol;
        this.threadNumber = threadNumber;
        this.totalThreads = totalThreads;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            synchronized (lock) {
                while (currentThread != threadNumber) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.print(symbol);
                currentThread = (currentThread + 1) % totalThreads;
                lock.notifyAll();
            }
        }
    }
}