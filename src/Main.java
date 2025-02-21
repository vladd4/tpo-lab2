public class Main {
    public static void main(String[] args) {
        System.out.println("Частина 1: Неконтрольоване виведення");
        System.out.println("=====================================");

        for (int i = 0; i < 90; i++) {
            Thread t1 = new Thread(new Symbol('|', 1));
            Thread t2 = new Thread(new Symbol('\\', 1));
            Thread t3 = new Thread(new Symbol('/', 1));

            t1.start();
            t2.start();
            t3.start();

            try {
                t1.join();
                t2.join();
                t3.join();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nЧастина 2: Контрольоване виведення патерну");
        System.out.println("================================================");

        int iterations = 90;
        Thread pt1 = new Thread(new Pattern('|', 0, 3, iterations));
        Thread pt2 = new Thread(new Pattern('\\', 1, 3, iterations));
        Thread pt3 = new Thread(new Pattern('/', 2, 3, iterations));

        pt1.start();
        pt2.start();
        pt3.start();

        try {
            pt1.join();
            pt2.join();
            pt3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}