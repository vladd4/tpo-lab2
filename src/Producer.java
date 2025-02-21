import java.util.Random;
public class Producer implements Runnable {
    private final Drop drop;
    private final int arraySize;

    public Producer(Drop drop, int arraySize) {
        this.drop = drop;
        this.arraySize = arraySize;
    }

    public void run() {
        Random random = new Random();
        try {
            for (int i = 0; i < arraySize; i++) {
                int number = random.nextInt(1000);
                System.out.println("Producing: " + number);
                drop.put(number);
                // Даємо час споживачу обробити число
                Thread.sleep(50);
            }
            // Сигнал завершення
            drop.put(-1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}