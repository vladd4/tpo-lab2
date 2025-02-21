public class Drop {
    private Integer number;
    private boolean empty = true;

    public synchronized Integer take() {
        // Чекаємо, поки з'явиться число
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        // Зберігаємо число перед очищенням
        Integer result = number;
        // Очищаємо дані
        number = null;
        empty = true;
        // Сповіщаємо виробника
        notifyAll();
        return result;
    }

    public synchronized void put(Integer number) {
        // Чекаємо, поки споживач забере попереднє число
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        // Зберігаємо нове число
        this.number = number;
        empty = false;
        // Сповіщаємо споживача
        notifyAll();
    }
}