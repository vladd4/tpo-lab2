class BankSync3 {
    private final int[] accounts;
    private long ntransacts = 0;
    public static final int NTEST = 10000;
    private final Object[] locks;

    public BankSync3(int n, int initialBalance) {
        accounts = new int[n];
        locks = new Object[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
            locks[i] = new Object();
        }
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) {
        int first = Math.min(from, to);
        int second = Math.max(from, to);

        synchronized(locks[first]) {
            synchronized(locks[second]) {
                accounts[from] -= amount;
                accounts[to] += amount;
                synchronized(this) {
                    ntransacts++;
                    if (ntransacts % NTEST == 0) test();
                }
            }
        }
    }

    public synchronized void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i];
        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }
}