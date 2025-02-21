public class Main {
    public static void main(String[] args) {
        int arraySize = 100;

        System.out.println("Starting Producer-Consumer test with array size: " + arraySize);
        System.out.println("----------------------------------------");

        Drop drop = new Drop();
        Thread producerThread = new Thread(new Producer(drop, arraySize));
        Thread consumerThread = new Thread(new Consumer(drop));

        long startTime = System.currentTimeMillis();

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("----------------------------------------");
        System.out.println("Total execution time: " + (endTime - startTime) + " ms");
    }
}