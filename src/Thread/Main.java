package Thread;

public class Main {
    public static void main(String[] args) {
        Thread subTask1 = new Thread1();
        Thread subTask2 = new Thread2();
        subTask1.start();
        subTask2.start();
    }
}
