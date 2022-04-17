package ThreadEx;

public class MultiThreadABC {
    //多线程交替输出ABC
    public static Object lock = new Object();
    public static int num = 0;

    void printABC(int target) {
        for(int i = 0;i < 10;i++) {
            synchronized(lock) {
                while(num % 3 != target) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                num++;
                System.out.print(Thread.currentThread().getName());
                lock.notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        MultiThreadABC mt = new MultiThreadABC();
        new Thread(() -> {
            mt.printABC(0);
        },"A").start();
        new Thread(() -> {
            mt.printABC(1);
        },"B").start();
        new Thread(() -> {
            mt.printABC(2);
        },"C").start();
    }

}
