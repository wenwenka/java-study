package ThreadEx;
import java.util.*;

public class ThreadLocalEx {
    static ThreadLocal<Integer> status = ThreadLocal.withInitial(() -> 0);//设置ThreadLocal变量的默认值

    //各个线程分别打印threadLocal变量
    static void printThreadLocal() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " status: " + status.get());//默认值为0
            status.set(1);
            System.out.println(Thread.currentThread().getName() + " status: "+status.get());
        },"t1");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " status: " + status.get());
            status.set(2);
            System.out.println(Thread.currentThread().getName() + " status: " + status.get());
        },"t2");

        t1.start();
        t2.start();
    }


    public static void main(String[] args) {
        printThreadLocal();

    }
}
