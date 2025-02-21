import java.util.concurrent.locks.ReentrantLock;

class BankSync2 {
    private final int[] accounts;
    private long ntransacts = 0;
    public static final int NTEST = 10000;
    private final ReentrantLock lock = new ReentrantLock();

    public BankSync2(int n, int initialBalance) {
        accounts = new int[n];
        for (int i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) {
        lock.lock();
        try {
            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            if (ntransacts % NTEST == 0) test();
        } finally {
            lock.unlock();
        }
    }

    public void test() {
        lock.lock();
        try {
            int sum = 0;
            for (int i = 0; i < accounts.length; i++)
                sum += accounts[i];
            System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }
}
