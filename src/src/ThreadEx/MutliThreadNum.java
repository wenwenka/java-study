package ThreadEx;

public class MutliThreadNum {
    //线程交替输出数字
    private static Object lock = new Object();
    public static int num = 0;

    static void printNum(int target) {
        while(num <= 50) {
            synchronized(lock){
                while(num % 3 != target) {
                    if(num > 50) break;
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(num > 50) break;
                System.out.print(Thread.currentThread().getName() + " : " + num + " ");
                num++;
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
//        MutliThreadNum mt = new MutliThreadNum();
        new Thread(() -> {
            printNum(0);
        },"thread-1").start();
        new Thread(() -> {
            printNum(1);
        },"thread-2").start();
        new Thread(() -> {
            printNum(2);
        },"thread-3").start();
    }
}
