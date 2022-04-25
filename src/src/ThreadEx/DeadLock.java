package ThreadEx;

public class DeadLock {

    public static void main(String[] args) {
        DeadLock lock1 = new DeadLock();
        DeadLock lock2 = new DeadLock();
        new Thread(() -> {
            synchronized(lock1) {
                System.out.println(Thread.currentThread().getName() + " et lock1");
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(lock2) {
                    System.out.println(Thread.currentThread().getName() + " get lock2");
                }
            }
        },"thread 1").start();

        new Thread(() -> {
            synchronized(lock2) {
                System.out.println(Thread.currentThread().getName() + " get lock2");
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(lock1) {
                    System.out.println(Thread.currentThread().getName() + " get lock1");
                }
            }
        },"thread 2").start();
    }
}
