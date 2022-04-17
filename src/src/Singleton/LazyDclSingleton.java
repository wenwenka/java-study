package Singleton;

public class LazyDclSingleton {
    //懒汉式双重校验锁
    private static volatile LazyDclSingleton instance = null;
    private LazyDclSingleton(){}

    public static LazyDclSingleton getInstance() {
        if(instance == null) {
            synchronized (LazyDclSingleton.class) {
                if(instance == null) {
                    instance = new LazyDclSingleton();
                }
            }
        }
        return instance;
    }

    //测试
    public static void main(String[] args) {
        //测试
        LazyDclSingleton s1 = LazyDclSingleton.getInstance();
        LazyDclSingleton s2 = LazyDclSingleton.getInstance();
        System.out.println(s1 == s2);

        //多线程测试
        new Thread(() -> {
            System.out.println(LazyDclSingleton.getInstance().hashCode());
        }).start();

        new Thread(() -> {
            System.out.println(LazyDclSingleton.getInstance().hashCode());
        }).start();
    }
}
