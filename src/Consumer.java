public class Consumer implements Runnable {
    private final Drop drop;
    private int count = 0;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        try {
            Integer number = drop.take();
            while (number != null && number != -1) {
                System.out.println("Consuming: " + number);
                count++;
                // Невелика затримка перед наступним читанням
                Thread.sleep(50);
                number = drop.take();
            }

            // Виводимо статистику
            System.out.println("\nProcessing complete!");
            System.out.println("Total numbers processed: " + count);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}