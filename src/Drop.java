public class Drop {
    private Integer number;
    private boolean empty = true;

    public synchronized Integer take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Integer result = number;

        number = null;
        empty = true;

        notifyAll();
        return result;
    }

    public synchronized void put(Integer number) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        this.number = number;
        empty = false;
        notifyAll();
    }
}